/*---------------------------------------------------------------------------------------------
 *  Copyright (c) 2025 Huawei Technologies Co., Ltd. All rights reserved.
 *  This file is a part of the ModelEngine Project.
 *  Licensed under the MIT License. See License.txt in the project root for license information.
 *--------------------------------------------------------------------------------------------*/

package modelengine.elsa.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * elsa的整个图的快照
 *
 * @author 陈镕希
 * @since 2025-07-24
 */
public class Graph implements Snapshot<ShapeIdentity, Shape> {
    @Getter
    private final List<Page> pages = new ArrayList<>();
    @Getter
    private final Map<String, Object> properties = new HashMap<>();
    @Getter
    private final String id;
    @Getter
    @Setter
    private int sequence;
    //    private final ElsaSession session;

    public Graph(String id) {
        this.id = id;
        //        this.session = session;
    }

    public Page getPage(String pageId) {
        Optional<Page> page = this.pages.stream().filter(p -> p.getId().equals(pageId)).findFirst();
        return page.orElse(null);
        // return page.orElseGet(() -> this.newPage(pageId));
    }

    public Page newPage(String pageId) {
        return this.newPage(pageId, this.pages.size());
    }

    public Page newPage(String pageId, Integer index) {
        return this.pages.stream().filter(p -> Objects.equals(p.getId(), pageId)).findFirst().orElseGet(() -> {
            Page newPage = new Page(pageId, this);
            this.pages.add(index, newPage);
            return newPage;
        });
    }

    /**
     * 获取 {@link Page} 对象所在的下标.
     *
     * @param pageId page的id.
     * @return 下标值.
     */
    public int indexOfPage(String pageId) {
        return this.pages.stream().map(Page::getId).toList().indexOf(pageId);
    }

    /**
     * 通过 {@code index} 删除 {@link Page} 对象.
     *
     * @param index 下标.
     */
    public void removePageByIndex(int index) {
        this.pages.remove(index);
    }

    /**
     * 通过 {@code id} 删除 {@link Page} 对象.
     *
     * @param id 页面id.
     */
    public void removePageById(String id) {
        Page page = this.getPage(id);
        if (page == null) {
            throw new IllegalArgumentException("page[" + id + "] is not found.");
        }
        this.pages.remove(page);
    }

    @Override
    public Shape get(ShapeIdentity targetId) {
        Page page = this.getPage(targetId.getPage());
        return targetId.getShape() == null ? page : page.getShape(targetId.getShape());
    }

    public void changePageIndex(Integer fromIndex, Integer toIndex) {
        Page page = this.pages.get(fromIndex);
        this.pages.remove(fromIndex.intValue());
        this.pages.add(toIndex, page);
    }

    /**
     * 刷新 {@link Page} 的index属性.
     */
    public void refreshPageIndex() {
        List<Page> pageList = this.getPages();
        for (int index = 0; index < pageList.size(); index++) {
            pageList.get(index).set("index", index);
        }
    }
}

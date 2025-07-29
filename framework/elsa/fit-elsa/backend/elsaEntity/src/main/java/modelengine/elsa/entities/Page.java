/*---------------------------------------------------------------------------------------------
 *  Copyright (c) 2025 Huawei Technologies Co., Ltd. All rights reserved.
 *  This file is a part of the ModelEngine Project.
 *  Licensed under the MIT License. See License.txt in the project root for license information.
 *--------------------------------------------------------------------------------------------*/

package modelengine.elsa.entities;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * elsa的page结构
 *
 * @author 陈镕希
 * @since 2025-07-25
 */
public class Page extends Shape {
    private static final Integer Z_INDEX_OFFSET = 100;

    @Getter
    private final List<Shape> shapes = new ArrayList<>();

    private final Graph graph;

    public Page(String id, Graph graph) {
        super(id);
        this.graph = graph;
    }

    public Shape getShape(String shapeId) {
        return this.shapes.stream()
                .filter(s -> s.getId().equals(shapeId))
                .findFirst()
                .orElseGet(() -> this.newShape(shapeId));
    }

    public boolean containsShape(String shapeId) {
        return this.shapes.stream().anyMatch(s -> s.getId().equals(shapeId) && !s.get("container").equals(""));
    }

    public Shape newShape(String id) {
        Shape shape = new Shape(id);
        shape.set("index",this.getShapes().size()+101);
        this.addShape(shape);
        return shape;
    }

    public void addShape(Shape shape) {
        this.addShape(shape, this.shapes.size());
    }

    public void addShape(Shape shape, Integer index) {
        this.shapes.add(index, shape);
    }

    public void changeShapeIndex(String shapeId, Integer fromIndex, Integer toIndex) {
        // if (fromIndex.equals(toIndex)) {
        //     return;
        // }
        // if (fromIndex < 0) {
        //     fromIndex = 0;
        // }
        // if (toIndex < 0) {
        //     toIndex = 0;
        // }
        // if (fromIndex > this.shapes.size() - 1) {
        //     fromIndex = this.shapes.size() - 1;
        // }
        // if (toIndex > this.shapes.size() - 1) {
        //     toIndex = this.shapes.size() - 1;
        // }
        //
        // Shape shape = this.getShape(shapeId);
        // Integer step = toIndex > fromIndex ? -1 : 1;
        // Integer finalFromIndex = fromIndex;
        // Integer finalToIndex = toIndex;
        // this.shapes.stream()
        //     .filter(s -> numberWithin((Integer) s.get("index") - Z_INDEX_OFFSET, finalFromIndex, finalToIndex))
        //     .forEach(s -> s.set("index", (Integer) s.get("index") + step));
        // shape.set("index", toIndex + Z_INDEX_OFFSET);
    }

    private static boolean numberWithin(Integer number, Integer fromIndex, Integer toIndex) {
        if (fromIndex < toIndex) {
            return number <= toIndex && number > fromIndex;
        } else {
            return number >= toIndex && number < fromIndex;
        }
    }

    //    public Integer getPros() {
    //        return session.getPros(this.getId());
    //    }
    //
    //    public Integer getCons() {
    //        return session.getCons(this.getId());
    //    }

    public Shape getSharedShape(String id) {
        Shape shared = new Shape(id);
        if (this.getId().equals(id)) {//page is shared
            shared.set("x", 0);
            shared.set("y", 0);
        } else {
            Optional<Shape> shape = this.shapes.stream().filter(s -> s.getId().equals(id)).findFirst();
            if (shape.isPresent()) {
                shared.set("x", shape.get().get("x"));
                shared.set("y", shape.get().get("y"));
                shared.set("container", shape.get().get("container"));
            }
        }
        return shared;
    }

    /**
     * 将数据反序列化到page中.
     *
     * @param attributes 数据集合.
     */
    public void deserialize(Map<String, Object> attributes) {
        if (attributes == null || attributes.isEmpty()) {
            return;
        }
        attributes.forEach(this::set);
        attributes.forEach((key,value)->{
            if(key.equals("shapes")){
                List<Map<String,Object>> shapes = (List<Map<String,Object>>)value;
                shapes.forEach(s -> {
                    Shape shape = this.newShape(s.get("id").toString());
                    s.forEach((k,v)->{
                        shape.set(k,v);
                    });
                });
            }else{
                this.set(key,value);
            }
        });
        int newIndex = (Integer) attributes.get("index");
        int currentIndex = this.graph.indexOfPage(this.getId());
        if (newIndex != currentIndex) {
            this.graph.changePageIndex(currentIndex, newIndex);
        }


    }

    /**
     * 获取 {@code page}所在 {@code graph} 的唯一标识.
     *
     * @return {@link String} graph的唯一标识.
     */
    public String getGraphId() {
        return this.graph.getId();
    }
}

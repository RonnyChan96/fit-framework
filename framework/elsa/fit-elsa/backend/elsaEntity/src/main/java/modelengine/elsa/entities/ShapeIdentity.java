/*---------------------------------------------------------------------------------------------
 *  Copyright (c) 2025 Huawei Technologies Co., Ltd. All rights reserved.
 *  This file is a part of the ModelEngine Project.
 *  Licensed under the MIT License. See License.txt in the project root for license information.
 *--------------------------------------------------------------------------------------------*/

package modelengine.elsa.entities;

import lombok.Getter;

/**
 * elsa中协同对象的id表达 elsa中shape的id表达为graph+page+shape
 *
 * @author 陈镕希
 * @since 2025-07-25
 */
public class ShapeIdentity {
    public ShapeIdentity(String graph, String page, String shape) {
        this.graph = graph;
        this.page = page;
        this.shape = shape;
    }

    @Getter
    private final String graph;
    @Getter
    private final String page;
    @Getter
    private final String shape;

    @Override
    public boolean equals(Object another) {
        if (another == this) {
            return true;
        }
        if (!(another instanceof ShapeIdentity obj)) {
            return false;
        }
        return (this.graph.equals(obj.graph) && this.page.equals(obj.page) && this.shape.equals((obj.shape)));
    }
}

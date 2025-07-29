/*---------------------------------------------------------------------------------------------
 *  Copyright (c) 2025 Huawei Technologies Co., Ltd. All rights reserved.
 *  This file is a part of the ModelEngine Project.
 *  Licensed under the MIT License. See License.txt in the project root for license information.
 *--------------------------------------------------------------------------------------------*/

package modelengine.elsa.entities;

import lombok.Getter;

import java.util.Map;

/**
 * 客户端传入的shape变更信息
 *
 * @author 陈镕希
 * @since 2025-07-25
 */
public class ShapeProperties {
    @Getter
    private String shape;
    @Getter
    private Map<String, Object> properties;

    public ShapeProperties(String shape, Map<String, Object> properties) {
        this.shape = shape;
        this.properties = properties;
    }
    public ShapeProperties(){

    }
}

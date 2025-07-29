/*---------------------------------------------------------------------------------------------
 *  Copyright (c) 2025 Huawei Technologies Co., Ltd. All rights reserved.
 *  This file is a part of the ModelEngine Project.
 *  Licensed under the MIT License. See License.txt in the project root for license information.
 *--------------------------------------------------------------------------------------------*/

package modelengine.elsa.entities;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * Elsa中的基本单位Shape是elsa体系中协同的基本对象 该对象的id为ShapeIdentity
 *
 * @author 陈镕希
 * @since 2025-07-24
 */
public class Shape implements CommandTarget{
    @Getter
    private final String id;

    public Shape(String id) {
        this.id = id;
        this.set("id", id);
    }

    @Getter
    private final Map<String, Object> properties = new HashMap<>();

    @Override
    public void set(String property, Object value) {
        this.properties.put(property, value);
    }

    @Override
    public Object get(String property) {
        return this.properties.get(property);
    }

    @Override
    public boolean contains(String property) {
        return this.properties.containsKey(property);
    }
}

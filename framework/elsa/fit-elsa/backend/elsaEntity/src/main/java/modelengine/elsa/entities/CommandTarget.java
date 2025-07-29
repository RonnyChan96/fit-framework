/*---------------------------------------------------------------------------------------------
 *  Copyright (c) 2025 Huawei Technologies Co., Ltd. All rights reserved.
 *  This file is a part of the ModelEngine Project.
 *  Licensed under the MIT License. See License.txt in the project root for license information.
 *--------------------------------------------------------------------------------------------*/

package modelengine.elsa.entities;

import java.util.Map;

/**
 * 需要协同的对象表达
 *
 * @author 陈镕希
 * @since 2025-07-24
 */
public interface CommandTarget {
    /**
     * 得到需要协同对象的唯一标识。
     *
     * @return 协同对象唯一标识的 {@link String}。
     */
    String getId();

    /**
     * 为协同对象属性赋值。
     *
     * @param property 对应属性名称的 {@link String}。
     * @param value 需要赋的值的 {@link Object}。
     */
    void set(String property, Object value);

    /**
     * 取得某属性值。
     *
     * @param property 需要获取的属性名称的 {@link String}。
     * @return 属性值的 {@link Object}。
     */
    Object get(String property);

    /**
     * 得到协同对象的所有属性列表。
     * 仅属性名，没有属性值
     *
     * @return 属性列表的 {@link Map}{@code <}{@link String}{@code ,}{@link Object}{@code >}。
     */
    Map<String, Object> getProperties();

    /**
     * 判断协同对象是否存在某个属性。
     *
     * @param property 需要判断的属性的 {@link String}。
     * @return 是否存在的结果。
     */
    boolean contains(String property);
}

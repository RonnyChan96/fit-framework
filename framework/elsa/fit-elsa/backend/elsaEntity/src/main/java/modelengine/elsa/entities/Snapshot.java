/*---------------------------------------------------------------------------------------------
 *  Copyright (c) 2025 Huawei Technologies Co., Ltd. All rights reserved.
 *  This file is a part of the ModelEngine Project.
 *  Licensed under the MIT License. See License.txt in the project root for license information.
 *--------------------------------------------------------------------------------------------*/

package modelengine.elsa.entities;

/**
 * 快照。
 *
 * @author 陈镕希
 * @since 2025-07-24
 */
public interface Snapshot<I, T> {
    String getId();

    T get(I targetId);

    int getSequence();

    void setSequence(int sequence);
}

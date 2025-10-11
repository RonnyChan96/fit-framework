/*---------------------------------------------------------------------------------------------
 *  Copyright (c) 2025 Huawei Technologies Co., Ltd. All rights reserved.
 *  This file is a part of the ModelEngine Project.
 *  Licensed under the MIT License. See License.txt in the project root for license information.
 *--------------------------------------------------------------------------------------------*/

import {containerDrawer} from '../../core/drawers/containerDrawer.js';

/**
 * document 绘制
 */
let docDrawer = (shape, div, x, y) => {
  let self = containerDrawer(shape, div, x, y);
  self.container.remove();
  self.container = self.parent;
  self.containerResize = (width, height) => {
  };//退化container
  return self;
};

export {docDrawer};
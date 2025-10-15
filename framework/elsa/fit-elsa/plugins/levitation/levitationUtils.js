/*---------------------------------------------------------------------------------------------
 *  Copyright (c) 2025 Huawei Technologies Co., Ltd. All rights reserved.
 *  This file is a part of the ModelEngine Project.
 *  Licensed under the MIT License. See License.txt in the project root for license information.
 *--------------------------------------------------------------------------------------------*/

import { levitation } from "./levitation.js";

export const LevitationUtils = {
  show: (shape) => {
    /**
     * 文档中批注功能，要求：批注的笔记可以跟随文字自适应变化，先只实现一根直线，不考虑圆
     */
    if(shape.needLevitation){
      levitation(shape);
    }
  }, remove: (shape) => {
    if (!shape || shape.name === 'imageTool' || shape.name === 'toolItem') {
      return;
    }
    const data = shape.page.shapes.filter(s => s.name === 'tool' || s.name === 'toolItems');
    data.forEach(s => s.remove());
  }
};
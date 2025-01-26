/*---------------------------------------------------------------------------------------------
 *  Copyright (c) 2024 Huawei Technologies Co., Ltd. All rights reserved.
 *  This file is a part of the ModelEngine Project.
 *  Licensed under the MIT License. See License.txt in the project root for license information.
 *--------------------------------------------------------------------------------------------*/

package modelengine.fitframework.aop.interceptor.support;

import modelengine.fitframework.aop.interceptor.MethodJoinPoint;
import modelengine.fitframework.inspection.Nonnull;
import modelengine.fitframework.inspection.Nullable;
import modelengine.fitframework.ioc.BeanFactory;

import java.lang.reflect.Method;

/**
 * 用于环绕方法调用时生效的方法拦截器。
 *
 * @author 季聿阶
 * @since 2022-05-11
 */
public class AroundInterceptor extends AbstractAdviceMethodInterceptor {
    /**
     * 使用拦截建议的对象和拦截建议的方法来实例化一个 {@link AroundInterceptor}。
     *
     * @param aspectFactory 表示真实拦截的对象的工厂的 {@link BeanFactory}。
     * @param advisorMethod 表示真实拦截的方法的 {@link Method}。
     */
    public AroundInterceptor(BeanFactory aspectFactory, Method advisorMethod) {
        super(aspectFactory, advisorMethod);
    }

    @Nullable
    @Override
    public Object intercept(@Nonnull MethodJoinPoint methodJoinPoint) throws Throwable {
        return this.invokeAdvisorPoint(methodJoinPoint, null, null);
    }
}

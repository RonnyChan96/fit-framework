/*---------------------------------------------------------------------------------------------
 *  Copyright (c) 2024 Huawei Technologies Co., Ltd. All rights reserved.
 *  This file is a part of the ModelEngine Project.
 *  Licensed under the MIT License. See License.txt in the project root for license information.
 *--------------------------------------------------------------------------------------------*/

package modelengine.fit.http.client.proxy.setter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import modelengine.fit.http.client.HttpClassicClient;
import modelengine.fit.http.client.HttpClassicClientRequest;
import modelengine.fit.http.client.proxy.DestinationSetter;
import modelengine.fit.http.client.proxy.RequestBuilder;
import modelengine.fit.http.client.proxy.support.DefaultRequestBuilder;
import modelengine.fit.http.client.proxy.support.setter.PathVariableDestinationSetter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * 为 {@link PathVariableDestinationSetter} 提供单元测试。
 *
 * @author 王攀博
 * @since 2024-06-12
 */
@DisplayName("测试 PathVariableDestinationSetter 接口")
class PathVariableDestinationSetterTest {
    private String key;
    private String pathVariable;
    private String pathPattern;
    private String protocol;
    private String domain;
    private RequestBuilder requestBuilder;
    private HttpClassicClientRequest expectRequest;
    private HttpClassicClient client;

    @BeforeEach
    void setup() {
        this.key = "gid";
        this.pathVariable = "test_gid";
        this.protocol = "http";
        this.domain = "test_domain";
        this.pathPattern = "/fit/{gid}";
        this.client = mock(HttpClassicClient.class);
        this.expectRequest = mock(HttpClassicClientRequest.class);
        this.requestBuilder = new DefaultRequestBuilder().client(this.client)
                .protocol(this.protocol)
                .domain(this.domain)
                .pathPattern(this.pathPattern);
    }

    @AfterEach
    void teardown() {
        this.requestBuilder = null;
    }

    @Test
    @DisplayName("向request中设置url路径变量")
    void shouldReturnUrlWhenCorrectPathVariableGivenPathVariable() {
        // given
        String url = "http://test_domain/fit/test_gid";
        when(this.client.createRequest(any(), eq(url))).thenReturn(this.expectRequest);
        when(this.expectRequest.requestUri()).thenReturn(url);
        // when
        DestinationSetter setter = new PathVariableDestinationSetter(this.key);
        setter.set(requestBuilder, this.pathVariable);
        String actualUri = requestBuilder.build().requestUri();

        // then
        assertThat(actualUri).isEqualTo(url);
    }
}
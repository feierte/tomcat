/*
* Licensed to the Apache Software Foundation (ASF) under one or more
* contributor license agreements.  See the NOTICE file distributed with
* this work for additional information regarding copyright ownership.
* The ASF licenses this file to You under the Apache License, Version 2.0
* (the "License"); you may not use this file except in compliance with
* the License.  You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package javax.servlet;

import java.util.Collection;
import java.util.Set;

/**
 * Interface through which a Servlet may be further configured.
 *
 * @since Servlet 3.0
 *
 * @apiNote ServletRegistration 代表了对一个 Servlet 的定义和它的初始化参数。
 * 向 Servlet 容器动态添加 Servlet时，会返回 ServletRegistration 对象引用，通过该对象引用可以获取 Servlet 的名称、类名、初始化参数以及它所对应的 URL 模式（URL patterns）。
 * 通过这个方式就相当于这个接口提供了一种方式来注册和管理 Servlet，不要理解为了容器（可以注册多个 Servlet 的容器），类似与持有 Servlet 对象引用的句柄。
 *
 * <p>ServletRegistration 的主要作用包括：
 *  1.获取 Servlet 的基本信息：可以通过 ServletRegistration 获取到 Servlet 的名字、类名等信息。
 *  2.获取和设置初始化参数：可以获取 Servlet 的初始化参数，也可以添加或修改这些参数。
 *  3.设置 Servlet 的 URL 映射：可以设置或获取 Servlet 的 URL 映射模式，也就是哪些 URL 请求会由这个 Servlet 来处理。
 */
public interface ServletRegistration extends Registration {

    /**
     * Adds a servlet mapping with the given URL patterns for the Servlet
     * represented by this ServletRegistration. If any of the specified URL
     * patterns are already mapped to a different Servlet, no updates will
     * be performed.
     *
     * If this method is called multiple times, each successive call adds to
     * the effects of the former. The returned set is not backed by the
     * ServletRegistration object, so changes in the returned set are not
     * reflected in the ServletRegistration object, and vice-versa.
     *
     * @param urlPatterns The URL patterns that this Servlet should be mapped to
     * @return the (possibly empty) Set of URL patterns that are already mapped
     * to a different Servlet
     * @throws IllegalArgumentException if urlPattern is null or empty
     * @throws IllegalStateException if the associated ServletContext has
     *                                  already been initialised
     */
    public Set<String> addMapping(String... urlPatterns);

    /**
     * Gets the currently available mappings of the Servlet represented by this
     * ServletRegistration.
     *
     * If permitted, any changes to the returned Collection must not affect this
     * ServletRegistration.
     *
     * @return a (possibly empty) Collection of the currently available mappings
     * of the Servlet represented by this ServletRegistration
     */
    public Collection<String> getMappings();

    public String getRunAsRole();

    /**
     * Interface through which a Servlet registered via one of the addServlet
     * methods on ServletContext may be further configured.
     */
    public static interface Dynamic extends ServletRegistration, Registration.Dynamic {
        public void setLoadOnStartup(int loadOnStartup);
        public Set<String> setServletSecurity(ServletSecurityElement constraint);
        public void setMultipartConfig(MultipartConfigElement multipartConfig);
        public void setRunAsRole(String roleName);
    }
}

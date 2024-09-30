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

import java.util.EventListener;

/**
 * Implementations of this interface receive notifications about changes to the
 * servlet context of the web application they are part of. To receive
 * notification events, the implementation class must be configured in the
 * deployment descriptor for the web application.
 *
 * @see ServletContextEvent
 * @since v 2.3
 *
 * @apiNote ServletContext 监听器，用于监听 ServletContext 对象的生命周期。当 Servlet 容器启动或终止 Web 应用程序时，会触发 ServletContextEvent 事件。
 *
 * <p> 应用场景 </p>
 * 全局数据初始化：在服务器启动时，可以初始化一些全局数据，如数据库连接池，并将其存储在 ServletContext 中供其他组件使用。
 * 资源清理：在服务器关闭前，进行资源的清理工作，如关闭数据库连接。
 * 框架初始化：许多框架（如 Spring）使用 ServletContextListener 来加载配置文件并初始化框架。
 * 定时任务调度：可以启动定时任务，例如每天晚上执行数据同步或清理任务。
 */
public interface ServletContextListener extends EventListener {

    /**
     ** Notification that the web application initialization process is starting.
     * All ServletContextListeners are notified of context initialization before
     * any filter or servlet in the web application is initialized.
     * The default implementation is a NO-OP.
     * @param sce Information about the ServletContext that was initialized
     */
    public default void contextInitialized(ServletContextEvent sce) {
    }

    /**
     ** Notification that the servlet context is about to be shut down. All
     * servlets and filters have been destroyed before any
     * ServletContextListeners are notified of context destruction.
     * The default implementation is a NO-OP.
     * @param sce Information about the ServletContext that was destroyed
     */
    public default void contextDestroyed(ServletContextEvent sce) {
    }
}

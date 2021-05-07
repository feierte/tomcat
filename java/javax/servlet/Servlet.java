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

import java.io.IOException;

/**
 * Defines methods that all servlets must implement.
 *
 * <p>
 * A servlet is a small Java program that runs within a Web server. Servlets
 * receive and respond to requests from Web clients, usually across HTTP, the
 * HyperText Transfer Protocol.
 *
 * <p>
 * To implement this interface, you can write a generic servlet that extends
 * <code>javax.servlet.GenericServlet</code> or an HTTP servlet that extends
 * <code>javax.servlet.http.HttpServlet</code>.
 *
 * <p>
 * This interface defines methods to initialize a servlet, to service requests,
 * and to remove a servlet from the server. These are known as life-cycle
 * methods and are called in the following sequence:
 * <ol>
 * <li>The servlet is constructed, then initialized with the <code>init</code>
 * method.
 * <li>Any calls from clients to the <code>service</code> method are handled.
 * <li>The servlet is taken out of service, then destroyed with the
 * <code>destroy</code> method, then garbage collected and finalized.
 * </ol>
 *
 * <p>
 * In addition to the life-cycle methods, this interface provides the
 * <code>getServletConfig</code> method, which the servlet can use to get any
 * startup information, and the <code>getServletInfo</code> method, which allows
 * the servlet to return basic information about itself, such as author,
 * version, and copyright.
 *
 * @see GenericServlet
 * @see javax.servlet.http.HttpServlet
 *
 * @apiNote 是用Java编写的服务器端程序。其主要功能在于交互式地浏览和修改数据，生成动态Web内容。
 * 狭义的Servlet是指Java语言实现的一个接口，广义的Servlet是指任何实现了这个Servlet接口的类，一般情况下，人们将Servlet理解为后者。
 *
 * <p> Servlet运行于支持Java的应用服务器中。从实现上讲，Servlet可以响应任何类型的请求，但绝大多数情况下Servlet只用来扩展基于HTTP协议的Web服务器。
 *
 * <p> Servlet接口定义了Servlet与servlet容器之间的契约。这个契约是：Servlet容器将Servlet类载入内存，并产生Servlet实例和调用它具体的方法。但是要注意的是，在一个应用程序中，每种Servlet类型只能有一个实例。
 */
public interface Servlet {

    /**
     * Called by the servlet container to indicate to a servlet that the servlet
     * is being placed into service.
     *
     * <p>
     * The servlet container calls the <code>init</code> method exactly once
     * after instantiating the servlet. The <code>init</code> method must
     * complete successfully before the servlet can receive any requests.
     *
     * <p>
     * The servlet container cannot place the servlet into service if the
     * <code>init</code> method
     * <ol>
     * <li>Throws a <code>ServletException</code>
     * <li>Does not return within a time period defined by the Web server
     * </ol>
     *
     *
     * @param config
     *            a <code>ServletConfig</code> object containing the servlet's
     *            configuration and initialization parameters
     *
     * @exception ServletException
     *                if an exception has occurred that interferes with the
     *                servlet's normal operation
     *
     * @see UnavailableException
     * @see #getServletConfig
     *
     * @apiNote Servlet的生命周期方法，执行Servlet的一些初始化工作
     * 当Servlet第一次被请求时，Servlet容器就会开始调用这个方法来初始化一个Servlet对象出来，但是这个方法在后续请求中不会在被Servlet容器调用，就像人只能“出生”一次一样。
     * 我们可以利用init()方法来执行相应的初始化工作。调用这个方法时，Servlet容器会传入一个ServletConfig对象进来从而对Servlet对象进行初始化。
     */
    public void init(ServletConfig config) throws ServletException;

    /**
     *
     * Returns a {@link ServletConfig} object, which contains initialization and
     * startup parameters for this servlet. The <code>ServletConfig</code>
     * object returned is the one passed to the <code>init</code> method.
     *
     * <p>
     * Implementations of this interface are responsible for storing the
     * <code>ServletConfig</code> object so that this method can return it. The
     * {@link GenericServlet} class, which implements this interface, already
     * does this.
     *
     * @return the <code>ServletConfig</code> object that initializes this
     *         servlet
     *
     * @see #init
     * @apiNote 这个方法会返回由Servlet容器传给init()方法的ServletConfig对象。
     */
    public ServletConfig getServletConfig();

    /**
     * Called by the servlet container to allow the servlet to respond to a
     * request.
     *
     * <p>
     * This method is only called after the servlet's <code>init()</code> method
     * has completed successfully.
     *
     * <p>
     * The status code of the response always should be set for a servlet that
     * throws or sends an error.
     *
     *
     * <p>
     * Servlets typically run inside multithreaded servlet containers that can
     * handle multiple requests concurrently. Developers must be aware to
     * synchronize access to any shared resources such as files, network
     * connections, and as well as the servlet's class and instance variables.
     * More information on multithreaded programming in Java is available in <a
     * href
     * ="http://java.sun.com/Series/Tutorial/java/threads/multithreaded.html">
     * the Java tutorial on multi-threaded programming</a>.
     *
     *
     * @param req
     *            the <code>ServletRequest</code> object that contains the
     *            client's request
     *
     * @param res
     *            the <code>ServletResponse</code> object that contains the
     *            servlet's response
     *
     * @exception ServletException
     *                if an exception occurs that interferes with the servlet's
     *                normal operation
     *
     * @exception IOException
     *                if an input or output exception occurs
     *
     * @apiNote Servlet的生命周期方法，执行Servlet的业务逻辑
     * 每当请求Servlet时，Servlet容器就会调用这个方法。就像人一样，需要不停的接受老板的指令并且“工作”。
     * 第一次请求时，Servlet容器会先调用init()方法初始化一个Servlet对象出来，然后会调用它的service()方法进行工作，
     * 但在后续的请求中，Servlet容器只会调用service方法了。
     */
    public void service(ServletRequest req, ServletResponse res)
            throws ServletException, IOException;

    /**
     * Returns information about the servlet, such as author, version, and
     * copyright.
     *
     * <p>
     * The string that this method returns should be plain text and not markup
     * of any kind (such as HTML, XML, etc.).
     *
     * @return a <code>String</code> containing servlet information
     */
    public String getServletInfo();

    /**
     * Called by the servlet container to indicate to a servlet that the servlet
     * is being taken out of service. This method is only called once all
     * threads within the servlet's <code>service</code> method have exited or
     * after a timeout period has passed. After the servlet container calls this
     * method, it will not call the <code>service</code> method again on this
     * servlet.
     *
     * <p>
     * This method gives the servlet an opportunity to clean up any resources
     * that are being held (for example, memory, file handles, threads) and make
     * sure that any persistent state is synchronized with the servlet's current
     * state in memory.
     *
     * @apiNote Servlet的生命周期方法，销毁Servlet
     * 当要销毁Servlet时，Servlet容器就会调用这个方法，就如人一样，到时期了就得死亡。
     * 在卸载应用程序或者关闭Servlet容器时，就会发生这种情况，一般在这个方法中会写一些清除代码。
     */
    public void destroy();
}

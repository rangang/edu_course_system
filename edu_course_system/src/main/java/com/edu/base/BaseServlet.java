package com.edu.base;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @BelongsProject: edu_course_system
 * @Author: RG
 * @CreateTime: 2022/6/15 5:30 下午
 * @Description:
 */
public class BaseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 获取参数，要访问的方法名
        String methodName = req.getParameter("methodName");
        // 判断参数不为空
        if (methodName != null) {

            try {
                // 获取字节码文件对象
                Class aClass = this.getClass();
                // 根据传入的方法名，获取对象的方法对象
                Method method = aClass.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
                // 调用method对象的invoke方法，执行对应的功能
                method.invoke(this,req,resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}

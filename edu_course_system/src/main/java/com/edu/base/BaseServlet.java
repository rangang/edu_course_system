package com.edu.base;

import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

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
        // String methodName = req.getParameter("methodName");
        String methodName = null;

        // 获取POST请求的Content-Type类型
        String header = req.getHeader("Content-Type");

        // 判断传递的数据是不是JSON格式
        if ("application/json".equals(header)) {
            // 是JSON格式，调用getPostJSON
            String postJSON = getPostJSON(req);

            // 将JSON格式的字符串转换为map
            Map<String, Object> map = JSON.parseObject(postJSON, Map.class);

            // 从map集合中获取methodName
            methodName = (String) map.get("methodName");

            // 将获取到的数据，保存到request域对象中
            req.setAttribute("map",map);

        } else {
            methodName = req.getParameter("methodName");
        }


        System.out.println("methodName:" + methodName);
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

    public String getPostJSON(HttpServletRequest request) {

        try {
            // 从request中获取 字符缓冲输入流对象
            BufferedReader reader = request.getReader();

            // 创建StringBuffer,用来保存读取出的数据
            StringBuffer sb = new StringBuffer();

            // 循环读取
            String line = null;
            while ((line = reader.readLine()) != null) {
                // 追加到StringBuffer中
                sb.append(line);
            }

            // 将读取到的内容转换为字符串，并返回
            return sb.toString();

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

}

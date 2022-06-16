package com.edu.web.servlet;

import com.edu.base.BaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @BelongsProject: edu_course_system
 * @Author: RG
 * @CreateTime: 2022/6/15 5:52 下午
 * @Description:
 */
@WebServlet("/demo002")
public class TestServlet extends BaseServlet {

    public void add(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("添加方法");
    }

    public void findByName(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("根据课程名查询");
    }

    public void findByState(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("根据状态查询");
    }

}

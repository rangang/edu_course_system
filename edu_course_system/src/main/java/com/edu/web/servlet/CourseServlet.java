package com.edu.web.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.edu.base.BaseServlet;
import com.edu.pojo.Course;
import com.edu.service.CourseService;
import com.edu.service.impl.CourseServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @BelongsProject: edu_course_system
 * @Author: RG
 * @CreateTime: 2022/6/20 2:13 下午
 * @Description:
 */
@WebServlet("/course")
public class CourseServlet extends BaseServlet {

    /**
     * 查询课程信息列表
     * @param request
     * @param response
     */
    public void findCourseList(HttpServletRequest request, HttpServletResponse response) {

        try {
            // 业务处理
            CourseService courseService = new CourseServiceImpl();
            List<Course> courseList = courseService.findCourseList();

            // 相应结果
            SimplePropertyPreFilter filter = new SimplePropertyPreFilter(Course.class, "id", "course_name", "price", "sort_num", "status");

            String result = JSON.toJSONString(courseList, filter);
            response.getWriter().print(result);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 根据条件查询课程信息
     * @param request
     * @param response
     */
    public void findByCourseNameAndStatus(HttpServletRequest request, HttpServletResponse response) {

        try {
            // 接受参数
            String courseName = request.getParameter("courseName");
            String status = request.getParameter("status");

            // 业务处理
            CourseService courseService = new CourseServiceImpl();
            List<Course> courseList = courseService.findByCourseNameAndStatus(courseName, status);

            // 返回结果 相应JSON格式数据
            // 使用SimplePropertyPreFilter,指定要转换为JSON的字段
            SimplePropertyPreFilter filter = new SimplePropertyPreFilter(Course.class,"id", "course_name", "price", "sort_num", "status");

            String result = JSON.toJSONString(courseList, filter);

            response.getWriter().print(result);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

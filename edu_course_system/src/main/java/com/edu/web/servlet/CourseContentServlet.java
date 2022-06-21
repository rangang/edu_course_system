package com.edu.web.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.edu.base.BaseServlet;
import com.edu.pojo.Course;
import com.edu.pojo.Course_Section;
import com.edu.service.CourseContentService;
import com.edu.service.impl.CourseContentServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * @BelongsProject: edu_course_system
 * @Author: RG
 * @CreateTime: 2022/6/21 3:52 下午
 * @Description:
 */
@WebServlet("/courseContent")
public class CourseContentServlet extends BaseServlet {

    /**
     * 展示对应课程的章节与课时信息
     * @param request
     * @param response
     */
    public void findSectionAndLessonByCourseId(HttpServletRequest request, HttpServletResponse response) {

        try {
            // 获取参数
            String course_id = request.getParameter("course_id");

            // 业务处理
            CourseContentService contentService = new CourseContentServiceImpl();
            List<Course_Section> sectionList = contentService.findSectionAndLessonByCourseId(Integer.parseInt(course_id));

            // 返回结果
            String result = JSON.toJSONString(sectionList);
            response.getWriter().print(result);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 回显章节对应的课程信息
     * @param request
     * @param response
     */
    public void findCourseByCourseId(HttpServletRequest request, HttpServletResponse response) {

        try {
            // 获取参数
            String course_id = request.getParameter("course_id");

            // 业务处理
            CourseContentService contentService = new CourseContentServiceImpl();

            Course course = contentService.findCourseByCourseId(Integer.parseInt(course_id));

            // 返回数据，将对象转换为JSON，只转换需要的字段
            SimplePropertyPreFilter filter = new SimplePropertyPreFilter(Course.class, "id", "course_name");

            String result = JSON.toJSONString(course, filter);
            response.getWriter().print(result);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 保存&修改 章节信息
     * @param request
     * @param response
     */
    public void saveOrUpdateSection(HttpServletRequest request, HttpServletResponse response) {

        try {
            // 获取参数，从域对象中获取
            Map<String,Object> map = (Map)request.getAttribute("map");

            // 创建Course_Section
            Course_Section section = new Course_Section();

            // 使用BeanUtils工具类，将map中的数据封装到section
            BeanUtils.populate(section,map);

            // 业务处理
            CourseContentService contentService = new CourseContentServiceImpl();

            if (section.getId() != 0) {
                // 修改操作
                String result = contentService.updateSection(section);

                // 响应结果
                response.getWriter().print(result);
            } else {
                String result = contentService.saveSection(section);

                // 响应结果
                response.getWriter().print(result);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 修改章节状态
     * @param request
     * @param response
     */
    public void updateSectionStatus(HttpServletRequest request, HttpServletResponse response) {

        try {
            // 获取参数
            int id = Integer.parseInt(request.getParameter("id"));
            int status = Integer.parseInt(request.getParameter("status"));

            // 业务处理
            CourseContentService contentService = new CourseContentServiceImpl();
            String result = contentService.updateSectionStatus(id,status);

            // 返回结果数据
            response.getWriter().print(result);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

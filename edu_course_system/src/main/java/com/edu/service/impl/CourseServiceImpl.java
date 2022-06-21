package com.edu.service.impl;

import com.edu.base.StatusCode;
import com.edu.dao.CourseDao;
import com.edu.dao.impl.CourseDaoImpl;
import com.edu.pojo.Course;
import com.edu.service.CourseService;
import com.edu.utils.DateUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @BelongsProject: edu_course_system
 * @Author: RG
 * @CreateTime: 2022/6/20 2:12 下午
 * @Description: 课程模块：Service层实现类
 */
public class CourseServiceImpl implements CourseService {

    CourseDao courseDao = new CourseDaoImpl();

    @Override
    public List<Course> findCourseList() {
        // 调用Dao进行查询
        return courseDao.findCourseList();
    }

    @Override
    public List<Course> findByCourseNameAndStatus(String courseName, String status) {
        return courseDao.findByCourseNameAndStatus(courseName,status);
    }

    @Override
    public String saveCourseSalesInfo(Course course) {

        // 补全课程信息
        String dateFormat = DateUtils.getDateFormart();
        course.setCreate_time(dateFormat);
        course.setUpdate_time(dateFormat);
        course.setStatus(0);

        // 调用Dao进行插入
        int i = courseDao.saveCourseSalesInfo(course);
        String result;
        if (i > 0) {
            // 保存成功
            result = StatusCode.SUCCESS.toString();
        } else {
            // 保存失败
            result = StatusCode.FAIL.toString();
        }
        return result;

    }

    @Override
    public Course findCourseById(int id) {
        return courseDao.findCourseById(id);
    }

    @Override
    public String updateCourseSalesInfo(Course course) {

        // 调用Dao
        int i = courseDao.updateCourseSalesInfo(course);
        // 根据插入是否成功，封装对应信息
        if (i > 0) {
            // 保存成功
            String result = StatusCode.SUCCESS.toString();
            return result;
        } else {
            // 保存失败
            String result = StatusCode.FAIL.toString();
            return result;
        }

    }

    @Override
    public Map<String, Integer> updateCourseStatus(Course course) {

        // 调用dao
        int row = courseDao.updateCourseStatus(course);

        Map<String,Integer> map = new HashMap<>();

        if (row > 0) {
            if (course.getStatus() == 0) {
                map.put("status",0);
            } else {
                map.put("status",1);
            }
        }

        return map;
    }
}

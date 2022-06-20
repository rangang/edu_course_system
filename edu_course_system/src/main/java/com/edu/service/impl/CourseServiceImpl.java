package com.edu.service.impl;

import com.edu.dao.CourseDao;
import com.edu.dao.impl.CourseDaoImpl;
import com.edu.pojo.Course;
import com.edu.service.CourseService;

import java.util.List;

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
}

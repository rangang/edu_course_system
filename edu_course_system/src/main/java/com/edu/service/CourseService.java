package com.edu.service;

import com.edu.pojo.Course;

import java.util.List;
import java.util.Map;

/**
 * @BelongsProject: edu_course_system
 * @Author: RG
 * @CreateTime: 2022/6/20 2:11 下午
 * @Description: 课程模块：Service层接口
 */
public interface CourseService {

    public List<Course> findCourseList();

    public List<Course> findByCourseNameAndStatus(String courseName, String status);

    public String saveCourseSalesInfo(Course course);

    public Course findCourseById(int id);

    public String updateCourseSalesInfo(Course course);

    public Map<String,Integer> updateCourseStatus(Course course);

}

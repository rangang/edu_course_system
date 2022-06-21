package com.edu.dao;

import com.edu.pojo.Course;

import java.util.List;

/**
 * @BelongsProject: edu_course_system
 * @Author: RG
 * @CreateTime: 2022/6/20 2:09 下午
 * @Description: 课程模块：DAO层接口
 */
public interface CourseDao {

    /**
     * 查询课程列表信息
     * @return
     */
    public List<Course> findCourseList();

    /**
     * 根据课程名称，课程状态 查询课程信息
     * @param courseName
     * @param status
     * @return
     */
    public List<Course> findByCourseNameAndStatus(String courseName, String status);

    /**
     * 保存课程营销信息
     * @param course
     * @return
     */
    public int saveCourseSalesInfo(Course course);

    /**
     * 根据课程ID查询课程信息
     * @param id
     * @return
     */
    public Course findCourseById(int id);

    /**
     * 修改课程营销信息
     * @param course
     * @return
     */
    public int updateCourseSalesInfo(Course course);

    /**
     * 修改课程状态
     * @param course
     * @return
     */
    public int updateCourseStatus(Course course);

}

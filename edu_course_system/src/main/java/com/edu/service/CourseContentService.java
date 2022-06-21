package com.edu.service;

import com.edu.pojo.Course;
import com.edu.pojo.Course_Section;

import java.util.List;

/**
 * @BelongsProject: edu_course_system
 * @Author: RG
 * @CreateTime: 2022/6/21 3:51 下午
 * @Description: 课程内容管理 Service层接口
 */
public interface CourseContentService {

    public List<Course_Section> findSectionAndLessonByCourseId(int courseId);

    public Course findCourseByCourseId(int courseId);

    public String saveSection(Course_Section section);

    public String updateSection(Course_Section section);

    public String updateSectionStatus(int id, int status);

}

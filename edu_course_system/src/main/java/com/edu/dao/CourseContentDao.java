package com.edu.dao;

import com.edu.pojo.Course;
import com.edu.pojo.Course_Lesson;
import com.edu.pojo.Course_Section;

import java.util.List;

/**
 * @BelongsProject: edu_course_system
 * @Author: RG
 * @CreateTime: 2022/6/21 3:49 下午
 * @Description: 课程内容管理 DAO层接口
 */
public interface CourseContentDao {

    /**
     * 根据课程ID查询课程相关信息
     * @param courseId
     * @return
     */
    public List<Course_Section> findSectionAndLessonByCourseId(int courseId);

    /**
     * 根据章节ID 查询章节相关的课时信息
     * @param sectionId
     * @return
     */
    public List<Course_Lesson> findLessonBySectionId(int sectionId);

    /**
     * 添加章节时进行数据回显
     * @param courseId
     * @return
     */
    public Course findCourseByCourseId(int courseId);

    /**
     * 保存章节信息
     * @param section
     * @return
     */
    public int saveSection(Course_Section section);

    /**
     * 修改章节信息
     * @param section
     * @return
     */
    public int updateSection(Course_Section section);

    /**
     * 修改章节状态
     * @param id
     * @param status
     * @return
     */
    public int updateSectionStatus(int id, int status);

    /**
     * 保存课时信息
     * @param course_lesson
     * @return
     */
    public int saveLesson(Course_Lesson course_lesson);

    /**
     * 修改课时信息
     * @param course_lesson
     * @return
     */
    public int updateLesson(Course_Lesson course_lesson);

}

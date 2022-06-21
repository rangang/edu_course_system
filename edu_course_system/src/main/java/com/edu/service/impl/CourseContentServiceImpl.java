package com.edu.service.impl;

import com.edu.base.StatusCode;
import com.edu.dao.CourseContentDao;
import com.edu.dao.impl.CourseContentDaoImpl;
import com.edu.pojo.Course;
import com.edu.pojo.Course_Section;
import com.edu.service.CourseContentService;
import com.edu.utils.DateUtils;

import java.util.List;

/**
 * @BelongsProject: edu_course_system
 * @Author: RG
 * @CreateTime: 2022/6/21 3:51 下午
 * @Description: 课程内容管理 Service层实现类
 */
public class CourseContentServiceImpl implements CourseContentService {

    CourseContentDao contentDao = new CourseContentDaoImpl();

    @Override
    public List<Course_Section> findSectionAndLessonByCourseId(int courseId) {
        List<Course_Section> sections = contentDao.findSectionAndLessonByCourseId(courseId);
        return sections;
    }

    @Override
    public Course findCourseByCourseId(int courseId) {
        Course course = contentDao.findCourseByCourseId(courseId);
        return course;
    }

    @Override
    public String saveSection(Course_Section section) {

        // 补全章节信息
        section.setStatus(2); // 状态 0：隐藏 2：待更新 3：已发布
        String date = DateUtils.getDateFormart();
        section.setCreate_time(date);
        section.setUpdate_time(date);

        // 调用Dao进行插入
        int i = contentDao.saveSection(section);

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
    public String updateSection(Course_Section section) {

        // 补全信息
        String date = DateUtils.getDateFormart();
        section.setUpdate_time(date);

        // 调用Dao进行插入
        int i = contentDao.updateSection(section);

        // 根据修改是否成功，封装对应信息
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
    public String updateSectionStatus(int id, int status) {

        // 调用Dao修改状态
        int i = contentDao.updateSectionStatus(id, status);

        // 根据修改是否成功，封装对应信息
        if (i > 0) {
            String result = StatusCode.SUCCESS.toString();
            return result;
        } else {
            String result = StatusCode.FAIL.toString();
            return result;
        }

    }

}

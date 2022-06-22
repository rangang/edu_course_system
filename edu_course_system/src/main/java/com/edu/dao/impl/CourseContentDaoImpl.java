package com.edu.dao.impl;

import com.edu.dao.CourseContentDao;
import com.edu.pojo.Course;
import com.edu.pojo.Course_Lesson;
import com.edu.pojo.Course_Section;
import com.edu.utils.DateUtils;
import com.edu.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @BelongsProject: edu_course_system
 * @Author: RG
 * @CreateTime: 2022/6/21 3:49 下午
 * @Description: 课程内容管理 DAO层实现类
 */
public class CourseContentDaoImpl implements CourseContentDao {

    @Override
    public List<Course_Section> findSectionAndLessonByCourseId(int courseId) {

        try {
            // 创建QueryRunner
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());

            // 编写SQL
            String sql = "SELECT \n" +
                    "id,\n" +
                    "course_id,\n" +
                    "section_name,\n" +
                    "description,\n" +
                    "order_num\n" +
                    "FROM course_section WHERE course_id = ?;";

            // 执行查询
            List<Course_Section> sectionList = queryRunner.query(sql, new BeanListHandler<Course_Section>(Course_Section.class), courseId);

            // 根据章节ID查询课时信息
            for (Course_Section section : sectionList) {

                // 调用方法获取章节对应的课时
                List<Course_Lesson> lessonList = findLessonBySectionId(section.getId());

                // 将课时数据封装到章节对象中
                section.setLessonList(lessonList);

            }
            return sectionList;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<Course_Lesson> findLessonBySectionId(int sectionId) {

        try {
            // 创建QueryRunner
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());

            // 编写SQL
            String sql = "SELECT\n" +
                    "id,\n" +
                    "course_id,\n" +
                    "section_id,\n" +
                    "theme,\n" +
                    "duration,\n" +
                    "is_free,\n" +
                    "order_num\n" +
                    "FROM course_lesson WHERE section_id = ?";

            // 执行查询
            List<Course_Lesson> lessonList = queryRunner.query(sql, new BeanListHandler<Course_Lesson>(Course_Lesson.class), sectionId);
            return lessonList;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Course findCourseByCourseId(int courseId) {

        try {
            // 创建QueryRunner
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());

            // 编写SQL
            String sql = "SELECT id,course_name FROM course WHERE id = ?;";

            // 执行查询
            Course course = queryRunner.query(sql, new BeanHandler<Course>(Course.class), courseId);

            // 返回结果
            return course;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public int saveSection(Course_Section section) {

        try {
            // 创建QueryRunner
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());

            // 编写SQL
            String sql = "INSERT INTO course_section(course_id,section_name,description,order_num,STATUS,create_time,update_time) VALUES(?,?,?,?,?,?,?);";

            // 准备参数
            Object[] param = {
                section.getCourse_id(),section.getSection_name(),section.getDescription(),section.getOrder_num(),
                section.getStatus(),section.getCreate_time(),section.getUpdate_time()
            };

            // 执行插入
            int i = queryRunner.update(sql, param);

            // 返回结果
            return i;

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }

    }

    @Override
    public int updateSection(Course_Section section) {

        try {
            // 创建QueryRunner
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());

            // 编写SQL
            String sql = "UPDATE course_section SET\n" +
                    "section_name = ?,\n" +
                    "description = ?,\n" +
                    "order_num = ?,\n" +
                    "update_time = ? WHERE id = ?;";

            // 准备参数
            Object[] param = {
                section.getSection_name(),section.getDescription(),section.getOrder_num(),section.getUpdate_time(),
                section.getId()
            };

            // 执行修改操作
            int row = queryRunner.update(sql, param);
            return row;

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }

    }

    @Override
    public int updateSectionStatus(int id, int status) {

        try {
            // 创建QueryRunner
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());

            // 编写SQL
            String sql = "UPDATE course_section SET STATUS = ?, update_time = ? WHERE id = ?";

            // 准备参数
            Object[] param = {
                status, DateUtils.getDateFormart(),id
            };

            // 执行修改操作
            int row = queryRunner.update(sql, param);
            return row;

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }

    }

    @Override
    public int saveLesson(Course_Lesson course_lesson) {

        try {
            // 创建QueryRunner
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());

            // 编写SQL
            String sql = "INSERT INTO course_lesson(course_id,section_id,theme,duration,is_free,order_num,is_del,`status`,create_time,update_time) VALUES(?,?,?,?,?,?,?,?,?,?);";

            // 准备参数
            Object[] param = {
                course_lesson.getCourse_id(),course_lesson.getSection_id(),course_lesson.getTheme(),
                course_lesson.getDuration(),course_lesson.getIs_free(),course_lesson.getOrderNum(),
                course_lesson.getIsDel(),course_lesson.getStatus(),course_lesson.getCreate_time(),course_lesson.getUpdate_time()
            };

            // 执行插入操作
            int row = queryRunner.update(sql, param);
            return row;

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }

    }

    @Override
    public int updateLesson(Course_Lesson course_lesson) {

        try {
            // 创建QueryRunner
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());

            // 编写SQL
            String sql = "UPDATE course_lesson SET section_id = ?, theme = ?, duration = ?, is_free = ?, order_num = ?, update_time = ? WHERE id = ?";

            // 准备参数
            Object[] param = {
                course_lesson.getSection_id(),course_lesson.getTheme(),course_lesson.getDuration(),
                course_lesson.getIs_free(),course_lesson.getOrderNum(),course_lesson.getUpdate_time(),
                course_lesson.getId()
            };

            // 执行修改操作
            int row = queryRunner.update(sql, param);
            return row;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }

    }
}

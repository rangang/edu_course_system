package com.edu.dao.impl;

import com.edu.dao.CourseDao;
import com.edu.pojo.Course;
import com.edu.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @BelongsProject: edu_course_system
 * @Author: RG
 * @CreateTime: 2022/6/20 2:09 下午
 * @Description: 课程模块：DAO层实现类
 */
public class CourseDaoImpl implements CourseDao {

    @Override
    public List<Course> findCourseList() {

        try {
            // 1.创建QueryRunner
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());

            // 2.编写SQL
            String sql = "SELECT id,course_name,price,sort_num,STATUS FROM course WHERE is_del=?";

            // 3.执行查询
            List<Course> courseList = queryRunner.query(sql, new BeanListHandler<Course>(Course.class), 0);

            return courseList;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<Course> findByCourseNameAndStatus(String courseName, String status) {

        // 执行查询
        try {
            // 1.创建QueryRunner
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());

            // 2.编写SQL 当前的查询为多条件不定项查询
            // 2.1 创建StringBuffer对象，将SQL字符串添加到缓存区
            StringBuffer stringBuffer = new StringBuffer("SELECT id,course_name,price,sort_num,STATUS FROM course WHERE 1=1 AND is_del = ? ");

            // 2.2 创建list集合保存参数
            List<Object> list = new ArrayList<>();
            list.add(0);

            // 2.3 判断传入的参数是否为空
            if (courseName != null && !"".equals(courseName)) {
                stringBuffer.append(" AND course_name LIKE ?");
                // like查询需要拼接%
                courseName = "%"+courseName+"%";
                // 将条件放进list集合
                list.add(courseName);
            }

            if (status != null && !"".equals(status)) {
                stringBuffer.append(" AND STATUS = ?");
                // 将status转换为int
                int i = Integer.parseInt(status);
                list.add(i);
            }

            List<Course> courseList = queryRunner.query(stringBuffer.toString(), new BeanListHandler<Course>(Course.class), list.toArray());

            return courseList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public int saveCourseSalesInfo(Course course) {

        try {
            // 创建QueryRunner
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());

            // 编写SQL
            String sql = "INSERT INTO course(course_name,brief,teacher_name,teacher_info,preview_first_field,preview_second_field,discounts,price,price_tag,share_image_title,share_title,share_description,course_description,course_img_url,STATUS,create_time,update_time)\n" +
                    "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

            // 准备参数
            Object[] param = {
                course.getCourse_name(),course.getBrief(),course.getTeacher_name(),course.getTeacher_info(),
                course.getPreview_first_field(),course.getPreview_second_field(),course.getDiscounts(),
                course.getPrice(),course.getPrice_tag(),course.getShare_image_title(),course.getShare_title(),
                course.getShare_description(),course.getCourse_description(),course.getCourse_img_url(),course.getStatus(),
                course.getCreate_time(),course.getUpdate_time()
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
    public Course findCourseById(int id) {

        try {
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
            String sql = "SELECT id,course_name,brief,teacher_name,teacher_info,preview_first_field,preview_second_field,discounts,price,price_tag,course_img_url,share_image_title,share_title,share_description,course_description,STATUS FROM course WHERE id = ?;";

            Course course = queryRunner.query(sql, new BeanHandler<Course>(Course.class), id);

            return course;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }


    }

    @Override
    public int updateCourseSalesInfo(Course course) {

        try {
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());

            String sql = "UPDATE course SET course_name = ?,brief = ?,teacher_name = ?,teacher_info = ?,preview_first_field = ?,preview_second_field = ?,discounts = ?,price = ?,price_tag = ?,share_image_title = ?,share_title = ?,share_description = ?,course_description = ?,course_img_url = ?,update_time = ? WHERE id = ?";

            Object[] param = {
                    course.getCourse_name(),course.getBrief(),course.getTeacher_name(),course.getTeacher_info(),course.getPreview_first_field(),
                    course.getPreview_second_field(),course.getDiscounts(),course.getPrice(),course.getPrice_tag(),course.getShare_image_title(),
                    course.getShare_title(),course.getShare_description(),course.getCourse_description(),course.getCourse_img_url(),course.getUpdate_time(),course.getId()
            };

            int row = queryRunner.update(sql, param);
            return row;

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }

    }

    @Override
    public int updateCourseStatus(Course course) {

        try {
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());

            String sql = "UPDATE course SET `status` = ?, update_time = ? WHERE id = ?;";
            Object[] param = {
                course.getStatus(),course.getUpdate_time(),course.getId()
            };

            int row = queryRunner.update(sql, param);
            return row;

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }

    }

}

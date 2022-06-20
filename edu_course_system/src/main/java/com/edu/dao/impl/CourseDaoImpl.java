package com.edu.dao.impl;

import com.edu.dao.CourseDao;
import com.edu.pojo.Course;
import com.edu.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
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

}

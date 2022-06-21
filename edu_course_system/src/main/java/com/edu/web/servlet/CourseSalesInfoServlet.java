package com.edu.web.servlet;

import com.edu.base.Constants;
import com.edu.pojo.Course;
import com.edu.service.CourseService;
import com.edu.service.impl.CourseServiceImpl;
import com.edu.utils.DateUtils;
import com.edu.utils.UUIDUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @BelongsProject: edu_course_system
 * @Author: RG
 * @CreateTime: 2022/6/21 1:28 下午
 * @Description:
 */
@WebServlet("/courseSalesInfo")
public class CourseSalesInfoServlet extends HttpServlet {

    /**
     * 保存课程营销信息
     * 收集表单数据，封装到course对象中，将图片上传到tomcat服务器中
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {

            // 创建Course对象
            Course course = new Course();

            // 创建Map集合，用来收集数据
            Map<String,Object> map = new HashMap<>();

            // 1.创建磁盘文件项工厂
            DiskFileItemFactory factory = new DiskFileItemFactory();

            // 2.创建文件上传核心类
            ServletFileUpload upload = new ServletFileUpload(factory);

            // 3.解析request,获取文件项集合
            List<FileItem> list = upload.parseRequest(req);
            if (list != null) {
                // 4.遍历获取表单项
                for (FileItem item : list) {
                    // 5.判断是不是一个普通表单项
                    boolean formField = item.isFormField();
                    if (formField) {

                        // 普通表单项，当enctype="multipart/form-data"时，request的getParameter()方法无法获取参数
                        String fieldName = item.getFieldName();
                        // 设置编码
                        String value = item.getString("utf-8");
                        System.out.println(fieldName + "=" + value);
                        // 使用map收集数据
                        map.put(fieldName,value);
                    } else {

                        // 文件上传项
                        // 文件名
                        String fileName = item.getName();
                        // 避免图片名重复 拼接UUID
                        String newFileName = UUIDUtils.getUUID() + "_" + fileName;

                        // 获取上传文件的内容
                        InputStream in = item.getInputStream();

                        String path = this.getServletContext().getRealPath("/");

                        // 获取到webapps路径
                        String webappsPath = path.substring(0,path.indexOf("edu_course_system"));
                        OutputStream out = new FileOutputStream(webappsPath + "/upload/" + newFileName);
                        // 拷贝文件到服务器
                        IOUtils.copy(in,out);

                        out.close();
                        in.close();

                        // 将图片路径进行保存
                        map.put("course_img_url", Constants.LOCAL_URL + "/upload/" + newFileName);

                    }
                }

                // 使用BeanUtils将map中的数据封装到course对象
                BeanUtils.populate(course,map);

                String dateFormat = DateUtils.getDateFormart();
                CourseService cs = new CourseServiceImpl();

                if (map.get("id") != null) {
                    // 修改操作
                    // 补全信息
                    course.setUpdate_time(dateFormat);
                    String result = cs.updateCourseSalesInfo(course);
                    // 响应结果
                    resp.getWriter().print(result);

                } else {
                    // 新建操作
                    // 补全信息
                    course.setCreate_time(dateFormat);
                    course.setUpdate_time(dateFormat);
                    course.setStatus(1); // 上架
                    String result = cs.saveCourseSalesInfo(course);
                    // 响应结果
                    resp.getWriter().print(result);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}

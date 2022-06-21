package com.edu.base;

import com.alibaba.fastjson.JSONObject;

/**
 * @BelongsProject: edu_course_system
 * @Author: RG
 * @CreateTime: 2022/6/21 10:28 上午
 * @Description: 相应状态码
 */
public enum StatusCode {

    SUCCESS(0,"success"),
    FAIL(1,"fail");

    // 定义属性
    private int code;
    private String message;

    // 定义构造
    StatusCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    // get/set
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    // 重写toString,将枚举对象转化为JSON
    @Override
    public String toString() {
        JSONObject object = new JSONObject();
        object.put("status",code);
        object.put("msg",message);
        return object.toString();
    }
}

<%--
  Created by IntelliJ IDEA.
  User: zhsmac
  Date: 2022/6/15
  Time: 5:55 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <a href="${pageContext.request.contextPath}/test?methodName=add">新增课程</a>
    <a href="${pageContext.request.contextPath}/test?methodName=findByName">根据课程名查询</a>
    <a href="${pageContext.request.contextPath}/test?methodName=findByState">根据状态查询</a>
</body>
</html>

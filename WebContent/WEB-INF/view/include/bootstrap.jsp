<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<base href="<%=basePath%>">
<link rel="stylesheet" type="text/css" href="plugins/bootstrap/css/bootstrap-theme.min.css">
<link rel="stylesheet" type="text/css" href="plugins/bootstrap/css/bootstrap.min.css">
<script type="text/javascript" src="plugins/bootstrap/js/bootstrap.min.js"></script>
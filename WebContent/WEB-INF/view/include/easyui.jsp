<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<base href="<%=basePath%>">
<link rel="stylesheet" type="text/css" href="plugins/jquery-ui/themes/material/easyui.css">
<script type="text/javascript" src="plugins/jquery-ui/jquery.easyui.min.js"></script>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<base href="<%=basePath%>">
<link rel="stylesheet" type="text/css" href="plugins/bootstrap-table/bootstrap-table.min.css">
<script type="text/javascript" src="plugins/bootstrap-table/bootstrap-table.min.js"></script>
<script type="text/javascript" src="plugins/bootstrap-table/bootstrap-table-export.min.js"></script>
<script type="text/javascript" src="plugins/bootstrap-table/tableExport.js"></script>
<script type="text/javascript" src="plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
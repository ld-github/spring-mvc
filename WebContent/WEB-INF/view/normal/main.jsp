<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta name="keywords" content="">
<meta name="description" content="">
<jsp:include page="/WEB-INF/view/include/include.jsp"></jsp:include>
<jsp:include page="/WEB-INF/view/include/layui.jsp"></jsp:include>
<jsp:include page="/WEB-INF/view/include/easyui.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="css/main.css">
<script type="text/javascript" src="js/main.js"></script>
<title>Main Page</title>
</head>
<body>
    <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container-fluid">
            <div class="navbar-header">
                <div class="navbar-brand"> 
                    <img src="images/brand.png" />
                </div>
                <p class="navbar-text">${ sessionScope.user.username }</p>
            </div>
            <div class="nav pull-right">
                <div class="btn-group">
                    <a href="javascript:;" class="btn btn-warning navbar-btn btn-sm" id="update-password-btn">修改密码</a>
                </div>
                <div class="btn-group right-menu-btns">
                    <a href="user/toLogout" class="btn btn-danger navbar-btn btn-sm">退出系统</a>
                </div>
            </div>
        </div>
    </div>

    <div id="menu-pannel" class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <ul id="menus" class="layui-nav layui-nav-tree site-menu-nav"></ul>
        </div>
    </div>

    <div id="main-panel-body">
        <div id="page-tabs"></div>
    </div>

    <div class="footer">LD Copyright &copy; 2017</div>
</body>
</html>
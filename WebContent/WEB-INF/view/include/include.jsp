<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.ld.web.controller.PageController" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="cache-control" content="no-cache">
<meta name="renderer" content="webkit|ie-stand|ie-comp">
<link rel="shortcut icon" href="favicon.ico" />
<script type="text/javascript" src="plugins/jquery-1.12.4.min.js"></script>
<jsp:include page="/WEB-INF/view/include/bootstrap.jsp"></jsp:include>
<script type="text/javascript" src="plugins/vue.min.js"></script>
<script type="text/javascript" src="js/util.js"></script>
<link rel="stylesheet" type="text/css" href="css/style.css">
<!--[if lte IE 9]>
    <script type="text/javascript" src="plugins/bootstrap/js/respond.min.js"></script>
    <script type="text/javascript" src="plugins/bootstrap/js/html5shiv.js"></script>
<![endif]-->

<script type="text/javascript">

    var contextPath = '<%=request.getContextPath()%>';

    $(function() {

        /**
         * Ajax Start
         */
        $(document).ajaxStart(function() {
            $('#loading').show();
        });

        /**
         * Ajax Stop
         */
        $(document).ajaxStop(function() {
            $('#loading').hide();
        });

        /**
         * Ajax Error
         */
        $(document).ajaxError(function(event, response, settings) {
            if (response.status == 518) {
                window.top.location.href = contextPath + '<%=PageController.REQUEST_PAGE_URL_LOGIN%>';
            }
        });

        var userAgent = window.navigator.userAgent.toLowerCase();
        var isLessThanIE9 = userAgent.indexOf("msie") !== -1 && /\d+/.exec(userAgent.split(";")[1]) < 9;

        var landingPath = '<%=PageController.REQUEST_PAGE_URL_LANDING%>';

        if (isLessThanIE9 && window.location.href.indexOf(landingPath) === -1) {
            window.top.location.href = contextPath + landingPath;
        }

    })

</script>
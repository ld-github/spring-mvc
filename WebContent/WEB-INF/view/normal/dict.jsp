<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta name="keywords" content="">
<meta name="description" content="">
<jsp:include page="/WEB-INF/view/include/include.jsp"></jsp:include>
<jsp:include page="/WEB-INF/view/include/bootstrap-table.jsp"></jsp:include>
<script type="text/javascript" src="js/dict.js"></script>
<title>Dict Page</title>
</head>
<body>
    <div class="main-panel">
        <div class="container-fluid">
            <div class="row-fluid">
                <div class="col-xs-6">
                    <div class="panel panel-default">
                        <div class="panel-heading"><h3 class="panel-title text-center">参数类型</h3></div>
                        <div class="panel-body">
                            <div class="panel panel-default">
                                <div class="panel-body">
                                    <form class="form-inline" role="form" id="dict-type-form">
                                        <div class="form-group">
                                            <label class="sr-only" for="dict-type-code-text">字典代码</label>
                                            <input type="text" class="form-control" id="dict-type-code-text" placeholder="字典代码" name="code">
                                        </div>
                                        <div class="form-group">
                                            <label class="sr-only" for="dict-type-name-text">字典名称</label>
                                            <input type="text" class="form-control" id="dict-type-name-text" placeholder="字典名称" name="name">
                                        </div>
                                        <button type="button" class="btn btn-primary pull-right" id="dict-type-query-btn">查询</button>
                                    </form>
                                </div>
                            </div>
                            <div id="dict-type-toolbar" class="btn-group">
                                <button id="btn-add-dict-type" type="button" class="btn btn-default">
                                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>添加
                                </button>
                                <button id="btn-edit-dict-type" type="button" class="btn btn-default">
                                    <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
                                </button>
                                <button id="btn-del-dict-type" type="button" class="btn btn-default">
                                    <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>删除
                                </button>
                            </div>
                            <table id="dict-type-table"></table>
                        </div>
                    </div>
                </div>
                <div class="col-xs-6">
                    <div class="panel panel-default">
                        <div class="panel-heading"><h3 class="panel-title text-center">参数配置</h3></div>
                        <div class="panel-body">
                            <div class="panel panel-default">
                                <div class="panel-body">
                                    <form class="form-inline" role="form" id="dict-value-form">
                                        <div class="form-group">
                                            <label class="sr-only" for="dict-code-text">字典值</label>
                                            <input type="text" class="form-control" id="dict-value-text" placeholder="字典值" name="value">
                                        </div>
                                        <div class="form-group">
                                            <label class="sr-only" for="dict-name-text">字典值名称</label>
                                            <input type="text" class="form-control" id="dict-name-text" placeholder="字典值名称" name="name">
                                        </div>
                                        <button type="button" class="btn btn-primary pull-right" id="dict-value-query-btn">查询</button>
                                    </form>
                                </div>
                            </div>
                            <div id="dict-value-toolbar" class="btn-group">
                                <button id="btn-add-dict-value" type="button" class="btn btn-default">
                                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>添加
                                </button>
                                <button id="btn-edit-dict-value" type="button" class="btn btn-default">
                                    <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
                                </button>
                                <button id="btn-del-dict-value" type="button" class="btn btn-default">
                                    <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>删除
                                </button>
                            </div>
                            <table id="dict-value-table"></table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
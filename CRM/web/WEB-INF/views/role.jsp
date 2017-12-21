<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <base href="<%=basePath%>">
    <title>CRM</title>
    <script src="assets/js/jquery-1.12.4.js"></script>
    <script src="assets/js/bootstrap.js"></script>
    <link rel="stylesheet" href="assets/css/bootstrap.css">
</head>
<body>

<div class="container-fluid">
    <!--顶部导航栏-->
    <div class="row">
        <jsp:include page="header.jsp"/>
    </div>

    <div class="row">
        <!--菜单-->
        <div class="col-md-2">
            <ul class="list-group">
                <li class="list-group-item disabled"><span class="glyphicon glyphicon-list"></span>&nbsp;菜单</li>
                <li class="list-group-item">
                    <div class="row">
                        <ul class="nav nav-pills nav-stacked">
                            <li role="presentation"><a href="crm/main"><span
                                    class="glyphicon glyphicon-home"></span>&nbsp;首页</a>
                            </li>
                            <li role="presentation"><a href="crm/main"><span class="glyphicon glyphicon-th-list"></span>&nbsp;机会管理</a>
                            </li>
                            <li role="presentation"><a href="crm/main"><span class="glyphicon glyphicon-shopping-cart"></span>&nbsp;营销管理</a>
                            </li>
                            <!-- 注册点击事件, 点击后展开下层菜单, 并改变右侧图标-->
                            <li role="presentation">
                                <a onclick="expandSystemMenu()">
                                    <span style="margin-right: 50px;"><span class="glyphicon glyphicon-cog"></span>&nbsp;系统设置</span>
                                    <span id="rightMark" class="glyphicon glyphicon-chevron-left"></span>
                                </a>

                                <div class="col-md-offset-1" id="systemMenu">
                                    <ul class="nav nav-pills nav-stacked">
                                        <li role="presentation"><a href="crm/main"><span
                                                class="glyphicon glyphicon-user"></span>&nbsp;用户管理</a>
                                        </li>
                                        <li role="presentation" class="active"><a href="crm/role"><span
                                                class="glyphicon glyphicon-eye-open"></span>&nbsp;角色管理</a>
                                        </li>
                                        <li role="presentation"><a href="crm/resource"><span
                                                class="glyphicon glyphicon-eye-open"></span>&nbsp;资源管理</a>
                                        </li>
                                        <li role="presentation"><a href="crm/main"><span
                                                class="glyphicon glyphicon-list"></span>&nbsp;菜单管理</a>
                                        </li>
                                    </ul>
                                </div>

                            </li>
                        </ul>
                    </div>
                </li>
            </ul>

        </div>
        <div class="col-md-10">
            <!--当前位置-->
            <div class="row">
                <ol class="breadcrumb">
                    <li><a href="crm/main">首页</a></li>
                    <li><a href="crm/role">系统设置</a></li>
                    <li class="active">角色管理</li>
                </ol>
            </div>
            <!--搜索-->
            <div class="row">
                <form action="#">
                    <div class="panel panel-default">
                        <div class="panel-heading" style="padding-bottom: 5px; padding-top: 5px">
                            <div class="row">
                                <div class="col-md-4">搜索条件</div>
                                <div class="col-md-8 text-right">
                                    <button type="button" class="btn btn-primary btn-sm" onclick="modifyRole(document.getElementById('searchContent').value)" data-toggle="modal"
                                            data-target="#myModalToUpdate">
                                        <span class="glyphicon glyphicon-search"></span>&nbsp;搜索
                                    </button>
                                    <button type="button" class="btn btn-danger btn-sm" onclick="removeContent()">
                                        <!-- 注册事件, 清除搜索框内容-->
                                        <span class="glyphicon glyphicon-remove"></span>&nbsp;清除条件
                                    </button>
                                </div>
                            </div>
                        </div>
                        <div class="panel-body" style="padding:0">
                            <input id="searchContent" type="text" class="form-control" style="border:none;"
                                   name="search"
                                   placeholder="请输入角色 ID ...">
                        </div>
                    </div>
                </form>

            </div>
            <!--角色列表-->
            <div class="row">
                <div class="panel panel-default">
                    <!-- Default panel contents -->
                    <div class="panel-heading" style="padding-bottom: 5px; padding-top: 5px">
                        <div class="row">
                            <div class="col-md-4">角色列表</div>
                            <div class="col-md-8 text-right">
                                <a role="button" href="#" class="btn btn-primary btn-sm" data-toggle="modal"
                                   data-target="#myModalToAdd">
                                    <span class="glyphicon glyphicon-plus"></span>&nbsp;添加角色
                                </a>
                                <a role="button" href="#" class="btn btn-warning btn-sm">
                                    <span class="glyphicon glyphicon-trash"></span>&nbsp;删除选中
                                </a>
                            </div>
                        </div>
                    </div>
                    <!-- Table -->
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>常量</th>
                            <th>备注</th>
                            <th>是否可用</th>
                            <th>主键</th>
                            <th>角色名</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <!-- 列表循环 -->
                        <c:forEach items="${crmRoleList}" var="role">
                            <tr>
                                <td>${role.constant}</td>
                                <td>${role.description}</td>
                                <td>${role.enabled == 1 ? '<span class="label label-primary">可用</span>' : '<span class="label label-danger">禁用</span>'}</td>
                                <td>${role.id}</td>
                                <td>${role.name}</td>

                                <td>
                                    <a role="button" href="#"
                                       class="btn btn-warning btn-xs" data-toggle="modal"
                                       data-target="#myModalToUpdate" onclick="modifyRole(${role.id})">
                                        <span class="glyphicon glyphicon-edit"></span>&nbsp;修改
                                    </a>
                                    <a role="button" href="crm/deleteRole/${role.id}" class="btn btn-danger btn-xs">
                                        <span class="glyphicon glyphicon-trash"></span>&nbsp;删除
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>

                        </tbody>
                    </table>
                    <%--分页--%>
                    <div class="panel-footer">
                        <td colspan="6">
                            <jsp:include page="page.jsp">
                                <jsp:param name="url" value="crm/role"/>
                            </jsp:include>
                        </td>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<!--动态模态框, 添加用户-->
<!-- Modal -->
<div class="modal fade" id="myModalToAdd" tabindex="-1" role="dialog" aria-labelledby="myModalLabel1">
    <div class="modal-dialog" role="document">
        <div class="modal-content">

            <form action="crm/addRole" method="post" class="form-horizontal">

                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel1">添加角色</h4>
                </div>
                <div class="modal-body">

                    <div class="form-group">
                        <label class="control-label col-md-2">常量</label>
                        <div class="col-md-10">
                            <input type="text" class="form-control" name="constant"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-md-2">备注</label>
                        <div class="col-md-10">
                            <input type="text" class="form-control" name="description"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-md-2">是否可用</label>
                        <div class="col-md-10">
                            <label class="radio-inline">
                                <input type="radio" name="enabled" value="1">是
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="enabled" value="0">否
                            </label>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-md-2">角色名</label>
                        <div class="col-md-10">
                            <input type="text" class="form-control" name="name"/>
                        </div>
                    </div>

                    <%--主键由数据库生成--%>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="submit" class="btn btn-primary">添加</button>
                </div>
            </form>
        </div>
    </div>
</div>

<!--动态模态框, 修改用户-->
<!-- Modal -->
<div class="modal fade" id="myModalToUpdate" tabindex="-1" role="dialog" aria-labelledby="myModalLabel2">
    <div class="modal-dialog" role="document">
        <div class="modal-content">

            <form action="crm/modifyRole" method="post" class="form-horizontal">

                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel2">修改角色</h4>
                </div>
                <div class="modal-body">

                    <div class="form-group">
                        <label class="control-label col-md-2">主键</label>
                        <div class="col-md-10">
                            <input type="text" id="id" class="form-control" readonly name="id"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-md-2">常量</label>
                        <div class="col-md-10">
                            <input type="text" id="constant" class="form-control" name="constant"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-md-2">备注</label>
                        <div class="col-md-10">
                            <input type="text" id="description" class="form-control" name="description"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-md-2">角色名</label>
                        <div class="col-md-10">
                            <input type="text" id="name" class="form-control" name="name"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-md-2">是否可用</label>
                        <div class="col-md-10">
                            <label class="radio-inline">
                                <input type="radio" name="enabled" class="enabled" value="1">是
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="enabled" class="enabled" value="0">否
                            </label>
                        </div>
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="submit" class="btn btn-primary">修改</button>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
</html>
<script>
    function expandSystemMenu() {
        var $systemMenu = $("#systemMenu");
        var $rightMark = $("#rightMark");
        if ($systemMenu.css("display") === "none") {
            $systemMenu.css("display", "block");
            // 改变右侧图标为向下箭头
            $rightMark.attr("class", "glyphicon glyphicon-chevron-down");
        } else {
            $systemMenu.css("display", "none");
            $rightMark.attr("class", "glyphicon glyphicon-chevron-left")
        }
    }

    // 清空搜索内容
    function removeContent() {
        $("#searchContent").val("");
    }

    function modifyRole(roleId) {
        // 先发出 ajax 请求, 返回原对象, 然后弹出模块框并设置初始值
        $.ajax({
            url: "crm/modifyRole/" + roleId,
            method: "get",
            dateType: "json"
        }).done(function (crmRole) {
            $("#constant").val(crmRole.constant);
            $("#id").val(crmRole.id);
            $("#description").val(crmRole.description);
            $("#name").val(crmRole.name);

            $(".enabled").each(function () {
                if ($(this).prop("value") == crmRole.enabled) {
                    $(this).prop("checked", true);
                }
            });
        });
    }
</script>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<!doctype html>
<html class="no-js">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Amaze UI Admin table Examples</title>
    <meta name="description" content="这是一个 table 页面">
    <meta name="keywords" content="table">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <link rel="icon" type="image/png" href="http://127.0.0.1:10088/assets/i/favicon.png">
    <link rel="apple-touch-icon-precomposed" href="http://127.0.0.1:10088/assets/i/app-icon72x72@2x.png">
    <meta name="apple-mobile-web-app-title" content="Amaze UI"/>
    <link rel="stylesheet" href="http://127.0.0.1:10088/assets/css/amazeui.min.css"/>
    <link rel="stylesheet" href="http://127.0.0.1:10088/assets/css/admin.css">
</head>
<body>
<!--[if lte IE 9]>
<p class="browsehappy">你正在使用<strong>过时</strong>的浏览器，Amaze UI 暂不支持。 请 <a href="http://browsehappy.com/" target="_blank">升级浏览器</a>
    以获得更好的体验！</p>
<![endif]-->

<header class="am-topbar am-topbar-inverse admin-header">
    <div class="am-topbar-brand">
        <strong>Amaze UI</strong>
        <small>后台管理模板</small>
    </div>

    <button class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-success am-show-sm-only"
            data-am-collapse="{target: '#topbar-collapse'}"><span class="am-sr-only">导航切换</span> <span
            class="am-icon-bars"></span></button>

    <div class="am-collapse am-topbar-collapse" id="topbar-collapse">

        <ul class="am-nav am-nav-pills am-topbar-nav am-topbar-right admin-header-list">
            <li><a href="javascript:;"><span class="am-icon-envelope-o"></span> 收件箱 <span
                    class="am-badge am-badge-warning">5</span></a></li>
            <li class="am-dropdown" data-am-dropdown>
                <a class="am-dropdown-toggle" data-am-dropdown-toggle href="javascript:;">
                    <span class="am-icon-users"></span> 管理员 <span class="am-icon-caret-down"></span>
                </a>
            <li><a href="#"><span class="am-icon-user"></span> 资料</a></li>
            <li><a href="#"><span class="am-icon-cog"></span> 设置</a></li>
            <li><a href="#"><span class="am-icon-power-off"></span> 退出</a></li>
        </ul>
        </li>
        <%--<li class="am-hide-sm-only"><a href="javascript:;" id="admin-fullscreen"><span--%>
        <%--class="am-icon-arrows-alt"></span> <span class="admin-fullText">开启全屏</span></a></li>--%>
        </ul>
    </div>
</header>

<div class="am-cf admin-main">
    <!-- sidebar start -->
    <jsp:include page="../left.jsp"/>
    <!-- sidebar end -->

    <!-- content start -->
    <div class="admin-content">
        <div class="admin-content-body">
            <div class="am-cf am-padding am-padding-bottom-0">
                <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">题库</strong> /
                    <small>Question</small>
                </div>
            </div>

            <hr>

            <div class="am-g">
                <div class="am-u-sm-12 am-u-md-6">
                    <div class="am-btn-toolbar">
                        <div class="am-btn-group am-btn-group-xs">
                            <a href="http://localhost:10088/question/save">
                                <button class="am-btn am-btn-default am-btn-xs am-text-secondary">
                                    新增题目
                                </button>
                            </a>

                        </div>
                    </div>
                </div>

                <div class="am-u-sm-12 am-u-md-3">
                    <div class="am-input-group am-input-group-sm">
                        <input type="text" class="am-form-field">
                        <span class="am-input-group-btn">
            <button class="am-btn am-btn-default" type="button">搜索</button>
          </span>
                    </div>
                </div>
            </div>

            <div class="am-g">
                <div class="am-u-sm-12">
                    <form class="am-form">
                        <table class="am-table am-table-striped am-table-hover table-main">
                            <thead>
                            <tr>
                                <%--<th class="table-id">ID</th>--%>
                                <th class="table-title">题号</th>
                                <th class="table-type">知识点</th>
                                <th class="table-date am-hide-sm-only">试题内容</th>
                                <th class="table-date am-hide-sm-only">正确答案</th>
                                <th class="table-date am-hide-sm-only">题型</th>
                                <th class="table-date am-hide-sm-only">题目难度</th>
                                <th class="table-date am-hide-sm-only">创建时间</th>
                                <th class="table-set">操作</th>
                            </tr>
                            </thead>
                            <tbody>

                            <c:forEach var="question" items="${questions}">
                                <tr>
                                    <td>${question.qId}</td>
                                    <td>${question.knowledgePoint.kNumber}&nbsp;${question.knowledgePoint.kName}</td>
                                    <td>${question.qContent}</td>
                                    <td>${question.qRightAnswer}</td>
                                    <td>${question.qType}</td>
                                    <td>${question.qDifflevel}</td>
                                    <td><fmt:formatDate value="${question.qCreatetime}" type="date"
                                                        pattern="yyyy-MM-dd"/></td>
                                    <td>
                                        <div class="am-btn-toolbar">
                                            <div class="am-btn-group am-btn-group-xs">
                                                <a href="http://localhost:10088/question/update/${question.qId}"
                                                   class="am-btn am-btn-default am-btn-xs am-hide-sm-only">
                                                    <span class="am-icon-trash-o"></span>编辑
                                                </a>

                                                <a href="http://localhost:10088/question/delete/${question.qId}"
                                                   class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only">
                                                    <span class="am-icon-trash-o"></span>删除
                                                </a>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>


                            </tbody>
                        </table>
                        <div class="am-cf">
                            共 ${total} 条记录
                        </div>
                        <hr/>

                    </form>
                </div>

            </div>
        </div>


    </div>
    <!-- content end -->
</div>

<a href="#" class="am-icon-btn am-icon-th-list am-show-sm-only admin-menu"
   data-am-offcanvas="{target: '#admin-offcanvas'}"></a>


<!--[if lt IE 9]>
<script src="http://libs.baidu.com/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.staticfile.org/modernizr/2.8.3/modernizr.js"></script>
<script src="assets/js/amazeui.ie8polyfill.min.js"></script>
<![endif]-->

<!--[if (gte IE 9)|!(IE)]><!-->
<script src="assets/js/jquery.min.js"></script>
<!--<![endif]-->
<script src="assets/js/amazeui.min.js"></script>
<script src="assets/js/app.js"></script>
</body>
</html>

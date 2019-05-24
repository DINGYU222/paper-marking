<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<html class="no-js">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Amaze UI Admin user Examples</title>
    <meta name="description" content="这是一个 user 页面">
    <meta name="keywords" content="user">
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
        <strong>试卷自动生成系统</strong>
        <small>自动组件界面</small>
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
                <ul class="am-dropdown-content">
                    <li><a href="#"><span class="am-icon-user"></span> 资料</a></li>
                    <li><a href="#"><span class="am-icon-cog"></span> 设置</a></li>
                    <li><a href="#"><span class="am-icon-power-off"></span> 退出</a></li>
                </ul>
            </li>
            <li class="am-hide-sm-only"><a href="javascript:;" id="admin-fullscreen"><span
                    class="am-icon-arrows-alt"></span> <span class="admin-fullText">开启全屏</span></a></li>
        </ul>
    </div>
</header>

<div class="am-cf admin-main">
    <!-- sidebar start -->


    <jsp:include page="left.jsp"/>
    <!-- sidebar end -->

    <!-- content start -->
    <div class="admin-content">
        <div class="admin-content-body">
            <div class="am-cf am-padding am-padding-bottom-0">
                <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">
                    <设置试卷基本信息></设置试卷基本信息>
                </strong> /
                    <small>Question information</small>
                </div>
            </div>

            <hr/>

            <div class="am-g">
                <div class="am-u-sm-12 am-u-md-4 am-u-md-push-8">
                </div>

                <div class="am-u-sm-12 am-u-md-8 am-u-md-pull-4">
                    <form class="am-form am-form-horizontal" action="http://localhost:10088/autoMP/save"
                          method="post">

                        <div class="am-form-group">
                            <label class="am-u-sm-3 am-form-label">试卷名称</label>
                            <div class="am-u-sm-9">
                                <input type="tel" disabled="disabled" value="${pName}"/>
                                <input name="pName" value="${pName}" hidden="hidden"/>
                            </div>
                        </div>


                        <div class="am-form-group">
                            <label class="am-u-sm-3 am-form-label">课程名字</label>
                            <div class="am-u-sm-9">
                                <input type="tel" disabled="disabled" value="${course.cName}">
                                <input name="cId" value="${course.cId}" hidden="hidden"/>
                            </div>
                        </div>

                        <div class="am-form-group">
                            <label class="am-u-sm-3 am-form-label">难度系数</label>
                            <div class="am-u-sm-9">
                                <input type="tel" disabled="disabled" value="${pDifflevel}">
                                <input name="pDifflevel" value="${pDifflevel}" hidden="hidden"/>

                            </div>
                        </div>


                        <div class="am-form-group">
                            <label class="am-u-sm-3 am-form-label">考试类型</label>
                            <div class="am-u-sm-9">
                                <input type="tel" disabled="disabled" value="${testTypes}">
                                <input name="testTypes" value="${testTypes}" hidden="hidden"/>
                            </div>
                        </div>


                        <div class="am-cf am-padding am-padding-bottom-0">
                            <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">
                                <设置抽取试题选项>
                            </strong> /
                                <small>Question information</small>
                            </div>
                        </div>

                        <div class="am-form-group">
                            <label class="am-u-sm-3 am-form-label">知识点</label>
                            <div class="am-u-sm-9">
                                <c:forEach var="point" items="${points}">
                                    <input type="checkbox" name="type"
                                           value="${point.kId}" checked >${point.kNumber}&nbsp;${point.kName}

                                    <%--<input type="checkbox" name="type" style="display:none"--%>
                                           <%--value="${point.kId}">${point.kNumber}&nbsp;${point.kName}--%>
                                </c:forEach>
                            </div>
                        </div>
                        <div class="am-form-group">
                            <label class="am-u-sm-3 am-form-label"><font color="#db7093">抽取选择题个数</font></label>
                            <div class="am-u-sm-9">
                                <select name="choiceCount">
                                    <option value="0">抽取选择题的个数 不选默认为0题</option>
                                    <c:forEach var="num" begin="0" end="${selectNum}" step="1">
                                        <option value="${num}">${num}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="am-form-group">
                            <label class="am-u-sm-3 am-form-label"><font color="#db7093">填写选择题分值 </font></label>
                            <div class="am-u-sm-9">
                                <select name="choiceSocre">
                                    <option value="5">请选择 选择题 每题的分值 默认每题5分</option>
                                    <c:forEach var="num" begin="0" end="100" step="1">
                                        <option value="${num}">${num}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>


                        <div class="am-form-group">
                            <label class="am-u-sm-3 am-form-label"><font color="#6495ed">抽取填空题个数</font></label>
                            <div class="am-u-sm-9">
                                <select name="fillBlankCount">
                                    <option value="0">抽取填空题的个数 不选默认为0题</option>
                                    <c:forEach var="num" begin="0" end="${fillBlankNum}" step="1">
                                        <option value="${num}">${num}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="am-form-group">
                            <label class="am-u-sm-3 am-form-label"><font color="#6495ed">填写填空题分值 </font></label>
                            <div class="am-u-sm-9">
                                <select name="fillBlankSocre">
                                    <option value="5">请选择 填空题 每题的分值 默认每题5分</option>
                                    <c:forEach var="num" begin="0" end="100" step="1">
                                        <option value="${num}">${num}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="am-form-group">
                            <label class="am-u-sm-3 am-form-label"><font color="#9acd32">抽取简答题个数</font></label>
                            <div class="am-u-sm-9">
                                <select name="answerCount">
                                    <option value="0">抽取简答题的个数 不选默认为0题</option>
                                    <c:forEach var="num" begin="0" end="${answerNum}" step="1">
                                        <option value="${num}">${num}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="am-form-group">
                            <label class="am-u-sm-3 am-form-label"><font color="#9acd32">填写简答题分值 </font></label>
                            <div class="am-u-sm-9">
                                <select name="answerSocre">
                                    <option value="5">请选择 简答题 每题的分值 默认每题5分</option>
                                    <c:forEach var="num" begin="0" end="100" step="1">
                                        <option value="${num}">${num}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="am-form-group">
                            <label class="am-u-sm-3 am-form-label"><font color="purple">抽取判断题个数</font></label>
                            <div class="am-u-sm-9">
                                <select name="judgeCount">
                                    <option value="0">抽取判断题的个数 不选默认为0题</option>
                                    <c:forEach var="num" begin="0" end="${judgeNum}" step="1">
                                        <option value="${num}">${num}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="am-form-group">
                            <label class="am-u-sm-3 am-form-label"><font color="purple">填写判断题分值 </font></label>
                            <div class="am-u-sm-9">
                                <select name="judgeSocre">
                                    <option value="5">请选择 判断题 每题的分值 默认每题5分</option>
                                    <c:forEach var="num" begin="0" end="100" step="1">
                                        <option value="${num}">${num}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>


                        <div class="am-form-group">
                            <div class="am-u-sm-9 am-u-sm-push-3">
                                <button type="submit" class="am-btn am-btn-primary">自动生成试卷</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>


    </div>

</div>

<a href="#" class="am-icon-btn am-icon-th-list am-show-sm-only admin-menu"
   data-am-offcanvas="{target: '#admin-offcanvas'}"></a>


<!--[if lt IE 9]>
<script src="http://libs.baidu.com/jquery/1.11.3/jquery.min.js"></script>
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

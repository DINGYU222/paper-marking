<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

<jsp:include page="../head.jsp"/>

<div class="am-cf admin-main">
    <!-- sidebar start -->
    <jsp:include page="../left.jsp"/>
    <!-- sidebar end -->

    <!-- content start -->
    <div class="admin-content">
        <div class="admin-content-body">
            <div class="am-cf am-padding am-padding-bottom-0">
                <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">试卷详情</strong>
                </div>


            </div>


            <div class="am-g">
                <div class="am-u-sm-12 am-u-md-6">
                    <div class="am-btn-toolbar">
                        <div class="am-btn-group am-btn-group-xs">
                            <form action="http://localhost:10088/paper/createDoc/${pid}" method="post">

                                <input type="tel" placeholder="存放的路径" name="path" >
                                <button class="am-btn am-btn-default am-btn-xs am-text-secondary" type="submit">
                                    生成WORD
                                </button>
                            </form>
                        </div>


                    </div>
                </div>
            </div>
            <form>
                <c:forEach var="question" items="${questions}">
                    &nbsp;&nbsp;&nbsp;
                    <div style="font-family:微软雅黑;font-size:19px;font-weight: bold;"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${question.content}<font
                            color="#FF0000"></font></div>
                    <div>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${question.rightAnswers}&nbsp;&nbsp;正确答案<font
                            color="#FF0000">(${question.rightAnswers})</font></div>
                </c:forEach>
            </form>
        </div>
    </div>
    <hr/>


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

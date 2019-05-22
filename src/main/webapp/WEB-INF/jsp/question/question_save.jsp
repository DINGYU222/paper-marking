<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<html class="no-js">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Amaze UI Admin user Examples</title>
  <meta name="description" content="这是一个 user 页面">
  <meta name="keywords" content="user">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="renderer" content="webkit">
  <meta http-equiv="Cache-Control" content="no-siteapp" />
  <link rel="icon" type="image/png" href="http://127.0.0.1:10088/assets/i/favicon.png">
  <link rel="apple-touch-icon-precomposed" href="http://127.0.0.1:10088/assets/i/app-icon72x72@2x.png">
  <meta name="apple-mobile-web-app-title" content="Amaze UI" />
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
    <strong>Amaze UI</strong> <small>后台管理模板</small>
  </div>

  <button class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-success am-show-sm-only" data-am-collapse="{target: '#topbar-collapse'}"><span class="am-sr-only">导航切换</span> <span class="am-icon-bars"></span></button>

  <div class="am-collapse am-topbar-collapse" id="topbar-collapse">

    <ul class="am-nav am-nav-pills am-topbar-nav am-topbar-right admin-header-list">
      <li><a href="javascript:;"><span class="am-icon-envelope-o"></span> 收件箱 <span class="am-badge am-badge-warning">5</span></a></li>
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
      <li class="am-hide-sm-only"><a href="javascript:;" id="admin-fullscreen"><span class="am-icon-arrows-alt"></span> <span class="admin-fullText">开启全屏</span></a></li>
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
        <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">增加题目</strong> / <small>Question information</small></div>
      </div>

      <hr/>

      <div class="am-g">
        <div class="am-u-sm-12 am-u-md-4 am-u-md-push-8">
        </div>

        <div class="am-u-sm-12 am-u-md-8 am-u-md-pull-4">
          <form class="am-form am-form-horizontal" action="http://localhost:10088/question/save" method="post">
            <div class="am-form-group">
              <label  class="am-u-sm-3 am-form-label">选择课程</label>
              <div class="am-u-sm-9">
                <select data-am-selected="{btnSize: 'sm'}" name="cid">
                  <c:forEach var="course" items="${courses}">
                  <option value="${course.cid}">${course.name}</option>
                  </c:forEach>
                </select>

              </div>
            </div>

            <div class="am-form-group">
              <label for="user-phone" class="am-u-sm-3 am-form-label">所属知识点</label>
              <div class="am-u-sm-9">
                <select data-am-selected="{btnSize: 'sm'}" name="kid">
                  <c:forEach var="point" items="${points}">
                    <option value="${point.kid}">${point.kid} . ${point.kname}</option>
                  </c:forEach>
                </select>
              </div>
            </div>

            <div class="am-form-group">
              <label for="user-QQ" class="am-u-sm-3 am-form-label" >试题内容</label>
              <div class="am-u-sm-9">
                <input type="text"  id="user-QQ" placeholder="试题内容" name="content">
              </div>
            </div>
            <div class="am-form-group">
              <label for="user-QQ" class="am-u-sm-3 am-form-label" >正确答案</label>
              <div class="am-u-sm-9">
                <input type="text"  id="x" placeholder="正确答案" name="rightAnswers">
              </div>
            </div>


            <div class="am-form-group">
              <label for="user-QQ" class="am-u-sm-3 am-form-label">题型</label>
              <div class="am-u-sm-9">
                <select data-am-selected="{btnSize: 'sm'}" name="type">
                    <option value="选择题">选择题</option>
                    <option value="填空题">填空题</option>
                    <option value="判断题">判断题</option>
                    <option value="简答题">简答题</option>
                </select>
              </div>
            </div>

            <div class="am-form-group">
              <label for="user-QQ" class="am-u-sm-3 am-form-label">试题难度</label>
              <div class="am-u-sm-9">
                <input type="text" placeholder="难度系数 填写0-1之间的数 如0.5" name="questionRating">
              </div>
            </div>




            <div class="am-form-group">
              <div class="am-u-sm-9 am-u-sm-push-3">
                <button type="submit" class="am-btn am-btn-primary">保存修改</button>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>



  </div>

</div>

<a href="#" class="am-icon-btn am-icon-th-list am-show-sm-only admin-menu" data-am-offcanvas="{target: '#admin-offcanvas'}"></a>



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

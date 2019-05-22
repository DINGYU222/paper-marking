<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" pageEncoding="UTF-8" %>


<div class="admin-sidebar am-offcanvas" id="admin-offcanvas">
    <div class="am-offcanvas-bar admin-offcanvas-bar">
        <ul class="am-list admin-sidebar-list">
            <%--<li><a href="admin-index.html"><span class="am-icon-home"></span> 首页</a></li>--%>
            <li class="admin-parent">
                <a class="am-cf" data-am-collapse="{target: '#collapse-nav'}" href="http://localhost:10088/course"><span
                        class="am-icon-file"></span>
                    课程 <span class="am-icon-star am-fr am-margin-right admin-icon-yellow"></span></a>

                <li><a href="http://localhost:10088/point" class="am-cf"><span
                        class="am-icon-check"></span> 知识点<span
                        class="am-icon-star am-fr am-margin-right admin-icon-yellow"></span></a></li>



            <li><a href="http://localhost:10088/question" class="am-cf"><span
                    class="am-icon-check"></span> 题库<span
                    class="am-icon-star am-fr am-margin-right admin-icon-yellow"></span></a></li>
            <li><a href="http://localhost:10088/paper"><span class="am-icon-th"></span> 试卷<span
                    class="am-icon-star am-fr am-margin-right admin-icon-yellow"></span></a></li>

            <li><a href="http://localhost:10088/autoMP"><span class="am-icon-puzzle-piece"></span> 自动组卷<span
                    class="am-icon-star am-fr am-margin-right admin-icon-yellow"></span></a></li>

                <li><a href="http://localhost:10088/moMP"><span class="am-icon-puzzle-piece"></span> 手动组卷<span
                        class="am-icon-star am-fr am-margin-right admin-icon-yellow"></span></a></li>


            </li>

        </ul>
    </div>
</div>



<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="css/layui.css" media="all">
</head>
<body>
<h2>Hello World!</h2>

<table class="layui-hide" id="test"></table>

<script src="js/layui.js" charset="utf-8"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->

<table class="layui-table">
    <colgroup>
        <col width="150">
        <col width="150">
        <col width="200">
        <col>
    </colgroup>
    <thead>
    <tr>
        <th>ID</th>
        <th>名字</th>
        <th>出生日期</th>
        <th>创表时间</th>
    </tr>
    </thead>
    <tbody>
        <c:forEach items="${requestScope.peoples}" var="people">
            <tr>
                <td>${people.id}</td>
                <td>${people.name}</td>
                <td>${people.birthday}</td>
                <td>${people.createTime}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<hr>
<form action="upload" method="post" enctype="multipart/form-data">
    选择文件:<input type="file" name="file" width="120px">
    <input type="submit" value="上传">
</form>
<hr>
<form action="down" method="get">
    <input type="submit" value="下载">
</form>

<a href="/down/ecpbm.rar">ecpbm.rar</a>
<a href="/down/简历、面试和offer.txt">简历、面试和offer.txt</a>

</body>
</html>

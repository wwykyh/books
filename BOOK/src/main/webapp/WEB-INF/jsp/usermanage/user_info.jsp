<%--
  Created by IntelliJ IDEA.
  User: wangnan
  Date: 2019/3/05
  Time: 9:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>用户详情</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common/iconfont/iconfont.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common/layout.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dvpt/css/libs.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/demo/style.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/theme/blue.css" id="style" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/dvpt/config.js"></script>
    <!-- 改造的脚本 -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/extend.js"></script>
    <!-- 共有的控件 -->
    <script data-main="main" src="${pageContext.request.contextPath}/dvpt/require.min.2.1.11.js"></script>
</head>
<body>
<div class="panel">
    <div class="panel-header">
        <h2>用户详情</h2>
        <a href="javascript:" class="arrow up"></a>
    </div>
    <div class="panel-body panel-noborder">
        <div class="write-box">
                <table class="form-table" width="100%">
                    <tr>
                        <th width="17%"><span class="ft-need"></span>用户ID：</th>
                        <td width="33%">${userInfo.userId}</td>
                        <th width="17%"><span class="ft-need"></span>姓名：</th>
                        <td width="33%"> ${userInfo.xm}</td>
                    </tr>
                    <tr>
                        <th width="17%"><span class="ft-need"></span>部门：</th>
                        <td width="33%">${userInfo.bm}</td>
                        <th width="17%"><span class="ft-need"></span>密码：</th>
                        <td width="33%"> ${userInfo.pwd}</td>
                    </tr>
                    <tr>
                        <th width="17%"><span class="ft-need"></span>次数：</th>
                        <td width="33%">${userInfo.cs}</td>
                        <th width="17%"><span class="ft-need"></span>地址：</th>
                        <td width="33%"> ${userInfo.dz}</td>
                    </tr>
                    <tr>
                        <th width="17%"><span class="ft-need"></span>Email：</th>
                        <td width="33%">${userInfo.email}</td>
                        <th width="17%"><span class="ft-need"></span>个人说明：</th>
                        <td width="33%"> ${userInfo.grsm}</td>
                    </tr>
                    <tr>
                        <th width="17%"><span class="ft-need"></span>是否黑名单：</th>
                        <td width="33%">${userInfo.ishmd}</td>
                        <th width="17%"><span class="ft-need"></span>可借图书册数：</th>
                        <td width="33%"> ${userInfo.kjtscs}</td>
                    </tr>
                    <tr>
                        <th width="17%"><span class="ft-need"></span>当前借阅图书ID：</th>
                        <td width="33%">${userInfo.isbn}</td>
                        <th width="17%"><span class="ft-need"></span>是否为管理员：</th>
                        <td width="33%"> ${userInfo.isadmin}</td>
                    </tr>
                </table>
        </div>
    </div>
</div>
</body>
</html>

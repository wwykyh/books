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
    <div class="panel-body panel-noborder">
        <div class="write-box">
            <table class="form-table" width="100%">
                <tr>
                    <th width="17%"><span class="ft-need"></span>图书编号：</th>
                    <td width="33%">${tborrow.sId}</td>
                    <th width="17%"><span class="ft-need"></span>书名：</th>
                    <td width="33%"> ${tborrow.sm}</td>
                </tr>
                <tr>
                    <th width="17%"><span class="ft-need"></span>借阅日期：</th>
                    <td width="33%">${tborrow.jyrq}</td>
                    <th width="17%"><span class="ft-need"></span>计划归还日期：</th>
                    <td width="33%"> ${tborrow.jhghrq}</td>
                </tr>
                <tr>
                    <th width="17%"><span class="ft-need"></span>续借日期：</th>
                    <c:choose>
                        <c:when test="${tborrow.xjrq == null || tborrow.xjrq=='' || tborrow.xjrq=='undefine'}">
                            <td width="33%">暂无续借信息</td>
                        </c:when>
                        <c:otherwise>
                            <td width="33%"> ${tborrow.xjrq}</td>
                        </c:otherwise>
                    </c:choose>
                    <th width="17%"><span class="ft-need"></span>当前借阅状态：</th>
                    <td width="33%"> ${jyzt}</td>
                </tr>
                <tr>
                    <th width="17%"><span class="ft-need"></span>确认状态：</th>
                    <td width="33%">${status}</td>
                    <th width="17%"><span class="ft-need"></span>确认不通过原因：</th>
                    <td width="33%" id="ishmd">${tborrow.bz}</td>
                </tr>
            </table>
        </div>
    </div>
</div>
</body>
</html>

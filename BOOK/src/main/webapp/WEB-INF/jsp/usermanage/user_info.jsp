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
    <%--弹窗引用Jquery--%>
    <script type="text/javascript" src="../dvpt/jquery.min.js"></script>
    <!-- 共有的控件 -->
    <%--<script data-main="main" src="${pageContext.request.contextPath}/dvpt/require.min.2.1.11.js"></script>--%>

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
                        <th width="17%"><span class="ft-need"></span>姓名：</th>
                        <td width="33%"> ${userInfo.xm}</td>
                        <th width="17%"><span class="ft-need"></span>用户ID：</th>
                        <td width="33%">${userInfo.userId}</td>
                    </tr>
                    <tr>
                        <th width="17%"><span class="ft-need"></span>部门：</th>
                        <td width="33%">${userInfo.bm}</td>
                        <th width="17%"><span class="ft-need"></span>地址：</th>
                        <td width="33%"> ${userInfo.dz}</td>
                    </tr>
                    <tr>
                        <th width="17%"><span class="ft-need"></span>是否黑名单：</th>
                        <td width="33%" id="ishmd"><div id="showIshmd" style="font-size: 16px">未赔偿</div></td>
                        <th width="17%"><span class="ft-need"></span>可借时长：</th>
                        <td width="33%">${userInfo.kjsc}天</td>
                    </tr>
                    <tr>
                        <th width="17%"><span class="ft-need"></span>是否为管理员：</th>
                        <td width="33%" id="isadmin"><div id="showIsadmin" style="font-size: 16px">未赔偿</div></td>
                        <th width="17%"><span class="ft-need"></span>Email：</th>
                        <td width="33%">${userInfo.email}</td>
                    </tr>
                    <tr>
                        <th width="17%"><span class="ft-need"></span>可借图书册数：</th>
                        <td width="33%"> ${userInfo.kjtscs}</td>
                        <th width="17%"><span class="ft-need"></span>个人说明：</th>
                        <td width="33%"> ${userInfo.grsm}</td>
                    </tr>
                </table>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function (){
        var ishmd = ${userInfo.ishmd};
        var isadmin  = ${userInfo.isadmin};

        if (ishmd==1){
            $("#showIshmd").text("是");
        }else {
            $("#showIshmd").text("否");
        }

        if (isadmin==1){
            $("#showIsadmin").text("是");
        }else {
            $("#showIsadmin").text("否");
        }
    });
</script>
</body>
</html>

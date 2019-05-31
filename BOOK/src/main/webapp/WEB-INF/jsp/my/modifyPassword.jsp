<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/1/24
  Time: 22:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>修改密码</title>
    <link rel="stylesheet" href="js/libs/jquery/jquery.datepick.css" type="text/css">
    <script type="text/javascript" src="js/libs/jquery/jquery.datepick.js"></script>
    <script type="text/javascript" src="js/libs/jquery/jquery.datepick-zh-CN.js"></script>
</head>
<body>
<div class="panel-body panel-noborder">
    <div class="write-box">
        <form role="form" method="post" class="form-modifypwd">
            <input id="userid" value="${sessionScope.userId}" type="hidden">
            <table class="form-table" width="100%">
                <head>
                    <tr>
                        <th width="40%"></th>
                        <th width="40%"></th>
                    </tr>
                </head>
                <tr>
                    <th width="17%"><span class="ft-need">*</span>旧密码：</th>
                    <td width="216px">
                        <input id="oldPwd" name="user.oldPwd" data-validate="required number" type="password"
                               class="input-text ">
                        <img id="imgA" src="../../../picture/eyes.png" title="点击显示密码"  align="middle" style="margin: 0px 0px 7px"  onclick="change('oldPwd','imgA')">
                    </td>

                </tr>
                <tr>
                    <th width="17%"><span class="ft-need">*</span>新密码：</th>
                    <td>
                        <input id="newPwd" name="user.newPwd" data-validate="required" type="password"
                               class="input-text ">
                        <img id="imgB" src="../../../picture/eyes.png" title="点击显示密码" align="middle" style="margin: 0px 0px 7px"  onclick="change('newPwd','imgB')">
                    </td>
                </tr>
                <tr>
                    <th width="17%"><span class="ft-need">*</span>确认密码：</th>
                    <td>
                        <input id="newPwdT" name="user.oldPwdT" data-validate="required" type="password"
                               class="input-text ">
                        <img id="imgC" src="../../../picture/eyes.png" title="点击显示密码" align="middle" style="margin: 0px 0px 7px" onclick="change('newPwdT','imgC')">
                    </td>
                </tr>
                <tr>
                    <th><input type="reset" class="btn" value="重置"></th>
                    <td><input type="button" class="btn" value="提交" onclick="updata()"></td>
                    <%--<td><button id="btnSubmitOne" class="btn">提交</button></td>--%>
                </tr>
            </table>
        </form>
    </div>
</div>
<script type="text/javascript">

    function change(oldPwd, img) {
        var btn = document.getElementById(img);
        var pass = document.getElementById(oldPwd)
        btn.onmousedown = function () {
            pass.type = "text"
        };
        btn.onmouseup = btn.onmouseout = function () {
            pass.type = "password"
        }
    }

    function updata() {
        var oldPwd = $("#oldPwd").val();
        var newPwd = $("#newPwd").val();
        var newPwdt = $("#newPwdT").val();
        var userId =${user.userId};
        // alert(userid);
        // alert(oldpwd);
        // alert(newpwd);
        if (null == oldPwd && "" == oldPwd) {
            alert("旧密码不能为空！")
        } else if (newPwdt == newPwd) {
            $.ajax({
                url: "/modifyPassword/updataPassword",
                data: {"oldPwd": oldPwd, "newPwd": newPwd, "userId": userId},
                success: function (data) {
                    alert(data);
                }
            });
        } else {
            alert("两次新密码输入不一致！")
        }

    }


</script>
</body>

</html>

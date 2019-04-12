<%--
  Created by IntelliJ IDEA.
  User: 999
  Date: 2019/3/12
  Time: 15:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="css/common/iconfont/iconfont.css"/>
    <link rel="stylesheet" type="text/css" href="css/common/layout.css"/>
    <link rel="stylesheet" type="text/css" href="dvpt/css/libs.css"/>
    <link rel="stylesheet" type="text/css" href="css/demo/style.css"/>
    <link rel="stylesheet" type="text/css" href="css/theme/blue.css" id="style"/>
    <script type="text/javascript" src="dvpt/config.js"></script>
    <!-- 改造的脚本 -->
    <script type="text/javascript" src="js/extend.js"></script>
    <!-- 共有的控件 -->
    <script data-main="main" src="dvpt/require.min.2.1.11.js"></script>

</head>
<form id="form">
    <table class="form-table">
        <tr>
            <th width="15%"><span class="ft-need">*</span>姓名</th>
            <td width="35%"><input type="text" class="input-text" name="xm" id="username" data-validate="required"
                                   onblur='checkName()'/></td>
            <th width="15%">部门</th>
            <td width="35%"><input type="text" class="input-text" name="bm" data-validate="required"/></td>
        </tr>
        <tr>

            <th width="15%">邮箱</th>
            <td width="35%"><input type="text" class="input-text" name="email" data-validate="required"/></td>
            <th width="15%">联系方式</th>
            <td width="35%"><input type="text" class="input-text" name="lxfs" data-validate="required"/></td>
        </tr>
        <tr>
            <th width="15%"><span class="ft-need">*</span>密码</th>
            <td width="35%"><input type="text" class="input-text" id="pwd" name="pwd" value="123456"/></td>
        </tr>
        <tr>

            <th width="20%"><input type="button" class="btn btn-default" value="提交" onclick="doreg()">
                <input type="button" class="btn" value="重置" onclick="reset()"></th>
        </tr>
    </table>
</form>
</body>
<script>

    /**
     * ajax验证用户名是否重复
     */
    function checkName() {
        $.ajax({
            type: 'GET',
            url: "checkName",
            data: {"username": $("#username").val()},// 你的form id
            success: function (data) {
                if (data == '0') {
                    alert("用户名已被注册");
                    $("#username").val("");
                }
            }
        })
    }


    function doreg() {

        var username = $("#username").val();
        var pwd = $("#pwd").val();
        //alert(username.length==0 );
        if (username.length == 0 || pwd.length == 0) {
            alert("用户名密码为空")
        } else {
            $.ajax({
                type: 'post',
                url: "usermanage/doReg",
                data: $('#form').serialize(),
                success: function (data) {
                    if (data == '1') {
                        alert("添加成功");

                    } else {
                        alert("添加失败")
                    }
                }
            })
        }


    }

    //验证是否重复密码
    function checkcpwd() {
        var pwd = document.getElementById("pwd").value;
        var cpwd = document.getElementById("cpwd").value;
        if (pwd == cpwd) {
            return true;
        } else {
            alert("密码不一致");
            return false;
        }
    }
</script>
</html>

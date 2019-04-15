<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

    <link rel="stylesheet" type="text/css" href="css/common/register.css"/>
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
    <title>注册</title>
    <script type="text/javascript">
        //验证是否重复密码
        function checkcpwd() {
            var pwd = document.getElementById("pwd").value;
            var cpwd = document.getElementById("cpwd").value;
          //
            if (pwd == cpwd) {
                return true;
            } else {
                alert("密码不一致");
                return false;
            }
        }

        function checkAllNull() {

            $("input").trigger("blur")
            var username = document.getElementById("username").value;
            var pwd = document.getElementById("pwd").value;
            var email = document.getElementById("email").value;

            //
            if (username.length>0 && email.length>0 && pwd.length>0) {
                return true;
            } else {
                alert("必选项不能为空");
                return false;
            }
        }

        requirejs(['jquery', 'jquery.form'], function ($) {
            $(function () {
                $("#register").formPrefer({
                    showTipsType: 'lineTips', // 报错信息提示方式 lineTips layerTips otherTips
                    submitBtn: '#btnSubmitOne',   // href="/bookAdd"
                    success: function () {
                       // alert("验证通过");
                        $("form[name='register']").submit();
                    }
                });
            });

        });

        $(document).ready(function(){
            $("form").submit(function(e){
                alert("Submitted");
            });

        });


    </script>

    <script>

        /**
         * ajax验证用户名是否重复
         */
        function checkName() {

            $.ajax({
                type: 'GET',
                url: "checkName",
                data: {"username": $("#username").val()},
                success: function (data) {
                    if (data == '0') {
                        alert("用户名已被注册");
                        $("#username").val("");
                    }
                }
            })


        }

    </script>
</head>
<body>
<div id="login">
    <div class="login-header login-wrap">
        <div id="login-logo"></div>
    </div>
    <div class="login-body clearfix">
        <div class="login-wrap">
            <div class="system-img"></div>
            <div class="login-con">
                <span style="color: red">${tip}<br/></span>
                <form action="doreg" method="post" <%--onsubmit="return checkAllNull()"--%>
                      name="register" id="register">
                    <table>
                            <tr>
                                <th>用户名：</th>
                            </tr>
                            <tr>
                            <td><input type="text" class="input-text" name="username"
                                       id="username" placeholder="请输入姓名" onblur='checkName()' data-validate="required"/>
                            </td>
                        </tr>
                        <tr>
                            <th>邮箱：</th>
                        </tr>
                        <tr>
                            <td><input type="text" class="input-text" name="email"
                                       placeholder="请输入邮箱" data-validate="required email"/></td>
                        </tr>
                        <tr>
                            <th>密码：</th>
                        </tr>
                        <tr>
                            <td><input type="password" class="input-text" id="pwd"
                                       name="pwd" placeholder="请输入密码" data-validate="required Ab_8_20"/></td>
                        </tr>
                        <tr>
                            <th>确认密码：</th>
                        </tr>
                        <tr>
                            <td><input type="password" class="input-text" name="cpwd"
                                       id="cpwd" placeholder="确认密码" data-validate="required Ab_8_20" onblur="checkcpwd()"/></td>
                        </tr>

                    </table>
                    <div class="login-btnbar">
                        <input type="button" value="注册" class="btn btn-zsdl" id="btnSubmitOne" name="btnSubmitOne"> <a
                            href="login" class="btn btn-zsdl">返回登录</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<div class="login-footer">
    <p>Copyright©2015 厦门市巨龙信息科技有限公司 版权所有</p>
</div>
</body>


<script type="text/javascript">


    window.onload = function () {
        //  var agoto = document.getElementById("goto");
        var agoto = $("#btnSubmitOne").val();
        alert(1111);
        agoto.onclick = function () {
            alert(111);
           /* var setPage = document.getElementById("setPage").value;
            //  page(setPage);
            window.location.href = "page?pageNumber=" + setPage;*/
            return false;
        };
    };
</script>
</html>
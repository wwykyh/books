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
    <script type="text/javascript">
        requirejs(['main'], function (main) {
            requirejs(['jquery', 'jquery.extend', 'basic-global'], function (jquery, extend, basic) {
                // 计算高度
                window.setInterval(function () {
                    frameHeight();
                }, 200);

                $(function () {
                  /*  // 加载导航事件
                    var sidebar = $("#sidebar").children(".sidebar-list");
                    sidebar.sidebarEffect();
                    $.menuLink(sidebar);*/

                    // 菜单扩展
                    $("#main-tab").menuLinkExtend({
                        contain: ".main-tab ul", // 盒容器
                        childItem: "li", // 列表容器
                        dataItem: "a", // 数据容器
                        closeItem: ".tab-close", // 关闭标签
                        menuClick: "#sidebar .sidebar-list dd a", // 菜单点击
                        rightMenu: ['closeThis', 'closeOthers', 'closeAll'], // 部署不同的功能及排序
                    });
                });
            });
        });
    </script>
</head>
<form action="doreg" method="post" onsubmit="return checkcpwd()"
      >
<table class="form-table">
    <tr>
        <th width="15%"><span class="ft-need">*</span>姓名：</th>
        <td width="35%"><input type="text" class="input-text" name="username" /></td>
        <th width="15%">邮箱</th>
        <td><input type="text" class="input-text" name="email"/></td>
    </tr>
    <tr>
        <th>密码</th>
        <td><input type="text" class="input-text" name="pwd"/></td>
        <th>确认密码</th>
        <td><input type="text" class="input-text" name="cpwd"/></td>
    </tr>
    <tr>

        <th width="20%"><input type="submit" class="btn btn-default" value="提交"></th>
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
            type : 'GET',
            url : "checkName",
            data :{"username":$("#username").val()},
            success : function(data) {
                if(data=='0'){
                    alert("用户名已被注册");
                    $("#username").val("");
                }
            }
        })
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

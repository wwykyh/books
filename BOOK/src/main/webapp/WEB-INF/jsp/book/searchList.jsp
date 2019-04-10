<%--
  Created by IntelliJ IDEA.
  User: wangweiyan
  Date: 2019-04-09
  Time: 15:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
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
    <meta charset="UTF-8">
    <title>百度搜索</title>
    <style>
        * {
            margin: 0;
            padding: 0;
        }

        .show {
            margin: 200px auto;
            width: 340px;
        }

        #text {
            /*去掉默认样式*/
            appearance: none;
            width: 250px;
            height: 20px;
            outline: 0;
        }

        #go {
            display: inline-block;
            text-decoration: none;
            text-align: center;
            line-height: 25px;
            border: 0;
            width: 70px;
            height: 25px;
            font-size: 14px;
            color: white;
            background-color: rgb(33, 134, 250);
        }

        .search {
            width: 252px;
            border: 1px solid #ccc;
            display: none;
        }

        ul {
            list-style: none;
        }

        li {
            line-height: 20px;
            font-size: 14px;
            cursor: pointer;
            box-sizing: border-box;
            padding: 0 5px;
        }

        li:hover {
            background-color: rgb(230, 230, 230);
        }
    </style>
</head>
<body>
<div class="show">
    <%--
       <img src="css/common/login/index.jpg" alt="">
    --%>

    <input type="text" id="dim" <%--style="height: 40px;width: 250px"--%>>
    <%--
            <a id="go"  type="button" value="查询" &lt;%&ndash;onclick="sub()"&ndash;%&gt;>搜索一下</a>
    --%>
    <a href="javascript:subm()" id="go">百度一下</a>

    <div class="search">
        <ul>

        </ul>
    </div>

    <script>

        requirejs(
            ['jquery', 'ligerGrid', 'dg.datePicker', 'artdialog'],

            function ($, datepicker) {
                $("#d100").on('focus', function () {
                    $(this).datePicker();
                });
                $("#d101").on('focus', function () {
                    $(this).datePicker();
                });

            });


        //ajax提交表单
    function subm() {
        // alert("详情" + id) ;
        var dim = $("#dim").val();

        art.dialog.open('page?dim=' + dim, {
            title: '图书检索',
            width: 900,
            height: 600,
            id: 'search',
            //ok: true,
            // okVal: "打印",
           // cancel: true,
            //cancelVal: "关闭"
        });

    }</script>
</body>
</html>

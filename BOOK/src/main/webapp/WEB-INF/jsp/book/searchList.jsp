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
<%--<head>
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
    &lt;%&ndash;
       <img src="css/common/login/index.jpg" alt="">
    &ndash;%&gt;

    <input type="text" id="dim" &lt;%&ndash;style="height: 40px;width: 250px"&ndash;%&gt;>
    &lt;%&ndash;
            <a id="go"  type="button" value="查询" &lt;%&ndash;onclick="sub()"&ndash;%&gt;>搜索一下</a>
    &ndash;%&gt;
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
</body>--%>

<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <link rel="stylesheet" type="text/css" href="css/style.css"/>
    <%--<script type="text/javascript" src="js/jQuery1.7.js"></script>

    <link rel="stylesheet" type="text/css" href="css/style2.css"/>

    <link rel="stylesheet" type="text/css" href="css/style.css"/>
    <link rel="stylesheet" type="text/css" href="css/common/iconfont/iconfont.css"/>
    <link rel="stylesheet" type="text/css" href="css/common/layout.css"/>
    <link rel="stylesheet" type="text/css" href="dvpt/css/libs.css"/>
    <link rel="stylesheet" type="text/css" href="css/demo/style.css"/>
    <link rel="stylesheet" type="text/css" href="css/theme/blue.css" id="style"/>
    <script type="text/javascript" src="dvpt/config.js"></script>
    <!-- 改造的脚本 -->
    <script type="text/javascript" src="js/extend.js"></script>
    <!-- 共有的控件 -->
    <script data-main="main" src="dvpt/require.min.2.1.11.js"></script>--%>
    <meta charset="UTF-8">
    <title>图书检索</title>
    <style type="text/css">
        /*div{
        text-align:right;
        }*/
        body {
            font-family: arial;
            font-size: 12px;
        }

        #topbar { /*id选择器*/
            text-align: right;
            font-sixe: 13px;
            margin-top: 18px;
            padding-right: 5px;
        }

        #topbar1 a {
            color: #999;
            margin-left: 25px;
            margin-top: 18px
        }

        #topbar2 a {
            color: #999;
        }

        /*#topbar0 span{
        color:#999;
        }8*/
        #topbar2 span {
            color: #999;
        }

        #topbar a {
            color: #333;
            margin-left: 20px;
        }

        .bola {
            font-weight: 700;
        }

        #topbar #morepro {
            background: #2d78f4;
            color: #fff;
            padding: 3px;
        }

        .centerdiv {
            text-align: center;
        }

        #searchText {
            width: 540px;
            height: 32px;
            border: 1px solid #2d78f4;
        }

        #submitBtn {
            width: 100px;
            height: 36px;
            background: #3385ff;
            color: #fff;
            border: none;
        }

        .iconCls0 {
               display: inline-block;
             width: 14px;
             height: 17px;
              background: url(icons_5859e57.png) -600px -95px;
        }

        .iconCls {
              display: inline-block;
              width: 14px;
             height: 17px;
             background: url(icons_5859e57.png) -624px -95px;
        }

    </style>
</head>
<body>
                                                                                                                                                          
 
<div class="centerdiv"><img src="images/bookLog.jpg" width="270" style="margin-top:100px" style="color:#999"></img>
</div>
<div class="centerdiv">
    <form><input id="searchText" name="dim" placeholder="书名、作者"><input id="submitBtn" type="button" value="GO" style="margin-top:30px"
                                                   onclick="sub()"></form>
</div>
<div style="height: 270px;width: 100%">
    <div style="margin-left: 30px;margin-top: 40px;margin-right:10px;height: 40%;width: 100%">
        <p style="text-align: left; font-size: 20px;margin-left: 10px"><strong>热门图书</strong></p>
        <c:forEach items="${borrow}" var="p">
            <div class="new_prod_box" style="margin: 10px 10px 10px 10px">

                <div style="height:60%;width: 100%">
                    <a href="javascript:void(0);" onclick="onBookInfo('${p.sId}')">
                        <img src="${p.book.picture}"
                             alt="" title=""
                             class="thumb"
                             border="0"
                             style="height: 150px;width: 100px"/></a>
                </div>
                <div style="width: auto;height: 20%">
                    <a href="javascript:void(0);"
                       onclick="onBookInfo('${p.sId}')"><strong>${p.sm}</strong></a>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<script>

    requirejs(
        ['jquery', 'ligerGrid', 'dg.datePicker', 'artdialog'],
    );

    //ajax提交表单
    function sub() {
        var dim = $("input[name='dim']").val();
        art.dialog.open('page?dim=' + dim, {
            title: '图书检索',
            width: 900,
            height: 600,
            id: 'search'
            //ok: true,
            // okVal: "打印",
            // cancel: true,
            //cancelVal: "关闭"
        });

    }

    function onBookInfo(id) {
        // alert("详情" + id) ;
        art.dialog.open('book_info?id=' + id, {
            title: '图书详情',
            width: 900,
            height: 820,
            id: 'book_info',
            //ok: true,
            // okVal: "打印",
            //cancel: true,
            // cancelVal: "关闭"
        });
    }

</script>
</body>


</html>

<%--
  Created by IntelliJ IDEA.
  User: wangweiyan
  Date: 2019-04-02
  Time: 14:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
    <title>Book Store</title>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <link rel="stylesheet" type="text/css" href="css/style1.css"/>
    <link rel="stylesheet" href="css/lightbox.css" type="text/css" media="screen"/>

    <script src="js/prototype.js" type="text/javascript"></script>
    <script src="js/scriptaculous.js?load=effects" type="text/javascript"></script>
    <script src="js/lightbox.js" type="text/javascript"></script>
    <script type="text/javascript" src="js/java.js"></script>


    <link rel="stylesheet" type="text/css" href="css/common/iconfont/iconfont.css" />
    <link rel="stylesheet" type="text/css" href="css/common/layout.css" />
    <link rel="stylesheet" type="text/css" href="dvpt/css/libs.css" />
    <link rel="stylesheet" type="text/css" href="css/demo/style.css" />
    <link rel="stylesheet" type="text/css" href="css/theme/blue.css" id="style" />
    <script type="text/javascript" src="dvpt/config.js"></script>
    <!-- 改造的脚本 -->
    <script type="text/javascript" src="js/extend.js"></script>
    <!-- 共有的控件 -->
    <script data-main="main" src="dvpt/require.min.2.1.11.js"></script>


</head>
<body>
<div id="wrap">


    <div class="left_content" style="width: 100%;height: 100%">


        <div class="feat_prod_box_details" style="height:30%;width: auto">

            <div class="prod_img" style="height: 80%;width: 30%;margin-left:100px"><a href="details.html" ><img
                    src="${bookInfo.picture}" alt="" title="" style="height:100%;width: auto"
                    border="0"/></a>
                <br/><br/>
                <a href="${bookInfo.picture}" rel="lightbox"><img src="images/zoom.gif" alt="" title="" border="0"/></a>
            </div>

            <div class="prod_det_box">

                <div class="box_center">
                    <div class="price"><strong>书名:</strong> <span class="red">${bookInfo.sm}</span></div>
                    <div class="price"><strong>出版社:</strong> <span class="red">${bookInfo.cbsmc}</span></div>
                    <div class="price"><strong>作者:</strong> <span class="red">${bookInfo.zz}</span></div>
                    <div class="price"><strong>图书类型:</strong> <span class="red">${bookInfo.lxmc}</span></div>
                    <div class="price"><strong>状态:</strong><span class="red">${bookInfo.status}</span></div>
                    <c:if test="${bookInfo.jyxm!=null}">
                    <div class="price"><strong>当前借阅人:</strong><span class="red">${bookInfo.jyxm}</span></div></c:if>
                    <c:if test="${bookInfo.status=='在库'}">
                    <a href="javascript:void(0);" onclick="borrow('${bookInfo.id}')" class="more" ><img src="images/jy.jpg" alt="" title="" border="0" style="height:26px;width: 96px"/></a></c:if>
                    <div class="clear"></div>
                </div>

                <div class="box_bottom"></div>
            </div>
            <div class="clear"></div>
        </div>


        <div id="demo" class="demolayout" style="margin-left:20%;margin-top:5%">

            <ul id="demo-nav" class="demolayout">
                <li><a class="active" href="#tab1">详情</a></li>
                <li><a class="" href="#tab2">评论</a></li>

            </ul>

            <div class="tabs-container">

                <div style="display: block;" class="tab" id="tab1">
                    <p class="more_details">${bookInfo.jj}
                    </p>

                </div>
                <div style="display: none;" class="tab" id="tab2">
                    <p class="more_details">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
                        tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud
                        exercitation.
                    </p>
                </div>

            </div>


        </div>


        <div class="clear"></div>
    </div><!--end of left content-->


    <div class="clear"></div>


</div>

</body>
<script type="text/javascript">

    requirejs(['jquery','artdialog'], function($) {

    });

    var tabber1 = new Yetii({
        id: 'demo'
    });

    function borrow(id) {
           // alert("详情" + id) ;
        art.dialog.open('borrow?id='+id, {
            title : '图书借阅',
            width : 200,
            height : 250,
            id:'borrow_id',
            //ok: true,
            // okVal: "打印",
            cancel : true,
            cancelVal : "关闭"
        });
    }
</script>
</html>

<%--
  Created by IntelliJ IDEA.
  User: wangweiyan
  Date: 2019-03-28
  Time: 09:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Title</title>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <script type="text/javascript" src="js/jQuery1.7.js"></script>

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
    <script data-main="main" src="dvpt/require.min.2.1.11.js"></script>
    <script type="text/javascript">

        requirejs(
            ['jquery', 'ligerGrid', 'dg.datePicker', 'artdialog']
        );

    </script>

</head>
<body>


<div style="height: 550px ;width: 100%">
    <div style="height: 10%;width: auto;margin-left: 10px;margin-top: 10px">
        <form method="get" action="page" id="pagebean">

            <select id="status" name="status" class="select">
                <option value="">-------------请选择图书状态------------------</option>
                <option value="" <c:if
                        test="${page.status==''}"> selected="selected"</c:if>>全部
                </option>
                <option value="1" <c:if
                        test="${page.status=='1'}"> selected="selected"</c:if>>可借
                </option>
                <option value="0" <c:if
                        test="${page.status=='0'}"> selected="selected"</c:if>>不可借
                </option>
            </select>

            <select id="s_type" name="s_type" class="select">
                <option value="">-------------请选择图书类型------------------</option>
                <c:forEach items="${typeList }" var="t">
                    <option value="${t.typeId }" <c:if
                            test="${page.s_type==t.typeId }"> selected="selected"</c:if> >${t.lxmc }</option>
                </c:forEach>
            </select>

            <input id="dim" type="text" placeholder="书名、作者"
                   class="input-text" value="${page.dim}" name="dim"/>

            <input type="submit" value="查询" class="btn" <%--onclick="sub()"--%>></form>
    </div>
    <c:forEach items="${page.list}" var="p">
        <div class="new_prod_box">

            <div style="height:60%;width: 100%">
                <a href="javascript:void(0);" onclick="onBookInfo('${p.tStore.id}')"><img src="${p.picture}"
                                                                                          alt="" title=""
                                                                                          class="thumb"
                                                                                          border="0"
                                                                                          style="height: 100px;width: 50%"/></a>
            </div>
            <div style="width: auto;height: 20%">
                <a href="javascript:void(0);"
                   onclick="onBookInfo('${p.tStore.id}')"><strong>${p.sm}</strong></a></div>
        </div>
    </c:forEach>
    <div style="width: 100%;height: 5%;float: left ; text-align: center">

        <div class="page">
            <a href="page?pageNumber=1">首页</a>
            <a href="page?pageNumber=${page.pageNumber-1}">上一页</a>
            <!--
                始终保证当前页在中间，一共显示5页
                1.总页码<=5时，显示所有页数
                2.总页码>5时：
                    当前页码<=3时：显示1~5页
                    当前页码>3时：显示当前页在中间的5页,当当前页为倒数前3页时，显示最后5页
            -->
            <c:choose>
                <c:when test="${page.totalPage<=5}">
                    <c:set var="begin" value="1"></c:set>
                    <c:set var="end" value="${page.totalPage}"></c:set>
                </c:when>
                <c:when test="${page.pageNumber<=3}">
                    <c:set var="begin" value="1"></c:set>
                    <c:set var="end" value="5"></c:set>
                </c:when>
                <c:when test="${page.pageNumber>3}">
                    <c:set var="begin" value="${page.pageNumber-2}"></c:set>
                    <c:set var="end" value="${page.pageNumber+2}"></c:set>
                    <c:if test="${page.pageNumber+2>=page.totalPage}">
                        <c:set var="begin" value="${page.totalPage-4}"></c:set>
                        <c:set var="end" value="${page.totalPage}"></c:set>
                    </c:if>
                </c:when>
            </c:choose>
            <!-- 通过循环显示由begin到end的5个页面，当前页用【】标识 -->
            <c:forEach begin="${begin}" end="${end}" var="index">
                <c:if test="${page.pageNumber==index}">
                    <a href="page?pageNumber=${index}">【${index}】</a>
                </c:if>
                <c:if test="${page.pageNumber!=index}">
                    <a href="page?pageNumber=${index}">${index}</a>
                </c:if>
            </c:forEach>

            <a href="page?pageNumber=${page.pageNumber+1}">下一页</a>
            第${page.pageNumber}页，共${page.totalPage}页
            转到第<input id="setPage" type="text" value="${page.pageNumber}"/>页，<a id="goto" href="">跳转</a>
            <a _href="page?pageNumber=${page.totalPage} " data-name="末页"
               href="page?pageNumber=${page.totalPage};">末页</a>

            <script type="text/javascript">
                window.onload = function () {
                    //  var agoto = document.getElementById("goto");
                    var agoto = $("#goto").val();
                    // alert(agoto);
                    agoto.onclick = function () {
                        //alert(111);
                        var setPage = document.getElementById("setPage").value;
                        // page(setPage);
                        alert(setPage);
                        window.location.href = "page?pageNumber=" + setPage;
                        return false;
                    };
                };
            </script>
        </div>
    </div>
</div>
<script type="text/javascript">


    /*var tabber1 = new Yetii({
        id: 'demo'
    });*/

    //ajax提交表单
    function sub() {
        //alert(id);
        $.ajax({
            cache: true,
            type: "GET",
            url: "page",
            data: {"pageNumber": id, "dim": $("#dim").val(), "s_type": $("#s_type").val(), "status": $("#status")},// 你的form id
            async: false,
            error: function (request) {
                alert("Connection error:" + request.error);
            },
            success: function (data) {
                document.write('')
            }
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

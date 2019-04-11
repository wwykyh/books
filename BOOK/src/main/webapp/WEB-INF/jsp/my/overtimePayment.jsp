<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="css/home.css" />
    <link rel="stylesheet" href="js/libs/jquery/jquery.datepick.css" type="text/css">
    <script type="text/javascript" src="js/libs/jquery/jquery.datepick.js"></script>
    <script type="text/javascript" src="js/libs/jquery/jquery.datepick-zh-CN.js"></script>
    <script type="text/javascript" src="dvpt/config.js"></script>
    <!-- 改造的脚本 -->
    <script type="text/javascript" src="js/extend.js"></script>
    <!-- 共有的控件 -->
    <script data-main="main" src="dvpt/require.min.2.1.11.js"></script>

</head>
<body>
<div class="panel">
<div class="main-cont">
    <div class="tab tab-default">
        <div class="tab-contbox">
            <p style="text-align: center; font-size: 20px;">超时信息</p>
            <div>
                <table class="simple-table">
                    <thead>
                    <tr >
                        <th width="20%">图书名称</th>
                        <th width="20%">计划归还日期</th>
                        <th width="20%">已超时时间</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody >
                    <c:forEach items="${tBorrows}" var="tBorrows">
                        <tr id="tr_${tBorrows.id}">
                            <td>${tBorrows.sm}</td>
                            <td>${tBorrows.jhghrq}</td>
                            <td>${tBorrows.bz}天</td>
                            <td>
                                <%--<a href="javascript:void(0);" onclick="renew(${tBorrows.sId},${tBorrows.userId})">续借</a>&nbsp;&nbsp;--%>
                                <a href="javascript:void(0);" onclick="rbook(${tBorrows.sId},${tBorrows.userId})">归还</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</div>
<script type="text/javascript">

    requirejs(['jquery', 'ligerGrid','dg.datePicker', 'artdialog'], function($) {
    });

    // function renew(isbn,userid) {
    //     if (confirm("你是否要续借图书？")){
    //         $.ajax({
    //             url:"/overtimePayment/renew?isbn="+isbn+"&userId="+userid+"",
    //             success:function(data){
    //                 if (data=="success"){
    //                     alert("续借申请成功，请等待管理员审核");
    //                     // $("tr").remove("#tr_"+isbn);
    //                 } else {
    //                     alert("续借申请失败！") ;
    //                 }
    //             }
    //         });
    //     }
    // }

    function rbook(isbn,userid) {
        // alert(isbn);
        // alert(userid);
        if (confirm("你是否要归还图书？")){
            $.ajax({
                url:"/overtimePayment/returnbook?isbn="+isbn+"&userId="+userid+"",
                success:function(data){
                    if (data=="success"){
                        alert("归还申请成功，请等待管理员审核");
                        // $("tr").remove("#tr_"+isbn);
                    } else {
                        alert(data) ;
                    }
                }
            });
        }
    }

</script>

</body>
</html>
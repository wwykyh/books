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
    <p style="text-align: center; font-size: 20px;">个人信息</p>
<div>
    <table class="simple-table">
        <thead>
        <tr>
            <th width="12%">姓名</th>
            <th width="12%">手机号</th>
            <th>地址</th>
            <th>部门</th>
            <th width="8%">可借图书数</th>
            <th>个人说明</th>
        </tr>
        </thead>
        <tbody>
            <tr>
                <td>${userInformation.xm}</td>
                <td>${userInformation.lxfs}</td>
                <td>${userInformation.dz}</td>
                <td>${userInformation.bm}</td>
                <td>${userInformation.kjtscs}</td>
                <td>${userInformation.grsm}</td>
            </tr>
        </tbody>
    </table>
</div>
    <p style="text-align: center; font-size: 20px;">借阅信息</p>
<div>
    <table class="simple-table">
        <thead>
        <tr >
            <th width="12%">图书编号</th>
            <th width="12%">书名</th>
            <th width="12%">作者</th>
            <th width="20%">借阅日期</th>
            <th width="20%">计划归还日期</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody >
        <c:forEach items="${bookBorrow}" var="bookBorrow">
            <tr id="tr_${bookBorrow.isbn}">
                <td>${bookBorrow.isbn}</td>
                <td>${bookBorrow.sm}</td>
                <td>${bookBorrow.zz}</td>
                <td>${bookBorrow.tBorrow.jyrq}</td>
                <td>${bookBorrow.tBorrow.jhghrq}</td>
                <td>
                    <a href="javascript:void(0);" onclick="onCheckInfo(${bookBorrow.tBorrow.id})">查看详情</a>&nbsp;&nbsp;
                    <a href="javascript:void(0);" onclick="renew(${bookBorrow.isbn},${user.userId})">续借</a>&nbsp;&nbsp;
                    <a href="javascript:void(0);" onclick="rbook(${bookBorrow.isbn},${user.userId})">归还</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

    <script type="text/javascript">

        requirejs(['jquery', 'ligerGrid','dg.datePicker', 'artdialog'], function($) {

        });

        function onCheckInfo(userId){
            art.dialog.open('/personal/borrowInfo?id='+userId, {
                title: '借阅详情',
                width: 900,
                height: 350,
                cancel: true,
                cancelVal: "关闭"
            });
        }

        function renew(isbn,userid) {
            if (confirm("你是否要续借图书？")){
                $.ajax({
                    url:"/personal/reNew?isbn="+isbn+"&userId="+userid+"",
                    success:function(data){
                        if (data=="success"){
                            alert("续借申请成功，请等待管理员审核");
                            // $("tr").remove("#tr_"+isbn);
                        } else {
                            alert("续借申请失败！") ;
                        }
                    }
                });
            }
        }

        function rbook(isbn,userid) {
            // alert(isbn);
            // alert(userid);
            if (confirm("你是否要归还图书？")){
                $.ajax({
                    url:"/personal/returnBook?isbn="+isbn+"&userId="+userid+"",
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
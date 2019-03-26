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
<p style="text-align: center; font-size: 20px;">借阅信息</p>
<div>
    <table class="simple-table">
        <thead>
        <tr >
            <th width="20%">消息名称</th>
            <th width="20%">消息日期</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody >
        <c:forEach items="${tBookNews}" var="tBookNews">
            <tr id="tr_${tBookNews.id}">
                <td>${tBookNews.news_name}</td>
                <td>${tBookNews.news_date}</td>
                <td>
                    <a href="javascript:void(0);" onclick="onCheckNewsInfo(${tBookNews.book_isbn},${tBookNews.user_id})">查看详情</a>&nbsp;&nbsp;
                    <a href="javascript:void(0);" onclick="deleteNew(${tBookNews.id})">删除</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<script type="text/javascript">

    requirejs(['jquery', 'ligerGrid','dg.datePicker', 'artdialog'], function($) {

    });

    function onCheckNewsInfo(book_isbn,user_id){
        var isbn = book_isbn;
        var id = user_id;
        art.dialog.open('/news/toNewsDetailInfo?isbn='+isbn+'&userid='+id+'',{
            title: '消息详情',
            width: 900,
            height: 200,
            cancel: true,
            cancelVal: "关闭"
        });
    }

    function deleteNew(id) {
        // alert(isbn);
        // alert(userid);
            $.ajax({
                url:"/news/deleteNews?id="+id,
                success:function(data){
                    if (data=="success"){
                        alert("删除成功！");
                        $("tr").remove("#tr_"+id);
                    } else {
                        alert(data) ;
                    }
                }
            });
    }
</script>

</body>
</html>
<%--
  用户借阅界面
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>消息通知</title>
    <!-- 日期插件，使用jquery -->
    <link rel="stylesheet" href="js/libs/jquery/jquery.datepick.css" type="text/css">
    <script type="text/javascript" src="js/libs/jquery/jquery.datepick.js"></script>
    <script type="text/javascript" src="js/libs/jquery/jquery.datepick-zh-CN.js"></script>
</head>
<body>
<div class="panel">
    <div class="panel-header">
        <p style="text-align: center; font-size: 20px;">消息</p>
        <a href="javascript:;" class="arrow up"></a>
    </div>

    <div class="main-cont">
        <div class="tab tab-default">
            <div class="tab-contbox" >
                <div id="divId">
                    <table class="simple-table" id="tableId">
                        <thead>
                        <tr >
                            <th width="20%" >消息名称</th>
                            <th width="20%">消息日期</th>
                            <th width="25%">内容</th>
                            <th>操作</th>
                            <th width="7%">
                                <input type="checkbox" onclick="checkAll()" id="checkAll"style="margin-top: 10px;float: left" >
                                <button style="margin-top: 5px;float: right" onclick="deleteAll()"id="deleteAll">批量删除</button>
                            </th>
                        </tr>
                        </thead>
                        <tbody >
                        <c:forEach items="${tBookNews}" var="tBookNews">
                            <tr id="tr_${tBookNews.id}">
                                <td>${tBookNews.news_name}</td>
                                <td>${tBookNews.news_date}</td>
                                <c:set var="type" scope="session" value="${tBookNews.news_type}"/>
                                <c:if test="${type<3}">
                                    <td>${tBookNews.tBook.sm}已${tBookNews.news_name}</td>
                                </c:if>
                                <c:if test="${type==3}">
                                    <td>${tBookNews.news_name}</td>
                                </c:if>
                                <td>
                                    <c:if test="${type<3}">
                                        <a href="javascript:void(0);" onclick="onCheckNewsInfo(${tBookNews.book_isbn},${tBookNews.user_id})">查看详情</a>&nbsp;&nbsp;
                                    </c:if>
                                    <a href="javascript:void(0);" onclick="deleteNew(${tBookNews.id})">删除</a>
                                </td>
                                <td><input type="checkbox" name="ck" value="${tBookNews.id}" id="check_${tBookNews.id}"style="margin-top: 10px;float: left" ></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <div id="divId_1" style="display: none">
            <p style=" font-size: 20px;text-align: center;">
                暂无消息
            </p>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">

    requirejs(['jquery', 'ligerGrid','dg.datePicker', 'artdialog'], function($) {
        nullNewsAction();
    });

    function checkAll(){
        var ck=document.getElementsByName("ck");
        for(var i=0;i<ck.length;i++){
            var c=ck[i];
            if(c.checked==true){
                c.checked=false;
            }else {
                c.checked=true;
            }
        }
    }

    function deleteAll() {
        var ck=document.getElementsByName("ck");
        var c = [];
        for(var i=0;i<ck.length;i++){
            var a=ck[i];
            if(a.checked==true){
                c.push(ck[i].value);
            }
        }
        // alert(c)
        if(c.length!=0){
            alert(c);
            $.ajax({
                traditional: true,
                data : {
                    check:c,
                    i:1
                },
                url:"/news/deleteMultipleNews",
                success:function (data) {
                    if(data=="success"){
                        alert("删除成功！");
                        for(var i=0;i<c.length;i++){
                            $("tr").remove("#tr_"+c[i]);
                        }
                    }else {
                        alert("删除失败！");
                    }
                }
            });
        }
    }

    function onCheckNewsInfo(book_isbn,user_id){
        var isbn = book_isbn;
        var id = user_id;
        art.dialog.open('/news/toNewsDetailInfo?isbn='+isbn+'&userId='+id+'',{
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
    function nullNewsAction() {
        var num = document.getElementById("tableId").getElementsByTagName("tr").length;
        if(num==1){

           var div = document.getElementById("divId");
           var div1 = document.getElementById("divId_1");
            div.style.display='none';
            div1.style.display='';
        }
    }
</script>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@page import="java.text.SimpleDateFormat" %>

<%@page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>图书借阅</title>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
</head>
<body>
<form name="borrow" id="borrow">
    <table class="form-table">
        <tr>
            <th width="20%">用户名：</th>
            <td width="35%"><input type="hidden" name="userId"
                                   value="${user.userId }"> <input type="text"
                                                                   class="input-text" readonly="readonly"
                                                                   name="username"
                                                                   value="${user.xm }"/></td>

        </tr>

        <tr>
            <th width="20%">图书编号：</th>
            <td width="35%">
                <input type="text" class="input-text"
                       readonly="readonly" name="id" value="${book.tStore.id }"/></td>


        </tr>
        <tr>
            <th width="20%">书名：</th>
            <td><input type="text" class="input-text" readonly="readonly"
                       value="${book.sm }" name="sm"/></td>

        </tr>
        <tr>

            <th width="20%">图书类型：</th>
            <td width="35%"><input type="text" class="input-text"
                                   value="${book.tType.lxmc}" readonly="readonly"
                                   name="lxmc"/></td>

        </tr>
        <tr>

            <th width="20%">出版社：</th>
            <td width="35%"><input type="text" class="input-text"
                                   value="${book.cbsmc }" readonly="readonly"
                                   name="pub_name"/></td>

        </tr>
        <tr>
            <%
                Date d = new Date();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                String now = df.format(d);
            %>

            <th width="20%">借阅日期：</th>
            <td width="35%"><input type="text" class="input-text"
                                   readonly="readonly" value="<%=now%>" name="jyrq"/></td>

        </tr>
        <tr>
            <th width="20%">借阅周期：</th>
            <td><select class="select" name="jhghrq">
                <option value="7">一周</option>
                <option value="15">半个月</option>
                <option value="30">一个月</option>
            </select></td>

        </tr>
        <tr>

            <th width="20%"><input type="button" class="btn" value="提交" onclick="sub()"></th>
        </tr>
    </table>
</form>
</body>

<script type="text/javascript">
    //ajax提交表单
    function sub() {
        $.ajax({
            cache: true,
            type: "POST",
            url: "doBorrow",
            data: $('#borrow').serialize(),// 你的form id
            async: false,
            error: function (request) {
                alert("Connection error:" + request.error);
            },
            success: function (data) {
                if (data == "0") {
                    alert("图书借阅申请成功，请到图书管理员处拿书！");
                    //  parent.select();
                } else {
                    alert("图书借阅申请失败,超出当前最大借阅数量！");
                }
                parent.art.dialog({id: 'borrow_id'}).close();

            }
        });
    }
</script>
</html>
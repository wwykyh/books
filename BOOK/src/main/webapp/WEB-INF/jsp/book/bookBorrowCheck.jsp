<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/2/18
  Time: 9:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>借阅确认</title>
    <link rel="stylesheet" type="text/css" href="../css/common/iconfont/iconfont.css"/>
    <link rel="stylesheet" type="text/css" href="../css/common/layout.css"/>
    <link rel="stylesheet" type="text/css" href="../dvpt/css/libs.css"/>
    <link rel="stylesheet" type="text/css" href="../css/demo/style.css"/>
    <link rel="stylesheet" type="text/css" href="../css/theme/blue.css" id="style"/>
    <script type="text/javascript" src="../dvpt/config.js"></script>
    <!-- 改造的脚本 -->
    <script type="text/javascript" src="../js/extend.js"></script>
    <!-- 共有的控件 -->
    <script data-main="main" src="../dvpt/jquery.min.js"></script>
    <style>
        .myInput {
            border: none;
            font-size: 16px;
            width: 300px;
        }
    </style>
</head>
<body>
<div class="panel">
    <div class="panel-body panel-noborder">
        <div class="write-box">
            <table class="form-table" width="100%">
                <tr>
                    <th width="17%">图书编号：</th>
                    <td width="33%">
                        <input type="hidden" id="idPk" value="${singleTBorrow.id}" class="input-text"/>
                        <input type="hidden" id="status" value="${singleTBorrow.status}" class="input-text"/>
                        <span>${singleTBorrow.isbn}</span>
                    </td>
                </tr>
                <tr>
                    <th width="17%">书名：</th>
                    <td width="33%">
                        <span>${singleTBorrow.sm}</span>
                    </td>
                </tr>
                <tr>
                    <th width="17%">用户id：</th>
                    <td width="33%">
                        <span>${singleTBorrow.userId}</span>
                    </td>
                </tr>
                <tr>
                    <th width="17%">用户名：</th>
                    <td width="33%">
                        <span>${singleTBorrow.xm}</span>
                    </td>
                </tr>
                <tr>
                    <th width="17%">借阅时间：</th>
                    <td width="33%">
                        <input id="jyrq" value="${singleTBorrow.jyrq}" class="myInput" readonly="readonly"/>
                    </td>
                </tr>
                <tr>
                    <th width="17%">计划归还时间：</th>
                    <td width="33%">
                        <input id="jhghrq" value="${singleTBorrow.jhghrq}" class="myInput" readonly="readonly"/>
                    </td>
                </tr>
                <tr>
                    <th width="17%">联系方式：</th>
                    <td width="33%">
                        <span>${singleTBorrow.lxfs}</span>
                    </td>
                </tr>
                <tr>
                    <th width="17%">备注：</th>
                    <td width="33%">
                        <textarea class="textarea input-wl" id="bz" name="bz">${singleTBorrow.bz}</textarea>
                    </td>
                </tr>
                <tr>
                    <th width="17%"></th>
                    <td width="33%">
                        <c:if test="${singleTBorrow.status == 2}">
                            <input name="check" type="button" class="layer-btn" id="check" value="同意"
                                   onclick="pass();"/>
                        </c:if>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    // 界面上的时间格式转化
    window.onload = function () {
        var newjyrq = new Date($("#jyrq").val());
        $("#jyrq").val(getDateString(newjyrq));

        var newjhghrq = new Date($("#jhghrq").val());
        $("#jhghrq").val(getDateString(newjhghrq));
    }

    function getDateString(data) {
        return data.getFullYear() + "-" + (data.getMonth() + 1) + "-" + data.getDate();
    }

    function pass() {
        var idPk = $("#idPk").val();
        var bz = $("#bz").val();
        $.ajax({
            type: 'post',
            url: '${path}/borrowCheck/',
            data: {_method: "PUT", id: idPk, status: 1, bz: bz},
            success: function (msg) {
                if (msg) {
                    alert("确认成功");
                    // 调用父级窗口的方法，重新刷新界面
                    parent.borrowInfo();
                    // 调用框架的close事件
                    parent.art.dialog({id: 'borrowCheckChild'}).close();
                } else {
                    alert("确认出错");
                }
            }
        });
    }
</script>
</html>

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
    <title>归还审核</title>
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
    <div class="panel-header">
        <h2>借阅审核</h2>
        <a href="javascript:;" class="arrow up"></a>
    </div>

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
                        <span>${singleTBorrow.user.userId}</span>
                    </td>
                </tr>
                <tr>
                    <th width="17%">用户名：</th>
                    <td width="33%">
                        <span>${singleTBorrow.user.xm}</span>
                    </td>
                </tr>
                <tr>
                    <th width="17%">联系方式：</th>
                    <td width="33%">
                        <span>${singleTBorrow.lxfs}</span>
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
                    <th width="17%">归还时间：</th>
                    <td width="33%">
                        <input id="ghrq" value="${singleTBorrow.ghrq}" class="myInput" readonly="readonly"/>
                    </td>
                </tr>
                <tr>
                    <th width="17%">图书损耗程度：</th>
                    <td width="33%">
                        <select class="select" name="sh" id="sh">
                            <option value="">==请选择==</option>
                            <c:choose>
                                <c:when test="${singleTBorrow.sh == 1}">
                                    <option value="1" selected="selected">轻度</option>
                                </c:when>
                                <c:when test="${singleTBorrow.sh == 2}">
                                    <option value="2" selected="selected">中度</option>
                                </c:when>
                                <c:when test="${singleTBorrow.sh == 3}">
                                    <option value="3" selected="selected">重度</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="0" >无损耗</option>
                                    <option value="1" selected="selected">轻度</option>
                                    <option value="2" >中度</option>
                                    <option value="3" >重度</option>
                                </c:otherwise>
                            </c:choose>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="17%"></th>
                    <td width="33%">
                        <c:if test="${singleTBorrow.jyzt == 0}">
                        <input name="check" type="button" class="layer-btn" id="check" value="通过"
                               onclick="pass();"/>
                        <%--<input name="uncheck" type="button" class="layer-btn" id="uncheck" value="不通过"
                               onclick="unPass();"/>--%>
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

        var newghrq = new Date($("#ghrq").val());
        $("#ghrq").val(getDateString(newghrq));
    }

    function getDateString(data) {
        return data.getFullYear() + "-" + (data.getMonth() + 1) + "-" + data.getDate() + " " +
            data.getHours() + ":" + change(data.getMinutes()) + ":" + change(data.getSeconds());
        function change(t) {
            if (t < 10) {
                return "0" + t;
            } else {
                return t;
            }
        }
    }

    function pass() {
        var idPk = $("#idPk").val();
        var sh = $("#sh").val();
        $.get('${path}/revertCheck/check', {id: idPk, sh: sh}, function (msg) {
            if (msg) {
                alert("审核完成");
            } else {
                alert("审核出错");
            }
        });
    }

    function unPass() {
        var idPk = $("#idPk").val();
        var sh = $("#sh").val();
        $.get('${path}/revertCheck/check', {id: idPk, sh: sh}, function (msg) {
            if (msg) {
                alert("审核完成");
            } else {
                alert("审核出错");
            }
        });
    }
</script>
</html>

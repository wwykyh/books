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
                        <select class="select" name="sh" id="sh" onchange="changeSh();">
                            <option value="">==请选择==</option>
                            <option value="0">无损耗</option>
                            <option value="1">轻度</option>
                            <option value="2">中度</option>
                            <option value="3">重度</option>
                        </select>
                    </td>
                </tr>
                <c:if test="${singleTBorrow.jyzt == 2}">
                    <tr>
                        <th width="17%">是否需要赔偿：</th>
                        <td width="33%">
                            <label>
                                <input type="radio" name="pay" value="1" id="yes"/>&nbsp;<label>是</label>
                            </label>&nbsp;&nbsp;&nbsp;&nbsp;
                            <label>
                                <input type="radio" name="pay" value="0" checked="checked"
                                       id="no"/>&nbsp;<label>否</label>
                            </label>
                        </td>
                    </tr>
                    <tr>
                        <th width="17%"></th>
                        <td width="33%">
                            <input name="check" type="button" class="layer-btn" id="check" value="通过"
                                   onclick="pass();"/>
                        </td>
                    </tr>
                </c:if>
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
        return data.getFullYear() + "-" + (data.getMonth() + 1) + "-" + data.getDate();
    }

    function pass() {
        var idPk = $("#idPk").val();
        var sh = $("#sh").val();
        var pays = $("input[name='pay']:checked").val();
        $.ajax({
            type: 'post',
            url: '${path}/revertCheck/',
            data: {_method: "PUT", id: idPk, sh: sh, status: pays},
            success: function (msg) {
                if (msg) {
                    alert("审核完成");
                    // 调用父级窗口的方法，重新刷新界面
                    parent.bookRevertInfo();
                    // 调用框架的close事件
                    parent.art.dialog({id: 'borrowRevertChild'}).close();
                } else {
                    alert("审核出错");
                }
            }
        });
    }

    function changeSh() {
        var value = $("#sh option:selected").val();
        if ("2" === value || "3" === value) {
            $("#yes").attr("checked", "checked");
        } else {
            $("#no").attr("checked", "checked");
        }
    }
</script>
</html>

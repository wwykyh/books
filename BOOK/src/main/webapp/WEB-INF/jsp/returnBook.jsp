<%--
  Created by IntelliJ IDEA.
  User: wangnan
  Date: 2019/3/05
  Time: 13:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
    <title>快捷还书</title>
    <link rel="stylesheet" type="text/css" href="../css/common/iconfont/iconfont.css" />
    <link rel="stylesheet" type="text/css" href="../css/common/layout.css" />
    <link rel="stylesheet" type="text/css" href="../dvpt/css/libs.css" />
    <link rel="stylesheet" type="text/css" href="../css/demo/style.css" />
    <link rel="stylesheet" type="text/css" href="../css/theme/blue.css" id="style" />

    <!-- 共有的控件 -->
    <script data-main="main" src="../dvpt/jquery.min.js"></script>
</head>
<body>
    <div class="panel">
        <div class="panel-header">
            <h2>快捷还书</h2>
            <a href="javascript:;" class="arrow up"></a></div>
        <div class="panel-body panel-noborder">
            <div class="write-box">
                <form action="" method="post" class="form-libs" name="form-libs" id="form-libs">
                <table class="form-table" width="100%">
                    <c:if test="${empty books}">
                        <tr><th width="20%"><span class="ft-need"></span></th>
                            <td width="33%">未借图书，无需还书</td>
                        </tr>
                        <tr><th width="20%"><span class="ft-need"></span></th>
                            <td width="33%"></td>
                        </tr>
                        <tr><th width="20%"><span class="ft-need"></span></th>
                            <td width="33%"><input  type="button" class="btn" value="确定"  onclick="myClose();" style="margin-left: 45px"></td>
                        </tr>
                    </c:if>
                    <c:forEach items="${books}" var="base" varStatus="sta">
                            <c:if test="${not empty base}">
                            <c:if test="${sta.first}">
                                <tr>
                                    <th width="30%"><span class="ft-need"></span>已借图书：</th>
                                    <td width="33%">${base.sm}&nbsp;<input type="checkbox" name="category" value="${base.sId}" checked="checked"/></td>
                                </tr>
                            </c:if>
                            <c:if test="${not sta.first and not sta.last}">
                                <tr>
                                    <th width="30%"><span class="ft-need"></span>已借图书：</th>
                                    <td width="33%">${base.sm}&nbsp;<input type="checkbox" name="category" value="${base.sId}" /></td>
                                </tr>
                            </c:if>
                            <c:if test="${sta.last and not sta.first}">
                                <tr>
                                    <th width="30%"><span class="ft-need"></span>已借图书：</th>
                                    <td width="33%">${base.sm}&nbsp;<input  type="checkbox" name="category" value="${base.sId}" /></td>
                                </tr>
                            </c:if>
                                <c:if test="${sta.last}">
                                    <tr>
                                        <th><input  type="button" class="btn" value="还书"  onclick="sub()"></th>
                                        <td><input  type="button" class="btn" value="取消"  onclick="myClose();"></td>
                                    </tr>
                                </c:if>
                        </c:if>
                    </c:forEach>
                </table>
                </form>
            </div>
        </div>
    </div>
    <script type="text/javascript">
        function myClose() {
            parent.art.dialog({id:'returnMyBook'}).close() ;
        }
        //ajax提交表单
        function sub() {
            $.ajax({
                cache: true,
                type: "POST",
                url:"/return_book_request",
                data:$('#form-libs').serialize(),// 你的formid
                async: false,
                error: function(request) {
                    alert("Connection error:"+request.error);
                },
                success: function(status) {
                    if(status){
                        alert("提交还书申请成功！");
                        parent.art.dialog({id:'returnMyBook'}).close() ;
                    }else {
                        alert("提交还书申请失败！");
                    }
                }
            });
        }
    </script>
</body>
</html>

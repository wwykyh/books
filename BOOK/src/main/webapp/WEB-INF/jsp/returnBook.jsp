<%--
  Created by IntelliJ IDEA.
  User: wangnan
  Date: 2019/3/05
  Time: 13:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
                <form action="/usermanage/userEdit" method="post" class="form-libs" name="form-libs" id="form-libs">
                <table class="form-table" width="100%">
                    <c:forEach items="${books}" var="base">
                    <tr>
                        <th width="17%"><span class="ft-need"></span>已借图书：</th>
                        <td width="33%">${base.sm}<input type="checkbox" name="category" value="${base.sId}"/></td>
                    </tr>
                    </c:forEach>
                        <th></th>
                        <td><input  type="button" class="btn" value="还书"  onclick="sub()"><input  type="button" class="btn" value="取消"  onclick="close();"></td>
                    </tr>
                </table>
                </form>
            </div>
        </div>
    </div>
    <script type="text/javascript">
        $(document).ready(function (){
            <%--if (${books}==null){--%>
                <%--alert("当前未有尚未归还的图书")--%>
        <%--}--%>
        });

        //ajax提交表单
        function sub() {
            $.ajax({
                cache: true,
                type: "POST",
                url:"/usermanage/user_edit",
                data:$('#form-libs').serialize(),// 你的formid
                async: false,
                error: function(request) {
                    alert("Connection error:"+request.error);
                },
                success: function(data) {
                    if(data == "0"){
                        alert("还书成功！");
                        parent.art.dialog({id:'user_edit_window'}).close() ;
                    }else {
                        alert("还书操作失败！");
                    }
                }
            });
        }
    </script>
</body>
</html>

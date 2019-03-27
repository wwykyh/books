
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>图书详情</title>
    <link rel="stylesheet" type="text/css" href="css/common/iconfont/iconfont.css"/>
    <link rel="stylesheet" type="text/css" href="css/common/layout.css"/>
    <link rel="stylesheet" type="text/css" href="dvpt/css/libs.css"/>
    <link rel="stylesheet" type="text/css" href="css/demo/style.css"/>
    <link rel="stylesheet" type="text/css" href="css/theme/blue.css" id="style"/>
    <script type="text/javascript" src="dvpt/config.js"></script>
    <!-- 共有的控件 -->
    <script data-main="main" src="dvpt/require.min.2.1.11.js"></script>
</head>
<body>
<div class="panel">
    <div class="panel-header">
        <h2>用户评价</h2>
        <a href="javascript:" class="arrow up"></a>
    </div>
    <div class="panel-body panel-noborder">
        <div class="write-box">
            <form  method="post" class="form_eva" name="form_eva"id="form_eva"  action="/userBorrow/commentService">
                <table>
                    <tr>
                        <input type="text" name="textComment" id="textComment"  style="width:800px;   height:100px;"/>
                    </tr>
                    <tr>
                        <input type="reset" class="btn" value="重置" />
                        <input type="button"  class="btn" value="提交" onclick="sub()" />
                    </tr>
                </table>
            </form>
            <table class="form-table" width="100%">
                <c:forEach items="${tComment}" var="arr">
                <tr>
                    <th width="10%"><span class="ft-need"></span>历史评价：</th>
                    <td width="70%">${arr.nr}</td>
                    </c:forEach>
                </tr>
            </table>
        </div>
    </div>

</div>
<script type="text/javascript">
    function sub() {
        alert("5145");
        $.ajax({
            cache: true,
            type: "POST",
            url:"userBorrow/commentService",
            data:$('#form_eva').serialize(),// 你的formid
            async: false,
            error: function(request) {
                alert("Connection error:"+request.error);
            },
            success: function(data) {
                if(data == "0"){
                    parent.select();
                    parent.art.dialog({id:'commentInfo_window'}).close() ;
                }else {
                    alert("评价失败");
                }
            }
        });
        alert("sfsf");
    }
</script>
</body>
</html>

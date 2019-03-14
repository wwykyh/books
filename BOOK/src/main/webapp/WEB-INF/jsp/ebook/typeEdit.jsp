<%--
  Created by IntelliJ IDEA.
  User: liulei
  Date: 2019/1/23
  Time: 13:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>图书类型修改</title>
    <link rel="stylesheet" type="text/css" href="css/common/iconfont/iconfont.css" />
    <link rel="stylesheet" type="text/css" href="css/common/layout.css" />
    <link rel="stylesheet" type="text/css" href="dvpt/css/libs.css" />
    <link rel="stylesheet" type="text/css" href="css/demo/style.css" />
    <link rel="stylesheet" type="text/css" href="css/theme/blue.css" id="style" />
    <script type="text/javascript" src="dvpt/config.js"></script>
    <!-- 改造的脚本 -->
    <script type="text/javascript" src="js/extend.js"></script>
    <!-- 共有的控件 -->
    <script data-main="main" src="dvpt/require.min.2.1.11.js"></script>
</head>
<body>
    <div class="panel">
        <div class="panel-header">
            <h2>图书类型修改</h2>
            <a href="javascript:;" class="arrow up"></a></div>
        <div class="panel-body panel-noborder">
            <div class="write-box">
                <form action="typeEdit" method="post" class="form-libs" name="form-type" id="form-type">
                    <input type="hidden" id="typeId" name="typeId" value="${type.typeId}">
                <table class="form-table" width="100%">
                    <tr>
                        <th width="17%"><span class="ft-need">*</span>类型名称：</th>
                        <td width="33%"><input id="lxmc" name="lxmc" value="${type.lxmc}" data-validate="required" type="text" class="input-text "></td>
                    </tr>
                    <tr>
                        <th></th>
                        <td><input type="button" class="btn" value="修改" onclick="sub()" ></td>
                    </tr>
                </table>
                </form>
            </div>
        </div>
    </div>
    <script type="text/javascript">

        //ajax提交表单
        function sub() {
            $.ajax({
                cache: true,
                type: "POST",
                url:"typeEdit",
                data:$('#form-type').serialize(),// 你的formid
                async: false,
                error: function(request) {
                    alert("Connection error:"+request.error);
                },
                success: function(data) {
                    if(data == "0"){
                        //alert("类型修改成功！") ;
                        //$("table input:lt(1)").val("") ;
                        parent.type_select() ;
                        parent.art.dialog({id:'type_edit_window'}).close() ;
                    }else {
                        alert("类型修改失败！");
                    }
                }
            });
        }

    </script>
</body>
</html>

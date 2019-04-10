
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>图书详情</title>
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
        <h2>用户评价</h2>
        <a href="javascript:" class="arrow up"></a>
    </div>
    <div class="panel-body panel-noborder">
        <div class="write-box">
            <form  method="post" class="form-libs" name="form-libs"id="form_eva">
                <table class="form-table" width="100%">
                <tr>
                    <th width="17%"><span class="ft-need"></span>用户名称：</th>
                    <td width="33%">${history.user.xm}</td>
                    <th width="17%"><span class="ft-need"></span>部门：</th>
                    <td width="33%"> ${history.user.bm}</td>
                </tr>
                <tr>
                    <th width="17%"><span class="ft-need"></span>联系方式：</th>
                    <td width="33%">${history.user.lxfs}</td>
                    <th width="17%"><span class="ft-need"></span>评价日期：</th>
                    <td width="33%">
                        <li class="nowtime">
       <span id="nowTime">
         <label class="data"></label>
         <label class="week"></label>
         <label class="time"></label>
</span>
                        </li>
                    </td>
                </tr>
                <tr>
                    <input type="text" name="evaluation" id="evaluation"  style="width:800px;   height:100px;"/>
                </tr>
                <tr>
                    <input type="reset" class="btn" value="重置" />
                    <input type="button"  class="btn" value="提交" onclick="sub()"/>
                </tr>
            </table>
            </form>
        </div>
    </div>

</div>
<script >

    function sub() {
        $.ajax({
            cache: true,
            type: "POST",
            url:"/usermanage/evaluation",
            data:$('#form_eva').serialize(),// 你的formid
            async: false,
            error: function(request) {
                alert("Connection error:"+request.error);
            },
            success: function(data) {
                if(data == "0"){
                    parent.select();
                    parent.art.dialog({id:'user_edit_window'}).close() ;
                }else {
                    alert("用户信息修改失败！");
                }
            }
        });
    }
</script>
</body>
</html>

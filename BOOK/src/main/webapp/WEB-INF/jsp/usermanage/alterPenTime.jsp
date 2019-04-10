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
    <title>黑名单</title>
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
            <h2>修改黑名单默认惩罚时间</h2>
            <a href="javascript:;" class="arrow up"></a></div>
        <div class="panel-body panel-noborder">
            <div class="write-box">
                <form action="" method="post" class="form-libs" name="form-libs" id="form-libs">
                <table class="form-table" width="100%">
                        <tr><th width="30%"><span class="ft-need"></span></th>
                            <td width="33%"></td>
                        </tr>
                        <tr><th width="30%">当前黑名单惩罚时间：</th>
                            <td width="33%"><input id="penTime" type="text" value="${PenTime}">天</td>
                        </tr>
                    <tr><th width="30%"><span class="ft-need"></span></th>
                        <td width="33%"></td>
                    </tr>
                        <tr><th width="30%"><input  type="button" class="btn" value="确定"  onclick="sub();" style="margin-left: 45px"></th>
                            <td width="33%"><input  type="button" class="btn" value="取消"  onclick="myClose();" ></td>
                        </tr>
                </table>
                </form>
            </div>
        </div>
    </div>
    <script type="text/javascript">
        function myClose() {
            parent.art.dialog({id:'alterPenTime'}).close() ;
        }
        //ajax提交表单
        function sub() {
            var penTime = $("#penTime").val();
            $.ajax({
                cache: true,
                type: "POST",
                url:"/usermanage/alterPenTime?penTime="+penTime,
                error: function(request) {
                    alert("Connection error:"+request.error);
                },
                success: function(status) {
                    if(status){
                        alert("修改成功！");
                        parent.refresh();
                        parent.art.dialog({id:'alterPenTime'}).close() ;
                    }else {
                        alert("修改失败！");
                    }
                }
            });
        }
    </script>
</body>
</html>

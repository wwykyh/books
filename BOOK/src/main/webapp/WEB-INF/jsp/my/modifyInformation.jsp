
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>修改信息</title>
    <link rel="stylesheet" href="js/libs/jquery/jquery.datepick.css" type="text/css">
    <script type="text/javascript" src="js/libs/jquery/jquery.datepick.js"></script>
    <script type="text/javascript" src="js/libs/jquery/jquery.datepick-zh-CN.js"></script>
</head>
<body>
<div class="panel-body panel-noborder">
    <div class="write-box">
        <form role="form" method="post" class="form-modifypwd" >
            <input id="userid" value="${user.userId}" type="hidden">
            <table class="form-table" width="100%">
                <tr>
                    <th width="17%"><span class="ft-need">*</span>姓名：</th>
                    <td width="33%"><input id="xm" name="user.xm" data-validate="required number" type="text" class="input-text " ></td>
                </tr>
                <tr>
                    <th width="17%"><span class="ft-need">*</span>联系方式：</th>
                    <td><input id="lxfs" name="user.lxfs" data-validate="required" type="text" class="input-text "></td>
                </tr>
                <tr>
                    <th width="17%"><span class="ft-need">*</span>地址：</th>
                    <td><input id="dz" name="user.dz" data-validate="required" type="text" class="input-text "></td>
                </tr>
                <tr>
                    <th width="17%"><span class="ft-need">*</span>部门：</th>
                    <td width="33%"><input id="bm" name="user.bm" data-validate="required number" type="text" class="input-text " ></td>
                </tr>
                <tr>
                    <th width="17%"><span class="ft-need">*</span>个人说明：</th>
                    <td width="33%"><input id="grsm" name="user.grsm" data-validate="required number" type="text" class="input-text " ></td>
                </tr>
                <tr>
                    <th><input type="reset" class="btn" value="重置"></th>
                    <td><input type="button" class="btn" value="提交" onclick="updata()" ></td>
                    <%--<td><button id="btnSubmitOne" class="btn">提交</button></td>--%>
                </tr>
            </table>
        </form>
    </div>
</div>
<script type="text/javascript">

    function updata(){
        var xm=$("#xm").val();
        var lxfs=$("#lxfs").val();
        var dz=$("#dz").val();
        var bm=$("#bm").val();
        var grsm=$("#grsm").val();
        var userId=${user.userId};
        // alert(xm);
        // alert(lxfs);
        // alert(dz);
        $.ajax({
                url: "/modifyInformation/doModifyInfortmation",
                data: {"xm":xm,"lxfs":lxfs,"dz":dz,"bm":bm,"grsm":grsm,"userId":userId},
                success: function (data) {
                   alert(data);
                }
            });



    }


</script>
</body>

</html>

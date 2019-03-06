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
    <title>用户信息修改</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common/iconfont/iconfont.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common/layout.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dvpt/css/libs.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/demo/style.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/theme/blue.css" id="style" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/dvpt/config.js"></script>
    <!-- 改造的脚本 -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/extend.js"></script>
    <!-- 共有的控件 -->
    <script data-main="main" src="${pageContext.request.contextPath}/dvpt/require.min.2.1.11.js"></script>
</head>
<body>
    <div class="panel">
        <div class="panel-header">
            <h2>用户信息修改</h2>
            <a href="javascript:;" class="arrow up"></a></div>
        <div class="panel-body panel-noborder">
            <div class="write-box">
                <form action="/usermanage/userEdit" method="post" class="form-libs" name="form-libs" id="form-libs">
                    <input type="hidden" id="userId" name="userId" value="${userInfo.userId}">
                <table class="form-table" width="100%">
                    <tr>
                        <th width="17%"><span class="ft-need">*</span>用户姓名：</th>
                        <td width="33%"><input id="userName" name="userName" value="${userInfo.xm}" data-validate="required" type="text" class="input-text "></td>
                    </tr>
                    <tr>
                        <th width="17%"><span class="ft-need">*</span>密码：</th>
                        <td width="33%"><input id="userPwd" name="userPwd" value="${userInfo.pwd}" data-validate="required" type="text" class="input-text "></td>
                    </tr>
                    <tr>
                        <th width="17%"><span class="ft-need">*</span>部门：</th>
                        <td width="33%"><input id="userDepartment" name="userDepartment" value="${userInfo.bm}" data-validate="required email" type="text" class="input-text "></td>
                    </tr>
                    <tr>
                        <th width="17%"><span class="ft-need">*</span>Email：</th>
                        <td width="33%"><input id="userEmail" name="userEmail" value="${userInfo.email}" data-validate="required phone" type="text" class="input-text "></td>
                    </tr>
                    <tr>
                        <th width="17%"><span class="ft-need">*</span>联系电话：</th>
                        <td width="33%"><input id="userTel" name="userTel" value="${userInfo.lxfs}" data-validate="required phone" type="text" class="input-text "></td>
                    </tr>
                    <tr>
                        <th width="17%"><span class="ft-need"></span>地址：</th>
                        <td width="33%"><input id="userAddress" name="userAddress" value="${userInfo.dz}" data-validate="required phone" type="text" class="input-text "></td>
                    </tr>
                    <tr>
                        <th width="17%"><span class="ft-need"></span>是否黑名单：</th>
                        <td width="33%"><input id="isBlacklist" name="isBlacklist" value="${userInfo.ishmd}" data-validate="required phone" type="text" class="input-text "></td>
                    </tr>
                    <tr>
                        <th width="17%"><span class="ft-need"></span>个人说明：</th>
                        <td width="33%"><input id="userIntroduce" name="userIntroduce" value="${userInfo.grsm}" data-validate="required phone" type="text" class="input-text "></td>
                    </tr>
                    <tr>
                        <th width="17%"><span class="ft-need"></span>可借图书册数：</th>
                        <td width="33%"><input id="userBroNum" name="userBroNum" value="${userInfo.kjtscs}" data-validate="required phone" type="text" class="input-text "></td>
                    </tr>
                    <tr>
                        <th width="17%"><span class="ft-need"></span>次数：</th>
                        <td width="33%"><input id="userTimes" name="userTimes" value="${userInfo.cs}" data-validate="required phone" type="text" class="input-text "></td>
                    </tr>
                    <tr>
                        <th width="17%"><span class="ft-need"></span>是否为管理员：</th>
                        <td width="33%"><input id="isAdmin" name="isAdmin" value="${userInfo.isadmin}" data-validate="required phone" type="text" class="input-text "></td>
                    </tr>
                    <tr>
                        <th></th>
                        <td><input  type="button" class="btn" value="修改"  onclick="sub()"></td>
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
                url:"/usermanage/user_edit",
                data:$('#form-libs').serialize(),// 你的formid
                async: false,
                error: function(request) {
                    alert("Connection error:"+request.error);
                },
                success: function(data) {
                    if(data == "0"){
                        alert("用户信息修改成功！") ;
                        $("table input:lt(4)").val("") ;
                    }else {
                        alert("用户信息修改失败！");
                    }
                }
            });
        }
    </script>
</body>
</html>

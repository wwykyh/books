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
            <h2>用户信息修改</h2>
            <a href="javascript:;" class="arrow up"></a></div>
        <div class="panel-body panel-noborder">
            <div class="write-box">
                <form action="/usermanage/userEdit" method="post" class="form-libs" name="form-libs" id="form-libs">
                    <input type="hidden" id="userId" name="userId" value="${userInfo.userId}">
                <table class="form-table" width="100%">
                    <tr>
                        <th width="17%"><span class="ft-need">*</span>用户姓名：</th>
                        <td width="33%"><input id="xm" name="xm" value="${userInfo.xm}" data-validate="required" type="text" class="input-text "></td>
                    </tr>
                    <tr>
                        <th width="17%"><span class="ft-need">*</span>密码：</th>
                        <td width="33%">
                            <input id="pwd" name="pwd" value="**********" data-validate="required" type="text" disabled="disabled"class="input-text ">
                            <input id="rePwd" name="rePwd" type="button" class="btn" value="重置密码" >
                        </td>
                    </tr>
                    <tr>
                        <th width="17%"><span class="ft-need">*</span>部门：</th>
                        <td width="33%"><input id="bm" name="bm" value="${userInfo.bm}" data-validate="required" type="text" class="input-text "></td>
                    </tr>
                    <tr>
                        <th width="17%"><span class="ft-need">*</span>Email：</th>
                        <td width="33%"><input id="email" name="email" value="${userInfo.email}"  data-validate="required email" type="text" class="input-text "></td>
                    </tr>
                    <tr>
                        <th width="17%"><span class="ft-need">*</span>联系电话：</th>
                        <td width="33%"><input id="lxfs" name="lxfs" value="${userInfo.lxfs}" data-validate="required phone" type="text" class="input-text "></td>
                    </tr>
                    <tr>
                        <th width="17%"><span class="ft-need"></span>地址：</th>
                        <td width="33%"><input id="dz" name="dz" value="${userInfo.dz}" data-validate="required" type="text" class="input-text "></td>
                    </tr>
                    <tr>
                        <th width="17%"><span class="ft-need"></span>是否黑名单：</th>
                        <td width="33%"><select id="ishmd" name="ishmd" value="${userInfo.ishmd}" style="width: 200px">
                            <option value="1" selected>是</option>
                            <option value="0" >否</option>
                        </select></td>
                    </tr>
                    <%--<tr>--%>
                        <%--<th width="17%"><span class="ft-need"></span>可借图书册数：</th>--%>
                        <%--<td width="33%"><input id="kjtscs" name="kjtscs" value="${userInfo.kjtscs}" data-validate="required" type="text" class="input-text "></td>--%>
                    <%--</tr>--%>
                    <%--<tr>--%>
                        <%--<th width="17%"><span class="ft-need"></span>可借时长（天）：</th>--%>
                        <%--<td width="33%"><input id="cs" name="cs" value="${userInfo.kjsc}" data-validate="required" type="text" class="input-text "></td>--%>
                    <%--</tr>--%>
                    <tr>
                        <th width="17%"><span class="ft-need"></span>是否为管理员：</th>
                        <td width="33%"><select id="isadmin" name="isadmin" value="${userInfo.isadmin}" style="width: 200px">
                            <option value="1" selected>是</option>
                            <option value="0" >否</option>
                        </select></td>
                    </tr>
                    <tr>
                        <th width="17%"><span class="ft-need"></span>个人说明：</th>
                        <td width="33%"><textarea id="grsm" name="grsm" style="width: 200px;height:100px">${userInfo.grsm}</textarea></td>
                    </tr>
                    <tr>
                        <th></th>
                        <td><input  type="button" class="btn" value="修改"  id="sub1" ><input  type="button" class="btn" value="重置"  onclick="reset()"></td>
                    </tr>
                </table>
                </form>
            </div>
        </div>
    </div>
    <script type="text/javascript">
            //快捷还书方法
            $("#sub1").click(function () {
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
                            parent.select();
                            parent.art.dialog({id:'user_edit_window'}).close() ;
                        }else {
                            alert("用户信息修改失败！");
                        }
                    }
                });
            })

//重置密码
            $("#rePwd").click(function () {
                var userId = "${userInfo.userId}";
                $.ajax({
                    cache: true,
                    type: "POST",
                    url:"/usermanage/reset_pwd?userId="+userId,
                    async: false,
                    error: function(request) {
                        alert("Connection error:"+request.error);
                    },
                    success: function(data) {
                        if(data){
                            alert("密码重置成功，初始密码为123456！");
                            parent.select();
                            parent.art.dialog({id:'user_edit_window'}).close() ;
                        }else {
                            alert("密码重置失败！");
                        }
                    }
                });
            })



        $(document).ready(function (){
            var isadmin = "${userInfo.isadmin}";
            var ishmd  = "${userInfo.ishmd}";
            if (isadmin==0){
                $("#isadmin").val('0');
            }
            if (ishmd==0){
                $("#ishmd").val('0');
            }
        });


    </script>
</body>
</html>

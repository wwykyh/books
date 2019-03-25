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
    <title>赔偿信息添加</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common/iconfont/iconfont.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common/layout.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dvpt/css/libs.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/demo/style.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/theme/blue.css" id="style" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/dvpt/config.js"></script>
    <!-- 日期插件，使用jquery -->
    <link rel="stylesheet" href="js/libs/jquery/jquery.datepick.css" type="text/css">
    <script type="text/javascript" src="js/libs/jquery/jquery.datepick.js"></script>
    <script type="text/javascript" src="js/libs/jquery/jquery.datepick-zh-CN.js"></script>
    <!-- 改造的脚本 -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/extend.js"></script>
    <!-- 共有的控件 -->
    <script data-main="main" src="${pageContext.request.contextPath}/dvpt/require.min.2.1.11.js"></script>
</head>
<body>
    <div class="panel">
        <div class="panel-header">
            <h2>赔偿信息添加</h2>
            <a href="javascript:;" class="arrow up"></a></div>
        <div class="panel-body panel-noborder">
            <div class="write-box">
                <form action="typeAdd" method="post" class="form-libs" name="form-libs" id="form-type">
                <table class="form-table" width="100%">
                    <tr>
                        <th width="17%"><span class="ft-need">*</span>用户ID：</th>
                        <td width="33%"><input id="userId" name="userId" data-validate="required" type="text" class="input-text "></td>
                    </tr>
                    <tr>
                        <th width="17%"><span class="ft-need">*</span>图书ISBN：</th>
                        <td width="33%"><input id="isbn" name="isbn" data-validate="required" type="text" class="input-text "></td>
                    </tr>
                    <tr>
                        <th width="17%"><span class="ft-need">*</span>损毁程度：</th>
                        <td width="33%"><select id="sh" name="sh" style="width: 200px">
                            <option value="1" selected>轻度</option>
                            <option value="2" >中度</option>
                            <option value="3" >重度</option>
                        </select></td>
                    </tr>
                    <tr>
                        <th width="17%"><span class="ft-need">*</span>是否已赔偿：</th>
                        <td width="33%"><select id="ispc" name="ispc" style="width: 200px">
                            <option value="0" selected>否</option>
                            <option value="1" >是</option>
                        </select></td>
                    </tr>
                    <tr>
                        <th width="17%"><span class="ft-need">*</span>是否已被黑名单计数：</th>
                        <td width="33%"><select id="iscount" name="iscount" style="width: 200px">
                            <option value="0" selected>否</option>
                            <option value="1" >是</option>
                        </select></td>
                    </tr>
                    <tr>
                    <tr>
                        <th width="17%"><span class="ft-need">*</span>日期：</th>
                        <td width="33%">  <input id="pcdate" name="pcdate" type="text" data-validate="required"  class="input-text" /></td>
                    </tr>
                    <tr>
                        <th width="17%"><span class="ft-need"></span>备注：</th>
                        <td width="33%"><textarea id="pc" name="pc" style="width: 200px;height:100px"></textarea></td>
                    </tr>
                    <tr>
                        <th></th>
                        <td><input type="button" class="btn" value="提交" onclick="sub()" ><input type="reset" class="btn" value="重置"></td>
                    </tr>
                </table>
                </form>
            </div>
        </div>
    </div>
    <script type="text/javascript">
        $(document).ready(function(){
            //使用class属性处理  'yy-mm-dd' 设置格式"yy-mm-dd"
            var date = new Date();
            alert(date.toString());
            $('#pcdate').valueOf(date.toString());
            $('#pcdate').datepick({dateFormat: 'yy-mm-dd'});
        });


        //ajax提交表单
        function sub() {
            $.ajax({
                cache: true,
                type: "POST",
                url:"pcAdd",
                data:$('#form-type').serialize(),// 你的formid
                async: false,
                error: function(request) {
                    alert("Connection error:"+request.error);
                },
                success: function(data) {
                    if(data == "0"){
                        parent.select() ;
                        parent.art.dialog({id:'pc_add_window'}).close() ;
                    }else {
                        alert("类型添加失败！");
                    }
                }
            });
        }

    </script>
</body>
</html>

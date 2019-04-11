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
    <meta http-equiv="Content-Type" content="multipart/form-data; charset=utf-8" />
    <title>图书添加</title>
    <!-- 日期插件，使用jquery -->
    <link rel="stylesheet" href="js/libs/jquery/jquery.datepick.css" type="text/css">
    <script type="text/javascript" src="js/libs/jquery/jquery.datepick.js"></script>
    <script type="text/javascript" src="js/libs/jquery/jquery.datepick-zh-CN.js"></script>
</head>
<body>
<div class="panel">
    <div class="panel-header">
        <h2>图书添加</h2>
        <a href="javascript:;" class="arrow up"></a></div>
    <div class="panel-body panel-noborder">
        <div class="write-box">
            <form action="bookAdd" method="post" class="form-libs" onsubmit="return sub();" name="form-libs"
                  id="form-libs"  enctype="multipart/form-data">
                <table class="form-table" width="100%">
                    <tr>
                        <th width="17%"><span class="ft-need">*</span>图书ISBN：</th>
                        <td width="33%"><input id="isbn" name="book.isbn" data-validate="required number" type="text"
                                               class="input-text " onBlur="getBookInfo();"></td>
                    </tr>
                    <tr>
                        <th width="17%"><span class="ft-need">*</span>图书名称：</th>
                        <td><input id="sm" name="book.sm" data-validate="required" type="text" class="input-text "></td>
                    </tr>
                    <tr>
                        <th><span class="ft-need">*</span>出版社名称：</th>
                        <td><select id="cbsmc" name="book.cbsmc" data-validate="required" class="select">
                            <option>==请选择==</option>
                            <c:forEach items="${publishList}" var="publish">
                                <option value="${publish.pubName}">${publish.pubName}</option>
                            </c:forEach>
                        </select></td>
                    </tr>
                    <tr>
                        <th><span class="ft-need">*</span>出版日期：</th>
                        <td><input id="cbrq" name="book.cbrq" vdata-validate="required" type="text" class="input-text"/>
                        </td>
                    </tr>
                    <tr>
                        <th><span class="ft-need">*</span>作者：</th>
                        <td><input id="zz" name="book.zz" data-validate="required" type="text" class="input-text "></td>
                    </tr>
                    <tr>
                        <th><span class="ft-need">*</span>图书类型：</th>
                        <td>
                            <select id="typeId" name="book.typeId" data-validate="required" class="select">
                                <option selected="selected">==请选择==</option>
                                <c:forEach items="${typeList}" var="type">
                                    <option value="${type.typeId}">${type.lxmc}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th><span class="ft-need">*</span>图片：</th>
                        <td><input id="pic" name="pic" data-validate="required" type="file"
                                   accept="image/*"></td>

                        <%--<th><span class="ft-need">*</span>所属种类：</th>--%>
                        <td><input  name="book.tsdl"  type="hidden"
                                   value="纸质"></td>
                        <%--<td>
<select id="tsdl" name="book.tsdl" data-validate="required" class="select">
    <option value="纸质书" selected="selected">纸质书</option>
    <option value="电子图书">电子图书</option>
    <option value="CSDN">CSDN</option>
</select>
</td>--%>
                    </tr>
                    <tr>
                        <th><span class="ft-need">*</span>图书提供者编号：</th>
                        <td><input id="userId" name="store.userId" data-validate="required number" type="text"
                                   class="input-text" value="6"></td>
                    </tr>
                    <tr>
                        <th><span class="ft-need">*</span>入库数量：</th>
                        <td><input id="num" name="num" data-validate="required number" type="number"
                                   class="input-text" value="1"></td>
                        <%--<td><input id="tsdl" name="book.tsdl" data-validate="required number" type="hidden"
                                   class="input-text" value="纸质"></td>--%>
                    </tr>
                    <tr>
                        <th><span class="ft-need">*</span>损毁程度：</th>
                        <td>
                            <select id="sh" name="store.sh" data-validate="required" class="select">
                                <option value="0" selected="selected">无损毁</option>
                                <option value="1">轻微损毁</option>
                                <option value="2">中度损毁</option>
                                <option value="3">重度损毁</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th><span class="ft-need">*</span>书库位置：</th>
                        <td>
                            <select id="wz" name="store.wz" data-validate="required" class="select">
                                <option value="1" selected="selected">1号书架</option>
                                <option value="2">2号书架</option>
                                <option value="3">3号书架</option>
                                <option value="4">4号书架</option>
                            </select>
                        </td>
                    </tr>

                    <tr>
                        <th><span class="ft-need">*</span>简介：</th>
                        <td><input id="jj" name="book.jj" data-validate="required" type="text" class="input-text "></td>

                    <%--  <textarea name="descript" id="descript" cols="60" rows="10"></textarea>--%>
                    </tr>
                    <tr>
                        <th><input type="reset" class="btn" value="重置"></th>
                        <td><input type="button" class="btn" value="提交" onclick="sub()"></td>
                        <%--<td><button id="btnSubmitOne" class="btn">提交</button></td>--%>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>
<script type="text/javascript">

    $(document).ready(function () {
        //使用class属性处理  'yy-mm-dd' 设置格式"yy-mm-dd"
        $('#cbrq').datepick({dateFormat: 'yy-mm-dd'});
    });

    //ajax提交表单
    function sub() {
        var formData =new FormData($("#form-libs")[0]);
        $.ajax({
            cache: true,
            contentType: false,
            processData: false,
            type: "POST",
            url: "bookAdd",
            data: formData,// 你的form id
            async: false,
            error: function (request) {
                alert("Connection error:" + request.error);
            },
            success: function (data) {
                if (data == "0") {
                    alert("图书添加成功！");
                } else {
                    alert("图书添加失败！");
                }
            }
        });
    }

    //通过图书ISBN获得图书信息
    function getBookInfo() {
        var isbn = $("#isbn").val();
        $.ajax({
            url: "${pageContext.request.contextPath}/getBookInfo?isbn=" + isbn, success: function (data) {
                //isbn   sm  cbsmc  cbrq  zz   typeId   tsdl
                $("#sm").val(data.sm);
                $("#cbsmc").find("option[value=" + data.cbsmc + "]").attr("selected", true);
                // var times = "" ;
                // if (data.cbrq != null){
                //     var d = new Date(data.cbrq);
                //     times=d.getFullYear() + '-' + (d.getMonth() + 1 < 10 ? "0" + (d.getMonth() + 1) : d.getMonth() + 1) + '-' + (d.getDate() < 10 ? "0" + d.getDate() : d.getDate()) ;
                // }
                $("#cbrq").val(data.cbrq);
                $("#jj").val(data.jj);
                $("#zz").val(data.zz);
                $("#typeId").find("option[value=" + data.typeId + "]").attr("selected", true);
                // $("#tsdl").find("option[value=" + data.tsdl + "]").attr("selected", true);
                $("#tsdl").val(data.tsdl);


            }
        });
    }

    $(function () {
        $("#userId").keyup(function () {
            console.log($("#userId").val());
        });
    });

    //表单校验
    requirejs(['jquery', 'jquery.form'], function ($) {
        $(function () {
            $("#form-libs").formPrefer({
                showTipsType: 'lineTips', // 报错信息提示方式 lineTips layerTips otherTips
                submitBtn: '#btnSubmitOne',   // href="/bookAdd"
                success: function () {
                    alert("验证通过");
                    $("form[name='form-libs']").submit();
                }
            });
        });
    });
</script>
</body>
</html>

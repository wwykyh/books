<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/1/24
  Time: 16:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>电子书上传</title>
    <script type="text/javascript" src="../js/ebook/ebook.js"></script>
</head>
<body>
<div class="panel">
    <div class="panel-header">
        <h2>电子书上传</h2>
        <a href="javascript:;" class="arrow up"></a></div>
    <form id="uploadForm" method="post" enctype="multipart/form-data">
        <div class="panel-body panel-noborder">
            <div class="write-box">
                <table class="form-table" width="100%">
                    <tr>
                        <th width="17%"><span class="ft-need">*</span>电子书管理：</th>
                        <td width="33%">
                            <button id="attachmentAddBtn" type="button" class="btn btn-default">Add File</button>&nbsp;&nbsp;
                            <button id="attachmentDeleteBtn" type="button" class="btn btn-default">Delete File</button>
                            &nbsp;&nbsp;&nbsp;&nbsp;<span class="ft-need">可上传多个文件(至少一个)</span>
                        </td>
                    </tr>
                    <tr>
                        <th width="17%"><span class="ft-need">*</span>电子书：</th>
                        <td width="33%">
                            <div id="attachmentInputs" style="width: 200px;line-height: 5px;">

                            </div>
                        </td>
                    </tr>
                    <tr>
                        <th width="17%"><span class="ft-need">*</span>文件格式：</th>
                        <td width="33%">
                            <span class="ft-need">请上传pdf、txt、html、chm、epub等文件</span>
                        </td>
                    </tr>
                    <%--<tr>
                        <th width="17%"><span class="ft-need">*</span>图书名称：</th>
                        <td width="33%">
                            <input id="bookName" name="eBookXm" type="text" class="input-text " onblur="confirmName()"
                                   placeholder="多个文件时，如：测试文件1.txt;测试文件2.txt;" style="font-size: 12px;width: 300px">
                            <span class="ft-need">有多个文件时，以分号" ; "区别</span>
                        </td>
                    </tr>--%>
                    <tr>
                        <th width="17%"><span class="ft-need">*</span>电子书类型：</th>
                        <td width="33%">
                            <select class="select" name="typeId" id="type">
                                <option value="">==请选择==</option>
                                <c:forEach items="${types}" var="type">
                                    <option value="${type.typeId}">${type.lxmc}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th width="17%"><span class="ft-need">*</span>电子书种类：</th>
                        <td width="33%">
                            <select class="select" name="tszl" id="tszl">
                                <option selected="selected" value="电子书">电子书</option>
                                <option value="CSDN">CSDN</option>
                                <option value="txt">txt</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th width="17%"><span class="ft-need">*</span>描述：</th>
                        <td width="33%">
                            <input id="ms" type="text" class="input-text" name="ms" style="width: 300px;"
                                   placeholder="请简要描述书籍，有多个文件时，以分号 ; 结束  ！">
                            <span class="ft-need">有多个文件时，以分号" ; "结束</span>
                        </td>
                    </tr>
                    <tr>
                        <th width="17%"></th>
                        <td width="33%">
                            <input type="reset" class="btn" value="重置">
                            <input type="button" class="btn" value="提交" onclick="sub();">
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </form>
</div>
</body>
<script type="text/javascript">
    var n = -1;
    // add
    $("#attachmentAddBtn").click(function (even) {
        n++;
        //name值一样就可以
        $("#attachmentInputs").append("<input name=\"ebookFile\" type=\"file\" class=\"input-text\" onchange=\"judageEbook("+ n +");\"/>");
    });
    // delete
    $("#attachmentDeleteBtn").click(function (even) {
        var files = $("#attachmentInputs input[type='file']");
        files.each(function (index, element) {
            //从最下面开始删除，至少保留一个
            if (!(index === 0) && index === (files.length - 1)) {
                $(element).next().remove();
                $(element).remove();
                n--;
            }
        });
    });

    // ajax提交表单
    function sub() {
        // $("#uploadForm").submit();
        var formData = new FormData($("#uploadForm")[0]);
        // 获取key值
        // var eBookXm = formData.get("eBookXm");
        var ebookFile = formData.get("ebookFile");
        var typeId = formData.get("typeId");
        var tszl = formData.get("tszl");
        var ms = formData.get("ms");
        if (ebookFile === null || typeId === "" || tszl === "" ||ms === "") {
            alert("请填写上传文件参数！");
        } else {
            $.ajax({
                type: "post",
                data: formData,
                url: "eBookFile/bookUpload",
                async: false,
                cache: false,
                contentType: false, //必须
                processData: false, //必须
                success: function (data) {
                    if (data === "success") {
                        alert("文件上传成功！");
                        $("input[type=reset]").trigger("click");
                    } else {
                        alert("文件上传失败！");
                    }
                }
            });
        }
    }

</script>
</html>

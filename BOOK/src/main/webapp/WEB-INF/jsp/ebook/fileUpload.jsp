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
    <form  id="uploadForm" method="post" enctype="multipart/form-data">
        <div class="panel-body panel-noborder">
        <div class="write-box">
            <table class="form-table" width="100%">
                <tr>
                    <th width="17%"><span class="ft-need">*</span>图书编号：</th>
                    <td width="33%">
                        <input id="ebookId" name="eBookId"type="text" class="input-text " value="${ebookId}">
                    </td>
                </tr>
                <tr>
                    <th><span class="ft-need">*</span>电子书：</th>
                    <td><input id="ebookFile" type="file"  class="input-text" name="ebookFile" onchange="judageEbook();">
                        <span class="ft-need">请上传pdf、txt、html、chm、epub等文件</span>
                    </td>
                </tr>
                <tr>
                    <th width="17%"><span class="ft-need">*</span>图书名称：</th>
                    <td><input id="bookName" name="eBookXm" type="text" class="input-text " onblur="confirmName()"
                               placeholder="根据上传文件自行获得，也可以自己设置书名" style="font-size: 10px;"></td>
                </tr>
                <tr>
                    <th><span class="ft-need">*</span>电子书类别：</th>
                    <td>
                        <select class="select" name="typeId" id="type">
                            <option value="">==请选择==</option>
                            <c:forEach items="${types}" var="type">
                                <option  value="${type.typeId}">${type.lxmc}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th><span class="ft-need">*</span>电子书类别：</th>
                    <td>
                        <select class="select" name="tszl" id="tszl">
                            <option selected="selected" value="电子书">电子书</option>
                            <option value="CSDN">CSDN</option>
                            <option value="txt">txt</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th><span class="ft-need"></span>描述：</th>
                    <td>
                        <input id="ms" type="text"  class="input-text" name="ms" placeholder="请简要描述下本书">
                    </td>
                </tr>
                <tr>
                    <th><input type="reset" class="btn" value="重置"></th>
                    <td><input type="button" class="btn" value="提交" onclick="sub();"></td>
                </tr>
            </table>
        </div>
    </div>
    </form>
</div>
</body>
<script type="text/javascript">
    // ajax提交表单
    function sub() {
        // $("#uploadForm").submit();
        var formData = new FormData($("#uploadForm")[0]);
        // 获取key值
        var eBookXm = formData.get("eBookXm");
        var ebookFile = formData.get("ebookFile");
        var typeId = formData.get("typeId");
        var tszl = formData.get("tszl");
        if(eBookXm === "" || ebookFile === null || typeId === "" || tszl === "" ){
            alert("请填写上传文件参数！");
        }else{
            $.ajax({
                type:"post",
                data:formData,
                url:"eBookFile/bookUpload",
                async : false,
                cache:false,
                contentType: false, //必须
                processData: false, //必须
                success: function(data) {
                    if(data === "success"){
                        alert("文件上传成功！") ;
                        $("input[type=reset]").trigger("click");
                    }else {
                        alert("文件上传失败！");
                    }
                }
            });
        }
    }

</script>
</html>

<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/3/27
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>电子书上传添加</title>
    <link rel="stylesheet" type="text/css" href="${path}/css/common/iconfont/iconfont.css"/>
    <link rel="stylesheet" type="text/css" href="${path}/css/common/layout.css"/>
    <link rel="stylesheet" type="text/css" href="${path}/css/demo/style.css"/>
    <link rel="stylesheet" type="text/css" href="${path}/css/theme/blue.css" id="style"/>
    <script type="text/javascript" src="${path}/dvpt/config.js"></script>
    <script type="text/javascript" src="${path}/dvpt/libs/artdialog/artDialog.source.js"></script>
    <script type="text/javascript" src="${path}/dvpt/libs/artdialog/iframeTools.source.js"></script>
    <!-- 改造的脚本 -->
    <script type="text/javascript" src="${path}/js/extend.js"></script>
    <!-- 共有的控件 -->
    <script data-main="main" src="${path}/dvpt/jquery.min.js"></script>
    <script type="text/javascript" src="${path}/js/ebook/ebook.js"></script>
</head>
<body>
<div style="margin-top: 10px;">
    <form id="fileForm">
        <table class="form-table" style="width: 450px;">
            <tr>
                <th style="width: 100px;"><span class="ft-need">*</span>电子书：</th>
                <td style="width: 280px;">
                    <input id="file" name="ebookFile" type="file" class="input-text" onchange="judageEbook();" data-validate="required"/>
                </td>
            </tr>
            <tr>
                <th><span class="ft-need">*</span>文件格式：</th>
                <td>
                    <span class="ft-need" style="font-size: 12px;">请上传pdf、txt、html、chm、epub等文件</span>
                </td>
            </tr>
            <tr>
                <th><span class="ft-need">*</span>电子书类型：</th>
                <td>
                    <select class="select" name="typeId" id="type" data-validate="required">
                        <option value="">==请选择==</option>
                        <c:forEach items="${types}" var="type">
                            <option value="${type.typeId}">${type.lxmc}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <th><span class="ft-need">*</span>电子书种类：</th>
                <td>
                    <select class="select" name="tszl" id="tszl" data-validate="required">
                        <option selected="selected" value="电子书">电子书</option>
                        <option value="CSDN">CSDN</option>
                        <option value="txt">txt</option>
                    </select>
                </td>
            </tr>
            <tr>
                <th><span class="ft-need">*</span>描述：</th>
                <td>
                    <input id="ms" type="text" class="input-text" name="ms" style="width: 210px;">
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>

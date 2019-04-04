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
    <script type="text/javascript" src="${path}/dvpt/libs/artdialog/artDialog.source.js"></script>
    <script type="text/javascript" src="${path}/dvpt/libs/artdialog/iframeTools.source.js"></script>
    <style type="text/css">
        #documentListContainer {
            width: 100%;
            height: 500px;
            margin: 0;
            padding: 0;
            list-style: none;
            border: none;
            outline: none;
            text-decoration: none;
        }

        .LV_Header {
            width: 100%;
            height: 25px;
            line-height: 27px;
            vertical-align: middle;
            background-color: #FAFAFA;
            border-bottom: solid 1px #dcdcdc;
            border-top: solid 1px #dcdcdc;
        }

        .LV_Header div {
            float: left;
            margin-top: 5px;
        }

        .file_list {
            width: 100%;
            height: 30px;
            line-height: 30px;
        }

        .file_list div {
            float: left;
            margin-top: 5px;
        }

        .uploadForm {
            width: 100%;
        }
    </style>
</head>
<body>
<div class="panel">
    <div class="panel-header">
        <h2>电子书上传</h2>
        <a href="javascript:;" class="arrow up"></a>
    </div>


    <div id="documentListContainer" style="clear:both;">

        <div class="actions" style="height: 27px;width: 900px;">
            <a id="all" onclick="checkAll();" href="javascript:;" class="btn"><i class="icon icon-batchreview"></i>全选</a>
            <a id="up" onclick="up();" href="javascript:;" class="btn"><i class="icon icon-upload"></i>批量上传</a>
            <a id="add" onclick="add();" href="javascript:;" class="btn btn-default"><i class="icon icon-add"></i>新建</a>
            <a id="del" onclick="del();" href="javascript:;" class="btn btn-danger"><i class="icon icon-delete"></i>删除</a>
        </div>

        <div class="LV_Header" style="clear:both;margin-top: 5px;">
            <div style="width: 10%; padding-left:4px;">
            </div>
            <div style="text-align: center; text-indent: 20px;width:20%;">
                文件
            </div>
            <div style="text-align: center;width:20%;">
                类型
            </div>
            <div style="width: 20%; text-align: center;">
                种类
            </div>
            <div style="width: 20%; text-align: center;">
                描述
            </div>
        </div>
    </div>
</div>
</body>
</html>

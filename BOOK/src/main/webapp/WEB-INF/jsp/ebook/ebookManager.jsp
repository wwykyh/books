<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/1/24
  Time: 22:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>电子书浏览</title>
    <script type="text/javascript" src="../js/ebook/ebook.js"></script>
</head>
<body>
<div class="panel">
    <div class="panel-header">
        <h2>电子书浏览</h2>
        <a href="javascript:;" class="arrow up"></a>
    </div>

    <form id="downForm" method="post">
        <div class="main-cont">
            <div class="tab tab-default">
                <div class="tab-contbox">
                    <div class="search-box">
                        <table class="search-table">
                            <tr>
                                <th></th>
                                <td>
                                    <input type="text" id="eBookXm" name="eBookXm" placeholder="书名" class="input-text"/>
                                </td>
                                <td>
                                    电子书类型：
                                    <select class="select" name="typeId" id="typeId">
                                        <option value="">==请选择==</option>
                                        <c:forEach items="${types}" var="type">
                                            <option  value="${type.typeId}">${type.lxmc}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td colspan="2"><a href="javascript:;" class="btn" onclick="submit();"><span>
                                    <i class="icon icon-search"></i>查询</span></a>
                                </td>
                            </tr>
                        </table>
                    </div>

                    <!--数据显示-->
                    <div id="comment"></div>
                </div>
            </div>
        </div>
    </form>
</div>
</body>
<script type="text/javascript">
    function downLoadFile(node) {
        // 获取到书名  (链接的父节点的父节点是tr)
        var tr1 = node.parentNode.parentNode;
        /*alert(tr1.rowIndex);
        alert(tr1.cells[1].innerText);
        alert(tr1.cells[1]);*/
        var bookName = tr1.cells[1].innerText;
        alert(bookName);
        // 获取当前行隐藏输入框中的值 服务器端的路径
        var ftpPath = $(node).parents("tr").find(".ftp").val();
        window.location.href = '${pageContext.request.contextPath}/eBookFile/bookDownload?bookName=' + bookName + "&ftpPath=" + ftpPath;
    }

    var eBookXm;
    var typeId;
    function submit() {
        eBookXm = $("#eBookXm").val();
        typeId = $("#typeId").val();
        select();
    }

    $(function () {
        select();   // 界面第一次加载时调出数据
    });

    function select(){
        requirejs(['jquery', 'ligerGrid', 'artdialog'], function ($) {
            $("#comment").ligerGrid({
                columns: [{
                    display: '图书编号',
                    name: 'eBookId',
                    width: 240,
                    frozen: true
                }, {
                    display: '书名',
                    name: 'eBookXm',
                    width: 200
                }, {
                    display: '类型',
                    name: 'lxmc'
                }, {
                    display: '上传时间',
                    name: 'scsj'
                }, {
                    display: '下载时间',
                    name: 'xzsj'
                }, {
                    display: '上传用户',
                    name: 'xm'
                }, {
                    display: '描述',
                    name: 'ms'
                }, {
                    display: '操作',
                    isAllowHide: false,
                    render: function (row) {
                        if (row.eBookId != null && row.eBookId !== "") {
                            var id = row.eBookId;
                            var html = "<a href='/eBookFile/bookDownload?eBookId="+id+"'>下载</a>&nbsp;&nbsp;&nbsp;";
                            html += "<a href='#'>预览</a>";
                            return html;
                        } else return "";
                    }
                }
                ],
                method: 'get',
                url: '${path}/eBookFile/ebookPage',
                dataType: 'server',
                dataAction: 'server',
                pageSize: 2,
                width: '100%',
                parms: [{name: "eBookXm", value: eBookXm},{name:"typeId",value:typeId}],
                checkbox: false,
                rownumbers: true,
                fixedCellHeight: false,
                iShowScroll: false
            });
        });
    }
</script>
</html>

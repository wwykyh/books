<%--
  Created by IntelliJ IDEA.
  User: liulei
  Date: 2019/1/23
  Time: 15:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>资产分析</title>
</head>
<body>
    <div class="panel">
        <div class="panel-header">
            <h2>资产分析</h2>
            <a href="javascript:;" class="arrow up"></a>
        </div>

        <div class="main-cont">
            <div class="tab tab-default">
                <div class="tab-contbox">
                    <div class="search-box">
                        <table class="search-table">
                            <tr>
                                <%--<td>状态：<select class="select">--%>
                                    <%--<option value="0">在库</option>--%>
                                    <%--<option value="1">借出</option>--%>
                                <%--</select></td>--%>
                                <%--<td>--%>
                                    <%--<input id="dim" type="text" placeholder="书名、作者" class="input-text" />--%>
                                <%--</td>--%>
                                <%--<td ><a href="javascript:;" class="btn"><span id="btnLoad"><i class="icon icon-search"></i>查询</span></a></td>--%>
                                <td ><a href="javascript:;" class="btn"><span id="btnExport"><i class="icon icon-export"></i>导出</span></a></td>

                            </tr>
                        </table>
                    </div>

                    <div id="booksInfo"></div>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript">
        var dim ;
        requirejs(['jquery', 'ligerGrid','dg.datePicker', 'artdialog'], function($) {
            $(function () {
                select() ;
            }) ;
            function select() {
                $("#booksInfo").ligerGrid({
                    columns: [{
                        display: 'ISBN',
                        name: 'isbn',
                        width: 120,
                        frozen: true
                    }, {
                        display: '书名',
                        name: 'sm',
                        width: 200,
                        frozen: true
                    }, {
                        display: '出版社名称',
                        name: 'cbsmc',
                        width: 120,
                        frozen: true
                    }, {
                        display: '出版日期',
                        name: 'cbrq',
                        width: 100,
                        frozen: true
                    }, {
                        display: '作者',
                        name: 'zz',
                        width: 160,
                        frozen: true
                    }, {
                        display: '类型',
                        name: 'lxmc',
                        width: 70,
                        frozen: true
                    }, {
                        display: '所属类型',
                        name: 'tsdl',
                        width: 80,
                        frozen: true
                    }, {
                        display: '图书提供者',
                        name: 'uname'
                    }, {
                        display: '位置',
                        name: 'wz'
                    }, {
                        display: '损毁程度',
                        name: 'sh'
                    }, {
                        display: '入库时间',
                        name: 'rksj'
                    }, {
                        display: '图书状态',
                        name: 'status'
                    }],
                    url: '/bookPage_manager',
                    method:'get',
                    dataType: 'server',
                    dataAction: 'server',
                    pageSize: 10,
                    width: '100%',
                    checkbox: false,
                    rownumbers: false,
                    parms:[{name:"dim",value:dim}],
                    fixedCellHeight: false,
                    iShowScroll: false,
                    allowAdjustColWidth: true
                }) ;
            };

            $("#btnLoad").click(function(){
                dim = $("#dim").val();
                select() ;
            }) ;

            $("#btnExport").click(function(){
                location.href = "${pageContext.request.contextPath}/book_export" ;
               // $.ajax({url:"${pageContext.request.contextPath}/book_export" });
            }) ;

        });


    </script>
</body>
</html>

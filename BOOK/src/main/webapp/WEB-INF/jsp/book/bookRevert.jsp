<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/2/18
  Time: 9:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>归还审核</title>
</head>
<body>
<div class="panel">
    <div class="panel-header">
        <h2>归还审核</h2>
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
                                    类型：
                                    <select class="select" name="typeId" id="typeId">
                                        <option value="">==请选择==</option>
                                        <c:forEach items="${types}" var="type">
                                            <option value="${type.typeId}">${type.lxmc}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>
                                    <input type="text" id="eBookXm" name="eBookXm" placeholder="书名" class="input-text"/>
                                </td>
                                <%--<td>
                                    审核状态：
                                    <select class="select" name="jyzt" id="jyzt">
                                        <option value="">==请选择==</option>
                                        <option value="0">待审核</option>
                                        <option value="1">续借</option>
                                        <option value="2">已审核</option>
                                    </select>
                                </td>--%>
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
    var typeId = "";
    var eBookXm = "";

    function submit() {
        typeId = $("#typeId").val();
        eBookXm = $("#eBookXm").val();
        bookRevertInfo();
    }

    requirejs(['jquery', 'ligerGrid', 'artdialog'], function ($) {
        $(function () {
            bookRevertInfo();   // 界面第一次加载时调出数据
        });
    });

    function bookRevertInfo() {
        $("#comment").ligerGrid({
            columns: [{
                display: '图书编号',
                name: 'isbn',
                width: 120,
                frozen: true
            }, {
                display: '图书名称',
                name: 'sm',
                width: 200
            }, {
                display: '借阅日期',
                name: 'jyrq',
                render: function (row) {
                    if (row.jyrq != null && row.jyrq !== "") {
                        return getColumnDate(row.jyrq);
                    }
                }
            }, {
                display: '计划归还日期',
                name: 'jhghrq',
                render: function (row) {
                    if (row.jhghrq != null && row.jhghrq !== "") {
                        return getColumnDate(row.jhghrq);
                    }
                }
            }, {
                display: '借阅人',
                name: 'xm'
            }, {
                display: '联系方式',
                name: 'lxfs'
            }, {
                display: '图书类型',
                name: 'lxmc'
            },{
                display: '操作',
                isAllowHide: false,
                render: function (row) {
                    var id = row.id;
                    if (row.jyzt != null && row.jyzt !== "" && row.jyzt === 0) {
                        var html = '<div style="margin-top: 10px;">' +
                            '<input name="check" type="button" class="layer-btn" id="check" value="同意" onclick="borCheck(' + id + ')"/>' +
                            '</div>';
                        return html;
                    }
                }
            }
            ],
            method: 'get',
            url: '${path}/revertCheck/',
            dataType: 'server',
            dataAction: 'server',
            pageSize: 5,
            width: '100%',
            parms: [{name: "search_typeId", value: typeId}, {name: "search_eBookXm", value: eBookXm}],
            checkbox: false,
            rownumbers: true,
            fixedCellHeight: false,
            iShowScroll: false
        });
    }

    function getColumnDate(data) {
        var date = new Date(data);
        var year = date.getFullYear();
        var month = date.getMonth() + 1;
        var day = date.getDate();
        return year + "-" + month + "-" + day;
    }

    function borCheck(id) {
        requirejs(['jquery', 'artdialog'], function ($) {
            $(function () {
                art.dialog.open('${path}/revertCheck/' + id + '', {
                    title: '归还审核',
                    width: 950,
                    height: 500,
                    ok: true,
                    okVal: "关闭",
                    id: "borrowRevertChild"
                });
            });
        });
    }
</script>
</html>

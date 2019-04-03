<%--
  Created by IntelliJ IDEA.
  User: liulei
  Date: 2019/1/23
  Time: 10:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>图书管理</title>
</head>
<body style="overflow: hidden;">
<div class="panel">
    <div class="panel-header">
        <h2>图书管理</h2>
        <a href="javascript:;" class="arrow up"></a>
    </div>

    <div class="main-cont">
        <div class="tab tab-default">
            <div class="tab-contbox">
                <div class="search-box">
                    <table class="search-table">
                        <tr>
                            <th></th>
                            <td>
                                <input id="dim" type="text" placeholder="书名、作者" class="input-text" />
                            </td>
                            <td colspan="2"><a href="javascript:;" class="btn"><span id="btnLoad"><i class="icon icon-search"></i>查询</span></a></td>
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
            dim = "" ;
            select() ;
        }) ;
        $("#btnLoad").click(function(){
            dim = $("#dim").val();
            select() ;
        }) ;
    });
    function select() {
        $("#booksInfo").ligerGrid({
            columns: [
                {
                    display: '书名',
                    name: 'sm'
                }, {
                    display: '作者',
                    name: 'zz'
                }, {
                    display: '类型',
                    name: 'lxmc'
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
                    display: '图书状态',
                    name: 'status'
                }, {
                    display: '操作',
                    isAllowHide: false,
                    render: function (row){
                        if (row.id != undefined && row.id != null && row.id != ""){
                            var html = '<a href="javascript:void(0);" onclick="onBookInfo(' + row.id + ')">查看详情</a>&nbsp;&nbsp;' ;
                            //html = html + '<a href="javascript:void(0);" onclick="onBookEdit(' + row.id + ')">库存管理</a>&nbsp;&nbsp;';
                            html = html + '<a href="javascript:void(0);" onclick="onBookDel(' + row.id + ')">出库</a>&nbsp;&nbsp';
                            html = html + '<a href="javascript:void(0);" onclick="onCommentManager(' + row.isbn+ ')">评论管理</a>&nbsp;&nbsp;';
                            return html;
                        }else return "" ;
                    }
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
    function onBookDel(id) {
        if (confirm("你是否要将这本书出库？")){
            $.ajax({url:"${pageContext.request.contextPath}/del_store?id="+id,
                success:function(data){
                    if (data == "0"){
                        select() ;
                    } else {
                        alert("出库失败！") ;
                    }
                }
            });
        }
    }

    function onBookEdit(id) {
        alert("编辑" + id) ;
    }
    function onBookInfo(id){
        //alert("详情" + id) ;
        art.dialog.open('book_info?id='+id, {
            title: '图书详情',
            width: 1200,
            height: 675,
            //ok: true,
           // okVal: "打印",
            cancel: true,
            cancelVal: "关闭"
        });
    }
    function onCommentManager(id) {
        art.dialog.open('getBookComment?id='+id, {
            title: '评论详情',
            width: 850,
            height: 575
        });
    }
</script>
<!--http://localhost:9090/bookPage_manager?dim=a&page=1&pagesize=10-->
</body>
</html>

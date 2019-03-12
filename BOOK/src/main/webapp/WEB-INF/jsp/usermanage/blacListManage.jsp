<%--
  Created by IntelliJ IDEA.
  User: wangnan
  Date: 2019/3/03
  Time: 10:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>黑名单</title>
</head>
<body style="overflow: hidden;">
<div class="panel">
    <div class="panel-header">
        <h2>黑名单</h2>
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
                                <input id="dim" type="text" placeholder="用户姓名" class="input-text" />
                            </td>
                            <td colspan="2"><a href="javascript:;" class="btn"><span id="btnLoad"><i class="icon icon-search"></i>查询</span></a></td>
                        </tr>
                    </table>
                </div>

                <div id="usersInfo"></div>
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
        $("#btnLoad").click(function(){
            dim = $("#dim").val();
            select() ;
        }) ;
    });
    function select() {
        $("#usersInfo").ligerGrid({
            columns: [{
                display: '用户ID',
                name: 'userId',
                width: 180,
                frozen: true
            }, {
                display: '姓名',
                name: 'xm',
                width: 180,
                frozen: true
            }, {
                display: '部门',
                name: 'bm',
                width: 350,
                frozen: true
            }, {
                display: '联系方式',
                name: 'lxfs',
                width: 350,
                frozen: true
            }, {
                display: '操作',
                isAllowHide: false,
                render: function (row){
                    if (row.userId != undefined && row.userId != null && row.userId != ""){
                        var   html =  '&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" onclick="onDelInfo(' + row.userId + ')">删除</a>';
                        return html;
                    }else return "" ;
                }
            }],
            url: '/usermanage/get_blacklist_data',
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
    function onDelInfo(userId) {
        if (confirm("你是否要把这个用户从黑名单中移除？")){
            $.ajax({url:"${pageContext.request.contextPath}/usermanage/del_black_user?id="+userId,
                success:function(data){
                    if (data){
                        select();
                    } else {
                        alert("移除失败！") ;
                    }
                }
            });
        }
    }

</script>
<!--http://localhost:9090/userPage_manager?dim=a&page=1&pagesize=10-->
</body>
</html>

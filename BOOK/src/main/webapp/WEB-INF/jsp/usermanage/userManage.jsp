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
    <title>用户管理</title>
</head>
<body style="overflow: hidden;">
<div class="panel">
    <div class="panel-header">
        <h2>用户管理 </h2>
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
                            <td>
                                是否为管理员：
                                <select class="select"  style="width:180px" name="dim2" id="dim2">
                                    <option value="1">是</option>
                                    <option value="0" selected>否</option>
                                    <option value="" selected>全部</option>
                                </select>
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
    var dim2 ;
    requirejs(['jquery', 'ligerGrid','dg.datePicker', 'artdialog'], function($) {
        $(function () {
            select() ;
        }) ;
        $("#btnLoad").click(function(){
            dim = $("#dim").val();
            dim2 = $("#dim2").val();
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
                        var html = '<a href="javascript:void(0);" onclick="onCheckInfo(' + row.userId + ')">查看资料</a>&nbsp;&nbsp;' ;
                        html = html + '<a href="javascript:void(0);" onclick="onEditInfo(' + row.userId + ')">修改资料</a>&nbsp;&nbsp;';
                        html = html + '<a href="javascript:void(0);" onclick="onDelInfo(' + row.userId + ')">删除</a>';
                        return html;
                    }else return "" ;
                }
            }],
            url: '/usermanage/usergetdata',
            method:'get',
            dataType: 'server',
            dataAction: 'server',
            pageSize: 10,
            width: '100%',
            checkbox: false,
            rownumbers: false,
            parms:[{name:"search_dim",value:dim},{name:"search_dim2",value:dim2}],
            fixedCellHeight: false,
            iShowScroll: false,
            allowAdjustColWidth: true
        }) ;
    };
    function onDelInfo(userId) {
        if (confirm("你是否要删除这个用户？")){
            $.ajax({url:"${pageContext.request.contextPath}/usermanage/del_user?id="+userId,
                success:function(data){
                    if (data){
                        select();
                    } else {
                        alert("删除失败！") ;
                    }
                }
            });
        }
    }

    function onEditInfo(userId) {
        art.dialog.open('/usermanage/showuser_edit?id='+userId, {
            title: '用户信息修改',
            id:'user_edit_window',
            width: 700,
            height: 650,
            cancel: true,
            cancelVal: "关闭"
        });
    }
    function onCheckInfo(userId){
        art.dialog.open('/usermanage/user_info?id='+userId, {
            title: '用户详情',
            width: 1000,
            height: 400,
            cancel: true,
            cancelVal: "关闭"
        });
    }

</script>
<!--http://localhost:9090/userPage_manager?dim=a&page=1&pagesize=10-->
</body>
</html>

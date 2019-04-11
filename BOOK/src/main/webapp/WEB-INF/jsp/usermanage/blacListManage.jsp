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
                            <th>黑名单记录：</th>
                        <td>
                            <select class="select"  style="width:180px" name="dim2" id="dim2">
                                <option value="0" selected>当前</option>
                                <option value="1" >历史</option>
                                <option value="" >全部</option>
                            </select>
                        </td>
                        <td colspan="2"><a href="javascript:;" class="btn" ><span id="btnLoad"><i class="icon icon-search"></i>查询</span></a></td>
                    </tr>
                        <tr>
                            <th></th>
                            <td>
                                当前黑名单默认惩罚时长：<span style="font-weight: bold;font-size: 16px">${PenTime}天</span>
                            </td>
                            <td colspan="2"><a href="javascript:;" class="btn"><span id="btnLoad1"><i class=""></i>修改</span></a></td>
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
        $(document).ready(function (){
                dim = $("#dim").val();
                dim2 = $("#dim2").val();
                select() ;
        });
        $(function () {
            select() ;
        }) ;
        $("#btnLoad").click(function(){
            dim = $("#dim").val();
            dim2 = $("#dim2").val();
            select() ;
        }) ;
        $("#btnLoad1").click(function(){
            onModifyPenTime();
        }) ;
    });
    function select() {
        $("#usersInfo").ligerGrid({
            columns: [{
                display: '用户ID',
                name: 'user.userId',
                width: 180,
                frozen: true
            }, {
                display: '姓名',
                name: 'user.xm',
                width: 180,
                frozen: true
            }, {
                display: '部门',
                name: 'user.bm',
                width: 180,
                frozen: true
            }, {
                display: '惩罚时间（天）',
                name: 'penTime',
                width: 300,
                frozen: true
            }, {
                display: '加入黑名单时间',
                name: 'startTime',
                width: 300,
                frozen: true
            }, {
                display: '移除黑名单时间',
                name: 'endTime',
                width: 300,
                frozen: true
            }, {
                display: '操作',
                isAllowHide: false,
                render: function (row){
                    if (row.userId != undefined && row.userId != null && row.userId != ""){
                        var   html =  '&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" onclick="onDelInfo(' + row.userId + ')">移除</a>';
                        if (row.status==1){html = '历史记录，只限查看'}
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
            parms:[{name:"search_dim",value:dim},{name:"search_dim2",value:dim2}],
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
    function onModifyPenTime(){
        art.dialog.open('/usermanage/show_penTime', {
            title: '黑名单',
            id:"alterPenTime",
            width: 900,
            height: 260,
        });
    }
    function refresh() {
        $("#blackList").click();
    }

</script>
<!--http://localhost:9090/userPage_manager?dim=a&page=1&pagesize=10-->
</body>
</html>

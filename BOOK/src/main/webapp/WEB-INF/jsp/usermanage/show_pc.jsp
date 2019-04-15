<%--
  Created by IntelliJ IDEA.
  User: liulei
  Date: 2019/1/23
  Time: 15:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>赔偿信息</title>
    <!-- 日期插件，使用jquery -->
    <link rel="stylesheet" href="js/libs/jquery/jquery.datepick.css" type="text/css">
    <script type="text/javascript" src="js/libs/jquery/jquery.datepick.js"></script>
    <script type="text/javascript" src="js/libs/jquery/jquery.datepick-zh-CN.js"></script>
</head>
<body>
    <div class="panel">
        <div class="panel-header">
            <h2>赔偿信息</h2>
            <a href="javascript:;" class="arrow up"></a>
        </div>

        <div class="main-cont">
            <div class="tab tab-default">
                <div class="tab-contbox">
                    <div class="search-box">
                        <table class="search-table">
                            <tr>
                                <td>
                                    <input id="bookName" type="text" placeholder="书名" class="input-text" />
                                </td>
                                <td>
                                    <input id="userName" type="text" placeholder="用户名" class="input-text" />
                                </td>
                                <td>
                                    <input id="start_time" type="text" placeholder="开始时间" class="input-text" />
                                </td>
                                <td>
                                    <input id="end_time" type="text" placeholder="结束时间" class="input-text" />
                                </td>
                                <td colspan="2"><a href="javascript:;" class="btn"><span id="btnLoad"><i class="icon icon-search"></i>查询</span></a></td>
                            </tr>
                        </table>
                    </div>
                    <%--<a href="javascript:void(0);" class="btn" onclick="onPcAdd()"><span id="btnTypeLoad"><i class="icon icon-add"></i>添加</span></a>--%>
                    <div id="typeInfo"></div>
                    <div id="pcInfo"></div>
                </div>
            </div>
        </div>
    </div>
</body>
<script>
    var start_time ;
    var end_time ;
    var bookName ;
    var userName ;
    requirejs(['jquery', 'ligerGrid','dg.datePicker', 'artdialog'], function($) {
        $(document).ready(function (){
            start_time = $("#start_time").val() ;
            end_time = $("#end_time").val() ;
            bookName = $("#bookName").val();
            userName = $("#userName").val() ;
            select() ;
        });
        $(function () {
            select() ;
        }) ;
        $("#btnLoad").click(function(){
            start_time = $("#start_time").val() ;
            end_time = $("#end_time").val() ;
            bookName = $("#bookName").val();
            userName = $("#userName").val() ;
            select() ;
        }) ;
    });
    function select() {
        $("#pcInfo").ligerGrid({
            columns: [{
                display: '书名',
                name: 'book.sm'
            }, {
                display: '用户姓名',
                name: 'user.xm'
            }, {
                display: '损毁程度',
                isAllowHide: false,
                render: function (row){
                    if (row.id != undefined && row.id != null && row.id != ""){
                        if (row.sh==1){return "轻度";}
                        if (row.sh==2){return "中度";}
                        if (row.sh==3){return "重度";}
                    }else return "" ;
                }
            }, {
                display: '是否已赔偿',
                isAllowHide: false,
                render: function (row){
                    if (row.id != undefined && row.id != null && row.id != ""){
                        if (row.ispc==1){return "已赔偿";}
                        else {return "未赔偿";}
                    }else return "" ;
                }
            }, {
            //     display: '是否已被黑名单计数',
            //     render: function (row){
            //         if (row.id != undefined && row.id != null && row.id != ""){
            //             if (row.iscount==1){return "已计数";}
            //             else {return "未计数";}
            //         }else return "" ;
            //     }
            // }, {
                display: '生成赔偿记录日期',
                name: 'pcdate'
            },{

                display: '操作',
                isAllowHide: false,
                render: function (row){
                    if (row.id != undefined && row.id != null && row.id != ""){
                        var html = '<a href="javascript:void(0);" onclick="onInfo(' + row.id + ')">查看详情</a>&nbsp;&nbsp;' ;
                        html = html + '<a href="javascript:void(0);" onclick="onEdit(' + row.id + ')">修改记录</a>&nbsp;&nbsp;';
                        html = html + '<a href="javascript:void(0);" onclick="onDel(' + row.id + ')">删除</a>';
                        return html;
                    }else return "" ;
                }
            }],
            url: '/usermanage/getpcdata',
            method:'get',
            dataType: 'server',
            dataAction: 'server',
            pageSize: 10,
            width: '100%',
            checkbox: false,
            rownumbers: false,
            parms:[
                {name:"startTime",value:start_time},
                {name:"endTime",value:end_time},
                {name:"dim",value:bookName},
                {name:"user",value:userName}
            ],
            fixedCellHeight: false,
            iShowScroll: false,
            allowAdjustColWidth: true
        }) ;
    };

    $(document).ready(function(){
        //使用class属性处理  'yy-mm-dd' 设置格式"yy-mm-dd"
        $('#start_time').datepick({dateFormat: 'yy-mm-dd'});
        $('#end_time').datepick({dateFormat: 'yy-mm-dd'});
    });
    //赔偿详情
    function onInfo(id){
        art.dialog.open('usermanage/pc_info?id='+id, {
            title: '赔偿详情',
            width: 880,
            height: 620,
            cancel: true,
            cancelVal: "关闭"
        });
    }
    //修改详情
    function onEdit(id){
        art.dialog.open('usermanage/showPc_edit?id='+id, {
            title: '修改赔偿信息',
            width: 800,
            height: 500,
            cancel: true,
            id:'pc_edit_window',
            cancelVal: "关闭"
        });
    }
    //添加赔偿
    function onPcAdd() {
        art.dialog.open('usermanage/pc_add', {
            title: '添加赔偿',
            width: 750,
            height: 500,
            cancel: true,
            id:'pc_add_window',
            cancelVal: "关闭"
        });
    }
    //删除赔偿信息
    function onDel(pcId) {
        if (confirm("你是否要删除这条赔偿信息？")){
            $.ajax({url:"${pageContext.request.contextPath}/usermanage/pc_del?pcId="+pcId,
                success:function(data){
                    if (data){
                        select() ;
                    }else {
                        alert("删除失败！") ;
                    }
                }
            });
        }
    }
</script>
</html>

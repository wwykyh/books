<%--
  用户借阅界面
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>借阅历史</title>
    <!-- 日期插件，使用jquery -->
    <link rel="stylesheet" href="js/libs/jquery/jquery.datepick.css" type="text/css">
    <script type="text/javascript" src="js/libs/jquery/jquery.datepick.js"></script>
    <script type="text/javascript" src="js/libs/jquery/jquery.datepick-zh-CN.js"></script>
</head>
<body>
    <div class="panel">
        <div class="panel-header">
            <h2>借阅历史</h2>
            <a href="javascript:;" class="arrow up"></a>
        </div>

        <div class="main-cont">
            <div class="tab tab-default">
                <div class="tab-contbox">
                    <div class="search-box">
                        <table class="search-table">
                            <tr>
                                <td>
                                    <input id="start_time" type="text" placeholder="开始时间" class="input-text" />
                                </td>
                                <td>
                                    <input id="end_time" type="text" placeholder="结束时间" class="input-text" />
                                </td>
                                <td>
                                    <input id="dim" type="text"  style="display:none" placeholder="书名、作者" class="input-text" />
                                </td>
                                <td>
                                    <input id="user" type="text"  style="display:none" placeholder="用户名" class="input-text" />
                                </td>
                                <td colspan="2"><a href="javascript:;" class="btn"><span id="btnLoad"><i class="icon icon-search"></i>查询</span></a></td>
                            </tr>
                        </table>
                    </div>

                    <div id="historyInfo"></div>
                </div>
            </div>
        </div>
    </div>
</body>
<script>

    var start_time ;
    var end_time ;
    var dim ;
    var user ;


    requirejs(['jquery', 'ligerGrid','dg.datePicker', 'artdialog'], function($) {
        $(function () {
            start_time = "" ;
            end_time = "" ;
            dim = "" ;
            if("${user.xm}"=="admin"){
                user = "" ;
            }else {
                user="${user.xm}";
            }
            select() ;
        }) ;
        function select() {
            $("#historyInfo").ligerGrid({
                columns: [{
                    display: '书名',
                    name: 'book.sm'
                }, {
                    display: '作者',
                    name: 'book.zz'
                },  {
                    display: '借阅用户',
                    name: 'user.xm'
                }, {
                    display: '借阅日期',
                    name: 'jyrq'
                }, {
                    display: '归还日期',
                    name: 'ghrq'
                },{
                    display: '操作',
                    isAllowHide: false,
                    render: function (row){
                        if (row.id != undefined && row.id != null && row.id != ""){
                            var html = '<a href="javascript:void(0);" onclick="onHistoryInfo(' + row.id + ')">查看详情</a>&nbsp;&nbsp;' ;

                                html = html + '<a href="javascript:void(0);" onclick="commentInfo(' + row.id + ')">评价</a>';
                            return html;
                        }
                        else return "" ;
                    }
                }],
                url: '/user_historyPage',
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
                    {name:"dim",value:dim},
                    {name:"user",value:user}
                ],
                fixedCellHeight: false,
                iShowScroll: false,
                allowAdjustColWidth: true
            }) ;
        };

        $("#btnLoad").click(function(){
            start_time = $("#start_time").val() ;
            end_time = $("#end_time").val() ;
            dim = $("#dim").val();
            if("${user.xm}"=="admin"){
                user = " " ;
            }else {
                user="${user.xm}"
            }
            select() ;
        }) ;
    });

    $(document).ready(function(){
        $('#start_time').datepick({dateFormat: 'yy-mm-dd'});
        $('#end_time').datepick({dateFormat: 'yy-mm-dd'});
    });

    function onHistoryInfo(id){
        art.dialog.open('history_info?id='+id, {
            title: '借阅详情',
            width: 850,
            height: 575,
            cancel: true,
            cancelVal: "关闭"
        });
    }
    function commentInfo(id){
        art.dialog.open('commentInfo?id='+id, {
            title: '评价',
            width: 850,
            height: 550,
            id:'commentInfo_window'
        });
    }
</script>
</html>

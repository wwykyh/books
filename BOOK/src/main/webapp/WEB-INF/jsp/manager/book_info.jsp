<%--
  Created by IntelliJ IDEA.
  User: liulei
  Date: 2019/2/12
  Time: 9:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>图书详情</title>
    <link rel="stylesheet" type="text/css" href="css/common/iconfont/iconfont.css" />
    <link rel="stylesheet" type="text/css" href="css/common/layout.css" />
    <link rel="stylesheet" type="text/css" href="dvpt/css/libs.css" />
    <link rel="stylesheet" type="text/css" href="css/demo/style.css" />
    <link rel="stylesheet" type="text/css" href="css/theme/blue.css" id="style" />
    <script type="text/javascript" src="dvpt/config.js"></script>
    <!-- 改造的脚本 -->
    <script type="text/javascript" src="js/extend.js"></script>
    <!-- 共有的控件 -->
    <script data-main="main" src="dvpt/require.min.2.1.11.js"></script>
</head>
<body>
<div class="panel">
    <div class="panel-header">
        <h2>图书详情</h2>
        <a href="javascript:" class="arrow up"></a>
    </div>
    <div class="panel-body panel-noborder">
        <div class="write-box">
                <table class="form-table" width="100%">
                    <tr>
                        <th width="17%"><span class="ft-need"></span>图书ISBN：</th>
                        <td width="33%">${bookInfo.isbn}</td>
                        <th width="17%"><span class="ft-need"></span>图书名称：</th>
                        <td width="33%"> ${bookInfo.sm}</td>
                    </tr>
                    <tr>
                        <th width="17%"><span class="ft-need"></span>出版社名称：</th>
                        <td width="33%">${bookInfo.cbsmc}</td>
                        <th width="17%"><span class="ft-need"></span>出版日期：</th>
                        <td width="33%"> ${bookInfo.cbrq}</td>
                    </tr>
                    <tr>
                        <th width="17%"><span class="ft-need"></span>作者：</th>
                        <td width="33%">${bookInfo.zz}</td>
                        <th width="17%"><span class="ft-need"></span>图书类型：</th>
                        <td width="33%"> ${bookInfo.lxmc}</td>
                    </tr>
                    <tr>
                        <th width="17%"><span class="ft-need"></span>所属类型：</th>
                        <td width="33%">${bookInfo.tsdl}</td>
                        <th width="17%"><span class="ft-need"></span>图书提供者：</th>
                        <td width="33%"> ${bookInfo.uname}</td>
                    </tr>
                    <tr>
                        <th width="17%"><span class="ft-need"></span>位置：</th>
                        <td width="33%">${bookInfo.wz}</td>
                        <th width="17%"><span class="ft-need"></span>损毁程度：</th>
                        <td width="33%"> ${bookInfo.sh}</td>
                    </tr>
                    <tr>
                        <th width="17%"><span class="ft-need"></span>入库时间：</th>
                        <td width="33%">${bookInfo.rksj}</td>
                        <th width="17%"><span class="ft-need"></span>图书状态：</th>
                        <td width="33%"> ${bookInfo.status}</td>
                    </tr>
                </table>
        </div>
    </div>

    <div class="panel-header">
        <h2>用户评论</h2>
        <a href="javascript:" class="arrow up"></a>
    </div>
    <div id="commemt"></div>
</div>
<script type="text/javascript">
    requirejs(['jquery','ligerGrid','artdialog'], function($) {
        $(function() {
            bookInfo_select() ;
        });
    });

    function bookInfo_select() {
        $("#commemt").ligerGrid({
            columns: [{
                display: '用户',
                name: 'xm'
            }, {
                display: '日期',
                name: 'pjrq'
            }, {
                display: '内容',
                name: 'nr'
            },{
                display: '操作',
                isAllowHide: false,
                render: function (row){
                    if (row.commentId != undefined && row.commentId != null && row.commentId != ""){
                        var html = '<a href="javascript:void(0);" onclick="onComInfo(' + row.commentId + ')">查看详情</a>&nbsp;&nbsp;' ;
                        html = html + '<a href="javascript:void(0);" onclick="onComDel(' + row.commentId + ')">删除</a>';
                        return html;
                    }else return "" ;
                }
            }],
            url: '/comment_manager',
            method:'get',
            dataType: 'server',
            dataAction: 'server',
            pageSize: 5,
            width: '100%',
            parms:[{name:"isbn",value:"${bookInfo.isbn}"}],
            checkbox: false,
            rownumbers: false,
            fixedCellHeight: false,
            iShowScroll: false
        });
    }
    function onComInfo(commentId){
        art.dialog.open('comment_info?commentId='+commentId+'&tsmc=${bookInfo.sm}', {
            title: '评价详情',
            width: 900,
            height: 475,
            cancel: true,
            cancelVal: "关闭"
        });
    }
    function onComDel(commentId){
        if (confirm("你是否要将这条评价删除？")){
            $.ajax({url:"${pageContext.request.contextPath}/del_comment?commentId="+commentId,
                success:function(data){
                    if (data == "0") {
                        bookInfo_select() ;
                    }else {
                        alert("删除失败！") ;
                    }
                }
            });
        }
    }
</script>
</body>
</html>

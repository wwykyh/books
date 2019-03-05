<%--
  Created by IntelliJ IDEA.
  User: liulei
  Date: 2019/1/23
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>出版社管理</title>
    <%--<script type="text/javascript" src="dvpt/libs/ligerGrid/ligerGrid.js"></script>--%>
</head>
<body>
    <div class="panel">
        <div class="panel-header">
            <h2>出版社管理</h2>
            <a href="javascript:;" class="arrow up"></a>
        </div>
    </div>
    <a href="javascript:void(0);" class="btn" onclick="onPublishAdd()"><span id="btnLoad"><i class="icon icon-add"></i>添加</span></a>
    <div id="studentsInfo"></div>

    <script type="text/javascript" >
        requirejs(['jquery', 'ligerGrid','artdialog'], function($) {
            $(function() {
                publish_select() ;
            });
        });

        function publish_select() {
            $("#studentsInfo").ligerGrid({
                columns: [{
                    display: '出版社名称',
                    name: 'pubName'
                }, {
                    display: '出版社代码',
                    name: 'pubNumber'
                }, {
                    display: '联系电话号码',
                    name: 'pubPhone'
                }, {
                    display: '邮箱',
                    name: 'pubEmail'
                },{
                    display: '操作',
                    isAllowHide: false,
                    render: function (row){
                        if (row.pubId != undefined && row.pubId != null && row.pubId != ""){
                            var html = '<a href="javascript:void(0);" onclick="onPublishEdit(' + row.pubId + ')">修改</a>&nbsp;&nbsp;' ;
                            html = html + '<a href="javascript:void(0);" onclick="onPublishDel(' + row.pubId + ')">删除</a>';
                            return html;
                        }else return "" ;
                    }
                }],
                url: '/publish_manager',
                method:'get',
                dataType: 'server',
                dataAction: 'server',
                pageSize: 10,
                width: '100%',
                checkbox: false,
                fixedCellHeight: false,
                iShowScroll: false
            });
        }
    function onPublishAdd() {
        art.dialog.open('publish_add', {
            title: '出版社添加',
            width: 700,
            height: 375,
            cancel: true,
            id:'pub_add_window',
            cancelVal: "关闭"
        });
    }
    function onPublishEdit(pubId) {
        art.dialog.open('publish_edit?pubId='+pubId, {
            title: '出版社修改',
            width: 700,
            height: 375,
            cancel: true,
            id:'pub_edit_window',
            cancelVal: "关闭"
        });
    }
    function onPublishDel(pubId) {
        if (confirm("你是否要删除出版社信息？")){
            $.ajax({url:"${pageContext.request.contextPath}/publish_del?pubId="+pubId,
                success:function(data){
                    if (data == '0'){
                        publish_select() ;
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

<%--
  Created by IntelliJ IDEA.
  User: liulei
  Date: 2019/1/23
  Time: 15:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>电子书种类管理</title>
</head>
<body>
    <div class="panel">
        <div class="panel-header">
            <h2>种类管理</h2>
            <a href="javascript:;" class="arrow up"></a>
        </div>

        <a href="javascript:void(0);" class="btn" onclick="onTypeAdd()"><span id="btnTypeLoad"><i class="icon icon-add"></i>添加</span></a>
        <div id="typeInfo"></div>
    </div>


    <script type="text/javascript">
        requirejs(['jquery', 'ligerGrid','artdialog'], function($) {
            $(function() {
                type_select() ;
            });
        });

        function type_select(){
            $("#typeInfo").ligerGrid({
                columns: [{
                    display: '电子书类型名称',
                    name: 'ebookTypemc'
                }, {
                    display: '操作',
                    isAllowHide: false,
                    render: function (row){
                        if (row.ebookTypeid != undefined && row.ebookTypeid != null && row.ebookTypeid != ""){
                            var html = '<a href="javascript:void(0);" onclick="onTypeEdit(' + row.ebookTypeid + ')">修改</a>&nbsp;&nbsp;' ;
                            html = html + '<a href="javascript:void(0);" onclick="onTypeDel(' + row.ebookTypeid + ')">删除</a>';
                            return html;
                        }else return "" ;
                    }
                }],
                url: 'eBookFile/typeManager',
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
        function onTypeAdd() {
            art.dialog.open('eBookFile/toTypeAdd', {
                title: '电子书类型添加',
                width: 700,
                height: 375,
                cancel: true,
                id:'type_add_window',
                cancelVal: "关闭"
            });
        }
        function onTypeEdit(typeId) {
            art.dialog.open('type_edit?typeId='+typeId, {
                title: '电子书类型修改',
                width: 700,
                height: 375,
                cancel: true,
                id:'type_edit_window',
                cancelVal: "关闭"
            });
        }
        function onTypeDel(typeId) {
            if (confirm("你是否要删除电子书类型？")){
                $.ajax({url:"${pageContext.request.contextPath}/type_del?typeId="+typeId,
                    success:function(data){
                        if (data == '0'){
                            type_select() ;
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

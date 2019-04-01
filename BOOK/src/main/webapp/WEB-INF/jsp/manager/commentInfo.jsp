
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<body style="overflow-x: hidden">
<div class="panel">
    <%--<div class="panel-header">
        <form>
            <tr style="width: 100%;height: 600px">
                <textarea id="container"></textarea>
            </tr>
            <input type="button" value="提交" onclick="tijiao()"/>
        </form>
    </div>--%>
    <div >
        <div style="text-align:center">
            <tr>
            <textarea id="container" style="width:825px; height: 180px"></textarea>
                <input type="button" class="btn" value="清空文档" onclick="qingkong()" />
                <input type="button"  class="btn" value="发表评论"  onclick="tijiao()"/>
            </tr>
        </div>
        <div class="write-box">
            <form  method="post" class="form_eva" name="form_eva"id="form_eva"  action="userBorrow/commentService">
                <table>
                    <tr style="display:none" >
                        <td>用户id：<input type="text" value="${history.user.userId}" disabled="disabled" name="userId" id="userId"/></td>
                        <td>图书id：<input type="text" value="${history.book.isbn}" disabled="disabled" name="bookId" id="bookId"/></td>
                    </tr>
                </table>
            </form>
        </div>
        <div >
            <table class="form-table" width="100%" id="commentTable" >
                <th width="10%" style="text-align: left" ><span> <font size="4">历史评价：</font></span></th>
                <c:forEach items="${tCommentInfo}" var="arr">
                   <div >
                     <tr  >
                         <td width="10%" style="padding-left:10px" ><h1  style="font-family:微软雅黑 ">${arr.xm}：</h1></td>
                     </tr>
                     <tr style="background:#EFEFEF;">
                         <td width="80%" style="padding-left: 35px">${arr.nr}</td>
                         <%--删除评论
                         <td width="10%" style="text-align:right"><a href="javascript:void(0);"  onclick="delComment('${arr.commentId}')">删除</a></td>--%>
                         <td width="10%" style="padding-right:10px" >${arr.pjrq}</td>
                     </tr>
                </c:forEach>
                   </div>
            </table>
        </div>

    </div>
</div>

</body>
<script type="text/javascript">
    function select() {
        $.ajax({
            type:"GET",
            URL:"/commentInfo"
        });
    }
    function qingkong() {

    //     document.execCommand("Delete",null);
        if (confirm("确定清空当前文档么？")){
            UE.getEditor('container').execCommand("cleardoc");
        }

    }
    function tijiao() {
        alert();
        var userId=form_eva.userId.value;
        var bookId=form_eva.bookId.value;
        $.ajax({
            type: "GET",
            url:"/commentService",
            data:{
                userId:userId,
                bookId:bookId,
                userComment:UE.getEditor('container').getContent()
            },
            async: false,
            success: function(data) {
                if(data == 0){
                    alert("评价失败");
                }
                //清空文档内容
                UE.getEditor('container').execCommand("cleardoc");
                //刷新评论区
                $("#commentTable").load(location.href+" #commentTable");
                //parent.art.dialog({id:'commentInfo_window'}).location.reload();

            }
        });
    };

    function delComment(id) {
        if (confirm("确定删除该评论？")){
            $.ajax({
                type: "GET",
                url:"/delCommentByid?id="+id+"",
                async: false,
                success: function(data) {
                    if(data == 0){
                        alert("删除失败");
                    }else {
                        $("#commentTable").load(location.href+" #commentTable");
                    }
                }
            });
        }
    }
    requirejs(['jquery', 'bdeditor', 'zeroclipboard'], function (jqeury, bdeditor, zeroclipboard) {
        window['ZeroClipboard'] = zeroclipboard;
        var ue = UE.getEditor('container',{
            toolbars: [[
                'fullscreen', 'source', '|', 'undo', 'redo', '|',
                'bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript', 'subscript',    'removeformat', 'formatmatch', 'autotypeset', 'blockquote', 'pasteplain', '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc', '|',
                'rowspacingtop', 'rowspacingbottom', 'lineheight', '|',
                'customstyle', 'paragraph', 'fontfamily', 'fontsize', '|',
                'directionalityltr', 'directionalityrtl', 'indent', '|',
                'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|', 'touppercase', 'tolowercase', '|',
                'link', 'unlink', 'anchor', '|', 'imagenone', 'imageleft', 'imageright', 'imagecenter', '|',
                'emotion',    'insertcode',  'pagebreak',   '|',
                'horizontal', 'date', 'time', 'spechars', 'snapscreen',  '|',
                'print', 'preview', 'searchreplace','help'
            ]]
        });
    });
</script>
</html>

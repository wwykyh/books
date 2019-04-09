
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>评论详情</title>

    <link rel="stylesheet" type="text/css" href="css/style1.css"/>
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
        <div >
            <tr style="text-align:center">
                <textarea id="container" style="width:815px; height: 180px"></textarea>
            </tr>
            <div style="text-align:right">
                <tr >
                    <input type="button" class="btn" value="清空文档" onclick="qingkong()" />
                    <input type="button"  class="btn" value="发表评论"  onclick="tijiao()"/>
                </tr>
            </div>
        </div>
        <div class="write-box">
            <form  method="post" class="form_eva" name="form_eva"id="form_eva"  action="userBorrow/commentService">
                <table>
                    <tr style="display:none" >
                        <td>用户id：<input type="text" value="${historyInfo.user.userId}" disabled="disabled" name="userId" id="userId"/></td>
                        <td>图书id：<input type="text" value="${historyInfo.book.isbn}" disabled="disabled" name="bookId" id="bookId"/></td>
                    </tr>
                </table>
            </form>
        </div>
        <th width="10%" style="text-align: left" ><span> <font size="4">历史评论：</font></span></th>
        <div style="padding-left:5% ; padding-right: 5%" class="parentid"id="parentid" >
            <table class="form-table" width="100%" id="commentTable" >

                <c:forEach items="${commentInfos}" var="arr">
                <div >
                    <tr style="background:#a7d0ef;" >
                        <td style="padding-left:10px ;" width="80%">${arr.xm} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${arr.pjrq}</td>
                        <td width="20%"  style="padding-left: 5%" onclick="delComment('${arr.commentId}')">
                            <c:choose>
                            <c:when test="${arr.xm==user.xm}">
                            删除</td>
                        </c:when>
                        <c:otherwise>
                            <td></td>
                        </c:otherwise>
                        </c:choose>
                    </tr>
                    <tr style="background:#EFEFEF;">
                        <td width="80%" style="padding-left: 35px">
                            <div  class="innerCtn" id="innerCtn-id" >
                                <span >${arr.nr}</span>
                            </div>
                        </td>
                        <td style="padding-left: 5%" onclick="xiangqing(this)">查看更多/收起</td>
                    </tr>
                    </c:forEach>
                </div>
            </table>
        </div>
    </div>
    <div style="height: 10px ;padding-left: 81%"><h3 onclick="allComment()" >查看全部/收起</h3></div>
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
        var userId=form_eva.userId.value;
        var bookId=form_eva.bookId.value;
        if(UE.getEditor('container').getContent()==null|| UE.getEditor('container').getContent()=="" ){
            alert("评论不能为空");
            return;
        }
        if(bookId==undefined || bookId==" " || bookId==null ){
            alert("评论失败");
            return;
        }
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


    function xiangqing(obj) {
        var trObj = obj.previousElementSibling;
        var divObj = trObj.children[0];
        if(divObj.className=="innerCtn"){
            divObj.className="innerCtn1"
        }else {
            divObj.className="innerCtn"
        }
        //divObj.style.height = 'auto';
    }
    function allComment() {
        var divEle= document.getElementById('parentid');
        if(divEle.className=="parentid"){
            divEle.className='parentid1'
        }else {
            divEle.className='parentid'
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
                'link', 'unlink',  '|',
                'emotion',    'insertcode',  'pagebreak',   '|',
                'horizontal', 'date', 'time', 'spechars', 'snapscreen',  '|',
                'print', 'preview', 'searchreplace','help'
            ]]
        });
    });
</script>
</html>

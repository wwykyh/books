
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>图书详情</title>
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
               <div style="text-align:center">
                   <table width="100%">
                       <tr>
                           <td style="text-align: center">书名：${bookInfo.sm}</td>
                       </tr>
                   </table>
                   <img src="${bookInfo.picture}" width="auto" height="200px"/>
               </div>
           </div>

            <div style="width: 100%">
                <table width="100%">
                    <tr>
                        <td width="20%" style="text-align: left" ><span> <font size="4" f>历史评论：</font></span></td>
                        <td width="80%" style="padding-left: 62%" ><span onclick="checkAll()" id="checkAll"  style="margin-top: 10px;float: left;cursor: pointer"><font face="黑体" >全选/反选</font></span></td>
                    </tr>
                </table>
            </div>
            <div  id="textComment" class="textComment"></div>
        <div style="padding-left:5% ; padding-right: 5% ;" class="parentid3"id="parentid"  >
            <table class="form-table" width="100%" id="commentTable"  >
                <c:forEach items="${commentInfos}" var="arr">
                <div >
                    <tr  style="background:#a7d0ef; " >
                        <td style="padding-left:10px ;" width="80%">${arr.xm} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${arr.pjrq}</td>
                        <c:choose>
                            <c:when test="${user.xm =='admin'}" >
                                <td width="20%" style="padding-left: 5%" >
                                    <span style="cursor: pointer" onclick="delComment('${arr.commentId}')">删除</span>
                                    <input type="checkbox" name="ck" value="${arr.commentId}" style="margin-top: 3px;float: left" >
                                </td>
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
                        <td style="padding-left: 5%" onclick="xiangqing(this)">
                            <span style="cursor: pointer">
                                查看更多/收起
                            </span>
                        </td>
                    </tr>

                    </c:forEach>
                </div>
            </table>
        </div>
        <div style="height: 10px ;padding-left: 75%">
            <table>
                <tr>
                    <td onclick="allComment()" style="cursor: pointer">查看全部/收起</td>&nbsp;
                    <td style="cursor: pointer" onclick="deleteAll()"id="deleteAll">批量删除</td>
                </tr>
            </table>
        </div>

    </div>
</div>

</body>
<script type="text/javascript">
    requirejs(['jquery' ], function (jqeury){
        var num = (document.getElementById('commentTable').getElementsByTagName('tr').length)/2 ;
        controllerline(num);
    });
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
                        var num = (document.getElementById('commentTable').getElementsByTagName('tr').length)/2 ;
                            num=num-1;
                        controllerline(num);
                    }
                }
            });
        }
    }

    function xiangqing(obj) {
        var trObj = obj.previousElementSibling;
        var divObj = trObj.children[0];
        var num = (document.getElementById('commentTable').getElementsByTagName('tr').length)/2 ;
        if(divObj.className=="innerCtn"){
            divObj.className="innerCtn1"
        }else {
            divObj.className="innerCtn"
        }
        //divObj.style.height = 'auto';

    }
    function allComment() {
        var divEle= document.getElementById('parentid');
        var num = (document.getElementById('commentTable').getElementsByTagName('tr').length)/2 ;
        if(num<=3){
            alert("无更多评论！");
            return;
        }
        if(divEle.className=="parentid3"){
            divEle.className='parentid'
        }
        else if(divEle.className=="parentid2"){
            divEle.className='parentid'
        }else if(divEle.className=="parentid1"){
            divEle.className='parentid'
        }
        else if(divEle.className=="parentid"){
            if(num<3){
                divEle.className='parentid'
            }else {
                divEle.className='parentid3'
            }
        }
    }

    function controllerline(num) {
        var divEle= document.getElementById('parentid');
        var divText = document.getElementById('textComment');
        if(num<=3){
            divEle.className='parentid'
        }
        else if(num>3){
            divEle.className='parentid3';
        }
        if(num==0){
            divText.className='parentid0';
            divText.innerHTML="<html><span ><font size='3' face='微软雅黑'>暂无评论哦，来做第一个评论的人吧！          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; --来自书评小精灵</font></span></html>";
        }else {
            divText.className="textComment";
            divText.innerHTML=null;
        }
    }

    function deleteAll() {
        var check=document.getElementsByName("ck");
        var ck = [];
        for(var i=0;i<check.length;i++){
            var a=check[i];
            if(a.checked==true){
                ck.push(check[i].value);
            }
        }
        if(ck==null||ck==""){
            alert("请选择你要删除的评论！");
            return;
        }
        if(ck.length!=0){
            $.ajax({
                traditional: true,
                data : {
                    check:ck,
                    i:1
                },
                url:"/delAllCommentByid",
                success:function (data) {
                    var num = (document.getElementById('commentTable').getElementsByTagName('tr').length)/2 ;
                    if(data==0){
                        alert("删除失败！");
                    }else {
                        alert("删除成功！");
                        for(var i=0;i<ck.length;i++){
                            $("tr").remove("#tr_"+ck[i]);
                            num=num-i;
                        }
                    }
                    console.log(num);
                    controllerline(num);
                    $("#commentTable").load(location.href+" #commentTable");
                }
            });
        }
    }

    function checkAll(){
        var ck=document.getElementsByName("ck");
        if(ck.length==0||ck.length==""){
            alert("暂无评论信息");
            return;
        }
        for(var i=0;i<ck.length;i++){
            var c=ck[i];
            if(c.checked==true){
                c.checked=false;
            }else {
                c.checked=true;
            }
        }
    }

</script>
</html>

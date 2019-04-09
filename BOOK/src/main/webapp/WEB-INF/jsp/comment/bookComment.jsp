
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
        <%--   <div >
               <div style="text-align:center">
                   <table width="100%">
                       <tr>
                           <td style="text-align: center">书名：${historyInfo.book.sm}</td>
                       </tr>
                   </table>
                   <img src="https://gss0.baidu.com/94o3dSag_xI4khGko9WTAnF6hhy/zhidao/wh%3D600%2C800/sign=7e15cda791529822056631c5e7fa57f3/0b55b319ebc4b7457b9f4676cefc1e178a821542.jpg" width="150px" height="200px"/>
               </div>
               <div>
                    <table width="100%" >
                        <tr >
                           <td style="text-align:center">
                               作者：${historyInfo.book.zz}
                           </td>
                        </tr>
                        <tr width="40%" >
                            <td style="padding-left: 25% ;padding-right: 25% ;" >
                                简介：${historyInfo.book.jj}
                            </td>
                        </tr>
                    </table>
               </div>
           </div>--%>
            <th width="10%" style="text-align: left" ><span> <font size="4">历史评论：</font></span></th>
        <div style="padding-left:5% ; padding-right: 5% ;" class="parentid"id="parentid"  >
            <table class="form-table" width="100%" id="commentTable"  >
                <c:forEach items="${commentInfos}" var="arr">
                <div >
                    <tr  style="background:#a7d0ef; " >
                        <td style="padding-left:10px ;" width="80%">${arr.xm} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${arr.pjrq}</td>
                        <c:choose>
                            <c:when test="${user.xm =='admin'}" >
                                <td width="20%" style="padding-left: 5%" onclick="delComment('${arr.commentId}')"> 删除
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
                        <td style="padding-left: 5%" onclick="xiangqing(this)">查看更多/收起</td>
                    </tr>

                    </c:forEach>
                </div>
            </table>
        </div>
        <div style="height: 10px ;padding-left: 81%"><h3 onclick="allComment()" >查看全部/收起</h3></div>

    </div>
</div>

</body>
<script type="text/javascript">
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


</script>
</html>

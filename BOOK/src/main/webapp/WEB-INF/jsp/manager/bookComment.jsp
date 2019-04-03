
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
        <div style="padding-left:8% ; padding-right: 8%">
            <table class="form-table" id="commentTable"  >
                <th width="10%" style="text-align: left" ><span> <font size="4">历史评论：</font></span></th>
                <c:forEach items="${commentInfos}" var="arr">
                   <div >
                     <tr  style="background:#a7d0ef;">
                         <td style="padding-left:10px" ><h1  style="font-family:微软雅黑 ">${arr.xm}：</h1></td>
                         <td  width="15%" >${arr.pjrq}</td>
                         <td width="10%" style="text-align: right"><a href="javascript:void(0);"  onclick="delComment('${arr.commentId}')">删除</a></td>
                     </tr>
                     <tr style="background:#EFEFEF;">
                         <td width="80%" style="padding-left: 35px">
                                     ${arr.nr}
                         </td>
                         <td width="10%"></td>
                         <td width="10%"></td>
                     </tr>
                </c:forEach>
                   </div>
            </table>
        </div></div>
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

</script>
</html>

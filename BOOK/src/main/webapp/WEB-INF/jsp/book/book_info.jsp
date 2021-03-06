<%--
  Created by IntelliJ IDEA.
  User: wangweiyan
  Date: 2019-04-02
  Time: 14:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
    <title>Book Store</title>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <script src="js/prototype.js" type="text/javascript"></script>
    <script src="js/scriptaculous.js?load=effects" type="text/javascript"></script>
    <script src="js/lightbox.js" type="text/javascript"></script>
    <script type="text/javascript" src="js/java.js"></script>

    <link rel="stylesheet" type="text/css" href="css/style1.css"/>
    <link rel="stylesheet" href="css/lightbox.css" type="text/css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="css/common/iconfont/iconfont.css" />

    <link rel="stylesheet" type="text/css" href="dvpt/css/libs.css" />
    <link rel="stylesheet" type="text/css" href="css/theme/blue.css" id="style" />
    <script type="text/javascript" src="dvpt/config.js"></script>
    <!-- 改造的脚本 -->
    <script type="text/javascript" src="js/extend.js"></script>
    <!-- 共有的控件 -->
    <script data-main="main" src="dvpt/require.min.2.1.11.js"></script>

</head>
<body style="overflow-y: scroll;overflow-x: hidden;font-size:16px" >
<div id="wrap">

    <div class="left_content" style="width: 100%;height: 100%">

        <div class="feat_prod_box_details" style="height:30%;width: auto">

            <div class="prod_img" style="height: 80%;width: 30%;margin-left:100px"><a href="details.html" ><img
                    src="${bookInfo.picture}" alt="" title="" style="height:187.5px;width: 125px"
                    border="0"/></a>
                <br/><br/>
                <a href="${bookInfo.picture}" rel="lightbox"><img src="images/zoom.gif" alt="" title="" border="0"/></a>
            </div>

            <div class="prod_det_box" >
                <div class="box_center" >
                    <div class="price"><strong>书名:</strong> <span class="red">${bookInfo.sm}</span></div>
                    <div class="price"><strong>出版社:</strong> <span class="red">${bookInfo.cbsmc}</span></div>
                    <div class="price"><strong>出版日期:</strong> <span class="red">${bookInfo.cbrq}</span></div>
                    <div class="price"><strong>作者:</strong> <span class="red">${bookInfo.zz}</span></div>
                    <div class="price"><strong>图书类型:</strong> <span class="red">${bookInfo.lxmc}</span></div>
                    <div class="price"><strong>状态:</strong><span class="red">${bookInfo.status}</span></div>
                    <c:if test="${bookInfo.jyxm!=null}">
                    <div class="price"><strong>当前借阅人:</strong><span class="red">${bookInfo.jyxm}</span></div></c:if>
                    <c:if test="${bookInfo.status=='在库' && user.xm!='admin'}">
                    <a href="javascript:void(0);" onclick="borrow('${bookInfo.id}')" class="more" ><img src="images/jy.jpg" alt="" title="" border="0" style="height:26px;width: 96px"/></a></c:if>
<%--
                    <a href="javascript:void(0);" onclick="like('${bookInfo.id}')" class="more" ><img src="images/sc.jpg" alt="" title="" border="0" style="height:26px;width: 96px"/></a>
--%>


                    <div class="clear"></div>
                </div>

                <div class="box_bottom"></div>
            </div>
            <div class="clear"></div>
        </div>


        <div id="demo" class="demolayout" style="margin-left:5%;margin-top:5%">

            <ul id="demo-nav" class="demolayout">
                <li><a class="active" href="#tab1">详情</a></li>
                <li><a class="" href="#tab2">评论</a></li>
            </ul>

            <div class="tabs-container">

                <div style="display: block;" class="tab" id="tab1">
                    <p class="more_details" style="font-size: 16px">${bookInfo.jj}</p>
                </div>
                <div style="display: none;" class="tab" id="tab2">
                    <div class="divtable2"id="divtable" >
                        <div class="textComment" id="textComment"></div>
                        <table class="form-table" width="100%" id="commentTable" style="font-size: 18px" >
                            <c:forEach items="${commentInfos}" var="arr">
                                <tr  style="background:#a7d0ef; " >
                                    <td style="padding-left:10px ;" width="80%">${arr.xm} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${arr.pjrq}</td>
                                    <c:choose>
                                        <c:when test="${arr.xm==user.xm || user.xm =='admin'}" >
                                            <td width="20%" style="padding-left: 5%" onclick="delComment('${arr.commentId}')">                              <span style="cursor: pointer">删除</span>
                                            </td>
                                        </c:when>
                                        <c:otherwise>
                                            <td></td>
                                        </c:otherwise>
                                    </c:choose>
                                </tr>
                                <tr style="background:#EFEFEF; ">
                                    <td width="80%" style="padding-left: 35px;" >
                                        <div  class="innerCtn" id="innerCtn-id" >
                                            <span >${arr.nr}</span>
                                        </div>
                                    </td>
                                    <td style="padding-left: 5%" onclick="xiangqing(this)">
                                       <span style="cursor: pointer">查看更多/收起</span>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                    <h3 onclick="allComment()" style="padding-left: 85%;cursor: pointer">查看全部/收起</h3>
                    <tr style="text-align:center">
                        <textarea id="container"style="height: 180px" ></textarea>
                    </tr>
                    <div style="text-align:right">
                        <tr >
                            <input type="button" class="btn" value="清空文档" onclick="qingkong()" />
                            <input type="button"  class="btn" value="发表评论"  onclick="tijiao()"/>
                        </tr>
                    </div>
                </div>
            </div>
        </div>
    </div>


        <div class="clear"></div>
    </div><!--end of left content-->

    <div class="clear"></div>


</div>

</body>
<script type="text/javascript">

    var tabber1 = new Yetii({
        id: 'demo'
    });

    function borrow(id) {
           // alert("详情" + id) ;
        /*art.dialog.open('borrow?id='+id, {
            title : '图书借阅',
            width : 300,
            height : 300,
            id:'borrow_id',
            //ok: true,
            // okVal: "打印",
           // cancel : true,
           // cancelVal : "关闭"
        });*/

        $.ajax({
            type : 'post',
            url : "doBorrow",
            data :{"id":id},
            error: function(request) {
                alert("Connection error:"+request.error);
            },
            success: function(data) {
                if(data == "0"){
                    alert("图书借阅申请成功，请到图书管理员处拿书！") ;
                    //  parent.select();
                }else {
                    alert("图书借阅申请失败,超出当前最大借阅数量！");
                }
                parent.art.dialog({id:'book_info'}).close() ;

            }
        })

    }

    function like(id) {
        //   alert("详情" + id) ;
        $.ajax({
            type : 'GET',
            url : "like",
            data :{"bookId":id},
            success : function(data) {
                if(data=='1'){
                    alert("收藏成功");

                }
                else {
                    alert("收藏失败,您已收藏");
                }
              //  parent.art.dialog({id:'book_info'}).close() ;

            }
        })
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
        var divEle= document.getElementById('divtable');
        var num = (document.getElementById('commentTable').getElementsByTagName('tr').length)/2 ;
        if(num<=2){
            alert("无更多评论！");
            return;
        }
        if(divEle.className=="divtable2"){
            divEle.className='divtable'
        }else if(divEle.className='divtable'){
            if(num<=2){
                divEle.className='divtable'
            }else{
                divEle.className='divtable2'
            }
        }
    }


    function qingkong() {
        controllerline();
        //     document.execCommand("Delete",null);
        if (confirm("确定清空当前文档么？")){
            UE.getEditor('container').execCommand("cleardoc");
        }

    }
    function tijiao() {
        var userId=${user.userId};
        var bookId=${bookInfo.isbn};
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
                var num = (document.getElementById('commentTable').getElementsByTagName('tr').length)/2 ;
                     num=num+1;
                controllerline(num);

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
                        var num = (document.getElementById('commentTable').getElementsByTagName('tr').length)/2 ;
                        num=num-1;
                        controllerline(num);
                    }
                }
            });
        }
    }
    function controllerline(num) {
        // var  commentNum =${commentNum};
        var divEle= document.getElementById('divtable');
        var divText = document.getElementById('textComment');
        if(num<=2){
            divEle.className='divtable'
        }else if(num>2){
            divEle.className='divtable2'
        }
        if(num==0){
            divText.className='divtable0';
            divText.innerHTML="<html><span ><font size='3' face='微软雅黑'>暂无评论哦，来做第一个评论的人吧！          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; --来自书评小精灵</font></span></html>";

        }else {
            divText.className="textComment";
            divText.innerText=null;
        }

    }
    requirejs(['jquery', 'bdeditor', 'zeroclipboard','artdialog'], function (jqeury, bdeditor, zeroclipboard) {
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
        var num = (document.getElementById('commentTable').getElementsByTagName('tr').length)/2 ;
        controllerline(num);
    });

</script>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- Material Design fonts -->
<html>

<body>
<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:300,400,500,700" type="text/css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

<!-- Bootstrap -->
<link href="home/css/font-awesome.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="home/fancybox/jquery.fancybox.css" type="text/css" media="screen" />
<link href="home/css/animate.css" rel="stylesheet" type="text/css">

<!-- Bootstrap Material Design -->
<link href="home/css/material-design.css" rel="stylesheet">
<link href="home/css/ripples.min.css" rel="stylesheet">


<link href="home/css/snackbar.min.css" rel="stylesheet">

<!-- Custome Styles -->
<link href="home/css/style.css" rel="stylesheet" type="text/css">


<section id="hero_section" class="top_cont_outer">
<div class="hero_wrapper">
<div class="container">
  <div class="hero_section">
	<div class="row">
	  <div class="col-lg-5 col-sm-7">
		<div class="top_left_cont zoomIn wow animated">
            <div style="margin-left: 90px "><h2><strong>巨龙</strong> 图书馆</h2></div>
            <div style="width: 500px;margin-left: 90px "><p id="dragonLibraryIntro">巨龙图书馆成立于2019年4月21日，管理的图书高达50本,活跃用户20人；图书馆中囊括了大多数精品图书和电子书。</p></div>
            <div style="margin-left: 90px "><a href="javascript:0" class="btn btn-raised btn-lg">了解更多</a></div>
		</div>
      </div>
		<img src="home/img/booker1.png"  class="zoomIn wow animated" alt="" />
        <a onclick="javascript:$('.borrow').click();"  class="btn btn-effect scroll-link" style="position: absolute;left: 1280px;top: 60px;height:80px;width:230px;">
            <span  style="display: block;font-size: 20px;margin-top: 20px">借书</span><i class="icon-envelope"></i>
        </a>
        <a href="javascript:;" id="returnBook" class="btn btn-effect scroll-link" style="position: absolute;left: 1280px;top: 230px;height:80px;width:230px; " >
            <span  style="display: block;font-size: 20px;margin-top: 20px">还书</span><i class="icon-envelope"></i><i class="icon-envelope"></i>
        </a>
        <marquee id="affiche" align="right"  behavior="scroll"   loop="-1"  onMouseOut="this.start()" onMouseOver="this.stop()">
            <div id="scrollbar"  style="height:23px;font-size:16px" >${overTimeUsers}</div>
        </marquee>
	</div>
  </div>
</div>
</div>
</section>
<!--Hero_Section-->
<section id="aboutUs" class="companyInfo">
<div class="company-ever">
	<div class="container">
	  <div class="row company-bg">
		<div class="col-md-6">
		  <div class="company-thumb">
			<img src="home/img/bg1.png" alt="">
		  </div>
		</div>
		<div class="col-md-6">
		  <div class="content">
              <img src="home/img/rankList.png" height="40" width="40" align="center"><h2>排行榜</h2>
              <div class="company-thumb" style="width: 700px;height: 300px">
                  <table align="right" >
                      <tr >
                          <td width="40px"></td>
                          <td width="70px"><font size="4px">姓名</font></td>
                          <td width="200px"><font size="4px">部门</font></td>
                          <td width="150px"><font size="4px">借阅次数</font></td>
                      </tr>
                     <c:forEach items="${rankingListUsers}" var="list" varStatus="st">
                      <tr >
                          <c:if test="${st.count==1}">
                              <td ><img src="home/img/num1.png" height="40" width="40"></td>
                          </c:if>
                          <c:if test="${st.count==2}">
                              <td ><img src="home/img/num2.png" height="40" width="40"></td>
                          </c:if>
                          <c:if test="${st.count==3}">
                              <td ><img src="home/img/num3.png" height="40" width="40"></td>
                          </c:if>
                          <td ><font size="4px">${list.user.xm}</font></td>
                          <td ><font size="4px">${list.user.bm}</font></td>
                          <td ><font size="4px">${list.cs}</font></td>
                      </tr>
                     </c:forEach>
                  </table>
              </div>
		  </div>
		</div>
	  </div>
	</div>
	<!-- .container -->
  </div>



<!--Service-->
<section  id="service">
<div class="container">
<h2>热门图书</h2>
<div class="service_wrapper">

<c:forEach items="${hotBooks}" var="hot" varStatus="sta">
    <c:if test="${sta.count==1}">
    <div class="row">
    </c:if>
     <c:if test="${sta.count==4}">
     <div class="row borderTop">
      </c:if>
	<div class="col-lg-4 col-md-4 col-sm-4" >
	  <div class="service_block withripple" style="height:360px" >
          <td >
              <c:if test="${not empty hot.book.picture}">
                  <img id="hot${sta.count}" src="${hot.book.picture}" style="width: 200px;height: 178px;CURSOR: pointer" >
              </c:if>
          <c:if test="${empty  hot.book.picture}">
              <img id="hot${sta.count}" src="home/img/noPicture.png" style="width: 200px;height: 178px;CURSOR: pointer">
          </c:if>
          </td>
		<a id="hot${sta.count}" style="CURSOR: pointer" ><h3  class="animated fadeInUp wow">${hot.book.sm}</h3></a>
          <p id="hotIsbn${sta.count}" hidden>${hot.book.isbn}</p>
		<p    class="" style="height: 190px;overflow:hidden;">&nbsp;&nbsp;&nbsp;&nbsp;${hot.book.jj}</p>
	  </div>
	</div>
        <c:if test="${sta.count==3 or sta.count==6 }">
            </div>
            </c:if>
</c:forEach>

</div>
</section>
<!--Service-->


    <!--Service-->
    <section  id="service2">
        <div class="container">
            <h2>新书推荐</h2>
            <div class="service_wrapper">
                <c:forEach items="${newBooks}" var="new1" varStatus="sta1">
                <c:if test="${sta1.count==1}">
                <div class="row">
                    </c:if>
                    <c:if test="${sta1.count==4}">
                    <div class="row borderTop">
                        </c:if>
                        <div class="col-lg-4 col-md-4 col-sm-4" >
                            <div class="service_block withripple" style="height:360px">
                                <td >
                                    <c:if test="${not empty new1.book.picture}">
                                        <img id="new${sta1.count}" src="${new1.book.picture}" style="width: 200px;height: 178px;CURSOR: pointer">
                                    </c:if>
                                    <c:if test="${empty  new1.book.picture}">
                                        <img  id="new${sta1.count}" src="home/img/noPicture.png" style="width: 200px;height: 178px;CURSOR: pointer">
                                    </c:if>
                                </td>
                                <a id="new${sta1.count}" style="CURSOR: pointer" ><h3  class="animated fadeInUp wow">${new1.book.sm}</h3></a>
                                <p id="newIsbn${sta1.count}" hidden>${hot.book.isbn}</p>
                                <p  style="height: 190px;overflow:hidden;">&nbsp;&nbsp;&nbsp;&nbsp;${new1.book.jj}</p>
                            </div>
                        </div>
                        <c:if test="${sta1.count==3 or sta1.count==6 }">
                    </div>
                    </c:if>
                    </c:forEach>
            </div>
        </div>
        </div>
    </section>
    <!--Service-->
    <div class="footer_bottom"><span><font color="black">Copyright ©2015 厦门市巨龙信息科技有限公司 技术支持   建议使用1280*768及以上分辨率</font></span> </div>
</section>

<script type="text/javascript">

    requirejs(['jquery','dg.datePicker', 'artdialog'], function($) {
    //快捷还书方法
        $("#returnBook").click(function () {
            art.dialog.open('/return_book?userid='+${ user.userId}, {
                id:'returnMyBook',
                title: '快捷还书'
            });
        })
        //查看图书详情
        $("[id^=hot]").click(function () {
            var val=$(this).attr("id");
            var v2 = val.substring(3,val.length);//取后半部分
            var bookIsbn = "hotIsbn"+v2;
            alert(bookIsbn);
            <%--art.dialog.open('/return_book?userid='+${ user.userId}, {--%>
                <%--id:'bookDetail',--%>
                <%--title: '图书详情'--%>
            <%--});--%>
        })

        //查看图书详情
        $("[id^=new]").click(function () {
            var val=$(this).attr("id");
            var v2 = val.substring(3,val.length);//取后半部分
            var bookIsbn = "newIsbn"+v2;
            alert(bookIsbn);
            <%--art.dialog.open('/return_book?userid='+${ user.userId}, {--%>
            <%--id:'bookDetail',--%>
            <%--title: '图书详情'--%>
            <%--});--%>
        })
    });
</script>
</body>
</html>




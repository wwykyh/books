<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=utf-8"%>
<!-- Material Design fonts -->
<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:300,400,500,700" type="text/css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

<!-- Bootstrap -->
<%--<link href="home/css/bootstrap.min.css" rel="stylesheet">--%>
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
            <div style="width: 500px;margin-left: 90px "><p>巨龙图书馆成立于2019年4月21日，管理的图书高达50本；其中囊括了大多数精品图书。</p></div>
            <div style="margin-left: 90px "><a href="#service" class="btn btn-raised btn-lg">了解更多</a></div>
		</div>
      </div>
		<img src="home/img/booker1.png" class="zoomIn wow animated" alt="" />
        <marquee id="affiche" align="right"  behavior="scroll"   loop="-1"  onMouseOut="this.start()" onMouseOver="this.stop()">
            <font color=red size="4px" style="font-weight: bold" >大数据事业部周赵茂借的“Java编程思想”逾期未还，请尽快归还！！！</font>
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
                      <tr >
                          <td ><img src="home/img/num1.png" height="40" width="40"></td>
                          <td ><font size="4px">周赵茂</font></td>
                          <td ><font size="4px">大数据事业部</font></td>
                          <td ><font size="4px">16次</font></td>
                      </tr>
                      <tr s>
                          <td ><img src="home/img/num2.png" height="40" width="40"></td>
                          <td ><font size="4px">周赵茂</font></td>
                          <td ><font size="4px">QB事业部</font></td>
                          <td ><font size="4px">14次</font></td>
                      </tr>
                      <tr >
                          <td ><img src="home/img/num3.png" height="40" width="40"></td>
                          <td ><font size="4px">周赵茂</font></td>
                          <td ><font size="4px">保洁部</font></td>
                          <td ><font size="4px">12次</font></td>
                      </tr>
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
  <div class="row">
	<div class="col-lg-4 col-md-4 col-sm-4">
	  <div class="service_block withripple">
          <img src="home/img/noPicture.png" style="width: 200px;height: 178px">
		<h3 class="animated fadeInUp wow">创新与法制</h3>
		<p class="animated fadeInDown wow">这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介.</p>
	  </div>
	</div>
	<div class="col-lg-4 col-md-4 col-sm-4 borderLeft">
	  <div class="service_block withripple">
          <img src="home/img/noPicture.png" style="width: 200px;height: 178px">
          <h3 class="animated fadeInUp wow">创新与法制</h3>
          <p class="animated fadeInDown wow">这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介.</p>
	  </div>
	</div>
	<div class="col-lg-4 col-md-4 col-sm-4 borderLeft">
	  <div class="service_block withripple">
          <img src="home/img/noPicture.png" style="width: 200px;height: 178px">
          <h3 class="animated fadeInUp wow">创新与法制</h3>
          <p class="animated fadeInDown wow">这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介.</p>
	  </div>
	</div>
  </div>
   <div class="row borderTop">
	<div class="col-lg-4 col-md-4 col-sm-4 mrgTop">
	  <div class="service_block withripple">
          <img src="home/img/noPicture.png" style="width: 200px;height: 178px">
          <h3 class="animated fadeInUp wow">创新与法制</h3>
          <p class="animated fadeInDown wow">这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介.</p>
	  </div>
	</div>
	<div class="col-lg-4 col-md-4 col-sm-4 borderLeft mrgTop">
	  <div class="service_block withripple">
          <img src="home/img/noPicture.png" style="width: 200px;height: 178px">
          <h3 class="animated fadeInUp wow">创新与法制</h3>
          <p class="animated fadeInDown wow">这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介.</p>
	  </div>
	</div>
	<div class="col-lg-4 col-md-4 col-sm-4 borderLeft mrgTop">
	  <div class="service_block withripple">
          <img src="home/img/noPicture.png" style="width: 200px;height: 178px">
          <h3 class="animated fadeInUp wow">创新与法制</h3>
          <p class="animated fadeInDown wow">这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介.</p>
	  </div>
	</div>
  </div>
</div>
</div>
</section>
<!--Service-->


    <!--Service-->
    <section  id="service2">
        <div class="container">
            <h2>新书推荐</h2>
            <div class="service_wrapper">
                <div class="row">
                    <div class="col-lg-4 col-md-4 col-sm-4">
                        <div class="service_block withripple">
                            <img src="home/img/noPicture.png" style="width: 200px;height: 178px">
                            <h3 class="animated fadeInUp wow">创新与法制</h3>
                            <p class="animated fadeInDown wow">这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介.</p>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-4 col-sm-4 borderLeft">
                        <div class="service_block withripple">
                            <img src="home/img/noPicture.png" style="width: 200px;height: 178px">
                            <h3 class="animated fadeInUp wow">创新与法制</h3>
                            <p class="animated fadeInDown wow">这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介.</p>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-4 col-sm-4 borderLeft">
                        <div class="service_block withripple">
                            <img src="home/img/noPicture.png" style="width: 200px;height: 178px">
                            <h3 class="animated fadeInUp wow">创新与法制</h3>
                            <p class="animated fadeInDown wow">这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介.</p>
                        </div>
                    </div>
                </div>
                <div class="row borderTop">
                    <div class="col-lg-4 col-md-4 col-sm-4 mrgTop">
                        <div class="service_block withripple">
                            <img src="home/img/noPicture.png" style="width: 200px;height: 178px">
                            <h3 class="animated fadeInUp wow">创新与法制</h3>
                            <p class="animated fadeInDown wow">这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介.</p>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-4 col-sm-4 borderLeft mrgTop">
                        <div class="service_block withripple">
                            <img src="home/img/noPicture.png" style="width: 200px;height: 178px">
                            <h3 class="animated fadeInUp wow">创新与法制</h3>
                            <p class="animated fadeInDown wow">这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介.</p>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-4 col-sm-4 borderLeft mrgTop">
                        <div class="service_block withripple">
                            <img src="home/img/noPicture.png" style="width: 200px;height: 178px">
                            <h3 class="animated fadeInUp wow">创新与法制</h3>
                            <p class="animated fadeInDown wow">这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介.</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!--Service-->
    <div class="footer_bottom"><span><font color="black">Copyright ©2015 厦门市巨龙信息科技有限公司 技术支持   建议使用1280*768及以上分辨率</font></span> </div>

<%--<script>--%>
<%--$(function () {--%>
<%--$.material.init(); --%>
<%--});--%>
<%--</script>--%>

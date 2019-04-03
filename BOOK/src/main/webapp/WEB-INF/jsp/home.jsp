<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=utf-8"%>
<!-- Material Design fonts -->
<html>
<body>
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
            <div id="scrollbar"  style="height:23px;font-size:16px;color:red" >${overTimeUsers}&emsp;</div>
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
                          <td id="name1"><font size="4px">周赵茂${rankingListUsers}</font></td>
                          <td id="depart1"><font size="4px">大数据事业部</font></td>
                          <td id="times1"><font size="4px">16次</font></td>
                      </tr>
                      <tr >
                          <td ><img src="home/img/num2.png" height="40" width="40"></td>
                          <td id="name2"><font size="4px">周赵茂</font></td>
                          <td id="depart2"><font size="4px">QB事业部</font></td>
                          <td id="times2"><font size="4px">14次</font></td>
                      </tr>
                      <tr >
                          <td ><img src="home/img/num3.png" height="40" width="40"></td>
                          <td id="name3"><font size="4px">周赵茂</font></td>
                          <td id="depart3"><font size="4px">保洁部</font></td>
                          <td id="times3"><font size="4px">12次</font></td>
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
          <img id="bookCover1" src="home/img/noPicture.png" style="width: 200px;height: 178px">
		<h3 id="bookName1" class="animated fadeInUp wow">创新与法制</h3>
		<p id="intro1" class="animated fadeInDown wow">这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介.</p>
	  </div>
	</div>
	<div class="col-lg-4 col-md-4 col-sm-4 borderLeft">
	  <div class="service_block withripple">
          <img id="bookCover2" src="home/img/noPicture.png" style="width: 200px;height: 178px">
          <h3 id="bookName2" class="animated fadeInUp wow">创新与法制</h3>
          <p id="intro2" class="animated fadeInDown wow">这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介.</p>
	  </div>
	</div>
	<div class="col-lg-4 col-md-4 col-sm-4 borderLeft">
	  <div class="service_block withripple">
          <img id="bookCover3" src="home/img/noPicture.png" style="width: 200px;height: 178px">
          <h3 id="bookName3" class="animated fadeInUp wow">创新与法制</h3>
          <p id="intro3" class="animated fadeInDown wow">这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介.</p>
	  </div>
	</div>
  </div>
   <div class="row borderTop">
	<div class="col-lg-4 col-md-4 col-sm-4 mrgTop">
	  <div class="service_block withripple">
          <img id="bookCover4" src="home/img/noPicture.png" style="width: 200px;height: 178px">
          <h3 id="bookName4" class="animated fadeInUp wow">创新与法制</h3>
          <p id="intro4" class="animated fadeInDown wow">这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介.</p>
	  </div>
	</div>
	<div class="col-lg-4 col-md-4 col-sm-4 borderLeft mrgTop">
	  <div class="service_block withripple">
          <img id="bookCover5" src="home/img/noPicture.png" style="width: 200px;height: 178px">
          <h3 id="bookName5" class="animated fadeInUp wow">创新与法制</h3>
          <p id="intro5" class="animated fadeInDown wow">这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介.</p>
	  </div>
	</div>
	<div class="col-lg-4 col-md-4 col-sm-4 borderLeft mrgTop">
	  <div class="service_block withripple">
          <img id="bookCover6" src="home/img/noPicture.png" style="width: 200px;height: 178px">
          <h3 id="bookName6" class="animated fadeInUp wow">创新与法制</h3>
          <p id="intro6" class="animated fadeInDown wow">这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介.</p>
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
                            <img id="bookCover7" src="home/img/noPicture.png" style="width: 200px;height: 178px">
                            <h3 id="bookName7" class="animated fadeInUp wow">创新与法制</h3>
                            <p id="intro7" class="animated fadeInDown wow">这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介.</p>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-4 col-sm-4 borderLeft">
                        <div class="service_block withripple">
                            <img id="bookCover8" src="home/img/noPicture.png" style="width: 200px;height: 178px">
                            <h3 id="bookName8" class="animated fadeInUp wow">创新与法制</h3>
                            <p id="intro8" class="animated fadeInDown wow">这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介.</p>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-4 col-sm-4 borderLeft">
                        <div class="service_block withripple">
                            <img id="bookCover9" src="home/img/noPicture.png" style="width: 200px;height: 178px">
                            <h3 id="bookName9" class="animated fadeInUp wow">创新与法制</h3>
                            <p id="intro9" class="animated fadeInDown wow">这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介.</p>
                        </div>
                    </div>
                </div>
                <div class="row borderTop">
                    <div class="col-lg-4 col-md-4 col-sm-4 mrgTop">
                        <div class="service_block withripple">
                            <img id="bookCover10" src="home/img/noPicture.png" style="width: 200px;height: 178px">
                            <h3 id="bookName10" class="animated fadeInUp wow">创新与法制</h3>
                            <p id="intro10" class="animated fadeInDown wow">这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介.</p>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-4 col-sm-4 borderLeft mrgTop">
                        <div class="service_block withripple">
                            <img id="bookCover11" src="home/img/noPicture.png" style="width: 200px;height: 178px">
                            <h3 id="bookName11" class="animated fadeInUp wow">创新与法制</h3>
                            <p id="intro11" class="animated fadeInDown wow">这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介.</p>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-4 col-sm-4 borderLeft mrgTop">
                        <div class="service_block withripple">
                            <img id="bookCover12" src="home/img/noPicture.png" style="width: 200px;height: 178px">
                            <h3 id="bookName12" class="animated fadeInUp wow">创新与法制</h3>
                            <p id="intro12" class="animated fadeInDown wow">这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介这是简介.</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!--Service-->
    <div class="footer_bottom"><span><font color="black">Copyright ©2015 厦门市巨龙信息科技有限公司 技术支持   建议使用1280*768及以上分辨率</font></span> </div>
</section>

<script type="text/javascript">
    // requirejs(['jquery', 'ligerGrid','dg.datePicker', 'artdialog'], function($) {
    // $(document).ready(function (){
    //     $.ajax({
    //         cache: true,
    //         type: "GET",
    //         url:"/get_home",
    //         async: false,
    //         error: function(request) {
    //             alert("Connection error:"+request.error);
    //         },
    //         success: function(data) {
    //             if(data !=null){
    //                 $("#scrollbar").text(data);
    //             }
    //         }
    //     });
    // });
    //
    // });


    requirejs(['jquery', 'ligerGrid','dg.datePicker', 'artdialog'], function($) {
    //快捷还书方法
        $("#returnBook").click(function () {
            art.dialog.open('/return_book?userid='+${ user.userId}, {
                title: '快捷还书',
                width: 700,
                height: 350,
                cancel: true,
                cancelVal: "关闭"
            });
        })
    });
</script>
</body>
</html>




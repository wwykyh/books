function tBNav(options){
	var option = $.extend({
      	activeClass:"active", 
      	defultIndex:0
	}, options);
	var nav = $("#header").find(".top-nav");
	var subNav = $("#header").find(".top-sub-nav");
	var subNavItem = subNav.find(".sub-nav-item");
	var active = (option.activeClass) ? option.activeClass:'active';
	for(var i = 0, k = subNavItem.length; i < k; i++){
		if($(subNavItem[i]).find("li").length <= 0){
			$(subNavItem[i]).css({"height":"0px","border-bottom":"0"});
		}
	}
	if(option.defultIndex){
		$(nav.find("li")[option.defultIndex]).addClass(active);
		$(subNav.find(".sub-nav-item")[option.defultIndex]).find("li:first").addClass(active);
		$(subNav.find(".sub-nav-item")[option.defultIndex]).show().siblings(".sub-nav-item").hide();
	}
	nav.find("li").click( function(){
		var index = nav.find("li").index($(this));
		$(this).addClass(active).siblings().removeClass(active);
		$(subNav.find(".sub-nav-item")[index]).show().siblings(".sub-nav-item").hide();
	});
	subNav.on("click","li", function(){	// 二级菜单
		subNav.find("li").removeClass(active);
		$(this).addClass(active);
	});

	$.menuLink(subNav);	//菜单跳转
}

function miniside(options){
	var opts = $.extend({
      	active:"active",
      	hover:"hover",
      	defultIndex:0
	}, options);
	var $sidebar = $("#sidebar");
	if(!opts.defultIndex){
		$($sidebar.find("dt")[opts.defultIndex]).addClass(opts.active).next().find("li:first").addClass(opts.active);
	}
	$sidebar.on('mouseover','dt',function(event){
		if(isMouseLeaveOrEnter(event, this)){
			$(this).addClass(opts.hover).next().show();
			$("#sidebar").find("dd").not($(this).next()).hide();
			$("#sidebar").find("dt").not($(this)).removeClass(opts.hover);
		} 
	});
	$sidebar.on('mouseout','dt',function(event){
		if(isMouseLeaveOrEnter(event, this)){
			var dt = $(this), dd = dt.next();
			setTimeout(function(){
				if(!dd.hasClass("over")) {
					dt.removeClass(opts.hover);
					dd.hide();
				}
			},200);
		} 
	});
	$sidebar.on('mouseover','dd',function(event){
		if(isMouseLeaveOrEnter(event, this)){
			$(this).addClass('over');
		}
	});
	$sidebar.on('mouseout','dd',function(event){
		if(isMouseLeaveOrEnter(event, this)){
			$(this).removeClass('over').hide().prev("dt").removeClass(opts.hover);
		}
	});
	$sidebar.on('click','li',function(){
		$(this).addClass(opts.active).parents("dd").prev("dt").addClass(opts.active);
		$sidebar.find("li").not($(this)).removeClass(opts.active);
		$sidebar.find("dt").not($(this).parents("dd").prev("dt")).removeClass(opts.active);
	});
	$.menuLink($("#sidebar"));	//菜单跳转
}

function tlrMenu(options){
	var option = $.extend({
      	activeClass:"active", 
      	defultIndex:0
	}, options);
	var nav = $("#header").find(".main-nav");
	var sidebarNav = $("#sidebar").find(".sidebar-list");
	var active = (option.activeClass) ? option.activeClass:'active';
	var sidebarWidth = $("#sidebar").width();
	var sideMenuTarget = $(sidebarNav.find("dl")[option.defultIndex]);
	// 初始化
	if(!option.defultIndex){
		$(nav.find("li")[option.defultIndex]).addClass(active).siblings("li").removeClass(active);
		if(sideMenuTarget.find("dt").length <= 0){
			noSubMenu();
		}else{
			sideMenuTarget.show().siblings().hide();
			sidebarNav.find("dt." + active).removeClass(active);
			sidebarNav.find("li." + active).removeClass(active);
			sideMenuTarget.find("dt:first").addClass(active);
			sideMenuTarget.find("dd li:first").addClass(active);
		}
	}
	// 一级导航点击
	nav.find("li").click( function(){
		if($(this).find("a").attr('_href')){
			$(this).addClass(active).siblings().removeClass(active);
			var _href=$(this).find("a").attr('_href');
			$("#mainFrame").attr("src",_href);
			noSubMenu();
		}else{
			$(this).addClass(active).siblings().removeClass(active);
			if(sideMenuTarget.find("dt").length <= 0){
				noSubMenu();
			}else{
				var sideBarLe = $("#sidebar").position().left;
				var index = nav.find("li").index($(this));
				var indexTarget = $(sidebarNav.find("dl")[index]);
				$("#sidebar").show();
				$("#main").css("left",(sideBarLe >=0)?sidebarWidth:0);
				indexTarget.show().siblings().hide();
				indexTarget.find("li:first").addClass(active).find("a[_href]:first").click();
			}
		}
	});
	sidebarToggle();
	$.menuLink($("#sidebar"));	//菜单跳转
	
	function noSubMenu(){
		$("#sidebar").hide();
		$(".toggle-collapse").hide();
		$("#main").css("left","0px");
	}

	function sidebarToggle(){
		$(".toggle-collapse").click(function(event) {
			var clickObj = $(this);
			if(clickObj.hasClass("toggle-show")){
				$("#sidebar").animate({left:"0"});
				clickObj.animate({left:"200px"}).removeClass('toggle-show');
				$("#main").animate({left:"200px"});
			}else{
				$("#sidebar").animate({left:"-200px"});
				clickObj.animate({left:"0"}).addClass('toggle-show');
				$("#main").animate({left:"0"});
			}
		});
	}
}
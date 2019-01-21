/**
 * 时间轴控件
 */
define(['jquery'], function($) {
	/**
	 * 时间轴弹窗显示
	 */
	$.fn.extend({
		timeaxis: function(options) {
			var defaults = {
				data: {}, // 传输数据
				mainClass: 'history-date', // 样式类名 若修改，则引入新的样式，否则调用默认 
				menuContain: false, // 年份的导航
				loadingEffect: false, // 加载事件的效果
				twoSideShow: false, // 左右侧显示
			}
			// 参数继承
			var options = $.extend(defaults, options);
			var $container = $(this);

			// 初始化样式
			$container.css({ "position": "relative", "overflow": "hidden"});

			var Timeline = {
				// 初始化页面
				init: function() {
					// data 数据检测
					if(options.data) {
						if(typeof options.data != 'object') return;
						// 数据
						var content = options.data;
						// 内容标记 年份个数
						var t = 0;
						// 载入数据
						for(var i in content) {
							var boxs = content[i];
							var timeBar = createTimeBar(i);
							$container.append(timeBar);
							for(var j in boxs) {
								var box = createBox(boxs[j].title, boxs[j].relation, boxs[j].content, j);
								$container.find('.'+ options.mainClass+' ul').eq(t).append(box);
							}
							t++;
						}
						if(options.twoSideShow) {
							setInterval(function() {
								var subW = $('.' + options.mainClass).width() * 0.48 - 380;
								$(".s-left .substance").css('marginLeft', subW + 'px');
							}, 100);
						}
						
					}
					// 初始化滚动效果
					Timeline.scrollEffect();
				},
				// 加载滚动效果
				scrollEffect: function() {
					// 初始化
					var $warpEle = $("." + options.mainClass),
						$targetA = $warpEle.find("h2 a"),
						parentH = $warpEle.parent().height();

					// 初始化高度值
					$warpEle.parent().css({"height": 59});

					if(options.loadingEffect) {
						setTimeout(function() {
							// 渐入效果
							$warpEle.find("ul").children(":not('li:first')").each(function (idx) {
								// 隐藏
								$(this).css({ "margin-top":-80 }).children().hide();
							}).animate({ "margin-top":0 }, 1000).children().fadeIn();

							// 高度的改变 animate 特效
							$warpEle.parent().animate({ "height":parentH }, 2500);

							$warpEle.find("ul").children(":not('li:first')").addClass("bounceInDown").css({
								"-webkit-animation-duration":"2s",
								"-webkit-animation-delay":"0",
								"-webkit-animation-timing-function":"fast",
								"-webkit-animation-fill-mode":"both"
							}).end().children("h2").css({"position":"relative"});
						}, 300);
					} else {
						$warpEle.parent().css({ "height": parentH });
					}

					// 加入点击事件收缩
					$targetA.click(function () {
						$(this).parent().siblings().slideToggle("slow");
					});
				},
			}

			// 数据初始化
			Timeline.init();

			// 创建(DOM) Box 包含年份事件 
			function createBox(title, relation, content, time) {
				// 是否左右两侧显示
				if(options.twoSideShow) {
					if(time % 2 == 0) {
						time = 's-right';
					} else {
						time = 's-left';
					}
				} else {
					time = '';
				}
				return '<li class="'+ time +'">'+
							'<h3>10.08<span>2012</span></h3>'+
							'<div class="substance">'+
								'<div class="subBox">'+
									'<div class="timeTitle">'+ title +
										'<div class="gxfx">'+ relation +'</div>'+
									'</div>'+
									'<div class="timetext">'+ content +'</div>'+
								'</div>'+
							'</div>'+
						'</li>';
			}

			// 创建(DOM) container
			function createTimeBar(time) {

				// 是否左右两侧显示
				if(options.twoSideShow) options.mainClass = 'history-center';

				// 年份导航是否开启
				if(options.menuContain) {
					if(!time) time = "未知时间";
					return '<div class="'+ options.mainClass +'">'+
								'<h2><a href="javascript:;">' + time + '</a></h2>'+
								'<ul></ul>'+
							'</div>';
				} else {
					return '<div class="'+ options.mainClass +'">'+
								'<h2></h2>'+
								'<ul></ul>'+
							'</div>';
				} 
			}
		},
	});
});
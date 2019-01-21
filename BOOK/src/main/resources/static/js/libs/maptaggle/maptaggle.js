$(function() {
	
	$.fn.extend({
		// map-taggle 地图标识轮播
		mapTaggle: function(options) {
			var defaults = {
				width: 620, // 宽度
				height: 400, // 高度
				data: [
					{
						position: [ // 各区的位置
							{'alerted': Math.ceil(Math.random() * 1000), 'surpervisory': Math.ceil(Math.random() * 1000), 'top': 25, 'left': 202},
							{'alerted': Math.ceil(Math.random() * 1000), 'surpervisory': Math.ceil(Math.random() * 1000), 'top': 105, 'left': 192},
							{'alerted': Math.ceil(Math.random() * 1000), 'surpervisory': Math.ceil(Math.random() * 1000), 'top': 130, 'left': 312},
							{'alerted': Math.ceil(Math.random() * 1000), 'surpervisory': Math.ceil(Math.random() * 1000), 'top': 125, 'left': 32},
							{'alerted': Math.ceil(Math.random() * 1000), 'surpervisory': Math.ceil(Math.random() * 1000), 'top': 158, 'left': 372},
							{'alerted': Math.ceil(Math.random() * 1000), 'surpervisory': Math.ceil(Math.random() * 1000), 'top': 151, 'left': 102},
							{'alerted': Math.ceil(Math.random() * 1000), 'surpervisory': Math.ceil(Math.random() * 1000), 'top': 170, 'left': 192},
							{'alerted': Math.ceil(Math.random() * 1000), 'surpervisory': Math.ceil(Math.random() * 1000), 'top': 190, 'left': 292},
							{'alerted': Math.ceil(Math.random() * 1000), 'surpervisory': Math.ceil(Math.random() * 1000), 'top': 177, 'left': 42},
							{'alerted': Math.ceil(Math.random() * 1000), 'surpervisory': Math.ceil(Math.random() * 1000), 'top': 197, 'left': 177},
							{'alerted': Math.ceil(Math.random() * 1000), 'surpervisory': Math.ceil(Math.random() * 1000), 'top': 215, 'left': 12},
							{'alerted': Math.ceil(Math.random() * 1000), 'surpervisory': Math.ceil(Math.random() * 1000), 'top': 237, 'left': 177}
						],
						name: [ // 各区的名称
							'蓟县',
							'宝坻区',
							'宁河县',
							'武清区',
							'汉沽区',
							'北辰区',
							'东丽区',
							'塘沽区',
							'西青区',
							'津南区',
							'静海县',
							'大港区'
						],
						map: [ // 地图各区的数据
							'js/libs/maptaggle/images/tianjin/tjjx.png', 
							'js/libs/maptaggle/images/tianjin/tjbd.png', 
							'js/libs/maptaggle/images/tianjin/tjnh.png', 
							'js/libs/maptaggle/images/tianjin/tjwq.png', 
							'js/libs/maptaggle/images/tianjin/tjhg.png', 
							'js/libs/maptaggle/images/tianjin/tjbc.png', 
							'js/libs/maptaggle/images/tianjin/tjdl.png', 
							'js/libs/maptaggle/images/tianjin/tjtg.png', 
							'js/libs/maptaggle/images/tianjin/tjxq.png', 
							'js/libs/maptaggle/images/tianjin/tjjn.png', 
							'js/libs/maptaggle/images/tianjin/tjjh.png', 
							'js/libs/maptaggle/images/tianjin/tjdg.png'
						],
						spot: [ // 光点分布位置
							{
								'num': [
									{'color': 'greendot', 'top': 13, 'left': 10},
									{'color': 'greendot', 'top': 2, 'left': 50},
									{'color': 'greendot', 'top': 36, 'left': 43},
									{'color': 'greendot', 'top': 42, 'left': 10},
									{'color': 'yellowdot', 'top': 29, 'left': 14},
									{'color': 'yellowdot', 'top': 5, 'left': 27},
									{'color': 'yellowdot', 'top': 46, 'left': 14},
									{'color': 'yellowdot', 'top': 47, 'left': 2}
								]
							}, {
								'num': [
									{'color': 'greendot', 'top': 10, 'left': 9},
									{'color': 'greendot', 'top': 24, 'left': 8},
									{'color': 'greendot', 'top': 1, 'left': 3},
									{'color': 'greendot', 'top': 16, 'left': 9},
									{'color': 'yellowdot', 'top': 28, 'left': 7},
									{'color': 'yellowdot', 'top': 31, 'left': 4},
									{'color': 'yellowdot', 'top': 1, 'left': 40},
									{'color': 'yellowdot', 'top': 47, 'left': 1}
								]
							}, {
								'num': [
									{'color': 'greendot', 'top': 29, 'left': 19},
									{'color': 'greendot', 'top': 43, 'left': 47},
									{'color': 'greendot', 'top': 32, 'left': 23},
									{'color': 'greendot', 'top': 19, 'left': 25},
									{'color': 'yellowdot', 'top': 1, 'left': 15},
									{'color': 'yellowdot', 'top': 8, 'left': 32},
									{'color': 'yellowdot', 'top': 31, 'left': 25},
									{'color': 'yellowdot', 'top': 32, 'left': 27}
								]
							}, {
								'num': [
									{'color': 'greendot', 'top': 11, 'left': 11},
									{'color': 'greendot', 'top': 39, 'left': 40},
									{'color': 'yellowdot', 'top': 17, 'left': 9},
									{'color': 'yellowdot', 'top': 3, 'left': 44}
								]
							}, {
								'num': [
									{'color': 'greendot', 'top': 33, 'left': 45},
									{'color': 'greendot', 'top': 34, 'left': 3},
									{'color': 'yellowdot', 'top': 13, 'left': 49},
									{'color': 'yellowdot', 'top': 26, 'left': 22}
								]
							}, {
								'num': [
									{'color': 'greendot', 'top': 31, 'left': 25},
									{'color': 'greendot', 'top': 16, 'left': 42}
								]
							}, {
								'num': [
									{'color': 'greendot', 'top': 13, 'left': 45},
									{'color': 'greendot', 'top': 6, 'left': 19}
								]
							}, {
								'num': [
									{'color': 'greendot', 'top': 24, 'left': 16},
									{'color': 'greendot', 'top': 20, 'left': 29}
								]
							}, {
								'num': [
									{'color': 'greendot', 'top': 2, 'left': 48},
									{'color': 'greendot', 'top': 25, 'left': 32}
								]
							}, {
								'num': [
									{'color': 'greendot', 'top': 22, 'left': 17},
									{'color': 'greendot', 'top': 50, 'left': 20}
								]
							}, {
								'num': [
									{'color': 'greendot', 'top': 41, 'left': 46},
									{'color': 'greendot', 'top': 48, 'left': 40}
								]
							}, {
								'num': [
									{'color': 'greendot', 'top': 40, 'left': 40},
									{'color': 'greendot', 'top': 27, 'left': 9}
								]
							},
						]
					}, {
						position: [ // 各区的位置
							{'alerted': Math.ceil(Math.random() * 1000), 'surpervisory': Math.ceil(Math.random() * 1000), 'top': 25, 'left': 42},
							{'alerted': Math.ceil(Math.random() * 1000), 'surpervisory': Math.ceil(Math.random() * 1000), 'top': 25, 'left': 202},
							{'alerted': Math.ceil(Math.random() * 1000), 'surpervisory': Math.ceil(Math.random() * 1000), 'top': 115, 'left': 262},
							{'alerted': Math.ceil(Math.random() * 1000), 'surpervisory': Math.ceil(Math.random() * 1000), 'top': 205, 'left': 222},
							{'alerted': Math.ceil(Math.random() * 1000), 'surpervisory': Math.ceil(Math.random() * 1000), 'top': 145, 'left': 42},
							{'alerted': Math.ceil(Math.random() * 1000), 'surpervisory': Math.ceil(Math.random() * 1000), 'top': 125, 'left': 152},
						],
						name: [ // 各区的名称
							'红桥区',
							'河北区',
							'河东区',
							'河西区',
							'南开区',
							'和平区'
						],
						map: [ // 地图各区的数据
							'js/libs/maptaggle/images/tianjin/tjhq.png', 
							'js/libs/maptaggle/images/tianjin/tjhb.png', 
							'js/libs/maptaggle/images/tianjin/tjhd.png', 
							'js/libs/maptaggle/images/tianjin/tjhx.png', 
							'js/libs/maptaggle/images/tianjin/tjnk.png', 
							'js/libs/maptaggle/images/tianjin/tjhp.png'
						],
						spot: [ // 光点分布位置
							{
								'num': [
									{'color': 'greendot', 'top': 12, 'left': 47},
									{'color': 'greendot', 'top': 10, 'left': 27}
								]
							}, {
								'num': [
									{'color': 'greendot', 'top': 24, 'left': 13},
									{'color': 'greendot', 'top': 13, 'left': 47}
								]
							}, {
								'num': [
									{'color': 'greendot', 'top': 23, 'left': 42},
									{'color': 'greendot', 'top': 44, 'left': 10}
								]
							}, {
								'num': [
									{'color': 'greendot', 'top': 12, 'left': 50},
									{'color': 'greendot', 'top': 2, 'left': 22}
								]
							}, {
								'num': [
									{'color': 'greendot', 'top': 32, 'left': 16},
									{'color': 'greendot', 'top': 4, 'left': 5}
								]
							}, {
								'num': [
									{'color': 'greendot', 'top': 44, 'left': 25},
									{'color': 'greendot', 'top': 26, 'left': 14}
								]
							}
						]
					}
				],
				iSlide: true, // 是否轮播
				slideDelay: 6000, // 延迟时间轮播 默认为6s
				mapExchange: function() { // 地图切换时触发

				}
			}
			// 参数继承
			var opts = $.extend(defaults, options);
			var $container = $(this);
			var timeSlide;
			$container.css({'width': opts.width + 'px', 'height': opts.height + 'px'});
			// 初始化
			_initHtml();

			// 是否轮播
			if(opts.iSlide) {
				timeSlide = setInterval(_initHtml, opts.slideDelay);
			}

			// 通过数据来显示地图及定位位置
			// 构造DOM 去展示
			function _initHtml() {
				// 初始化
				var data = opts.data;

				if(data) {
					if($container.find('.map-box').length == 0) {
						for(var j in data) {
							// 地图块
							var $map = $("<div class='map-box'></div>").appendTo($container);
							// 数据初始化
							var map = data[j].map,
								position = data[j].position,
								name = data[j].name,
								spot = data[j].spot,

							// 地图数据
							$mapUl = $("<ul class='map-bg'></ul>").appendTo($map);
							for(var i in map) {
								$mapUl.append("<li><img src='"+ map[i]+"' /></li>");
							}

							// 各区的位置
							$positionUl = $("<ul class='map-position'></ul>").appendTo($map);
							for(var i in position) {
								$positionLi = $("<li>预警数：<span class='yellow'>"+ position[i]['alerted'] +"</span>&nbsp;&nbsp;"+
									"&nbsp;&nbsp;临控数：<span class='yellow'>"+ position[i]['surpervisory'] +"</li>").appendTo($positionUl);
								// 进行定位
								$positionLi.css({'top': position[i]['top'] + 'px', 'left': position[i]['left'] + 'px'});
							}

							// 各区的名称
							$nameUl = $("<ul class='map-name'></ul>").appendTo($map);
							for(var i in name) {
								$nameLi = $("<li>"+ name[i] +"</li>").appendTo($nameUl);
								$nameLi.css({'top': position[i]['top'] + 110 + 'px', 'left': position[i]['left'] + 100 + 'px'});
							}

							// 各区的光点
							$spotUl = $("<ul class='map-spot'></ul>").appendTo($map);
							for(var i in spot) {
								$spotLi = $("<li><div class='spot-box'></div></li>").appendTo($spotUl);
								$spotLi.css({'top': position[i]['top'] + 110 + 'px', 'left': position[i]['left'] + 100 + 'px'});
								var num = spot[i]['num'];
								for(var t in num) {
									$spotSpan = $("<span class='"+ num[t]['color'] +"'></span>").appendTo($spotLi.find('.spot-box'));
									$spotSpan.css({'top': num[t]['top'] + '%', 'left': num[t]['left'] + '%'});
								}
							}
						}
						// 文档初始化
						$container.find('.map-box:first').addClass('active').find('.map-bg li:first').addClass('active');
						$container.find('.map-box:first').find('.map-position li:first').addClass('active');
					} else {
						// 进行切换
						var $mapBoxActive = $container.find(".map-box.active");
						if($mapBoxActive.find(".map-position li:last").hasClass('active')) {
							$mapBoxActive.find(".map-position li:last").removeClass('active');
							$mapBoxActive.find(".map-bg li:last").removeClass('active');
							// 判断是否多张地图
							if($mapBoxActive.next('.map-box').length > 0) {
								$mapBoxActive.removeClass('active').next('.map-box').addClass('active');
							} else {
								$mapBoxActive.removeClass('active');
								$container.find(".map-box:first").addClass('active');
							}
							$container.find(".map-box.active").find(".map-position li:first").addClass('active');
							$container.find(".map-box.active").find(".map-bg li:first").addClass('active');
							// 地图切换时触发函数
							opts.mapExchange();
						} else {
							$mapBoxActive.find(".map-bg li.active").removeClass('active').next('li').addClass('active');
							$mapBoxActive.find(".map-position li.active").removeClass('active').next('li').addClass('active');
						}
					}
				}
			}
		}
	});
});
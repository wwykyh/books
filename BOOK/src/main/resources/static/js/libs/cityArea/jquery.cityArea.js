$(function() {
	$.fn.extend({
		cityArea: function(options) {
			var defaults = {
				width: 450, // 宽度控制
				url: './js/libs/location/cityData.json', // 获取数据
			}

			// 参数继承
            var opts = $.extend(defaults, options);
            var $container = $(this);

            // 点击时触发构造函数
            $container.on('click', function() {
            	// 构造DOM
            	if($("#cityArea").length == 0) {
            		selCity($(this));
            	}
            });

            // 构造DOM
            function selCity(obj) {
            	// 初始化
            	var $this = obj;
			    var dal =	'<div id="cityArea" class="_citys">'+
								'<span title="关闭" class="cColse" >×</span>'+
								'<ul class="_citys0">'+
									'<li class="active">省份</li>'+
									'<li>城市</li>'+
									'<li>区县</li>'+
								'</ul>'+
								'<div id="_citys0" class="_citys1"></div>'+
								'<div id="_citys1" class="_citys1" style="display:none"></div>'+
								'<div id="_citys2" class="_citys1" style="display:none"></div>'+
							'</div>';
				// 进行定位
				$("body").append(dal);
				$("#cityArea").css({
					"position":"absolute",
					"z-index":"100",
					"width": opts.width,
					"top": $this.offset().top + $this.outerHeight() + 3,
					"left": $this.offset().left - 3
				});
				// 构造省市区数据
				$.getJSON(opts.url, function(data) {
					// 省级数据
					$.each(data, function(i, val) {
						if(val.item_code.substr(2, 4) == '0000') {
							$("#_citys0").append('<a data-level="0" data-code="'+val.item_code+'" data-name="' + val.item_name + '">'+val.item_name+'</li>');
						}
					});

					// 绑定事件
					$("#_citys0 a").on('click', function() {
						// 进行状态变化
						var province_name = $(this).data('name');
						var province_code = $(this).data('code');
						$("#cityArea ._citys0 li").eq(0).text(province_name);
						//  清空改变的数据
						$("#cityArea ._citys0 li").eq(1).text('城市');
						$("#cityArea ._citys0 li").eq(2).text('区县');
						$this.siblings('.municipa-box').remove();
						$this.siblings('.district-box').remove();
						$("#_citys2").empty();

						if($this.siblings('.province-box').length == 0) {
							var hcitys = $('<input>', {
								"type": 'hidden',
								"name": "province",
								"data-code": province_code,
								"class": "province-box",
								"val": province_name
							});
							$this.after(hcitys);
						} else {
							$this.siblings('.province-box').data('code', province_code).val(province_name);
						}
						$this.val(province_name);

						// 市级数据
						$("#cityArea ._citys0 li").removeClass('active').eq(1).addClass('active');
						$("#cityArea ._citys1").hide().eq(1).show();

						if(province_code) {
							$("#_citys1").empty();
							$.each(data, function(i, val) {
								if(val.item_code.substr(0, 2) == province_code.toString().substr(0, 2) && val.item_code.substr(2, 4) != '0000' && val.item_code.substr(4, 2) == '00') {
									$("#_citys1").append('<a data-level="1" data-code="'+val.item_code+'" data-name="' + val.item_name + '">'+val.item_name+'</li>');
								}
							});
						}

						// 绑定事件
						$("#_citys1 a").on('click', function() {
							// 进行状态变化
							var municipa_name = $(this).data('name');
							var municipa_code = $(this).data('code');
							// 清空改变的数据
							$("#cityArea ._citys0 li").eq(2).text('区县');
							$this.siblings('.district-box').remove();

							$("#cityArea ._citys0 li").eq(1).text(municipa_name);
							if($this.siblings('.municipa-box').length == 0) {
								var hcitys = $('<input>', {
									"type": 'hidden',
									"name": "municipa",
									"data-code": municipa_code,
									"class": "municipa-box",
									"val": municipa_name
								});
								$this.after(hcitys);
							} else {
								$this.siblings('.municipa-box').data('code', municipa_code).val(municipa_name);
							}
							$this.val(province_name + '-' + municipa_name);

							// 区级数据
							$("#cityArea ._citys0 li").removeClass('active').eq(2).addClass('active');
							$("#cityArea ._citys1").hide().eq(2).show();

							if(municipa_code) {
								$("#_citys2").empty();
								$.each(data,
									function(i, val) {
									if (municipa_code == '110100' || municipa_code == "120100" || municipa_code == "310100" || municipa_code == "500100") {
										if (val.item_code.substr(0, 3) == municipa_code.toString().substr(0, 3) && val.item_code.substr(4, 2) != '00') {
											$("#_citys2").append('<a data-level="2" data-code="'+val.item_code+'" data-name="' + val.item_name + '">'+val.item_name+'</li>');
										}
									} else {
										if (val.item_code.substr(0, 4) == municipa_code.toString().substr(0, 4) && val.item_code.substr(4, 2) != '00') {
											$("#_citys2").append('<a data-level="2" data-code="'+val.item_code+'" data-name="' + val.item_name + '">'+val.item_name+'</li>');
										}
									}
								});
							}

							// 绑定事件
							$("#_citys2 a").on('click', function() {
								// 进行状态变化
								var district_name = $(this).data('name');
								var district_code = $(this).data('code');
								$("#cityArea ._citys0 li").eq(1).text(district_name);
								if($this.siblings('.district-box').length == 0) {
									var hcitys = $('<input>', {
										"type": 'hidden',
										"name": "district",
										"data-code": district_code,
										"class": "district-box",
										"val": district_name
									});
									$this.after(hcitys);
								} else {
									$this.siblings('.district-box').data('code', district_code).val(district_name);
								}
								$this.val(province_name + '-' + municipa_name + '-' + district_name);

								// 关闭
								$("#cityArea").remove();
							});
						})
					})

				});
				// 进行省市区切换
				$("#cityArea ._citys0 li").on('click', function() {
					var index = $("#cityArea ._citys0 li").index(this);
					// 标题切换
					$("#cityArea ._citys0 li").removeClass("active").eq(index).addClass("active");
					// 内容切换
					$("#cityArea ._citys1").hide().eq(index).show();
				});
				// 关闭窗口
				$("#cityArea .cColse").on('click', function() {
					$("#cityArea").remove();
				});
				// 点击其他地方关闭窗口
				$(document).off('click').on('click', function(e) {
					var $current = $(e.target);
					if($current.closest($container.selector).length == 0 ) {
						if($current.closest('#cityArea').length == 0) {
							$("#cityArea").remove();
						}
					}
				});	
            }
		}
	});
});
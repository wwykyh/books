(function($) {
	$.extend({
		/**
		 * 省市区三级联动选择框 DIV
		 * @param  {[type]} options [description]
		 * @return {[type]}         [description]
		 */
		cityContract: function(options) {
			var defaults = {
				provinceSelector: '#province', // 省级
				municipaSelector: '#municipa', // 市级
				districtSelector: "#district", // 区级
				themeStyle: "theme-style", // 样式主题
				isDefaultFirst: false, // 是否默认显示第一项 默认为false 不显示
				isFixListHeight: false, // 是否固定列表的高度 默认为 false
				listBoxHeight: 180, // 固定列表的默认高度
				url: './cityData.json' // 数据路径
			}
			// 参数继承
			var opts = $.extend({}, defaults, options);
			// 初始化
			var $provinceSel = $(opts.provinceSelector);
			var $municipaSel = $(opts.municipaSelector);
			var $districtSel = $(opts.districtSelector);

			// 获取城市数据
			$.getJSON(opts.url, function(data) {
				// console.log(data.length);
			
				// 初始化数据
				var textProvince = $provinceSel.attr('rel');
				var textMunicipa = $municipaSel.attr('rel');
				var textDistrict = $districtSel.attr('rel');
				$provinceSel.addClass(opts.themeStyle)
						.append('<div class="data-btn">'+
									'<span class="text">'+ textProvince +'</span>'+
									'<i class="arrow"></i>'+
								'</div>'+
								'<div class="data-list" style="display: none; height: '+ opts.listBoxHeight +'px">'+
									'<ul>'+
										'<li>'+textProvince+'</li>'+
									'</ul>'+
								'</div>');
				$municipaSel.addClass(opts.themeStyle)
						.append('<div class="data-btn">'+
									'<span class="text">'+ textMunicipa +'</span>'+
									'<i class="arrow"></i>'+
								'</div>'+
								'<div class="data-list" style="display: none; height: '+ opts.listBoxHeight +'px">'+
									'<ul>'+
										'<li>'+textMunicipa+'</li>'+
									'</ul>'+
								'</div>');
				$districtSel.addClass(opts.themeStyle)
						.append('<div class="data-btn">'+
									'<span class="text">'+ textDistrict +'</span>'+
									'<i class="arrow"></i>'+
								'</div>'+
								'<div class="data-list" style="display: none; height: '+ opts.listBoxHeight +'px">'+
									'<ul>'+
										'<li>'+ textDistrict +'</li>'+
									'</ul>'+
								'</div>');
				// 省级数据
				$.each(data, function(i, val) {
					if(val.item_code.substr(2, 4) == '0000') {
						$provinceSel.find('ul').append('<li data-code="'+val.item_code+'">'+val.item_name+'</li>');
					}
				});

				// 是否默认显示第一项
				if(opts.isDefaultFirst) {
					var $firstProvince = $provinceSel.find('li').eq(1);
					var firstText = $firstProvince.text();
					var firstCode = $firstProvince.data('code');
					$provinceSel.find(".data-btn .text").text(firstText);
					$provinceSel.find(".data-btn .text").data('code', firstCode);
				}

				// 市级数据
				selectedProvinceData(data, true);

				// 区级数据
				selectedMunicipaData(data, true);

				// 操作数据
				operateData(data);


			});

			// 市级数据
			function selectedProvinceData(data, tolerate) {
				var provinceVal = $provinceSel.find('.text').data('code');
				if(provinceVal) {
					// 市级数据
					$municipaSel.find('ul').empty();
					$municipaSel.find('ul').append('<li>'+ $municipaSel.attr('rel') +'</li>');
					$municipaSel.find('.text').removeData('code').text($districtSel.attr('rel'));
					$.each(data, function(i, val) {
						if(val.item_code.substr(0, 2) == provinceVal.toString().substr(0, 2) && val.item_code.substr(2, 4) != '0000' && val.item_code.substr(4, 2) == '00') {
							$municipaSel.find('ul').append('<li data-code="'+val.item_code+'">'+val.item_name+'</li>');
						}
					});
					// 是否首次进入
					if(tolerate) {
						// 是否默认显示第一项
						if(opts.isDefaultFirst) {
							var $firstMunicipa = $municipaSel.find('li').eq(1);
							var firstText = $firstMunicipa.text();
							var firstCode = $firstMunicipa.data('code');
							$municipaSel.find(".data-btn .text").text(firstText);
							$municipaSel.find(".data-btn .text").data('code', firstCode);
						}
					}

					// 是否固定列表的高度
					if(!opts.isFixListHeight) {
						var liHeight = $municipaSel.find('li:first').height();
						var liNum = $municipaSel.find('li').length;

						if(opts.listBoxHeight > liHeight * liNum) {
							$municipaSel.find('.data-list').css('height','auto');
						} else {
							$municipaSel.find('.data-list').css('height', opts.listBoxHeight + 'px');
						}
					}
				} else {
					// 市级数据
					$municipaSel.find('ul').empty();
					$municipaSel.find('ul').append('<li>'+ $municipaSel.attr('rel') +'</li>');
					$municipaSel.find('.text').removeData('code').text($districtSel.attr('rel'));
					// 是否固定列表的高度
					if(!opts.isFixListHeight) {
						$municipaSel.find('.data-list').css('height','auto');
					}
				}

				// 绑定事件
				operateData(data);
			}

			// 区级数据
			function selectedMunicipaData(data, tolerate) {
				var municipaVal = $municipaSel.find('.text').data('code');
				if(municipaVal) {
					// 区级数据
					$districtSel.find('ul').empty();
					$districtSel.find('ul').append('<li>'+ $districtSel.attr('rel') +'</li>');
					$districtSel.find('.text').removeData('code').text($districtSel.attr('rel'));
					$.each(data,
						function(i, val) {
						if (municipaVal == '110100' || municipaVal == "120100" || municipaVal == "310100" || municipaVal == "500100") {
							if (val.item_code.substr(0, 3) == municipaVal.toString().substr(0, 3) && val.item_code.substr(4, 2) != '00') {
								$districtSel.find('ul').append('<li data-code="'+val.item_code+'">'+val.item_name+'</li>');
							}
						} else {
							if (val.item_code.substr(0, 4) == municipaVal.toString().substr(0, 4) && val.item_code.substr(4, 2) != '00') {
								$districtSel.find('ul').append('<li data-code="'+val.item_code+'">'+val.item_name+'</li>');
							}
						}
					});
					// 是否首次进入
					if(tolerate) {
						// 是否默认显示第一项
						if(opts.isDefaultFirst) {
							var $firstDistrict = $districtSel.find('li').eq(1);
							var firstText = $firstDistrict.text();
							var firstCode = $firstDistrict.data('code');
							$districtSel.find(".data-btn .text").text(firstText);
							$districtSel.find(".data-btn .text").data('code', firstCode);
						}
					}
					// 是否固定列表的高度
					if(!opts.isFixListHeight) {
						var liHeight = $districtSel.find('li:first').height();
						var liNum = $districtSel.find('li').length;

						if(opts.listBoxHeight > liHeight * liNum) {
							$districtSel.find('.data-list').css('height','auto');
						} else {
							$districtSel.find('.data-list').css('height', opts.listBoxHeight + 'px');
						}
					}
				} else {
					// 区级数据
					$districtSel.find('ul').empty();
					$districtSel.find('ul').append('<li>'+ $districtSel.attr('rel') +'</li>');
					$districtSel.find('.text').removeData('code').text($districtSel.attr('rel'));
					// 是否固定列表的高度
					if(!opts.isFixListHeight) {
						$districtSel.find('.data-list').css('height','auto');
					}
				}

				// 绑定事件
				operateData(data);
			}

			// 绑定事件
			function operateData(data) {
				// 绑定事件
				$(opts.provinceSelector + ", " + opts.municipaSelector + ", " + opts.districtSelector).find(".data-btn").off('click').on('click', function() {
					var $dataList = $(this).next(".data-list");
					if($dataList.is(":visible")){
						$dataList.hide();
					}else{
						$("." + opts.themeStyle).find(".data-list").hide();
						$dataList.show();
					}
				});

				// 获取省级区域值
				$(opts.provinceSelector).find('li').off('click').on('click',function() {
					var code_text = $(this).data('code');
					var name_text = $(this).text();
					if(code_text) {
						$(this).parents('.data-list').siblings('.data-btn').find('.text').data('code', code_text).text(name_text);
					} else {
						$(this).parents('.data-list').siblings('.data-btn').find('.text').removeData('code').text(name_text);
					}
					$(this).parents('.data-list').css('display', 'none');

					// 市级数据
					selectedProvinceData(data);

					// 区级数据
					selectedMunicipaData(data);
				});

				// 获取市级区域值
				$(opts.municipaSelector).find('li').off('click').on('click',function() {
					var code_text = $(this).data('code');
					var name_text = $(this).text();
					if(code_text) {
						$(this).parents('.data-list').siblings('.data-btn').find('.text').data('code', code_text).text(name_text);
					} else {
						$(this).parents('.data-list').siblings('.data-btn').find('.text').removeData('code').text(name_text);
					}
					$(this).parents('.data-list').css('display', 'none');

					// 区级数据
					selectedMunicipaData(data);
				});

				// 获取区级区域值
				$(opts.districtSelector).find('li').off('click').on('click',function() {
					var code_text = $(this).data('code');
					var name_text = $(this).text();
					if(code_text) {
						$(this).parents('.data-list').siblings('.data-btn').find('.text').data('code', code_text).text(name_text);
					} else {
						$(this).parents('.data-list').siblings('.data-btn').find('.text').removeData('code').text(name_text);
					}
					$(this).parents('.data-list').css('display', 'none');
				});
			}

			// 点击空白处隐藏
			$(document).bind("click",function(e){ 
				var target = $(e.target); 
				if(target.closest(".theme-style").length == 0){ 
					$(".data-list").hide(); 
				} 
			});
			
		}
	});
})(jQuery);
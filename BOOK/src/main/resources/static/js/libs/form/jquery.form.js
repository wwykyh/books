/**
 * form 表单样式 + 表单验证 ( override + validate )
 * @author DaMin
 */
;$(function() {
	'use strict';
	// 验证规则列表
	var _verify = {
		required: [/[\S]+/, "必填项不能为空"],
		phone: [/^1\d{10}$/, "请输入正确的手机号"],
		email: [/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/, "邮箱格式不正确"],
		url: [/(^#)|(^http(s*):\/\/[^\s]+\.[^\s]+)/, "链接格式不正确"],
		number: [/^\d+$/, "只能填写数字"],
		date: [/^(\d{4})[-\/](\d{1}|0\d{1}|1[0-2])([-\/](\d{1}|0\d{1}|[1-2][0-9]|3[0-1]))*$/, "日期格式不正确"],
		identity: [/(^\d{15}$)|(^\d{17}(x|X|\d)$)/, "请输入正确的身份证号"],
		A_b_8_20: [/(?![0-9A-Z]+$)(?![0-9a-z]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,20}$/, "请输入8-20位同时包含大小写字母和数字"],
		Ab_8_20: [/(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,20}$/, "请输入8-20位同时包含字母和数字"],

	}

	// 提示方式
	var _tips = [
		'lineTips', // 单行提醒
		'layerTips', // 居中浮层显示
		'otherTips', // 第三方浮层调用
		'suspendTips' // 悬浮框提醒
	]

	$.fn.extend({
		/**
		 * 表单验证
		 * @param showTipsType 消息提醒方式 lineTips/layerTips/otherTips
		 * @param validateRule 验证规则扩展 格式例如:( phone: [/^1\d{10}$/, "请输入正确的手机号"] )
		 * @param labelWidth 左侧标题宽度
		 * @param formFormat 表单格式化参数 参数说明 formRender
		 * @param puspendFormat 悬浮框配置参数 参数说明详见 suspendTips 当 showTipsType 为 puspend，该配置生效。
		 * @param submitBtn 按钮ID/Class，用于提交数据(采用此按钮提交，则进行验证消息)
		 * @param otherTips(msg)  继承消息提醒方式扩展第三方提示层 参数 msg
		 * @param success 方法提交传值
		 */
		formPrefer: function(options) {
			// 默认参数
			var defaults = {
				showTipsType: 'lineTips', // 消息提醒方式
				validateRule: {}, // 验证规则扩展
				labelWidth: 60, // 左侧标题宽度
				formFormat: { // 表单格式化参数
					selectWidth: 300, // Select框宽度值
					selectMaxH: 300, // Select框最大高度值 超出该高度则滚动
					inputWidth: 300, // 输入框宽度值
					textareaWidth: 300, // 文本域宽度值
					textareaHeight: 100, // 文本域高度值
					textareaMaxH: 160, // 文本域最大高度
					init: function() { // 初始化事件

					}
				},
				puspendFormat: {
					content: 'title',
					className: 'tip-yellowsimple',
					bgImageFrameSize: 9,
					showTimeout: 500,
					hideTimeout: 500,
					timeOnScreen: 3000,
					alignTo: 'target',
					alignX: 'inner-left',
					alignY: 'top',
					offsetX: 0,
					offsetY: 5,
					fade: true,
					slide: true,
					baseUrl: "./dvpt/libs/form/theme/"
				},
				submitBtn: '', // 按钮ID/Class，用于提交数据
				otherTips: function(msg) { // 接入第三方提示层
					// console.log(msg);
				},
				success: function() { // 验证表单后执行的方法 submitBtn来触发
					console && console.log('success');
				}
			}
			// 参数继承
			var opts = $.extend(defaults, options);
			_verify = $.extend(_verify, opts.validateRule);
			var $container = $(this);

			// 表单样式优化
			$container.formRender(opts.formFormat);
			$container.find('.form-label').css('width', opts.labelWidth);

			// 文本类表单的焦点失去时验证
			$container.find("input[type='text'], input[type='password'], .form-ui-textarea").on('blur', function() {
				$(this).textValidate(opts.showTipsType, opts.otherTips, opts.puspendFormat);
			});

			// 获取焦点时显示正常格式
			$container.find("input[type='text'], input[type='password'], .form-ui-textarea").on('focus', function() {
				var $current = $(this);
				$current.removeClass('form-error');

				// 根据提示方式
				if(opts.showTipsType == 'lineTips') {
					$current.next('.form-msg').remove();
				}
			});

			// 表单提交时验证
			if(opts.submitBtn && ($(opts.submitBtn).length > 0)) {
				$container.formValidate(opts.submitBtn, opts.showTipsType, opts.puspendFormat, opts.otherTips, opts.success);
			}
		},

		/**
		 * 表单的样式优化
		 * 优化样式包括：Select 框、Radio 框、Switch 框、CheckBox 框、 Input 框
		 * @param selectWidth  Select框宽度值
		 */
		formRender: function(options) {
			// 默认参数
			var defaults = {
				selectWidth: 300, // 选择框的宽度值
				selectMaxH: 300, // 选择框的最大高度值
				inputWidth: 300, // 输入框的宽度值
				textareaWidth: 300, // 文本域的宽度值
				textareaHeight: 100, // 文本域的高度值
				textareaMaxH: 160, // 文本域的最大高度值
				init: function() { // 初始化事件

				}
			}
			// 参数继承
			var opts = $.extend(defaults, options);

			// 初始化
			var $container = $(this);

			// 获取选择的表单列表 Select Block
			var $selectArr = $container.find('.form-select');
			// 获取原先的表单信息，并优化样式进行输出
			$selectArr.each(function(i, item) {
				// 初始化
				var $item = $(item),
					$optionList = $item.find('option'),
					disabled = $item.attr('disabled') ? 'disabled':'',
					$uiSelector = $("<div class='form-ui-select "+ disabled +"' style='width: "+ opts.selectWidth +"px;'><div class='select-title'><span>请选择选项</span><em class='icon icon-soliddown form-soliddown'></em></div><ul class='select-li' style='min-width: "+ opts.selectWidth +"px; max-height: "+ opts.selectMaxH +"px;'></ul></div>");

				// 列表
				$optionList.each(function(t, value) {
					var $option = $(value),
						textS = $option.html(),
						selectedS = $option.attr('selected'),
						valueS = $option.attr('value'),
						index = $option.index();

					// text show
					$uiSelector.find('ul').append(
						'<li data-value="'+ valueS +'">'+ textS+'</li>');

					// 赋值
					if(selectedS) {
						$uiSelector.find('.select-title').addClass('active');
						$uiSelector.find('.select-title span').data('value', valueS).text(textS);
						$uiSelector.find('li').eq(index).addClass('selected');
					}

				});

				// 进行样式优化
				$item.after($uiSelector).hide();

				// 点击列表事件
				if(!$uiSelector.hasClass('disabled')) {

					// 事件绑定
					$uiSelector.on('click.form-select', function(e) {
						if($(this).find("ul").is(":visible")) {
							$(this).find("ul").hide();
						} else {
							$(".form-ui-select .select-li").hide();
							$(this).find('ul').show();
						}

						// 阻止默认冒泡
						e.preventDefault();

						return false;
					});

					// 点击列表项
					$uiSelector.find('li').on('click', function() {
						var textS = $(this).html(),
							valueS = $(this).data('value'),
							index = $(this).index();
						$(this).addClass('selected').siblings('li').removeClass('selected');
						// 进行赋值
						$uiSelector.find('.select-title').addClass('active');
						$uiSelector.find('.select-title span').data('value', valueS).text(textS);
						$item.find('option').eq(index).prop('selected', true);
					});

					$(document).off('click.form-select').on('click.form-select', function(e) {
						var _selectBox = $(e.target).closest('.form-ui-select');
						
						if(_selectBox.length == 0) {
							$(".form-ui-select .select-li").hide();
						}
					});
				}
			});

			// 获取单选的表单列表 Radio Block
			var $radioArr = $container.find('.form-radio');
			// 获取原先的表单信息，并优化样式进行输出
			$radioArr.each(function(i, item) {
				// 初始化
				var $item = $(item),
					textR = $item.attr('title'),
					checkedR = $item.attr('checked'),
					disabledR = $item.attr('disabled')? 'disabled':'',
					nameR = $item.attr('name'),
					valueR = $item.val(),
					$uiRadio = $("<div class='form-ui-radio "+ disabledR +"'  data-name='"+ nameR +"'><em class='from-radio'></em><span data-value='"+ valueR+"'>"+ textR +"</span></div>");
				// 赋值
				if(checkedR) {
					$uiRadio.addClass('active');
				}

				// 进行样式优化
				$item.after($uiRadio).hide();

				// 绑定事件
				$item.next('.form-ui-radio').find('.from-radio').on('click', function() {
					var $current = $(this),
						$radioBox = $current.parents('.form-ui-radio');
					if(!$radioBox.hasClass('active')) {
						$radioBox.addClass('active').siblings(".form-ui-radio[data-name='"+nameR+"']").removeClass('active');
						$item.prop('checked', true).siblings("input[name='"+nameR+"']").prop('checked', false);
					}
				})
			});

			// 获取复选框的表单列表，并优化样式进行输出 CheckBox
			var $checkboxArr = $container.find('.form-checkbox');
			// 获取原先的表单信息，并优化样式进行输出
			$checkboxArr.each(function(i, item) {
				// 初始化
				var $item = $(item),
					textC = $item.attr('title'),
					checkedC = $item.attr('checked'),
					disabledC = $item.attr('disabled')? 'disabled':'',
					nameC = $item.attr('name'),
					valueC = $item.val(),
					$uiCheckBox = $("<div class='form-ui-checkbox "+ disabledC +"'  data-name='"+ nameC +"'><em class='from-checkbox'></em><span data-value='"+ valueC+"'>"+ textC +"</span></div>");
				// 赋值
				if(checkedC) {
					$uiCheckBox.addClass('active');
				}

				// 进行样式优化
				$item.after($uiCheckBox).hide();

				// 绑定事件
				$item.next('.form-ui-checkbox').on('click', function() {
					var $current = $(this);
					if(!$current.hasClass('active')) {
						$current.addClass('active');
						$item.prop('checked', true);
					} else {
						$current.removeClass('active');
						$item.prop('checked', false);
					}
				});
			});

			// 获取开关的切换列表 Switch Block
			var $switchArr = $container.find('.form-switch');
			// 获取原先的表单信息，并优化样式进行输出
			$switchArr.each(function(i, item) {
				// 初始化
				var $item = $(item),
					nameS = $item.attr('name'),
					checkedS = $item.attr('checked'),
					valueS = $item.val(),
					$uiSwitch = $("<div class='form-ui-switch' data-name='"+ nameS +"' data-value='"+ valueS +"'><em class='from-switch'></em></div>");
				// 赋值
				if(checkedS) {
					$uiSwitch.addClass('active');
				}

				// 进行样式优化
				$item.after($uiSwitch).hide();

				// 绑定事件
				$item.next('.form-ui-switch').find('.from-switch').on('click', function() {
					var $current = $(this),
						$switchBox = $current.parents('.form-ui-switch');
					if(!$switchBox.hasClass('active')) {
						$switchBox.addClass('active');
						$item.prop('checked', true);
					} else {
						$switchBox.removeClass('active');
						$item.prop('checked', false);
					}
				})
			});

			// Input框宽度
			var $inputArr = $container.find('.form-text');
			// 获取原先的表单信息，并优化样式进行输出
			$inputArr.each(function(i, item) {
				// 预留
				var $item = $(item);
				$item.css('width', opts.inputWidth);

				// placeholder 兼容
				_showIEPlaceHolder($item);
			});

			// Textarea 框宽度
			var $areaArr = $container.find('.form-textarea');
			// 获取原先的表单信息，并优化样式进行输出
			$areaArr.each(function(i, item) {

				// 优化文本域编辑
				var $item = $(item),
					nameA = $item.attr('name'),
					validateA = $item.data('validate'),
					valueA = $item.val(),
					$uiArea = $("<div class='form-ui-textarea' data-name='"+ nameA +"' data-validate='"+ validateA +"' contenteditable='true' >"+ valueA +"</div>");

				// 预留
				var $item = $(item);
				$uiArea.css({
					'width': opts.textareaWidth,
					'minHeight': opts.textareaHeight,
					'maxHeight': opts.textareaMaxH
				});

				// 进行样式优化
				$item.after($uiArea).hide();
			});

			// 兼容IE上 H5 属性 placeholder
			function _showIEPlaceHolder($con) {
				// 检测是否需要兼容
				var supportPlaceholder= 'placeholder'in document.createElement('input');

				if(!supportPlaceholder) {
					// 获取 placeholderText 属性值
					var placeholderText = $con.attr('placeholder');
					var defaultsValue = $con.val();

					// 是否存在属性 placeholder
					if(placeholderText) {
						if(!defaultsValue) {
							$con.val(placeholderText);
						}
					}
					// 焦点
					$con.on('focus', function() {
						if($con.val()===placeholderText) {
							$con.val('');
						}
					});
					// 焦点移出
					$con.on('blur', function() {
						if($con.val()==='') {
							$con.val(placeholderText);
						}
					});
				}
			}

			// 初始化事件
			opts.init.call($container);
		},

		/**
		 * 文本类表单的验证
		 * @param [String] tipsType 显示错误信息的方式 
		 * @param [Function] otherTips 调用第三方消息提示
		 */
		textValidate: function(tipsType, otherTips, puspendFormat) {
			// 初始化
			var $current = $(this),
				value = $current.val() ? $current.val() : $current.text(),
				rules = $current.data('validate');

			// 是否存在规则限制
			if(rules) {
				var rulesArr = rules.toString().split(' ');

				// 比较验证规则
				for(var i =0; i < rulesArr.length; i++) {
					var ruleName = rulesArr[i];
					var codeArr = _verify[ruleName];

					// 验证是否存在该规则
					if(codeArr) {
						// 进行验证
						if(!codeArr[0].test(value)) {
							// 显示错误信息
							switch(tipsType) {
								case 'lineTips':
									$current.lineTips(codeArr[1]);
									break;
								case 'layerTips':
									$current.layerTips(codeArr[1]);
									break;
								case 'otherTips':
									$current.otherTips(codeArr[1], otherTips);
									break;
								case 'suspendTips':
									$current.suspendTips(codeArr[1], puspendFormat);
									break;
								default:
									console && console.log("no the tips's type");
									return false;
							}
							return false;
						} else {
							// 取消错误信息显示
							switch(tipsType) {
								case 'lineTips':
									$current.lineTips();
									break;
								case 'layerTips':
									$current.layerTips();
									break;
								case 'otherTips':
									$current.otherTips();
									break;
								case 'suspendTips':
									$current.suspendTips();
									break;
								default:
									console && console.log("no the tips's type");
									return false;
							}
						}
					}
				}
			}

			return true;
		},

		/**
		 * 单选/复选/多选框的验证 此处验证必填项
		 * @param [String] tipsType 显示错误信息的方式 
		 * @param [Function] otherTips 调用第三方消息提示
		 */
		otherValidate: function(tipsType, otherTips, puspendFormat) {
			// 初始化
			var $current = $(this),
				name = $current.attr('name'),
				rules = $current.data('validate');

			// 是否存在规则
			if(rules == 'required') {
				var nodeName = $current[0].nodeName;
				var msg = '必填项不能为空';
				var $next, value;
				if(nodeName == 'SELECT') {
					value = $current.val();
					$next = $current.siblings('.form-ui-select');
				} else {
					var $inputArr = $current.parent().find('input');
					var tag = 0;
					for(var i = 0; i < $inputArr.length; i++) {
						var checked = $inputArr.eq(i).prop('checked');
						if(checked) {
							value = checked;
							break;
						}
					}
					$next = $current.nextAll("input[name="+ name +"]:last").next('div');
				}

				if(!value) {
					// 显示错误信息
					switch(tipsType) {
						case 'lineTips':
							$next.lineTips(msg);
							break;
						case 'layerTips':
							$next.layerTips(msg);
							break;
						case 'otherTips':
							$next.otherTips(msg, otherTips);
							break;
						case 'suspendTips':
							$next.parents('.form-input').suspendTips(msg, puspendFormat);
							break;
						default:
							console && console.log("no the tips's type");
							return false;
					}
					return false;
				} else {
					// 取消显示错误信息
					switch(tipsType) {
						case 'lineTips':
							$next.lineTips();
							break;
						case 'layerTips':
							$next.layerTips();
							break;
						case 'otherTips':
							$next.otherTips();
							break;
						case 'suspendTips':
							$next.parents('.form-input').suspendTips();
							break;
						default:
							console && console.log("no the tips's type");
							return false;
					}
				}
			}
			return true;
		},

		/**
		 * 表单提交验证
		 * 
		 */
		formValidate: function(submitBtn, tipsType, puspendFormat, otherTips, success) {
			// 初始化
			var $container = $(this);

			// 点击提交时触发
			$(submitBtn).on('click', function(e) {
				// 阻止默认事件
				e.preventDefault();
				// 通行证
				var tag = 1;
				var $conArr = $container.find(".form-box");
				for(var i = 0; i < $conArr.length; i++) {
					var $current = $($conArr[i]).find("input[type='text'], input[type='password'], .form-ui-textarea");
					var isChecked;
					if($current.length > 0) {
						isChecked = $current.textValidate(tipsType, otherTips, puspendFormat);
					} else {
						$current = $($conArr[i]).find("select, input[type='checkbox'], input[type='radio']");
						if($current.length > 0) {
							isChecked = $current.otherValidate(tipsType, otherTips, puspendFormat);
						}
					}

					if(!isChecked) {
						tag = 0;
						break;
					}
				}

				if(tag === 0) {
					return false;
				} else {
					(typeof(success) === 'function') && success();
				}
				return true;
			});
		},

		/**
		 * 提示框样式 - lineTips
		 * @param [String] msg 报错信息 
		 */
		lineTips: function(msg) {
			// 初始化
			var $container = $(this);

			// 判断是否返回消息
			if(msg) {
				if($container.next('.form-msg').length > 0) {
					$container.next('.form-msg').text('* ' + msg);
				} else {
					$container.after('<span class="form-msg">* '+ msg +'</span>');
				}
				$container.addClass('form-error');
			} else {
				$container.next('.form-msg').remove();
				$container.removeClass('form-error');
			}
		},

		/**
		 * 提示框样式 - layerTips
		 * 自定义样式
		 */
		layerTips: function(msg) {
			// 初始化
			var $container = $(this);

			if(msg) {
				$container.addClass('form-error');

				// 调用显示
				$.fn.buildTipsLay(msg);
			} else {
				$container.removeClass('form-error');
			}
		},
		
		/**
		 * 提示框样式 - otherTips
		 * 外部调用提示框
		 */
		otherTips: function(msg, tips) {
			// 初始化
			var $container = $(this);

			if(msg) {
				$container.addClass('form-error');

				// 调用其他组件 代入参数
				tips(msg);
			} else {
				$container.removeClass('form-error');
			}
		},

		/**
		 * 悬浮框提示样式 - suspend
		 * 外部调用提示框
		 */
		suspendTips: function(msg, puspendFormat) {
			// 初始化
			var $container = $(this);

			if(msg) {
				$container.addClass('form-error');

				puspendFormat = $.extend(puspendFormat, {content: msg});

				// 调用悬浮框组件
				$container.buildSuspendTips(puspendFormat);
			} else {
				$container.removeClass('form-error');
			}
		},

		/**
		 * 内置提示浮层构建
		 * @param { String } msg 报错信息
		 */
		buildTipsLay: function(msg) {

			if($(".form-layer").length == 0) {
				// 构建提示框
				var $layer = $("<div class='form-layer'>\
								<div class='layer-head'><p>提示信息<em class='icon icon-close3'></em></p></div>\
								<div class='layer-body'>\
								</div>\
							</div>");
				// 追加 DOM
				$("body").append($layer);
				$layer.find('.layer-body').append("<div class='layer-msg'><span class='layer-icon-warning'></span><p>"+ msg +"</p></div>");
			} else {
				var $layer = $(".form-layer");
				$layer.find('.layer-msg p').text(msg);
				$layer.show();
			}

			// 样式调整
			var width = $layer.width(),
				height = $layer.height();
			$layer.css('marginLeft', - width /2 + 'px');
			$layer.css('marginTop', - height /2 + 'px');

			// 绑定事件
			$layer.find('.layer-head em').off('click').on('click', function() {
				$layer.hide();
			});
		},

		/**
		 * 内置悬浮框提醒层（改编自poshytipJs，继承样式上和定位功能）
		 * @param { String } content 提示内容 默认值 [title]
		 * @param { String } className 提示层样式 默认值 tip-yellow
		 * @param { Number } bgImageFrameSize 背景图片大小
		 * @param { Number } showTimeout 工具条出现前的过渡时间
		 * @param { Number } hideTimeout 工具条消失前的过渡时间
		 * @param { Number } timeOnScreen 显示时间的长短
		 * @param { Number } alignTo 提示工具条 'cursor'/'target'
		 * @param { Number } alignX 提示工具条出现在水平方向相对当前元素的位置，有'right', 'center', 'left', 'inner-left', 'inner-right'
		 * @param { Number } alignY 提示工具条出现在垂直方向相对当前元素的位置，有'bottom', 'center', 'top', 'inner-bottom', 'inner-top'
		 * @param { Number } offsetX 相对位置偏移
		 * @param { Number } offsetY 相对位置偏移
		 * @param { Boolean } fade 使用渐显渐隐效果
		 * @param { Boolean } slide 使用滑动效果
		 * @param { String } baseUrl 样式文件的基础路径
		 */
		buildSuspendTips: function(options) {
			// 默认配置
			var defaults = {
				content: 'title',
				className: 'tip-yellowsimple',
				bgImageFrameSize: 9,
				showTimeout: 500,
				hideTimeout: 500,
				timeOnScreen: 3000,
				alignTo: 'target',
				alignX: 'inner-left',
				alignY: 'top',
				offsetX: 0,
				offsetY: 5,
				fade: true,
				slide: true,
				baseUrl: "./dvpt/libs/form/theme/"
			}

			// 继承参数
			var opts = $.extend(defaults, options);
			var $container = $(this);
			var tips = [],
				reBgImage = /^url\(["']?([^"'\)]*)["']?\);?$/i,
				rePNG = /\.png$/i,
				IE = !!window.createPopup,
		 		IE6 = IE && typeof document.documentElement.currentStyle.minWidth == 'undefined',
				IElt9 = IE && !document.defaultView;

			// 对于样式进行添加
			if (!$('#poshytip-css-' + opts.className)[0]) $(['<style id="poshytip-css-', opts.className, '" type="text/css">', 'div.', opts.className, '{visibility:hidden;position:absolute;top:0;left:0;}', 'div.', opts.className, ' table.tip-table, div.', opts.className, ' table.tip-table td{margin:0;font-family:inherit;font-size:inherit;font-weight:inherit;font-style:inherit;font-variant:inherit;vertical-align:middle;}', 'div.', opts.className, ' td.tip-bg-image span{display:block;font:1px/1px sans-serif;height:', opts.bgImageFrameSize, 'px;width:', opts.bgImageFrameSize, 'px;overflow:hidden;}', 'div.', opts.className, ' td.tip-right{background-position:100% 0;}', 'div.', opts.className, ' td.tip-bottom{background-position:100% 100%;}', 'div.', opts.className, ' td.tip-left{background-position:0 100%;}', 'div.', opts.className, ' div.tip-inner{background-position:-', opts.bgImageFrameSize, 'px -', opts.bgImageFrameSize, 'px;}', 'div.', opts.className, ' div.tip-arrow{visibility:hidden;position:absolute;overflow:hidden;font:1px/1px sans-serif;}', '</style>'].join('')).appendTo('head');

			if($("link" + opts.className)[0]) {
				$("link" + opts.className).attr("href", opts.baseUrl + opts.className + "/" + opts.className + ".css");
			} else {
				$('<link id="'+ opts.className +'" rel="stylesheet" type="text/css" href="'+ opts.baseUrl + opts.className + '/' + opts.className + '.css" />').appendTo($("head"));
			}

			// 直接显示提示信息
			var build = new _initHtml();
			build.init();

			// 进行初始化
			function _initHtml() {
				// 构建DOM
				this.$elm = $container;
				this.opts = opts;
				this.$tip = $(['<div ',' class="',this.opts.className,'">',
						'<div class="tip-inner tip-bg-image"></div>',
						'<div class="tip-arrow tip-arrow-top tip-arrow-right tip-arrow-bottom tip-arrow-left"></div>',
					'</div>'].join('')).appendTo(document.body);
				this.$arrow = this.$tip.find('div.tip-arrow');
				this.$inner = this.$tip.find('div.tip-inner');
				this.disabled = false;
				this.content = null;
				this.init = function() {
					tips.push(this);

					// save the original title and a reference to the Poshytip object
					var title = this.$elm.attr('title');
					this.$elm.data('title.poshytip', title !== undefined ? title : null)
						.data('poshytip', this);

					this.showDelayed();
				},
				this.show = function() {
					if (this.disabled || this.$tip.data('active'))
						return;

					this.reset();
					this.update();

					// don't proceed if we didn't get any content in update() (e.g. the element has an empty title attribute)
					if (!this.content)
						return;

					this.display();
					if (this.opts.timeOnScreen)
						this.hideDelayed(this.opts.timeOnScreen);
				},
				this.showDelayed = function(timeout) {
					this.clearTimeouts();
					this.showTimeout = setTimeout($.proxy(this.show, this), typeof timeout == 'number' ? timeout : this.opts.showTimeout);
				},
				this.hide = function() {
					if (this.disabled || !this.$tip.data('active'))
						return;

					this.display(true);
				},
				this.hideDelayed = function(timeout) {
					this.clearTimeouts();
					this.hideTimeout = setTimeout($.proxy(this.hide, this), typeof timeout == 'number' ? timeout : this.opts.hideTimeout);
				},
				this.reset = function() {
					this.$tip.queue([]).detach().css('visibility', 'hidden').data('active', false);
				},
				this.update = function(content, dontOverwriteOption) {
					if (this.disabled)
						return;

					var async = content !== undefined;
					if (async) {
						if (!dontOverwriteOption)
							this.opts.content = content;
						if (!this.$tip.data('active'))
							return;
					} else {
						content = this.opts.content;
					}

					// update content only if it has been changed since last time
					var self = this,
						newContent = typeof content == 'function' ?
							content.call(this.$elm[0], function(newContent) {
								self.update(newContent);
							}) :
							content == '[title]' ? this.$elm.data('title.poshytip') : content;
					if (this.content !== newContent) {
						this.$inner.empty().append(newContent);
						this.content = newContent;
					}

					this.refresh(async);
				},
				this.refresh = function(async) {
					if (this.disabled)
						return;

					if (async) {
						if (!this.$tip.data('active'))
							return;
						// save current position as we will need to animate
						var currPos = {left: this.$tip.css('left'), top: this.$tip.css('top')};
					}

					// reset position to avoid text wrapping, etc.
					this.$tip.css({left: 0, top: 0}).appendTo(document.body);

					// save default opacity
					if (this.opacity === undefined)
						this.opacity = this.$tip.css('opacity');

					// check for images - this code is here (i.e. executed each time we show the tip and not on init) due to some browser inconsistencies
					var bgImage = this.$tip.css('background-image').match(reBgImage),
						arrow = this.$arrow.css('background-image').match(reBgImage);

					if (bgImage) {
						var bgImagePNG = rePNG.test(bgImage[1]);
						// fallback to background-color/padding/border in IE6 if a PNG is used
						if (IE6 && bgImagePNG) {
							this.$tip.css('background-image', 'none');
							this.$inner.css({margin: 0, border: 0, padding: 0});
							bgImage = bgImagePNG = false;
						} else {
							this.$tip.prepend('<table class="tip-table" border="0" cellpadding="0" cellspacing="0"><tr><td class="tip-top tip-bg-image" colspan="2"><span></span></td><td class="tip-right tip-bg-image" rowspan="2"><span></span></td></tr><tr><td class="tip-left tip-bg-image" rowspan="2"><span></span></td><td></td></tr><tr><td class="tip-bottom tip-bg-image" colspan="2"><span></span></td></tr></table>')
								.css({border: 0, padding: 0, 'background-image': 'none', 'background-color': 'transparent'})
								.find('.tip-bg-image').css('background-image', 'url("' + bgImage[1] +'")').end()
								.find('td').eq(3).append(this.$inner);
						}
						// disable fade effect in IE due to Alpha filter + translucent PNG issue
						if (bgImagePNG && IElt9)
							this.opts.fade = false;
					}
					// IE arrow fixes
					if (arrow && IElt9) {
						// disable arrow in IE6 if using a PNG
						if (IE6 && rePNG.test(arrow[1])) {
							arrow = false;
							this.$arrow.css('background-image', 'none');
						}
						// disable fade effect in IE due to Alpha filter + translucent PNG issue
						this.opts.fade = false;
					}

					var $table = this.$tip.find('> table.tip-table');
					if (IE6) {
						// fix min/max-width in IE6
						this.$tip[0].style.width = '';
						$table.width('auto').find('td').eq(3).width('auto');
						var tipW = this.$tip.width(),
							minW = parseInt(this.$tip.css('min-width')),
							maxW = parseInt(this.$tip.css('max-width'));
						if (!isNaN(minW) && tipW < minW)
							tipW = minW;
						else if (!isNaN(maxW) && tipW > maxW)
							tipW = maxW;
						this.$tip.add($table).width(tipW).eq(0).find('td').eq(3).width('100%');
					} else if ($table[0]) {
						// fix the table width if we are using a background image
						// IE9, FF4 use float numbers for width/height so use getComputedStyle for them to avoid text wrapping
						// for details look at: http://vadikom.com/dailies/offsetwidth-offsetheight-useless-in-ie9-firefox4/
						$table.width('auto').find('td').eq(3).width('auto').end().end().width(document.defaultView && document.defaultView.getComputedStyle && parseFloat(document.defaultView.getComputedStyle(this.$tip[0], null).width) || this.$tip.width()).find('td').eq(3).width('100%');
					}
					this.tipOuterW = this.$tip.outerWidth();
					this.tipOuterH = this.$tip.outerHeight();

					this.calcPos();

					// position and show the arrow image
					if (arrow && this.pos.arrow) {
						this.$arrow[0].className = 'tip-arrow tip-arrow-' + this.pos.arrow;
						this.$arrow.css('visibility', 'inherit');
					}

					if (async && this.opts.refreshAniDuration) {
						this.asyncAnimating = true;
						var self = this;
						this.$tip.css(currPos).animate({left: this.pos.l, top: this.pos.t}, this.opts.refreshAniDuration, function() { self.asyncAnimating = false; });
					} else {
						this.$tip.css({left: this.pos.l, top: this.pos.t});
					}
				},
				this.display = function(hide) {
					var active = this.$tip.data('active');
					if (active && !hide || !active && hide)
						return;

					this.$tip.stop();
					if ((this.opts.slide && this.pos.arrow || this.opts.fade) && (hide && this.opts.hideAniDuration || !hide && this.opts.showAniDuration)) {
						var from = {}, to = {};
						// this.pos.arrow is only undefined when alignX == alignY == 'center' and we don't need to slide in that rare case
						if (this.opts.slide && this.pos.arrow) {
							var prop, arr;
							if (this.pos.arrow == 'bottom' || this.pos.arrow == 'top') {
								prop = 'top';
								arr = 'bottom';
							} else {
								prop = 'left';
								arr = 'right';
							}
							var val = parseInt(this.$tip.css(prop));
							from[prop] = val + (hide ? 0 : (this.pos.arrow == arr ? -this.opts.slideOffset : this.opts.slideOffset));
							to[prop] = val + (hide ? (this.pos.arrow == arr ? this.opts.slideOffset : -this.opts.slideOffset) : 0) + 'px';
						}
						if (this.opts.fade) {
							from.opacity = hide ? this.$tip.css('opacity') : 0;
							to.opacity = hide ? 0 : this.opacity;
						}
						this.$tip.css(from).animate(to, this.opts[hide ? 'hideAniDuration' : 'showAniDuration']);
					}
					hide ? this.$tip.queue($.proxy(this.reset, this)) : this.$tip.css('visibility', 'inherit');
					if (active) {
						var title = this.$elm.data('title.poshytip');
						if (title !== null)
							this.$elm.attr('title', title);
					}
					this.$tip.data('active', !active);
				},
				this.disable = function() {
					this.reset();
					this.disabled = true;
				},
				this.enable = function() {
					this.disabled = false;
				},
				this.destroy = function() {
					this.reset();
					this.$tip.remove();
					delete this.$tip;
					this.content = null;
					this.$elm.unbind('.poshytip').removeData('title.poshytip').removeData('poshytip');
					tips.splice($.inArray(this, tips), 1);
				},
				this.clearTimeouts = function() {
					if(this.showTimeout) {
						clearTimeout(this.showTimeout);
						this.showTimeout = 0;
					}
					if(this.hideTimeout) {
						clearTimeout(this.hideTimeout);
						this.hideTimeout = 0;
					}
				},
				this.updateCursorPos = function(e) {
					this.eventX = e.pageX;
					this.eventY = e.pageY;
				},
				this.calcPos = function() {
					var pos = {l: 0, t: 0, arrow: ''},
						$win = $(window),
						win = {
							l: $win.scrollLeft(),
							t: $win.scrollTop(),
							w: $win.width(),
							h: $win.height()
						}, xL, xC, xR, yT, yC, yB;
					if (this.opts.alignTo == 'cursor') {
						xL = xC = xR = this.eventX;
						yT = yC = yB = this.eventY;
					} else { // this.opts.alignTo == 'target'
						var elmOffset = this.$elm.offset(),
							elm = {
								l: elmOffset.left,
								t: elmOffset.top,
								w: this.$elm.outerWidth(),
								h: this.$elm.outerHeight()
							};
						xL = elm.l + (this.opts.alignX != 'inner-right' ? 0 : elm.w);	// left edge
						xC = xL + Math.floor(elm.w / 2);				// h center
						xR = xL + (this.opts.alignX != 'inner-left' ? elm.w : 0);	// right edge
						yT = elm.t + (this.opts.alignY != 'inner-bottom' ? 0 : elm.h);	// top edge
						yC = yT + Math.floor(elm.h / 2);				// v center
						yB = yT + (this.opts.alignY != 'inner-top' ? elm.h : 0);	// bottom edge
					}

					// keep in viewport and calc arrow position
					switch (this.opts.alignX) {
						case 'right':
						case 'inner-left':
							pos.l = xR + this.opts.offsetX;
							if (this.opts.keepInViewport && pos.l + this.tipOuterW > win.l + win.w)
								pos.l = win.l + win.w - this.tipOuterW;
							if (this.opts.alignX == 'right' || this.opts.alignY == 'center')
								pos.arrow = 'left';
							break;
						case 'center':
							pos.l = xC - Math.floor(this.tipOuterW / 2);
							if (this.opts.keepInViewport) {
								if (pos.l + this.tipOuterW > win.l + win.w)
									pos.l = win.l + win.w - this.tipOuterW;
								else if (pos.l < win.l)
									pos.l = win.l;
							}
							break;
						default: // 'left' || 'inner-right'
							pos.l = xL - this.tipOuterW - this.opts.offsetX;
							if (this.opts.keepInViewport && pos.l < win.l)
								pos.l = win.l;
							if (this.opts.alignX == 'left' || this.opts.alignY == 'center')
								pos.arrow = 'right';
					}
					switch (this.opts.alignY) {
						case 'bottom':
						case 'inner-top':
							pos.t = yB + this.opts.offsetY;
							// 'left' and 'right' need priority for 'target'
							if (!pos.arrow || this.opts.alignTo == 'cursor')
								pos.arrow = 'top';
							if (this.opts.keepInViewport && pos.t + this.tipOuterH > win.t + win.h) {
								pos.t = yT - this.tipOuterH - this.opts.offsetY;
								if (pos.arrow == 'top')
									pos.arrow = 'bottom';
							}
							break;
						case 'center':
							pos.t = yC - Math.floor(this.tipOuterH / 2);
							if (this.opts.keepInViewport) {
								if (pos.t + this.tipOuterH > win.t + win.h)
									pos.t = win.t + win.h - this.tipOuterH;
								else if (pos.t < win.t)
									pos.t = win.t;
							}
							break;
						default: // 'top' || 'inner-bottom'
							pos.t = yT - this.tipOuterH - this.opts.offsetY;
							// 'left' and 'right' need priority for 'target'
							if (!pos.arrow || this.opts.alignTo == 'cursor')
								pos.arrow = 'bottom';
							if (this.opts.keepInViewport && pos.t < win.t) {
								pos.t = yB + this.opts.offsetY;
								if (pos.arrow == 'bottom')
									pos.arrow = 'top';
							}
					}
					this.pos = pos;
				}
			}
		}
	});

});
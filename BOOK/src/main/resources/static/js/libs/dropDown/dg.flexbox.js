define(['jquery','dg.data','phoneticize','jquery.flexbox'],function($) {
	// 基本函数
	var setDisplayValueByValue=function(value,selectObj,result,props){
		var displayVal="";
		if(!value) {
			selectObj.find(".ffb-input").val(displayVal);
			selectObj.find(".dropDownRadioBox").attr("displayValue","");
			selectObj.find(".ffb-input").removeClass('watermark');
			selectObj.find(".dropDownRadioBox").val("");
			return;
		}
		$.each(props.dataParam.results, function(i, item){
			if(item[result.hiddenValue] == value) {
				displayVal = item[result.displayValue];
				return;
			}
		});
		selectObj.find(".ffb-input").val(displayVal);
		selectObj.find(".dropDownRadioBox").attr("displayValue",displayVal);
		selectObj.find(".ffb-input").removeClass('watermark');
		selectObj.find(".dropDownRadioBox").val(value);
		return;
	}
	// 获取值
	var getTextByValue=function(value,selectObj,result,props){
		var displayVal="";
		if(!value) return "";
		$.each(props.dataParam.results, function(i, item){
			if(item[result.hiddenValue] == value){
				displayVal = item[result.displayValue];
				return;
			}
		});
		return displayVal;
	}
	var getValueByText=function(value,selectObj,result,props){
		var val="";
		if(!value) return "";
		$.each(props.dataParam.results, function(i, item){
			if(item[result.displayValue] == value){
				val = item[result.hiddenValue];
				return;
			}
		});
		return val;
	}
	var validate=function($input,selectObj){
		var cssText=$input.attr("class");
		var position=$input.attr("data-prompt-position");
		var target=$input.attr("data-prompt-target");
		var placeholder=$input.attr("data-validation-placeholder");
		var engine=$input.attr("data-validation-engine");
		selectObj.find(".ffb-input").addClass(cssText);
		selectObj.find(".ffb-input").attr("data-prompt-position",position)
		.attr("data-prompt-target",target)
		.attr("data-validation-placeholder",placeholder)
		.attr("data-validation-engine",engine);
	}
	// 内置方法
	var addProp=function(selectObj,changeCalls,result,props){
		selectObj.setValue=function(value){// 设定值
			setDisplayValueByValue(value,selectObj,result,props);
		}
		selectObj.getValue=function(value){
			return selectObj.find("input:hidden").val();// 获取值
		}
		selectObj.getTextByValue=function(value){ //根据值获取显示文本
			return getTextByValue(value,selectObj,result,props);
		}
		selectObj.getValueByText=function(text){ //根据显示文本获取值
			return getValueByText(value,selectObj,result,props);
		}
			selectObj.displayVal=selectObj.getDisplayValue=function(){ //获取显示文本
			return selectObj.find(".ffb-input").val();
		}
		selectObj.reload=function(callback){
			selectObj.load(props,callback);
		}
		selectObj.load=function(param,callback){
			param= $.extend({},result,param);
			$.fn.getDataStore(param,function(data){
				props.dataParam.results=data;
				props.dataParam.total=data.length;
				for (var i=0; i < props.dataParam.results.length; i++) {
				    var row = props.dataParam.results[i];
				    row.pym = pinyin.getCamelChars(row[result.displayValue]);
				}
				selectObj.val(selectObj.val());
				callback&&callback(selectObj);
			});
		}
		selectObj.val=function(value){
			if(!value)return selectObj.find("input:hidden").val();
			setDisplayValueByValue(value,selectObj,result,props);
		}
		selectObj.change=function(callback){
			if(callback)changeCalls.push(callback);
		}
		selectObj.setReadonly=function(read){
			if(read){
				//设置只读
				selectObj.children().addClass("readonly");
				selectObj.children().eq(1).attr("readonly","readonly");
				//selectObj.children().eq(2).addClass("readonly");
			}else{
				//设置可用
				selectObj.children().removeClass("readonly");
				selectObj.children().eq(1).removeAttr("readonly");
				//selectObj.children().eq(2).removeClass("readonly");
			}
		}
		selectObj.setDisabled=function(able){
			if(able){
				//设置禁用，不做表单提交
				selectObj.children().addClass("disabled");
				selectObj.children().eq(1).attr("disabled","disabled");
			}else{
				//设置可用
				selectObj.children().removeClass("disabled");
				selectObj.children().eq(1).removeAttr("disabled");
			}
		}
	}

	/**
	 * [dropDownRadioDp 下拉框]
	 * @param submitMode 提交模式 hidden-编号、ID display-文本
	 * @param method 传输方式 默认值
	 * @param queryDelay 延迟查询的时间
	 * @param allowInput 是否允许手动输入
	 * @param ...Class 样式类名
	 * @param noResultsText 未查到数据提示消息
	 * @param noResultsClass 提示消息样式
	 * @param showResults/selectFirstMatch/autoCompleteFirstMatch/highlightMatches/highlightMatchesRegExModifier 不常用参数
	 * @param showArrow 显示箭头
	 * @param arrowQuery 箭头触发搜索
	 * @param onSelect 选中后触发方法
	 * @param maxCacheBytes 缓存字节数限制 0 则表示没有缓存
	 * @param resultTemplate 取出来的值模板
	 * @param displayValue 显示的值
	 * @param hiddenValue 当表单提交时json数据提交的值
	 * @param initialValue 当表单数据加载时显示的初始值
	 * @param watermark 显示文本信息 style with css class '.ffb-input.watermark'
	 * @param width flexbox 的宽度
	 * @param maxVisibleRows 显示最大的行数 而不能使用分页功能
	 * @param manualInput 手动输入数据 默认关闭
	 * @param paging 分页显示
	 */
	$.fn.extend({
		dropDownRadioDp: function(options) {
			// 默认参数
			var defaults = {
				submitMode : 'hidden',
				method: 'GET',
				queryDelay: 100,
				allowInput: true,
				containerClass: 'ffb',
				contentClass: 'content',
				selectClass: 'ffb-sel',
				inputClass: 'ffb-input',
				arrowClass: 'ffb-arrow',
				matchClass: 'ffb-match',
				noResultsText: '无匹配记录',
				noResultsClass: 'ffb-no-results',
				showResults: true, // whether to show results at all, or just typeahead
				selectFirstMatch: true, // whether to highlight the first matching value
				autoCompleteFirstMatch: false, // whether to complete the first matching value in the input box
				highlightMatches: true, // whether all matches within the string should be highlighted with matchClass
				highlightMatchesRegExModifier: 'i', // 'i' for case-insensitive, 'g' for global (all occurrences), or combine
				matchAny: true, // for client-side filtering ONLY, match any occurrence of the search term in the result (e.g. "ar" would find "area" and "cart")
				minChars: 1, // the minimum number of characters the user must enter before a search is executed
				showArrow: true,
				arrowQuery: '',
				onSelect: false,
				onChange: false,
				maxCacheBytes: 32768,
				resultTemplate: '{value}',
				displayValue: 'value',
				hiddenName : '',
				hiddenValue: 'code',
				initialValue: '',
				watermark: '',
				width: 200,
				resultsProperty: 'results', // json property in response that references array of results
				totalProperty: 'total', // json property in response that references the total results (for paging)
				maxVisibleRows: 0,
				bmName : '',
				readonly: false, // 是否只读
				disabled: false, // 是否禁止
				isMoreSelecte: false,
				manualInput:false,
				paging: {
					style: 'input', // or 'links'
					cssClass: 'paging', // 设定容器 Padding
					pageSize: 10, // 当小于10条时则不显示分页
					maxPageLinks: 5, // 最大显示五条
					showSummary: true, // 是否显示结尾样式（分页）
					summaryClass: 'summary', // 样式类
					summaryTemplate: '共{total}条记录' // 显示总条数
	            }
			}

			// 对象值
			var $dropDownRadio=$(this);
			options.width = $dropDownRadio.width();
			// 初始化一些 jquery.flexbox.js 不存在的属性值
			var dataParam={results:[],total:0};
			options.dataParam=dataParam;
			var  selectObj=null;
			var  changeCalls=[];

			// 可以增加扩展属性
			var result = $.extend({}, defaults, options);

			// 当前对象 
			var $domNode= $('<div class="dropDownRadioBox"></div>');

			// 将匹配的元素替换成指定的HTML 移动到目标位置来替换，而不是复制一份来替换
			$dropDownRadio.replaceWith($domNode);

			// 存在宽度配置时执行
			options.width&&$domNode.css("width",options.width);
			var domNodeId=$dropDownRadio.attr("id");
			if(domNodeId) {
				$($domNode).attr("id",domNodeId);
			}

			// 调用 flexbox 函数
			selectObj= $($domNode).flexbox(dataParam,result);

			//清空
			var del = $('<a href="javascript:;" class="clearIcon"></a>').insertBefore(selectObj.find(".ffb-arrow")).click(function(e){
				if($dropDownRadio.attr("disabled")||$dropDownRadio.attr("readonly")) return;
				$(this).siblings(".ffb-input").attr("value","");
				e.stopPropagation();// 阻止事件传播
				e.preventDefault();// 阻止默认事件
			});


			// 绑定一些数据
			selectObj.find("input[name]").attr("op",options.op);
			selectObj.find("input[name]").attr("dpType","dropDownRadio");
			selectObj.find("input[name]").data("dropDownRadio",selectObj);

			// 只读
			if($dropDownRadio.attr("readonly")){
				selectObj.children().addClass("readonly").attr("readonly",true);
			}
			// 禁止
			if($dropDownRadio.attr("disabled")){
				selectObj.children().addClass("disabled").attr("disabled",true);
			} 

			// addProp 调用内置函数
			addProp(selectObj,changeCalls,result,options);

			// 调用 dg.data.js
			$.fn.getDataStore(options,function(data){
				dataParam.results=data;
				dataParam.total=data.length;
				for (var i=0; i < dataParam.results.length; i++) {
				var row = dataParam.results[i];
				row.pym = pinyin.getCamelChars(row[result.displayValue]);
				}
				result.initialValue&&setDisplayValueByValue(result.initialValue,selectObj,result,options);     //塞值
			});
			
			return selectObj;
		}
	});
});
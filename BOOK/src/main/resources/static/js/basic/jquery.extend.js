$(function() {

	/**
	 * JQuery Extend Global
	 */
	$.extend({

		/**
		 * loading a page
		 * @param  container 页面容器
		 * @param  url 页面路径
		 * @param  async true/false 异步/同步
		 * @param  loadJs 加载脚本文件
		 * @param  success DOM加载完毕后执行
		 * @param  beforeSend 发送请求前执行
		 * @param  error 发送请求失败后执行
		 * @return {bool} true/false
		 */
		openWindow: function(options) {
			var defaults = {
				container: "#main-content",
				url: 'test.html',
				type: 'get',
				data: null,
				async: true,
				loadJS: null,
				success: false,
				beforeSend: false,
				error: false,
			};

			// 参数初始化
			var options = $.extend(defaults, options);

			$.ajax({
				type: options.type,
				data: options.data,
				url: $.getProUrl(options.url),
				async: options.async,
				dataType:'html',
				beforeSend: function() {
					if(options.beforeSend) {return options.beforeSend();}
				},
				error: function(XMLHttpRequest, msg) {
					if(options.error) {options.error(msg);}
					return false;
				},
				success: function(html) {
					$(options.container).empty().append(html);
					if(options.container == '#main-content') {
						$(options.container).append('<div id="footer">Copyright ©2015  厦门市巨龙信息科技有限公司 技术支持 &nbsp;&nbsp;建议使用1280*768及以上分辨率</div>');
					}
				}
			}).done(function() {
				// 加载完成后运行
				if(options.loadJS) {
					$.getScript(options.loadJS,function() {
						if(options.success) {
							options.success();
						}
					})
				} else {
					if(options.success) {
						options.success();
					}
				}
			});
		},

		/**
		 * Downloads a script and executes a callback when done.
		 * @param {String} 脚本路径
		 * @param {Function} 回调
		 */
		getScript: function(scriptLocation, callback) {
			var head = document.getElementsByTagName('head')[0],
				script = document.createElement('script');

			script.type = 'text/javascript';
			script.src = scriptLocation;
			script.onload = callback;
			head.appendChild(script);
		},

		/**
		 * Only for downloads scripts
		 * @param {String} baseUrl 基本路径
		 * @param {Array} scriptsName 脚本名称数组
		 */
		downloadScripts: function(options) {
			var defaults = {
				scriptsName: null,
				baseUrl: './js/transfer/'
			}
			// 参数初始化
			var options = $.extend(defaults, options);
			var head = document.getElementsByTagName('head')[0];

			// 检验
			if(options.scriptsName == null) {
				return false;
			}

			// 执行
			if(typeof(options.scriptsName) === 'object') {
				for(var i = 0; i < options.scriptsName.length; i++) {
					var script = document.createElement('script');
					script.type = 'text/javascript';
					script.src = options.baseUrl + options.scriptsName[i] + '.js';

					head.appendChild(script);
				}
			}
		},

		/**
		 * overlay for main-content
		 * @param {String} zIndex
		 * @param {String} close
		 */
		overlay: function(options) {
			var defaults = {
				zIndex: '100',
				close: false,
				opacity: 0.4,
			}
			// 参数初始化
			var options = $.extend(defaults, options);
			if(options.close) {
				$("#main-overlay").css({'display':'none', 'zIndex': options.zIndex}).animate({opacity: 0}, 500);
			} else {
				$('#main-overlay').css({'display': 'block', 'zIndex': options.zIndex, 'opacity': '0'}).show().animate({opacity: options.opacity}, 500);
			}
		},

		/**
		 * 菜单跳转链接
		 * @param  {object} obj 对象
		 * @return html
		 */
		menuLink: function(obj) {
			// 菜单跳转
			obj.on("click","a",function(){
				if($(this).attr('_href')){

					var location = window.location,
						_href = $(this).attr('_href'),
						_loadJs = $(this).data('js');

					// 打开窗口
					$.openWindow({
						url:_href,
						success: function() {
							// 加载常用的脚本依赖
							if(_loadJs) {
								var scripts = _loadJs.split(' ');
								$.downloadScripts({'scriptsName':scripts});
							}
						} 
					});
				}
			});
		},

		/**
		 * IE兼容性 中文解析
		 * @param { new_url } 传入路径
		 * @return String 传出路径
		 */
		getProUrl: function(new_url) {
			// 初始化
			var old_url = window.location.href;
			var lastIndex = old_url.lastIndexOf("\/");

			// 检验新路径源
			new_url = iServiceUrl(new_url) ? encodeURI(new_url) : new_url;

			// URL路径验证 http 协议
			function iServiceUrl (str_url) { 
				var strRegex = '^((https|http)?:)',
					regex = new RegExp(strRegex);

				// 检测路径
				if (regex.test(str_url)) { 
					return true; 
				} else { 
					return false; 
				} 
			}

			// 检验路径源
			if(iServiceUrl(old_url)) {

				var userAgent = navigator.userAgent,
					isOpera = userAgent.indexOf("Opera") > -1;

				// 检测是否IE浏览器
				if(userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1 && !isOpera) {
					
					// 若是绝对路径，则返回原路径
					return encodeURI(old_url.substr(0, lastIndex +1)) + encodeURI(new_url);
				} else {
					return new_url;
				}
			} else {

				return new_url;
			}
		},

		/**
		 * 更换皮肤效果
		 * @param  {String} basePath    皮肤路径
		 * @param  {String} defaultSkin 默认
		 */
		switchSkin: function(basePath,defaultSkin){

			/*设置Cookie*/
			function jSetCookie(cName,cssPath,expiredays){
				$.cookie(cName,cssPath,{expires:expiredays});
			}

			/* 获取Cookie*/ 
			function jGetCookie(cName) {
				var cssPath = $.cookie(cName);
				return cssPath;
			}

			/* 递归换肤*/  
			function changeSkin(winObj, cssPath) {
			    var frames = winObj.frames; 
			    for (var i = 0; i < frames.length; i++) { 
			        if (frames[i].frames.length > 0) {  
			            //判断页面中是否存在iframe              
			            var iframes = frames[i].document.getElementsByTagName("iframe");   
			            if(iframes.length > 0){  
			                var linkObj = frames[i].document.getElementById("style"); //获取link对象   
			                if (linkObj != null) {   
			                    linkObj.href = basePath + "/theme/" + cssPath + ".css";   
			                }   
			            }    
			            changeSkin(frames[i], cssPath);//递归换肤
			        } else {   
			            //非框架页面,换肤 
			            var linkObj = frames[i].document.getElementById("style"); //获取link对象 
			            if (linkObj != null) { 
			                linkObj.href = basePath + "/theme/" + cssPath + ".css";   
			            }
			        }   
			    }
			}

			$(".skin-change").on("mouseover mouseout",function(event){
				if(isMouseLeaveOrEnter(event, this)){
					var obj = $(this).find(".skin-change-acitve");
					var targetObj = $(this).find(".skin-changelist");
					var arrow = obj.find(".arrow");
					if(event.type == 'mouseover'){
				  		obj.addClass("on");
						arrow.css("visibility","visible");
				  		targetObj.show();
					}else if(event.type == 'mouseout'){
				  		obj.removeClass("on");
						arrow.css("visibility","hidden");
				  		targetObj.hide();
					}
				}
			});

			/*换肤下拉事件*/
			$(".skin-changelist a").click(function(){
				if($(this).children("i").hasClass("checked")) return;
		  		$(this).children("i").addClass("checked");
		  		$(this).siblings("a").children("i").removeClass("checked");
		  		var skin = $(this).attr("title");
			    if(skin != null && skin != ""){
					$("link#style").attr("href",basePath + "/theme/" + skin + ".css");

					changeSkin(window.top, skin); //换肤
				}
				jSetCookie("cssPath", skin, 365); //设置cookie
		  	});
	  
			var cssPath = jGetCookie("cssPath"); //获取默认皮肤路径   
			//判断用户Cookie中是否有路径,无采用默认
			if(cssPath != null && cssPath != ""){
				// var h = $('<link id="style" rel="stylesheet" type="text/css" href="css/theme/'+ cssPath +'/skin.css" />').appendTo($("head"));
				$("link#style").attr("href",basePath + "/theme/" +  cssPath + ".css");
				changeSkin(window.top, cssPath); //换肤
				//皮肤选中状态返回
				$(".skin-changelist a[title='"+ cssPath +"']").children("i").addClass("checked").end().siblings("a").children("i").removeClass("checked");
			}else{
				$(".skin-changelist a[title='"+ defaultSkin +"']").children("i").addClass("checked").end().siblings("a").children("i").removeClass("checked");
			}
		},
	});

	/**
	 * JQuery Extend Attribute
	 */
	$.fn.extend({

		// 侧边栏
		sidebarEffect: function(options){
			var defaults = {
				activeClass:"active", 
				speed:"normal",
				treesidebar:false,
				defultIndex:0,
			}

			var opts = $.extend(defaults, options);
			return this.each(function(){
				var $siderbar = $(this);
				if(!opts.defultIndex){
					$($siderbar.find("dd")[opts.defultIndex]).addClass("slide").show().find("li:first").addClass("active");
					$($siderbar.find("dt")[opts.defultIndex]).addClass("active");
				}
				$siderbar.on('click','dt',function(event){ //根节点
					if(!$(this).hasClass("active")){
						$(".slide").slideUp().removeClass("slide").prev("dt").find(".arrow.hide").removeClass("hide").siblings(".arrow").addClass("hide");
						$siderbar.find("dt.active").removeClass("active");
						$(this).addClass("active");
					}
					$(this).next().slideToggle(opts.speed, function(){
						if($(this).is(":visible")){
							$(this).addClass("slide");
						}else{
							$(this).removeClass("slide");
						}
						$(this).prev("dt").find(".arrow.hide").removeClass("hide").siblings(".arrow").addClass("hide");
					});
				});

				$siderbar.on('click','li',function(event){
					if(opts.treesidebar){
						treeNode($siderbar,$(this));
						event.stopPropagation(); //阻止事件冒泡
					}else{
						if($(this).parents(".sidebar-sublist").length > 0){
							$siderbar.find("li").removeClass("active");
							$(this).addClass('active').parents("li:first").addClass('active');
						}else{
							if($(this).children(".sidebar-sublist").length <= 0){
								$siderbar.find("li").removeClass("active");
								$(this).addClass('active');
							}
						}
					}
				});
				if(!opts.treesidebar){
					subMenuHover($siderbar,".sidebar-sublist");
				}
				sidebarToggle();
			});

			function subMenuHover($target,$slide){
				$target.on('mouseover mouseout','dd>ul>li',function(event){
					if(event.type == 'mouseover'){
						$(this).addClass("hover");
						$(this).find($slide).show();
					}else if(event.type == 'mouseout'){
						$(this).removeClass("hover");
						$(this).find($slide).hide();
					}
				});
			}

			function treeNode($target,node){
				var tree = $(".sidebar-submenu", node);
				var parentLi = node.parents("li:first");
				if (tree.size()>0) { //存在子节点
					if(tree.is(":visible")){
						tree.hide();
						node.removeClass((tree.hasClass("parent-active")?"":"expand")).find(".arrow.hide").removeClass("hide").siblings(".arrow").addClass("hide");
					}else{
						var expand = $target.find(".expand").not($(this));
						expand.removeClass("expand").find(".sidebar-submenu").hide();
						expand.find(".arrow.hide").removeClass("hide").siblings(".arrow").addClass("hide");
						tree.show();
						node.addClass("expand").find(".arrow.hide").removeClass("hide").siblings(".arrow").addClass("hide");
					}
				}else{//叶子节点
					var expand = $target.find(".expand").not(parentLi);//操作对象非父对象
					var parentActive = $target.find(".parent-active").not(parentLi);
					if(parentLi.length > 0){//是否存在父节点
						if(expand.hasClass("parent-active")){
							expand.removeClass("parent-active");
						}
						parentLi.addClass("parent-active");
						parentActive.removeClass("parent-active");
					}else{
						$target.find(".parent-active").removeClass();
						expand.removeClass("expand").find(".sidebar-submenu").hide();
						expand.find(".arrow.hide").removeClass("hide").siblings(".arrow").addClass("hide");
					}
					$target.find("li.active").removeClass("active"); //叶子节点移除样式
					node.addClass("active");
				}
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
		},

		// jPanel
		jPanel: function(options){
	      	var op = $.extend({header:"panel-header", headerC:"panel-header-cont", content:"panel-cont", coll:"icon icon-lineup", exp:"icon icon-linedown", footer:"panel-footer", footerC:"panel-footer-cont"}, options);
			
			return this.each(function(){
				var $panel = $(this);
				var close = $panel.hasClass("close");
				var collapse = $panel.hasClass("collapse");

				var $content = $(">div", $panel).addClass(op.content);        
				var title = $(">h1",$panel).wrap('<div class="'+op.header+'"><div class="'+op.headerC+'"></div></div>');
				if(collapse)$("<i></i>").addClass(close?op.exp:op.coll).insertAfter(title);

				var header = $(">div:first", $panel);
				var footer = $('<div class="'+op.footer+'"><div class="'+op.footerC+'"></div></div>').appendTo($panel);

				var defaultH = $panel.attr("defH")?$panel.attr("defH"):0;
				var minH = $panel.attr("minH")?$panel.attr("minH"):0;
				if (close) 
					$content.css({
						display: "none"
					});
				else {
					if (defaultH > 0) 
						$content.height(defaultH + "px");
					else if(minH > 0){
						$content.css("minHeight", minH+"px");
					}
	        	}
				if(!collapse) return;

				var $pucker = $("i", header);
				var inH = $content.innerHeight() - 6;

				if(minH > 0 && minH >= inH) defaultH = minH;
				else defaultH = inH;
				$pucker.click(function(){
					if($pucker.hasClass(op.exp)){
						$content.slideDown('400', function() {
							$pucker.removeClass(op.exp).addClass(op.coll);
							if(minH > 0) $content.css("minHeight",minH+"px");
						});
					} else { 
						if(minH > 0) $content.css("minHeight","");
						if(minH >= inH) $content.css("height", minH+"px");
						$content.slideUp(400, function(){
							$pucker.removeClass(op.coll).addClass(op.exp);
						});
	          		}
					return false;
				});
			});
	    },

	    /**
		 * options: reverse[true, false], eventType[click, hover], currentIndex[default index 0]
		 * stTab[tabs selector], stTabPanel[tab panel selector]
		 * ajaxClass[ajax load], closeClass[close tab]
		 */ 
		tabs: function(options){
			var op = $.extend({reverse:false, eventType:"click", currentIndex:0, stTabHeader:"> .tab-header", stTab:">ul", stTabPanel:"> .tab-contbox", ajaxClass:"j-ajax", closeClass:"close", prevClass:"tabsLeft", nextClass:"tabsRight"}, options);
		  
			return this.each(function(){
				initTab($(this));
			});
		  
			function initTab(jT){
				var jSelector = jT.add($("> *", jT));
				var jTabHeader = $(op.stTabHeader, jSelector);
				var jTabs = $(op.stTab + " li", jTabHeader);
				var jGroups = $(op.stTabPanel + " > *", jSelector);

				jTabs.unbind().find("a").unbind();
				jTabHeader.find("."+op.prevClass).unbind();
				jTabHeader.find("."+op.nextClass).unbind();
		    
				jTabs.each(function(iTabIndex){
					if(op.currentIndex == iTabIndex) 
						$(this).addClass("cur");
					else 
						$(this).removeClass("cur");
				  
					if(op.eventType == "hover") 
						$(this).hover(function(event){switchTab(jT, iTabIndex)});
					else 
						$(this).click(function(event){switchTab(jT, iTabIndex)});

					$("a", this).each(function(){
						if ($(this).hasClass(op.ajaxClass)) {
				    		$(this).click(function(event){
								var jGroup = jGroups.eq(iTabIndex);
								if (this.href && !jGroup.attr("loaded")) 
									jGroup.load(this.href,{},function(){
										jGroup.find("[layoutH]").layoutH();
										jGroup.attr("loaded",true);
									});
				        		event.preventDefault();
				    		});
				      
				    	} else if($(this).hasClass(op.closeClass)) {
				    		$(this).click(function(event){
					    		jTabs.eq(iTabIndex).remove();
								jGroups.eq(iTabIndex).remove();
								if (iTabIndex == op.currentIndex) {
						          op.currentIndex = (iTabIndex+1 < jTabs.size()) ? iTabIndex : iTabIndex - 1;
						        } else if (iTabIndex < op.currentIndex){
						          op.currentIndex = iTabIndex;
						        }
				        		initTab(jT);
				        		return false;
				    		});
				    	}
					});
				});
				switchTab(jT, op.currentIndex);
			}
		  
			function switchTab(jT, iTabIndex){
				var jSelector = jT.add($("> *", jT));
				var jTabHeader = $(op.stTabHeader, jSelector);
				var jTabs = $(op.stTab + " li", jTabHeader);
				var jGroups = $(op.stTabPanel + " > *", jSelector);

				var jTab = jTabs.eq(iTabIndex);
				var jGroup = jGroups.eq(iTabIndex);
				if (op.reverse && (jTab.hasClass("cur") )) {
					jTabs.removeClass("cur");
					jGroups.hide();
				} else {
					op.currentIndex = iTabIndex;
					jTabs.removeClass("cur");
					jTab.addClass("cur");
				  
					jGroups.hide().eq(op.currentIndex).show();
				}
			}
		},

		visTabs: function(options){
			var op = $.extend({reverse:false, eventType:"click", currentIndex:0, stTabHeader:"> .tab-header", stTab:">ul", stTabPanel:"> .tab-contbox", ajaxClass:"j-ajax", closeClass:"close", prevClass:"tabsLeft", nextClass:"tabsRight"}, options);
		  
			return this.each(function(){
				initTab($(this));
			});
		  
			function initTab(jT){
				var jSelector = jT.add($("> *", jT));
				var jTabHeader = $(op.stTabHeader, jSelector);
				var jTabs = $(op.stTab + " li", jTabHeader);
				var jGroups = $(op.stTabPanel + " > *", jSelector);

				jTabs.unbind().find("a").unbind();
				jTabHeader.find("."+op.prevClass).unbind();
				jTabHeader.find("."+op.nextClass).unbind();
		    
				jTabs.each(function(iTabIndex){
					if(op.currentIndex == iTabIndex) 
						$(this).addClass("cur");
					else 
						$(this).removeClass("cur");
				  
					if(op.eventType == "hover") 
						$(this).hover(function(event){switchTab(jT, iTabIndex)});
					else 
						$(this).click(function(event){switchTab(jT, iTabIndex)});

					$("a", this).each(function(){
						if ($(this).hasClass(op.ajaxClass)) {
				    		$(this).click(function(event){
								var jGroup = jGroups.eq(iTabIndex);
								if (this.href && !jGroup.attr("loaded")) 
									jGroup.load(this.href,{},function(){
										jGroup.find("[layoutH]").layoutH();
										jGroup.attr("loaded",true);
									});
				        		event.preventDefault();
				    		});
				      
				    	} else if($(this).hasClass(op.closeClass)) {
				    		$(this).click(function(event){
					    		jTabs.eq(iTabIndex).remove();
								jGroups.eq(iTabIndex).remove();
								if (iTabIndex == op.currentIndex) {
						          op.currentIndex = (iTabIndex+1 < jTabs.size()) ? iTabIndex : iTabIndex - 1;
						        } else if (iTabIndex < op.currentIndex){
						          op.currentIndex = iTabIndex;
						        }
				        		initTab(jT);
				        		return false;
				    		});
				    	}
					});
				});
				switchTab(jT, op.currentIndex);
			}
		  
			function switchTab(jT, iTabIndex){
				var jSelector = jT.add($("> *", jT));
				var jTabHeader = $(op.stTabHeader, jSelector);
				var jTabs = $(op.stTab + " li", jTabHeader);
				var jGroups = $(op.stTabPanel + " > *", jSelector);

				var jTab = jTabs.eq(iTabIndex);
				var jGroup = jGroups.eq(iTabIndex);
				if (op.reverse && (jTab.hasClass("cur") )) {
					jTabs.removeClass("cur");
					jGroups.css({'visibility':'hidden', 'height': '0px'});
				} else {
					op.currentIndex = iTabIndex;
					jTabs.removeClass("cur");
					jTab.addClass("cur");
					jGroups.css({'visibility': 'hidden', 'height': '0px'})
							.eq(op.currentIndex).css({'visibility':'visible', 'height': 'auto'});
				}
			}
		},

		loadUrl: function(url,data,callback,type){
			//增加loadUrl指定加载类型 yh 2015.6.25
			var op={url:url, data:data, callback:callback};
			if(type) op.type=type;
			$(this).ajaxUrl(op);
		},

		/**
		 * adjust component inner reference box height
		 * @param {Object} refBox: reference box jQuery Obj
		 */
		layoutH: function($refBox){
			return this.each(function(){
				var $this = $(this);
				if (! $refBox) $refBox = $this.parents("div.layoutBox:first");
				var iRefH = $refBox.height();
				var iLayoutH = parseInt($this.attr("layoutH"));
				var iH = iRefH - iLayoutH > 50 ? iRefH - iLayoutH : 50;

				if ($this.isTag("table")) {
					$this.removeAttr("layoutH").wrap('<div layoutH="'+iLayoutH+'" style="overflow:auto;height:'+iH+'px"></div>');
				} else {
					$this.height(iH).css("overflow","auto");
				}
			});
		},

		isTag: function(tn) {
			if(!tn) return false;
			return $(this)[0].tagName.toLowerCase() == tn ? true:false;
		},

		// 多行文字截取
		limittext: function(opt){
			opt=$.extend({"limit":30,"fill":"..."},opt);

			opt.morefn=$.extend({
				"status": false,//是否启用更多
				"moretext": "更多",//隐藏部分文字时候显示的文字
				"lesstext":"(less)",//全部文字时候显示的文字
				"cssclass": "limittextclass",//启用更多的A标签的CSS类
				"lessfn": function(){},//当文字为更少显示时候回调
				"fullfn": function(){}//当文字为更多时候回调函
			},opt.morefn);

			var othis=this;
			var $this=$(othis);
			var body=$this.data('body');

			if(body==null){
				body=$this.html();
				$this.data('body',body);
			}
			var getbuttom=function(showtext){
				return "<a href='javascript:;' class='"
				+opt.morefn.cssclass+"'>"
				+showtext
				+"<a>";
			}
			this.limit=function(limit){
				if(body.length<=limit||limit=='all'){
					var showbody=body+(opt.morefn.status?getbuttom(opt.morefn.lesstext):"");
				}else{
					if(!opt.morefn.status){
						var showbody=body.substring(0,limit)
						+opt.fill;
					} else {
						var showbody=body.substring(0,limit)
						+opt.fill
						+getbuttom(opt.morefn.moretext);
					}
				}
				$this.html(showbody);
			}
			this.limit(opt.limit);
			$("."+opt.morefn.cssclass).live("click",function(){
				if($(this).html()==opt.morefn.moretext){
					showbody=body
					+getbuttom(opt.morefn.lesstext);
					$this.html(showbody);
					opt.morefn.fullfn();
				}else{
					othis.limit(opt.limit);
					opt.morefn.lessfn();
				}
			});
			return this;
		},

		// 表格隔行变色
		alterBgColor: function(options){
			//设置默认值
			option=$.extend({
				odd:"odd",
				even:"even"
			},options);

			//隔行变色
			$("tbody>tr:even",this).addClass(option.even);
			$("tbody>tr:odd",this).addClass(option.odd);
			return this; //返回this，使方法可链
		},

		/**
		 * 菜单跳转扩展
		 * 自带切换页轮播
		 */
		menuLinkExtend: function(options) {
			// 参数初始化
			var defaults = {
				contain: ".main-tab ul", // 盒容器
				childItem: "li", // 列表容器
				dataItem: "a", // 数据容器
				closeItem: ".tab-close", // 关闭标签
				menuClick: "#sidebar .sidebar-list dd a", // 菜单点击
				rightMenu: ['closeThis', 'closeOthers', 'closeAll'], // 部署不同的功能及排序
			}
			// 初始化
			var opts = $.extend(defaults, options),
				$container = $(this),
				$btn_before = $('<div class="tab-btn tab-before"></div>'),
				$btn_after = $('<div class="tab-btn tab-after"></div>');

			// Dom 操作
			if($container.find('.tab-before').length == 0 ) {
				$btn_before.prependTo($container);
				$btn_after.appendTo($container);
			}

			// 菜单点击事件绑定
			$(opts.menuClick).on('click', function() {
				// 初始化
				var _this = $(this).closest(opts.dataItem),
					_href = _this.attr('_href'),
					_name = _this.data('name');

				// 检测是否有链接地址
				if(!_href) {
					console && console.log("lose the _href");
					return false;
				}

				// 检测是否有名称
				if(!_name) {
					console && console.log("lose the name");
					return false;
				}

				// 构建头部标签
				var _titleTab = '<a href="javascript:;" _href="'+ _href +'">'+ _name +'</a>';

				// 计算位置的数据
				var _num = $container.find(opts.contain).find(opts.childItem).length;

				// 往 tab 增加标签
				if(_this.length == 1 && _href) {
					// 标记是否存在
					var tag = 0;
					for(var i = 0; i < _num; i++) {
						var item = $container.find(opts.contain).find(opts.childItem).eq(i).find(opts.dataItem);
						var item_href = item.attr('_href');
						var item_name = item.text();
						// 判断是否重叠
						if(item_href === _href && item_name === _name) {
							tag = 1;
							$container.find(opts.contain).find(opts.childItem).eq(i).addClass('active').siblings(opts.childItem).removeClass('active');
							setPosition(i);
							break;
						}
					}

					// 判断
					if(tag == 0) {
						// 增加新标签
						$container.find(opts.contain).find(opts.childItem).removeClass('active');
						$container.find(opts.contain).append('<li class="active">'+_titleTab+'<i class="tab-close"></i></li>');

						// 初始化事件
						_initEvent();
					}

					// 点击菜单时，定位到具体的标签兼左右切换效果
					function setPosition(i) {
						// 1、先判断宽度值是否超出 2、判断该标签的位置 是否在显示区域内
						var container_width = $container.outerWidth();
						var li_width = $container.find(opts.contain).find(opts.childItem).outerWidth();
						var ul_width = $container.find(opts.contain).find(opts.childItem).outerWidth() * $container.find(opts.contain).find(opts.childItem).length;
						var adjust_num = 24;

						// 获取 margin_left 的值
						var ul_margin = $container.find(opts.contain).css('marginLeft');
						var left_length = ul_margin.toString().replace('px', '');
						var i_length = Number(li_width) * (i + 1);

						// 判断是否超出，并且在隐藏区域
						if(container_width < ul_width && ((Math.abs(left_length) > i_length) || i_length > container_width) ) {
							// 判断是左侧超出还是右侧超出，若是，则左侧超出
							if(i_length < container_width ) {
								// 样式初始化
								$container.find('ul').animate({
									'marginLeft': - (Number(li_width) * i) + 'px'
								}, 500);
							} else {
								// 样式初始化
								$container.find('ul').animate({
									'marginLeft': container_width - i_length - adjust_num + 'px'
								}, 500);
							}
						}
					}
				}
			});

			// 初始化事件
			_initEvent();

			// 窗口调整
			window.onresize = function() {
				_initEvent();
			}

			function _initEvent() {
				// 宽度计算
				_widthCount();

				// 切换页面标签
				$container.find(opts.contain).find("a").off("click").on("click", function(){
					if($(this).attr('_href')){
						var _href = $(this).attr('_href');
						// 样式改变
						var _style = $(this).parent().addClass('active').siblings('li').removeClass('active');
						$.openWindow({
							url: _href
						});
					}
				});

				// 关闭页面标签
				$container.find(opts.contain).find(opts.closeItem).off('click').on('click', function() {
					// 初始化
					var $current = $(this),
						$currentItem = $current.parents(opts.childItem),
						index = $currentItem.index();

					// 挪除 Item
					$currentItem.remove();

					// 检测当前页面是否为移除页面标签
					if($currentItem.hasClass('active')) {
						var _this = $container.find(opts.childItem).eq(--index).find("a");
						$container.find(opts.childItem).eq(index).addClass('active');

						// 判断路径是否存在
						if(_this.attr('_href')){
							var _href = _this.attr('_href'),
								_loadJs = _this.data('js');

							// 打开窗口
							$.openWindow({
								url:_href,
								success: function() {
									// 加载常用的脚本依赖
									if(_loadJs) {
										var scripts = _loadJs.split(' ');
										$.downloadScripts({'scriptsName':scripts});
									}
								} 
							});
						}
					}

					// 宽度计算
					_widthCount();
				});

				// 右击出现菜单栏
				$container.find(opts.contain).find("a").off('mousedown').on('mousedown', function(event) {
					// 初始化
					var $current = $(this);

					// 检测
					if(!event) event = window.event;

					// 是否鼠标右击事件
					if(event.button == 2) {
						// 获取点击的位置，显示菜单
						// console && console.log('menu');

						// 右侧菜单栏
						_rightMenu($current, event.clientX, event.clientY);
					}

					// 禁止冒泡
					return false;
				});

				// 阻止右侧默认菜单栏
				$container.find(opts.contain).find("a").off('contextmenu').on('contextmenu', function(event) {
					if (document.all) window.event.returnValue = false;
					else event.preventDefault();
				});

				// 宽度计算，是否显示左右切换条
				function _widthCount() {
					var container_width = $container.outerWidth();
					var li_width = $container.find(opts.contain).find(opts.childItem).outerWidth();
					var ul_width = $container.find(opts.contain).find(opts.childItem).outerWidth() * $container.find(opts.contain).find(opts.childItem).length;
					var adjust_num = 24;
					if(container_width < ul_width) {
						$container.find('.main-tab').animate({
							marginLeft: '18px',
							marginRight: '18px'
						}, 500);

						// 样式初始化
						$container.find('.tab-before').removeClass('disabled').css('display','block');
						$container.find('.tab-after').addClass('disabled').css('display','block');
						$container.find(opts.contain).animate({
							'width': ul_width + 'px',
							'marginLeft': container_width - ul_width - adjust_num + 'px'
						}, 500);

					} else {
						$container.find('.main-tab').animate({
							marginLeft: '0px',
							marginRight: '0px'
						}, 500);

						// 样式初始化
						$container.find('.tab-before').removeClass('disabled').css('display', 'none');
						$container.find('.tab-after').removeClass('disabled').css('display', 'none');
						$container.find(opts.contain).animate({
							'marginLeft': '0px'
						});
						// 宽度自动化
						$container.find(opts.contain).css('width', 'auto');
					}

					// 按钮切换向前滑动
					$container.find('.tab-before').off('click').on('click', function() {
						var ul_margin = $container.find(opts.contain).css('marginLeft');
						var left_length = ul_margin.toString().replace('px', '');
						// 判断是否能向左滑动
						if(left_length < 0) {
							$container.find(opts.contain).animate({
								'marginLeft': (Number(left_length) + Number(li_width) > 0) ? '0px' : Number(left_length) + Number(li_width) + 1 + 'px'
							}, 500);
						}

						// 样式改变
						(Number(left_length) + Number(li_width) > 0) ? $(this).addClass('disabled') : $(this).removeClass('disabled');
						$container.find('.tab-after').removeClass('disabled');
					});

					// 按钮切换向后滑动
					$container.find('.tab-after').off('click').on('click', function() {
						var ul_margin = $container.find(opts.contain).css('marginLeft');
						var left_length = ul_margin.toString().replace('px', '');
						var left_total = container_width - ul_width - adjust_num;
						// 判断是否能向左滑动
						if(left_length > left_total) {
							$container.find(opts.contain).animate({
								'marginLeft': (Number(left_length) - Number(li_width) < left_total) ? left_total + 'px' : Number(left_length) - Number(li_width) + 1 + 'px'
							}, 500);
						}

						// 样式改变
						(Number(left_length) - Number(li_width) < left_total) ? $(this).addClass('disabled') : $(this).removeClass('disabled');
						$container.find('.tab-before').removeClass('disabled');
					});
				}

				// 右侧菜单栏
				function _rightMenu(cont, x, y) {
					// 构建DOM
					if($container.find('.tab-menu').length == 0) {
						// 初始化DOM
						var $tabMenu = $('<div class="tab-menu"></div>');
						var list = opts.rightMenu;

						$tabMenu.append('<ul></ul>');

						for(var i = 0; i < list.length; i++) {

							// 查找对应菜单名，添加该功能
							switch(list[i]) {
								case 'closeThis':
									$tabMenu.find('ul').append('<li data-tag="this">关闭标签页</li>');
									break;
								case 'closeOthers':
									$tabMenu.find('ul').append('<li data-tag="others">关闭其他标签页</li>');
									break;
								case 'closeAll':
									$tabMenu.find('ul').append('<li data-tag="all">关闭所有标签页</li>');
									break;
								default:
									console && console.log('未找到该标签名');
							}
						}

						$tabMenu.appendTo($container);
					} else {
						$tabMenu = $container.find('.tab-menu');
					}

					// 右侧菜单位置
					$tabMenu.css({
						"display": "block",
						"top": y + "px",
						"left": x + "px"
					});

					// DOM 点击其他处隐藏
					$(document).off('click').on('click', function() {
						if($tabMenu.is(':visible')) {
							$tabMenu.css('display', 'none');
						}
					});

					// 禁止默认菜单栏
					$tabMenu.off('contextmenu').on('contextmenu', function(event) {
						if (document.all) window.event.returnValue = false;
						else event.preventDefault();
					});

					// 标签菜单事件
					$tabMenu.find('li').off('click').on('click', function() {
						var tag = $(this).data('tag');
						switch(tag) {
							case 'this':
								_closeThisTag();
								break;
							case 'others':
								_closeOthersTag();
								break;
							case 'all':
								_closeAllTag();
								break;
							default:
								console && console.log('未找到该标签标记');
						}
					});

					// 关闭当前标签页
					function _closeThisTag() {
						// 初始化
						var $current = cont,
							$currentItem = $current.parents(opts.childItem),
							index = $currentItem.index();

						// 消失
						$('.tab-menu').css('display', 'none');

						// 主页禁止挪除
						if($currentItem.find('i').length == 0) {
							return false;
						}

						// 挪除 Item
						$currentItem.remove();

						// 检测当前页面是否为移除页面标签
						if($currentItem.hasClass('active')) {
							var _this = $container.find(opts.childItem).eq(--index).find("a");
							$container.find(opts.childItem).eq(index).addClass('active');

							// 判断路径是否存在
							if(_this.attr('_href')){
								var _href = _this.attr('_href'),
									_loadJs = _this.data('js');

								// 打开窗口
								$.openWindow({
									url:_href,
									success: function() {
										// 加载常用的脚本依赖
										if(_loadJs) {
											var scripts = _loadJs.split(' ');
											$.downloadScripts({'scriptsName':scripts});
										}
									} 
								});
							}
						}

						// 宽度计算
						_widthCount();
					}

					// 关闭其他标签页
					function _closeOthersTag() {
						// 初始化
						var $current = cont,
							$currentItem = $current.parents(opts.childItem),
							index = $currentItem.index();

						// 消失
						$('.tab-menu').css('display', 'none');

						// 是否选中
						if(!$currentItem.hasClass('active')) {
							var _this = $currentItem.addClass('active').find("a");

							// 判断路径是否存在
							if(_this.attr('_href')){
								var _href = _this.attr('_href'),
									_loadJs = _this.data('js');

								// 打开窗口
								$.openWindow({
									url:_href,
									success: function() {
										// 加载常用的脚本依赖
										if(_loadJs) {
											var scripts = _loadJs.split(' ');
											$.downloadScripts({'scriptsName':scripts});
										}
									} 
								});
							}
						}

						// 移除其他标签(除主页外)
						$currentItem.siblings(opts.childItem).each(function(i, con) {
							if($(con).find('i').length != 0) {
								$(con).remove();
							}
						});

						// 宽度计算
						_widthCount();
					}

					// 关闭所有标签页
					function _closeAllTag() {
						// 初始化
						var $currentItem = $container.find(opts.contain).find(opts.childItem).eq(0);

						// 消失
						$('.tab-menu').css('display', 'none');

						// 是否选中
						if(!$currentItem.hasClass('active')) {
							var _this = $currentItem.addClass('active').find("a");

							// 判断路径是否存在
							if(_this.attr('_href')){
								var _href = _this.attr('_href'),
									_loadJs = _this.data('js');

								// 打开窗口
								$.openWindow({
									url:_href,
									success: function() {
										// 加载常用的脚本依赖
										if(_loadJs) {
											var scripts = _loadJs.split(' ');
											$.downloadScripts({'scriptsName':scripts});
										}
									} 
								});
							}
						}

						// 移除其他标签(除主页外)
						$currentItem.siblings(opts.childItem).remove();

						// 宽度计算
						_widthCount();
					}
				}
			}
		},

		/**
		 * 内容标签点击扩展
		 * (附带头部标签显示)
		 */
		contentLink: function(_item) {

			// 检测是否存在点击元素
			if(!$(this).length) {
				return false;
			}

			// 初始化
			var _this = $(this),
				_item = _item ? _item : 'a';
				_tagName = (_this[0].tagName.toLowerCase() == _item) ? true : false,
				_selector = _this.selector;

			// 检验菜单标签是否为 a 标签
			if(_tagName) {
				// 菜单扩展
				$("#main-tab").menuLinkExtend({
					contain: ".main-tab ul", // 盒容器
					childItem: "li", // 列表容器
					dataItem: _item, // 数据容器
					closeItem: ".tab-close", // 关闭标签
					menuClick: _selector, // 菜单点击
					rightMenu: ['closeThis', 'closeOthers', 'closeAll'], // 部署不同的功能及排序
				});
			}

			// 点击内容加载
			_this.on('click', function() {
				var	_href = $(this).attr('_href');
				
				// 检测链接路径是否存在
				if(_href) {
					$.openWindow({ url:_href });
				}
			});
		}
	});
});

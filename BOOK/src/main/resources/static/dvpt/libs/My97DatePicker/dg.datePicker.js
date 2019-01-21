define(['jquery'],function($) {

	$.fn.extend({
		/**
		 * datePicker 调用日历
		 * @param  langList  语言包
		 * @param  skinList  皮肤包
		 * @param  wdate 是否自动引入 Wdate 样式类 Wdate可在skin目录下的WdatePicker.css文件中定义
		 * @param  crossFrame 是否跨框架
		 * @param  preLoad 是否预加载
		 * @param  dpPath 是否显示指定程序包的绝对位置, 防止极少情况下出错
		 * @param  doubleCalendar 是否是双月模式
		 * @param  enableKeyboard 键盘控制开关
		 * @param  enableInputMask 文本框输入启用掩码开关
		 * @param  autoUpdateOnChanged 在修改年月日时分秒等元素时,自动更新到el,默认是关闭的(即:需要点击确定或点击日期才更新)
		 * @param  weekMethod ISO8601:规定第一个星期四为第一周,默认值: 4
		 * @param  position 显示位置
		 * @param  lang 当值为'auto'时 自动根据客户端浏览器的语言自动选择语言
		 * @param  skin 默认为default
		 * @param  dateFmt 日期显示格式
		 * @param  realDateFmt/realTimeFmt/realFullFmt 计算机可识别的,真正的日期格式，一般为默认值
		 * @param  startDate 起始时间
		 * @param  isShowWeek 是否显示周
		 * @param  highLineWeekDay 周末日期高亮
		 * @param  alwaysUseStartDate 是否总使用起始时间
		 * @param  isShowWeek/isShowToday/isShowOK 三个按钮参数配置是否显示
		 * @param  isShowOthers 为true时,第一行空白处显示上月的日期，末行空白处显示下月的日期,否则不显示
		 * @param  readOnly 是否只读
		 * @param  errDealMode 纠错模式设置 可设置3中模式 0 - 提示 1 - 自动纠错 2 - 标记
		 * @param  autoPickDate 为false时 点日期的时候不自动输入,而是要通过确定才能输入
								为true时 即点击日期即可返回日期值
								为null时(推荐使用) 如果有时间置为false 否则置为true
		 * @param  qsEnabled 是否启用快速选择功能
		 * @param  autoShowQS 是否默认显示快速选择
		 * @param  opposite 默认为false, 为true时,无效日期变成有效日期
		 * @param  hmsMenuCfg 可以实现时分秒菜单的自定义
		 * @param  specialDates... 自定义事件
		 */
		datePicker: function(options) {
			var defaults = {
				langList: [
					{ name: "en", charset: "UTF-8" },
					{ name: "zh-cn", charset: "gb2312" },
					{ name: "zh-tw", charset: "GBK" }
				],
				skinList: [
					{ name: "default", charset: "gb2312" },
					{ name: "whyGreen", charset: "gb2312" },
					{ name: "blue", charset: "gb2312" },
					{ name: "green", charset: "gb2312" },
					{ name: "simple", charset: "gb2312" },
					{ name: "ext", charset: "gb2312" },
					{ name: "blueFresh", charset: "gb2312" },
					{ name: "twoer", charset: "gb2312" },
					{ name: "YcloudRed", charset: "gb2312" }
				],
				wdate: true,
				crossFrame: true,
				preLoad: false,
				dpPath: "",
				doubleCalendar: false,
				enableKeyboard: true,
				enableInputMask: true,
				autoUpdateOnChanged: null,
				weekMethod: "ISO8601",
				position: {},
				lang: "auto",
				skin: "default",
				dateFmt: "yyyy-MM-dd",
				realDateFmt: "yyyy-MM-dd",
				realTimeFmt: "HH:mm:ss",
				realFullFmt: "%Date %Time",
				minDate: "1900-01-01 00:00:00",
				maxDate: "2099-12-31 23:59:59",
				startDate: "",
				alwaysUseStartDate: false,
				yearOffset: 1911,
				firstDayOfWeek: 0,
				isShowWeek: false,
				highLineWeekDay: true,
				isShowClear: true,
				isShowToday: true,
				isShowOK: true,
				isShowOthers: true,
				readOnly: false,
				errDealMode: 0,
				autoPickDate: null,
				qsEnabled: true,
				autoShowQS: false,
				opposite: false,
				hmsMenuCfg: { H: [1, 6], m: [5, 6], s: [15, 4] },

				specialDates: null,
				specialDays: null,
				disabledDates: null,
				disabledDays: null,
				onpicking: null,
				onpicked: null,
				onclearing: null,
				oncleared: null,
				ychanging: null,
				ychanged: null,
				Mchanging: null,
				Mchanged: null,
				dchanging: null,
				dchanged: null,
				Hchanging: null,
				Hchanged: null,
				mchanging: null,
				mchanged: null,
				schanging: null,
				schanged: null,
				eCont: null,
				vel: null,
				elProp: "",
				errMsg: "",
				quickSel: [],
				has: {},
				getRealLang: function() {
					var _ = defaults.langList;
					for (var A = 0; A < _.length; A++)
						if (_[A].name == this.lang) return _[A];
					return _[0]
				}
			}

			// 参数初始化
			var options = $.extend(defaults, options);
			var $dp,
				$this = $(this)[0];
			var Y = window,
				T = { innerHTML: "" },
				N = "document",
				H = "documentElement",
				C = "getElementsByTagName",
				V, A, S, G, c, X = navigator.appName;// 浏览器名称
			if (X == "Microsoft Internet Explorer") S = true;
			else if (X == "Opera") c = true;
			else G = true;
			A = options.dpPath || J();
			// 引入布局样式文件
			// if (options.wdate) K(A + "skin/WdatePicker.css");

			V = Y;
			if (options.crossFrame) {
			    try {
			        while (V.parent != V && V.parent[N][C]("frameset").length == 0) V = V.parent 
			    } catch (O) {} 
			}
			if(!V.$dp) V.$dp = { ff: G, ie: S, opera: c, status: 0, defMinDate: options.minDate, defMaxDate: options.maxDate }
			B();
			if (options.preLoad && $dp.status == 0) E(Y, "onload", function() {U(null, true) });
			if (!Y[N].docMD) { E(Y[N], "onmousedown", D, true);
			    Y[N].docMD = true }
			if (!V[N].docMD) { E(V[N], "onmousedown", D, true);
			    V[N].docMD = true }
			E(Y, "onunload", function() {
			    if ($dp.dd) P($dp.dd, "none")
			});

			// 执行调用方法
			U(options);

			// 初始化配置
			function B() {
				try { V[N], V.$dp = V.$dp || {} } catch (options) { V = Y; $dp = $dp || {} }
				var A = {
					win: Y,
					$: function(options) {
				        return (typeof options == "string") ? Y[N].getElementById(options) : options 
				    }, 
				    $D: function(options, _) {
				        return this.$DV(this.$(options).value, _) 
				    }, 
				    $DV: function(_, options) {
				        if (_ != "") { 
				        	this.dt = $dp.cal.splitDate(_, $dp.cal.dateFmt);
				            if (options)
				                for (var B in options)
				                    if (this.dt[B] === undefined) this.errMsg = "invalid property:" + B;
				                    else { this.dt[B] += options[B];
				                        if (B == "M") {
				                            var C = options["M"] > 0 ? 1 : 0,
				                                A = new Date(this.dt["y"], this.dt["M"], 0).getDate();
				                            this.dt["d"] = Math.min(A + C, this.dt["d"]) 
				                        } 
				                    }
				            if (this.dt.refresh()) return this.dt 
				        }
				        return "" 
				    }, 
				    show: function() {
				        var A = V[N].getElementsByTagName("div"),
						$ = 100000;
				        for (var B = 0; B < A.length; B++) {
				            var _ = parseInt(A[B].style.zIndex);
				            if (_ > $) $ = _ }
				        this.dd.style.zIndex = $ + 2;
				        P(this.dd, "block");
				        P(this.dd.firstChild, "");
				    }, 
				    unbind: function(options) { options = this.$(options);
				        if (options.initcfg) { 
				        	L(options, "onclick", function() { U(options.initcfg) });
				            L(options, "onfocus", function() { U(options.initcfg) }) 
				        } 
				    }, 
				    hide: function() { 
				    	P(this.dd, "none") 
				    }, 
				    attachEvent: E 
				};
				for (var _ in A) V.$dp[_] = A[_];
				$dp = V.$dp;
			}
			// 绑定事件
			function E(B, _, A, options) {
				if (B.addEventListener) {
					var C = _.replace(/on/, "");
					A._ieEmuEventHandler = function(options) {
						return A(options);
					}
					B.addEventListener(C, A._ieEmuEventHandler, options) 
				} else 
					B.attachEvent(_, A)
			}
			// 解绑事件
			function L(A, options, _) {
				if (A.removeEventListener) {
					var B = options.replace(/on/, "");
					_._ieEmuEventHandler = function(options) {
						return _(options) 
					}
					A.removeEventListener(B, _._ieEmuEventHandler, false) 
				} else 
					A.detachEvent(options, _) 
			}
			// 验证
			function a(_, options, A) {
				if (typeof _ != typeof options) return false;
				if (typeof _ == "object") {
					if (!A)
						for (var B in _) {
							if (typeof options[B] == "undefined") return false;
							if (!a(_[B], options[B], true)) return false 
						}
						return true 
					} else if (typeof _ == "function" && typeof options == "function") 
						return _.toString() == options.toString();
				else 
					return _ == options 
			}
			// 获取脚本的路径
			function J() {
				var _, A, arr = Y[N][C]("script");
				for (var B = 0; B < arr.length; B++) { 
					_ = arr[B].getAttribute("src") || "";
					_ = _.substr(0, _.toLowerCase().indexOf("dg.datepicker.js"));
					A = _.lastIndexOf("/");
					if (A > 0) 
						_ = _.substring(0, A + 1);
					if (_) break 
				}
				return _ 
			}
			// 追加CSS样式
			function K(A, options, B) {
				var D = Y[N][C]("HEAD").item(0),
				_ = Y[N].createElement("link");
				if (D) { 
					_.href = A;
					_.rel = "stylesheet";
					_.type = "text/css";
					if (options) _.title = options;
					if (B) _.charset = B;
					D.appendChild(_) 
				} 
			}
			// 控制 iframe 的距离
			function F(options) {
				options = options || V;
				var A = 0,
				_ = 0;
				while (options != V) {
					var D = options.parent[N][C]("iframe");
					for (var F = 0; F < D.length; F++) {
						try {
							if (D[F].contentWindow == options) {
								var E = W(D[F]);
								A += E.left;
								_ += E.top;
								break 
							} 
						} catch (B) {} 
					}
					options = options.parent 
				}
				return { "leftM": A, "topM": _ } 
			}
			// 控制距离
			function W(G, F) {
				if (G.getBoundingClientRect) 
					return G.getBoundingClientRect();
				else {
					var A = { ROOT_TAG: /^body|html$/i, OP_SCROLL: /^(?:inline|table-row)$/i },
					E = false,
					I = null,
					_ = G.offsetTop,
					H = G.offsetLeft,
					D = G.offsetWidth,
					B = G.offsetHeight,
					C = G.offsetParent;
				if (C != G)
					while (C) { 
						H += C.offsetLeft;
						_ += C.offsetTop;
						if (R(C, "position").toLowerCase() == "fixed") E = true;
						else if (C.tagName.toLowerCase() == "body") I = C.ownerDocument.defaultView;
						C = C.offsetParent 
					}
					C = G.parentNode;
					while (C.tagName && !A.ROOT_TAG.test(C.tagName)) {
						if (C.scrollTop || C.scrollLeft)
						if (!A.OP_SCROLL.test(P(C)))
						if (!c || C.style.overflow !== "visible") { 
							H -= C.scrollLeft;
							_ -= C.scrollTop 
						}
						C = C.parentNode 
					}
					if (!E) {
						var base = b(I);
						H -= base.left;
						_ -= base.top 
					}
					D += H;
					B += _;
					return { "left": H, "top": _, "right": D, "bottom": B } 
				} 
			}
			// 控制 width height
			function M(options) { 
				options = options || V;
				var B = options[N],
					A = (options.innerWidth) ? options.innerWidth : (B[H] && B[H].clientWidth) ? B[H].clientWidth : B.body.offsetWidth,
					_ = (options.innerHeight) ? options.innerHeight : (B[H] && B[H].clientHeight) ? B[H].clientHeight : B.body.offsetHeight;
				return { "width": A, "height": _ } 
			}
			// 定位 Top Left
			function b(options) { 
				options = options || V;
				var B = options[N],
					A = B[H],
					_ = B.body;
				B = (A && A.scrollTop != null && (A.scrollTop > _.scrollTop || A.scrollLeft > _.scrollLeft)) ? A : _;
				return { "top": B.scrollTop, "left": B.scrollLeft } 
			}
			// 关闭日历
			function D(options) {
				try {
					var _ = options ? (options.srcElement || options.target) : null;
					if ($dp.cal && !$dp.eCont && $dp.dd && _ != $dp.el && $dp.dd.style.display == "block") $dp.cal.close()
				} catch (options) {} 
			}

			function Z() { $dp.status = 2 }
			var Q, _;

			// 执行函数
			function U(K, C) {
				if (!$dp) return;
				B();
				var L = {};
				for (var H in K) L[H] = K[H];
				for (H in options)
					if (H.substring(0, 1) != "options" && L[H] === undefined) L[H] = options[H];
				if (C) {
					if (!J()) { _ = _ || setInterval(function() {
						if (V[N].readyState == "complete") clearInterval(_);
						U(null, true) }, 50);
					return 
					}
					if ($dp.status == 0) { 
						$dp.status = 1;
						L.el = T;
						I(L, true) 
					} else return 
				} else if (L.eCont) { 
					L.eCont = $dp.$(L.eCont);
					L.el = T;
					L.autoPickDate = true;
					L.qsEnabled = false;
					I(L) 
				} else {
					if (options.preLoad && $dp.status != 2) return;
					var F = D();
					if (Y.event === F || F) { 
						L.srcEl = F.srcElement || F.target;
						F.cancelBubble = true 
					}
					L.el = L.el = $dp[(L.el || L.srcEl)] || $this;
					if (!L.el || L.el["My97Mark"] === true || L.el.disabled || ($dp.dd && P($dp.dd) != "none" && $dp.dd.style.left != "-970px")) {
						try {
							if (L.el["My97Mark"]) L.el["My97Mark"] = false 
						} catch (A) {}
						return 
					}
					if (F && L.el.nodeType == 1 && !a(L.el.initcfg, K)) { $dp.unbind(L.el);
						E(L.el, F.type == "focus" ? "onclick" : "onfocus", function() { U(K) });
						L.el.initcfg = K 
					}
					I(L);
				}

				function J() {
					if (S && V != Y && V[N].readyState != "complete") return false;
					return true 
				}

				function D() {
					if (G) { 
						func = D.caller;
						while (func != null) {
							var a = func.arguments[0];
							if (a && (a + "").indexOf("Event") >= 0) return a;
							func = func.caller 
						}
						return null 
					}
					return event 
				} 
			}

			function R(_, options) {
				return _.currentStyle ? _.currentStyle[options] : document.defaultView.getComputedStyle(_, false)[options] 
			}

			function P(_, options) {
				if (_)
					if (options != null) _.style.display = options;
				else return R(_, "display") 
			}

			// 判断是否输入框 若是，则进行追加
			function I(G, _) {
				var D = G.el ? G.el.nodeName : "INPUT";
				if (_ || G.eCont || new RegExp(/input|textarea|div|span|p|a/ig).test(D)) G.elProp = D == "INPUT" ? "value" : "innerHTML";
				else return;
				if (G.lang == "auto") G.lang = S ? navigator.browserLanguage.toLowerCase() : navigator.language.toLowerCase();
				if (!G.eCont)
				    for (var C in G) $dp[C] = G[C];
				if (!$dp.dd || G.eCont || ($dp.dd && (G.getRealLang().name != $dp.dd.lang || G.skin != $dp.dd.skin))) {
				    if (G.eCont) E(G.eCont, G);
				    else {
				    	$dp.dd = V[N].createElement("DIV");
				        $dp.dd.style.cssText = "position:absolute";
				        V[N].body.appendChild($dp.dd);
				        E($dp.dd, G);
				        if (_) $dp.dd.style.left = $dp.dd.style.top = "-970px";
				        else { 
				        	$dp.show();
				            B($dp);
				        } 
				    } 
				} else if ($dp.cal) { 
					$dp.show();
				    $dp.cal.init();
				    if (!$dp.eCont) B($dp) 
				}

				// 日历内容
				function E(K, J) {
				    var I = V[N].domain,
				        F = false,
				        G = "<iframe hideFocus=true width=9 height=7 frameborder=0 border=0 scrolling=no src=\"about:blank\"></iframe>";
				    K.innerHTML = G;
				    var _ = options.langList,
				        D = options.skinList,
				        H;
				    try { H = K.lastChild.contentWindow[N] } catch (E) { F = true;
				        K.removeChild(K.lastChild);
				        var L = V[N].createElement("iframe");
				        L.hideFocus = true;
				        L.frameBorder = 0;
				        L.scrolling = "no";
				        L.src = "javascript:(function(){var d=document;d.open();d.domain='" + I + "';})()";
				        K.appendChild(L);
				        setTimeout(function() { H = K.lastChild.contentWindow[N];
				            C() }, 97);
				        return }
				    C();
				    // 调用 carendar 组件
				    function C() {
				        var _ = J.getRealLang();
				        K.lang = _.name;
				        K.skin = J.skin;
				        var arr = ["<head><script>", "", "var doc=document, $d, $dp, $cfg=doc.cfg, $pdp = parent.$dp, $dt, $tdt, $sdt, $lastInput, $IE=$pdp.ie, $FF = $pdp.ff,$OPERA=$pdp.opera, $ny, $cMark = false;", "if($cfg.eCont){$dp = {};for(var p in $pdp)$dp[p]=$pdp[p];}else{$dp=$pdp;};for(var p in $cfg){$dp[p]=$cfg[p];}", "doc.oncontextmenu=function(){try{$c._fillQS(!$dp.has.d,1);showB($d.qsDivSel);}catch(e){};return false;};", "</script><script src=", A, "lang/", _.name, ".js charset=", _.charset, "></script>"];
				        if (F) arr[1] = "document.domain=\"" + I + "\";";
				        for (var C = 0; C < D.length; C++)
				            if (D[C].name == J.skin) arr.push("<link rel=\"stylesheet\" type=\"text/css\" href=\"" + A + "skin/" + D[C].name + "/datepicker.css\" charset=\"" + D[C].charset + "\"/>");
				        arr.push("<script src=\"" + A + "calendar.js\"></script>");
				        arr.push("</head><body leftmargin=\"0\" topmargin=\"0\" tabindex=0></body></html>");
				        arr.push("<script>var t;t=t||setInterval(function(){if(doc.ready){new My97DP();$cfg.onload();$c.autoSize();$cfg.setPos($dp);clearInterval(t);}},20);</script>");
				        J.setPos = B;
				        J.onload = Z;
				        H.write("<html>");
				        H.cfg = J;
				        H.write(arr.join(""));
				        H.close() 
				    } 
				}

				// 定位
				function B(J) {
				    var H = J.position.left,
				        C = J.position.top,
				        D = J.el;
				    if (D == T) return;
				    if (D != J.srcEl && (P(D) == "none" || D.type == "hidden")) D = J.srcEl;
				    var I = W(D),
				        $ = F(Y),
				        E = M(V),
				        B = b(V),
				        G = $dp.dd.offsetHeight,
				        A = $dp.dd.offsetWidth;
				    if (isNaN(C)) C = 0;
				    if (($.topM + I.bottom + G > E.height) && ($.topM + I.top - G > 0)) C += B.top + $.topM + I.top - G - 2;
				    else { C += B.top + $.topM + I.bottom;
				        var _ = C - B.top + G - E.height;
				        if (_ > 0) C -= _ }
				    if (isNaN(H)) H = 0;
				    H += B.left + Math.min($.leftM + I.left, E.width - A - 5) - (S ? 2 : 0);
				    J.dd.style.top = C + "px";
				    J.dd.style.left = H + "px";
				} 
			}
		}
	})
});
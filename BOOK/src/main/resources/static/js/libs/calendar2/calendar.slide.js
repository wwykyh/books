$(function() {
    $.fn.extend({
        /**
         * 动态获取日期数据（例如今天向后延迟多少天，向前前进多少天的日期数据）
         */
        getCarendar: function(options) {
            // 配置参数
            var defaults = {
                    daysDelay: 60, // 即日起向后延迟多少天 默认 60天
                    daysBefore: 7, // 即日起向前前进多少天 默认 一周

                }
            // 参数继承
            options = $.extend(defaults, options);
            // 初始化 太阳历每月天数
            var solarMonth = new Array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
            // 农历数据信息
            var lunarInfo = new Array(
                0x04bd8, 0x04ae0, 0x0a570, 0x054d5, 0x0d260, 0x0d950, 0x16554, 0x056a0, 0x09ad0, 0x055d2,
                0x04ae0, 0x0a5b6, 0x0a4d0, 0x0d250, 0x1d255, 0x0b540, 0x0d6a0, 0x0ada2, 0x095b0, 0x14977,
                0x04970, 0x0a4b0, 0x0b4b5, 0x06a50, 0x06d40, 0x1ab54, 0x02b60, 0x09570, 0x052f2, 0x04970,
                0x06566, 0x0d4a0, 0x0ea50, 0x06e95, 0x05ad0, 0x02b60, 0x186e3, 0x092e0, 0x1c8d7, 0x0c950,
                0x0d4a0, 0x1d8a6, 0x0b550, 0x056a0, 0x1a5b4, 0x025d0, 0x092d0, 0x0d2b2, 0x0a950, 0x0b557,
                0x06ca0, 0x0b550, 0x15355, 0x04da0, 0x0a5d0, 0x14573, 0x052d0, 0x0a9a8, 0x0e950, 0x06aa0,
                0x0aea6, 0x0ab50, 0x04b60, 0x0aae4, 0x0a570, 0x05260, 0x0f263, 0x0d950, 0x05b57, 0x056a0,
                0x096d0, 0x04dd5, 0x04ad0, 0x0a4d0, 0x0d4d4, 0x0d250, 0x0d558, 0x0b540, 0x0b5a0, 0x195a6,
                0x095b0, 0x049b0, 0x0a974, 0x0a4b0, 0x0b27a, 0x06a50, 0x06d40, 0x0af46, 0x0ab60, 0x09570,
                0x04af5, 0x04970, 0x064b0, 0x074a3, 0x0ea50, 0x06b58, 0x055c0, 0x0ab60, 0x096d5, 0x092e0,
                0x0c960, 0x0d954, 0x0d4a0, 0x0da50, 0x07552, 0x056a0, 0x0abb7, 0x025d0, 0x092d0, 0x0cab5,
                0x0a950, 0x0b4a0, 0x0baa4, 0x0ad50, 0x055d9, 0x04ba0, 0x0a5b0, 0x15176, 0x052b0, 0x0a930,
                0x07954, 0x06aa0, 0x0ad50, 0x05b52, 0x04b60, 0x0a6e6, 0x0a4e0, 0x0d260, 0x0ea65, 0x0d530,
                0x05aa0, 0x076a3, 0x096d0, 0x04bd7, 0x04ad0, 0x0a4d0, 0x1d0b6, 0x0d250, 0x0d520, 0x0dd45,
                0x0b5a0, 0x056d0, 0x055b2, 0x049b0, 0x0a577, 0x0a4b0, 0x0aa50, 0x1b255, 0x06d20, 0x0ada0);

        	//节气
			var solarTerm = new Array("小寒", "大寒", "立春", "雨水", "惊蛰", "春分", "清明", "谷雨", "立夏", "小满", "芒种", "夏至", "小暑", "大暑", "立秋", "处暑", "白露", "秋分", "寒露", "霜降", "立冬", "小雪", "大雪", "冬至");
			// 节气编码
			var sTermInfo = new Array(0, 21208, 42467, 63836, 85337, 107014, 128867, 150921, 173149, 195551, 218072, 240693, 263343, 285989, 308563, 331033, 353350, 375494, 397447, 419210, 440795, 462224, 483532, 504758);
			// 农历日期
			var nStr1 = new Array('日', '一', '二', '三', '四', '五', '六', '七', '八', '九', '十');
			// 农历日期
			var nStr2 = new Array('初', '十', '廿', '卅', '　');

			// 当天日期
			var tDay = new Date();
			var tYear = tDay.getFullYear();
			var tMonth = tDay.getMonth();
			var tDay = tDay.getDate();

			return carendarForDays();

			/**
			 * 构造所属天数内的日期数据
			 * @param 
			 */
			function carendarForDays() {
				// 当月的天数
				var tDays = solarDays(tYear, tMonth);
				// 上一天下一天的日期
				var nYear = sYear = tYear;
				var nMonth = sMonth = tMonth;
				var nDay = sDay = tDay;
				// 提前的天数
				var beforeCount = options.daysBefore;
				// 延迟的天数
				var afterCount = options.daysDelay;
				var beforeArr = [];
				var afterArr = [];
				// 数据初始化
				var tDObj = new Date(tYear, tMonth, tDay);
				var zDObj = new Lunar(tDObj);
				beforeArr.push(calElement(tYear, tMonth + 1, tDay, weekdaysPreview(tYear, tMonth, tDay), zDObj.year, zDObj.month, zDObj.day, true));

				// 计算天数内的日期值，采用递归算法 需要倒置
				while(beforeCount--) {
					if( sDay > 1) {
						sDay--;
					} else {
						if( sMonth > 0) {
							sMonth--;
							sDay = solarDays(sYear, sMonth);
						} else {
							sYear--;
							sMonth = 11;
							sDay = solarDays(sYear, sMonth);
						}
					}
					// 通过 sYear sMonth sDay 求得其属性
					var sDObj = new Date(sYear, sMonth, sDay);
					var lDObj = new Lunar(sDObj);
					beforeArr.push(calElement(sYear, sMonth + 1, sDay, weekdaysPreview(sYear, sMonth, sDay), lDObj.year, lDObj.month, lDObj.day, false));
				}

				// 计算天数内的日期值，采用递归算法
				while(afterCount--) {
					if( nDay < tDays) {
						nDay++;
					} else {
						if( nMonth < 11) {
							nMonth++;
							nDay = 1;
						} else {
							nYear++;
							nMonth = 0;
							nDay = 1;
						}
					}
					// 通过 nYear nMonth nDay 求得其属性
					var nDObj = new Date(nYear, nMonth, nDay);
					var lDObj = new Lunar(nDObj);
					afterArr.push(calElement(nYear, nMonth + 1, nDay, weekdaysPreview(nYear, nMonth, nDay), lDObj.year, lDObj.month, lDObj.day, false));
				}

				// 整合数组
				beforeArr = beforeArr.reverse();
				var newArr = beforeArr.concat(afterArr);

				return newArr;
				
			}

			/**
			 * 具体星期几的算法
			 * @param year 年份
			 * @param month 月份
			 * @param day 天数
			 */
			function weekdaysPreview(year, month, day) {
				var dayObj = new Date(year, month, 1);
				var firstWeek = dayObj.getDay();

				var num = (Number(firstWeek) + Number(day) - 1) % 7;

				return "周" + nStr1[num];
			}

			/**
			 * 算出当年当月的天数
			 * @param year 年份
			 * @param month 月份 [0-11]
			 */
			function solarDays(year,month) {
				if(month == 1 )
					return (((year %4 == 0) && (year %100 != 0) || (year %400 == 0))? 29: 28);
				else
					return(solarMonth[month]);
			}

			/**
			 * [Lunar 农历计算]
			 * @param {[type]} objDate [description]
			 */
			function Lunar(objDate) {
				var i, leap=0, temp=0;
				var baseDate = new Date(1900,0,31);
				
				var offset   = Math.floor((objDate.getTime() + 2206425600000)/86400000);

				this.dayCyl = offset + 40;
				this.monCyl = 14;

				for(i=1900; i<2050 && offset>0; i++) {
					temp = lYearDays(i);
					offset -= temp;
					this.monCyl += 12;
				}

				if(offset<0) {
					offset += temp;
					i--;
					this.monCyl -= 12;
				}

				this.year = i;
				this.yearCyl = i-1864;

				leap = leapMonth(i);
				this.isLeap = false;

				for(i=1; i<13 && offset>0; i++) {
					if(leap>0 && i==(leap+1) && this.isLeap==false)
					{ --i; this.isLeap = true; temp = leapDays(this.year); }
					else
					{ temp = monthDays(this.year, i); }

					if(this.isLeap==true && i==(leap+1)) this.isLeap = false;

					offset -= temp;
					if(this.isLeap == false) this.monCyl ++;
				}

				if(offset==0 && leap>0 && i==leap+1)
					if(this.isLeap)
					{ this.isLeap = false; }
					else
					{ this.isLeap = true; --i; --this.monCyl;}

				if(offset<0){ offset += temp; --i; --this.monCyl; }

				this.month = i;
				this.day = offset + 1;
			}

			function lYearDays(y) {
				var i, sum = 348;
				for(i=0x8000; i>0x8; i>>=1) sum += (lunarInfo[y-1900] & i)? 1: 0;
				return(sum+leapDays(y));
			}

			// 农历闰月天数
			function leapDays(year) {
				if(leapMonth(year))  return((lunarInfo[year-1900] & 0x10000)? 30: 29);
				else return(0);
			}

			// 农历月份数?
			function leapMonth(year) {
				return(lunarInfo[year-1900] & 0xf);
			}

			/**
			 * 农历当月的日期数
			 * @param year  年份
			 * @param month 月份
			 */
			function monthDays(year, month) {
				return( (lunarInfo[year-1900] & (0x10000>>month))? 30: 29 );
			}

			/**
			 * 构造当天的日期属性
			 * @param  sYear  太阳历年份
			 * @param  sMonth 太阳历月份
			 * @param  sDay   太阳历当天
			 * @param  week   星期
			 * @param  lYear  农历年份
			 * @param  lMonth 农历月份
			 * @param  lDay   农历当天
			 * @param  today  当前日期为今天
			 */
			function calElement(year, month, day, week, lYear, lMonth, lDay, today) {
				return {
					sYear: year,
					sMonth: month,
					sDay: day,
					week: week,
					lYear: lYear,
					lMonth: lMonth,
					lDay: lDay,
					isToday: today
				}
			}
		},


        /**
         * 时间表点击左右切换轮播
         * [Object] options
         * @param { String } contain 盒容器 传入为 ID 名称
         * @param { String } childItem 子节点 数据列表单元 传入为 类名或节点名称
         * @param { Number } currentPage 当前所要显示日期 默认为 0
         * @param { Boolean } showBtn 翻页按钮 默认显示 现今也只是显示而已
         * @param { Array } data 数据数组
         */
        superMarquee: function(options) {
            // 配置参数
            var defaults = {
                    contain: "carendar", // 盒容器
                    childItem: "li", // 数据容器
                    currentPage: 0, // 当前所要显示日期 
                    showBtn: true, // 翻页按钮显示
                    data: [1,2,3,4,5,6,7,8,9,10], // 数据
                }
            // 主块
            var $contain = $(this);
            // console.log(options.data);
            
            // 按钮
            var btnLeft = '<div class="bar-date prev-date-disabled" id="prevX"><b></b></div>';
            var btnRight = '<div class="bar-date next-date-disabled" id="nextX"><b></b></div>';

            // 时间表初始化
            Pages();

            // 翻页功能
            function Pages() {
            	// 追加左侧按钮
            	$contain.append(btnLeft);
            	// 追加轮播内容
                $contain.append('<div id="'+options.contain+'" class="calendar-tab-list"><ul></ul></div>');

                var $appList = $contain.find('#' + options.contain + ' ul');

				for (var i = 0; i < options.data.length; i++) {
					var dateObj = options.data[i];
					var today = "";
					if(dateObj.isToday) {
						today = "今天";
					}
					$appList.append('<li>'+
										'<span>'+dateObj.sMonth+'&nbsp;-&nbsp;'+dateObj.sDay+'&nbsp;&nbsp;'+dateObj.week+'</span>'+
										'<span>'+today+'</span>'+
									'</li>');
				}

				// 容器盒的宽度
				var containW = $appList.innerWidth();
				// 子节点的宽度
				var childW = $contain.find(options.childItem).outerWidth();
				// 对宽度进行定义
				$appList.css('width', childW * options.data.length);

				// 追加右侧按钮
				$contain.append(btnRight);
				// 容器盒可以容纳子节点数
				var stepNum = containW / childW;
				var current_page = options.currentPage;
				var pageCount = options.data.length - stepNum;
				// 初始化翻页
				turnPage();
				$appList.animate({ left: '-' + childW * current_page + 'px' }, "100");

				$("#nextX").click(function() {
				    if (current_page >= 0 && current_page < pageCount) {
				        $appList.animate({ left: '-' + childW * (current_page + 1) + 'px' }, "500", function() {
				            turnPage('add');
				        });
				    }
				});

				$("#prevX").click(function() {
				    if (current_page > 0 && current_page <= pageCount) {
				        $appList.animate({ left: '-' + childW * (current_page - 1) + 'px' }, "500", function() {
				            turnPage('reduce');
				        });
				    }
				});

                // 页数判断
                function turnPage(action) {
                    if (action == 'add') {
                        current_page++;
                    } else if (action == 'reduce') {
                        current_page--;
                    }
                    if (current_page > pageCount) { current_page = pageCount; }
                    if (current_page < 0) { current_page = 0; }
                    switch (current_page) {
                    	case 0:
                    		$("#prevX").removeClass('prev-date').addClass('prev-date-disabled');
                    		$("#nextX").removeClass('next-date-disabled').addClass('next-date');
                    		break;
                        case 1:
                            $("#prevX").removeClass('prev-date-disabled').addClass('prev-date');
                            break;
                        case pageCount:
                            $("#nextX").removeClass('next-date').addClass('next-date-disabled');
                            break;
                        default:
                        	$("#prevX").removeClass('prev-date-disabled').addClass('prev-date');
                        	$("#nextX").removeClass('next-date-disabled').addClass('next-date');
                            return false;
                    }
                }
            }
        }
    })
})

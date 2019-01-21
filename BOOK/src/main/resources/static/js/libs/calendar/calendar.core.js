define(['jquery'], function($) {
    // 定义一些局部函数
    //农历数据信息
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
        0x0b5a0, 0x056d0, 0x055b2, 0x049b0, 0x0a577, 0x0a4b0, 0x0aa50, 0x1b255, 0x06d20, 0x0ada0)

    //太阳历每月天数
    var solarMonth = new Array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
    //天干
    var Gan = new Array("甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸");
    //地支
    var Zhi = new Array("子", "丑", "寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌", "亥");
    //属相
    var Animals = new Array("鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊", "猴", "鸡", "狗", "猪");
    //节气
    var solarTerm = new Array("小寒", "大寒", "立春", "雨水", "惊蛰", "春分", "清明", "谷雨", "立夏", "小满", "芒种", "夏至", "小暑", "大暑", "立秋", "处暑", "白露", "秋分", "寒露", "霜降", "立冬", "小雪", "大雪", "冬至");
    //Download by www.codefans.net
    var sTermInfo = new Array(0, 21208, 42467, 63836, 85337, 107014, 128867, 150921, 173149, 195551, 218072, 240693, 263343, 285989, 308563, 331033, 353350, 375494, 397447, 419210, 440795, 462224, 483532, 504758);
    //
    var nStr1 = new Array('日', '一', '二', '三', '四', '五', '六', '七', '八', '九', '十');
    //
    var nStr2 = new Array('初', '十', '廿', '卅', '　');
    //英语月份简写
    var monthName = new Array("JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC");

    //国历节日 *表示节假日
    var sFtv = new Array(
        "0101*元旦",
        "0214 情人节",
        "0308 妇女节",
        "0312 植树节",
        "0315 消费者权益日",
        "0321 世界森林日、世界儿歌日",
        "0322 世界水日",
        "0323 世界气象日",
        "0324 世界防治结核病日",

        "0401 愚人节",
        "0407 世界卫生日",
        "0422 世界地球日",

        "0501*劳动节",
        "0504 青年节",
        "0505 碘缺乏病防治日",
        "0508 世界红十字日",
        "0512 国际护士节",
        "0515 国际家庭日",
        "0517 世界电信日",
        "0518 国际博物馆日",
        "0520 全国学生营养日",
        "0523 国际牛奶日",
        "0531 世界无烟日",

        "0601 儿童节",
        "0605 世界环境日",
        "0606 全国爱眼日",
        "0616 防治荒漠化和干旱日",
        "0623 国际奥林匹克日",
        "0625 全国土地日",
        "0626 国际反毒品日",

        "0701 建党节 香港回归纪念 国际建筑日",
        "0707 中国人民抗日战争纪念日",
        "0711 世界人口日",

        "0801 建军节",
        "0808 父亲节",

        "0908 国际扫盲日",
        "0909 毛泽东逝世纪念",
        "0910 教师节",
        "0916 国际臭氧层保护日",
        "0920 国际爱牙日",
        "0927 世界旅游日",
        "0928 孔子诞辰",

        "1001*国庆节 国际音乐日",
        "1004 世界动物日",
        "1006 老人节",
        "1008 全国高血压日 世界视觉日",
        "1009 世界邮政日",
        "1015 国际盲人节",
        "1016 世界粮食日",
        "1017 世界消除贫困日",
        "1024 联合国日",

        "1108 中国记者日",
        "1109 消防宣传日",
        "1112 孙中山诞辰纪念",
        "1114 世界糖尿病日",
        "1117 国际大学生节",

        "1201 世界艾滋病日",
        "1203 世界残疾人日",
        "1209 世界足球日",
        "1220 澳门回归纪念",
        "1225 圣诞节",
        "1226 毛泽东诞辰纪念",
        "1229 国际生物多样性日"
    );

    //农历节日 *表示节假日
    var lFtv = new Array(
        "0101*春节",
        "0115 元宵节",
        "0505 端午节",
        "0707 七夕情人节",
        "0715 中元节",
        "0815 中秋节",
        "0909 重阳节",
        "1208 腊八节",
        "1223 小年",
        "0100*除夕"
    );

    //按周计算 月周日
    var wFtv = new Array(
        "0520 国际母亲节",
        "0530 全国助残日",
        "0630 国际父亲节",
        "0932 国际和平日",
        "0940 国际聋人节",
        "1013 国际减轻自然灾害日",
        "1011 国际住房日"
    );

    /**
     * 构造月份数据
     * @param year 年份
     * @param month 月份 [0-11]
     * @param params 配置参数
     */
    function calendar(year,month,params) {
		var sDObj, lDObj, lY, lM, lD=1, lL, lX=0, tmp1, tmp2;
		var lDPOS = new Array(3);
		var n = 0;
		var firstLM = 0;

		// 当月1日的时间对象
		sDObj = new Date(year, month, 1);

		// 获取当月的天数
		this.days = solarDays(year, month);

		// 获取当月的前几天是上月份的
		this.firstWeek = sDObj.getDay();

		// 当前月份的日期
		this.currentMonth = [];

		// 当月外的日期
		this.otherMonth = [];

		// 当月份的日期对象
		for(var i = 0; i < this.days; i++) {
			// lD lX
			if(lD>lX) {
				sDObj = new Date(year, month, i + 1);
				lDObj = new Lunar(sDObj);
				lY    = lDObj.year;
				lM    = lDObj.month;
				lD    = lDObj.day;
				lL    = lDObj.isLeap;
				lX    = lL? leapDays(lY): monthDays(lY,lM);

				if(n==0) firstLM = lM;

				lDPOS[n++] = i-lD+1;
			}

			// 当天日期对象
			this.currentMonth.push(new calElement(year, month + 1, i + 1, nStr1[(i+this.firstWeek)%7], lY, lM, lD++, lL));

			// 颜色赋值
			if((i+this.firstWeek)%7==0) this.currentMonth[i].color = '#ff5f07';
		}

		tmp1 = sTerm(year, month * 2  ) -1;
		tmp2 = sTerm(year, month * 2 + 1) -1;

		this.currentMonth[tmp1].solarTerms = solarTerm[month * 2];
		this.currentMonth[tmp2].solarTerms = solarTerm[month * 2 + 1];
		if(month==3) this.currentMonth[tmp1].color = '#ff5f07';

		// 当月下的节日添加
		if(params.iShowLunar) {
			// 国历节假日
			for(i in sFtv)
				// 匹配正则值
				if(sFtv[i].match(/^(\d{2})(\d{2})([\s\*])(.+)$/))
					// RegExp.$1 - $.3 匹配正则表达式的括号内容
					if(Number(RegExp.$1)==(month+1)) {
						this.currentMonth[Number(RegExp.$2)-1].solarFestival += RegExp.$4 + ' ';
						if(RegExp.$3=='*') this.currentMonth[Number(RegExp.$2)-1].color = '#ff5f07';
					}

			// 周月日
			for(i in wFtv)
				if(wFtv[i].match(/^(\d{2})(\d)(\d)([\s\*])(.+)$/))
					if(Number(RegExp.$1)==(month+1)) {
						tmp1=Number(RegExp.$2);
						tmp2=Number(RegExp.$3);
						this.currentMonth[((this.firstWeek>tmp2)?7:0) + 7*(tmp1-1) + tmp2 - this.firstWeek].solarFestival += RegExp.$5 + ' ';
					}

			// 农历节假日
			for(i in lFtv)
				if(lFtv[i].match(/^(\d{2})(.{2})([\s\*])(.+)$/)) {
					tmp1=Number(RegExp.$1)-firstLM;
					if(tmp1==-11) tmp1=1;
					if(tmp1 >=0 && tmp1<n) {
						tmp2 = lDPOS[tmp1] + Number(RegExp.$2) -1;
						if( tmp2 >= 0 && tmp2<this.length) {
							this.currentMonth[tmp2].lunarFestival += RegExp.$4 + ' ';
							if(RegExp.$3=='*') this.currentMonth[tmp2].color = '#ff5f07';
						}
					}
				}
		}

		if((this.firstWeek+12)%7==5)
			this.currentMonth[12].solarFestival += '黑色星期五 ';

		if(year == tY && month == tM) {
			this.currentMonth[tD-1].isToday = true;
		}

		// 当月外的日期对象
		var out_days = 42 - this.days;
		var weekday;

		for(var i = 0; i < out_days; i++) {
			if( i < this.firstWeek) {
		 		if(month <= 0) { // 月份 [0~11] 天数数组 [0~11]
		 			var b_year = year -1;
		 			var b_month = 12;
		 		} else {
		 			var b_year = year;
		 			var b_month = month;
		 		}
		 		var days = solarDays(b_year,b_month - 1);
		 		var b_day = days - this.firstWeek + i + 1;//当前月份
		 		var weekday = nStr1[i % 7];
			} else {
				if(month == 11) {
					var b_year = year + 1;
					var b_month = 1;
				} else {
					var b_year = year;
					var b_month = month + 2;
				}
				var days = solarMonth[month];
				var b_day = i - this.firstWeek + 1;//当前月份
				var weekday = nStr1[(i + this.days) % 7];
			}

			sDObj = new Date(b_year, b_month - 1, b_day);
			lDObj = new Lunar(sDObj);
			lY    = lDObj.year;
			lM    = lDObj.month;
			lD    = lDObj.day;
			lL    = lDObj.isLeap;
			lX    = lL? leapDays(lY): monthDays(lY,lM);

			// 当天日期对象
			this.otherMonth.push(new calElement(b_year, b_month, b_day, weekday, lY, lM, lD, lL));
		}
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
	 * 当天的相关信息
	 * @param  sYear  太阳历年份
	 * @param  sMonth 太阳历月份
	 * @param  sDay   太阳历当天
	 * @param  week   星期
	 * @param  lYear  农历年份
	 * @param  lMonth 农历月份
	 * @param  lDay   农历当天
	 * @param  isLeap 是否为闰月（农历）
	 */
	function calElement(sYear,sMonth,sDay,week,lYear,lMonth,lDay,isLeap) {
		this.isToday    = false;
		this.sYear      = sYear;
		this.sMonth     = sMonth;
		this.sDay       = sDay;
		this.week       = week;
		this.lYear      = lYear;
		this.lMonth     = lMonth;
		this.lDay       = lDay;
		this.isLeap     = isLeap;
		this.color      = '';
		this.lunarFestival = '';
		this.solarFestival = '';
		this.solarTerms    = '';
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

	function sTerm(y,n) {
		var offDate = new Date( ( 31556925974.7*(y-1900) + sTermInfo[n]*60000  ) -2208549300000 );
		return(offDate.getUTCDate());
	}

	// 农历当天
	function cDay(day){
		var s;
		switch (day) {
			case 10:
				s = '初十';
				break;
			case 20:
				s = '二十';
				break;
			case 30:
				s = '三十';
				break;
			default :
				s = nStr2[Math.floor(day/10)];
				s += nStr1[day%10];
		}
		return(s);
	}

	var cld;

	function drawCld($p,SY, SM,params) {

		$(".calendar-table").find("td").removeClass("tag-hread");
		var i,sD,s,size;
		cld = new calendar(SY,SM,params);

		$p.find("#gz").html('&nbsp;&nbsp;【'+Animals[(SY-4)%12]+'】');

		var six=[6,13,20,27,34,41];
		// console.log(cld);
		for(i=0;i<42;i++) {

			sObj = $p.find("#sd"+i)[0];
			lObj = $p.find("#ld"+i)[0];

			sObj.style.background = '';
			lObj.style.background = '';

			sD = i - cld.firstWeek;
			if(sD>-1 && sD<cld.days) {
				sObj.innerHTML = sD+1;
				var cMonth = cld.currentMonth;

				sObj.style.color = cMonth[sD].color;
				($.inArray(i,six)!=-1)&&(sObj.style.color="#ff5f07");

				// 日期为今天的标记
				if(params.iShowToDay) {
					if(cMonth[sD].isToday){
						//设置今天的背景色
						sObj.style.color='blue';
					}
				}

				// 农历时间显示
				if(params.iShowLunar) {
					lObj.innerHTML = cDay(cMonth[sD].lDay);
				
					s = cMonth[sD].lunarFestival;
					if(s.length>0) {
						//农历节日名称大于5个字截去
						if(s.length>=5){
							$(lObj).attr("title",s);
							s = s.substr(0, 3)+'…';
						}
						s = s.fontcolor('#ff5f07');
					} else {

						// 阳历节日
						if(params.iShowSolarFes) {
							s=cMonth[sD].solarFestival;
							if(s.length>0) {
								//阳历节日名称截去
								size = (s.charCodeAt(0)>0 && s.charCodeAt(0)<128)?9:5;
								if(s.length>5)$(lObj).attr("title",s);
								if(s.length>size+1) s = s.substr(0, size-1)+'…';
								s = s.fontcolor('#0168ea');
							}
							else {
								s=cMonth[sD].solarTerms;
								if(s.length>0) s = s.fontcolor('#44d7cf');
							}
						}
					}

					// 农历显示
					if(s.length>0) lObj.innerHTML = s;
				}

			} else {
				// 当月日历外的日期值（显示）
			 	if(i < cld.firstWeek) {
			 		// 当前月份下日期
			 		sObj.innerHTML = cld.otherMonth[i].sDay;
			 		var cMonth = cld.otherMonth;
			 		// 当前月份下农历
			 		if(params.iShowLunar) lObj.innerHTML = cDay(cMonth[i].lDay);
				} else {
					// 当前月份下日期
					sObj.innerHTML = cld.otherMonth[i - cld.days].sDay;
					var cMonth = cld.otherMonth;
			 		// 当前月份下农历
					if(params.iShowLunar) lObj.innerHTML = cDay(cMonth[i - cld.days].lDay);
				}

				// 显示样式调整
				$(sObj).parent().removeClass("tag-hwrite");
				$(sObj).parent().removeClass("tag-hread");
				sObj.style.color = '#999';// 默认为灰色
				lObj.style.color = '#999';// 默认为灰色
			}
		}
		params.afterRefresh&&params.afterRefresh(currentDate);
	}

	function changeCld($p,params) {
		var y,m;
		y = $p.find("#sy")[0].selectedIndex + 1900;
		m = $p.find("#sm")[0].selectedIndex;
		drawCld($p,y,m,params);
	}

	function pushBtm($p,K,params) {
		switch (K){
			case 'YU' :    //上一年↑
				if($p.find("#sy")[0].selectedIndex > 0) //document.getElementById("sy")
					$p.find("#sy")[0].selectedIndex--;
				break;
			case 'YD' :   //下一年
				if($p.find("#sy")[0].selectedIndex < 149)
					$p.find("#sy")[0].selectedIndex++;
				break;
			case 'MU' :    //上一月
				if($p.find("#sm")[0].selectedIndex > 0) {
					$p.find("#sm")[0].selectedIndex--;
				}
				else {
					$p.find("#sm")[0].selectedIndex = 11;
					if($p.find("#sy")[0].selectedIndex > 0)
						$p.find("#sy")[0].selectedIndex--;
				}
				break;
			case 'MD' :  //下一月
				if($p.find("#sm")[0].selectedIndex < 11) {
					$p.find("#sm")[0].selectedIndex++;
				}
				else {
					$p.find("#sm")[0].selectedIndex = 0;
					if($p.find("#sy")[0].selectedIndex < 149)
						$p.find("#sy")[0].selectedIndex++;
				}
				break;
			default :
				$p.find("#sy")[0].selectedIndex = tY - 1900;
				$p.find("#sm")[0].selectedIndex = tM;
		}
		changeCld($p,params);
	}

	var Today = new Date();
	var tY = Today.getFullYear();
	var tM = Today.getMonth();
	var tD = Today.getDate();

	var width = "130"; //detail层宽度
	var offsetx = 2;
	var offsety = 16;

	var x = 0;
	var y = 0;
	var show = 0;
	var sw = 0;
	var cnt = 0;

	var dStyle;
	var param={};
	document.onmousemove = mEvn;

	//用detail层显示详细信息
	function mOvr(v) {

		var festival = document.getElementById("festival");
		var datedetail = document.getElementById("datedetail");

		var sObj = document.getElementById('sd'+ v);
		//alert(v);
		var d = sObj.innerHTML - 1;

		if( sObj.innerHTML != '' ) {
			sObj.style.cursor = 'move';
			if(cld[d].solarTerms == ''
				&& cld[d].solarFestival == ''
				&& cld[d].lunarFestival == '')
			{
				festival.innerHTML = "";
				festival.style.display = "none";
			}
			else
			{

				festival.innerHTML = cld[d].solarTerms + ' ' + cld[d].solarFestival + ' ' + cld[d].lunarFestival;
				festival.style.display = "block";
			}
			datedetail.innerHTML = cld[d].sYear +' 年 '+ cld[d].sMonth
			+ ' 月 '+cld[d].sDay +' 日<br />星期' + cld[d].week + '<br />'
			+ '<span>农历' + (cld[d].isLeap?'闰 ':' ')
			+ cld[d].lMonth + ' 月 ' + cld[d].lDay + ' 日<br />'
			+ cld[d].cYear + '年 ' + cld[d].cMonth
			+ '月 ' + cld[d].cDay + '日</span>';

			if (show == 0) {
				dStyle.left = (x + offsetx - (width/2)) + "px";
				dStyle.top = (y + offsety) + "px";
				dStyle.visibility = "visible";
				show = 1;
			}
		}
	}

	function mOut() {
		if ( cnt >= 1 ) { sw = 0 }
		if ( sw == 0 ) { show = 0; dStyle.visibility = "hidden";}
		else cnt++;
	}

	//获取鼠标坐标
	function mEvn(e) {
		if (!show) return;
		if(window.event){
			x = event.x ;
			y = event.y ;
			if (document.body.scrollLeft){
				x += document.body.scrollLeft;
				y += document.body.scrollTop;
			}
			dStyle.left = (x + offsetx-(width/2)) + "px";
			dStyle.top = (y + offsety) + "px";
		}
		else {
			dStyle.left = (e.pageX + offsetx-(width/2)) + "px";
			dStyle.top = (e.pageY + offsety) + "px";
		}
	}

	function changeTZ() {
		document.getElementById("city").innerHTML = document.getElementById("tz").value.substr(6);
		setCookie("TZ",document.getElementById("tz").selectedIndex);
	}

	function tick() {
		var today;
		today = new Date();
		document.getElementById("clock").innerHTML = today.getFullYear() + "年"
		+ today.getMonth() + "月" + today.getDay() + "日" + today.getTime();
		window.setTimeout("tick()", 1000);
	}

	function setCookie(name, value) {
		var today = new Date()
		var expires = new Date()
		expires.setTime(today.getTime() + 1000*60*60*24*365)
		document.cookie = name + "=" + escape(value)    + "; expires=" + expires.toGMTString()
	}

	function getCookie(Name) {
		var search = Name + "=";
		if(document.cookie.length > 0) {
			offset = document.cookie.indexOf(search);
			if(offset != -1) {
				offset += search.length;
				end = document.cookie.indexOf(";", offset);
				if(end == -1) end = document.cookie.length;
				return unescape(document.cookie.substring(offset, end));
			}
			else return "";
		}
	}

	function fillSelect($p) {

		syd =$p.find("#sy")[0];
		syd.innerHTML = "";
		for(i=1900;i<2050;i++)
		{
			ins =$("<option></option>")[0];
			ins.innerHTML = i;
			syd.appendChild(ins);
		}
		smd =$p.find("#sm")[0]; 
		smd.innerHTML = "";
		for(i=1;i<13;i++)
		{
			ins =$("<option></option>")[0];
			ins.innerHTML = i;
			smd.appendChild(ins);
		}
	}

	function fillCalendar() {
		var gNum;
		var tablex = document.createElement("table");
		tablex.setAttribute("id","week");
		for(i=0;i<6;i++) {
			var trx1 = document.createElement("tr");
			var trx2 = document.createElement("tr");
			trx1.setAttribute("class","tr1");
			trx2.setAttribute("class","tr2");
			for(j=0;j<7;j++) {
				gNum = i*7+j;
				var tdx = document.createElement("td");
				tdx.setAttribute("id","sd"+gNum);
				tdx.setAttribute("onMouseOver",'mOvr('+gNum+')');
				tdx.setAttribute("onMouseOut","mOut()");
				if(j == 0){
					tdx.setAttribute("class","aorange");
				}
				else if(j == 6){
					if(i%2==1) tdx.setAttribute("class","aorange");
					else tdx.setAttribute("class","agreen");
				}
				else{
					tdx.setAttribute("class","one");
				}
				trx1.appendChild(tdx);

				tdx = document.createElement("td");
				tdx.setAttribute("id","ld"+gNum);
				tdx.setAttribute("onMouseOver",'mOvr('+gNum+')');
				tdx.setAttribute("onMouseOut","mOut()");
				trx2.appendChild(tdx);
			}
			tablex.appendChild(trx1);
			tablex.appendChild(trx2);
		}
		document.getElementById("calendar").appendChild(tablex);
	}

	//构造日历表格
	function createCalendarBody(options) {
		var table=$("<table  class='calendar-table'></table>");
		var headTr=$("<tr><th width='15%'>日</th><th width='14%'>一</th><th width='14%'>二</th> " +
		"<th width='14%'>三</th> <th width='14%'>四</th> <th width='14%'>五</th> <th width='15%'>六</th> </tr>");
		table.append(headTr);
		for(var row=0;row<6;row++){
			var tr=$("<tr></tr>"),td,sd,ld,fd,calendarGregorian,calendarLunar,festival,num=0;
			for(var col=0;col<7;col++){
				num=(row*7)+col;
				td=$("<td></td>");
				if(col == 0){ td.addClass("aorange");}
				else if(col == 6){
					if(row%2==1) td.addClass("aorange");
					else td.addClass("agreen");
				}
				else{ td.addClass("one"); }
				sd='sd'+num;
				ld='ld'+num;
				fd='fd'+num;
				calendarGregorian=$("<div id='"+sd+"' class='calendarGregorian'></div>");
				calendarLunar=$("<div id='"+ld+"'  class='calendarLunar' ></div>");
				(options.type=="small")&&calendarLunar.css("display","none");
				festival=$("<div id='"+fd+"' class='todoDay' ></div>");
				td.append(calendarGregorian).append(calendarLunar).append(festival);
				td.click(function(){
					table.find("td").removeClass("cur");
					$(this).addClass("cur");
				});
				tr.append(td);
			}
			table.append(tr);
		}
		return table;
	}

	function bindEvent($p,options){
		//上一年
		$p.find(".a-prev").click(function(){pushBtm($p,'MU',options)});
		//下一年
		$p.find(".a-next").click(function(){pushBtm($p,'MD',options)});
		//年月改变
		$p.find("#sm,#sy").change(function(){changeCld($p,options)});

		$("table",$p).dblclick(function(e){
			var _target=$(e.target);
			if(e.target.tagName.toLowerCase()!="td"){
				_target=_target.closest("td");
			}
			var _tD=$(".calendarGregorian",_target).html();
			if(_tD.trim().length==0)return
			_tD=parseInt(_tD);
			_tD=(_tD<10)?("0"+_tD):_tD;
			var _tM=(tM+1<10)?("0"+(tM+1)):tM+1;
			var _tY=tY;
			var today=""+tY+_tM+_tD;
			options.dblclick&&options.dblclick(today,_target);
			return
		});
	}

	$.fn.initCalendar=function(calendar,options){
		//var _calendar={};
		if(options)param=options;
		dStyle =$(".detail",calendar)[0].style;
		fillSelect(calendar);
		calendar.append(createCalendarBody(param));
		if(options.date){
			tY=parseInt(options.date.slice(0,4),10);
			tM=parseInt(options.date.slice(4,6),10)-1;
		}
		calendar.find("#sy")[0].selectedIndex=tY-1900;
		calendar.find("#sm")[0].selectedIndex=tM;
		drawCld(calendar,tY,tM,param);

		bindEvent(calendar,param);

		calendar.setDate=function(date){
			if(date.length%2==1){
				alertMsg.info("请输入正确日期格式(yyyyMM或者yyyyMMdd),如201508,20150803");
				return;
			}
			var year,month;
			year=parseInt(date.slice(0,4),10);
			month=parseInt(date.slice(4,6),10)-1;
			calendar.find("#sy")[0].selectedIndex=year-1900;
			calendar.find("#sm")[0].selectedIndex=month;
			drawCld(calendar,year,month,options);
		};
		calendar.getDate=function(){
			if(cld.length<=0)return;
			var month=(cld[0].sMonth<10)?"0"+cld[0].sMonth:cld[0].sMonth;
			return ""+cld[0].sYear+month;
		};
		return calendar;
	}

	function TimeAdd(UTC,T)
	{
		var PlusMinus, DST, y;
		alert(UTC);
		if (T.substr(0,1) == "-"){
			PlusMinus = -1;
		}
		else{
			PlusMinus = 1;
		}
	}

});

define(['jquery', 'calendar.core'], function($) {
    /**
     * calendar 日历控件
     * @param calenderType 日历模式 选项（normal/simple)  默认 normal
     * @param headerType 头部年月份 选项（select/text/none） 默认 select
     * @param iShowLunar 是否显示农历日期 选项（true/false） 默认 true 优先级高于 iShowSolarFes
     * @param iShowSolarFes 是否显示阳历节日 选项（true/false） 默认 true
     */
    $.fn.calendar = function(options) {
        var defaultSetting = {
            calenderType: "normal",
            headerTpye: "select",
            iShowLunar: true,
            iShowSolarFes: true
        }
        var $calender = $(this);
        $calender.addClass('calendar');
        var opts = $.extend({}, defaultSetting, options);
        $calender.append(createCalendarDetail());
        $calender.append(createCalendarHead(opts));
        (opts.calenderType == "simple") && $calender.addClass('simple-calendar');
        if (opts.headerTpye == "text") {
            var date = new Date(),
                currentDay = date.getFullYear() + "年" + (date.getMonth() + 1) + "月" + date.getDay() + "日",
                calendarTime = $("<div class='calendarTime'></div>");
            calendarTime.html(currentDay);
            $(".detail", $calender).before(calendarTime);
            $(".calendar-top", $calender).hide();
        }
        $calender = $calender.initCalendar($calender, opts);
        $calender.data("calendar", $calender);
        return $calender;
    }

    var createCalendarDetail = function() {
        return $("<div class='detail'><div class='datedetail'></div><div class='festival'></div></div>");
    }

    var createCalendarHead = function(opts) {
        var calendar_top = $("<div class='calendar-top'></div>");
        var prev = $("<a href='#' class='a-prev'></a>");

        var date = $("<span class='calendar-date'></span>");
        var year = $("<select id='sy' class='select-year' ></select><span class='calendar-tip'>年</span>");

        var month = $("<select class='select-month' id='sm' ></select><span  class='calendar-tip'>月</span> <span id='gz' class='hide' >&nbsp;</span>");

        var next = $("<a href='#' class='a-next'></a>");

        date.append(year).append(month);
        calendar_top.append(prev).append(date).append(next);
        return calendar_top;
    }
});

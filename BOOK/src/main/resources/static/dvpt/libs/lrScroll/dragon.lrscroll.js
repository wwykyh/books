(function ($) {
    var defaultSetting = {
        btnPrev: ".prev", //btnPrev text
        btnNext: ".next",  //btnNext text
        btnGo: null,
        mouseWheel: false,
        auto: null, //自动播放
        speed: 1000,//滚动速度
        easing: null,
        vertical: false,
        circular: false, //是否循环
        visible: 1, //显示个数
        start: 0,
        scroll: 1, //滚动个数
        imgChange: null, // 图片切换事件
        beforeStart: null,  //开始回调函数
        afterEnd: null //结束回调函数
    };

    var ajaxImg = function ($scroll, param) {
        var url = param.url;
        if (!url)url = "uploader/getUploaderBlob.action";
        if (!param.value) {
            $scroll.data("_postData", []);
            return;
        }
        $.post(url, {"uploaderBlob": param.value}, function (data) {
            $.each(data.result, function (index, item) {
                $scroll.addImgData(item);
            });
            $scroll.data("_postData", data.result.length > 0 ? [eval('(' + param.value + ')')] : []);
        }, "json");
    }
    var showImg = function ($scroll, result, isUpdate) {
        var size = $("li", $("ul", $scroll)).size();
        result.size = size;
        if (size == 0) {
            $(".scrollFooter", $scroll).find("label").text("0/0");
            $scroll.css("height", "auto");
            return;
        }
        $(".scrollFooter", $scroll).find("label").text(1 + "/" + size);
        $scroll.find("img").css({
            "width": $scroll.width(),
            "height": $(".scrollContent", $scroll).height()
        }).parent("li").
            css({"width": $scroll.width(), "height": $(".scrollContent", $scroll).height()});

        $scroll.children(".scrollContent").jCarouselLite(result);
        $scroll.find("li").each(function () {
            var url = $(this).find("img").attr("src");
            $scroll.attr("businessId", $(this).find("img").attr("businessId"));//后端业务数据
            if ($(this).find("img").parent().attr("href"))return;
            var $a = $("<a href='" + url + "'></a>");
            $(this).append($a);
            $(this).find("img").appendTo($a);
        });
        $scroll.find("li>a").lightBox();
        // $scroll.css("height","auto");
        $scroll.css({
            width: $scroll.find("li").outerWidth(),
            height: "auto"
        }).find(".scrollContent").css({
            width: $scroll.find("li").outerWidth(),
            height: $scroll.find("li").outerHeight()
        });

    }
    var valueStr2Json = function (props) {
        var value = props.value || "";
        var obj = {};
        if (value && value.indexOf(":") < 0)
            obj["businessId"] = value;
        else
            obj =eval('(' + value + ')');
        props.value = $.isEmptyObject(obj) ? "" : JSON.stringify(obj);
    }
    $.fn.scrollDp = function ($scroll) {
        var props = $scroll.data("props")||"";
        props=eval("({"+props+"})");
        //valueStr2Json(props);
        $scroll.addClass("jScroll");
        var result = $.extend({}, defaultSetting, props);
        var id = "scroll_" + new Date().getTime();
        var preId = id + "_prev";
        var nextId = id + "_next";
        $.extend(result,
            {
                btnPrev: "#" + preId, //btnPrev text
                btnNext: "#" + nextId,  //btnNext text
                beforeStart: function ($li) {
                    props.beforeStart && props.beforeStart($li);
                },
                afterEnd: function ($li) {
                    $(".scrollFooter", $scroll).find("label").text(($li.index() + 1) + "/" + result.size);
                    props.afterEnd && props.afterEnd($li);
                }
            }
        );

        var footer = $('<div class="scrollFooter"><div class="scrollFooterInner">' +
        '<a class="prev" id="' + preId + '"></a><label>0/0</label><a class="next" id="' + nextId + '"></a></div></div>');
        $scroll.prepend(footer);
        $scroll.prepend('<div class="scrollContent"><ul class="scrollLists"></ul></div>');
        $scroll.css({width: result.width, height: result.height});
        $scroll.find(".scrollContent").css({width: result.width, height: result.height})
        var $ul = $scroll.find(".scrollLists");

        if (result.sourceType == "qqfw") {
            qqfw({
                condition: result.condition,
                $ul: $ul,
                style: result.imgStyle,
                $scroll: $scroll,
                result: result,
                callback: showImg
            });
            $scroll.find("img").each(function (index) {
                $("<li></li>").append(this).appendTo($ul);
            });
            showImg();
        } else if (result.sourceType == "ajax") {
            ajaxImg($scroll, props);
            showImg($scroll, result);
        } else {
            $scroll.find("img").each(function (index) {
                var $li = $("<li></li>");
                $li.append(this).appendTo($ul);
            });
            showImg($scroll, result);
        }
        /** 没图片加个样式 **/
        if ($scroll.find("img").length <= 0)
            $scroll.addClass("no-picture");
        $scroll.data("lrScroll", $scroll);
        $scroll.get(0).checked = true;
        /**  函数方法 **/
        $scroll.addImg = function (srcArray, styleArray) {
            $("#" + preId).unbind("click");
            $("#" + nextId).unbind("click");
            if ($.isArray(srcArray)) {
                $.each(srcArray, function (index) {
                    var img = $("<img   />");
                    $.each(srcArray[index], function (key, value) {
                        img.attr(key, value);
                        if (key == "url") img.attr("src", value);
                    });
                    $("<li></li>").append(img).appendTo($("ul", $scroll));
                })
                showImg($scroll, result, true);
                return;
            }
            if (!styleArray)styleArray = "";
            srcArray = srcArray.split(",");
            styleArray = styleArray.split(",");
            $.each(srcArray, function (index) {
                var img = $("<img src=' " + this + " '  >");
                (styleArray[index]) && (img.attr("style", styleArray));
                $("<li></li>").append(img).appendTo($("ul", $scroll));
            })
            showImg($scroll, result, true);
        },

        /*64位缩略图预览*/
        $scroll.addThumbImg=function(src,attrObj){
            $("#"+preId).unbind("click");
            $("#"+nextId).unbind("click");
            var img=$("<img src=' "+src+" '  >");
            if(attrObj){
                for(var i in attrObj){
                    img.attr(i,attrObj[i]);
                }
            }
            $("<li></li>").append(img).appendTo($("ul",$scroll));
            showImg($scroll,result,true);
            return $scroll;
        }
        
        $scroll.addImgData = function (data) {
            $.extend(data, DWZ.jsonEval(props.value));
            var postData = $scroll.data("postData") || [];
            $scroll.addImg([data]);
            delete data.url;
            delete data.size;
            delete data.oriName;
            postData.push(data);
            $scroll.data("postData", postData);
        },
        $scroll.deleteImgData = function (index) {
            var postData = $scroll.data("postData") || [];
            postData.splice(index - 1, 1);
            $scroll.data("postData", postData);
            $scroll.deleteImg(index);
        },
        $scroll.deleteImg = function (index) {
            $("#" + preId).unbind("click");
            $("#" + nextId).unbind("click");
            if (!index) {
                var index = parseInt($(".scrollFooter").find("label").text().split("/")[0]);
                if (index <= 0) return;
                $("ul>li", $scroll).eq(index - 1).remove();
                showImg($scroll, result, true);
                return;
            }
            var num = $("li", $("ul", $scroll)).size();
            if (index > num || index < 0) return;
            var li = $("li", $("ul", $scroll));
            $(li[index - 1]).remove();
            showImg($scroll, result, true);
        },
        $scroll.reload = function () {
            showImg($scroll, result);
        },
        $scroll.getIndex = function () {
            var index = parseInt($(".scrollFooter").find("label").text().split("/")[0]);
            return index;
        },

        $scroll.getImage = function (index) {
            if (typeof index=="undefined")index = $scroll.getIndex();
            return $("ul>li", $scroll).eq(index).find("img");
        },
        $scroll.load4qqfw = function (condition, serviceId) {
            condition.SFZH = "'" + condition.SFZH + "'";
            for (var key in condition) {
                condition[key] = "'" + condition[key] + "'";
            }
            if (!serviceId)serviceId = "QueryQGRKXP";
            var numUrl = $.fn.contextPath + "rest/qqfwresource/cachePhoto/" + serviceId + ".action";
            $.post(numUrl, {'conditions': $.param(condition)}, function (data) {
                $.each(data.result, function (index, item) {
                    $scroll.addImgData(item);
                });
                showImg($scroll, result);
            });
        },
        $scroll.getImgNum = function () {
            return $("li", $("ul", $scroll)).size();
        };

        //todo 显示指定图片
        $scroll.showImage = function (index) {

            //debugger
        }

        return $scroll;
    }
})(jQuery);
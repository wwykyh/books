/*!
 * jQuery FlexBox $Version: 0.9.6 $
 *
 * Copyright (c) 2008-2010 Noah Heldman and Fairway Technologies (http://www.fairwaytech.com/flexbox)
 * Licensed under Ms-PL (http://www.codeplex.com/flexbox/license)
 *
 * $Date: 2010-11-24 01:02:00 PM $
 * $Rev: 0.9.6.1 $
 */
define(['jquery','phoneticize'],function($) {
    // 定位
    var flexBoxes=[];

    // 隐藏其它下拉项
    var hideOtherDropDownBox=function(){
        $(".dropDownBox").each(function(){
            var panelId=$(this).data("panelId");
            if ($("#"+panelId).is(":hidden")) return;
            $("#"+panelId).hide();
        });
        $.each(flexBoxes, function() {

            if(this.ctr.is(":visible"))this.ctr.hide();
        });
    }
    // 位置定位(若到底则显示在上方)
    var postion=function($input,$ctr){
        var bottomHeight=$(window).height() - $input.offset().top - $input.height();
        if(bottomHeight < $ctr.outerHeight()){
            $ctr.css('top', $input.offset().top - $ctr.outerHeight());
        }else{
            $ctr.css('top', $input.offset().top + $input.outerHeight());
        }
        $ctr.css('left', $input.offset().left);
    }

    // flexBoxes 隐藏
    $.fn.flexboxResizePos=function(){
        $.each(flexBoxes, function() {
            if(this.ctr.is(":visible")) this.ctr.hide();
        });
    }

    /**
     * [flexbox description]
     * @param  {[type]} div [description]
     * @param  {[type]} o   [description]
     * @return {[type]}     [description]
     */
    $.flexbox = function(div, o) {
        var timeout = false, 	// hold timeout ID for suggestion results to appear
            cache = [], 		    // simple array with cacheData key values, MRU is the first element
            cacheData = [],         // associative array holding actual cached data
            cacheSize = 0, 		    // size of cache in bytes (cache up to o.maxCacheBytes bytes)
            delim = '\u25CA',       // use an obscure unicode character (lozenge) as the cache key delimiter【菱形作为缓存键分割符】
            scrolling = false,
            pageSize = o.paging && o.paging.pageSize ? o.paging.pageSize : 0,
            retrievingRemoteData = false,
            $div = $(div).css('z-index', 0);
        o.width = parseInt($div.width());
        o.filterMode = 'filter';	//数据项过滤模式 filter-过滤  full-完整显示

        // 初始拼音码
        if (typeof (o.source) === 'object') {
            for (var i=0; i < o.source[o.resultsProperty].length; i++) {
                var row = o.source[o.resultsProperty][i];
                // row.pym = getPY(row[o.displayValue]);
                row.pym = pinyin.getCamelChars(row[o.displayValue]);
            }
        }

        var nowTime=(new Date()).getTime();
        // The hiddenField MUST be appended to the div before the input, or IE7 does not shift the dropdown below the input field (it overlaps)
        var $hdn = $('<input  validateHidden/>')
            .attr('id', $div.attr('id') + '_hidden')
            .attr('name', o.hiddenName)
            .attr('class', $div.attr('class'))
            .css("display","none")
            .val(o.initialValue)
            .appendTo($div);
        var $input = $('<input/>')//文本框
            .attr('id', nowTime)
            .attr('autocomplete', 'off')
            .addClass(o.inputClass)
            .css('width', (o.width) + 'px')
            .appendTo($div)
            .click(function(e) {
                if (o.watermark !== '' && this.value === o.watermark)
                    this.value = '';
                else
                    this.select();
            })
            .focus(function(e) {
                $(this).removeClass('watermark');
            })
            .blur(function(e) {
                //setTimeout(function() { if (!$input.data('active')) hideResults(); }, 200);
            })
            .keydown(processKeyDown);

        if (o.initialValue !== ''){
            setFlexBoxValue(o.initialValue, '', true);
            $input.removeClass('watermark');
        }else{
            $input.val(o.watermark).addClass('watermark');
        }

        var arrowWidth = 0;
        if (o.showArrow && o.showResults) {
            var arrowClick = function(event) {
                if($(this).hasClass("disabled")||$(this).hasClass("readonly")) return;
                $input=$(event.target).siblings(".ffb-input");//改变下拉框弹出层定位方式 yh 2015.12.14
                // 存在禁用、只读属性，三角点击事件不执行 yh 2015.7.8
                if ($ctr.is(':visible')) {
                    hideResults();
                }else {
                    hideOtherDropDownBox();
                    $input.focus();
                    if (o.watermark !== '' && $input.val() === o.watermark)
                        $input.val('');
                    else
                        $input.select();
                    if (timeout)
                        clearTimeout(timeout);
                    o.filterMode = "full";
                    timeout = setTimeout(function() { flexbox(1, true, o.arrowQuery); }, o.queryDelay);
                }
                event.stopPropagation();    //  阻止三角事件冒泡 yh2015.5.13
            };
            var $arrow = $('<span></span>')//下拉图标事件
                .attr('id', $div.attr('id') + '_arrow')
                .addClass(o.arrowClass)
                .addClass('out')
                .hover(function() {
                    //存在只读、禁用属性，禁用下拉图标鼠标指向样式 yh 2015.7.9
                    if($(this).hasClass("disabled")||$(this).hasClass("readonly")) return;
                    $(this).removeClass('out').addClass('over');
                }, function() {
                    $(this).removeClass('over').addClass('out');
                })
                .mousedown(function() {
                    //存在只读、禁用属性，禁用下拉图标鼠标指向样式 yh 2015.7.9
                    if($(this).hasClass("disabled")||$(this).hasClass("readonly")) return;
                    $(this).removeClass('over').addClass('active');
                })
                .mouseup(function() {
                    //存在只读、禁用属性，禁用下拉图标鼠标指向样式 yh 2015.7.9
                    if($(this).hasClass("disabled")||$(this).hasClass("readonly")) return;
                    $(this).removeClass('active').addClass('over');
                })
                .click(arrowClick)
                .appendTo($div);
            arrowWidth = $arrow.width();
            $input.css('width', (o.width - arrowWidth + 1) + 'px');

            //重新调整最顶层的div，适应实际宽度，不换行
            $div.width($input.outerWidth() + (($.browser.version=="6.0")?2:0));
        }else{
            $div.width($input.outerWidth());
        }

        if (!o.allowInput) { o.selectFirstMatch = false; $input.click(arrowClick); } // simulate <select> behavior

        // Handle presence of CSS Universal Selector (*) that defines padding by verifying what the browser thinks the outerHeight is.
        // In FF, the outerHeight() will not pick up the correct input field padding
        var inputPad = $input.outerHeight() - $input.height() - 2;
        var inputWidth = $input.innerWidth();
        var ObjBottom = $(window).height() - $input.offset().top - $input.height();
        var top = $input.offset().top + $input.outerHeight();
        var inputHeight=$input.outerHeight();
        if (inputPad === 0) {
            inputWidth += 4;
            top += 4;
        }else if (inputPad !== 4) {
            inputWidth += inputPad;
            top += inputPad;
        }

        var $ctr = $('<div></div>')//$ctr 下拉层
            .attr('id', 'ctr'+ nowTime)
            .css('width', inputWidth)
            .css('top', top)
            .css('left', $input.offset().left)
            .css('z-index', 99900)  //yangjy 2015-3-2  对话框弹出层可见 修改弹出层优先级，低于对话框
            .addClass(o.containerClass)
            .appendTo("body")
            .mousedown(function(e) {
                $input.data('active', true);
            })
            .hide();
        flexBoxes.push({input:$input,ctr:$ctr});//yangjy 存取所有对象
        $(document).click(function (e) {    //yangjy 2015-5-5 点击左边菜单未隐藏菜单
            if ($(e.target)[0] === $ctr[0] ||
                $(e.target).parents("#"+$ctr.attr("id"))[0] === $ctr[0]) {
                return;
            }
            if (($(e.target)[0] === $ctr[0] ||
                $(e.target).parents('.ffb')[0] !== $ctr) &&
                $ctr.is(":visible")) {
                hideResults();
            }
        });


        var $content = $('<div></div>')
            .addClass(o.contentClass)
            .appendTo($ctr)
            .scroll(function() {
                scrolling = true;
            });

        var $paging = $('<div></div>').appendTo($ctr);
        $div.css('height', $input.outerHeight());

        function processKeyDown(e) {
            // handle modifiers
            o.filterMode = 'filter';
            var mod = 0;
            if (typeof (e.ctrlKey) !== 'undefined') {
                if (e.ctrlKey) mod |= 1;
                if (e.shiftKey) mod |= 2;
            } else {
                if (e.modifiers & Event.CONTROL_MASK) mod |= 1;
                if (e.modifiers & Event.SHIFT_MASK) mod |= 2;
            }
            // if the keyCode is one of the modifiers, bail out (we'll catch it on the next keypress)
            if (/16$|17$/.test(e.keyCode)) return; // 16 = Shift, 17 = Ctrl

            var tab = e.keyCode === 9, esc = e.keyCode === 27;
            var tabWithModifiers = e.keyCode === 9 && mod > 0;
            var backspace = e.keyCode === 8; // we will end up extending the delay time for backspaces...

            // tab is a special case, since we want to bubble events...
            if (tab) if (getCurr()) selectCurr();

            // handling up/down/escape/right arrow/left arrow requires results to be visible
            // handling enter requires that AND a result to be selected
            if ((/27$|38$|33$|34$/.test(e.keyCode) && $ctr.is(':visible')) ||
                (/13$|40$/.test(e.keyCode)) || !o.allowInput) {

                if (e.preventDefault) e.preventDefault();
                if (e.stopPropagation) e.stopPropagation();

                //ie 下阻止冒泡
                e.cancelBubble = true;
                e.returnValue = false;

                switch (e.keyCode) {
                    case 38: // up arrow
                        prevResult();
                        break;
                    case 40: // down arrow
                        if ($ctr.is(':visible')) nextResult();
                        else flexboxDelay(true);
                        break;
                    case 13: // enter
                        if (getCurr()) selectCurr();
                        else flexboxDelay(true);
                        break;
                    case 27: //	escape
                        hideResults();
                        break;
                    case 34: // page down
                        if (!retrievingRemoteData) {
                            if (o.paging) $('#' + $div.attr('id') + 'n').click();
                            else nextPage();
                        }
                        break;
                    case 33: // page up
                        if (!retrievingRemoteData) {
                            if (o.paging) $('#' + $div.attr('id') + 'p').click();
                            else prevPage();
                        }
                        break;
                    default:
                        if (!o.allowInput) { return; }
                }
            } else if (!esc && !tab && !tabWithModifiers) { // skip esc and tab key and any modifiers
                flexboxDelay(false, backspace);
            }
        }

        function flexboxDelay(simulateArrowClick, increaseDelay) {
            if (timeout) clearTimeout(timeout);
            var delay = increaseDelay ? o.queryDelay * 5 : o.queryDelay;
            timeout = setTimeout(function() { flexbox(1, simulateArrowClick, ''); }, delay);
        }

        function flexbox(p, arrowOrPagingClicked, prevQuery) {
            if (arrowOrPagingClicked) prevQuery = '';
            var q = prevQuery && prevQuery.length > 0 ? prevQuery : $.trim($input.val());

            if(o.filterMode == 'full') q="";

            if (q.length >= o.minChars || arrowOrPagingClicked) {
                // If we are getting data from the server, set the height of the content box so it doesn't shrink when navigating between pages, due to the $content.html('') below...
                if ($content.outerHeight() > 0)
                    $content.css('height', $content.outerHeight());
                $content.html('').attr('scrollTop', 0);

                var params = { q: q, p: p, s: pageSize, contentType: 'application/json; charset=utf-8' };
                var callback = function(data, overrideQuery) {
                    if (overrideQuery === true) q = overrideQuery; // must compare to boolean because by default, the string value "success" is passed when the jQuery $.getJSON method's callback is called
                    var totalResults = parseInt(data[o.totalProperty]);

                    // Handle client-side paging, if any paging configuration options were specified
                    if (isNaN(totalResults) && o.paging) {
                        if (o.maxCacheBytes <= 0) alert('The "maxCacheBytes" configuration option must be greater\nthan zero when implementing client-side paging.');
                        totalResults = data[o.resultsProperty].length;

                        var pages = totalResults / pageSize;
                        if (totalResults % pageSize > 0) pages = parseInt(++pages);

                        for (var i = 1; i <= pages; i++) {
                            var pageData = {};
                            pageData[o.totalProperty] = totalResults;
                            pageData[o.resultsProperty] = data[o.resultsProperty].splice(0, pageSize);
                            if (i === 1) totalSize = displayItems(pageData, q);
                            // updateCache(q, i, pageSize, totalResults, pageData, totalSize);
                        }
                    }
                    else {
                        var totalSize = displayItems(data, q);
                        // updateCache(q, p, pageSize, totalResults, data, totalSize);
                    }
                    showPaging(p, totalResults);
                    $content.css('height', 'auto');
                    retrievingRemoteData = false;
                };
                if (typeof (o.source) === 'object') {
                    if (o.allowInput) callback(filter(o.source, params));
                    else callback(o.source);
                }
                else {
                    retrievingRemoteData = true;
                    if (o.method.toUpperCase() == 'POST') $.post(o.source, params, callback, 'json');
                    else $.getJSON(o.source, params, callback);
                }
                if($.browser.version=="6.0"||$.browser.version=="8.0")ObjBottom=$(window).height()- $input.offset().top - $input.height();
                if(ObjBottom < $ctr.outerHeight()){
                    $ctr.css('top', $input.offset().top - $ctr.outerHeight());
                    $ctr.css('left', $input.offset().left);  //yangjy 2015-3-2 增加下拉层左边定位
                }else{
                    $ctr.css('top', $input.offset().top+inputHeight);  //yangjy 2015-3-2 增加下拉层top定位
                    $ctr.css('left', $input.offset().left);  //yangjy 2015-3-2 增加下拉层左边定位
                }
            } else
                hideResults();
        }

        function filter(data, params) {
            var filtered = {};
            filtered[o.resultsProperty] = [];
            filtered[o.totalProperty] = 0;
            var index = 0;

            for (var i=0; i < data[o.resultsProperty].length; i++) {
                var indexOfMatch = data[o.resultsProperty][i][o.displayValue].toLowerCase().indexOf(params.q.toLowerCase());
                var pymMatch = data[o.resultsProperty][i]['pym'].indexOf(params.q.toUpperCase());
                if ((o.matchAny && indexOfMatch !== -1) ||
                    (o.matchAny &&  pymMatch !== -1) ||
                    (!o.matchAny && indexOfMatch === 0)) {
                    filtered[o.resultsProperty][index++] = data[o.resultsProperty][i];
                    filtered[o.totalProperty] += 1;
                }
            }
            if (o.paging) {
                var start = (params.p - 1) * params.s;
                var howMany = (start + params.s) > filtered[o.totalProperty] ? filtered[o.totalProperty] - start : params.s;
                filtered[o.resultsProperty] = filtered[o.resultsProperty].splice(start, howMany);
            }
            return filtered;
        }

        function showPaging(p, totalResults) {
            $paging.html('').removeClass(o.paging.cssClass); // clear out for threshold scenarios
            if (o.showResults && o.paging && totalResults > pageSize) {
                $paging.css('height', 'auto');
                var pages = totalResults / pageSize;
                if (totalResults % pageSize > 0) pages = parseInt(++pages);
                outputPagingLinks(pages, p, totalResults);
            }else{
                $paging.css('height', '0');
            }
        }

        function handleKeyPress(e, page, totalPages) {
            if (/^13$|^39$|^37$/.test(e.keyCode)) {
                if (e.preventDefault)
                    e.preventDefault();
                if (e.stopPropagation)
                    e.stopPropagation();

                e.cancelBubble = true;
                e.returnValue = false;

                switch (e.keyCode) {
                    case 13: // Enter
                        if (/^\d+$/.test(page) && page > 0 && page <= totalPages)
                            flexbox(page, true);
                        else
                            alertMsg.info("请输入 1 到 "+totalPages+"之间的页面序号");
                            //alert('Please enter a page number between 1 and ' + totalPages);
                        // TODO: make this alert a function call, and a customizable parameter
                        break;
                    case 39: // right arrow
                        $('#' + $div.attr('id') + 'n').click();
                        break;
                    case 37: // left arrow
                        $('#' + $div.attr('id') + 'p').click();
                        break;
                }
            }
        }

        function handlePagingClick(e) {
            flexbox(parseInt($(this).attr('page')), true, $input.attr('pq')); // pq == previous query
            return false;
        }

        function outputPagingLinks(totalPages, currentPage, totalResults) {
            // TODO: make these configurable images
            var first = '',
                prev = '',
                next = '',
                last = '',
                more = '...';

            $paging.addClass(o.paging.cssClass);

            // set up our base page link element
            var $link = $('<a/>')
                    .attr('href', '#')
                    .addClass('pager')
                    .click(handlePagingClick),
                $span = $('<span></span>').addClass('pager'),
                divId = $div.attr('id');

            // show first page
            if (currentPage > 1) {
                $link.clone(true).attr('id', divId + 'f').attr('page', 1).addClass('first-btn').html(first).appendTo($paging);
                $link.clone(true).attr('id', divId + 'p').attr('page', currentPage - 1).addClass("prev-btn").html(prev).appendTo($paging);
            }
            else {
                $span.clone(true).addClass('first-btn').html(first).appendTo($paging);
                $span.clone(true).addClass("prev-btn").html(prev).appendTo($paging);
            }

            if (o.paging.style === 'links') {
                var maxPageLinks = o.paging.maxPageLinks;
                // show page numbers
                if (totalPages <= maxPageLinks) {
                    for (var i = 1; i <= totalPages; i++) {
                        if (i === currentPage) {
                            $span.clone(true).html(currentPage).appendTo($paging);
                        }
                        else {
                            $link.clone(true).attr('page', i).html(i).appendTo($paging);
                        }
                    }
                }
                else {
                    if ((currentPage + parseInt(maxPageLinks / 2)) > totalPages) {
                        startPage = totalPages - maxPageLinks + 1;
                    }
                    else {
                        startPage = currentPage - parseInt(maxPageLinks / 2);
                    }

                    if (startPage > 1) {
                        $link.clone(true).attr('page', startPage - 1).html(more).appendTo($paging);
                    }
                    else {
                        startPage = 1;
                    }

                    for (var i = startPage; i < startPage + maxPageLinks; i++) {
                        if (i === currentPage) {
                            $span.clone(true).html(i).appendTo($paging);
                        }
                        else {
                            $link.clone(true).attr('page', i).html(i).appendTo($paging);
                        }
                    }

                    if (totalPages > (startPage + maxPageLinks)) {
                        $link.clone(true).attr('page', i).html(more).appendTo($paging);
                    }
                }
            }
            else if (o.paging.style === 'input') {
                var $pagingBox = $('<input/>')
                    .addClass('page-input')
                    .click(function(e) {
                        this.select();
                    })
                    .keypress(function(e) {
                        return handleKeyPress(e, this.value, totalPages);
                    })
                    .val(currentPage)
                    .appendTo($paging);
            }

            if (currentPage < totalPages) {
                $link.clone(true).attr('id', divId + 'n').attr('page', +currentPage + 1).addClass('next-btn').html(next).appendTo($paging);
                $link.clone(true).attr('id', divId + 'l').attr('page', totalPages).addClass('last-btn').html(last).appendTo($paging);
            }
            else {
                $span.clone(true).addClass('next-btn').html(next).appendTo($paging);
                $span.clone(true).addClass('last-btn').html(last).appendTo($paging);
            }
            var startingResult = (currentPage - 1) * pageSize + 1;
            var endingResult = (startingResult > (totalResults - pageSize)) ? totalResults : startingResult + pageSize - 1;

            if (o.paging.showSummary) {
                var summaryData = {
                    "start": startingResult,
                    "end": endingResult,
                    "total": totalResults,
                    "page": currentPage,
                    "pages": totalPages
                };
                var html = o.paging.summaryTemplate.applyTemplate(summaryData);
                $('<br/>').appendTo($paging);
                $('<div></div>')
                    .addClass(o.paging.summaryClass)
                    .html(html)
                    .appendTo($paging);
            }
        }

        function displayItems(d, q) {
            var totalSize = 0, itemCount = 0;

            if (!d)
                return;

            if (parseInt(d[o.totalProperty]) === 0 && o.noResultsText && o.noResultsText.length > 0) {
                $content.addClass(o.noResultsClass).html(o.noResultsText);
                $ctr.show();
                return;
            } else $content.removeClass(o.noResultsClass);

            for (var i = 0; i < d[o.resultsProperty].length; i++) {
                var data = d[o.resultsProperty][i],
                    result = o.resultTemplate.applyTemplate(data),
                    exactMatch = q === result,
                    selectedMatch = false,
                    hasHtmlTags = false,
                    match = data[o.displayValue];

                if (!exactMatch && o.highlightMatches && q !== '') {
                    var pattern = q,
                        highlightStart = match.toLowerCase().indexOf(q.toLowerCase()),
                        replaceString = '<span class="' + o.matchClass + '">' + match.substr(highlightStart,q.length) + '</span>';
                    if (result.match('<(.|\n)*?>')) { // see if the content contains html tags
                        hasHtmlTags = true;
                        pattern = '(>)([^<]*?)(' + q + ')((.|\n)*?)(<)'; // TODO: look for a better way
                        replaceString = '$1$2<span class="' + o.matchClass + '">$3</span>$4$6';
                    }
                    result = result.replace(new RegExp(pattern, o.highlightMatchesRegExModifier), replaceString);
                }

                // write the value of the first match to the input box, and select the remainder,
                // but only if autoCompleteFirstMatch is set, and there are no html tags in the response
                if (o.autoCompleteFirstMatch && !hasHtmlTags && i === 0) {
                    if (q.length > 0 && match.toLowerCase().indexOf(q.toLowerCase()) === 0) {
                        $input.attr('pq', q); // pq == previous query
                        setFlexBoxValue(data[o.hiddenValue], data[o.displayValue], false);
                        selectedMatch = selectRange(q.length, $input.val().length);
                    }
                }

                if (!o.showResults) return;

                $row = $('<div></div>')
                    .attr('id', data[o.hiddenValue])
                    .attr('val', data[o.displayValue])
                    .attr('title', data[o.displayValue])
                    .addClass('row')
                    .html(result)
                    .appendTo($content);

                if (exactMatch || (++itemCount == 1 && o.selectFirstMatch) || selectedMatch) {
                    $row.addClass(o.selectClass);
                }
                totalSize += result.length;
            }

            if (totalSize === 0) {
                hideResults();
                return;
            }

            $ctr.parent().css('z-index', 11000);
            $ctr.show();
            $content //下拉项事件
                .children('div')
                .mouseover(function() {
                    $content.children('div').removeClass(o.selectClass);
                    $(this).addClass(o.selectClass);
                })
                .mouseup(function(e) {
                    e.preventDefault();
                    e.stopPropagation();
                    selectCurr();
                });

            if (o.maxVisibleRows > 0) {
                var maxHeight = $row.outerHeight() * o.maxVisibleRows;
                $content.css('max-height', maxHeight);
            }

            return totalSize;
        }

        function selectRange(s, l) {
            var tb = $input[0];
            if (tb.createTextRange) {
                var r = tb.createTextRange();
                r.moveStart('character', s);
                r.moveEnd('character', l - tb.value.length);
                r.select();
            } else if (tb.setSelectionRange) {
                tb.setSelectionRange(s, l);
            }
            tb.focus();
            return true;
        }

        String.prototype.applyTemplate = function(d) {
            try {
                if (d === '') return this;
                return this.replace(/{([^{}]*)}/g,
                    function(a, b) {
                        var r;
                        if (b.indexOf('.') !== -1) { // handle dot notation in {}, such as {Thumbnail.Url}
                            var ary = b.split('.');
                            var obj = d;
                            for (var i = 0; i < ary.length; i++)
                                obj = obj[ary[i]];
                            r = obj;
                        }
                        else
                            r = d[b];
                        if (typeof r === 'string' || typeof r === 'number') return r; else throw (a);
                    }
                );
            } catch (ex) {
                alert('Invalid JSON property ' + ex + ' found when trying to apply resultTemplate or paging.summaryTemplate.\nPlease check your spelling and try again.');
            }
        };

        function hideResults() { // 原没有参数
            if ($input.val() === ''){
                setFlexBoxValue('', '', true);
            }else{
                (o.manualInput)?setFlexBoxValue($hdn.val(), $input.val(), true):setFlexBoxValue($hdn.val(), '', true);  // 手动输入数据，默认关闭 yh 2015.8.24
                //setFlexBoxValue($hdn.val(), '', true);
            }

            $input.data('active', false); // for input blur
            $div.css('z-index', 0);
            $ctr.hide();
            o.filterMode = "filter";
        }

        function getCurr() {//获取当前被选中的
            if (!$ctr.is(':visible'))
                return false;

            var $curr = $content.children('div.' + o.selectClass);

            if (!$curr.length)
                $curr = false;

            return $curr;
        }

        function selectCurr() {
            $curr = getCurr();
            if ($curr) {
                setFlexBoxValue($curr.attr('id'), $curr.attr('val'), false);
                $curr.focus();
                hideResults();

                if (o.onSelect) {//选中之后的回调函数
                    o.onSelect.apply($input[0]);
                }
            }
        }

        function supportsGetBoxObjectFor() {
            try {
                document.getBoxObjectFor(document.body);
                return true;
            }
            catch (e) {
                return false;
            }
        }

        function supportsGetBoundingClientRect() {
            try {
                document.body.getBoundingClientRect();
                return true;
            }
            catch (e) {
                return false;
            }
        }

        function nextPage() {
            $curr = getCurr();

            if ($curr && $curr.next().length > 0) {
                $curr.removeClass(o.selectClass);

                for (var i = 0; i < o.maxVisibleRows; i++) {
                    if ($curr.next().length > 0) {
                        $curr = $curr.next();
                    }
                }

                $curr.addClass(o.selectClass);
                var scrollPos = $content.attr('scrollTop');
                $content.attr('scrollTop', scrollPos + $content.height());
            }
            else if (!$curr)
                $content.children('div:first-child').addClass(o.selectClass);
        }

        function prevPage() {
            $curr = getCurr();

            if ($curr && $curr.prev().length > 0) {
                $curr.removeClass(o.selectClass);

                for (var i = 0; i < o.maxVisibleRows; i++) {
                    if ($curr.prev().length > 0) {
                        $curr = $curr.prev();
                    }
                }

                $curr.addClass(o.selectClass);
                var scrollPos = $content.attr('scrollTop');
                $content.attr('scrollTop', scrollPos - $content.height());
            }
            else if (!$curr)
                $content.children('div:last-child').addClass(o.selectClass);
        }

        function nextResult() {
            $curr = getCurr();

            if ($curr && $curr.next().length > 0) {
                $curr.removeClass(o.selectClass).next().addClass(o.selectClass);
                var scrollPos = $content.attr('scrollTop'),
                    curr = $curr[0], parentBottom, bottom, height;
                if (supportsGetBoxObjectFor()) {
                    parentBottom = document.getBoxObjectFor($content[0]).y + $content.attr('offsetHeight');
                    bottom = document.getBoxObjectFor(curr).y + $curr.attr('offsetHeight');
                    height = document.getBoxObjectFor(curr).height;
                }
                else if (supportsGetBoundingClientRect()) {
                    parentBottom = $content[0].getBoundingClientRect().bottom;
                    var rect = curr.getBoundingClientRect();
                    bottom = rect.bottom;
                    height = bottom - rect.top;
                }
                if (bottom >= parentBottom)
                    $content.attr('scrollTop', scrollPos + height);
            }
            else if (!$curr)
                $content.children('div:first-child').addClass(o.selectClass);
        }

        function prevResult() { // up 键调用事件
            $curr = getCurr();

            if ($curr && $curr.prev().length > 0) {
                $curr.removeClass(o.selectClass).prev().addClass(o.selectClass);
                var scrollPos = $content.attr('scrollTop'),
                    curr = $curr[0],
                    parent = $curr.parent()[0],
                    parentTop, top, height;
                if (supportsGetBoxObjectFor()) {
                    height = document.getBoxObjectFor(curr).height;
                    parentTop = document.getBoxObjectFor($content[0]).y - (height * 2); // TODO: this is not working when i add another control...
                    top = document.getBoxObjectFor(curr).y - document.getBoxObjectFor($content[0]).y;
                }
                else if (supportsGetBoundingClientRect()) {
                    parentTop = parent.getBoundingClientRect().top;
                    var rect = curr.getBoundingClientRect();
                    top = rect.top;
                    height = rect.bottom - top;
                }
                if (top <= parentTop)
                    $content.attr('scrollTop', scrollPos - height);
            }
            else if (!$curr)
                $content.children('div:last-child').addClass(o.selectClass);
        }

        function setFlexBoxValue(hiddenVal, displayVal, fromUser){
            if(o.submitMode === 'hidden'){
                if(hiddenVal == ''){
                    $hdn.val('');
                    $input.val('');
                }else{
                    if(fromUser == false){
                        $hdn.val(hiddenVal);
                        $input.val(displayVal);
                    }else{
                        if(displayVal == ''){
                            $.each(o.source[o.resultsProperty], function(i, item){
                                if(item[o.hiddenValue] == hiddenVal){
                                    displayVal = item[o.displayValue];
                                    return;
                                }
                            });
                        }
                        if(displayVal == ''){
                            $hdn.val('');
                            $input.val('');
                        }else{//隐藏域赋值
                            $hdn.val(hiddenVal);
                            $input.val(displayVal);
                        }
                    }
                }
            }else{
                $hdn.val(displayVal);
            }
            $hdn.attr("displayValue",displayVal);//yangjy 2015-3-26 通过hdn获取显示值
            $hdn.trigger("change");
        }
    }

    /**
     * 主入口函数 $.fn.flexbox
     * @param  source 容器对象
     * @param  options 配置的参数
     */
    $.fn.flexbox = function(source, options) {
        if (!source)
            return;

        try {
            var defaults = {};
            var o = $.extend({}, defaults, options);
            o.source = source;

            if (options) {
                o.paging = (options.paging || options.paging == null) ? $.extend({}, defaults.paging, options.paging) : false;

                if (options.displayValue && !options.hiddenValue) {
                    o.hiddenValue = options.displayValue;
                }
            }

            this.each(function() {
                new $.flexbox(this, o);
            });

            return this;
        } catch (ex) {
            if (typeof ex === 'object') alert(ex.message); else alert(ex);
        }
    }

    /**
     * 设定值 
     * @param val 输入框值
     */
    $.fn.setValue = function(val) {
        var id = '#' + this.attr('id');
        $(id + '_hidden,' + id + '_input').val(val).removeClass('watermark');
    }
});
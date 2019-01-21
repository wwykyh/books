(function ($) {
    $.fn.extend({
        selectDrop: function (options) {
            var defaults = {
                width: 450, // 宽度限制
                height: 300,
                isOpenSpell: true, // 默认开启字母识别
                url: './js/libs/selectDrop/raceData.json', // 获取数据
            }

            // 参数继承
            var opts = $.extend(defaults, options);

            return this.each(function () {
                // 初始化
                var $this = $(this);
                var thumbArr = [];

                // 获取数据 根据选择的字母进行数据筛选显示
                $.getJSON(opts.url, function(data) {
                    // 生成DOM结构
                    buildDrop($this, data);
                    // 生成民族数据
                    buildItems(data);
                });

                function buildDrop($this, data){
                    // 构造弹窗
                    var $drop = $('<div id="'+ $this.attr("id") +'Pop" class="drop-pop"></div>');

                    // 新增拼音查找
                    if(opts.isOpenSpell) {
                        $drop.css('paddingTop', '70px');
                        $drop.append('<ul class="spell-box">'+
                                        '<li>A</li>'+
                                        '<li>B</li>'+
                                        '<li>C</li>'+
                                        '<li>D</li>'+
                                        '<li>E</li>'+
                                        '<li>F</li>'+
                                        '<li>G</li>'+
                                        '<li>H</li>'+
                                        '<li>I</li>'+
                                        '<li>J</li>'+
                                        '<li>K</li>'+
                                        '<li>L</li>'+
                                        '<li>M</li>'+
                                        '<li>N</li>'+
                                        '<li>O</li>'+
                                        '<li>P</li>'+
                                        '<li>Q</li>'+
                                        '<li>R</li>'+
                                        '<li>S</li>'+
                                        '<li>T</li>'+
                                        '<li>U</li>'+
                                        '<li>V</li>'+
                                        '<li>W</li>'+
                                        '<li>X</li>'+
                                        '<li>Y</li>'+
                                        '<li>Z</li>'+
                                    '</ul>'+
                                    '<span class="close" title="关闭">x</span>'+
                                    '<ul class="sd-list"></ul>');
                    } else {
                        $drop.css('paddingTop', '30px');
                        $drop.append('<span class="close" title="关闭">x</span>'+
                                    '<ul class="sd-list"></ul>');
                    }

                    $drop.css({
                        "position":"absolute",
                        "z-index":"20000",
                        "width": opts.width,
                        "height": opts.height, 
                        "top": $this.offset().top + $this.outerHeight() + 3,
                        "left":$this.offset().left - 3
                    }).appendTo('body');

                    // 进行选择字母筛选
                    $drop.find('.spell-box li').on('click', function() {
                        var $current = $(this);
                        var singleThumb = $current.text();
                        if($current.hasClass('active')) {
                            $current.removeClass('active');
                            $.each(thumbArr,function(index, item) {
                                if(item == singleThumb) {
                                    thumbArr.splice(index, 1);
                                }
                            })
                        } else {
                            $current.addClass('active');
                            thumbArr.push(singleThumb);
                        }
                        // 重新生成民族数据
                        buildItems(data);
                    });

                    // 关闭操作
                    $drop.children('.close').on('click', function(){
                        $drop.remove();
                    });

                    // 点击其他地方隐藏列表
                    $(document).on('click',function(e) {
                        var $current = $(e.target);
                        if($current.closest("#" + $this.attr('id') +"Pop").length == 0) {
                            $drop.remove();
                        }
                    })

                    return $drop;
                }

                function buildItems(data){
                    // 清空数据
                    var $container = $("#" + $this.attr("id") + "Pop");
                    $container.find('.sd-list').empty();
                    // 筛选对应的数据
                    for(var i = 0; i < data.length; i++){
                        var singleData = data[i];
                        if(thumbArr.length != 0) {
                            var tag = 1;
                            for(var j = 0; j < thumbArr.length; j++) {
                                var spell = singleData.spell;
                                if(!spell.match(thumbArr[j])) {
                                    tag = 0;
                                    break;
                                }
                            }
                            if(tag == 1) {
                                var item = '<li data-id="'+ singleData.code +'" data-spell="'+ singleData.spell +'" data-value="'+ singleData.value +'">'+ singleData.value +'</li>';
                                $container.find(".sd-list").append(item);
                            }
                        } else {
                            var item = '<li data-id="'+ singleData.code +'" data-spell="'+ singleData.spell +'" data-value="'+ singleData.value +'">'+ singleData.value +'</li>';
                            $container.find(".sd-list").append(item);
                        }
                    }
                    
                    $container.find('.sd-list li').on('click', function() {
                        var $current = $(this);
                        $("#" + $this.attr('id')).data({"id": $current.data('id'), "value": $current.data('value')});
                        $("#" + $this.attr('id')).val($current.data('value'));
                        $container.remove();
                    });
                }
            });
        }
    });
})(jQuery);

define(['jquery', 'jquery.multiselect'], function($) {
    /**
     * [multiSelect2Side 选择区]
     * options 参数列表
     * @param { Array} data 未选择区数据列
     * @param { Array} selected 选择的数据列
     * @param { String } selectedPosition 添加右侧的下拉框选择项位置
     * @param { Boolean } moveOptions 移动选项 用于排序和上下移
     * @param { String } labelTop/labelBottom/labelUp/labelDown/labelSort 移动选项的名称
     * @param { String } labelsx/labeldx 左右标题的名称
     * @param { String } maxSelected 最大选项数
     * @param { String } autoSort 自动排序
     * @param { String } search 搜索栏
     */
    $.fn.multiSelect2Side = function(options){
        // 初始化
        var $selectNode = $(this);

        // 默认参数
        var selectDefault = {
            data: [],
            selected: [],
            selectedPosition: 'right',
            moveOptions: true,
            labelTop: '顶部',
            labelBottom: '底部',
            labelUp: '上移',
            labelDown: '下移',
            labelSort: '排序',
            labelsx: 'Available',
            labeldx: 'Selected',
            maxSelected: -1,
            autoSort: false,
            autoSortAvailable: false,
            search: false,
            caseSensitive: false,
            delay: 200,
            optGroupSearch: false,
            minSize: 6
        };

        var select=$("<select multiple='multiple'></select>");
        select.attr("name",$selectNode.attr("name"));
        options.size&&select.attr("size",options.size);
        var option = $.extend(selectDefault,options);

        // 通过数据追加内容
        var data = option.data;
        for(var i = 0; i < data.length; i++){
            var $option = $("<option value='"+data[i]+"'>"+data[i]+"</option>");
            if(option.selected) iSelect(data[i],option.selected)&&$option.attr("selected","selected");
            select.append($option);
        }
        $selectNode.append(select.show());
        ($selectNode.attr("style"))&&$selectNode.find("select").attr("style",$selectNode.attr("style"));
        $selectNode.find("select").multiselect2side(option);
        $selectNode.find("select").eq(0).remove();

        $selectNode.getSelected = function(){
            var val=[];
            var options=$("#liOptionms2side__dx",$selectNode).find("option");
            $.each(options,function(){
                val.push($(this).val());
            });
            return val.toString();
        }

        $selectNode.setSelected = function(val){
            var select=$("#liOptionms2side__dx",$selectNode);
            select.find("option").remove();
            for(var i=0;i<val.length;i++){
                var $option=$("<option value='"+val[i]+"'>"+val[i]+"</option>");
                select.append($option);
            }
        }
        $selectNode.getAvailable = function(){
            var val = [];
            var $options = $("#liOptionms2side__sx",$selectNode).find("option");
            $.each($options,function(){
                val.push($(this).val());
            });
            return val.toString();
        }
        $selectNode.setAvailable=function(val){
            var select=$("#liOptionms2side__sx",$selectNode);
            select.find("option").remove();
            for(var i=0;i<val.length;i++){
                var $option=$("<option value='"+val[i]+"'>"+val[i]+"</option>");
                select.append($option);
            }
        }

        // 匹配是否已选数据
        function iSelect(value,selected) {
            for(var i=0;i<selected.length;i++){
                if(value==selected[i]) return true;
            }
            return false;
        }

        return $selectNode;
    }
});
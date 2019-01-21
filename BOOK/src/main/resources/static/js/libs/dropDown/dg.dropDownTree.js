define(['jquery','phoneticize','jquery.ztree','dg.data'], function($) {
    var dropDownTrees=[];
    /**
     * [dropDownTreeDp 下拉树]
     * @param {object} dpProps 配置的参数
     * @param dpProps.value 默认值
     * @param dpProps.key_value 数据值标识符 默认值 value
     * @param dpProps.key_code 数据编码标识符 默认值 code
     * @param dpProps.is_search 是否启用搜索 默认值 false
     * @param dpProps.is_ztrees_checked_open 是否打开选中列表 默认值 false
     * @param dpProps.url 数据路径 json 格式
     * @param dpProps.check {enable: true} 显示勾选状态
     */
    $.fn.dropDownTreeDp=function(dpProps){
        // 参数初始化
        if(!dpProps.key_value) dpProps.key_value = "value";
        if(!dpProps.key_code) dpProps.key_code = "code";
        if(!dpProps.is_search) dpProps.is_search = false;
        if(!dpProps.is_ztrees_checked_open) dpProps.is_ztrees_checked_open = false;
        var name = dpProps.name;
        var value = dpProps.value;

        // 对象初始化
        var $dropDownTree=$(this);
        var text = $dropDownTree.attr("text");
        $dropDownTree.attr("dpType","dropDownTree");
        $dropDownTree.css("display","none");

        // 创建相关dom元素
        var domObject = createDropDownDom($dropDownTree,dpProps);
        $dropDownTree = domObject.$inputDiv.find(".combo-text").prev();
        // 将配置参数 绑定到 Object
        domObject.dpProps=dpProps;

        var $inputDiv = domObject.$inputDiv;
        var $treeDiv = domObject.$treeDiv;
        $inputDiv.find(".combo-text").css("width",$inputDiv.find("input[dptype]").width()-$inputDiv.find(".clearIcon").width()-$inputDiv.find(".combo-arrow").width() + 1);
        if(dpProps.width){
            $inputDiv.find(".combo-text").css("width",parseInt(dpProps.width)-44);
            $inputDiv.find(".combo").css("width",parseInt(dpProps.width));
        }
        if(value&&text){
            domObject.$inputDiv.find(".combo-text").attr("value",text);
            domObject.$inputDiv.find(".combo-text").attr("title",text);
        }
        if(value&&!text){
            $.fn.getDataStore(dpProps,function(data){
                var childNodes= transformToArrayFormat(data);
                var valArray=value.split(",");
                var textValue="";
                var hasEqual={};// 表码有相同的只显示一个,如果已经匹配的就放进去，不做第二次匹配
                $.each(childNodes,function(){
                    var item=this;
                    if(hasEqual[item])return;
                    $.each(valArray,function(){
                        if(item[dpProps.key_code]==this){
                            (textValue.length>0)&&(textValue=textValue+",");
                            textValue=textValue+item[dpProps.key_value];
                            hasEqual[this]=true;
                        }
                    });
                });
                domObject.$inputDiv.find(".combo-text").attr("value",textValue);
                domObject.$inputDiv.find(".combo-text").attr("title",textValue);
                domObject.$inputDiv.find("input:hidden").val(value);
            });
        }
        $inputDiv.getTreeObj=function(){
            return $inputDiv.treeObj;
        }
        $inputDiv.getValue=function(){
            return $dropDownTree.attr("value");
        }
        $inputDiv.getText=function(){ //获取显示文本
            return $dropDownTree.attr("displayValue");
        }
        $inputDiv.getTextByValue=function(value){ //根据值获取显示文本
            return getTextByValue(value,$inputDiv.treeObj,dpProps);
        }
        $inputDiv.getValueByText=function(text){ //根据显示文本获取值
            return getValueByText(text,$inputDiv.treeObj,dpProps);
        }

        $inputDiv.setReadonly=function(read){
            if(read){
                //设置只读
                $inputDiv.children().children().addClass("readonly");
                $inputDiv.children().children().eq(1).attr("readonly","readonly");
            }else{
                //设置可用
                $inputDiv.children().children().removeClass("readonly");
                $inputDiv.children().children().eq(1).removeAttr("readonly");
            }
        }
        $inputDiv.setDisabled=function(able){
            if(able){
                //设置禁用，不做表单提交
                $inputDiv.children().children().addClass("disabled");
                $inputDiv.children().children().eq(1).attr("disabled","disabled");
            }else{
                //设置可用
                $inputDiv.children().children().removeClass("disabled");
                $inputDiv.children().children().eq(1).removeAttr("disabled");
            }
        }

        $inputDiv.setValue=function(value){
            setValueOnCheck(value,$dropDownTree,$inputDiv.treeObj,dpProps);
        }
        $inputDiv.val=function(value) {
            if(!value)return  $dropDownTree.val();
            setValueOnCheck(value,$dropDownTree,$inputDiv.treeObj,dpProps);
        }

        $inputDiv.load=function(param,callback){
            $.extend(domObject.dpProps,param);
            ($inputDiv.getTreeObj())&&$inputDiv.getTreeObj().destroy();
            createObject(domObject,function(treeObj){$inputDiv.treeObj=treeObj;callback&&callback($inputDiv);});
        };
        bindDomEvent(domObject,$inputDiv);
        dropDownTrees.push($inputDiv);
        $dropDownTree.data("dropDownTree",$inputDiv);
        //readonly 添加下拉树只读属性
        if($inputDiv.children().children(1).hasClass("readonly")){
            $inputDiv.children().children().addClass("readonly");
        }
        if($inputDiv.children().children(1).hasClass("disabled")){
            $inputDiv.children().children().addClass("disabled");
        }
        return domObject.$inputDiv;
    }

    // 下拉树定位
    $.fn.dropDownTreeResizePos=function(){
        var position=function($inputDiv){
            var $inputText= $inputDiv.find(".combo-text");
            var panelId=$inputDiv.data("panelId");
            var $treeDiv=$("#"+panelId);
            if ($treeDiv.is(":hidden"))return;
            var ObjBottom = $(window).height() - $inputText.offset().top - $inputText.height();
            $treeDiv.css({left:$inputText.offset().left});
            if(ObjBottom > $treeDiv.height()){
                $treeDiv.css({top:$inputText.offset().top+$inputText.outerHeight()-1});
            }else{
                $treeDiv.css({top:$inputText.offset().top - $treeDiv.height() + 1});
                $treeDiv.data("upOrDown","up");
            }
        }
        $.each(dropDownTrees, function() {
            var $inputDiv=this;
            var $inputText= $inputDiv.find(".combo-text");
            var panelId=$inputDiv.data("panelId");
            var $treeDiv=$("#"+panelId);
            if($treeDiv.is(":visible")) $treeDiv.hide();
        });
    }

    // 树节点线性化
    function transformToArrayFormat(nodes) {
        if (!nodes) return [];
        var childKey = "children",
            r = [];
        if ($.isArray(nodes)) {
            for (var i=0, l=nodes.length; i<l; i++) {
                r.push(nodes[i]);
                if (nodes[i][childKey])
                    r = r.concat(transformToArrayFormat(nodes[i][childKey]));
            }
        } else {
            r.push(nodes);
            if (nodes[childKey])
                r = r.concat(transformToArrayFormat(nodes[childKey]));
        }
        return r;
    }

    // 设置值，改变checkbox选中状态
    function setValueOnCheck(value,$dropDownTree,treeObj,dpProps){
        if(!treeObj){
            // 延迟加载时，塞值、清空处理
            if(!value){
                $dropDownTree.siblings(".combo-text").attr("value", "");
                $dropDownTree.siblings(".combo-text").attr("title", "");
                $dropDownTree.attr("value", "");
                $dropDownTree.attr("displayValue", "");
                return;
            }
            $.fn.getDataStore(dpProps,function(data){
                var childNodes= transformToArrayFormat(data);
                var valArray=value.split(","),txtArray=[],valueArray=[];
                $.each(childNodes,function(){
                    if($.inArray(this[dpProps.key_code],valArray)==-1) return
                    txtArray.push(this[dpProps.key_value]);
                    valueArray.push(this[dpProps.key_code]);
                });
                $dropDownTree.siblings(".combo-text").attr("value", txtArray.toString());
                $dropDownTree.siblings(".combo-text").attr("title", txtArray.toString());
                $dropDownTree.attr("value", valueArray.toString());
                $dropDownTree.attr("displayValue", txtArray.toString());
            });
            return;
        }
        var checkNodes=treeObj.getCheckedNodes();
        $.each(checkNodes,function(){
            treeObj.checkNode(this,false);
            //item.halfCheck=false;
        });

        if(!value){
            $dropDownTree.siblings(".combo-text").attr("value", "");
            $dropDownTree.siblings(".combo-text").attr("title", "");
            $dropDownTree.attr("value", "");
            $dropDownTree.attr("displayValue", "");
            return;
        }

        var valArray=value.split(","),txtArray=[];
        for(var i=0;i<valArray.length;i++){
            var node=treeObj.getNodeByParam(treeObj.key_code,valArray[i]);
            if(!node)continue;
            txtArray.push(node[treeObj.key_value]);
            treeObj.checkNode(node,true);
        }
        $dropDownTree.siblings(".combo-text").attr("value", txtArray.toString());
        $dropDownTree.siblings(".combo-text").attr("title", txtArray.toString());
        $dropDownTree.attr("value", value);
        $dropDownTree.attr("displayValue", txtArray.toString());
    }

    // 根据实际值，获取显示文本
    function getTextByValue(value,treeObj,dpProps){
        if(!treeObj){
            // 延迟加载时，塞值、清空处理
            if(!value) return "";
            $.fn.getDataStore(dpProps,function(data){
                var childNodes= transformToArrayFormat(data);
                $.each(childNodes,function(){
                    if(this[dpProps.key_code]!=value)return;
                    return this[dpProps.key_value];//增加显示值
                });
            });
            return;
        }
        if(!value) return "";
        var valArray=value.split(","),txtArray=[];
        for(var i=0;i<valArray.length;i++){
            var node=treeObj.getNodeByParam(treeObj.key_code,valArray[i]);
            if(!node)continue;
            txtArray.push(node[treeObj.key_value]);
        }
        return txtArray.toString();
    }

    // 根据显示文本，获取真实值
    function getValueByText(value,treeObj,dpProps){
        if(!treeObj){
            // 延迟加载时，塞值、清空处理
            if(!value) return "";
            $.fn.getDataStore(dpProps,function(data){
                var childNodes= transformToArrayFormat(data);
                $.each(childNodes,function(){
                    if(this[dpProps.key_value]!=value) return;
                    return this[dpProps.key_code];//增加显示值
                });
            });
            return "";
        }
        if(!value) return "";
        var valArray=value.split(","),txtArray=[];
        for(var i=0;i<valArray.length;i++){
            var node=treeObj.getNodeByParam(treeObj.key_value,valArray[i]);
            if(!node)continue;
            txtArray.push(node[treeObj.key_code]);
        }
        return txtArray.toString();
    }

    // 设置checkbox选中状态，改变值
    function checkOnSetValue($dropDownTree,treeObj){
        var nodes = treeObj.getNodesByParam("checked",true),
            val = "",txt="";
        for (var i=0, l=nodes.length; i<l; i++) {
            var halfCheck = nodes[i].getCheckStatus();
            var chkboxType=treeObj.setting.check.chkboxType;
            if(halfCheck){
                if (!halfCheck.half||!chkboxType.Y.match("s")){
                    txt += nodes[i][treeObj.key_value] + ",";
                    val+=nodes[i][treeObj.key_code] + ",";
                }
            }


        }
        if (val.length > 0 ) val = val.substring(0, val.length-1);
        if (txt.length > 0 ) txt = txt.substring(0, txt.length-1);
        $dropDownTree.siblings(".combo-text").attr("value", txt);
        $dropDownTree.siblings(".combo-text").attr("title", txt);
        $dropDownTree.attr("value", val);
        $dropDownTree.attr("displayValue", txt);
    }

    // 加入拼音字段
    function addPinField(data,attrName){
        var getPY=function(str){
            var doublePY=[{"hz":"厦门市","py":"xms"},{"hz":"重庆市","py":"cqs"}];
            var py=pinyin.getCamelChars(str);;
            $.each(doublePY,function(){
                var index= str.indexOf(this.hz);
                if(index<0)return;
                py=py.substr(0,index)+this.py+py.substr(index+this.py.length,py.length);
            });
            return py;
        }
        var addPin=function(data){
            data["py"]=getPY(data[attrName]);
            if(!data.children) return;
            if(data.children.length<=0) return;
            for(var i=0;i<data.children.length;i++){
                addPin(data.children[i]);
            }
        }
        for(var i=0;i<data.length;i++){
            addPin(data[i]) ;
        }
        return data;
    }

    // 调用 zTree 树创建对象
    function createObject(domObject,callback){
        var $inputDiv=domObject.$inputDiv;
        var $treeDiv=domObject.$treeDiv;
        var $zTree=$treeDiv.find("ul");
        var value=$inputDiv.find(".combo-text").prev().val();
        var dpProps=domObject.dpProps;
        var setting={
            view: {
                fontCss: setFontCss_ztree
            },
            callback: {
                onClick: function (event, treeId, treeNode) {
                    if($.fn.zTree.getZTreeObj(treeId).setting.check.enable)return;
                    if(dpProps.onSelect&&!dpProps.onSelect(treeNode))return;  //点击节点自定义操作
                    $inputDiv.find(".combo-text").prev().val(treeNode[dpProps.key_code]);
                    $inputDiv.find(".combo-text").attr("value",treeNode[dpProps.key_value]);
                    $inputDiv.find(".combo-text").attr("title",treeNode[dpProps.key_value]);
                    $inputDiv.attr("validate")&&$inputDiv.find(".combo-text").validationEngine("validate");
                    $treeDiv.hide();
                    dpProps.onClick&&dpProps.onClick(treeNode);//增加点击事件
                },

                onCheck: function(event, treeId, treeNode){
                    var treeObj = $.fn.zTree.getZTreeObj(treeId);
                    if(dpProps.onSelect&&!dpProps.onSelect(treeNode)){
                        treeObj.checkNode(treeNode,false);
                        return;
                    }  //点击节点自定义操作
                    checkOnSetValue( $inputDiv.find(".combo-text").prev(),treeObj);
                    $inputDiv.attr("validate")&&$inputDiv.find(".combo-text").validationEngine("validate");
                    dpProps.onClick&&dpProps.onClick(treeNode);//增加点选中事件
                }
            } ,
            data:{key:{name:dpProps.key_value}}
        };
        var  treeObj="";
        $.extend(dpProps, setting);
        $.fn.getDataStore(dpProps,function(data){
            data=addPinField(data,dpProps.key_value);
            treeObj= $.fn.zTree.init($zTree, dpProps, data);
            treeObj.key_code=dpProps.key_code;
            treeObj.key_value=dpProps.key_value;
            setTimeout(function(){
                var value=$inputDiv.find(".combo-text").prev().val();
                setValueOnCheck(value,$inputDiv.find(".combo-text").prev(),treeObj);
                callback&&callback(treeObj);
            },50);
        });
    }

    // 输入框及下拉树DOM 绑定事件
    function bindDomEvent(domObject,$inputDiv){
        var $inputDiv=domObject.$inputDiv;
        var $treeDiv=domObject.$treeDiv;
        var $tree= $treeDiv.find(".ztree");

        // 搜索事件 键盘 Enter 进行搜索
        $treeDiv.find(".search_condition").keypress(function(e) {
            if(/^13$/.test(e.keyCode)) { //是否为Enter键
                // 进行数据查找
                search_ztree($tree.attr("id"), $(this).attr("id"));
                e.stopPropagation();
                e.preventDefault();
            }
        });

        // 输入框点击 选中文本事件 附带键盘 backspace 清空
        $inputDiv.find(".combo-text").click(function(){
            this.select();
        }).keydown(function(e) {
            // 是否为backspace按钮
            if(/^8$/.test(e.keyCode)) {
                $inputDiv&&$inputDiv.setValue("");
                e.stopPropagation();
                e.preventDefault();
            }
        });

        // 清空按钮 清空事件
        $inputDiv.find(".clearIcon").click(function(e){
            // 存在禁用、只读属性时，清空事件禁用
            if($(this).hasClass("disabled")||$(this).hasClass("readonly")) return;
            $inputDiv&&$inputDiv.setValue("");
            e.stopPropagation();
            e.preventDefault();
        });

        // 全局点击 隐藏下拉树弹出层
        $(document).bind("click",function(e){
            var target  = $(e.target);
            // .closest()沿 DOM 树向上遍历，直到找到已应用选择器的一个匹配为止，返回包含零个或一个元素的 jQuery 对象。
            if(target.closest(".dropDownBox").length == 0 && target.closest(".combo-panel").length == 0) {
                $treeDiv.hide();
                $inputDiv.css("z-index","0");
            };
            e.stopPropagation();
        });

        // 三角图标 下拉事件
        $(".combo-arrow",$inputDiv).click(function(e){
            // 存在禁用、只读属性时，三角点击事件禁用
            if($(this).hasClass("disabled")||$(this).hasClass("readonly")) return;

            var treeObj=$treeDiv.data("treeObj");
            BindPanelShowHide($treeDiv,$inputDiv,e);
            if(!treeObj){
                $treeDiv.find("ul").append("<li>&nbsp;&nbsp;数据正在加载</li>");
                $treeDiv.data("treeObj",{});
                createObject(domObject,function(treeObj){
                    treeObj.pageNum=domObject.dpProps.pageNum?domObject.dpProps.pageNum:5;
                    //修改父节点 禁用属性 yh 2015.9.25
                    if(domObject.dpProps.unSelectParents){
                        $.each(treeObj.getNodes(),function(){
                            if(this.children) treeObj.setChkDisabled(this,true);
                        })
                    }
                    $treeDiv.data("treeObj",treeObj);
                    $inputDiv.treeObj= treeObj;
                    $inputDiv.val($inputDiv.find("input:hidden").val());//yangyj 2015-11-11 创建对象塞值

                    // 增加显示展开分列
                    if(domObject.dpProps.is_ztrees_checked_open) {
                        var treeId = $treeDiv.find(".ztree").attr('id');
                        open_ztree_list(treeId);
                    }
                });
                e.stopPropagation();
                return;
            }
            e.stopPropagation();
        });
    }

    // 创建相关dom元素
    function createDropDownDom($inputValue,dpProps){
        var nowTime=(new Date()).getTime();
        var txtId="text-"+nowTime +$(".combo-text").length;
        var ztreeId ="ztree-"+nowTime + $(".ztree").length;
        var searchId="search_condition-"+nowTime + $(".search_condition").length;
        // 输入框 DOM 
        var htmlText='<div class="dropDownBox"><span class="combo">' +
            $inputValue.prop('outerHTML')+
            '<input type="text" readonly="true" id="'+txtId+'" class="combo-text" >' +
            '<a href="javascript:;" class="clearIcon"></a> <span class="combo-arrow"></span>' +
            '</span></div>';
        var $inputDom=$(htmlText);
        $inputValue.replaceWith($inputDom);

        // IE 6.0  兼容性
        if($.browser.version=="6.0"){
            setTimeout(function(){
                $inputDom.css("width",$inputDom.find("#"+txtId).width()+$inputDom.find(".clearIcon").width()+$inputDom.find("span.combo-arrow").width()+8) ;
            },100);
        }

        // 下拉层 DOM
        var panelId="panel-"+ nowTime+$(".combo-panel").length;
        htmlText='<div class="combo-panel" id="'+panelId+'">';

        if(dpProps.is_search) htmlText += '<div class="search-box"><input type = "text" class="search_condition" id="' +searchId+ '" /><span class="summary-bar"></span></div>';

        htmlText += '<div class="d-p-m"><ul class="ztree" style="margin-top:2px;" id="' + ztreeId +'" ></ul></div></div>';
        var $treeDom= $(htmlText);
        $treeDom.appendTo("body");
        $inputDom.data("panelId",panelId);

        $inputDom.find(".combo-text").addClass($inputValue.attr("class")).data("treeId",ztreeId);
        $inputDom.find(".combo-text").attr("data-prompt-position",$inputValue.attr("data-prompt-position"));
        return {'$inputDiv':$inputDom,'$treeDiv':$treeDom};
    }

    // 控制下拉层的显示隐藏
    function BindPanelShowHide($treeDiv,$inputDiv,event) {
        //改变下拉树弹出层定位方式
        var $inputText=$(event.target).siblings(".combo-text");
        if ($treeDiv.is(":hidden")) {
            $(".dropDownBox").css("z-index","0");
            $(".dropDownBox").find(".combo-panel").hide();
            //修改弹出层优先级，低于对话框
            $treeDiv.css("z-index",99900);
            $treeDiv.css({"width": $inputDiv.find(".combo").width() - 2 + "px"});
            $treeDiv.find(".d-p-m").css("height","200px");
            var ObjBottom = $(window).height() - $inputText.offset().top - $inputText.height();
            $treeDiv.css({left: $inputText.offset().left});
            if(ObjBottom > $treeDiv.height()){
                $treeDiv.css({top:$inputText.offset().top+$inputDiv.height() - 1});
            }else{
                $treeDiv.css({top:$inputText.offset().top -  $treeDiv.height() + 1});
                $treeDiv.data("upOrDown","up");
            }
            $treeDiv.show();
        }else {
            $treeDiv.hide();
            $treeDiv.css("z-index","0");
        }
    }

    /**
     * 收起树：只展开根节点下的一级节点
     * @param treeId
     */
    function close_ztree(treeId){
        var treeObj = $.fn.zTree.getZTreeObj(treeId);
        var nodes = treeObj.transformToArray(treeObj.getNodes());
        var nodeLength = nodes.length;
        for (var i = 0; i < nodeLength; i++) {
            if (nodes[i][treeObj.key_code] == '0') {
                //根节点：展开
                (!nodes[i].open)&&treeObj.expandNode(nodes[i], true, true, false);
            } else {
                //非根节点：收起
                nodes[i].open&&treeObj.expandNode(nodes[i], false, true, false);
            }
        }
    }

    /**
     * 当存在选择值时下拉，列表展开
     */
    function open_ztree_list(treeId) {
        var treeObj = $.fn.zTree.getZTreeObj(treeId);
        var nodes = treeObj.transformToArray(treeObj.getNodes());
        var nodeLength = nodes.length;
        for (var i = 0; i < nodeLength; i++) {
            if (nodes[i]['check_Child_State'] == '1') {
                //根节点：展开
                treeObj.expandNode(nodes[i], true, true, false);
            } else {
                //非根节点：收起
                treeObj.expandNode(nodes[i], false, true, false);
            }
        }
    }

    /**
     * 搜索树，高亮显示并展示(模糊匹配搜索条件的节点)
     * @param treeId
     * @param searchConditionId 文本框的id
     */
    function search_ztree(treeId, searchConditionId){
        console.log(treeId);
        var treeObj = $.fn.zTree.getZTreeObj(treeId);
        console.log(treeObj);
        var pageSize=treeObj.pageNum;
        if($.browser.msie&&($.browser.version=="6.0")){
            pageSize=1;
        }
        //<1>.搜索条件
        var searchCondition = $.trim($('#' + searchConditionId).val());
        //<2>.得到模糊匹配搜索条件的节点数组集合
        var highlightNodes =[];

        if (searchCondition != "") {
            highlightNodes = treeObj.getNodesByParamFuzzy("value", searchCondition, null);
            var pyNodes=treeObj.getNodesByParamFuzzy("py", searchCondition, null);
            highlightNodes= $.merge(highlightNodes,pyNodes);
        }else{
            showAllNodes(treeObj);
        }

        var pageObj=getPageObject(treeObj,searchConditionId,highlightNodes.length,pageSize);
        highlightNodes=highlightNodes.slice((pageObj.index-1)*pageSize,pageObj.index*pageSize);
        var searchSpan=$('#' + searchConditionId).next();
        searchSpan.html(pageObj.html);
        $('#' + searchConditionId).attr("title","输入查询条件，回车翻页！");
        //<3>.高亮显示并展示【指定节点s】
        if($.browser.msie&& $.browser.version=="6.0"){
            (!searchCondition)&&removeHighlight(treeObj);
            (!searchCondition)&&close_ztree(treeId);
            searchCondition&&showTreePaths(treeObj,highlightNodes);
            return;
        }
        removeHighlight(treeObj);
        close_ztree(treeId);
        searchCondition&&showTreePaths(treeObj,highlightNodes);
    }

    // 显示所有节点
    function showAllNodes(treeObj){
        var nodes = treeObj.getNodesByParam("isHidden", true);
        if($.browser.msie&& $.browser.version=="6.0"){
            var pageSize=10;
            for(var i=0;i<nodes.length/pageSize+1;i++){
                var tempNodes=nodes.slice(i*pageSize,(i+1)*pageSize);
                var fun=(function(tempNodes){
                    return function(){treeObj.showNodes(tempNodes); }
                })(tempNodes);
                setTimeout(fun,i*30);
            }
            return;
        }
        treeObj.showNodes(nodes);
    }

    // 删除高亮显示
    function removeHighlight(treeObj){
        var treeNodes = treeObj.transformToArray(treeObj.getNodes());
        for (var i = 0; i < treeNodes.length; i++) {
            if(!treeNodes[i].highlight) continue;
            treeNodes[i].highlight = false;
            treeObj.updateNode(treeNodes[i]);
        }
    }

    // 获取分页对象
    function getPageObject(treeObj,searchConditionId,recordCount,pageSize){
        var searchCondition = $.trim($('#' + searchConditionId).val());
        /**  空的时候返回-1  **/
        if(searchCondition == ""){
            treeObj.searchObj={"key":searchCondition,index:1};
            return {index:-1,pageTotal:-1,html:"全部"};
        }
        var pageText="页,每页:"+pageSize+",总记录:"+recordCount;
        if(recordCount<=0)
            return {index:-1,pageTotal:-1,html:"0/0"};
        var pageTotal= parseInt(recordCount/pageSize);
        if(recordCount%pageSize!=0) pageTotal+=1;
        if(!treeObj.searchObj||treeObj.searchObj.key!=searchCondition) {
            treeObj.searchObj={"key":searchCondition,index:1};
            return {index:1,pageTotal:pageTotal,html:"1/"+pageTotal+pageText};
        }
        var index=treeObj.searchObj.index;
        index=index>=pageTotal?1:index+1;
        treeObj.searchObj.index=index;
        return {index:index,pageTotal:pageTotal,html:index+"/"+pageTotal+pageText};
    }

    // 显示对应节点的根路径并展开高亮 失效
    function showTreePaths(treeObj,nodeList){
        // 获取父节点路径直到根节点
        var treeStructure={};
        var key_code=treeObj.key_code;
        var createTreeStructure=function (node){
            if(node == null || node.getParentNode() == null)return;
            var parentNode = node.getParentNode();
            treeStructure[node[key_code]].pid= parentNode[key_code];
            if(!treeStructure[parentNode[key_code]]){
                treeStructure[parentNode[key_code]]={node:parentNode,pid:"",cid:{}};
            }
            treeStructure[parentNode[key_code]].cid[node[key_code]]=true;
            arguments.callee( parentNode);
        }
        $.each(nodeList,function(){
            if(!treeStructure[this[key_code]]) treeStructure[this[key_code]]={node:this,pid:"",cid:{}};
            this.highlight=true;
            treeObj.updateNode(this);
            createTreeStructure(this);
        });

        var showAndExpandNodes= function(nodeStructure){
            if($.isEmptyObject(nodeStructure.cid)) return;
            var levelNodes=nodeStructure.node.children;
            treeObj.hideNodes(levelNodes);
            for(var cid in nodeStructure.cid){
                if($.browser.msie&&($.browser.version=="6.0")){
                    setTimeout(function(){
                        treeObj.showNode(treeStructure[cid].node);
                        treeObj.expandNode(treeStructure[cid].node,true,false,false);
                    },20);
                }else{
                    treeObj.showNode(treeStructure[cid].node);
                    treeObj.expandNode(treeStructure[cid].node,true,false,false);
                }
                arguments.callee(treeStructure[cid]);
            }
        }
        treeObj.hideNodes(treeObj.getNodes());
        (function(){
            for(var code in treeStructure){
                var nodeStructure=treeStructure[code];
                if(nodeStructure.pid)continue;
                var currentNode=nodeStructure.node;
                treeObj.showNode(currentNode);
                treeObj.expandNode(currentNode,true,false,false);
                showAndExpandNodes(nodeStructure);
            }
        })();
    }
    /**
     * 设置树节点字体样式
     */
    function setFontCss_ztree(treeId, treeNode) {
        var treeObj = $.fn.zTree.getZTreeObj(treeId);
        if (treeNode[treeObj.key_code] == 0) {
            //根节点
            return {color:"#333", "font-weight":"bold"};
        } else if (treeNode.isParent == false){
            //叶子节点
            return (!!treeNode.highlight) ? {color:"#ff0000", "font-weight":"bold"} : {color:"#333", "font-weight":"normal"};
        } else {
            //父节点
            return (!!treeNode.highlight) ? {color:"#ff0000", "font-weight":"bold"} : {color:"#333", "font-weight":"normal"};
        }
    }



});
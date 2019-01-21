/**
 * Created by yan on 2015/12/6 0006.
 */

(function($,window){

    /*表格默认设置*/
    var defaultSetting={
        contextPath:"",// todo 增加上下文路径
        widthTh:{},//存储设置自定义宽度字段
        flitDataTh:{},//存储需要过滤数据格式字段。日期：data，下拉框/下拉树：code,enum,sql,url
        colModels:[], //存储列 控件类型，属性...
        editRowObj:{},  //存储编辑行控件 dom 对象
        editRowId:'',//当前编辑行id
        editState:false,//表格是否处于编辑状态，默认false
        rowDraggable:false,//是否允许行拖拽，默认false
        rowHeight:36, //默认行高
        currentPage:1,//当前页数，默认第一页
        pageSize:10,//每页显示数量，默认10
        pageSizeOption:[10,20,30,40,50], //下拉框每页显示数量设置
        templateUrl:'js/libs/editGrid/editGrid.html',// 表格结构模板 todo 修改模板路径

        /*
         todo 增加一个表格对外提供添加数据方法
         新增前事件,暂时没有返回参数。需调用组件方法生成唯一id，并将数据存入id对应数据对象。
         return false 不执行表格默认新增操作；true 继续执行表格新增操作
         * */
        beforeAdd:null,
        beforeEdit:null,//编辑前事件，返回当前行数据对象。return false 不执行表格编辑操作；true 继续表格编辑操作
        beforeSave:null, //保存前事件，返回当前行数据对象。return false 不执行表格保存操作；true 继续表格保存操作
        beforeDelete:null,//删除前事件，返回当前行数据对象。return false 不执行表格删除操作；true 继续表格删除操作
        beforeCancel:null //撤销前事件，返回当前行数据对象。return false 不执行表格撤销操作；true 继续表格撤销操作
    }

    /*工具类*/
    var util={
        /*生成唯一id*/
        uuid:function(len, radix) {
            var chars = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz'.split('');
            var uuid = [], i;
            radix = radix || chars.length;

            if (len) {
                // Compact form
                for (i = 0; i < len; i++) uuid[i] = chars[0 | Math.random()*radix];
            } else {
                // rfc4122, version 4 form
                var r;

                // rfc4122 requires these characters
                uuid[8] = uuid[13] = uuid[18] = uuid[23] = '-';
                uuid[14] = '4';

                // Fill in random data.  At i==19 set the high bits of clock sequence as
                // per rfc4122, sec. 4.1.5
                for (i = 0; i < 36; i++) {
                    if (!uuid[i]) {
                        r = 0 | Math.random()*16;
                        uuid[i] = chars[(i == 19) ? (r & 0x3) | 0x8 : r];
                    }
                }
            }
            return uuid.join('');
        },
        /*将options属性转成对象*/
        optionsObj:function(options){
            var _options=options.split(","),
                optionsObj={}; //用"," 拆分option选项
            //var options=options.pop(options.length-1);
            $.each(_options,function(index,item){
                var value=item.split(":")[0];//用":"拆分item，取得真实值/显示值(value,html);
                var html=item.split(":")[1];
                optionsObj[value]=html;
            });
            return optionsObj;
        },
        /* todo 加入编辑行表单验证*/
        validateForm:function(grid){
            if(!grid.attr("validate")){
                grid.validationEngine();
                grid.attr("validate",true);
            }
            if (!grid.validationEngine("validate")) {
                //grid.formValidatePosDp();
                var tips=$.Poshytip.prototype.resizePos();
                var $this=$(grid);
                $.each(tips,function(){
                    if(this.$tip.data("active")){
                        if(this.pos.t>$this.offset().top+$this.height()||this.pos.t<$this.offset().top) {
                            this.$tip.hide();
                        }else{
                            this.$tip.show();
                        }
                    };
                });
                return false;
            }
            return true;
        }
    }

    /*表头对象*/
    function headerThObj(config,postData){
        this.config=config;
        //this.colModels=config.colModels;
        this.postData=postData;
    }
    headerThObj.prototype={
        /*初始化表头*/
        initTh:function(th){
            var param={},dpProps={},rowData={};
            param=eval("({"+th.attr("param")+"})");
            rowData.name=param.name;
            param.displayName=rowData.displyName=th.html(); //原数据显示名称
            /* 封装组件特殊处理，下拉框、下拉树、日期 */
            if(th.data("props")){
                dpProps=th.data("props");
                param.dpProps=dpProps;
                this.initPostData(param.type,dpProps,rowData);
            }
            if(th.attr("width")){
                this.config.widthTh[param.name]=th.attr("width");
            }
            /*将原生下拉，单选，复选纳入数据过滤对象，后面用自定义的options进行数据处理*/
            switch (param.type){
                case "checkbox":
                    this.config.flitDataTh[param.name]={
                        type:"checkbox",
                        option:util.optionsObj(param.options)
                    };break;
                case "radio":
                    this.config.flitDataTh[param.name]={
                        type:"radio",
                        option:util.optionsObj(param.options)
                    };break;
                case "select":
                    this.config.flitDataTh[param.name]={
                        type:"select",
                        option:util.optionsObj(param.options)
                    };break;
            }
            //this.initPostData(param.type,dpProps,rowData);
            this.config.colModels.push(param);
            this.postData._metaData.push(rowData);
        },
        /*初始化提交参数*/
        initPostData:function(type,dpProps,rowData){
            //var rowData={};
            var gridConfig=this.config
            dpProps=eval("({"+dpProps+"})");
            switch (type){
                case "date":
                    rowData.displayType="DATE";
                    rowData.dateFormat=dpProps.dateFmt;
                    gridConfig.flitDataTh[rowData.name]={type:"DATE"};
                    //result.metadata[param.name]={type:"DATE"}
                    break;
                case "dropDownTree":
                case "dropDownRadio":
                    gridConfig.flitDataTh[rowData.name]={type:"CODE"};
                    if(dpProps.enumName){
                        rowData.displayType="ENUM";
                        rowData.codeId=dpProps.enumName;
                        //result.metadata[param.name]={type:"CODE"}
                    }else if(dpProps.sql){
                        rowData.displayType="SQL";
                        rowData.codeId=dpProps.sql;
                        //result.metadata[param.name]={type:"CODE"}
                    }else if(dpProps.url){
                        rowData.displayType="URL";
                        rowData.codeId=dpProps.code;
                        gridConfig.flitDataTh[rowData.name]["isUrl"]=true;
                        //result.metadata[param.name]={type:"CODE",isUrl:true}
                    }
                    else{
                        rowData.displayType="CODE";
                        rowData.codeId=dpProps.code;
                        //result.metadata[param.name]={type:"CODE"}
                    }
                    //result.metadata[param.name]={type:"CODE"}
                    break;
            }
        }
    }

    /*分页栏对象*/
    function pageBarObj(gridObj){
        this.gridObj=gridObj;
        this.config=gridObj.config;
        this.dataObj=gridObj.dataObj;
        this.pageBar=$(".e-panel-bar-inner",this.gridObj.grid);
        this.pageBar.css("width",this.pageBar.parent().width());
        this.initPageBar(this.config.currentPage,this.config.pageSize);
    }
    pageBarObj.prototype={
        /*初始化分页栏*/
        initPageBar:function(currentPage,pageSize){
            this._initPageSelect();
            this._initPageGroup();
            this._setPageMessage(currentPage,pageSize);
        },
        /*构建分页栏 页数选择select下拉框部分*/
        _initPageSelect:function(){
            var selectPage=$(".e-bar-selectOption",this.pageBar),
                select=$("<select class='e-bar-select'></select>");
            $.each(this.config.pageSizeOption,function(index,item){
                var option=$("<option></option>");
                option.html(item);
                select.append(option);
            });
            selectPage.append(select);
        },
        /* 构建分页栏翻页部分*/
        _initPageGroup:function(currentPage,pageSize){
            var pageBar=$(".pagination",this.pageBar),
                total=this.dataObj.gridDataArray4Obj.length;
            pageBar.attr({currentPage:currentPage,numPerPage:pageSize,totalCount:total});
            /* todo 引入dwz分页工具 */
            pageBar.pagination();
            //pageBar.parent().initUI();
        },
        /*构建分页栏信息提示部分*/
        _setPageMessage:function(currentPage,pageSize){
            var textMessage=$(".e-bar-text",this.pageBar),
                message="显示从 {{start}} 到 {{end}} ，总{{total}}条。每页显示:{{numPerPage}}";
            var start=(currentPage==1)?1:(currentPage-1)*pageSize,
                end=start+pageSize-1,
                total=this.dataObj.gridDataArray4Obj.length;
            (total<end)&&(end=total);
            (total==0)&&(start==total);
            message="显示从 "+start+" 到 "+end+" ，总"+total+"条。每页显示:"+pageSize;
            //message=tpl.compile(message)({start:start,end:end,total:total,numPerPage:pageSize});
            textMessage.html();
            textMessage.html(message);
        },
        /* 分页跳转 */
        setPage:function(currentPage,pageSize){
            /* 设置当前页显示数据，触发显示数据事件 */
            var currentData=this.gridObj.dataObj.getCurrentData(currentPage,pageSize);
            this.gridObj.showObj.showData(currentData);
            var pageBar=$(".e-panel-bar",this.gridObj.grid);
            pageBar.trigger("update");
        }
    }

    /*显示对象*/
    function showObj(gridObj){
        this.gridObj=gridObj;
        this.grid=gridObj.grid;
        this.config=gridObj.config;
        this.dataObj=gridObj.dataObj;
        this.initGrid();
    }
    showObj.prototype={
        initGrid:function(){
            this.initGridHeader();
            this.initHiddenRow();
        },

        /*构建表格表头*/
        initGridHeader:function(){
            var leftTable=$(".e-grid-header-table",this.grid).eq(0),
                rightTable=$(".e-grid-header-table",this.grid).eq(1);
            this._initLeftTableHeader(leftTable);
            this._initRightTableHeader(rightTable);
            this.gridObj.initEvent();
        },
        /*构建隐藏行*/
        initHiddenRow:function(){
            var gridObj=this.gridObj,
                showObj=this,
                editRow=$("tfoot tr",this.grid),
                bodyHeaderTable=$(".e-grid-header-table",this.grid).eq(1);
            $.each(gridObj.config.colModels,function(index,item){
                var td=$("<td><div align='center' class='e-edit-row' ></div></td>"),
                    headerTd=$("[colName='"+item.name+"']",bodyHeaderTable);
                if(item.hide||item.frozen)return //隐藏字段、固定列不进行编辑控件创建
                var $input=showObj._initComponent(item,item.dpProps);

                switch (item.type){
                    case "date":{
                        if(item.dpProps){
                            var props=eval("({"+item.dpProps+"})"),
                                dateFmt=props.dateFmt||"yyyy-MM-dd";
                            $input.click(function(){
                                WdatePicker({dateFmt:dateFmt});
                            });
                            $("div",td).append($input);
                            break;
                        }
                        $("div",td).append($input);
                        break;
                    }
                    case "dropDownRadio":
                        var props=eval("({"+item.dpProps+"})");
                        props.name=item.name;
                        $input=$input.dropDownRadioDp(props);
                        $("div",td).append($input);
                        break;
                    case "dropDownTree":
                        var props=eval("({"+item.dpProps+"})");
                        props.name=item.name;
                        $input=$input.dropDownTreeDp(props);
                        $("div",td).append($input);
                        break;
                    default:{
                        /* 原生控件加入change事件监控，下拉树、下拉框自带onChange事件 */
                        item.onChange&&$input.change(function(e){
                            var _target=$(e.target).closest("tr"),
                                rowData= gridObj.dataObj.getRowData(_target);//获取当前行编辑后的值
                            item.onChange(rowData,gridObj.config.editRowObj);
                        });
                        $("div",td).append($input);
                        break;
                    }
                }

                td.css("width",headerTd.outerWidth());
                editRow.append(td);
                gridObj.config.editRowObj[item.name]=$input;
            });
            //初始化表格中控件
            //window._setActiveDp(true, gridObj.$p.data( window.duceap_activeKey));
            //editRow.initUI();
            editRow.hide();
            editRow.find("input[name],textArea[name],select[name]").attr("editFormFlag","true");// 编辑表格控件标识，不做提交
        },
        /*构建左侧表头*/
        _initLeftTableHeader:function(leftTable){
            var tr=leftTable.find("tbody tr"),
                td=$('<td class="e-grid-hd-cell">' +
                '<div class="e-grid-hd-cell-inner">' +
                '<span class="e-grid-hd-cell-text"></span>'+
                '</div>'+
                '</td>');
            td.attr("colName","e-order");
            $(".e-grid-hd-cell-text",td).html("序号");
            tr.append(td);
            /*
             *  todo 判断是否创建序号列，内容列中是否含有固定列
             * */
        },
        /*构建右侧表头*/
        _initRightTableHeader:function(rightTable){
            var tr=rightTable.find("tbody tr"),
                td=$('<td class="e-grid-hd-cell">' +
                '<div class="e-grid-hd-cell-inner">' +
                '<span class="e-grid-hd-cell-text"></span>'+
                '</div>'+
                '</td>'),
                colModels=this.config.colModels,//表格列字段
                _ignoreTds= 0,//忽略宽度补偿列个数
                _showObj=this;
            $.each(colModels,function(index,item){
                if(item.hide||item.frozen) return //存在隐藏或固定列属性，不进行创建
                var _td=td.clone();
                $(".e-grid-hd-cell-text",_td).html(item.displayName);
                _td.attr("colName",item.name);
                if(_showObj.config.widthTh[item.name]){
                    //设定用户设置宽度,同时不纳入待补全宽度列
                    _ignoreTds++;
                    _td.css("width",_showObj.config.widthTh[item.name]);
                }
                tr.append(_td);
            });
            /*添加默认操作列*/
            var operateTd=$('<td name="operateCol" class="e-grid-hd-operate-cell">' +
                '<div class="e-grid-hd-cell-inner">' +
                '<span class="e-grid-hd-cell-text"></span>'+
                '</div>'+
                '</td>'),
                addBtn=$("<a href='#' class='icon icon-add' id='add' title='增加'/></a>")
            $(".e-grid-hd-cell-text",operateTd).append(addBtn);
            tr.append(operateTd);
            var tableWidth=rightTable.width(),
                headerContainerWidth= $(".e-panel-wrap",_showObj.config.grid).width()-$(".e-grid-left",_showObj.config.grid).width();
            if(tableWidth>=headerContainerWidth)return
            /* 宽度自动撑开处理处理 */
            var tdNum=rightTable.find("td.e-grid-hd-cell").length,
                addWidth=(headerContainerWidth-tableWidth)/(tdNum-_ignoreTds);
            addWidth=parseInt(addWidth);
            $.each(rightTable.find("td.e-grid-hd-cell"),function(index,item){
                //if($(item).attr("name")=="operateCol") return
                var colName=$(item).attr("colName");
                if(_showObj.config.widthTh[colName]) return
                $(item).width($(item).width()+addWidth);
            })
        },
        /*表格显示数据*/
        showData:function(currentPageData){
            //todo 打开遮罩
            var _showObj=this,
                leftTable=$(".e-grid-body-table",this.grid).eq(0),
                rightTable=$(".e-grid-body-table",this.grid).eq(1);
            //清除旧表格数据
            leftTable.find("tbody tr").remove();
            rightTable.find("tbody tr").remove();
            $.each(currentPageData,function(index,item){
                var leftTr=$('<tr id='+item+'></tr>'),
                    rightTr=leftTr.clone();
                _showObj.createLeftTableTr(leftTr,index+1,item);
                leftTable.append(leftTr);
                _showObj.createRightTableTr(rightTr,item);
                //隔行变色
                if(!_showObj.config.rowDraggable&&index%2!=0){
                    leftTr.addClass("e-row-bg");
                    rightTr.addClass("e-row-bg");
                }
                rightTable.append(rightTr);
            });
            (this.config.rowDraggable)&&this.grid.trigger("draggable");
            //todo 关闭遮罩
        },
        /*创建左侧表体行*/
        createLeftTableTr:function(tr,index,item){
            var _showObj=this,
            //tr=$('<tr></tr>'),
                td=$('<td class="e-grid-row-cell">' +
                '<div class="e-grid-row-cell-inner"></div>'+
                '</td>');
            /*设置序号列*/
            var orderTd=td.clone();
            $(".e-grid-row-cell-inner",orderTd).html(index);
            var headerTable=$(".e-grid-header-table",_showObj.grid);
            orderTd.width($("[colName='e-order']",headerTable).outerWidth());
            tr.append(orderTd);
            //return tr
            /* todo 判断colModels中是否存在固定列，存在则加入到左侧固定列表格*/
            //table.find("tbody").append(tr);
        },
        /*创建右侧表体行*/
        createRightTableTr:function(tr,rowId,table){
            var _showObj=this,
            //tr=$('<tr id='+rowId+'></tr>'),
                td=$('<td class="e-grid-row-cell">' +
                '<div class="e-grid-row-cell-inner" align="center"></div>'+
                '</td>'),
                rowData=this.dataObj.gridDataObj[rowId];
            //tr.data("dataId",item);
            tr.data("rowData",rowData);
            $.each(_showObj.config.colModels,function(index,model){
                if(model.hide||model.frozen)return //隐藏列，固定列不进行创建
                var _td=td.clone();
                var colWidth=$("[colName='"+ model.name +"']",_showObj.grid).outerWidth();
                //_td.width(colWidth);
                var text=_showObj._getCellHtml(rowData,model.name);
                $(".e-grid-row-cell-inner",_td).attr("title",text).html(text).width(colWidth);
                tr.append(_td);
            });
            tr=this._addOperateBtn(tr,["edit","delete"]);
            //return tr
        },
        /*添加操作按钮*/
        _addOperateBtn:function(tr,btnName){
            var td=$('<td name="operateCell" class="e-grid-row-operate-cell">' +
            '<div class="e-grid-row-cell-inner"></div>'+
            '</td>');
            var colWidth=$(".e-grid-hd-operate-cell",this.grid).outerWidth();
            td.width(colWidth);
            $.each(btnName,function(index,item){
                var btn;
                switch (item){
                    case "edit":
                        btn=$('<a href="#" class="icon icon-edit" id="edit" title="编辑"/></a>');break;
                    case "save":
                        btn=$('<a href="#" class="icon icon-save1" id="save" title="确定"/></a>');break;
                    case "cancel":
                        btn=$('<a href="#" class="icon icon-zhongzhi" id="cancel" title="重置"/></a>');break;
                    case "delete":
                        btn=$('<a href="#" class="icon icon-qingkong" id="delete" title="删除"/></a>');break;
                    default :break;
                }
                $(".e-grid-row-cell-inner",td).append(btn);
            })
            tr.append(td);
            return tr;
        },
        /*生成编辑控件*/
        _initComponent:function(param,dpProps){

            var $input,
                bodyHeaderTable=$(".e-grid-header-table",this.grid).eq(1),
                headerTd=$("[colName='"+param.name+"']",bodyHeaderTable);
            switch (param.type){
                case "text":
                    $input=$("<input type='text' class='input-text' />");
                    param.maxLength&&$input.attr("maxLength",param.maxLength);
                    break;
                case "select" :
                    $input=$("<select class='select'></select>");
                    var optionsObj=util.optionsObj(param.options);
                    for(var i in optionsObj){
                        $input.append("<option value='"+i+"'>"+optionsObj[i]+"</option>");
                    }
                    break;
                case "textarea": $input=$("<textarea class='textarea' style='height:25px;' />"); break;
                case "checkbox":
                    $input=$("<input type='checkbox' />");
                    var checkValue=this.config.flitDataTh[param.name].option.check;
                    $input.attr("value",checkValue); //设置默认选中值
                    break;
                case "radio":
                    $input=$("<input type='radio' />");
                    var checkValue=this.config.flitDataTh[param.name].option.check;
                    $input.attr("value",checkValue);//设置默认选中值
                    break;
                case "date":
                    $input=$('<input type="text" class="input-text Wdate" />');break;
                case "readonly":
                    $input=$("<input type='text' readonly='readonly' class='input-text' />");break;
                default : $input=$("<input type='text' type='"+param.type+"' />");break;
            }
            $input.attr("name",param.name).css("width",$(".e-grid-hd-cell-inner",headerTd).width());
            param.class&&$input.addClass(param.class); //添加样式，一般用于隐藏行表单验证
            return $input;
        },
        /* 恢复隐藏行 */
        _recoverHiddenRow:function(){
            if(!this.gridObj.config.editState)return
            var showObj=this,
                rightGrid=$(".e-grid-right",this.grid),
                hiddenRow=$("tfoot tr",rightGrid);
            var editRow=(this.config.editRowId.length>0)?$("#"+this.config.editRowId,rightGrid):null;
            if(!editRow)return
            //存在，先将其它编辑行重置为文本状态
            $.each($("td",editRow),function(index,item){
                if($(item).hasClass("e-grid-row-operate-cell")){
                    $(item).remove();
                    return
                }
                hiddenRow.append($(item));
            });
            $.fn.resetFormObject(hiddenRow);
            //editRow.empty();
            showObj.createRightTableTr(editRow,this.config.editRowId);
            this.gridObj.config.editState=false;

        },
        /* 获得单元格显示文本
         * radio checkbox需要以控件形式展示，select需要根据值进行前端翻译
         * */
        _getCellHtml:function(rowData,name){
            var text=rowData[name],
                flitTh=this.config.flitDataTh[name];
            if(flitTh){
                switch (flitTh.type){
                    case "radio":{
                        text=$("<input type='radio' name='"+name+"' disabled='disabled' />");
                        (flitTh.option.check==rowData[name])&&text.attr("checked","checked");
                        break;
                    }
                    case "checkbox":{
                        text=$("<input type='checkbox' name='"+name+"' disabled='disabled' />");
                        (flitTh.option.check==rowData[name])&&text.attr("checked","checked");
                        break;
                    }
                    case "select":{
                        text=flitTh.option[text];break;
                    }
                    default :break;
                }
            }
            return text;
        },
        /* 设置行拖拽 */
        setRowDrag:function(){
            var showObj=this,
                config=this.config;
            $(".validationEngineContainer",this.grid).dragsort({
                dragSelector: "tr",
                dragBetween: false,
                placeHolderTemplate: "<tr></tr>",
                scrollContainer:".validationEngineContainer",
                dragEnd: function() {
                    /* 拖动完成，更新当前数据在数据数组gridDataArray4Obj中索引 */
                    var oldIndex=showObj.dataObj.gridDataArray4Obj.indexOf(this.attr("id")),
                        currentIndex=this.index(),//当前行拖动完成序列索引
                        newIndex=0;
                    newIndex=(config.currentPage==1)?currentIndex:((config.currentPage-1)*config.pageSize+currentIndex);
                    if(oldIndex==newIndex) return
                    /* 先删除旧索引处元素，再往新索引处插入该元素 */
                    else if(oldIndex>newIndex){
                        showObj.dataObj.gridDataArray4Obj.splice(oldIndex,1);
                        showObj.dataObj.gridDataArray4Obj.splice(newIndex+1,0,this.attr("id"));
                    }
                    /* 先往新索引处插入该元素，再删除旧索引处元素 */
                    else{
                        showObj.dataObj.gridDataArray4Obj.splice(newIndex+1,0,this.attr("id"));
                        showObj.dataObj.gridDataArray4Obj.splice(oldIndex,1);
                    }
                }
            });
        }
    }

    /*数据对象*/
    function dataObj(config){
        this.config=config;
        this.gridDataObj={};//表格数据对象
        this.gridDataArray4Obj=[];//表格数据对象映射数组
    }
    dataObj.prototype={
        /*对后台传过来的数据进行过滤*/
        gridDataFilter:function(data){
            var dataObj=this;
            $.each(data,function(index,item){
                var temId="e-temId-"+util.uuid(6,16); //生成临时数据id对应唯一对象
                dataObj.gridDataArray4Obj.push(temId);
                dataObj.gridDataObj[temId]=item;
            });
            //return data;
        },
        /*提交前对表格数据转译*/
        transformData4Submit:function(){
            var gridDataObj=this.gridDataObj,
                flitDataTh=this.config.flitDataTh,
                transformEdData=[];
            /*源数据可能涉及位置移动，只能根据对象数组进行数据处理。*/
            $.each(this.gridDataArray4Obj,function(index,item){
                var dataItem= $.extend({},gridDataObj[item]);
                for(var i in dataItem){
                    if(flitDataTh[i]){
                        switch (flitDataTh[i]["type"]) {
                            /*对过滤字段中的 日期，下拉树、下拉框数据特殊处理 */
                            case "DATE":{
                                /*日期去掉分隔符，转化为纯数字字符串*/
                                dataItem[i]=dataItem[i].replace(/[^0-9]/ig,"");
                                break;
                            }
                            case "CODE":{
                                /*下拉框、下拉树 字段名存放显示值，字段名_CODE存放真实值，
                                 * 需将字段名值设为真是值
                                 * */
                                dataItem[i]=dataItem[i+"_CODE"];
                                delete dataItem[i+"_CODE"];
                                /*todo url类型由于没有_code值，需要特殊处理*/
                                break;
                            }
                            /*过滤字段中radio,checkbox,select 无需处理*/
                            default :break;
                        }
                    }
                }
                transformEdData.push(dataItem);
            })
            return transformEdData;
        },
        /* 根据当前页数，每页显示数量返回当前待显示数据 */
        getCurrentData:function(currentPage,pageSize){
            var currentData=[],
                currentDataArray4Obj=[],
                start=(currentPage-1)*pageSize,
                end=start+pageSize,
                dataObj=this;
            (this.gridDataArray4Obj.length<end)&&(end=this.gridDataArray4Obj.length);
            currentDataArray4Obj= this.gridDataArray4Obj.slice(start,end);
            /*$.each(currentDataArray4Obj,function(index,item){
             currentData.push(dataObj.gridDataObj[item]);
             })*/
            return currentDataArray4Obj;
        },

        /*转换表码、日期显示值和真实值，用于编辑时控件塞值*/
        translateData4Edit:function(item){
            var tmp="",
                flitDataTh=this.config.flitDataTh;
            for(var i in item){
                if(flitDataTh[i]){
                    var type=flitDataTh[i].type;
                    switch (type){
                        case "CODE" :{
                            /*转译前，*/
                            var isUrl=flitDataTh[i].isUrl;
                            if(isUrl&&(!item[i+"_CODE"])){
                                /* 如果url没有返回_CODE，显示值即真实值。*/
                                item[i+"_CODE"]=item[i];
                                break;
                            }else{
                                tmp=item[i];
                                item[i]=item[i+"_CODE"];
                                item[i+"_CODE"]=tmp;
                                break;
                            }
                        }
                        case "DATE" :{
                            item[i+"_TIME"]=item[i]?item[i].replace(/[^0-9]/ig,""):"";
                        }
                    }
                }
            }
            return item;
        },
        /*获取编辑行的数据，用于数据更新*/
        getRowData:function(tr){
            var config=this.config,
                rowData={},
                elements=tr.find("input[name],textArea[name],select[name]");
            $.each(elements,function(index,item){
                //var tag = item.tagName.toLowerCase();
                var type=item.type;//获取控件类型
                if($(item).attr("dpType")) type=$(item).attr("dpType"); //获取封装后的下拉框，下拉树类型
                if($(item).hasClass("Wdate")) type="date"; //特殊日期控件类型
                switch (type){
                    case "checkbox":{
                        rowData[this.name]=$(this).is(":checked")?config.flitDataTh[this.name].option["check"]:config.flitDataTh[this.name].option["uncheck"];
                        break;
                    }
                    case "radio":{
                        rowData[this.name]=$(this).is(":checked")?config.flitDataTh[this.name].option["check"]:config.flitDataTh[this.name].option["uncheck"];
                        break;
                    }
                    case "dropDownRadio":
                    case "dropDownTree":
                    {
                        rowData[this.name]= $(this).attr("displayValue")||"";
                        rowData[this.name+"_CODE"]=this.value;
                        break;
                    }
                    /*case "date":{
                     rowData[this.name]=this.value.replace(/[^0-9]/ig,"");break;
                     }*/
                    default :{
                        rowData[this.name]=this.value;break;//默认文本、date、select、textarea处理
                    }
                }
            });
            return rowData;
        },
        /* 更新数据中的radio字段
         * 每个单选框选择后，需要将数据中其它radio字段值设为false
          * */
        updateRadioData:function(rowdata){
            var radioThs=[],
                gridDataObj=this.gridDataObj;
            for(var i in this.config.flitDataTh){
                if(this.config.flitDataTh[i]["type"]=="radio"){
                    radioThs.push({
                        name:i,
                        check:this.config.flitDataTh[i]["option"]["check"],
                        uncheck:this.config.flitDataTh[i]["option"]["uncheck"]
                    });
                }
            }
            if(radioThs.length==0)return;
            $.each(radioThs,function(index,item){
                /* 如果当前行数据radio为选中状态，则将其它radio置为未选中 */
                if(rowdata[item.name]==item.check){
                    for(var i in gridDataObj){
                        gridDataObj[i][item.name]=item.uncheck;
                    }
                }
            });
        }
    }

    /*编辑表格主对象*/
    function editGrid($grid,$p){
        this.grid=$grid; //表格dom节点
        this.$p=$p||{};//编辑表格所在父页面
        /*编辑表格默认配置*/
        this.config= $.extend(true,{},defaultSetting);
        /*表格提交数据*/
        this.postData={
            _metaData:[], //源数据
            _condition:[], //查询条件
            _sort:{} //排序字段
        };
        /* 自定义事件存储对象 */
        this.handlers={};

        this.dataObj= new dataObj(this.config);//表格数据对象
        this.headerThObj=new headerThObj(this.config,this.postData);//表头字段对象
    }
    editGrid.prototype={

        /*控件初始化方法*/
        render:function(callback){
            var container=this.grid;
            this.initTableProps(container);
            this.initThSetting(container);
            this.initGrid(container,callback);
            return this;
        },
        /*初始化表格属性*/
        initTableProps:function(grid){
            var props = grid.data("props")||"";
                props=eval("({"+props+"})");
            this.config= $.extend({},this.config,props);
            /* 添加表格自定义事件 */
            (this.config.beforeAdd)&&(this.on("beforeAdd",this.config.beforeAdd));
            (this.config.beforeEdit)&&(this.on("beforeEdit",this.config.beforeEdit));
            (this.config.beforeCancel)&&(this.on("beforeCancel",this.config.beforeCancel));
            (this.config.beforeSave)&&(this.on("beforeSave",this.config.beforeSave));
            (this.config.beforeDelete)&&(this.on("beforeDelete",this.config.beforeDelete));
            //this.postData._condition=this.config._condition;//设置查询条件字段_condition
            //this.postData._sort=this.config._sort;//排序字段_sort
        },
        /*初始化表格字段设置*/
        initThSetting:function(grid){
            /*遍历设置源数据字段_metaData、过滤字段_flitData*/
            var _gridObj=this;
            var ths=grid.find("thead th");
            $.each(ths,function(index,item){
                _gridObj.headerThObj.initTh($(item));
            });
        },
        /*渲染表格结构、数据*/
        initGrid:function(container,callback){
            var gridObj=this;
            var gridTml=$('<div class="edit-grid "></div>');
            gridTml.get(0).checked=true;//作为表单提交过滤用
            container.attr("name")&&gridTml.attr("name",container.attr("name"));
            //往dom节点加载表格模板
            gridTml.load(this.config.templateUrl,{},function(){
                container.after(gridTml);
                container.remove();
                gridObj.grid=gridTml;
                gridObj.showObj= new showObj(gridObj);//表格显示对象
                callback&&callback(gridObj);
                var postParam=[{name:"_condition",value:JSON.stringify(gridObj.postData._condition)},
                    {name:"_metadata",value:JSON.stringify(gridObj.postData._metaData)},
                    {name:"_sort",value:JSON.stringify(gridObj.postData._sort)}];

                $.post(gridObj.config.url,postParam,function(data){
                    gridObj.dataObj.gridDataFilter(data.result);
                    /* 设置当前页显示数据，触发显示数据事件 */
                    var currentData=gridObj.dataObj.getCurrentData(gridObj.config.currentPage,gridObj.config.pageSize);
                    gridObj.showObj.showData(currentData);
                    /*获取数据完成后，构建分页栏*/
                    gridObj.pageBarObj=new pageBarObj(gridObj);
                },"json")
            });
        },
        /* 自定义事件绑定 */
        on:function(type,handler){
            if(typeof this.handlers[type]=="undefined"){
                this.handlers[type]=[];
            }
            this.handlers[type].push(handler);
        },
        /* 自定义事件触发 */
        fire:function(type,data){
            //todo 自定义函数进程阻断
            var isContinue;
            if(this.handlers[type] instanceof Array){
                var handlers=this.handlers[type];
                $.each(handlers,function(){
                    isContinue=this(data);
                })
            }else{
                isContinue=true;
            }
            return isContinue;
        },
        /*初始化表格事件*/
        initEvent:function(){
            var gridObj=this;
            var leftGrid=$(".e-grid-left",this.grid),
                rightGrid=$(".e-grid-right",this.grid),
                pageBar=$(".e-panel-bar",this.grid);
            rightGrid.click(function(event){
                if(event.target.tagName.toLowerCase() !="a")return //现在只绑定了a 标签图标事件
                var _target=event.target;
                _target=$(_target).closest("tr");
                switch (event.target.id){
                    case "add":{
                        //artDialog.correct("新增一行");
                        if(!gridObj.fire("beforeAdd")) return;
                        gridObj.showObj._recoverHiddenRow();//先判断进行还原编辑行处理
                        var _index=(gridObj.config.currentPage==1)?0:(gridObj.config.currentPage-1)*gridObj.config.pageSize;
                        var dataId="e-temId-"+util.uuid(4,16);
                        gridObj.dataObj.gridDataArray4Obj.splice(_index,0,dataId);//插入新的id
                        gridObj.dataObj.gridDataObj[dataId]={} //todo
                        var currentData=gridObj.dataObj.getCurrentData(gridObj.config.currentPage,gridObj.config.pageSize);
                        gridObj.showObj.showData(currentData);
                        pageBar.trigger("update");//新增数据后更新分页栏信息
                        var newTr=$("#"+dataId,rightGrid);
                        $("#edit",newTr).trigger("click");
                        //编辑塞值前，转换当前数据的显示值和真实值
                        var rowData= $.extend({},newTr.data("rowData"));
                        rowData = gridObj.dataObj.translateData4Edit(rowData);
                        $.fn.setFormObject(newTr,rowData);
                        gridObj.config.editRowId=dataId;
                        break;
                    }
                    case "edit":{
                        //artDialog.correct("编辑一行");
                        if(!gridObj.fire("beforeEdit",_target.data("rowData"))) return;
                        /* 保存之前编辑行数据 */
                        if(gridObj.config.editState){
                            var dataObj=gridObj.dataObj.getRowData($("#"+gridObj.config.editRowId,rightGrid));
                            if(!gridObj.fire("beforeSave",dataObj)) return;
                            gridObj.dataObj.updateRadioData(dataObj);//存在radio单选控件
                            gridObj.dataObj.gridDataObj[gridObj.config.editRowId]=dataObj;
                            gridObj.showObj._recoverHiddenRow();//还原编辑行处理
                        }
                        var editRowTd=$("tfoot tr td",rightGrid),
                            _target=_target.empty();//现将当前行清空
                        $.each(editRowTd,function(index,item){
                            _target.append($(item));
                        });
                        gridObj.showObj._addOperateBtn(_target,["save","cancel","delete"])
                        gridObj.config.editRowId=_target.attr("id");
                        gridObj.config.editState=true;
                        //编辑塞值前，转换当前数据的显示值和真实值
                        var rowData= $.extend({},_target.data("rowData"));
                        rowData = gridObj.dataObj.translateData4Edit(rowData);
                        $.fn.setFormObject(_target,rowData);
                        break;
                    }
                    case "cancel":{
                        //artDialog.correct("撤销操作");
                        if(!gridObj.fire("beforeCancel",_target.data("rowData"))) return;
                        gridObj.showObj._recoverHiddenRow();//还原文本行处理
                        break;
                    }
                    case "save":{
                        //artDialog.correct("保存操作");
                        /* todo 开启保存验证 */
                        //if(!util.validateForm($(".validationEngineContainer",rightGrid))) return
                        var dataObj= gridObj.dataObj.getRowData(_target);//获取当前行编辑后的值
                        if(!gridObj.fire("beforeSave",dataObj)) return;
                        gridObj.dataObj.updateRadioData(dataObj);//存在radio单选控件
                        gridObj.dataObj.gridDataObj[_target.attr("id")]=dataObj;
                        gridObj.showObj._recoverHiddenRow();//还原文本行处理
                        break;
                    }
                    case "delete":{
                        //artDialog.correct("删除一行");
                        if(!gridObj.fire("beforeDelete",_target.data("rowData"))) return
                        artDialog.confirm("确认删除这条数据吗？",function(){
                            gridObj.showObj._recoverHiddenRow();//先判断是否处于编辑状态，是：进行还原文本行处理
                            var dataId=_target.attr("id"),
                                _index= $.inArray(dataId,gridObj.dataObj.gridDataArray4Obj);
                            gridObj.dataObj.gridDataArray4Obj.splice(_index,1);//删除gridDataArray4Obj数据对象中,索引为_index的元素。
                            delete gridObj.dataObj.gridDataObj[dataId];//同时删除gridDataObj对应对象
                            var currentData=gridObj.dataObj.getCurrentData(gridObj.config.currentPage,gridObj.config.pageSize);
                            gridObj.showObj.showData(currentData);
                            pageBar.trigger("update");
                        });
                        break;
                    }
                    default :break;
                }
            });
            rightGrid.dblclick(function(event){
                var _target=$(event.target).closest("tr");
                if(!gridObj.fire("beforeEdit",_target.data("rowData"))) return;
                var edit=_target.find("#edit");
                if(edit) edit.trigger("click");
            });
            /* 分页栏刷新事件 */
            pageBar.on("update",function(){
                /* 更新分页栏 */
                gridObj.pageBarObj._initPageGroup(gridObj.config.currentPage,gridObj.config.pageSize);
                gridObj.pageBarObj._setPageMessage(gridObj.config.currentPage,gridObj.config.pageSize);
            });
            pageBar.click(function(event){
                var type="",
                    tar=event.target.tagName.toLowerCase();
                if(tar=="div"||$(event.target).hasClass("e-bar-text")||$(event.target).hasClass("e-bar-select"))return
                switch (tar){
                    case "span":
                        var _target=$(event.target).closest("a",pageBar);
                        type=_target.attr("class");
                        break;
                    case "a": type=event.target.class; break;
                    //case "select": type="select";break;
                }

                switch (type){
                    case "first":{
                        //首页
                        gridObj.showObj._recoverHiddenRow();//先判断是否需要进行还原编辑行处理
                        gridObj.config.currentPage=1;
                        gridObj.pageBarObj.setPage(gridObj.config.currentPage,gridObj.config.pageSize);
                        break;
                    }
                    case "previous":{
                        //上一页
                        gridObj.showObj._recoverHiddenRow();//先判断是否需要进行还原编辑行处理
                        gridObj.config.currentPage=gridObj.config.currentPage-1;
                        gridObj.pageBarObj.setPage(gridObj.config.currentPage,gridObj.config.pageSize);
                        break;
                    }
                    case "next":{
                        //下一页
                        gridObj.showObj._recoverHiddenRow();//先判断是否需要进行还原编辑行处理
                        gridObj.config.currentPage=gridObj.config.currentPage+1;
                        gridObj.pageBarObj.setPage(gridObj.config.currentPage,gridObj.config.pageSize);
                        break;
                    }
                    case "last":{
                        //末页
                        gridObj.showObj._recoverHiddenRow();//先判断是否需要进行还原编辑行处理
                        var dataLength=gridObj.dataObj.gridDataArray4Obj.length,
                            lastPage=0;
                        if(dataLength<=gridObj.config.pageSize){
                            lastPage=1;
                        }else{
                            lastPage=parseInt(dataLength/gridObj.config.pageSize);
                            if((dataLength%gridObj.config.pageSize)!=0)lastPage++;
                        }
                        gridObj.config.currentPage=lastPage;
                        gridObj.pageBarObj.setPage(lastPage,gridObj.config.pageSize);
                        break;
                    }
                    case "goto": {
                        //确定按钮，指定页数跳转
                        gridObj.showObj._recoverHiddenRow();//先判断是否需要进行还原编辑行处理
                        var inputPage=$(event.target).siblings().val();
                        inputPage=parseInt(inputPage);
                        gridObj.config.currentPage=inputPage;
                        gridObj.pageBarObj.setPage(inputPage,gridObj.config.pageSize);
                        break;
                    }
                    default :break;
                }
            });
            pageBar.change(function(event){
                var tar=event.target.tagName.toLowerCase();
                if(tar!="select") return
                gridObj.showObj._recoverHiddenRow();//先判断是否需要进行还原编辑行处理
                var newPageSize=parseInt($(event.target).val());
                gridObj.config.pageSize=newPageSize;
                gridObj.pageBarObj.setPage(gridObj.config.currentPage,gridObj.config.pageSize);
            });
            /* 表格内容拖动事件，需要在创建行后重新绑定 */
            this.grid.on("draggable",function(){
                gridObj.showObj.setRowDrag();
            });

        },
        /*控件销毁方法*/
        destroy:function(){
            this.handlers={};//清空事件
        }
    }

    $.fn.editGrid=function($grid,$p){
        var newGrid= new editGrid($grid,$p);
        newGrid.render(function(gridObj){
            addEditGridFn(gridObj);
            (gridObj.grid).data("editGrid",gridObj);
        });
    }


    /*添加编辑表格对外提供的方法*/
    var addEditGridFn = function(gridObj){
        /*刷新表格
         * url 存在，载入指定url数据
         * url 不存在，刷新表格数据
         * */
        gridObj.reload=function(_url){
            gridObj.showObj._recoverHiddenRow();//先判断是否需要进行还原编辑行处理
            /*先清空数据对象中旧数据*/
            gridObj.dataObj.gridDataObj={};
            gridObj.dataObj.gridDataArray4Obj=[];
            var url= (_url)?(_url):(gridObj.config.url);  // url存在加载新数据，不存在重载
            if(!url.startsWith("http://")) url= $.fn.contextPath+url;
            var postParam=[{name:"_condition",value:JSON.stringify(gridObj.postData._condition)},
                {name:"_metadata",value:JSON.stringify(gridObj.postData._metaData)},
                {name:"_sort",value:JSON.stringify(gridObj.postData._sort)}];
            $.ajaxSetup({cache:false});
            $.post(url,postParam,function(data){
                gridObj.dataObj.gridDataFilter(data.result);
                /* 设置当前页显示数据，触发显示数据事件 */
                var currentData=gridObj.dataObj.getCurrentData(gridObj.config.currentPage,gridObj.config.pageSize);
                gridObj.showObj.showData(currentData);
                $(".e-panel-bar",gridObj.grid).trigger("update");//更新分页栏
            },"json");
        }
        /*表格数据是否都保存*/
        gridObj.isSaved=function(){

        };
        /*获取表格编辑状态*/
        gridObj.getEditSate=function(){
            return gridObj.config.editState;
        };
    }

})(jQuery,window)
/**
 * Created by yan on 2015/12/6 0006.
 */

(function ($, window, tpl) {

    /* 表格外层模板 */
    var $domNodeHtml = '<div class="edit-grid" ><div>';
    /** 基本结构模板 **/
    var gridHtml = null;
    var getGridHtml = function () {
        if (gridHtml)return gridHtml;
        var gridHtmlUrl = "widgets-src/duceap/js/grid/editGrid/cacheGrid.html";
        gridHtml = $.ajax({url: $.fn.contextPath + gridHtmlUrl, async: false}).responseText;
        return gridHtml;
    };
    /** 基本th模板 **/
    var gridThHtml = '<th name="{{name}}" ><div>{{text}}</div></th>';
    /** 基本tr模板 **/
    var gridTrHtml = '<tr id="{{id}}"></tr>';
    /** 无数据tr模板 **/
    var gridTrHtml4NoneData = '<tr ><td>无数据</td></tr>';
    /** 基本tdtr模板 **/
    var gridTdHtml = '<td name="{{name}}"><div name="{{name}}" title="{{text}}">{{text}}</div></td>';
    /** 文字显示 **/
    var gridPageMsg = "显示从{{start}}到{{end}}，总 {{recordCount}} 条 。每页显示：{{pageSize}}";
    var KEY_NAME = "key_name_data_cache_grid_duceap";


    /** 工具类 **/
    var util = {
        maxLength: function ($input, maxLength) {
            maxLength = parseInt(maxLength);
            var getStrLength = function (str) {
                var myLen = 0;
                for (var i = 0; i < str.length; i++) {
                    if (str.charCodeAt(i) > 255)
                        myLen += 2;
                    else
                        myLen++;
                }
                return myLen;
            }
            var subStr = function (str, maxlength) {
                var myLen = 0;
                ;
                for (var i = 0; i < str.length; i++) {
                    if (str.charCodeAt(i) > 255)
                        myLen += 2;
                    else
                        myLen++;
                    if (myLen > maxlength) {
                        str = str.substr(0, i);
                    }
                }
                return str;
            }
            $input.bind('keydown keyup keypress copy cut paste propertychange', function (e) {
                var value = $(this).val();
                var len = getStrLength(value);
                if (len > maxLength) {
                    value = subStr(value, maxLength);
                    $(this).val(value);
                } else {
                    return;
                }
                $(this).val(value);
                e.returnValue = false;
            });
        }
    }

    /**  控件接口 **/
    var CmpInterface = function ($grid, dpProps) {
    }
    var cmpFn = CmpInterface.prototype;
    cmpFn["common"] = {
        component: function (obj) {
            var tplHtml = "<div class='input-div'><input type='text'  name='{{name}}'class='textInput {{jclass}}'/></div>";
            tplHtml = tpl.compile(tplHtml)(obj);
            var $inputDiv = $(tplHtml);
            obj.$div4foot.append($inputDiv);

            for (var key in obj.props) {
                if (!key.startsWith("on"))continue;
                $inputDiv.find("input").on(key.replace("on", "").toLowerCase(), obj.props[key]);
            }
            /** 长度限制 **/
            var $cmp = $inputDiv.find("input");
            if (obj.maxLength)
                util.maxLength($cmp, obj.maxLength);

            $cmp.readonly = function (flag) {
                $cmp.attr("readonly", flag);
            };
            return $cmp;
        },
        setText: function (obj) {
            var text = obj.rowData[obj.name];
            obj.$div4tbody.html(text).attr("title", text);
        },
        getValue: function (obj) {
            return obj.$cmp.val();
        },
        setValue: function (obj) {
            obj.$cmp.val(obj.rowData[obj.name] || "");
        }
    }
    cmpFn["text"] = $.extend({}, cmpFn["common"]);
    cmpFn["radio"] = $.extend({}, cmpFn["common"], {
        component: function (obj) {
            var tplHtml = "<div class='check-div'><input type='radio' name='{{name}}'class='textInput {{jclass}}'/><label>&nbsp;</label></div>";
            tplHtml = tpl.compile(tplHtml)(obj);
            var $inputDiv = $(tplHtml);
            obj.$div4foot.append($inputDiv);

            for (var key in obj.props) {
                if (!key.startsWith("on"))continue;
                $inputDiv.find("input").on(key.replace("on", "").toLowerCase(), obj.props[key]);
            }


            var $cmp = $inputDiv.find("input");

            $cmp.iCheck({
                checkboxClass: 'icheckbox-blue',
                radioClass: 'iradio-blue',
                increaseArea: '20%'
            });

            return $inputDiv;
        },
        setText: function (obj) {
            var tplHtml = "<div class='check-div'><input type='radio' name='{{name}}' class='{{jclass}}' disabled='disabled'  /><label>&nbsp;</label></div>";
            var text = obj.rowData[obj.name];
            var compareYes = obj.hasOwnProperty("check") ? obj.check : "1";
            tplHtml = tpl.compile(tplHtml)(obj);
            obj.$div4tbody.html(tplHtml);
            if (text == compareYes) {
                obj.$div4tbody.find(":radio").attr("checked", "true");
            }
            obj.$div4tbody.find(":radio").iCheck({
                checkboxClass: 'icheckbox-blue',
                radioClass: 'iradio-blue',
                increaseArea: '20%'
            });
        },
        getValue: function (obj) {
            var value = "";
            if (obj.$cmp.find("input").is(":checked")) {
                value = obj.hasOwnProperty("check") ? obj.check : "1";
            } else {
                value = obj.hasOwnProperty("uncheck") ? obj.uncheck : "0";
            }
            obj.$cmp.find("input").iCheck("uncheck");
            return value;
        },
        setValue: function (obj) {
            var value = obj.rowData[obj.name];
            var compareYes = obj.hasOwnProperty("check") ? obj.check : "1";
            var compareNo = obj.hasOwnProperty("uncheck") ? obj.uncheck : "0";
            if (value == compareYes) {
                obj.$cmp.find("input").iCheck("check");
            } else {
                obj.$cmp.find("input").iCheck("uncheck");
            }
        }
    });
    cmpFn["checkbox"] = $.extend({}, cmpFn["common"], {
        component: function (obj) {
            var tplHtml = "<div class='check-div'><input type='checkbox' name='{{name}}'class='textInput {{jclass}}'/><label>&nbsp;</label></div>";
            tplHtml = tpl.compile(tplHtml)(obj);
            var $inputDiv = $(tplHtml);
            obj.$div4foot.append($inputDiv);

            for (var key in obj.props) {
                if (!key.startsWith("on"))continue;
                $inputDiv.find("input").on(key.replace("on", "").toLowerCase(), obj.props[key]);
            }


            var $cmp = $inputDiv.find("input");

            $cmp.iCheck({
                checkboxClass: 'icheckbox-blue',
                radioClass: 'iradio-blue',
                increaseArea: '20%'
            });

            return $inputDiv;
        },
        setText: function (obj) {
            var tplHtml = "<div class='check-div'><input type='checkbox' name='{{name}}' class='{{jclass}}' disabled='disabled'  /><label>&nbsp;</label></div>";
            var text = obj.rowData[obj.name];
            var compareYes = obj.hasOwnProperty("check") ? obj.check : "1";
            tplHtml = tpl.compile(tplHtml)(obj);
            obj.$div4tbody.html(tplHtml);
            if (text == compareYes) {
                obj.$div4tbody.find(":checkbox").attr("checked", "true");
            }
            obj.$div4tbody.find(":checkbox").iCheck({
                checkboxClass: 'icheckbox-blue',
                radioClass: 'iradio-blue',
                increaseArea: '20%'
            });
        },
        getValue: function (obj) {
            var value = "";
            if (obj.$cmp.find("input").is(":checked")) {
                value = obj.hasOwnProperty("check") ? obj.check : "1";
            } else {
                value = obj.hasOwnProperty("uncheck") ? obj.uncheck : "0";
            }
            obj.$cmp.find("input").iCheck("uncheck");
            return value;
        },
        setValue: function (obj) {
            var value = obj.rowData[obj.name];
            var compareYes = obj.hasOwnProperty("check") ? obj.check : "1";
            var compareNo = obj.hasOwnProperty("uncheck") ? obj.uncheck : "0";
            if (value == compareYes) {
                obj.$cmp.find("input").iCheck("check");
            } else {
                obj.$cmp.find("input").iCheck("uncheck");
            }
        }
    });
    cmpFn["duceap.date"] = $.extend({}, cmpFn["common"], {
        component: function (obj) {
            var tplHtml = "<div class='input-div'><input type='text' name='{{name}}'class='textInput {{jclass}}'/></div>";
            tplHtml = tpl.compile(tplHtml)(obj);
            var $inputDiv = $(tplHtml);
            obj.$div4foot.append($inputDiv);

            for (var key in obj.props) {
                if (!key.startsWith("on"))continue;
                $inputDiv.find("input").on(key.replace("on", "").toLowerCase(), obj.props[key]);
            }

            return $inputDiv.find("input").dateDp(obj.props);
        },
        setText: function (obj) {
            var text = obj.$cmp.text4val(obj.rowData[obj.name]);
            obj.$div4tbody.html(text).attr("title", text);
        }
    });
    cmpFn["edit"] = $.extend({}, cmpFn["common"], {
        component: function (obj) {
            var tpl = '<a  id="save4cacheGrid"  title="保存" class="a-icon a-save" href="#"></a>' +
                '<a  id="delete4cacheGrid"  title="删除" class="a-icon a-delete" href="#"></a>' +
                '<a  id="cancel4cacheGrid"  title="取消" class="a-icon a-cancel" href="#"></a>';
            var $a = $(tpl);
            obj.$div4foot.append($a);
        },
        setText: function (obj) {
            obj.$div4tbody.html('<a  id="edit4cacheGrid"  title="修改" class="a-icon a-edit" href="#"></a>' +
            '<a  id="delete4cacheGrid"  title="删除" class="a-icon a-delete" href="#"></a>');
        },
        getValue: function (obj) {
        },
        setValue: function (obj) {
        }
    });
    cmpFn["duceap.dropDownRadio"] = $.extend({}, cmpFn["common"], {
        component: function (obj) {
            var props = $.extend({store: new Date().getTime()}, obj.props, {width: "auto"});
            var $input = $("<input type='text' name='" + obj.name + "'class=='textInput" + obj.jclass + "'/>");
            obj.$div4foot.append($input);
            return $input.dropDownRadioDp(props);

        },
        setText: function (obj) {
            var text = obj.$cmp.text4val(obj.rowData[obj.name]);
            obj.$div4tbody.html(text).attr("title", text);
        }
    });
    cmpFn["duceap.dropDownTree"] = $.extend({}, cmpFn["common"], {
        component: function (obj) {
            var props = $.extend({store: new Date().getTime()}, obj.props, {width: "auto"});
            var $input = $("<input type='text' name='" + obj.name + "'class='textInput" + obj.jclass + "'/>");
            obj.$div4foot.append($input);
            return $input.dropDownTreeDp(props);
        },
        setText: function (obj) {
            var text = obj.$cmp.text4val(obj.rowData[obj.name]);
            obj.$div4tbody.html(text).attr("title", text);
        }
    });
    cmpFn["duceap.autoUploader"] = $.extend({}, cmpFn["common"], {
        component: function (obj) {
            var props = $.extend({}, obj.props, {width: "86"});
            var $input = $("<div ></div>");
            obj.$div4foot.append($input);
            var $uploader = $input.autoUploaderDp(props);
            return $uploader;
        },
        setText: function (obj) {
            //var text = obj.$cmp.text4val(obj.rowData[obj.name]);
            //obj.$div4tbody.html(text).attr("title", text);
        }
    });
    cmpFn["lookup"] = $.extend({}, cmpFn["common"], {
        component: function (obj) {
            var props = $.extend({}, obj.props, {width: "auto"});
            var id = new Date().getTime();
            var $inputDiV = $("<div class='lookup-div'><input type='text' id='" + id + "'  name='" + obj.name + "'class='textInput " + obj.jclass + "'/> <div class='btn-square'> <span>选择</span></div></div>");
            $inputDiV.find(".btn-square").click(function (event) {
                var data = obj.props.data || {};
                if (obj.props.onClick)
                    $.extend(data, obj.props.onClick(obj));
                var bringMap = {};
                bringMap["#" + id] = obj.name;
                $m.lookUp({
                    url: obj.props.url,
                    width: obj.props.dialogWidth,
                    height: obj.props.dialogHeight,
                    data: data,
                    bringMap: bringMap
                });
            });
            obj.$div4foot.append($inputDiV);
            return $inputDiV.find("input");
        },
        setText: function (obj) {
            var text = obj.rowData[obj.name];
            obj.$div4tbody.html(text).attr("title", text);
        }
    });
    $.extend(CmpInterface.prototype, {
        filterProps: function (obj) {
            var attrs = ["text", "jclass", "plugin", "name", "type", "maxLength"];
            $.each(attrs, function (index, attr) {
                obj[attr] = obj.props[attr];
            });
            var props = {};
            for (var key in obj.props) {
                if ($.inArray(key, attrs) >= 0)continue;
                props[key] = obj.props[key];
            }
            obj.props = props;
            return obj;
        },
        getCmpObj: function (obj) {
            if (obj.plugin) return $.extend({}, cmpFn["common"], obj.plugin);
            return CmpInterface.prototype[obj.type] || CmpInterface.prototype["common"]; //获取控件对象
        }
    });


    /** 数据缓存 **/
    var DataStore = function ($grid, dpProps) {
        this.$grid = $grid;
        this.dpProps = dpProps;
        $.extend(this, {
            pageSize: 10,
            pageNumber: 1,
            recordCount: 10,
            start: 0, //显示页面的第一条记录的序号
            end: 10, // 显示页面的最后一条记录的序号
            isLast: true,
            isNext: true,
            isFirst: false,
            isPrev: false,
            pageCount: 1,
            uniqueObjects: dpProps.uniqueObjects,//单选，数据，只有一个是选择的唯一字段，用于单选框
            dataMap: {},
            dataKeys: [],
            loadParam: {url: dpProps.url, param: dpProps.param, callback: dpProps.callback}
        });
    }
    $.extend(DataStore.prototype, {
        setPageView: function () {
            /** 根据 记录数 和 单页数，计算分页页面数 **/
            this.recordCount = this.dataKeys.length;
            var pageCount = parseInt(this.recordCount / this.pageSize);
            if (this.recordCount % this.pageSize > 0) pageCount += 1;
            this.pageCount = pageCount;

            /** 根据 分页页面数 和 页面索引，修正 页面索引**/
            if (this.pageNumber <= 1) {
                this.pageNumber = 1;
            } else if (this.pageNumber > this.pageCount) {
                this.pageNumber = this.pageCount;
            } else {
                this.pageNumber = this.pageNumber;
            }

            /** 根据 单页数 和 页面索引，计算 当前页记录的始终索引**/
            this.start = (this.pageNumber - 1) * this.pageSize + 1;
            this.end = this.pageNumber * this.pageSize;
            if (this.end > this.recordCount)
                this.end = this.recordCount;

            /** 根据 分页页面数 和 页面索引 判断分页按钮可用性**/
            var isSmall = this.pageNumber <= 1;
            var isLarge = this.pageNumber >= this.pageCount;
            this.isPrev = isSmall ? false : true;
            this.isFirst = isSmall ? false : true;
            this.isNext = isLarge ? false : true;
            this.isLast = isLarge ? false : true;

        },
        getView: function () {
            this.setPageView();
            var viewData = [];
            var keys = this.dataKeys.slice(this.start - 1, this.end + 1);
            var dataMap = this.dataMap;
            $.each(keys, function (index, key) {
                dataMap[key][KEY_NAME] = key;
                viewData.push(dataMap[key]);
            });
            return viewData;
        },
        getData: function () {
            var viewData = [];
            var dataMap = this.dataMap;
            $.each(this.dataKeys, function (index, key) {
                var dataTemp = $.extend({}, {}, dataMap[key]);
                delete dataTemp[KEY_NAME];
                viewData.push(dataTemp);
            });
            return viewData;
        },
        transformData4Submit: function () {
            return this.getData();
        },
        onChange: function () {
            var _this = this;
            _this.$grid.trigger("viewChange", [this.getView(), this]);
        },
        refresh: function () {
            this.onChange();
        },
        _createDataStore: function (data, postParam) {
            var idStart = (new Date()).getTime();
            var _this = this;
            _this.dataMap = {};
            _this.dataKeys = [];
            $.each(data, function (index, item) {
                var key = idStart + "_" + index;
                _this.dataMap[key] = item;
                _this.dataKeys.push(key);
            });
            this.recordCount = _this.dataKeys.length;
        },
        _uniqueData: function (id, rowData) {
            var _this = this;
            $.each(this.dpProps.uniqueObjects, function (index, obj) {
                if (rowData[obj.name] != obj.check)return;//当前行没有选择，则不执行
                /** 其他没选择的行重置为没选择的值 **/
                for (var key in _this.dataMap) {
                    var rowDataTemp = _this.dataMap[key];
                    if (key == id)continue;
                    rowDataTemp[obj.name] = obj.uncheck;
                }
            })

        },
        load: function (url, param, callback) {
            /** 根据参数类型，来确定参数，覆盖之前的参数 **/
            var loadParam = {};
            $.each(arguments, function (index, item) {
                if ($.isFunction(item))loadParam["callback"] = item;
                if (typeof  item == "string")loadParam["url"] = item;
                if (typeof  item == "object")loadParam["param"] = item;
            });
            $.extend(this.loadParam, loadParam);
            //var postParam = [
            //    {name: "_condition", value: JSON.stringify(this.dpProps._condition)},
            //    {name: "_metadata", value: JSON.stringify(this.dpProps._metaData)},
            //    {name: "_sort", value: JSON.stringify(this.dpProps._sort)}
            //];


            if (!this.loadParam.url) {
                this._createDataStore([]);
                this.onChange();
                return;
            }

            var _this = this;
            $.post($.fn.contextPath + this.loadParam.url, this.loadParam.param, function (data) {
                _this._createDataStore(data.result);
                _this.loadParam.callback && _this.loadParam.callback(_this.getView(), this);
                _this.onChange();
            }, "json");
        },
        getRow: function (key) {
            return {key: key, value: this.dataMap[key]};
        },
        _isMatch: function (row, conditions) {
            var flag = true;
            $.each(conditions, function (index, condition) {
                var isMatch = false;
                if (condition.op == "=") {
                    isMatch = (condition.value == row[condition.name]);
                } else if (condition.op == "start") {
                    isMatch = (row[condition.name].startsWith(condition.value));
                } else if (condition.op == "like") {
                    isMatch = (row[condition.name].indexOf(condition.value) >= 0);
                } else if ($.isFunction(condition.op)) {
                    isMatch = condition.op(row);
                }
                flag=flag&&isMatch;
            });
            return flag;
        },
        findRows: function (conditions) {
            var result=[];
            var dataMap = this.dataMap;
            var _this=this;
            $.each(this.dataKeys, function (index, key) {
                var dataTemp = $.extend({}, {}, dataMap[key]);
                delete dataTemp[KEY_NAME];
                if(_this._isMatch(dataTemp,conditions))
                    result.push({key:key,value:dataTemp});
            });
            return result;
        },
        updateRow: function (rowData, key) {
            $.extend(this.dataMap[key], rowData);
            this._uniqueData(key, rowData);
            this.onChange();
            return {key: key, value: rowData};
        },
        addRow: function (rowData) {
            var key = (new Date()).getTime() + "_0";
            this.dataMap[key] = rowData;
            this.dataKeys.unshift(key);
            this._uniqueData(key, rowData);
            this.onChange();
            return {key: key, value: rowData};
        },
        deleteRow: function (key) {
            this.dataMap[key] = null;
            delete this.dataMap[key];
            for (var i = 0; i < this.dataKeys.length; i++) {
                if (this.dataKeys[i] != key)continue;
                this.dataKeys.splice(i, 1);
                break;
            }
            this.onChange();
        },
        /** 分页 **/
        goTo: function (pageNumber) {
            this.pageNumber = pageNumber;
            this.onChange();
        },
        next: function () {
            this.goTo(this.pageNumber + 1);
        },
        prev: function () {
            this.goTo(this.pageNumber - 1);
        },
        first: function () {
            this.goTo(1);
        },
        last: function () {
            this.goTo(this.pageCount);
        },
        changePageSize: function (pageSize) {
            this.pageSize = pageSize;
            this.onChange();
        },

        changeIndex: function (id, index) {
            /** 删除指定的id **/
            for (var i = 0; i < this.dataKeys.length; i++) {
                var key = this.dataKeys[i];
                if (key == id) {
                    this.dataKeys.splice(i, 1);
                }
            }

            /** 增加指定的id到index **/
            var allIndex = index + this.start - 1;
            this.dataKeys.splice(allIndex, 0, id);

            this.onChange();
        }
    });
    /** 提供外部使用数据缓存接口 **/
    $.fn.cacheStoreDp = function (dpProps) {
        var $grid = $(this);
        return new DataStore($grid, dpProps);
    }

    /** 拖动改变顺序 **/
    var DragRender = function ($grid, dpProps) {
        this.$grid = $grid;
        this.dpProps = dpProps;
    }
    $.extend(DragRender.prototype, {
        bindRowDrag: function (callback) {
            $("tbody", this.$grid).dragsort({
                dragSelector: "tr",
                tagName: "tr",
                scrollContainer: "tbody",
                dragEnd: function () {
                    callback && callback({id: $(this).attr("id"), index: $(this).index(), $tr: $(this)});
                },
                dragBetween: false,
                placeHolderTemplate: "<tr></tr>"
            });
        }
    });

    /** 展现表格  **/
    var GridRender = function ($grid, dpProps) {
        this.$grid = $grid;
        this.dpProps = dpProps;
        this.cmpIt = CmpInterface.prototype;
    }
    $.extend(GridRender.prototype, {
        /** 创建表结构，初始化表头和表尾  begin**/
        initTable: function () {
            /** 创建表基本结构 **/
            var gridHtml = getGridHtml();
            this.$grid.html(gridHtml)

            /** 创建表头基本结构 **/
            this._initHeadTh();
            this._initFootTh();
        },
        _initHeadTh: function () {
            var dpProps = this.dpProps;
            var thProps = this.dpProps.thProps;
            var $theadTr = this.$grid.find("thead tr");
            $.each(thProps, function (index, thProp) {
                var $td = $(tpl.compile(gridThHtml)(thProp));
                $theadTr.append($td);
                thProp.html && $td.find("div").html(thProp.html);
            });
        },
        _initFootTh: function () {
            var dpProps = this.dpProps;
            var thProps = this.dpProps.thProps;
            var $tfootTr = this.$grid.find("tfoot tr");
            $.each(thProps, function (index, thProp) {
                $tfootTr.append(tpl.compile(gridTdHtml)({name: thProp.name, text: ""}));
            });
        },
        /** 创建表结构，初始化表头和表尾  end**/
        hidePage: function (pageData) {
            /** 显示信息 **/
            var pagePanel = this.$grid.find("#pagePanel4cacheGrid");
            pagePanel.hide();
        },
        showPage: function (pageData) {
            /** 显示信息 **/
            var pagePanel = this.$grid.find("#pagePanel4cacheGrid");
            if (pageData.isFirst)
                pagePanel.find("span[id^='first']").removeClass("l-disabled");
            else
                pagePanel.find("span[id^='first']").addClass("l-disabled");

            if (pageData.isPrev)
                pagePanel.find("span[id^='prev']").removeClass("l-disabled");
            else
                pagePanel.find("span[id^='prev']").addClass("l-disabled");

            if (pageData.isNext)
                pagePanel.find("span[id^='next']").removeClass("l-disabled");
            else
                pagePanel.find("span[id^='next']").addClass("l-disabled");

            if (pageData.isLast)
                pagePanel.find("span[id^='last']").removeClass("l-disabled");
            else
                pagePanel.find("span[id^='last']").addClass("l-disabled");

            pagePanel.find("input[id^='goto']").val(pageData.pageNumber);
            pagePanel.find("select[id^='pageSize']").val(pageData.pageSize);
            pagePanel.find("span[id^='pageCount']").html(pageData.pageCount);
            pagePanel.find("span[id^='msg']").html(tpl.compile(gridPageMsg)(pageData));
        },
        showRows: function (data) {
            var _this = this;
            this.$grid.find("tbody>tr").remove();

            /**  无数据 **/
            if (data.length <= 0) {
                this.$grid.find("table.cache-table").css("width", "100%");
                this.showRow4NoneData();
            } else {
                this.$grid.find("table.cache-table").css("width", "auto");
            }


            $.each(data, function (index, rowData) {
                _this.showRow(rowData);
            });
            _this._calculateTable();
        },
        showRow4NoneData: function () {
            var thProps = this.dpProps.thProps;
            var $tr = $(gridTrHtml4NoneData);
            $tr.find("td").attr("colspan", thProps.length);
            this.$grid.find("tbody").append($tr);
        },
        showRow: function (rowData) {
            var _this = this;
            var thProps = this.dpProps.thProps;
            var $tr = $(tpl.compile(gridTrHtml)({id: rowData[KEY_NAME]}));
            this.$grid.find("tbody").append($tr);
            var $foot = this.$grid.find("tfoot");
            $.each(thProps, function (index, thProp) {
                var $td = $(tpl.compile(gridTdHtml)({name: thProp.name, text: rowData[thProp.name]}));
                $tr.append($td);
                var cmpMap = $foot.data("cmpMap") || {};

                var paramObj = _this.cmpIt.filterProps({
                    rowData: rowData,
                    props: thProp,
                    $div4tbody: $td.find(">div"),
                    $cmp: cmpMap[thProp.name]
                });//参数组装
                var cmpObj = _this.cmpIt.getCmpObj(paramObj); //获取控件对象
                thProp.onBeforeSetText && thProp.onBeforeSetText(paramObj);
                cmpObj.setText(paramObj);
                thProp.onAfterSetText && thProp.onAfterSetText(paramObj);
            })
        },
        _getWidthByName: function (name) {
            var resultProp = {};
            $.each(this.dpProps.thProps, function (index, thProp) {
                if (thProp.name != name)return;
                resultProp = thProp;
            });
            return parseInt(resultProp.width);
        },
        /** 显示完表格，根据内容进行一轮计算，使得内容大于设置的宽度的列出现省略号  **/
        _calculateTable: function () {
            var _this = this;

            /** （1）
             *  如果用户设置宽度，以用户设置的宽度为主（内容过多，将出现省略号），
             *  否则，自动撑开
             **/
            $("thead th", this.$grid).each(function (index, th) {
                var name = $(th).attr("name");
                var width = _this._getWidthByName(name);
                var $bodyDivs = _this.$grid.find("tbody div[name='" + name + "']").first();
                /** 用户设置宽度设置 **/
                width && $bodyDivs.css("width", width);
            });

            /** （2） 若表格实际宽度小于容器的宽度,按照单元格所在比例为，单元格填充  **/
            var tableWidth = $("table", this.$grid).outerWidth(); //表格宽度
            var spaceWidth = this.$grid.width() - tableWidth; //空隙宽度
            var leaveSpaceWidth = spaceWidth; //所有已使用的空隙宽度

            if (spaceWidth <= 0)return;//没有空隙不用填充

            $("thead th", this.$grid).each(function (index, th) {
                var name = $(th).attr("name");
                var thWidth = $(th).width();
                var thSpaceWidth = parseInt(spaceWidth * (thWidth / tableWidth));
                var width = thWidth + thSpaceWidth;//根据比例增加空隙
                leaveSpaceWidth -= thSpaceWidth; //累计已使用的空隙宽度
                var $bodyDivs = _this.$grid.find("tbody div[name='" + name + "']").first();
                width && $bodyDivs.css("width", width); //根据比例增加空隙
            });

            /** (3) 剩下的零头加最后可见列  **/
            var $bodyDivs = this.$grid.find("tbody tr").find("td:first>div");
            $bodyDivs.css("width", $bodyDivs.width() + leaveSpaceWidth - 1);
            //this.$grid.find("table.cache-table").css("width", "100%");
        },
        calculateTable: function () {
            var tableWidth = $("table", this.$grid).outerWidth(); //表格宽度
            var spaceWidth = this.$grid.width() - tableWidth; //空隙宽度
            if (spaceWidth <= 0)return;
            this._calculateTable();
        }

    });

    /** 编辑表格  **/
    var EditRender = function ($grid, dpProps) {
        this.$grid = $grid; //表格控件根节点
        this.dpProps = dpProps; //表格控件属性
        this.cmpMap = {};  //字段名和控件对象的键值对
        this.cmpIt = CmpInterface.prototype;
    }
    $.extend(EditRender.prototype, {
        initComponent: function () {
            var thProps = this.dpProps.thProps;
            var $tfoot = this.$grid.find("tfoot");
            var _this = this;
            $.each(thProps, function (index, thProp) {
                var $div = $tfoot.find("tr>td>div[name='" + thProp.name + "']");

                var paramObj = _this.cmpIt.filterProps({$div4foot: $div, props: thProp, $tfoot: $tfoot});//参数组装
                var cmpObj = _this.cmpIt.getCmpObj(paramObj); //获取控件对象
                thProp.onBeforeRender && thProp.onBeforeRender(paramObj);
                var component = cmpObj.component(paramObj);
                thProp.readonly && component.readonly(true);
                thProp.disabled && component.disabled(true);
                thProp.onAfterRender && thProp.onAfterRender($.extend(paramObj, {$cmp: component}));

                _this.cmpMap[thProp.name] = component;
            });
            $tfoot.data("cmpMap", _this.cmpMap);
        },
        hideComponent: function () {
            var $tr = this.$grid.find("tr[edit]");
            var $divs4tfoot = this.$grid.find("tfoot td>div");
            var $divs4tbody = $tr.find("td>div");
            $divs4tbody.each(function (index, div) {
                var $component = $(div).children();
                var $div4tfoot = $divs4tfoot.eq(index);
                $div4tfoot.append($component);
            });
            $tr.removeAttr("edit");
        },
        showComponent: function (id) {
            var thProps = this.dpProps.thProps;
            var $divs4tfoot = this.$grid.find("tfoot td>div");
            var $divs4tbody = this.$grid.find("tbody #" + id).find("td>div");
            $.each(thProps, function (index, thProp) {
                var $div4tbody = $divs4tbody.filter("[name='" + thProp.name + "']");
                var $div4foot = $divs4tfoot.filter("[name='" + thProp.name + "']");
                var $component = $div4foot.children();
                $div4tbody.empty();
                $div4tbody.append($component);
                thProp.onAfterShow && thProp.onAfterShow($div4tbody);
            });
            this.$grid.find("tbody #" + id).attr("edit", "true");
        },
        setValue: function (id, rowData) {
            var _this = this;
            var thProps = this.dpProps.thProps;
            var $tr = this.$grid.find("tbody tr#" + id);
            $.each(thProps, function (index, thProp) {
                var $div = $tr.find("div[name='" + thProp.name + "']");

                var paramObj = _this.cmpIt.filterProps({
                    rowData: rowData,
                    props: thProp,
                    $div4tbody: $div,
                    $cmp: _this.cmpMap[thProp.name],
                    $tfoot: this.$tfoot
                });//参数组装
                var cmpObj = _this.cmpIt.getCmpObj(paramObj); //获取控件对象
                thProp.onBeforeSetValue && thProp.onBeforeSetValue(paramObj);
                var component = cmpObj.setValue(paramObj);
                thProp.onAfterSetValue && thProp.onAfterSetValue(paramObj);
            });
        },
        getValue: function (id) {
            var _this = this;
            var thProps = this.dpProps.thProps;
            var $tr = this.$grid.find("tbody tr#" + id);
            var rowData = {};
            $.each(thProps, function (index, thProp) {
                var name = thProp.name;
                var $div = $tr.find("div[name='" + name + "']");

                var paramObj = _this.cmpIt.filterProps({props: thProp, $div4tbody: $div, $cmp: _this.cmpMap[name]});//参数组装
                var cmpObj = _this.cmpIt.getCmpObj(paramObj); //获取控件对象
                thProp.onBeforeGetValue && thProp.onBeforeGetValue(paramObj);
                var value = cmpObj.getValue(paramObj);
                thProp.onAfterGetValue && thProp.onAfterGetValue(paramObj);
                rowData[name] = value;
            });
            return rowData;
        },
        validateForm: function () {
            var $form = this.$grid.find("table>tbody");
            if (!$form.attr("validate")) {
                $form.validationEngine();
                $form.attr("validate", true);
            }
            var jqv = $form.closest("form").data("jqv") || eval("({" + ($form.closest("form").attr("dpProps") || "") + "})") || {};
            if (!$form.validationEngine("validate", {showMode: jqv.showMode, offsetX: 5})) {
                $form.clearPromptText();
                $form.find(".dropDownRadioBox").attr("validate", true);
                $form.find(".dropDownBox").attr("validate", true);
                $form.formValidatePosDp();
                return false;
            }
            return true;
        },
        closePrompt: function () {
            var $form = this.$grid.find("table>tbody");
            $form.validationEngine("hideAll");
            return true;
        },
        getEditRowId: function () {
            return this.$grid.find("tr[edit]").attr("id");
        },
        getCmpObj: function () {
            return this.$grid.find("tfoot").data("cmpMap");
        }
    });


    var defaultSetting = {
        url: "",
        param: "",
        callback: null,
        pageNumber: 1,
        pageSize: 10,//每页显示数量，默认10
        pageSizeOption: [10, 20, 30, 40, 50], //下拉框每页显示数量设置
        rowNumbers: true, //是否显示行序号，默认显示
        operateCol: true,//是否显示操作列，默认显示
        operateTh: '<a href="#" class="a-icon a-add" title="添加" id="add4cacheGrid"></a>', //操作列的th
        onOperateRender: "", //操作列的td
        onOperateText: "",
        rowDraggable: false,//是否允许行拖拽，默认false
        usePager: true,//是否显示分页，默认显示 todo 增加分页栏控制

        onBeforeAdd: null,
        onBeforeEdit: null,//编辑前事件，返回当前行数据对象。return false 不执行表格编辑操作；true 继续表格编辑操作
        onBeforeSave: null, //保存前事件，返回当前行数据对象。return false 不执行表格保存操作；true 继续表格保存操作
        onBeforeDelete: null,//删除前事件，返回当前行数据对象。return false 不执行表格删除操作；true 继续表格删除操作
        onBeforeCancel: null //撤销前事件，返回当前行数据对象。return false 不执行表格撤销操作；true 继续表格撤销操作
    }
    /** 编缓存编辑表格主类  **/
    var CacheGrid = function ($grid) {
        /** 表格外部传参数 **/
        var outerProps = $grid.initPluginDp(["name"]);
        this.outerProps = $.extend({}, defaultSetting, outerProps);

        /** 表格列外部传参数 **/
        this.outerProps.thProps = this.initThProps($grid);

        /** 外部参数转内部参数 **/
        this.innerProps = this.outerProps2innerProps(this.outerProps);

        /** table替换为div **/
        this.$grid = $($domNodeHtml);
        $grid.replaceWith(this.$grid);

        this.gridRender = new GridRender(this.$grid, this.innerProps);
        this.editRender = new EditRender(this.$grid, this.innerProps);
        this.dataStore = new DataStore(this.$grid, this.innerProps);
        this.dragRender = new DragRender(this.$grid, this.innerProps);
    }
    $.extend(CacheGrid.prototype, {

        /** 对象入口函数：
         * （1）创建html结构
         * （2）根据buisinessId，businessType请求文件数据
         * （3）根据属性创建webUploader对象
         * （4）绑定事件，回调外部接口
         * （5）加入对象
         * （6）增加返回对象，函数提供外部调用
         * **/
        main: function () {
            this.initStruct();//（1）创建html基本结构
            this.bindEvent();//（2）绑定事件，回调外部接口
            this.requestData();//（3）初始化请求数据
            this.afterInit();//（4）渲染后执行
            this.addReturnFn();//（5）增加返回对象，函数提供外部调用
            return this.$grid;
        },
        initThProps: function ($grid) {
            var uniqueObjects = [];
            var thProps = [];
            $grid.find("thead th").each(function (index, th) {
                var thProp = $(th).initPluginDp(["name", "readonly", "disabled",
                    "width", "jclass", "plugin", "props", "maxLength", "type", "html"]);
                if (thProp.plugin) {
                    thProp.plugin = eval(thProp.plugin);
                }
                thProp.text = $(th).html();
                if (!thProp.plugin && !thProp.type)
                    thProp.readonly = "true";
                if (thProp.hasOwnProperty("props")) {
                    $.extend(thProp, eval("({" + (thProp.props || "") + "})"));
                    delete thProp.props;
                }
                thProps.push(thProp);

                if (thProp.type == "radio") {
                    var uniqueObj = {
                        name: thProp.name,
                        check: thProp.hasOwnProperty("check") ? thProp.check : "1",
                        uncheck: thProp.hasOwnProperty("uncheck") ? thProp.uncheck : "0"
                    };
                    uniqueObjects.push(uniqueObj);
                }

            });
            this.outerProps.uniqueObjects = uniqueObjects;
            if (this.outerProps.operateCol) {
                if (this.outerProps.hasOwnProperty("isDelete") && !this.outerProps.isDelete) {
                    this.outerProps.onOperateRender = function (obj) {
                        obj.$div4foot.find("a[id^=delete]").remove();
                    }
                    this.outerProps.onOperateText = function (obj) {
                        obj.$div4tbody.find("a[id^=delete]").remove();
                    }
                }
                thProps.push({
                    "width": "100",
                    "name": "cache-grid-op-col",
                    "type": "edit",
                    "text": "操作列",
                    "html": this.outerProps.operateTh,
                    "onAfterSetText": this.outerProps.onOperateText,
                    "onAfterRender": this.outerProps.onOperateRender
                });
            }
            return thProps;
        },
        /** 外部参数统一转化为 内部可使用的参数  **/
        outerProps2innerProps: function (outerProps) {
            var innerProps = {};
            /** 外部统一接口，转化为内部可用接口 todo **/
            innerProps = {};

            return outerProps;
        },

        /** 初始化控件渲染的html结构 **/
        initStruct: function () {
            this.$grid.attr("name", this.innerProps.name); //把未渲染前table的name值移到现在div上
            this.gridRender.initTable();  //创建表的基本结构
            this.editRender.initComponent(); //创建编辑控件
        },

        /** 根据业务id和业务类型，向后台请求数据 **/
        requestData: function () {
            this.gridRender.showRow4NoneData();
            this.dataStore.load();
        },

        /*初始化上传事件*/
        bindEvent: function () {
            var _this = this;

            /** 1 表格展示：改变dataStore的分页和数据信息，触发显示事件，通过监听显示事件，调用进行显示 **/
            this.$grid.on("viewChange", function (evt, data, pageData) {
                if (!_this.innerProps.usePager) {
                    _this.gridRender.hidePage();
                } else {
                    _this.gridRender.showPage(pageData);
                }
                _this.gridRender.showRows(data);
            });

            /** 2 表格分页：通过捕获分页按钮的事件，隐藏编辑框，改变dataStore的分页数据，触发显示事件 **/
            this.$grid.on("click", "#pagePanel4cacheGrid span", function (evt, data, pageData) {
                var $target = $(evt.target);
                _this.editRender.hideComponent();
                if (0 <= $target.attr("id").indexOf("first")) {
                    _this.dataStore.first();
                } else if (0 <= $target.attr("id").indexOf("last")) {
                    _this.dataStore.last();
                } else if (0 <= $target.attr("id").indexOf("prev")) {
                    _this.dataStore.prev();
                } else if (0 <= $target.attr("id").indexOf("next")) {
                    _this.dataStore.next();
                } else if (0 <= $target.attr("id").indexOf("load")) {
                    _this.dataStore.load();
                }
            });
            this.$grid.on("blur", "#pagePanel4cacheGrid input", function (evt) {
                var $target = $(evt.target);
                _this.editRender.hideComponent();
                if (0 <= $target.attr("id").indexOf("goto")) {
                    _this.dataStore.goTo(parseInt($target.val()));
                }
            });
            this.$grid.on("change", "#pagePanel4cacheGrid select", function (evt) {
                var $target = $(evt.target);
                _this.editRender.hideComponent();
                _this.dataStore.changePageSize(parseInt($target.val()));
            });

            /** 3 表格大小改变响应  **/
            $(window).on(DWZ.eventType.resizeGrid, {$gird: this.$grid}, function (evt) {
                if (evt.data.$gird.closest("body").length <= 0) {
                    $(window).off(DWZ.eventType.resizeGrid, arguments.callee);
                    return;
                }
                _this.gridRender.calculateTable();
            });

            /** 4 编辑表格：增、删、改、保存、取消 **/
            this.$grid.on("click", "table a", function (evt, data, pageData) {
                var $target = $(evt.target);
                var id = $target.closest("tr").attr("id");
                if (0 <= $target.attr("id").indexOf("edit")) {
                    _this._editRow(id);
                } else if (0 <= $target.attr("id").indexOf("save")) {
                    _this._saveRow(id);
                } else if (0 <= $target.attr("id").indexOf("delete")) {
                    _this._deleteRow(id);
                } else if (0 <= $target.attr("id").indexOf("cancel")) {
                    _this._cancelRow(id);
                } else if (0 <= $target.attr("id").indexOf("add")) {
                    _this._addRow();
                }
            });


        },

        /*初始化完成操作*/
        afterInit: function () {
            this.$grid.get(0).checked = true;  //表单提交防止被过滤
            this.$grid.find("input").attr("editFormFlag", "true");//提交数据过滤编辑表格里面的编辑控件

            /** 获取数据时调用的接口 **/
            this.$grid.data("editGrid", this.$grid);
            this.$grid.dataObj = this.dataStore;

            /** 设置拖动行 **/
            this.innerProps.rowDraggable && this._bindRowDrag();
        },

        /** 增加返回函数 **/
        addReturnFn: function () {
            var _this = this;
            this.$grid.load = function (param, url, callBack) {
                _this.editRender.hideComponent();
                _this.dataStore.load(param, url, callBack);
            };
            this.$grid.addRow = function (rowData) {
                _this.dataStore.addRow(rowData);
            };
            this.$grid.updateRow = function (rowData, key) {
                _this.dataStore.updateRow(rowData, key);
            };
            this.$grid.getRowData = function (id) {
                return _this.dataStore.getRow(id);
            };
            this.$grid.deleteRow = function (id) {
                return _this.dataStore.deleteRow(id);
            };
            this.$grid.findRows = function (conditions) {
                return _this.dataStore.findRows(conditions);
            };
            this.$grid.getCmp = function() {
                return _this.editRender.cmpMap;
            }
        },

        _bindRowDrag: function () {
            var callback = function (obj) {
                var editId = this.editRender.getEditRowId();
                this.editRender.hideComponent();
                this.dataStore.changeIndex(obj.id, obj.index);
                this.editRender.showComponent(editId);
            }
            this.dragRender.bindRowDrag($t.fun.hitch(this, callback));
        },
        _editRow: function (id) {
            /** （0）编辑前 **/
            if (this.innerProps.onBeforeEdit) {
                var result = this.innerProps.onBeforeEdit(this.dataStore.getRow(id));
                if (result)return false;
            }
            /** （1）验证，保存，编辑行 **/
            if (!this._saveRow())return false;
            /** （2）从前端缓存中，获取行数据 **/
            var rowMap = this.dataStore.getRow(id);
            /** （3）显示行组件 **/
            this.editRender.showComponent(id);
            /** （4）为行组件塞值 **/
            this.editRender.setValue(id, rowMap.value);
            /** （5）重新计算 **/
            this.gridRender.calculateTable();
        },
        _deleteRow: function (id) {
            this.editRender.closePrompt();
            /** （1）取出编辑行id**/
            var editId = this.editRender.getEditRowId();
            /** （2）隐藏编辑行 **/
            this.editRender.hideComponent();
            /** （3）从前端缓存中删除数据 **/
            this.dataStore.deleteRow(id);
            /** （4）显示之前编辑行 **/
            editId && this.editRender.showComponent(editId);
            /** （5）重新计算 **/
            this.gridRender.calculateTable();
        },
        _saveRow: function (id) {
            if (!id)
                id = this.editRender.getEditRowId();
            /** （1）验证是否通过 **/
            if (!this.editRender.validateForm())return false;
            /** （2）取行控件数据 **/
            var rowData = this.editRender.getValue(id);
            /** （3）隐藏组件 **/
            this.editRender.hideComponent();
            /** （4）更新缓存数据 **/
            this.dataStore.updateRow(rowData, id);

            /** （5）重新计算 **/
            this.gridRender.calculateTable();

            return true;
        },
        _cancelRow: function (id) {
            /** （1）取数据回塞 **/
            var rowMap = this.dataStore.getRow(id);
            this.editRender.setValue(id, rowMap.value);
            /** （2）验证 **/
            if (!this.editRender.validateForm())return false;
            /** （3）隐藏组件 **/
            this.editRender.hideComponent();
            /** （4）刷新 **/
            this.dataStore.refresh();
            /** （5）重新计算 **/
            this.gridRender.calculateTable();
        },
        _addRow: function () {
            /** （0）添加前 **/
            if (this.innerProps.onBeforeAdd) {
                var result = this.innerProps.onBeforeAdd();
                if (result)return false;
            }

            /** （1）验证，保存，编辑行 **/
            if (!this._saveRow())return false;
            /** （2）隐藏组件 **/
            this.editRender.hideComponent();
            /** （3）新增数据 **/
            var rowMap = this.dataStore.addRow({});
            /** （4）显示行组件 **/
            this.editRender.showComponent(rowMap.key);
            /** （5）为行组件塞值 **/
            this.editRender.setValue(rowMap.key, rowMap.value);
            /** （6）重新计算 **/
            this.gridRender.calculateTable();
        }
    });

    $.fn.cacheGridDp = function ($grid) {
        var cacheGrid = new CacheGrid($grid);
        cacheGrid.main();
        return cacheGrid.$grid;
    }

})
(jQuery, window, template);
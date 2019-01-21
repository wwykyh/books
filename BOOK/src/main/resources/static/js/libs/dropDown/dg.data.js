define(['jquery'],function($) {
    var dataStore = {};
    // 传入 props Array
    var getKey = function(props) {
        if (props.code)
            return "code_" + props.code;
        if (props.sql)
            return "sql_" + props.sql;
        if (props.enumName)
            return "enum_" + props.enumName;
        if (props.store)
            return "store_" + props.store;
        return '';
    }
    // 获取路径
    var getUrl = function(props) {
        return props.url;
    }
    // 获取数据值，回调返回 data 数据
    $.fn.extend({
        getDataStore: function(props, callBack) {
            var key = getKey(props);
            var url = getUrl(props);
            var loadCache = props.hasOwnProperty("loadCache") ? (props.loadCache) : true; // 增加loadCache参数，是否加载缓存
            if (key && dataStore[key] && loadCache) {
                callBack && callBack(dataStore[key]);
                return dataStore[key];
            }
            if ("resultData" in props) {
                callBack && callBack(props["resultData"]);
                return props["resultData"];
            }
            $.getJSON($.fn.contextPath || "" + url, {}, function(data) {
                if (props.defaultData) {
                    data.splice(0, 0, props.defaultData);
                }
                dataStore[key] = data;
                callBack && callBack(data);
            });
        },
        isExistDataStore: function(props) {
            var key = getKey(props);
            var isExist = true;
            if (!dataStore[key]) isExist = false;
            return isExist;
        },
        setDataStore: function(props, data) {
            var key = getKey(props);
            dataStore[key] = data;
        }
    });
});

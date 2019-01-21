(function($){

    var rCRLF = /\r?\n/g,
        rselectTextarea = /^(?:select|textarea|table)/i,
        rinput = /^(?:color|date|datetime|datetime-local|email|hidden|month|number|password|range|search|tel|text|time|url|week)$/i;
    var _filterForm=function(form){
        var elements=form.find("input[name],textArea[name],select[name],table[name]," +
                "div.webUploader[name],div.edit-grid[name]");
        return form.map(function(){
            return elements ? jQuery.makeArray( elements ) : form;
        })
            .filter(function(){
                return $(this).attr("name") && !this.disabled && !$(this).attr("editFormFlag") &&
                    ( this.checked || rselectTextarea.test( this.nodeName ) ||
                    rinput.test( this.type ) );
            });
    }
    //获取表单对象
    $.fn.getFormObject=function(form){
        var formObject=[];
        var filterForm=_filterForm(form);
        filterForm.map(function( i, elem ){
            var dateFormat=$(elem).attr("dateFormat")?$(elem).attr("dateFormat"):"";
            var op =$(elem).attr("op")?$(elem).attr("op"):"=";
            var type = elem.type;
            var tag = elem.tagName.toLowerCase();
            elem.value=elem.value.trim();//yangjy 2015-11-6 两边去空
            if(type == 'text' || type == 'password' || tag == 'textarea'
                ||type == 'checkbox' || type == 'radio'||type == 'hidden'){
                var displayType=$(elem).attr("displayType")?$(elem).attr("displayType"):"";
                var value=($(elem).hasClass("Wdate"))?(elem.value.replace(/[^0-9]/ig,"")):(elem.value);
                if($(elem).attr("prompt")&&$(elem).hasClass("watermark")){
                    return formObject.push({"name": elem.name, "op": op, "value": "","displayType":displayType,"dateFormat":dateFormat}); //移除水印字样属性
                }
                if(!elem.value) return;
                var condition = {"name": elem.name, "op": op, "value": value,"displayType":displayType,"dateFormat":dateFormat};
                formObject.push(condition);
            }else if(tag=='select'){
                if(!elem.value) return;
                var value=elem.value;
                //todo select第一个选项判断 ?
                if(value==$(elem).children()[0].value) return;
                var displayType=$(elem).attr("displayType")?$(elem).attr("displayType"):"CHAR";
                var condition = {"name": elem.name, "op": op, "value": value,"displayType":displayType,"dateFormat":dateFormat};
                formObject.push(condition);
            }
        });
        return formObject;
    };

    $.fn.clearPromptText=function(){
        $("input[prompt]",this).each(function(){
            if($(this).attr("prompt")&&$(this).hasClass("watermark")){
                $(this).val("");
                $(this).removeClass(".watermark");
            }
        });
        $(".dropDownRadioBox>.ffb-input",this).each(function(){
            if($(this).hasClass("watermark")) {
                $(this).val("");
                $(this).removeClass(".watermark");
            }
        });
    }
    //表单塞值
    $.fn.setFormObject=function(form,data){
        var elements=form.find("input[name],textArea[name],select[name]");
        return elements
            .map(function( i, elem ){
                var type = elem.type;
                var tag = elem.tagName.toLowerCase();
                if(!(elem.name in data))return;//yangjy 2015-11-6  字段名不在数据中，不操作
                var value=data[elem.name]; // 排除value=0，不存在影响
                if(!value&&value!=0) value="";
                if(type == 'text' || type == 'password' || tag == 'textarea'||tag=='select'){
                    //var value=(data[elem.name])?(data[elem.name]):"";
                    if(($(elem).attr("dptype")=="dropDownRadio")||($(elem).attr("dptype")=="dropDownTree")){
                        $(elem).data($(elem).attr("dptype")).setValue(value);
                        return;
                    }
                    elem.value=value.toString();
                }else if(type == 'checkbox' || type == 'radio'){
                    if(!data[elem.name]) return
                    //  checkbox 多选 todo radio 要不要互斥
                    var _value=data[elem.name].split(",");
                    for(var i=0;i<_value.length;i++){
                        (elem.value==_value[i])&&(elem.checked = true);
                    }
                }
            });
    }

    //表单重置
    $.fn.resetFormObject=function(form){
        if(!form)form=this;
        var elements=form.find("input[name],textArea[name],select[name]");
        return form.map(function(){

            return elements ? jQuery.makeArray( elements ) : form;
        })
            .filter(function(){
                return this.name && !this.disabled && !(this.type=="hidden") &&
                    ( this.checked || rselectTextarea.test( this.nodeName ) ||
                    rinput.test( this.type ) );
            })
            .map(function( i, elem ){
                var type = elem.type;
                var tag = elem.tagName.toLowerCase();
                if(type == 'text' || type == 'password' || tag == 'textarea'){
                    // todo  $(elem).attr("dptype") 改为 elem.dptype
                    if(($(elem).attr("dptype")=="dropDownRadio")||($(elem).attr("dptype")=="dropDownTree")){
                        $(elem).data($(elem).attr("dptype")).setValue();
                        return;
                    }
                    elem.value="";
                }else if(type == 'checkbox' || type == 'radio'){
                    elem.checked = false;
                }else if (tag == 'select'){
                    elem.selectedIndex = -1;
                }
            });
    };

    //表单提交 todo 值为空时，是否提交
    $.fn.getFormArray=function(form,param) {
        var filterForm=_filterForm(form);
        return filterForm
            .map(function( i, elem ){
                var val = $(this).val();
                if($(this).attr("prompt")&&$(this).hasClass("watermark")) return { name: elem.name, value: ""}; //移除水印字样属性

                var tag = elem.tagName.toLowerCase();
                if(tag=='select'){
                    if(!elem.value) return null;
                    val=elem.value;
                    if(val==$(elem).children()[0].value) return null;
                    return { name: elem.name, value: val.replace( rCRLF, "\r\n" ) };
                }else if($(elem).hasClass("webUploader")){
                    var data=  $(elem).data("postData"),postData=[];
                    for(var i in data){postData.push(data[i]);}
                    //if(!postData||postData.length<=0)postData=$(elem).data("_postData");
                    return  { name: $(elem).attr("name"), value:  JSON.stringify( postData)  };
                }else if($(elem).hasClass("edit-grid")){
                    var editGrid=$(elem).data("editGrid"),
                        data=editGrid.dataObj.transformData4Submit();
                    return  { name: $(elem).attr("name"), value:  JSON.stringify( data)  };
                }
                $(elem).hasClass("Wdate")&&(val=val.replace(/[^0-9]/ig,"")); //日期控件 value值去掉分隔符
                return val == null ?
                    null :
                    jQuery.isArray( val ) ?
                        jQuery.map( val, function( val, i ){
                            return { name: elem.name, value: val.replace( rCRLF, "\r\n" ) };
                        }) :
                    { name: elem.name, value: val.replace( rCRLF, "\r\n" ) };
            }).get();
    };

})(jQuery);
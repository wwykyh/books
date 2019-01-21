function cookie(name){  
   var cookieArray=document.cookie.split("; "); //得到分割的cookie名值对   
   var cookie=new Object();   
   for (var i=0;i<cookieArray.length;i++){  
      var arr=cookieArray[i].split("=");//将名和值分开
      if(arr[0]==name)return unescape(arr[1]); //如果是指定的cookie，则返回它的值 
   } 
   return ""; 
} 

function getCookie(objName){//获取指定名称的cookie的值
    var arrStr = document.cookie.split("; ");
    for(var i = 0;i < arrStr.length;i ++){
        var temp = arrStr[i].split("=");
        if(temp[0] == objName) return unescape(temp[1]);
   } 
}

function delCookie(name)//删除cookie
{ 
  document.cookie = name+"=;expires="+(new Date(0)).toGMTString(); 
}

//mouseover mouseout 冒泡
function isMouseLeaveOrEnter(e, handler) {
  if (e.type != 'mouseout' && e.type != 'mouseover') return false;
  var reltg = e.relatedTarget ? e.relatedTarget : e.type == 'mouseout' ? e.toElement : e.fromElement;
  while (reltg && reltg != handler)
      reltg = reltg.parentNode;
  return (reltg != handler);
}

/*** window onload 监听事件***/
function addLoadEvent(obj,func) {
    var ages = [].slice.call(arguments,1);
    var oldonload = obj.onload;
    if (typeof obj.onload != 'function') {
        obj.onload = function() {
            func.apply(this,ages);
        }
    } else {
        obj.onload = function() {
            oldonload.apply(this);
            func.apply(this,ages);
        }
    }
}

/***resize 监听事件***/
function addResizeEvent(func) {
    var ages = [].slice.call(arguments,1);
    var oldOnresize = window.onresize;
    if (typeof window.onresize != 'function') {
        window.onresize = function() {
            func.apply(this,ages);
        }
    } else {
        window.onresize = function() {
            oldOnresize.apply(this);
            func.apply(this,ages);
        }
    }
}

// 全局变量
window.gFrameHeight = 0; 
window.gScreenHeight = 0;


/** iframe 高度计算（改造使之适应tab的高度 tangdm）  **/
function frameHeight(options){
  var option = $.extend({
        header:"#header", 
        content:"#main", 
        breadcrumb:".breadcrumb",
        sidebar:"#sidebar",
        frame:"#main-content",
        tab:"#main-tab",
        footer:"#footer",
        sidebarFixed:false, //左边栏是否固定
        footerFixed:false, //底部是否固定
  }, options);
  var headerHeight = $(option.header).outerHeight(true);
  var tabHeight = $(option.tab).outerHeight(true);
  var footerHeight = $(option.footer).outerHeight(true);
  var sHeight = document.documentElement.clientHeight;
  var bHeight = ($(option.frame).length > 0)? $(option.frame).height():0;
  bHeight = (bHeight)? bHeight:0;
  if(bHeight == gFrameHeight && gScreenHeight == sHeight) return;
  if($(option.sidebar).length > 0) {
    height = Math.max(bHeight, sHeight - headerHeight);
    if(option.sidebarFixed) {// 固定左侧栏 上左右结构
      // console.log('固定左侧栏 上左右结构');
      $(option.sidebar).css("height", sHeight - headerHeight + "px");
      $(option.content).css({"height": sHeight - headerHeight + "px", "overflowX": "hidden", "overflowY": "auto"});
      $(option.frame).css("height", height - tabHeight  + "px");
    } else {// 左右结构
      // console.log('左右结构');
      $(option.sidebar).css("min-height",height + "px");
      $(option.content).css("height", height + "px");
    }
  } else {
    var crumbHeight = ($(option.breadcrumb).length > 0)? $(option.breadcrumb).outerHeight():0;
    height = Math.max(bHeight,sHeight - headerHeight);
    if(option.footerFixed) {// 底部固定 上下结构 底部暂时无法使用
      // console.log('底部固定 上下结构 底部暂时无法使用');
      $(option.content).css("height", sHeight - headerHeight - footerHeight - crumbHeight - 5);
    } else {// 上下结构
      // console.log('上下结构');
      $(option.content).css({"height":sHeight - headerHeight - crumbHeight + "px", "overflowX": "hidden", "overflowY": "auto"});
      $(option.frame).css("height",height);
    }
  }
  gFrameHeight = bHeight;
  gScreenHeight = sHeight;
}

/*=========================系统日期=========================*/
function sysTime(options){
  var option = $.extend({
        isShowWeek:false
  }, options);
  function data(){ 
    var d=new Date(),str=''; 
    str +=d.getFullYear()+'年'; //获取当前年份 
    str +=d.getMonth()+1+'月'; //获取当前月份（0——11） 
    str +=d.getDate()+'日'; 
    return str; 
  } 
  function week(){
    var arr = new Array("日", "一", "二", "三", "四", "五", "六");  
      var week = new Date().getDay(); 
      return "星期" + arr[week];
  }
  function time(){
    var d=new Date();
    var hours=d.getHours();
      var minutes = d.getMinutes()>9?d.getMinutes().toString():'0' + d.getMinutes();
      var seconds = d.getSeconds()>9?d.getSeconds().toString():'0' + d.getSeconds();
      var str = hours + ':' + minutes + ':' + seconds;
      return str;
  }
  setInterval(function(){
    if(option.isShowWeek){
      $("#nowTime").children(".data").html(data() + "&nbsp&nbsp" + week());
    }else{
      $("#nowTime").children(".data").html(data);
    }
  },1000);
}

//表格hover
function trHover(tableObj,hoverClass){
  tableObj.on("mouseover mouseout","tbody tr", function(event){
    if(event.type == 'mouseover'){
      $(this).addClass(hoverClass);
    }else if(event.type == 'mouseout'){
      $(this).removeClass(hoverClass);
    }
  })
}
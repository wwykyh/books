/** 
 * RequireJs 全局配置
 * @param baseUrl 基本路径
 * @param paths 指定的路径名称及声明 baseURL 以外的文件路径
 * @param deps 建立依赖，在配置加载后开始加载文件
 * @param map 指定固定的路径
 * @param shim 声明第三方插件的相互依赖关系
 * @param packages 第三方插件（支持依赖包的关系）
 * @author DaMin
 **/
requireManage.config({
	paths: {
		// 系统结构
		'basic-frame': './basic/basic-frame',
		'basic-global': './basic/basic-global',
		'jquery.extend': './basic/jquery.extend',
		'prettify': './tools/prettify',
		'html5': './tools/html5',
		// 'respond': './tools/respond.min',
		'PIE_IE678': './tools/PIE_IE678',
		'jquery.cookie': './tools/jquery.cookie',
		'jquery.nicescroll': './tools/jquery.nicescroll',

		// 下拉框/下拉树 组件
		'phoneticize': './libs/dropDown/phoneticize',
		'jquery.flexbox': './libs/dropDown/jquery.flexbox',
		'dg.data': './libs/dropDown/dg.data',
		'dg.flexbox': './libs/dropDown/dg.flexbox',
		'dg.dropDownTree': './libs/dropDown/dg.dropDownTree',
		
		// 编辑器
		'bdeditor': './libs/ueditor/bdeditor.min',
		'bdlang': './libs/ueditor/lang/zh-cn/zh-cn',
		'zeroclipboard': './libs/ueditor/third-party/zeroclipboard/ZeroClipboard.min',

		// 弹出层
		'iDialog': './libs/iDialog/jquery.iDialog',
		'poshytip': './libs/poshytip/poshytip',

		// 双向选择器
		'jquery.multiselect': './libs/multiselect2side/jquery.multiselect2side',
		'dg.multiselect': './libs/multiselect2side/dg.multiselect2side',

		// 日历
		'ui.calendar': './libs/calendar/ui.calendar',
		'calendar.core': './libs/calendar/calendar.core',
		'calendar.slide': './libs/calendar2/calendar.slide',
		'dg.timeaxis': './libs/timeaxis/dg.timeaxis',

		// 表单
		'iCheck': './libs/iCheck/jquery.icheck.min',
		'cityContract': './libs/cityContract/jquery.cityContract',
		'cityArea': './libs/cityArea/jquery.cityArea',
		'selectDrop': './libs/selectDrop/jquery.selectDrop',
		'jquery.form': './libs/form/jquery.form',

		// 地图
		'mapTaggle': './libs/maptaggle/maptaggle',
		'highcharts': './libs/highcharts/highcharts',

		// d3 组件
		'd3.v3': './libs/d3/d3.v3.min',
		'd3.force': './libs/d3/d3.force'
	},
	shim: {
		// 系统结构
		'jquery.extend': {
			deps: ['jquery', 'jquery.cookie'],
		},
		'jquery.cookie': {
			deps: ['jquery'],
		},

		// 编辑器
		'bdeditor': {
			deps: ['./libs/ueditor/ueditor.config', 'css!./libs/ueditor/themes/default/css/ueditor'],
		},
		'bdlang': {
			deps: ['bdeditor']
		},

		// 日历
		'ui.calendar': {
			deps: ['calendar.core']
		},
		'calendar.slide': {
			deps: ['css!./libs/calendar2/style.css']
		},
		'dg.timeaxis': {
			deps: ['css!./libs/timeaxis/css/history.css']
		},

		// 表单
		'iCheck': {
			deps: ['jquery'],
		},
		'cityContract': {
			deps: ['css!./libs/cityContract/cityContract.css']
		},
		'cityArea': {
			deps: ['css!./libs/cityArea/cityArea.css']
		},
		'selectDrop': {
			deps: ['css!./libs/selectDrop/selectDrop.css']
		},
		'jquery.form': {
			deps: ['css!./libs/form/form.css']
		},

		// 地图
		'mapTaggle': {
			deps: ['css!./libs/maptaggle/maptaggle.css']
		}
	}
});
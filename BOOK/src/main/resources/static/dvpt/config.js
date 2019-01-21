//requireJS会自动加载全局变量名为require的配置
var require = {
	baseUrl: './js/',
	paths: {
		// jquery
		'jquery': '../dvpt/jquery.min',

		// 弹出层
		'artdialog': '../dvpt/libs/artdialog/artdialog',
		'layer': '../dvpt/libs/layer/layer',

		// 日期选择控件
		'dg.datePicker': '../dvpt/libs/My97DatePicker/dg.datePicker',

		// ligerGrid 组件
		'ligerGrid.base': '../dvpt/libs/ligerGrid/base',
		'ligerGrid': '../dvpt/libs/ligerGrid/ligerGrid',
		'ligerTextBox': '../dvpt/libs/ligerGrid/ligerTextBox',
		'ligerCheckBox': '../dvpt/libs/ligerGrid/ligerCheckBox',
		'ligerComboBox': '../dvpt/libs/ligerGrid/ligerComboBox',
		'ligerDateEditor': '../dvpt/libs/ligerGrid/ligerDateEditor',

		// 上传组件
		'webuploader': '../dvpt/libs/uploader2.0/webuploader',
		'dg.uploader': '../dvpt/libs/uploader2.0/uploader',
		'upload': '../dvpt/libs/uploader2.0/upload',
		'lrscroll': '../dvpt/libs/lrScroll/lrscroll',
		'jquery.lightbox': '../dvpt/libs/lrScroll/jquery.lightbox-0.5',
		'dragon.lrscroll': '../dvpt/libs/lrScroll/dragon.lrscroll',

		// ztree 组件
		'jquery.ztree': '../dvpt/libs/ztree/jquery.ztree.all.min',
		'box.zTree': '../dvpt/libs/ztree/box.zTree',

		// echarts2.0 组件
		// 'echarts2.simple': '../dvpt/libs/echarts2.0/echarts.simple.min',
		// 'echarts2.common': '../dvpt/libs/echarts2.0/echarts.common.min',
		// 'echarts2.all': '../dvpt/libs/echarts2.0/echarts.all.min',
	},
	deps: ["jquery"],
	map: {
		'*': {
			'css': '../dvpt/plugins/css.min',
			'text': '../dvpt/plugins/text',
			'domReady': '../dvpt/plugins/domReady'
		}
	},
	shim: {
		'ligerGrid': {
			deps: ['ligerGrid.base'],
		},
		'webuploader': {
			deps: ['css!../dvpt/libs/uploader2.0/css/webuploader', 'css!../dvpt/libs/uploader2.0/css/webuploader1']
		},
		'dg.datePicker': {
			deps: ['css!../dvpt/libs/My97DatePicker/skin/WdatePicker.css']
		},
		'layer': {
			deps: ['css!../dvpt/libs/layer/skin/layer.css']
		},
	},
	// packages: [{
	// 	name: 'echarts',
	// 	location: '../dvpt/libs/echarts',
	// 	main: 'echarts'
	// }, {
	// 	name: 'zrender',
	// 	location: '../dvpt/libs/zrender',
	// 	main: 'zrender'
	// }]
};

var requireManage = (function() {

	return {
		/**
		 * 各页面自定义require配置接口
		 */
		config: function(ops) {
			var paths = ops.paths || {};
			var deps = ops.deps || [];
			var shim = ops.shim || {};
			for (var p in paths) {
				require.paths[p] ? "" : require.paths[p] = paths[p];
			}
			for (var p in shim) {
				require.shim[p] = shim[p];
			}
			require.deps = require.deps.concat(deps);
		}
	}
})();


/**
 * ReaquireJs 主入口
 * @message ReaquireJs 首页调用
 */

// 全局配置
requirejs(['jquery', 'jquery.extend', 'basic-global'], function(jquery, extend, basic) {
	//系统时间设置  isShowWeek：是否显示周
	sysTime();

	// IE兼容性处理
	if ($('html.lt-ie9').size()) {
		// requirejs(['html5', 'respond', 'PIE_IE678'], function(html5, respond, PIE_IE678) {
		// 	// console.log('html5/respond/PIE_IE678');
		// });
	}

	$(function() {
		// 默认加载页面
		$.openWindow({ url: 'home.html' });

		// 换肤操作
		$.switchSkin('css');
	});
});


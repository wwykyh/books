requirejs(['jquery','jquery.extend','prettify'],function(jquery,extend,prettify) {
	$(function() {
		$("pre").addClass("prettyprint");
		prettyPrint();
	});
});
define(['jquery','d3.v3'], function($) {
	$.fn.extend({

		forceMap: function(options) {
			var defaults = {
				nodes: [], // 节点数据 [{name: xx}]
				edges: [], // 关系数据 [{source: 0, target: 1}]
				width: 200, // 宽度 默认 200
				height: 200, // 高度 默认 200
				contain: "body", // 容器 默认
				linkDistance: 150, // 连线的长度 默认 150

			}
			// 扩展
			var options = $.extend(defaults, options);
			// 制定画布
			var svg = d3.select(options.contain)
						.append("svg")
						.attr("width", options.width)
						.attr("height", options.height);
			// 力关系作用
			var force = d3.layout.force()
							.nodes(options.nodes) //指定节点数组
							.links(options.edges) //指定连线数组
							.size([options.width, options.height]) //指定范围
							.linkDistance(options.linkDistance) //指定连线长度
							.charge(-400) //相互之间的作用力
							.start(); // 开始执行
			// 添加连线		
			var svg_edges = svg.selectAll("line")
								.data(options.edges)
								.enter()
								.append("line")
								.style("stroke", "#ccc")
								.style("stroke-width", 1);
			// 色系
			var color = d3.scale.category20();

			// 添加节点
			var svg_nodes = svg.selectAll("circle")
								.data(options.nodes)
								.enter()
								.append("circle")
								.attr("r", 25)
								.style("fill", function(d, i) {
								    return color(i);
								})
								.call(force.drag); // 执行拖拽
			// 添加文字描述
			var svg_texts = svg.selectAll("text")
								.data(options.nodes)
								.enter()
								.append("text")
								.style("fill", "white")
								.attr("dx", -12.5)
								.attr("dy", 4)
								.text(function(d) {
								    return d.name;
								});
			// 对于每一个时间间隔
			force.on("tick", function() {
				//更新连线坐标
				svg_edges.attr("x1", function(d) {
					return d.source.x;
				})
				.attr("y1", function(d) {
					return d.source.y;
				})
				.attr("x2", function(d) {
					return d.target.x;
				})
				.attr("y2", function(d) {
					return d.target.y;
				});

				// 更新节点坐标
				svg_nodes.attr("cx", function(d) {
					return d.x;
				})
				.attr("cy", function(d) {
					return d.y;
				});

				// 更新文字坐标
				svg_texts.attr("x", function(d) {
					return d.x;
				})
				.attr("y", function(d) {
					return d.y;
				});
			});

		}
	})
});
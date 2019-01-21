define(['jquery','jquery.ztree'], function($) {
	// zTree 初始化配置
	$.fn.extend({
		// 树模型
		zTreeBox: function(options) {
			var defaults = {
				setting: {
					async: null,
					callback: null,
					check: null,
					data: null,
					edit: null,
					view: null,
					rMenu: {
						newFile: false,
						newNode: false,
						copy: false,
						cut: false,
						past: false,
						edit: false,
						del: false,
					},
					isDrag: false,
					isEdit: false
				},
				zNodes: [],
			}
			// 数据扩展
			var options = $.extend({}, defaults, options);
			var $zTree = $(this);
			var newCount = 1;

			// 是否存在右菜单栏
			if(options.setting.rMenu) {
				if(options.setting.callback) {
					options.setting.callback.onRightClick = OnRightClick;
				} else {
					options.setting.callback = {
						onRightClick: OnRightClick
					}
				}
			}

			// 是否可拖拽
			if(options.setting.isDrag) {
				options.setting.edit = {
					enable: true,	
					showRemoveBtn: false,
					showRenameBtn: false,
					drag: {
						isMove: true,
						prev: true,
						inner: true,
						next: true
					}
				}
				if(options.setting.callback) {
					options.setting.callback.beforeDrag = beforeDrag;
					options.setting.callback.beforeDrop = beforeDrop;
				} else {
					options.setting.callback = {
						beforeDrag: beforeDrag,
						beforeDrop: beforeDrop
					}
				}
			}

			// 配置初始化
			$.fn.zTree.init($zTree, options.setting, options.zNodes);

			// 实例化
			var zTree = $.fn.zTree.getZTreeObj($zTree[0].id);

			// 右侧菜单栏点击
			if(options.setting.rMenu) rMenu(options.setting.rMenu);

			// 右侧菜单栏
			function rMenu(menu) {
				// 建立容器
				$zTree.parent().append('<div class="rmenu"><ul></ul></div>');
				var $menuCon = $zTree.siblings('.rmenu').find('ul');

				// 复制 剪切 粘贴
				for(var i in menu) {
					switch(i) {
						case 'newFile':
							$menuCon.append('<li><i class="r-file"></i>新建文件夹</li>');
							$menuCon.find('.r-file').parent('li').on('click', { isParent: true }, menuAdd);
							break;
						case 'newNode':
							$menuCon.append('<li><i class="r-node"></i>新建子文件</li>');
							$menuCon.find('.r-node').parent('li').on('click', { isParent: false }, menuAdd);
							break;
						case 'copy':
							$menuCon.append('<li><i class="r-copy"></i>复制</li>');
							$menuCon.find('.r-copy').parent('li').on('click', menuCopy);
							break;
						case 'cut':
							$menuCon.append('<li><i class="r-cut"></i>剪切</li>');
							$menuCon.find('.r-cut').parent('li').on('click', menuCut);
							break;
						case 'past':
							$menuCon.append('<li><i class="r-past"></i>粘贴</li>');
							$menuCon.find('.r-past').parent('li').on('click', menuPast);
							break;
						case 'edit':
							$menuCon.append('<li><i class="r-edit"></i>编辑</li>');
							$menuCon.find('.r-edit').parent('li').on('click', menuEdit);
							break;
						case 'del':
							$menuCon.append('<li><i class="r-del"></i>删除</li>');
							$menuCon.find('.r-del').parent('li').on('click', menuDel);
							break;
						default :
							alert('found no case');
					}
				}

				// 点击函数集
				var curSrcNode, curType;
				function setCurSrcNode(zTreeObj, treeNode) {
					if (curSrcNode) {
						delete curSrcNode.isCur;
						var tmpNode = curSrcNode;
						curSrcNode = null;
					}
					curSrcNode = treeNode;
					if (!treeNode) return;

					curSrcNode.isCur = true;
					zTreeObj.cancelSelectedNode();
				}
				// 复制 
				function menuCopy() {
					var nodes = zTree.getSelectedNodes();
					// 是否选中
					if (nodes.length == 0) {
						alert("请先选择一个节点进行复制");
						return;
					}
					// 执行操作
					curType = 'copy';
					setCurSrcNode(zTree, nodes[0]);
					hideMenu();
				}
				// 剪切
				function menuCut() {
					var nodes = zTree.getSelectedNodes();
					// 是否选中
					if (nodes.length == 0) {
						alert("请先选择一个节点进行剪切操作");
						return;
					}
					// 执行操作
					curType = 'cut';
					setCurSrcNode(zTree, nodes[0]);
					hideMenu();
				}
				// 粘贴
				function menuPast() {
					var nodes = zTree.getSelectedNodes();
					if (!curSrcNode) {
						alert("请先选择一个节点进行 复制 / 剪切");
						return;
					}
					nodes = zTree.getSelectedNodes(),
					targetNode = nodes.length>0? nodes[0]:null;
					if (curSrcNode === targetNode) {
						alert("不能移动，源节点 与 目标节点相同");
						return;
					} else if (curType === "cut" && ((!!targetNode && curSrcNode.parentTId === targetNode.tId) || (!targetNode && !curSrcNode.parentTId))) {
						alert("不能移动，源节点 已经存在于 目标节点中");
						return;
					} else if (curType === "copy") {
						targetNode = zTree.copyNode(targetNode, curSrcNode, "inner");
					} else if (curType === "cut") {
						targetNode = zTree.moveNode(targetNode, curSrcNode, "inner");
						if (!targetNode) {
							alert("剪切失败，源节点是目标节点的父节点");
						}
						targetNode = curSrcNode;
					}
					setCurSrcNode(zTree);
					delete targetNode.isCur;
					zTree.selectNode(targetNode);
					hideMenu();
				}
				// 新增节点
				function menuAdd(e) {
					isParent = e.data.isParent,
					nodes = zTree.getSelectedNodes(),
					treeNode = nodes[0];
					if (treeNode) {
						treeNode = zTree.addNodes(treeNode, {id:(100 + newCount), pId:treeNode.id, isParent:isParent, name:"new node" + (newCount++)});
					} else {
						treeNode = zTree.addNodes(null, {id:(100 + newCount), pId:0, isParent:isParent, name:"new node" + (newCount++)});
					}
					if (treeNode) {
						zTree.editName(treeNode[0]);
					} else {
						alert("叶子节点被锁定，无法增加子节点");
					}
					hideMenu();
				}

				// 编辑节点
				function menuEdit() {
					var nodes = zTree.getSelectedNodes();
					if(nodes.length == 0) {
						alert("请先选择一个节点进行编辑操作");
						return;
					}
					zTree.editName(nodes[0]);
					hideMenu();
				}
				// 删除节点
				function menuDel() {
					var nodes = zTree.getSelectedNodes();
					if(nodes.length == 0) {
						alert("请先选择一个节点进行删除操作");
						return;
					}
					zTree.removeNode(nodes[0]);
					hideMenu();
				}
				// 隐藏菜单
				function hideMenu() {
					if($zTree.siblings('.rmenu').length > 0 ) {
						$zTree.siblings('.rmenu').css({
			                "visibility": "hidden"
			            });
					}
				}
			}

			// 右击事件显示菜单
		    function OnRightClick(event, treeId, treeNode) {
		        if (!treeNode && event.target.tagName.toLowerCase() != "button" && $(event.target).parents("a").length == 0) {
		            zTree.cancelSelectedNode();
		            showRMenu("root", event.clientX, event.clientY);
		        } else if (treeNode && !treeNode.noR) {
		            zTree.selectNode(treeNode);
		            showRMenu("node", event.clientX, event.clientY);
		        }
		    }

		    // 显示具体菜单
		    function showRMenu(type, x, y) {
		        $zTree.siblings('.rmenu').find('ul').show();

		        // 高度控制
		        var menuH = $zTree.siblings('.rmenu').height();
		        var windowH = $(window).height();

		        if(Number(menuH) + Number(y) < Number(windowH)) {
			        $zTree.siblings('.rmenu').css({
			            "top": y + "px",
			            "left": x + "px",
			            "visibility": "visible"
			        });
			    } else {
			    	$zTree.siblings('.rmenu').css({
			            "top": y - menuH + "px",
			            "left": x + "px",
			            "visibility": "visible"
			        });
			    }

		        $("body").bind("mousedown", onBodyMouseDown);
		    }

		    // 鼠标向下点击 隐藏
		    function onBodyMouseDown(event) {
		    	// 判断当前点击处的 ".rmenu" 是否存在
				if ($(event.target).parents(".ztree").siblings(".rmenu").length > 0) {
		            $(".rmenu").css({
		                "visibility": "hidden"
		            });
		        }
		    }

		    // 拖拽节点
			function beforeDrag(treeId, treeNodes) {
				for (var i=0,l=treeNodes.length; i<l; i++) {
					if (treeNodes[i].drag === false) {
						return false;
					}
				}
				return true;
			}

			// 禁止某节点拖拽
			function beforeDrop(treeId, treeNodes, targetNode, moveType) {
				return targetNode ? targetNode.drop !== false : true;
			}
		}
	})
});
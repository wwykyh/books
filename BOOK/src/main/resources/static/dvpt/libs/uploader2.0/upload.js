// 文件上传
define(['jquery', 'webuploader'], function($, WebUploader) {

	$.extend({
		fileUpload: function(options) {
			var defaults = {
				swf: "./dvpt/server/Uploader.swf", // swf 路径
				server: "./dvpt/server/fileupload.php", // 文件接收服务端
				btnPick: "#picker", // 选择文件的按钮
				btnUpload: "#ctlBtn", // 开始上传按钮
				uploadType: "file", // 上传的类型
				fileNumLimit: 1, // 文件数量限制

			}
			var opts = $.extend(defaults, options),
				$list = $('#thelist'),
				$btn = $(opts.btnUpload),
				state = 'pending',
				uploader;

			uploader = WebUploader.create({
				resize: false, // 不压缩image
				swf: opts.swf, // swf文件路径
				server: opts.server, // 文件接收服务端
				uploadType: opts.upLoadFile, // 上传文件类型，默认file
				pick: opts.btnPick, // 选择文件的按钮
				fileNumLimit: opts.fileNumLimit, // 文件数量限制
			});

			// 当有文件添加进来的时候
			uploader.on( 'fileQueued', function( file ) {
				$list.empty().append('<div id="' + file.id + '" class="item">' +
					'<h4 class="info">' + file.name + '</h4>' +
					'<p class="state">等待上传...</p>' +
				'</div>' );
			});

			// 文件上传过程中创建进度条实时显示。
			uploader.on( 'uploadProgress', function( file, percentage ) {
				var $li = $( '#'+file.id ),
					$percent = $li.find('.progress .progress-bar');

				// 避免重复创建
				if ( !$percent.length ) {
					$percent = $('<div class="progress progress-striped active">' +
					  '<div class="progress-bar" role="progressbar" style="width: 0%">' +
					  '</div>' +
					'</div>').appendTo( $li ).find('.progress-bar');
				}

				$li.find('p.state').text('上传中');

				$percent.css( 'width', percentage * 100 + '%' );
			});

			uploader.on( 'uploadSuccess', function( file ) {
				$( '#'+file.id ).find('p.state').text('已上传');
			});

			uploader.on( 'uploadError', function( file ) {
				$( '#'+file.id ).find('p.state').text('上传出错');
			});

			uploader.on( 'uploadComplete', function( file ) {
				$( '#'+file.id ).find('.progress').fadeOut();
			});

			uploader.on( 'all', function( type ) {
				if ( type === 'startUpload' ) {
					state = 'uploading';
				} else if ( type === 'stopUpload' ) {
					state = 'paused';
				} else if ( type === 'uploadFinished' ) {
					state = 'done';
				}

				if ( state === 'uploading' ) {
					$btn.text('暂停上传');
				} else {
					$btn.text('开始上传');
				}
			});

			$btn.on( 'click', function() {
				if ( state === 'uploading' ) {
					uploader.stop();
				} else {
					uploader.upload();
				}
			});
		}
	});
});
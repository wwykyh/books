define(['jquery', 'webuploader', 'artdialog', 'lrscroll', 'jquery.lightbox', 'dragon.lrscroll'], function($, WebUploader, artdialog) {
    $.extend({
        // style one
        UploadImg: function(options) {
            var defaults = {
                wrap: '#uploader', // 主容器
                pick: { // 添加图片文件按钮 按钮定义
                    id: '#filePicker', // 创建文件按钮选择器
                    label: '点击选择图片', // 上传按钮文字
                    multiple: true // 开启多文件上传
                },
                formData: {
                    uid: 123
                },
                dnd: '#dndArea', // 指定Drag And Drop拖拽的容器，如果不指定，则不启动
                swf: './js/server/Uploader.swf', // swf地址
                chunked: false,
                chunkSize: 512 * 1024,
                server: './js/server/fileupload.php', // 上传地址
                uploadType: 'file', // 上传文件类型，默认file，所有文件。可选image：图片

                disableGlobalDnd: true, // 是否禁掉整个页面的拖拽功能，如果不禁用，图片拖进来的时候会默认被浏览器打开
                fileNumLimit: 300, // 验证文件总数量, 超出则不允许加入队列
                fileSizeLimit: 200 * 1024 * 1024, // 验证文件总大小是否超出限制, 超出则不允许加入队列 200 M
                fileSingleSizeLimit: 50 * 1024 * 1024, // 验证单个文件大小是否超出限制 50 M
                duplicate: true // 去重, 根据文件名字、文件大小和最后修改时间来生成hash Key.
            }
            var option = $.extend({}, defaults, options);
            var $wrap = $(option.wrap),
                // 图片容器
                $queue = $('<ul class="filelist"></ul>')
                .appendTo($wrap.find('.queueList')),

                // 状态栏，包括进度和控制按钮
                $statusBar = $wrap.find('.statusBar'),

                // 文件总体选择信息。
                $info = $statusBar.find('.info'),

                // 上传按钮
                $upload = $wrap.find('.uploadBtn'),

                // 没选择文件之前的内容。
                $placeHolder = $wrap.find('.placeholder'),

                $progress = $statusBar.find('.progress').hide(),

                // 添加的文件数量
                fileCount = 0,

                // 添加的文件总大小
                fileSize = 0,

                // 优化retina, 在retina下这个值是2
                ratio = window.devicePixelRatio || 1,

                // 缩略图大小
                thumbnailWidth = 110 * ratio,
                thumbnailHeight = 110 * ratio,

                // 可能有pedding, ready, uploading, confirm, done.
                state = 'pedding',

                // 所有文件的进度信息，key为file id
                percentages = {},
                // 判断浏览器是否支持图片的base64
                isSupportBase64 = (function() {
                    var data = new Image();
                    var support = true;
                    data.onload = data.onerror = function() {
                        if (this.width != 1 || this.height != 1) {
                            support = false;
                        }
                    }
                    data.src = "data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///ywAAAAAAQABAAACAUwAOw==";
                    return support;
                })(),

                supportTransition = (function() {
                    var s = document.createElement('p').style,
                        r = 'transition' in s ||
                        'WebkitTransition' in s ||
                        'MozTransition' in s ||
                        'msTransition' in s ||
                        'OTransition' in s;
                    s = null;
                    return r;
                })();

            if (!$.checkFlashVersion($wrap)) return;

            // 实例化
            var uploader = $.getUploadObj(option);

            // 拖拽时不接受 js, txt 文件。
            uploader.on('dndAccept', function(items) {
                var denied = false,
                    len = items.length,
                    i = 0,
                    // 修改js类型
                    unAllowed = 'text/plain;application/javascript ';

                for (; i < len; i++) {
                    // 如果在列表里面
                    if (~unAllowed.indexOf(items[i].type)) {
                        denied = true;
                        break;
                    }
                }

                return !denied;
            });

            // 打开文件窗口事件
            uploader.on('dialogOpen', function() {
                // console.log('here');
            });

            // 添加“添加文件”的按钮，
            uploader.addButton({
                id: '#filePicker2',
                label: '继续添加'
            });

            uploader.on('ready', function() {
                window.uploader = uploader;
            });

            // 当有文件添加进来时执行，负责view的创建
            function addFile(file) {
                var $li = $('<li id="' + file.id + '">' +
                        '<p class="title">' + file.name + '</p>' +
                        '<p class="imgWrap"></p>' +
                        '<p class="progress"><span></span></p>' +
                        '</li>'),

                    $btns = $('<div class="file-panel">' +
                        '<span class="cancel">删除</span>' +
                        '<span class="rotateRight">向右旋转</span>' +
                        '<span class="rotateLeft">向左旋转</span></div>').appendTo($li),
                    $prgress = $li.find('p.progress span'),
                    $wrap = $li.find('p.imgWrap'),
                    $info = $('<p class="error"></p>'),

                    showError = function(code) {
                        switch (code) {
                            case 'exceed_size':
                                text = '文件大小超出';
                                break;

                            case 'interrupt':
                                text = '上传暂停';
                                break;

                            default:
                                text = '上传失败，请重试';
                                break;
                        }

                        $info.text(text).appendTo($li);
                    };

                if (file.getStatus() === 'invalid') {
                    showError(file.statusText);
                } else {
                    // @todo lazyload
                    $wrap.text('预览中');
                    uploader.makeThumb(file, function(error, src) {
                        var img;

                        if (error) {
                            $wrap.text('不能预览');
                            return;
                        }

                        if (isSupportBase64) {
                            img = $('<img src="' + src + '">');
                            $wrap.empty().append(img);
                        } else {
                            $.ajax('../../server/preview.php', {
                                method: 'POST',
                                data: src,
                                dataType: 'json'
                            }).done(function(response) {
                                if (response.result) {
                                    img = $('<img src="' + response.result + '">');
                                    $wrap.empty().append(img);
                                } else {
                                    $wrap.text("预览出错");
                                }
                            });
                        }
                    }, thumbnailWidth, thumbnailHeight);

                    percentages[file.id] = [file.size, 0];
                    file.rotation = 0;
                }

                file.on('statuschange', function(cur, prev) {
                    if (prev === 'progress') {
                        $prgress.hide().width(0);
                    } else if (prev === 'queued') {
                        $li.off('mouseenter mouseleave');
                        $btns.remove();
                    }

                    // 成功
                    if (cur === 'error' || cur === 'invalid') {
                        // console.log(file.statusText);
                        showError(file.statusText);
                        percentages[file.id][1] = 1;
                    } else if (cur === 'interrupt') {
                        showError('interrupt');
                    } else if (cur === 'queued') {
                        $info.remove();
                        $prgress.css('display', 'block');
                        percentages[file.id][1] = 0;
                    } else if (cur === 'progress') {
                        $info.remove();
                        $prgress.css('display', 'block');
                    } else if (cur === 'complete') {
                        $prgress.hide().width(0);
                        $li.append('<span class="success"></span>');
                    }

                    $li.removeClass('state-' + prev).addClass('state-' + cur);
                });

                $li.on('mouseenter', function() {
                    $btns.stop().animate({ height: 30 });
                });

                $li.on('mouseleave', function() {
                    $btns.stop().animate({ height: 0 });
                });

                $btns.on('click', 'span', function() {
                    var index = $(this).index(),
                        deg;

                    switch (index) {
                        case 0:
                            uploader.removeFile(file);
                            return;

                        case 1:
                            file.rotation += 90;
                            break;

                        case 2:
                            file.rotation -= 90;
                            break;
                    }

                    if (supportTransition) {
                        deg = 'rotate(' + file.rotation + 'deg)';
                        $wrap.css({
                            '-webkit-transform': deg,
                            '-mos-transform': deg,
                            '-o-transform': deg,
                            'transform': deg
                        });
                    } else {
                        $wrap.css('filter', 'progid:DXImageTransform.Microsoft.BasicImage(rotation=' + (~~((file.rotation / 90) % 4 + 4) % 4) + ')');
                    }


                });

                $li.appendTo($queue);
            }

            // 负责view的销毁
            function removeFile(file) {
                var $li = $('#' + file.id);

                delete percentages[file.id];
                updateTotalProgress();
                $li.off().find('.file-panel').off().end().remove();
            }

            function updateTotalProgress() {
                var loaded = 0,
                    total = 0,
                    spans = $progress.children(),
                    percent;

                $.each(percentages, function(k, v) {
                    total += v[0];
                    loaded += v[0] * v[1];
                });

                percent = total ? loaded / total : 0;


                spans.eq(0).text(Math.round(percent * 100) + '%');
                spans.eq(1).css('width', Math.round(percent * 100) + '%');
                updateStatus();
            }

            function updateStatus() {
                var text = '',
                    stats;

                if (state === 'ready') {
                    text = '选中' + fileCount + '张图片，共' +
                        WebUploader.formatSize(fileSize) + '。';
                } else if (state === 'confirm') {
                    stats = uploader.getStats();
                    if (stats.uploadFailNum) {
                        text = '已成功上传' + stats.successNum + '张照片至XX相册，' +
                            stats.uploadFailNum + '张照片上传失败，<a class="retry" href="#">重新上传</a>失败图片或<a class="ignore" href="#">忽略</a>'
                    }

                } else {
                    stats = uploader.getStats();
                    text = '共' + fileCount + '张（' +
                        WebUploader.formatSize(fileSize) +
                        '），已上传' + stats.successNum + '张';

                    if (stats.uploadFailNum) {
                        text += '，失败' + stats.uploadFailNum + '张';
                    }
                }

                $info.html(text);
            }

            function setState(val) {
                var file, stats;

                if (val === state) {
                    return;
                }

                $upload.removeClass('state-' + state);
                $upload.addClass('state-' + val);
                state = val;

                switch (state) {
                    case 'pedding':
                        $placeHolder.removeClass('element-invisible');
                        $queue.hide();
                        $statusBar.addClass('element-invisible');
                        uploader.refresh();
                        break;

                    case 'ready':
                        $placeHolder.addClass('element-invisible');
                        $('#filePicker2').removeClass('element-invisible');
                        $queue.show();
                        $statusBar.removeClass('element-invisible');
                        uploader.refresh();
                        break;

                    case 'uploading':
                        $('#filePicker2').addClass('element-invisible');
                        $progress.show();
                        $upload.text('暂停上传');
                        break;

                    case 'paused':
                        $progress.show();
                        $upload.text('继续上传');
                        break;

                    case 'confirm':
                        $progress.hide();
                        $('#filePicker2').removeClass('element-invisible');
                        $upload.text('开始上传');

                        stats = uploader.getStats();
                        if (stats.successNum && !stats.uploadFailNum) {
                            setState('finish');
                            return;
                        }
                        break;
                    case 'finish':
                        stats = uploader.getStats();
                        if (stats.successNum) {
                            // alert( '上传成功' );
                        } else {
                            // 没有成功的图片，重设
                            state = 'done';
                            location.reload();
                        }
                        break;
                }

                updateStatus();
            }

            uploader.onUploadProgress = function(file, percentage) {
                var $li = $('#' + file.id),
                    $percent = $li.find('.progress span');

                $percent.css('width', percentage * 100 + '%');
                percentages[file.id][1] = percentage;
                updateTotalProgress();
            };

            uploader.onFileQueued = function(file) {
                fileCount++;
                fileSize += file.size;

                if (fileCount === 1) {
                    $placeHolder.addClass('element-invisible');
                    $statusBar.show();
                }

                addFile(file);
                setState('ready');
                updateTotalProgress();
            };

            uploader.onFileDequeued = function(file) {
                fileCount--;
                fileSize -= file.size;

                if (!fileCount) {
                    setState('pedding');
                }

                removeFile(file);
                updateTotalProgress();

            };

            uploader.on('all', function(type) {
                var stats;
                switch (type) {
                    case 'uploadFinished':
                        setState('confirm');
                        break;

                    case 'startUpload':
                        setState('uploading');
                        break;

                    case 'stopUpload':
                        setState('paused');
                        break;

                }
            });

            uploader.onError = function(code) {
                alert('Eroor: ' + code);
            };

            $upload.on('click', function() {
                if ($(this).hasClass('disabled')) {
                    return false;
                }

                if (state === 'ready') {
                    uploader.upload();
                    // 增加暂停 TDM 20160816
                    setState('uploading');
                } else if (state === 'paused') {
                    uploader.upload();
                } else if (state === 'uploading') {
                    uploader.stop();
                }
            });

            $info.on('click', '.retry', function() {
                uploader.retry();
            });

            $info.on('click', '.ignore', function() {
                alert('todo');
            });

            $upload.addClass('state-' + state);
            updateTotalProgress();
        },

        // style two
        UploadFile: function(options) {
            // 默认配置
            var defaults = {
                contextPath: "", //配置地址上下文 todo 如：localhost:8080/xx/xx
                swf: "./js/server/Uploader.swf", // swf地址
                server: './js/server/fileupload.php', //上传地址
                dnd: null, //指定Drag And Drop拖拽的容器，如果不指定，则不启动。
                disableGlobalDnd: false, //是否禁掉整个页面的拖拽功能，如果不禁用，图片拖进来的时候会默认被浏览器打开。
                paste: null, //通过粘贴来添加截屏的图片
                pick: { //指定按钮文字
                    id: "#picker", //创建文件按钮选择器
                    innerHTML: "选择文件", //上传按钮文字
                    multiple: true //开启多文件上传
                },
                uploadType: 'file', // 上传文件类型，默认file，所有文件。可选image：图片
                custom: false, //上传控件显示自定义
                uploadSuccess: null, // 上传成功回调函数,返回后台response对象 response.result取得结果
                selectFile: null, // 选择文件回调函数 ，返回props对象 props={ uploader:uploader, file:file, uploaderDom:uploaderDom }
                setDownloadItem: null,
                setUploadStruct: null, //设置上传控件显示结构,返回上传控件dom对象
                state: 'pending',
                // accept: { //指定接受哪些类型的文件
                //     title: "",
                //     extensions: "", //允许的文件后缀，不带点，多个用逗号分割
                //     mimeTypes: ""
                // },
                thumb: { //配置生成缩略图选项
                    width: 120,
                    height: 140,
                    //quality: 100, // 图片质量，只有type为`image/jpeg`的时候才有效。
                    allowMagnify: true, // 是否允许放大，如果想要生成小图的时候不失真，此选项应该设置为false.
                    crop: true, // 是否允许裁剪。
                    // 为空的话则保留原有图片格式。
                    // 否则强制转换成指定的类型。
                    type: 'image/jpeg'
                },

                compress: false, //配置压缩的图片的选项
                auto: false, //自动上传
                prepareNextFile: true, //是否允许在文件传输时提前把下一个文件准备好
                chunked: true, // 开起大文件分片上传。
                breakPoints: false, //断点续传开关，需开启chunked 分片上传
                chunkSize: 5242880, //分片上传大小，5M 5242880
                chunkRetry: 2, //上传失败，分片重传次数
                threads: 1, //同时允许上传并发数
                formData: {}, //文件上传请求的参数表，每次发送都会发送此对象中的参数
                fileVal: "file", //设置文件上传域的name
                method: "POST",
                sendAsBinary: false, //是否已二进制的流的方式发送文件，这样整个上传内容php://input都为文件内容， 其他参数在$_GET数组中
                fileNumLimit: 50, //验证文件总数量, 超出则不允许加入队列
                fileSizeLimit: null, //验证文件总大小是否超出限制, 超出则不允许加入队列
                fileSingleSizeLimit: null, //验证单个文件大小是否超出限制
                fileNameLengthLimit: null, //文件名长度限制
                duplicate: true, //去重， 根据文件名字、文件大小和最后修改时间来生成hash Key.
                disableWidgets: null // 默认所有 Uploader.register 了的 widget 都会被加载，如果禁用某一部分，请通过此 option 指定黑名单。
            }
            var option = $.extend({}, defaults, options);

            // 主容器
            $uploader = $(option.wrap);
            // 初始化页面配置
            var option = $.initUploadersetting($uploader, option);
            $.initUploaderStruct($uploader, option);

            // 检测 flash 支持性
            if (!$.checkFlashVersion($uploader)) return;
            // 实例化上传控件
            var _uploader = $.getUploadObj(option);
            // 绑定事件
            $.uploaderObjEvent(_uploader, $uploader, option);
            return $uploader;
        },

        // 检测 Flash 版本
        checkFlashVersion: function($wrap) {

            // 判断检测
            if (!WebUploader.Uploader.support('flash') && WebUploader.browser.ie) {

                // flash 安装了但是版本过低。
                if (flashVersion) {
                    (function(container) {
                        window['expressinstallcallback'] = function(state) {
                            switch (state) {
                                case 'Download.Cancelled':
                                    art.dialog.confirm('您取消了更新！')
                                    break;

                                case 'Download.Failed':
                                    art.dialog.confirm('安装失败')
                                    break;

                                default:
                                    art.dialog.confirm('安装已成功，请刷新！');
                                    break;
                            }
                            delete window['expressinstallcallback'];
                        };

                        var swf = './expressInstall.swf';
                        // insert flash object
                        var html = '<object type="application/' +
                            'x-shockwave-flash" data="' + swf + '" ';

                        if (WebUploader.browser.ie) {
                            html += 'classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" ';
                        }

                        html += 'width="100%" height="100%" style="outline:0">' +
                            '<param name="movie" value="' + swf + '" />' +
                            '<param name="wmode" value="transparent" />' +
                            '<param name="allowscriptaccess" value="always" />' +
                            '</object>';

                        container.html(html);

                    })($wrap);

                    // 压根就没有安转。
                } else {
                    art.dialog({ title: '提示', content: '没有检测到可用flash插件，请点击Flash图标进行下载。', icon: 'error' });
                    $wrap.html('<a href="./js/libs/uploader2.0/flash_player.exe" target="_blank" border="0"><img alt="get flash player" title="下载flash播放器" src="./js/libs/uploader2.0/css/down_flash.png" /></a>');
                }

                return false;
            } else if (!WebUploader.Uploader.support()) {
                art.dialog({ title: '提示', content: 'Web Uploader 不支持您的浏览器！', icon: 'error' });
                return false;
            } else {
                return true;
            }

            // 检测是否已经安装flash，检测flash的版本
            var flashVersion = function() {
                var version;

                try {
                    version = navigator.plugins['Shockwave Flash'];
                    version = version.description;
                } catch (ex) {
                    try {
                        version = new ActiveXObject('ShockwaveFlash.ShockwaveFlash')
                            .GetVariable('$version');
                    } catch (ex2) {
                        version = '0.0';
                    }
                }
                version = version.match(/\d+/g);
                return parseFloat(version[0] + '.' + version[1], 10);
            }
        },

        // 生成 WebUploader 对象
        getUploadObj: function(options) {
            // 调用百度 WebUploader 接口
            var uploader = new WebUploader.create(options);

            return uploader;
        },

        // 事件调用
        uploaderObjEvent: function(_uploader, $uploader, setting) {
            // 文件加入队列前事件 进行文件名称及数量验证
            _uploader.on('beforeFileQueued', function(file) {
                if (setting.fileNameLengthLimit && (file.name.length > setting.fileNameLengthLimit)) {
                    art.dialog.confirm(file.name + '文件名过长');
                    return false
                }
                if (!setting.fileNumLimit) return true
                    // 文件列表list数量验证
                if ($("#lists", $uploader)) {
                    if ($("#lists", $uploader).children().length < setting.fileNumLimit) return true;
                    art.dialog.confirm("只能上传"+setting.fileNumLimit+"个文件");
                    return false
                }

            });

            // 文件加入队列
            _uploader.on('fileQueued', function(file) {
                var itemFiles = $uploader.data("postData"),
                    index = 0;
                for (var i in itemFiles) { index++; } //当前已有文件数
                file.index = index + 1;
                (setting.uploadType == "image") ? imageObj.addImgItem(_uploader, $uploader, file, setting): fileObj.addFileItem(_uploader, file, $uploader);
            });

            /*大文件分块发送后触发*/
            _uploader.on('uploadAfterSend', function(object, data, headers) {
                /*开启断点续传，每个分块上传成功后，更新服务器端已上传文件大小*/
                /*if(_uploader.options.breakPoints){
                 webUploader.saveUploadedSize(data);
                 }*/
            });

            // 文件上传过程中创建进度条实时显示。
            _uploader.on('uploadProgress', function(file, percentage) {
                if (setting.custom) return
                var $li = $('#' + file.id, $uploader),
                    $percent = $li.find('.webUploader-progress-bar', $uploader);
                $li.find('span.state').css("color", "#005fa0").text('上传中');
                var width = percentage * 100 + '%';
                if ($percent.attr("uploadedWidth")) {
                    var uploadedWidth = parseInt($percent.attr("uploadedWidth"), 10),
                        percentWidth = $percent.parent().width(),
                        leftWidth = percentWidth - uploadedWidth;
                    var currentPer = ((percentage * leftWidth) + uploadedWidth) / percentWidth;
                    width = currentPer * 100 + '%';
                }
                $percent.css('width', width);
            });

            _uploader.on('startUpload', function(file) {
                if (setting.auto) {
                    //$.fn.shadow(true,"上传中，请稍后..."); todo 增加遮罩
                }
            });
            //上传成功
            _uploader.on('uploadSuccess', function(file, response) {
                // console.log('上传成功:');
                // console.log(response);
                if (setting.auto) {
                    //$.fn.shadow(false); todo 增加遮罩
                }
                var data = {
                    // upLoadFile:response.result.groupId,
                    businessType: $uploader.attr("businessType") || "",
                    businessId: $uploader.attr("businessId") || "",
                    operate: true
                }
                var props = {
                    setting: setting, //上传控件属性
                    file: {
                        id: $uploader.find(".btns a").attr('id'), //文件id
                        name: response.result.fileName, //文件名
                        size: $.fileSize(response.result.fileSize), //文件大小
                        index: file.index, //文件序号
                        type: $.fileType(response.result.fileName) //文件类型
                    },
                    uploaderDom: $uploader, //上传控件DOM节点
                    data: data
                }
                if (setting.custom) {
                    setting.uploadSuccess && setting.uploadSuccess(props);
                    return
                }
                switch (setting.uploadType) {
                    case "file":
                        {
                            var fileItem = $('#' + file.id, $uploader),
                                state = fileItem.find('span.state'),
                                downloadBtn = fileItem.find(".a-dis-upload"); //a-download 改变上传按钮为下载
                            state.css("color", "green").text('已上传');
                            fileItem.find(".icon-play2").remove();
                            downloadBtn.unbind("click");
                            // $.upDatePostData($uploader,data);
                            setting.uploadSuccess && setting.uploadSuccess(props);
                            break;
                        }
                    case "image":
                        {
                            // file.groupId=response.result.groupId;
                            // $.upDatePostData($uploader,data);
                            /* 上传图片完成后更新图片组件中对应图片信息 */
                            var imgIndex = $uploader.lrScrollObj.getImgNum() - 1;
                            var uploadedImg = $uploader.lrScrollObj.getImage(imgIndex);
                            uploadedImg.attr({
                                // groupId:response.result.groupId,
                                oriName: response.result.fileName,
                                size: response.result.fileSize
                            });
                            break;
                        }
                    default:
                        {
                            art.dialog.alert("错误的上传类型!请检查uploadType属性!");
                            break;
                        }
                }

            });

            //上传出错
            _uploader.on('uploadError', function(file) {
                if (setting.custom) return
                $('#' + file.id, $uploader).find('span.state').css("color", "red").text('上传出错');
            });
            // 上传完成
            _uploader.on('uploadComplete', function(file) {
                if (setting.custom) return
                $('#' + file.id, $uploader).find('.webUploader-progress').fadeOut();
            });
            // 初始化完成操作
            _uploader.on('ready', function() {
                //修改上传按钮样式
                var btnUpload = $(".webuploader-pick", $uploader);
                btnUpload.html("<span>" + btnUpload.html() + "</span>")
                    .removeClass("webuploader-pick")
                    .addClass("buttonActive");

                (setting.uploadType == "image") && imageObj.addImgOperateBar(_uploader, $uploader);

                (setting.setUploadStruct) && setting.setUploadStruct($uploader);

                (setting.custom) && $(".webuploader-container", $uploader).removeClass("webuploader-container");

                // (setting.isDownLoad)&&$.initDownLoadList($uploader,setting);
            });
            //
            _uploader.on('all', function(type) {
                if (setting.auto) return
                if (type === 'startUpload') {
                    _uploader.options.state = 'uploading';
                } else if (type === 'stopUpload') {
                    _uploader.options.state = 'paused';
                } else if (type === 'uploadFinished') {
                    _uploader.options.state = 'done';
                }

                if (_uploader.options.state === 'uploading') {
                    $('#btnBegin', $uploader).find("span").text('暂停上传');
                } else {
                    $('#btnBegin', $uploader).find("span").text('开始上传');
                }
            });

            $('#btnBegin', $uploader).on('click', function() {
                if (setting.custom) return
                if (_uploader.options.state === 'uploading') {
                    _uploader.stop(true);
                } else {
                    _uploader.upload();
                }
            });
        },

        initUploadersetting: function($uploader, defaults) {
            var props = $uploader.data("props") || "";
            props = eval("({" + props + "})");
            var setting = $.extend(true, {}, defaults);
            if (props.uploadType == "image") {
                setting.auto = (props.auto) ? props.auto : true; //图片默认开启自动上传
                setting.pick.innerHTML = "添加"; //按钮文本
                setting.accept = {};
                setting.accept.title = 'Images';
                setting.accept.extensions = 'gif,jpg,jpeg,bmp,png';
                setting.accept.mimeTypes = 'image/*';
            }
            if (props.btnText) {
                setting.pick.innerHTML = props.btnText;
                delete props.btnText
            }
            if (props.multiple == "false") { //多文件上传
                setting.pick.multiple = false;
                delete props.multiple
            }
            setting = $.extend({}, setting, props);
            if ($uploader.attr("businessId")) {
                setting.isDownLoad = true;
            }
            return setting;
        },

        // 初始化上传控件结构
        initUploaderStruct: function($uploader, setting) {
            var lists = $("<div id='lists' class='uploader-list'></div>"),
                btns = $("<div class='btns'></div>"),
                btnUpload = $('<a href="javascript:;" class="btn btn-default">上传文件</a>'),
                btnBegin = $("<a class='btn btn-default' id='btnBegin' >开始上传</a>");

            var time = new Date().getTime();
            setting.pick.id = "#uploader" + time;
            $uploader.get(0).checked = true;
            if (setting.uploadType != "image") {
                if (setting.custom) {
                    btnUpload.attr("id", "uploader" + time);
                    $uploader.append(btnUpload);
                    return;
                }
                btnUpload.attr("id", "uploader" + time);
                btns.append(btnUpload);
                //(!setting.auto)&&(btns.append(btnBegin));
                $uploader.addClass("fileUpload");
                $uploader.append(btns).append(lists);
                //(setting.isDownLoad)&&util.initDownLoadList($uploader,setting);
                return
            }
            //构造上传图片结构
            var addImg = $("<a class='btn btn-default'>添加</a>"),
                delImg = $("<a class='btn btn-default' id='delImg' >删除</a>");
            addImg.attr("id", "uploader" + time);
            btns.append(addImg).append(delImg);
            var divTc = $("<div class='divTc'></div>").append(btns);
            $uploader.addClass("imageUpload");
            $uploader.append(lists).append(divTc); // 构造图片删除按钮
            imageObj.addImgList($uploader, setting);
            //(setting.isDownLoad)&&util.initDownLoadList($uploader,setting);
        },

        // 公共函数 - 时间串转换
        getTimeString: function(time) {
            time = time.toLocaleString().split(" ");
            var _date = time[0].split("/"),
                _time = time[1].split(":");
            var year = _date[0],
                month = _date[1],
                day = _date[2],
                hours = _time[0],
                minutes = _time[1],
                seconds = _time[2];
            (parseInt(month, 10) < 10) && (month = "0" + month);
            (parseInt(day, 10) < 10) && (day = "0" + day);
            (parseInt(minutes, 10) < 10) && (minutes = "0" + minutes);
            if (hours.match("上午")) {
                hours = hours.replace("上午", "");
                hours = (parseInt(hours, 10) < 10) ? ("0" + month) : hours;
            } else {
                hours = hours.replace("下午", "");
                hours = (parseInt(hours, 10) + 12);
            }
            return "" + year + month + day + hours + minutes + seconds;
        },

        // 生成唯一组id，用于标识上传时分片所属文件
        uuid: function(len, radix) {
            var chars = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz'.split('');
            var uuid = [],
                i;
            radix = radix || chars.length;

            if (len) {
                // Compact form
                for (i = 0; i < len; i++) uuid[i] = chars[0 | Math.random() * radix];
            } else {
                // rfc4122, version 4 form
                var r;

                // rfc4122 requires these characters
                uuid[8] = uuid[13] = uuid[18] = uuid[23] = '-';
                uuid[14] = '4';

                // Fill in random data.  At i==19 set the high bits of clock sequence as
                // per rfc4122, sec. 4.1.5
                for (i = 0; i < 36; i++) {
                    if (!uuid[i]) {
                        r = 0 | Math.random() * 16;
                        uuid[i] = chars[(i == 19) ? (r & 0x3) | 0x8 : r];
                    }
                }
            }
            return uuid.join('');
        },

        upDatePostData:function(uploaderDom,data){
            var postData=uploaderDom.data("postData")||{};
            postData[data.upLoadFile]=data;
            (data.operate)&&(postData[data.upLoadFile]["operate"]="add");
            uploaderDom.data("postData",postData);
        },

        removePostData: function(uploaderDom, groupId) {
            var postData = uploaderDom.data("postData") || {};
            if (!postData[groupId]) return
            if (postData[groupId]["operate"] == "add") {
                delete postData[groupId] //新增的数据直接删除，不做提交
                uploaderDom.data("postData", postData);
                return
            }
            postData[groupId]["operate"] = "del"; //从服务器下载的数据，更新删除状态，服务器删除
            uploaderDom.data("postData", postData);
            return
        },

        // 获取文件大小
        fileSize: function(size) {
            var result = "";
            var sizeM = parseInt(size / (1024 * 1024));
            var sizeK = parseInt(size / 1024);
            if (sizeM > 0) {
                result = sizeM + "MB";
            } else if (sizeK > 0) {
                result = sizeK + "KB";
            } else {
                result = size + "byte";
            }
            return result;
        },

        // 获取文件类型
        fileType: function(fileName) {
            var typeArray = ["accdb", "chm", "doc", "docx", "exe", "htm", "pdf", "ppt",
                "pptx", "rar", "txt", "xls", "zip", "xlsx", "gif", "jpg", "png"
            ];
            var name = fileName.split(".");
            var fileType = name[name.length - 1];
            ($.inArray(fileType, typeArray) < 0) && (fileType = "noKownFile");
            return fileType;
        },

        // 对外提供函数
        addUploaderFn: function(uploaderDom, uploaderObj) {

            uploaderDom.startUpload = function(fileId) {
                uploaderObj.upload(fileId);
            };

            uploaderDom.pauseUpload = function(fileId) {
                $.each(uploaderObj.getFiles, function() {
                    if (this.groupId == fileId) {
                        uploaderObj.stop(this, true);
                        return;
                    }
                });
            };

            uploaderDom.cancelUpload = function(fileId) {
                uploaderObj.removeFile(fileId, true);
                $.removePostData(uploaderDom, fileId);
            };

            uploaderDom.deleteFile = function(fileId) {
                $.removePostData(uploaderDom, fileId);
            };

            uploaderDom.upDatePostData = function(data) {
                $.upDatePostData(uploaderDom, data);
            }
        }


    });

    // 图片上传类
    var imageObj = {
        // 构建图片显示组件
        addImgList: function($uploader, setting) {
            var imgDiv = $("<div class='imgList'></div>");
            var imgList = $("<div></div>");
            imgList.attr("data-props", "width:'" + setting.thumb.width + "',height:'" + setting.thumb.height + "',sourceType:'ajax'");
            imgList = $uploader.lrScrollObj = $.fn.scrollDp(imgList);
            imgDiv.append(imgList);
            $("#lists", $uploader).append(imgDiv);

        },
        // 新增图片
        addImgItem: function(_uploader, $uploader, file, setting) {
            var props = {
                uploader: _uploader,
                file: file,
                uploaderDom: $uploader
            }
            var ratio = window.devicePixelRatio || 1,
                width = setting.thumb.width * ratio,
                height = setting.thumb.height * ratio;
            _uploader.makeThumb(file, function(error, src) {
                if (error) {
                    art.dialog.alert("无法预览!");
                    return;
                }
                var imgIndex = $uploader.lrScrollObj.addThumbImg(src).getIndex();
                $uploader.lrScrollObj.showImage(imgIndex); //todo 显示新增的图片
            }, width, height);

            // 上传功能
            props.uploader.upload(props.file);

        },
        // 图片操作按钮栏
        addImgOperateBar: function(_uploader, $uploader) {
            $("#delImg", $uploader).click(function() {
                var imgIndex = $uploader.lrScrollObj.getIndex();
                var img = $uploader.lrScrollObj.getImage(imgIndex - 1);
                $.removePostData($uploader, img.attr("groupId"));
                $uploader.lrScrollObj.deleteImg(imgIndex);
                // (_uploader.options.auto)?(_uploader.getFiles().splice(imgIndex-1,1)):_uploader.removeFile( img, true )
            });
        }
    }

    // 文件上传类
    var fileObj = {
        // 构建文件显示容器
        addFileItem: function(uploader, file, uploaderDom) {
            var props = {
                uploader: uploader,
                file: file,
                uploaderDom: uploaderDom
            }
            file.groupId = $.uuid(19, 16) + new Date().getTime(); //分片组id
            file.businessId = uploaderDom.attr("businessId") || ""; //业务id
            file.businessType = uploaderDom.attr("businessType") || ""; //业务类型
            file.lastModifiedDate = $.getTimeString(file.lastModifiedDate);
            if (!uploader.options.breakPoints) {
                fileObj.addItem(props);
                return
            }

            // 获取服务器端已上传文件大小 分片数量
            $.getUploadedSize(uploader.options, file, uploaderDom, function(uploadedFileObj) {
                if (uploadedFileObj.combined == 1) {
                    if (uploader.options.pick.multiple) {
                        file.groupId = uploadedFileObj.groupId;
                        fileObj.addDownLoadItem(props);
                        var fileItem = $("#" + file.id, uploaderDom),
                            state = $('<span class="state" style="color: green;" >已上传</span>');
                        fileItem.find(".operateBar").before(state).append(fileObj.addCancelBtn(props));
                        fileItem.find(".icon-delete").remove();
                        fileItem.find(".icon-download").remove();
                        return
                    }
                    art.dialog.confirm("该文件在服务器中已存在，是否需要重新上传？", function() {
                        fileObj.addItem(props);
                        $("#" + file.id, uploaderDom).find(".icon-upload").trigger("click");
                        return true
                    }, function() {
                        file.groupId = uploadedFileObj.groupId;
                        fileObj.addDownLoadItem(props);
                        var fileItem = $("#" + file.id, uploaderDom),
                            state = $('<span class="state" style="color: green;" >已上传</span>');
                        fileItem.find(".operateBar").before(state).append(fileObj.addCancelBtn(props));
                        fileItem.find(".icon-delete").remove();
                        fileItem.find(".icon-download").remove();
                        return true
                    });
                } else if (uploadedFileObj.uploadedSize) {
                    if (uploader.options.pick.multiple) {
                        file.groupId = uploadedFileObj.groupId;
                        fileObj.addItem(props);
                        fileObj.setProgress(props, uploadedFileObj);
                        $("#" + file.id, uploaderDom).find(".icon-upload").trigger("click");
                        return
                    }
                    art.dialog.confirm("该文件上传中断，是否从中断处继续上传？", function() {
                        file.groupId = uploadedFileObj.groupId;
                        fileObj.addItem(props);
                        fileObj.setProgress(props, uploadedFileObj);
                        $("#" + file.id, uploaderDom).find(".icon-upload").trigger("click");
                        return true
                    }, function() {
                        fileObj.addItem(props);
                        $("#" + file.id, uploaderDom).find(".icon-upload").trigger("click");
                        return true
                    });
                } else {
                    fileObj.addItem(props);
                    $("#" + file.id, uploaderDom).find(".icon-upload").trigger("click");
                }
            });

        },
        /*新增待上传文件*/
        addItem: function(props) {
            if (props.uploader.options.custom) {
                props.uploader.options.selectFile && props.uploader.options.selectFile(props);
                return;
            }
            var name = props.file.name;
            var fileType = $.fileType(name);
            (name.length > 10) && (name = name.slice(0, 9) + "...");
            var item = $('<div id="' + props.file.id + '" class="webUploader-queue-item">' +
                '<div class="webUploader-progress"><div class="webUploader-progress-bar"></div></div>' +
                '<span class="fileName" title="' + props.file.name + '">' + name + '</span>' +
                '<span class="fileSize">' + $.fileSize(props.file.size) + '</span>' +
                '<span class="state" >等待上传</span></div>'); //todo 修改样式
            $(".fileName", item).addClass(fileType);
            var operateBar = $("<span class='operateBar'></span>")
                .append(fileObj.addUploadBtn(props))
                .append(fileObj.addPauseBtn(props))
                .append(fileObj.addCancelBtn(props));
            item.append(operateBar);
            $("#lists", props.uploaderDom).append(item);
        },
        /*新增可下载文件队列*/
        addDownLoadItem: function(props) {
            //props.file.index
            var name = props.file.name;
            var fileType = $.fileType(name);
            (name.length > 12) && (name = name.slice(0, 11) + "...");
            var item = $('<div id="' + props.file.id + '" class="webUploader-queue-item">' +
                '<span class="fileName" title="' + props.file.name + '">' + name + '</span>' +
                '<span class="fileSize">' + $.fileSize(props.file.size) + '</span></div>');
            $(".fileName", item).addClass(fileType);
            var operateBar = $("<span class='operateBar'></span>")
                .append(fileObj.addDownloadBtn(props))
                .append(fileObj.addDeleteBtn(props));
            item.append(operateBar);
            $("#lists", props.uploaderDom).append(item);
        },
        // 设置文件上传进度条
        setProgress: function(props, uploadedFileObj) {
            if (props.uploader.options.custom) return
            
            //重置上传进度
            var $li = $('#' + props.file.id, props.uploaderDom),
                $percent = $li.find('.webUploader-progress-bar', props.uploaderDom);
            $percent.css('width', uploadedFileObj.uploadedSize / props.file.size * 100 + '%');
            $percent.attr("uploadedWidth", $percent.width());
            props.file.uploadedChunk = uploadedFileObj.uploadedChunk;
            (uploadedFileObj.groupId) && (props.file.groupId = uploadedFileObj.groupId);
        },
        // 上传按钮
        addUploadBtn: function(props) {
            var uploadBtn = $('<a class="icon icon-upload" href="#" title="上传"></a>');
            uploadBtn.click(function() {
                if (uploadBtn.hasClass("disabled")) return
                // 开始按钮（点击开始上传后阻止再次点击）
                uploadBtn.addClass("disabled");
                uploadBtn.closest(".webUploader-queue-item").find(".state").html("上传中");

                // 暂停按钮（点击开始上传后可以执行）
                var pauseBtn = uploadBtn.closest(".operateBar").find(".icon-play2");
                pauseBtn.removeClass("disabled");

                // 上传函数
                props.uploader.upload(props.file);
            });
            return uploadBtn
        },
        /*下载按钮*/
        addDownloadBtn: function(props) {
            //更新下载链接地址
            var downloadBtn = $('<a class="icon icon-download"  title="下载"></a>');
            downloadBtn.attr("href", props.file.url);
            //downloadBtn.attr("href",props.url);
            return downloadBtn;
        },
        /*取消文件按钮*/
        addCancelBtn: function(props) {
            //var cancelBtn=$('<span class="deletebtn">删除</span>');
            var cancelBtn = $('<a class="icon icon-cancel" href="#" title="取消"></a>');
            cancelBtn.click(function() {
                props.uploader.removeFile(props.file, true);
                $('#' + props.file.id, props.file.uploaderDom).remove();
                $.removePostData(props.uploaderDom, props.file.groupId);
            });
            return cancelBtn;
        },
        // 删除文件按钮
        addDeleteBtn: function(props) {
            var deleteBtn = $('<a class="icon icon-delete" href="#" title="删除"></a>');
            deleteBtn.click(function() {
                $('#' + props.file.id, props.uploaderDom).remove();
                $.removePostData(props.uploaderDom, props.file.id);
            });
            return deleteBtn;
        },
        // 暂停按钮
        addPauseBtn: function(props) {
            //var pauseBtn=$('<span class="pauseBtn">暂停</span>');
            var pauseBtn = $('<a class="icon icon-play2 disabled" href="#" title="暂停" ></a>');
            pauseBtn.click(function() {
                if (pauseBtn.hasClass("disabled")) return
                pauseBtn.addClass("disabled");
                pauseBtn.closest(".webUploader-queue-item").find(".state").html("等待上传");
                var uploadBtn = pauseBtn.closest(".operateBar").find(".icon-upload");
                uploadBtn.removeClass("disabled");
                props.uploader.stop(true, props.file);
            });
            return pauseBtn
        },
        // 文件进度文本提示
        addUploadProgress: function(props) {
            var progress = $("<span class='progressInfo' >0%</span>")
        }
    }

});

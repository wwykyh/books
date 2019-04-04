/**
 * js 电子书相关的js文件
 */
//提示用户是不是要确定这个名称
var bookName = "";

function confirmName() {
    var originalValue = $("#bookName").val();
    if (!confirm("确定上传文件名为  " + originalValue + '  吗？')) {
        $("#bookName").val("");
    } else {
        $("#bookName").val(originalValue);
    }
}

// 判断是不是电子书
function judageEbook() {
    var ebook2 = $("input[name='ebookFile']");
    var tempName = "";
    for (var i = 0; i < ebook2.length; i++) {
        var ebook = ebook2[i].value;
        var suff = ebook.substr(ebook.indexOf(".")).toLocaleLowerCase();
        tempName = ebook.substr(ebook.lastIndexOf("\\") + 1).trim() + "；";
        var flag = contain(suff);
        if (!flag) {
            alert("你上传的文件不符合要求,请重新选择文件!");
            ebook2.val("");
        } else {
            alert("上传文件合法");
        }
        bookName += tempName;

    }
    $("#bookName").val(bookName);
}

function contain(suff) {
    var flag = false;
    var fileSuff = [".exe", ".txt", ".html", ".chm", ".hlp", ".epub", ".hlp", ".wdl", ".lit", ".ceb", ".caj", ".pdf", ".abm", ".doc", ".docx"];
    for (var i = 0; i < fileSuff.length; i++) {
        if (suff === fileSuff[i]) {
            flag = true;
            break;
        } else {
            flag = false;
        }
    }
    return flag;
}

// 电子书添加
function add() {
    requirejs(['jquery', 'artdialog'], function ($) {
        $(function () {
            art.dialog.open('eBookFile/page/uploadAdd', {
                title: '上传添加',
                width: 480,
                height: 300,
                id: "uploadPage",
                lock: false,
                resize: false,
                drag: false,
                cancelVal: '关闭',
                cancel: true,
                button: [{
                    name: '确定', callback: function () {
                        var fileObj = this.iframe.contentWindow.$('#file');
                        // console.info(fileObj[0].files); // $('#file')[0].files[0]
                        var file = this.iframe.contentWindow.$("#file").val();
                        var typeId = this.iframe.contentWindow.$("#type").val();
                        var typeValue = this.iframe.contentWindow.$("#type option:selected").text();
                        var tszl = this.iframe.contentWindow.$("#tszl").val();
                        var ms = this.iframe.contentWindow.$("#ms").val();
                        // console.info(file), console.info(typeId), console.info(typeValue), console.info(tszl), console.info(ms);
                        if (file !== "" && typeId !== "" && tszl !== "") {

                            // 动态创建div
                            var LV_Header = $(".LV_Header");
                            var newNode = $("<form class=\"uploadForm\" method=\"post\" enctype=\"multipart/form-data\">" +
                                "<div class=\"file_list\" style=\"clear:both;margin-top: 5px;\">\n" +
                                "                <div style=\"width: 10%; padding-left:4px;\">\n" +
                                "                    <input name=\"che\" type=\"checkbox\">\n" +
                                "                </div>\n" +
                                "                <div class='file' id=\"file\" style=\"text-align: center; text-indent: 20px;width:20%;\">\n" +
                                "                    <span>文件</span><input type='file' name='eBookFile' style='visibility: hidden'/> \n" +
                                "                </div>\n" +
                                "                <div class='type' id=\"type2\" style=\"text-align: center;width:20%;\">\n" +
                                "                    <span>类型</span><input type='hidden' name='typeId'/>\n" +
                                "                </div>\n" +
                                "                <div class='tszl' id=\"tszl2\" style=\"width: 20%; text-align: center;\">\n" +
                                "                    <span>种类</span><input type='hidden' name='tszl'/>\n" +
                                "                </div>\n" +
                                "                <div class='ms' id=\"ms2\" style=\"width: 20%; text-align: center;\">\n" +
                                "                    <span>备注</span><input type='hidden' name='ms'/>\n" +
                                "                </div>\n" +
                                "            </div>" +
                                "</form>");
                            // 为隐藏表单赋值
                            $(newNode).find(".file input")[0].files = fileObj[0].files;
                            $(newNode).find(".type input").val(typeId);
                            $(newNode).find(".tszl input").val(tszl);
                            $(newNode).find(".ms input").val(ms);
                            // 为界面显示赋值
                            $(newNode).find(".file span").html(file.substr(file.lastIndexOf("\\") + 1));
                            $(newNode).find(".type span").html(typeValue);
                            $(newNode).find(".tszl span").html(tszl);
                            $(newNode).find(".ms span").html(ms);
                            LV_Header.append(newNode);
                        } else {
                            // alert("请在添加文件界面将参数填写完整！");
                            return true;
                        }
                    },
                    focus: true
                }]
            });
        });
    });
}

// 删除选中电子书
function del() {
    $(".file_list input:checked").each(function () {
        var parent = $(this).parent().parent();
        // console.info("element parent "+parent.html());
        parent.remove();
    });
}
//  全选
function checkAll() {
    $("input[name='che']").each(function () {
        this.checked = !this.checked;
    });
}
// 上传 ajax提交表单
function up() {
    var success = "";
    $(".file_list input:checked").each(function () {
        var form = $(this).parents(".uploadForm");
        var formData = new FormData(form[0]);
        // console.info(form.find("input[name='eBookFile']")[0].files[0]);
        $.ajax({
            type: "post",
            data: formData,
            url: "eBookFile/eBook",
            async: false,
            cache: false,
            contentType: false, //必须
            processData: false, //必须
            success: function (data) {
                success = data;
            }
        });
        form.html("");
    });
    if (success === "success") {
        alert("文件上传成功！");
    } else if (success !== "error") {
        alert("文件上传失败！");
    }
}



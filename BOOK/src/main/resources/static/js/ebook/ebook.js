/**
 * js 电子书相关的js文件
 */
//提示用户是不是要确定这个名称

function confirmName() {
    var ebook = $("#ebookFile").val();
    var value= ebook.substr(ebook.lastIndexOf("\\") + 1).trim();
    var originalValue = $("#bookName").val();
    $("#bookName").val(value);
    if (!confirm("确定上传文件名为" + originalValue + '吗？')) {
        $("#bookName").val("");
    }else{
        $("#bookName").val(originalValue);
    }
}

// 判断是不是电子书
function judageEbook() {
    var ebook = $("#ebookFile").val();
    var suff = ebook.substr(ebook.indexOf(".")).toLocaleLowerCase();
    $("#bookName").val(ebook.substr(ebook.lastIndexOf("\\") + 1).trim());
    var flag = contain(suff);
    if (!flag) {
        alert("你上传的文件不符合要求,请重新选择文件!");
        $("#ebookFile").val("");
        $("#bookName").val("");
    } else {
        alert("上传文件合法");
    }
}

function contain(suff) {
    var flag = false;
    var fileSuff = [".exe", ".txt", ".html", ".chm", ".hlp", ".epub", ".hlp", ".wdl", ".lit", ".ceb", ".caj", ".pdf", ".abm"];
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



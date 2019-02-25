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
function judageEbook(n) {
    var ebook2 = $("input[name='ebookFile']");
    var tempName="";
    for (var i = 0; i < ebook2.length; i++) {
        if (i === n) {
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
    }
    $("#bookName").val(bookName);
}

function contain(suff) {
    var flag = false;
    var fileSuff = [".exe", ".txt", ".html", ".chm", ".hlp", ".epub", ".hlp", ".wdl", ".lit", ".ceb", ".caj", ".pdf", ".abm",".doc",".docx"];
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



/**
 * 让用户管理自己的稿件的js文件;专用于稿件详情页面,因为需要用到页面刷新而不同于adminContribution.js里的移除元素;
 */

/*建立模态框对象*/
var modalBox = {};//普通操作的模态框
var topicCategoryModalBox = {};//修改分类操作的模态框
var plate = "";//用于记录点击退稿按钮时的所属稿件;
var categoryId = ""; //用于记录修改分类操作所需的分类id数据;
var sendBackContribution;//用于记录点击退稿按钮的所属稿件元素,方便移除;
/*模态框相关事件的js代码*/
(function () {

    /*获取模态框*/
    modalBox.modal = document.getElementById("myModal");
    topicCategoryModalBox.modal = document.getElementById("topicCategoryModal");

    /*获得关闭按钮*/
    modalBox.closeBtn = document.getElementById("closeBtn");
    topicCategoryModalBox.closeBtn = document.getElementById("closeTopicBtn");

    /*模态框显示*/
    modalBox.show = function () {
        console.log(this.modal);
        this.modal.style.display = "block";
    }
    topicCategoryModalBox.show = function () {
        console.log(this.modal);
        this.modal.style.display = "block";
    }

    /*模态框关闭*/
    modalBox.close = function () {
        this.modal.style.display = "none";
    }
    topicCategoryModalBox.close = function () {
        this.modal.style.display = "none";
    }

    /*当用户点击模态框内容之外的区域，模态框也会关闭*/
    modalBox.outsideClick = function () {
        var modal = this.modal;
        var modal1 = topicCategoryModalBox.modal;
        window.onclick = function (event) {
            if (event.target == modal) {
                modal.style.display = "none";
            }
            if (event.target == modal1) {
                modal1.style.display = "none";
            }
        }
    }

    /*模态框初始化*/
    topicCategoryModalBox.init = function () {
        var that1 = this;
        that1.closeBtn.onclick = function () {
            that1.close();
        }
       /* that1.outsideClick();*///已经交给普通模态框去绑定此点击事件;
    }
    topicCategoryModalBox.init();

    modalBox.init = function () {
        var that = this;
        this.closeBtn.onclick = function () {
            that.close();
        }
        this.outsideClick();
    }
    modalBox.init();

    /*点击退稿按钮弹出模态框*/
    $(document).on('click','.btn-sendBack-contribution',function () {
        sendBackContribution = $(this).parent().parent();
         plate = $(this).attr("plate");
        modalBox.show();
    });

    /*点击修改专辑分类弹出对应的模态框*/
    $(document).on('click','.btn-editCategory-contribution',function () {
        topicCategoryId = $(this).attr("categoryId");
        var topicCategoryName = $(this).attr("categoryName");
        if (0 == topicCategoryId) {
            $("#btnGroup-edit-topicCategory").hide();
            $("#btnGroup-add-topicCategory").show();
        }else {
            $("#btnGroup-edit-topicCategory").show();
            $("#btnGroup-add-topicCategory").hide();
        }
        $("#categoryNameText").text(" "+topicCategoryName);
        topicCategoryModalBox.show();
    });

})();



/*点击保存修改按钮提交分类的修改*/
$(".btn-edit-topicCategory").click(function () {
    var categoryId = $(".categoryOption:checked").val();//如果使用$(".categoryOption[checked=checked]").val()则只能取出页面生成时的选中的哪一项,取不出后来改变之后的选中项;
    var topicId = $(".btn-editCategory-contribution").attr("topicId");//如果使用$(".categoryOption[checked=checked]").val()则只能取出页面生成时的选中的哪一项,取不出后来改变之后的选中项;
    alert(categoryId);
    $.ajax({
        url:"/admin/topic/category/edit",
        type:"POST",
        data:{"topicCategoryId":categoryId,"topicId":topicId},
        success:function (result) {
            alert(result.msg);
            window.location.reload();
        }
    });
});

/*点击取消关闭修改分类的模态框并清空输入框*/
$(".btn-cancel-topicCategory").click(function () {
    $("categoryRankInput").empty();
    topicCategoryModalBox.close();
});

/*快捷选择退稿理由填充进输入框;*/
$(".autoStuff").click(function () {
    var info = $(this).text();
    /*alert(info);*/
    $("#sendBackTextarea").append(info);
});

/*点击取消关闭模态框并清空输入框*/
$("#cancel").click(function () {
    $("#sendBackTextarea").empty();
    modalBox.close();
});

/*点击提交按钮取出退稿理由关闭模态框并清空输入框*/
$("#sendBack").click(function () {
    var sendBackInfo = $("#sendBackTextarea").text();
    /*alert(plate);*/
    $("#sendBackTextarea").empty();
    modalBox.close();
    $.ajax({
        url:"/admin/contribute/sendBack",
        type:"POST",
        data:{"sendBackInfo":sendBackInfo,"plate":plate},
        success:function (result) {
            alert(result.msg);
            if (101 == result.code){
                window.location.href="/login";
            }
            window.location.reload();

        }
    });
});



$(".btn-delete-contribution").click(function () {
    var btn = $(this);
    var plate = $(this).attr("plate");
    /*alert(plate);*/
    if (confirm("确认将此稿件放入回收站?")){
        $.ajax({
            url:"/admin/contribute/deprecated",//将稿件状态设置成4的方法
            type:"POST",
            data:{"plate":plate},
            success:function (result) {
                alert(result.msg);
                if (101 == result.code){
                    window.location.href="/login";
                }
               window.location.reload();
            }

        });
    }

});

/*将稿件设置为隐藏*/
$(".btn-hide-contribution").click(function () {
    var btn = $(this);
    var plate = $(this).attr("plate");
    /*alert(plate);*/
    if (confirm("确认隐藏此稿件?")){
        $.ajax({
            url:"/admin/contribute/hide",
            type:"POST",
            data:{"plate":plate},
            success:function (result) {
                alert(result.msg);
                if (101 == result.code){
                    window.location.href="/login";
                }
                window.location.reload();
            }

        });
    }
});

/*将稿件设置为展示*/
$(".btn-show-contribution").click(function () {
    var btn = $(this);
    var plate = $(this).attr("plate");
    /*alert(plate);*/
    if (confirm("确认展示此稿件?")){
        $.ajax({
            url:"/admin/contribute/show",
            type:"POST",
            data:{"plate":plate},
            success:function (result) {
                alert(result.msg);
                if (101 == result.code){
                    window.location.href="/login";
                }
                window.location.reload();
            }

        });
    }
});

/*显示被退回的稿件的信息*/
$(".btn-info-contribution").click(function () {
    var info = $(this).children("input").attr("info");
    alert(info);
});

/*显示申请恢复需要的信息*/
$(".btn-report-contribution").click(function () {
    var plate = $(this).attr("plate");
    var id = plate.substring(6);
    alert("请联系客服恢复id值为: "+id+" 的topic专辑稿件!");
});



/*将稿件从回收站恢复*/
$(".btn-recover-contribution").click(function () {
    var btn = $(this);
    var plate = $(this).attr("plate");
    /*alert(plate);*/
    if (confirm("确认从回收站恢复此稿件?")){
        $.ajax({
            url:"/admin/contribute/recover",
            type:"POST",
            data:{"plate":plate},
            success:function (result) {
                alert(result.msg);
                if (101 == result.code){
                    window.location.href="/login";
                }
                window.location.reload();
            }
        });
    }
});



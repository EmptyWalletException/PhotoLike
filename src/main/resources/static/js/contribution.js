/**
 * 让用户管理自己的稿件的js文件;管理收藏的js代码直接写在相应的页面中;
 */

$(".btn-delete-contribution").click(function () {
    var btn = $(this);
    var plate = $(this).attr("plate");
    /*alert(plate);*/
    if (confirm("确认将此稿件放入回收站?")){
        $.ajax({
            url:"/user/contribute/deprecated",//将稿件状态设置成4的方法
            type:"POST",
            data:{"plate":plate},
            success:function (result) {
                if (101 == result.code){
                    alert("请先登录再操作!");
                    window.location.href="/login";
                }
                alert(result.msg);
                btn.parent().parent().remove();
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
            url:"/user/contribute/hide",
            type:"POST",
            data:{"plate":plate},
            success:function (result) {
                if (101 == result.code){
                    alert("请先登录再操作!");
                    window.location.href="/login";
                }
                alert(result.msg);
                btn.parent().parent().remove();
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
            url:"/user/contribute/show",
            type:"POST",
            data:{"plate":plate},
            success:function (result) {
                if (101 == result.code){
                    alert("请先登录再操作!");
                    window.location.href="/login";
                }
                alert(result.msg);
                btn.parent().parent().remove();
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
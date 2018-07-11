/*用于收藏,移除收藏,以及删除投稿操作的js*/

/*灰色爱心的收藏按钮,点击后收藏;用于topic和essay*/
$(document).on('click','.btn-like',function () {
    like(this);
    $(this).attr("class", "btn-unlike icon-faved");
});

/*灰色爱心的收藏按钮,点击后收藏;用于story*/
$(document).on('click','.btn-like-story',function () {
    like(this);
    $(this).attr("class", "btn-unlike-story").children().attr("class", "icon-essay-faved");
});

/*红色爱心的收藏按钮,点击后取消收藏;用于topic和essay*/
$(document).on('click','.btn-unlike',function () {
    unlike(this);
    $(this).attr("class", "btn-like icon-fav");
});

/*红色爱心的收藏按钮,点击后取消收藏;用于story*/
$(document).on('click','.btn-unlike-story',function () {
    unlike(this);
    $(this).attr("class","btn-like-story").children().attr("class","icon-essay-fav");
});

function like(ele) {
    var plate = $(ele).attr("plate");
    $.ajax({
        url:"/json/user/like",
        type:"POST",
        data:{"plate":plate},
        success:function (result) {
            if (101 == result.code){
                alert("请先登录再操作!");
                window.location.href="/login";
            }
        }
    });
}

function unlike(ele) {
    var plate = $(ele).attr("plate");
    $.ajax({
        url:"/json/user/unlike",
        type:"POST",
        data:{"plate":plate},
        success:function (result) {
            if (101 == result.code){
                alert("请先登录再操作!");
                window.location.href="/login";
            }
        }
    });
}

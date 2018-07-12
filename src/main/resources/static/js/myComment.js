var subcommentPageMap;//用于储存页面加载后发送ajax返回此页所有父评论的子评论集合;
var praiseCommentIds;//用于储存后台返回的记录所有被赞过的子评论的id的拼接字符串;
/*页面加载后遍历出评论和子评论生成页面元素*/
$(function () {
    /*遍历取出所有父评论的id,发送给后端,获得返回值取出子评论,在对应的父评论下生成子评论*/
    var supcommentIds ="";
    $.each($(".subcomments"),function () {
        supcommentIds  += $(this).attr("commentId")+",";

    });
    //测试后确定已经成功取出,alert的结果为 15,14,13,12,11,10,9,8,7,6,  最后多了一个","符号需要处理;
    //alert(supcommentIds);

    //处理一下最后多出的一个逗号;
    supcommentIds = supcommentIds.substring(0,supcommentIds.length-1);

    $.ajax({
        url:"/comment/json/subcomments",
        type:"post",
        data:{"supcommentIds":supcommentIds},
        success:function (result) {
            //注意后端返回的是一个map,map键为父评论id,值为子评论page,
             subcommentPageMap = result.extend.subcommentPageMap;
            praiseCommentIds = result.extend.praiseCommentIds;
            //遍历并生成每一个父评论下的所有子评论;
            for (var supcommentId in subcommentPageMap) {
                loadSubcomment(supcommentId,1);
            }
            initPraiseStatus(praiseCommentIds);
        }
    });



    /*点击评论框的"发布"按钮提交评论*/
    $("#commentAdd").click(function () {
        var comment = $("#commentEditor").val();
        var plateAndId = $(this).parent().next("input").val();
        if ("" == comment.trim()){
            alert("请输入有效的评论!")
        }else if (1000 <= comment.length){
            alert(comment.length+"评论字数不得大于500个字!")
        }else {
            $.ajax({
                url:"/comment/json/add",
                type:"POST",
                data:{"comment":comment,"plateAndId":plateAndId},
                success:function (result) {
                    alert(result.msg);
                    if (200 == result.code){
                        location.reload(true);//重新请求当前页面;
                    }
                }
            });
        }
    });

    /*点击父评论的回复按钮显示出回复框,再次点击隐藏*/
    $(".btn-action-reply").click(function () {
        var subcommentEditDiv = $(this).parent().next("div");
        if ("display:none;" == subcommentEditDiv.attr("style")){
            subcommentEditDiv.attr("style","display:block;");
        } else {
            subcommentEditDiv.attr("style","display:none;");
        }
    });

    /*点击父评论的回复框的"取消按钮"隐藏此评论框*/
    $(".btn-action-cancel").click(function () {
        $(this).parents("div[class='sub-comment clearfix ']").attr("style","display:none;");
    });

    /*点击父评论的回复框的"评论"按钮提交子评论*/
    $(".btn-at-comment-submit").click(function () {
        var subcomment = $(this).parents("div[class='toolbar clearfix']").prev("div").children("textarea").val();
        var supcommentId = $(this).parent().parent().next("input").val();
        var plateAndId = $(this).parent().parent().next("input").next("input").val();
        if ("" == subcomment.trim()){
            alert("请输入有效的评论!")
        }else if (1000 <= comment.length){
            alert(comment.length+"评论字数不得大于500个字!")
        }else {
            $.ajax({
                url: "/comment/json/subcomment/add",
                type: "POST",
                data: {"subcomment": subcomment, "supcommentId": supcommentId, "plateAndId": plateAndId},
                success: function (result) {
                    alert(result.msg);
                    if (200 == result.code) {
                        location.reload(true);//重新请求当前页面;
                    }
                }
            });
        }
    });


});

/*根据传入的父评论id和子评论页数pageNum遍历并加载子评论*/
function loadSubcomment(supcommentId,pageNum){
    var pageNum = pageNum;
    /*开始遍历生成子评论区域*/
    var subcomments = subcommentPageMap[supcommentId].content;
    var ele = ".subcomments[commentId='"+supcommentId+"']";
    var subcommentDiv = $(ele);
    var subcommentIndex;//用于判断当前子评论数量,判断是否生成"折叠子批评"按钮;

    /*第一次生成时创建头区域*/
    if (1 == pageNum) {
        subcommentDiv.empty();
        /*生成当前父评论的总子评论区域头*/
        subcommentDiv.append(
            "<div class='sub-comment clearfix '> " +
            "      <div class='clearfix items subcomment' > "+
            "      </div>"+
            "</div>"
        );
    }

    $.each(subcomments,function (index,subcomment) {
        if((pageNum-1)*10 <= index && (pageNum)*10 > index){//遍历符合页码范围要求的子评论
            subcommentIndex = index;//将当前的下标保存,用于判断当下标大于10时显示"折叠子评论"按钮;
            var commentDate = new Date(subcomment.creatTime);
            subcommentDiv.children("div").children(".subcomment").append(
                "                               <div class='item subcommentItem' subcommentId='" +subcomment.id + "'  >" +
                "                                    <a href='/user?userId=" +subcomment.author.id+ "' class='avatar-wrapper' target='_blank' >" +
                "                                        <img src='"+subcomment.author.imgAddr+"' alt='" +subcomment.author.nickname+ "' class='avatar rounded' >" +
                "                                    </a>" +
                "                                    <div class='item-wrapper'>" +
                "                                        <a href='/user?userId=" +subcomment.author.id+ "' class='username'" +
                "                                           target='_blank'  >" +subcomment.author.nickname+ "</a>" +
                "                                        <div class='comment-ct'>" +
                "                                            <p class='the-comment' data-vote='1' data-ct='1512961397' >" +
                "                                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +subcomment.content+ "</p>" +
                "                                        </div>" +
                "                                        <div class='helper clearfix'>" +commentDate.toLocaleDateString()+
                "                                            <a commentId='"+subcomment.id+"'"+
                "                                               data-width='235' class='btn-vote btn-action-praise '" +
                "                                               href='javascript:void(0);' rel='nofollow'>  &nbsp;<i class='icon-vote'></i>" +
                "                                                <span>赞</span></a>" +
                "                                            <span class='vote-count' >"+subcomment.praiseNumber+"赞</span>" +
                "                                        </div>" +
                "                                    </div>" +
                "                                    <form action='#'" +
                "                                          class='editor-wrapper form-comment-at hide'>" +
                "                                        <div class='editor'>" +
                "                                            <textarea name='content' class='editor-comment-at' spellcheck='false'" +
                "                                                      autocomplete='off'></textarea>" +
                "                                        </div>" +
                "                                        <div class='toolbar clearfix'>" +
                "                                            <div class='btn-group'>" +
                "                                                <a href='javascript:void(0);'" +
                "                                                   class='btn-link btn-action-cancel-at'>取消</a>" +
                "                                                <button class='btn btn-positive btn-not-ready rounded btn-at-comment-submit'" +
                "                                                        data-tipid='commentSubmitDialog'" +
                "                                                        data-remote='http://www.luoo.net/login/dialog' data-width='235'>" +
                "                                                    评论" +
                "                                                </button>" +
                "                                            </div>" +
                "                                        </div>" +
                "                                        <input type='hidden' name='app_id' value='1'>" +
                "                                        <input type='hidden' name='res_id' value='977'>" +
                "                                        <input type='hidden' name='comment_at' value='611072'>" +
                "                                    </form>" +
                "                               </div>"
            );
        }



    });

    /*第一次生成时同时生成翻页操作区域*/
    if(1 == pageNum){
        /*生成子评论翻页区域*/
        subcommentDiv.children("div").append(
            " <div class='paginator'  style='float: right;margin-top: 10px;padding: 0 15px;'>" +
            " </div>"
        );

    }

    /*生成加载更多子评论按钮*/
    var moreA = subcommentDiv.children("div").children(".paginator").children(".more")
    if (subcomments.length > 10 && moreA.length <= 0){//当子评论数量大于10个则生成"加载更多"按钮;
        subcommentDiv.children("div").children(".paginator").append(
            "                <a class='page more' href='javascript:void(0);' style='height: 20px;line-height: 20px;float: right;' pageNum='2'>▼更多子评论</a>"
        );
    }

    /*判断是否还有更多评论,决定是否要隐藏此按钮*/
    if (subcomments.length <= pageNum*10 && moreA.length > 0){
        subcommentDiv.children("div").children(".paginator").children(".more").text("▼已加载全部").removeAttr("class").removeAttr("href").attr("style","height: 20px;line-height: 20px;float: right;color:#7d7d7d");
    }


    /*生成折叠子评论按钮*/
    var foldA =subcommentDiv.children("div").children(".paginator").children(".flodSubcomments");
    if(subcommentIndex >= 10 && foldA.length <= 0){//当下标大于10时则生成"折叠子评论按钮";
        subcommentDiv.children("div").children(".paginator").append(
            "<a class='page flodSubcomments' href='javascript:void(0);' style='height: 20px;line-height: 20px;' >▲折叠子评论</a>"
        );

    }

}

function initPraiseStatus(praiseCommentIds){
    <!--判断子评论是否被点赞并显示不同状态的代码-->
    if ("" != praiseCommentIds.trim()){
        /*alert(favEssayIds);*/
        var ids =praiseCommentIds.split(',');
        for(var index in ids){
            var ele =".btn-vote[commentId='"+ids[index]+"']";
            $(ele).attr("class","btn-vote btn-action-praised");
            $(ele).children("i").attr("class","icon-vote-actived");
        }
    }
}

/*点击"折叠子评论按钮"的事件,等同于刷新掉超出10个子评论的部分*/
$(document).on('click','.flodSubcomments',function () {
    var supcommentId =  $(this).parents(".subcomments").attr("commentid");
    loadSubcomment(supcommentId,1);
    initPraiseStatus(praiseCommentIds);
});

/*点击"加载更多子评论"的事件*/
$(document).on('click','.more',function () {
    var supcommentId =  $(this).parents(".subcomments").attr("commentid");
    var pageNum = $(this).attr("pageNum");//利用按钮里的属性来记录需要加载的子评论页码;
    loadSubcomment(supcommentId,pageNum);
    initPraiseStatus(praiseCommentIds);
    $(this).attr("pageNum",parseInt(pageNum)+1);
});


/*点赞*/
$(document).on('click','.btn-action-praise',function () {
    var commentId = $(this).attr("commentId");
    var btn = $(this);
    /*alert(commentId);*/
    $.ajax({
        url:"/comment/json/praise",
        type:"POST",
        data:{"commentId":commentId},
        success:function (result) {
            if (101 == result.code){
                alert(result.msg)
                window.location.href="/login";
            } else {
                btn.attr("class","btn-vote btn-action-praised");
                btn.children("i").attr("class","icon-vote-actived");
            }
        }
    })
});

/*取消点赞*/
$(document).on('click','.btn-action-praised',function () {
    var commentId = $(this).attr("commentId");
    var btn = $(this);
    /*alert(commentId);*/
    $.ajax({
        url:"/comment/json/cancelPraise",
        type:"POST",
        data:{"commentId":commentId},
        success:function (result) {
            if (101 == result.code){
                alert(result.msg)
                window.location.href="/login";
            } else {
                btn.attr("class","btn-vote btn-action-praise");
                btn.children("i").attr("class","icon-vote");
            }
        }
    })
});





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
           var subcommentPageMap = result.extend.subcommentPageMap;
                //遍历并生成每一个父评论下的所有子评论;
                for (var supcommentId in subcommentPageMap) {
                    //alert(supcommentId);  //输出为1,因为数据库中只有id为1的父评论有子评论
                   // console.log(subcommentPageMap[1]); //测试成功,输出了父评论的10条子评论;

                    /*开始遍历生成子评论区域*/
                    var subcomments = subcommentPageMap[supcommentId].content;
                    var ele = ".subcomments[commentId='"+supcommentId+"']";
                    var subcommentDiv = $(ele);
                    subcommentDiv.empty();
                    /*生成总子评论区域头,注意在遍历完后补上尾*/
                   /* subcommentDiv.append(
                        "             <div class='clearfix items' id='subcommentItems'> "+
                        "</div>"
                    );*/
                    subcommentDiv.append("<div class='sub-comment clearfix '> " +

                            "             <div class='clearfix items' id='subcommentItems'> "+
                            "</div>"+
                            "</div>"
                        );

                    $.each(subcomments,function (index,subcomment) {
                        $("#subcommentItems").append(
                            "                               <div class='item subcommentItem' subcommentId='" +subcomment.id + "'  >" +
                            "                                    <a href='/user?userId=" +subcomment.author.id+ "' class='avatar-wrapper' target='_blank' >" +
                            "                                        <img src='"+subcomment.author.imgAddr+"' alt='" +subcomment.author.nickname+ "' class='avatar rounded' >" +
                            "                                    </a>" +
                            "                                    <div class='item-wrapper'>" +
                            "                                        <a href='/user?userId=" +subcomment.author.id+ "' class='username'" +
                            "                                           target='_blank'  >" +subcomment.author.nickname+ "</a>" +
                            "                                        <div class='comment-ct'>" +
                            "                                            <p class='the-comment' data-vote='1' data-ct='1512961397' >" +
                            "                                                " +subcomment.content+ "</p>" +
                            "                                        </div>" +
                            "                                        <div class='helper clearfix'>" +
                            "                                            2017-12-11" +
                            "                                            <a data-res='611072' data-app='5' data-tipid='commentVoteDialog611072'" +
                            "                                               data-width='235' class='btn-vote btn-action-vote hide'" +
                            "                                               href='javascript:void(0);' rel='nofollow'><i class='icon-vote'></i>" +
                            "                                                <span>赞</span></a>" +
                            "                                            <a href='javascript:void(0);' class='btn-reply btn-action-reply hide'" +
                            "                                               data-res='" +subcomment.author.id+ "' data-user='" +subcomment.author.nickname+ "' >" +
                            "                                                <span class='icon-reply'></span>" +
                            "                                                回复" +
                            "                                            </a>" +
                            "                                            <a href='javascript:void(0);' class='comment-more-item btn-report hide'" +
                            "                                               data-res='611072' data-app='5' data-tipid='commentReportDialog611072'" +
                            "                                               data-width='235' rel='nofollow'><span class='icon-report'></span><span" +
                            "                                                    class='report-status'> 举报</span></a>" +
                            "                                            <span class='vote-count' >0赞</span>" +
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
                        "                               </div>");
                    });

                }
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





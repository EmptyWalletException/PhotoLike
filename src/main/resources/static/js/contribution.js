$(".btn-delete-contribution").click(function () {
    var btn = $(this);
    var plate = $(this).attr("plate");
    /*alert(plate);*/
    if (confirm("确认删除此稿件?")){
        $.ajax({
            url:"/user/contribute/delete",
            type:"POST",
            data:{"plate":plate},
            success:function () {
                btn.parent().parent().remove();
            }

        });
    }

});
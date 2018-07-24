/**
 * 管理员管理稿件的js文件;管理收藏的js代码直接写在相应的页面中;
 */

/*建立模态框对象*/
var topicCategoryModalBox = {};
var cityModalBox = {};
var topicCategoryId = "";//用于记录要操作的专辑分类的id;
var cityId = "";//用于记录要操作的城市的id;
/*模态框相关事件的js代码*/
(function () {

    /*获取模态框*/
    topicCategoryModalBox.modal = document.getElementById("topicCategoryModal");
    cityModalBox.modal = document.getElementById("cityCategoryModal");

    /*获得关闭按钮*/
    topicCategoryModalBox.closeBtn = document.getElementById("closeTopicBtn");
    cityModalBox.closeBtn = document.getElementById("closeCityBtn");

    /*模态框显示*/
    topicCategoryModalBox.show = function () {
        console.log(this.modal);
        this.modal.style.display = "block";
    }
    cityModalBox.show = function () {
        console.log(this.modal);
        this.modal.style.display = "block";
    }

    /*模态框关闭*/
    topicCategoryModalBox.close = function () {
        this.modal.style.display = "none";
    }
    cityModalBox.close = function () {
        this.modal.style.display = "none";
    }

    /*当用户点击模态框内容之外的区域，模态框也会关闭*/
    topicCategoryModalBox.outsideClick = function () {
        var modal1 = topicCategoryModalBox.modal;
        var modal2 = cityModalBox.modal;
        window.onclick = function (event) {
            /*
                注意如果点击模态框外面关闭模态框的window事件如果分开成两个模态框方法写,
                后面的window事件会覆盖掉前面的导致点击外面区域是city模态框关闭不了,
                所以这里注释掉city模态框的outsideClick方法,一起写在这里
            */
            if (event.target == modal1) {
                modal1.style.display = "none";
            }
            if (event.target == modal2) {
                modal2.style.display = "none";
            }
        }


    }

    /*cityModalBox.outsideClick = function () {
        var modal2 = cityModalBox.modal;
        window.onclick = function (event2) {
            if (event2.target == modal2) {
                modal2.style.display = "none";
            }
        }
    }*/


    /*模态框初始化*/
    cityModalBox.init = function () {
        var that2 = this;
        that2.closeBtn.onclick = function () {
            that2.close();
        }
       /* that2.outsideClick();*///已经合并到topicCategory中,否则后面的topicCategoryModal初始化时会覆盖此模态框的外部点击关闭事件;
    }
    cityModalBox.init();
    topicCategoryModalBox.init = function () {
        var that1 = this;
        that1.closeBtn.onclick = function () {
            that1.close();
        }
        that1.outsideClick();
    }
    topicCategoryModalBox.init();

    /*点击专辑分类名字弹出对应的模态框*/
    $(document).on('click','.topicCategory',function () {
        topicCategoryId = $(this).attr("categoryId");
        var topicCategoryName = $(this).attr("categoryName");
        var topicCategoryRank = $(this).attr("categoryRank");
        if (0 == topicCategoryId) {
            $("#btnGroup-edit-topicCategory").hide();
            $("#btnGroup-add-topicCategory").show();
        }else {
            $("#btnGroup-edit-topicCategory").show();
            $("#btnGroup-add-topicCategory").hide();
        }
        $("#categoryNameInput").val(topicCategoryName);
        $("#categoryRankInput").val(topicCategoryRank);
        topicCategoryModalBox.show();
    });
    /*点击城市名字弹出对应的模态框*/
    $(document).on('click','.city',function () {
        cityId = $(this).attr("cityId");
        var cityName = $(this).attr("cityName");
        var cityRank = $(this).attr("cityRank");
        if (0 == cityId) {
            $("#btnGroup-edit-city").hide();
            $("#btnGroup-add-city").show();
        }else {
            $("#btnGroup-edit-city").show();
            $("#btnGroup-add-city").hide();
        }
        $("#cityNameInput").val(cityName);
        $("#cityRankInput").val(cityRank);
        cityModalBox.show();
    });

})();

/*点击保存修改按钮提交分类的修改*/
$(".btn-edit-topicCategory").click(function () {
    var name = $("#categoryNameInput").val();
    var rank = $("#categoryRankInput").val();
    $.ajax({
        url:"/admin/topicCategory/edit",
        type:"POST",
        data:{"topicCategoryId":topicCategoryId,"topicCategoryName":name,"topicCategoryRank":rank},
        success:function (result) {
            alert(result.msg);
            window.location.reload();
        }
    });
});

$(".btn-delete-topicCategory").click(function () {
    $.ajax({
        url:"/admin/topicCategory/delete",
        type:"POST",
        data:{"topicCategoryId":topicCategoryId},
        success:function (result) {
            alert(result.msg);
            window.location.reload();
        }
    });
});

$(".btn-edit-city").click(function () {
   var name = $("#cityNameInput").val();
   var rank = $("#cityRankInput").val();
    $.ajax({
        url:"/admin/city/edit",
        type:"POST",
        data:{"cityId":cityId,"cityName":name,"cityRank":rank},
        success:function (result) {
            alert(result.msg);
            window.location.reload();
        }
    });
});

$(".btn-delete-city").click(function () {
    $.ajax({
        url:"/admin/city/delete",
        type:"POST",
        data:{"cityId":cityId},
        success:function (result) {
            alert(result.msg);
            window.location.reload();
        }
    });
});

/*点击取消关闭模态框并清空输入框*/
$("#cancel1").click(function () {
    $("input").empty();
    topicCategoryModalBox.close();
});
$("#cancel2").click(function () {
    $("input").empty();
    cityModalBox.close();
});







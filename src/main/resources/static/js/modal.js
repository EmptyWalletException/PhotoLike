(function() {
    /*建立模态框对象*/
    var modalBox = {};
    /*获取模态框*/
    modalBox.modal = document.getElementById("myModal");
    /*获得trigger按钮*/
    modalBox.triggerBtn = document.getElementById("lnEditAvatar");
    /*获得关闭按钮*/
    modalBox.closeBtn = document.getElementById("closeBtn");
    /*模态框显示*/
    modalBox.show = function() {
        console.log(this.modal);
        this.modal.style.display = "block";
    }
    /*模态框关闭*/
    modalBox.close = function() {
        this.modal.style.display = "none";
    }
    /*当用户点击模态框内容之外的区域，模态框也会关闭*/
    modalBox.outsideClick = function() {
        var modal = this.modal;
        window.onclick = function(event) {
            if(event.target == modal) {
                modal.style.display = "none";
            }
        }
    }
    /*模态框初始化*/
    modalBox.init = function() {
        var that = this;
        this.triggerBtn.onclick = function() {
            that.show();
        }
        this.closeBtn.onclick = function() {
            that.close();
        }
        this.outsideClick();
    }
    modalBox.init();


    /*加载和裁剪图片的js    开始*/
    var img ;
    var options =
        {
            thumbBox: '.thumbBox',
            spinner: '.spinner',
            imgSrc: '/img/test/testUserHead.jpg'
        }
    var cropper = $('.imageBox').cropbox(options);
    $('#upload-file').on('change', function(){
        var reader = new FileReader();
        reader.onload = function(e) {
            options.imgSrc = e.target.result;
            cropper = $('.imageBox').cropbox(options);
            //图片加载后先自动触发一次裁切事件;
            $('#btnCrop').click();
        }
        reader.readAsDataURL(this.files[0]);
        $('#upload-file').value= "";
    })
    $('#btnCrop').on('click', function(){
        img = cropper.getDataURL();
        $('.cropped').html('');
        $('.cropped').append('<img src="'+img+'" align="absmiddle" style="width:64px;margin-top:4px;box-shadow:0px 0px 12px #7E7E7E;" ><p>64px*64px</p>');
        $('.cropped').append('<img src="'+img+'" align="absmiddle" style="width:128px;margin-top:4px;box-shadow:0px 0px 12px #7E7E7E;"><p>128px*128px</p>');
        $('.cropped').append('<img src="'+img+'" align="absmiddle" style="width:180px;margin-top:4px;box-shadow:0px 0px 12px #7E7E7E;"><p>180px*180px</p>');
    })

    //点击模态框加载后先自动触发一次裁切事件;
    $('#lnEditAvatar').on('click', function(){
        $('#btnCrop').click();
    })

    //点击上传图像按钮时触发文件上传输入框;
    $('#chooseImg').on('click', function(){
        $('#upload-file').click();
    })
    $('#btnZoomIn').on('click', function(){
        cropper.zoomIn();
    })
    $('#btnZoomOut').on('click', function(){
        cropper.zoomOut();
    })
    $("#btnSubmit").click(function () {
       var fd = new FormData();
        img = cropper.getDataURL();
        fd.append('img',img);
        $.ajax({
            url:"/user/headImgUpload",
            type:"POST",
            data:fd,
            async: false,
            contentType:false,
            processData:false,
            cache:false,
            success:function(result){
                alert(result.msg);
                //判断当错误原因是登录超时则重新登录;
               if(101 == result.code){
                    window.location.href="/login";
                }else if (200 == result.code){
                   window.location.href="/user/editInfo";
               }
            }
        });
    });

    /*加载和裁剪图片的js    结束*/

})();

/*修改个性签名*/
$("#lnSaveMood").click(function () {
    var a = $("#txtContent").val();
    if( a.length > 100 ){
        alert("个性签名请控制在100字以内");
    }
    if (a == $("#txtContent").data("value")){
        $("#formMood").hide();
        $("#textMood").show();
    }else{
        $.ajax({
            url:"/user/signature/add",
            type:"POST",
            data:{"signature":a},
            success:function (result) {
                if (200 == result.code){
                    $("#txtMoodCt").text(a);
                    $("#formMood").hide();
                    $("#textMood").show();
                } else {
                    alert(result.msg);
                }
            }
        });
    }

});

/*修改基本信息*/
$("#btnEditSubmit").click(function () {
    // TODO :修改昵称输入框需要前端校验和ajax重复性校验;
    var nickname = $("#nickname").val();
    var gender = $("input:checked").val();
    var cityId= $("#city option:selected").attr("value");

    $.ajax({
        url:"/user/edit",
        type:"POST",
        data:{"nickname":nickname,"gender":gender,"cityId":cityId},
        success:function (result) {
            alert(result.msg);
            if (200 == result.code){
                window.location.href="/user/editInfo";
            } else {

            }


        }
    });

});


var oldNickName = "";//用于在文档加载时储存旧昵称的值,用于判断用户最终是否修改了昵称;

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

    oldNickName = $("#nickname").val();
    /*加载和裁剪图片的js    结束*/

})();

/*修改个性签名*/
$("#lnSaveMood").click(function () {
    var a = $("#txtContent").val();
    if( a.length > 100 ){
        alert("个性签名请控制在100个字符以内");
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

//抽取出来的用户昵称输入框的校验;
function checkNicknameInput(nicknameEle) {
    // 验证用户昵称  /^[a-z0-9_-]{2,10}$)|(^[\u2E80-\u9FFF]{2,10}/
    var inputNickName = $(nicknameEle).val();
    var regNickName = /(^[\u2E80-\u9FFFa-z0-9_-]{2,9}$)/;

    if(!regNickName.test(inputNickName)){
        //alert("昵称格式不正确,请输入2~10位字符,只能出现数字或英文或汉字的组合!");
        showValidateInfo(nicknameEle,"error","请输入2~9位数字或英文或汉字的组合!");
    }else{
        showValidateInfo(nicknameEle,"ok","");
    }

}

//抽取出来的ajax校验用户昵称;
function ajaxCheckNicknameInput(nicknameEle,ajaxEle){
    var inputValue = $(nicknameEle).val();
    $.ajax({
        url:"/user/ajax/checkNickname",
        data:"inputValue="+inputValue,
        type:"POST",
        success:function(result){
            if(200 == result.code){
                showValidateInfo(nicknameEle,"ok",result.msg);
                $(ajaxEle).attr("ajaxCheckNickname","success");//在昵称校验标签上储存一个值用于记录是否通过了后台校验;
            }else{
                showValidateInfo(nicknameEle,"error",result.msg);
                $(ajaxEle).attr("ajaxCheckNickname","error");
            }
        }
    });
}

/* 显示校验信息*/
function showValidateInfo(ele,status,msg){
    $(ele).removeAttr("style");
    $(ele).next("span").removeAttr("style");
    if("ok" == status){
        $(ele).attr("style","border: 1px solid silver;");//将输入框样式变成默认的银色;
        $(ele).next("span").attr("style","color: green;").text(msg);
    }else{
        $(ele).attr("style","border: 1px solid #dd5862;");//将输入框样式变成红色;
        $(ele).next("span").attr("style","color: #dd5862;").text(msg);
    }
}

//抽取出来的密码输入框校验;
function checkPasswordInput(passwordEle) {
  // 验证用户密码  /^[a-z0-9_-]{5,15}$/
  var inputPassword = $(passwordEle).val();
  var regPassword = /(^[a-z0-9-]{5,15}$)/;
  if(!regPassword.test(inputPassword)){
      showValidateInfo(passwordEle,"error","请输入5~15位字符,只能出现数字与英文!");
  }else{
      showValidateInfo(passwordEle,"ok","");
  }
}

//抽取出来的两个密码输入框一致性的校验方法;
function checkPasswordCheckInput(passwordCheckEle,passwordEle) {
    var inputValue = $(passwordCheckEle).val();
    var password = $(passwordEle).val();
    if(inputValue!=password){
        //alert("用户名格式不正确,请输入2~10位字符,只能出现数字或英文或汉字的组合!");
        showValidateInfo(passwordCheckEle,"error","两次输入的密码不一致!");
    }else{
        showValidateInfo(passwordCheckEle,"ok","");
    }
}

/* 当用户更改第一个新密码输入框时进行校验,这是为了用户体验*/
$("#txtNewpassword").change(function(){
    checkPasswordInput("#txtNewpassword");
});

/* 当用户更改第二个新密码输入框时进行密码校验框的一致性校验,这是为了用户体验*/
$("#txtCmfpassword").change(function(){
    checkPasswordCheckInput("#txtCmfpassword","#txtNewpassword");
});

/*点击按钮后提交密码修改*/
$("#editPassword").click(function(){
	checkPasswordInput("#txtNewpassword");
	checkPasswordCheckInput("#txtCmfpassword","#txtNewpassword");
	var newPassword = $("#txtNewpassword").val();
	var oldPassword = $("#txtPassword").val();
	 $.ajax({
	        url:"/user/password",
	        type:"POST",
	        data:{"newPassword":newPassword,"oldPassword":oldPassword},
	        success:function(result){
	                alert(result.msg);
	                if(200 == resutl.code){
	                	window.location.href="/login";
	                }
	            }
	   	 });
});

/* 抽取出来的整体输入框的前端校验 */
function validateInput(nicknameEle){
    //验证用户昵称输入框;
    checkNicknameInput(nicknameEle);
    //以上是前端输入框用户体验的校验,下面是真正的功能性校验
    //上面的校验不能中途return,否则会影响用户体验;
    //同时,以上校验不能干预提交按钮上的ajax校验标记,防止出现错误;
    var inputNickname = $(nicknameEle).val();//昵称输入框
    var regNickname = /(^[a-z0-9-]{2,10}$)|(^[\u2E80-\u9FFF]{2,10})/;//昵称正则
    if( !regNickname.test(inputNickname) ){
        return false;
    }
    return true;
}

/* 当用户更改昵称输入框时进行校验,这是为了用户体验*/
$("#nickname").change(function(){

    if ($(this).val()!=oldNickName){ //判断用户修改昵称之后最后是否改回了原来的昵称,如果确定修改成了新的昵称则进行后续判断;
        //先检查正则,正则通过后再检查ajax,减轻服务器压力;
        checkNicknameInput("#nickname","#btnEditSubmit");
        var cls = $("#nickname").next("span").attr("style");//读取此输入框后面的提示信息的值,用于判断是否通过了正则校验;
        if ("color: green;" == cls){//如果样式是绿色说明通过了校验;
            ajaxCheckNicknameInput("#nickname","#btnEditSubmit");
        }
    }else {//如果用户最终改回了原来的昵称,则清空输入框样式,同时让保存按钮通过检验;
        $(this).removeAttr("style");
        $(this).next("span").removeAttr("style").text("");
        $("#btnEditSubmit").attr("ajaxCheckNickname","success");
    }
});



/*修改基本信息*/
$("#btnEditSubmit").click(function () {
    var gender = $("input:checked").val();
    var cityId= $("#city option:selected").attr("value");
    var nickname = $("#nickname").val();
    if (oldNickName!=nickname){ //判断用户修改昵称之后最后是否改回了原来的昵称,如果确定修改成了新的昵称则进行后续判断;
        /* 检查输入框是否符合正则表达式 */
        if (!validateInput("#nickname")) {
            return false;
        }
        /*再次检查ajax昵称是否可用*/
        ajaxCheckAccountInput("#account", "#btnEditSubmit");
        ajaxCheckNicknameInput("#nickname","#btnEditSubmit");
        /* 检查ajax校验用户名是否可用后的标记 */
        if( $(this).attr("ajaxCheckNickname") == "error"){
            return false;
        }

        $.ajax({
            url:"/user/info",
            type:"POST",
            data:{"nickname":nickname,"gender":gender,"cityId":cityId},
            success:function (result) {
                alert(result.msg);
                if (200 == result.code){
                    window.location.href="/user/editInfo";
                }
            }
        });
    }else {//如果用户最终改回了原来的昵称,则清空输入框样式,同时让保存按钮通过检验;;
        $("#nickname").removeAttr("style");
        $("#nickname").next("span").removeAttr("style").text("");
        $("#btnEditSubmit").attr("ajaxCheckNickname","success");
        $.ajax({
            url:"/user/info",
            type:"POST",
            data:{"gender":gender,"cityId":cityId},
            success:function (result) {
                alert(result.msg);
                if (200 == result.code){
                    window.location.href="/user/editInfo";
                }
            }
        });
    }


});




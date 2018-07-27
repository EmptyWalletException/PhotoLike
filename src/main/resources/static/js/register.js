


/*提交注册表单*/
$("#submit_register").click(function () {


    /* 检查输入框是否符合正则表达式 */
    if (!validateInput("#account","#password","#passwordCheck","#nickname","#submit_register")) {
        return false;
    }

    /*再次检查ajax账号及用户名是否可用*/
    ajaxCheckAccountInput("#account", "#submit_register");
    ajaxCheckNicknameInput("#nickname","#submit_register");
    /* 检查ajax校验用户名是否可用后的标记 */
    if($(this).attr("ajaxCheckAccount") == "error" || $(this).attr("ajaxCheckNickname") == "error"){
        return false;
    }



    /*因为涉及到文件的处理,无法直接用form封装到pojo中,所以先使用js代码来封装数据*/
    var user={};
    user.account = $("#account").val();
    user.password = $("#password").val();
    user.nickname = $("#nickname").val();
    user.gender = $("input:checked").val();
    var cityId= $("#city option:selected").attr("value");
   /* var formData = new FormData();
    formData.append("userStr",JSON.stringify(user));
    formData.append("cityId",cityId);*/

    $.ajax({
        url:"/register",
        type:"POST",
        data:{"userStr":JSON.stringify(user),"cityId":cityId},
        success:function(result){
            /* 这里要检查一下后端是否返回了错误报告信息 */
            if(200 == result.code){
                alert("注册成功!");
            }else{
                alert("注册失败!");
                /* 判断从后台返回的错误字段是哪个,如果有,则显示错误信息 */

            }
        }
    });
});


//抽取出来的用户帐号校验;
function checkAccountInput(accountEle) {
    // 验证用户账号  /^[a-z0-9_-]{5,15}$/
    var inputAccount = $(accountEle).val();
    var regAccount = /(^[a-z0-9-]{5,15}$)/;
    //alert(!regName.test(inputName));

    if(!regAccount.test(inputAccount)){
        //alert("用户名格式不正确,请输入2~10位字符,只能出现数字或英文或汉字的组合!");
        showValidateInfo(accountEle,"error","请输入5~15位字符,只能出现数字与英文!");
    }else{
        showValidateInfo(accountEle,"ok","");
    }

}

//抽取出来的ajax校验用户账号;
function ajaxCheckAccountInput(accountEle,ajaxEle){
    var inputValue = $(accountEle).val();
    $.ajax({
        url:"/user/ajax/checkAccount",
        data:"inputValue="+inputValue,
        type:"POST",
        success:function(result){
            if(200 == result.code){
                showValidateInfo("#account","ok",result.msg);
                //在按钮上添加一个信息,用于判断是否通过校验,从而决定能否提交表单;
                $(ajaxEle).attr("ajaxCheckAccount","success");
            }else{
                showValidateInfo("#account","error",result.msg);
                $(ajaxEle).attr("ajaxCheckAccount","error");
            }
        }
    });
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

/* 当用户更改第二个密码输入框时进行密码校验框的一致性校验,这是为了用户体验*/
$("#passwordCheck").change(function(){
    checkPasswordCheckInput("#passwordCheck","#password");
});

/* 当用户更改昵称输入框时进行校验,这是为了用户体验*/
$("#nickname").change(function(){
    //先检查正则,正则通过后再检查ajax,减轻服务器压力;
    checkNicknameInput("#nickname","#submit_register");
    var cls = $("#nickname").next("span").attr("style");//读取此输入框后面的提示信息的值,用于判断是否通过了正则校验;
    if ("color: green;" == cls){//如果样式是绿色说明通过了校验;
        ajaxCheckNicknameInput("#nickname","#submit_register");
    }

});

/* 当用户更改密码输入框时进行校验,这是为了用户体验*/
$("#password").change(function(){
    checkPasswordInput("#password");
});

/* 当用户更改账号输入框时进行校验,这是为了用户体验*/
$("#account").change(function(){
    checkAccountInput("#account","#submit_register");
    var cls = $("#account").next("span").attr("style");
    if ("color: green;" == cls) {
        ajaxCheckAccountInput("#account", "#submit_register");
    }
});

/* 抽取出来的整体输入框的前端校验 */
function validateInput(usernameEle,passwordEle,passwordCheckEle,nicknameEle,ajaxEle){

    //用户账号校验;
    checkAccountInput(usernameEle);

    //验证第一个密码输入框;
    checkPasswordInput(passwordEle);

    //验证第二个密码输入框;
    checkPasswordCheckInput(passwordCheckEle,passwordEle);

    //验证用户昵称输入框;
    checkNicknameInput(nicknameEle);

    //以上是前端输入框用户体验的校验,下面是真正的功能性校验
    //上面的校验不能中途return,否则会影响用户体验;
    //同时,以上校验不能干预提交按钮上的ajax校验标记,防止出现错误;
    var inputUsername = $(usernameEle).val();//用户账号输入框
    var regUserName = /(^[a-z0-9-]{5,15}$)/;//账号正则表达式

    var inputPassword = $(passwordEle).val();//密码输入框
    var regPassword = /(^[a-z0-9-]{5,15}$)/;//密码正则

    var inputValue = $(passwordCheckEle).val();//第二个密码输入框

    var inputNickname = $(nicknameEle).val();//昵称输入框
    var regNickname = /(^[a-z0-9-]{2,10}$)|(^[\u2E80-\u9FFF]{2,10})/;//昵称正则
    

    if(!regUserName.test(inputUsername) || !regPassword.test(inputPassword) ||
        inputValue!=inputPassword || !regNickname.test(inputNickname) ){
        return false;
    }

    return true;
}



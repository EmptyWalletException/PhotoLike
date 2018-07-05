


/*提交注册表单*/
$("#submit_register").click(function () {

    /* 检查输入框是否符合正则表达式 */


    /* 检查ajax校验用户名是否可用后的标记 */

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




/*封面图片的回显,利用图片上传按钮改变时触发此事件*/
var result;
var singleInput = document.getElementById("file_single_input");

$('#file_single_input').on('change',function(){
    $("#file_single_input").value = "";   // 将singleInput值清空
    //清空图片预览
    $('.singleImgEle').remove();

    var filePath = $(this).val(),         //获取到input的value，里面是文件的路径
        fileFormat = filePath.substring(filePath.lastIndexOf(".")).toLowerCase(),
        src = window.URL.createObjectURL(this.files[0]); //转成可以在本地预览的格式

    // 检查是否是图片
    if( !fileFormat.match(/.png|.jpg|.jpeg/) ) {
        alert('上传错误,文件格式必须为：png/jpg/jpeg');
        return;
    }

    result = '<img src="'+src+'" alt="封面图片"/>';
    var div = document.createElement('p');
    div.innerHTML = result;
    div['style'] = 'float:left;';
    div['className'] = 'singleImgEle';
    $("#singlePhotoEcho").append(div); 　　//在封面图片预览区域插入dom树
    var img = div.getElementsByTagName('img')[0]; //取出图片元素,然后在下面重新设置
    img.onload = function(){
        var nowHeight = ReSizePic(this); //设置图片大小
        this.parentNode.style.display = 'block';
        var oParent = this.parentNode;
        if(nowHeight){
            oParent.style.paddingTop = (oParent.offsetHeight - nowHeight)/2 + 'px';
        }

    }

});


selectCover.onclick=function(){ //单次点击选择图片按钮默认是重新选择图片;
    singleInput.value = "";   // 将singleInput值清空
    //清空图片预览
    $('.singleImgEle').remove();

    singleInput.click();
}

/*
 用ajax发送fd参数时要告诉jQuery不要去处理发送的数据，
 不要去设置Content-Type请求头才可以发送成功，否则会报“Illegal invocation”的错误，
 也就是非法调用，所以要加上“processData: false,contentType: false,”
 * */


function ReSizePic(ThisPic) {
    var RePicWidth = 200; //这里修改为您想显示的宽度值

    var TrueWidth = ThisPic.width; //图片实际宽度
    var TrueHeight = ThisPic.height; //图片实际高度

    if(TrueWidth>TrueHeight){
        //宽大于高
        var reWidth = RePicWidth;
        ThisPic.width = reWidth;
        //垂直居中
        var nowHeight = TrueHeight * (reWidth/TrueWidth);
        return nowHeight;  //将图片修改后的高度返回，供垂直居中用
    }else{
        //宽小于高
        var reHeight = RePicWidth;
        ThisPic.height = reHeight;
    }

}

$("#submit").click(function () {

    /* 检查输入框是否符合正则表达式 */


    /* 检查ajax校验用户名是否可用后的标记 */

    /*因为涉及到文件的处理,无法直接用form封装到pojo中,所以先使用js代码来封装数据*/
    var essay={};

    /*限制标题字数*/
    var title = $("#title").val();
    if ("" == title.trim()){
        alert("请输入有效的标题!");
        return false;
    }else if (30 <= title.length){
        alert("标题字数不得大于15个字!");
        return false;
    }
    essay.title = title;

    /*限制内容的字数*/
    var content = $("#subscribe").val();
    if ("" == content.trim()){
        alert("请输入有效的内容!");
        return false;
    }else if (200 <= content.length){
        alert("内容字数不得大于100个字!");
        return false;
    }
    essay.content= content;
    /*用户信息不能从页面往后台传,防止用户修改信息导致绑定了错误的作者*/

    var img = $("#file_single_input")[0].files[0];
    var formData = new FormData();
    formData.append("essayStr",JSON.stringify(essay));
    formData.append("img",img);

    $.ajax({
        url:"/essay/contribute",
        type:"POST",
        data:formData,
        async: false,
        contentType:false,
        processData:false,
        cache:false,
        success:function(result){
            /* 这里要检查一下后端是否返回了错误报告信息 */
            if(200 == result.code){
                alert("投稿成功,请等待审核!");
            }else{
                alert("投稿失败了!");
                /* 判断从后台返回的错误字段是哪个,如果有,则显示错误信息 */

            }
        }
    });
});


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

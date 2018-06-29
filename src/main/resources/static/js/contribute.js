


/*多图片上传并回显*/
window.onload = function(){
    var input = document.getElementById("file_input");
    var result;
    var multifileDataArr = []; // 储存多图片上传所选图片的结果(文件名和base64数据)
    var sinaleDataArr = []; // 储存单图所选图片的结果(文件名和base64数据)
    var fd;  //FormData方式发送请求
    var oSelect = document.getElementById("select");
    var clear = document.getElementById("clear");
    var oSubmit = document.getElementById("submit");
    var oInput = document.getElementById("file_input");
    var singleInput = document.getElementById("file_single_input");
    var selectCover = document.getElementById("selectCover");


    if(typeof FileReader==='undefined'){
        alert("抱歉，你的浏览器不支持 FileReader");
        input.setAttribute('disabled','disabled');
    }else{
        input.addEventListener('change',readFile,false);
    }　　　　　//handler


    function readFile(){
        fd = new FormData();
        var iLen = this.files.length;
        var index = 0;
        for(var i=0;i<iLen;i++){
            if (!input['value'].match(/.jpg|.gif|.png|.jpeg|.bmp/i)){　　//判断上传文件格式
                return alert("上传的图片格式不正确，请重新选择");
            }
            var multifileReader = new FileReader();
            multifileReader.index = i;
            fd.append(i,this.files[i]);
            multifileReader.readAsDataURL(this.files[i]);  //转成base64
            multifileReader.fileName = this.files[i].name;


            multifileReader.onload = function(e){
                var imgMsg = {
                    name : this.fileName,//获取文件名
                    base64 : this.result   //multifileReader.readAsDataURL方法执行完后，base64数据储存在multifileReader.result里
                }
                multifileDataArr.push(imgMsg);
                result = '<img src="'+this.result+'" alt=""/>';
                var div = document.createElement('p');
                div.innerHTML = result;
                div['style'] = 'float:left;';
                div['className'] = 'imgEle';
                div['index'] = index;
                $("#photoEcho").append(div); 　　//插入dom树
                var img = div.getElementsByTagName('img')[0];
                img.onload = function(){
                    var nowHeight = ReSizePic(this); //设置图片大小
                    this.parentNode.style.display = 'block';
                    var oParent = this.parentNode;
                    if(nowHeight){
                        oParent.style.paddingTop = (oParent.offsetHeight - nowHeight)/2 + 'px';
                    }

                }


                div.onclick = function(){
                    this.remove();                  // 在页面中删除该图片元素
                    delete multifileDataArr[this.index];  // 删除multifileDataArr对应的数据

                }
                index++;
            }
        }
    }



    function send(){


        var submitArr = [];
        for (var i = 0; i < multifileDataArr.length; i++) {
            if (multifileDataArr[i]) {
                submitArr.push(multifileDataArr[i]);
            }
        }
        // console.log('提交的数据：'+JSON.stringify(submitArr))
        $.ajax({
            url : 'http://123.206.89.242:9999',
            type : 'post',
            data : JSON.stringify(submitArr),
            dataType: 'json',
            //processData: false,   用FormData传singleFd时需有这两项
            //contentType: false,
            success : function(data){
                console.log('返回的数据：'+JSON.stringify(data))
            }

        })
    }


    selectCover.onclick=function(){ //单次点击选择图片按钮默认是重新选择图片;
        singleInput.value = "";   // 将singleInput值清空
        //清空图片预览
        $('.singleImgEle').remove();

        singleInput.click();
    }

    oInput.onclick=function(){ //在多文件上传是单次点击选择图片按钮默认是重新选择图片;
        oInput.value = "";   // 将oInput值清空
        //清空图片预览
        $('.imgEle').remove();
        multifileDataArr = [];
        index = 0;
    }

    clear.onclick=function() {
        oInput.value = "";   // 将oInput值清空
        //清空图片预览
        $('.imgEle').remove();
        multifileDataArr = [];
        index = 0;
    }
    oSelect.onclick=function(){
        oInput.value = "";   // 先将oInput值清空，否则选择图片与上次相同时change事件不会触发
        //清空已选图片
        $('.imgEle').remove();
        multifileDataArr = [];
        index = 0;
        oInput.click();
    }


    oSubmit.onclick=function(){
        if(!multifileDataArr.length){
            return alert('请先选择文件');
        }
        send();
    }

}

/*封面图片的回显,利用图片上传按钮改变时触发此事件*/
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

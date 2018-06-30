
var result;
var multifileDataArr = []; // 储存多图片上传所选图片的结果(文件名和base64数据)

var fd;  //FormData方式发送请求
var imgsSelect = document.getElementById("select");
var clear = document.getElementById("clear");
var imgsInput = document.getElementById("file_input");
var singleInput = document.getElementById("file_single_input");
var selectCover = document.getElementById("selectCover");

/*多图片上传并回显*/
window.onload = function(){


    if(typeof FileReader==='undefined'){
        alert("抱歉，你的浏览器不支持 FileReader");
    }else{
        imgsInput.addEventListener('change',readFile,false);
    }

    function readFile(){
        fd = new FormData();
        multifileDataArr = []; //每次用户重新点击按钮时清空存储的图片数组;
        var iLen = this.files.length;
        var index = 0;
        for(var i=0;i<iLen;i++){
            if (!imgsInput['value'].match(/.jpg|.gif|.png|.jpeg|.bmp/i)){　　//判断上传文件格式
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

    selectCover.onclick=function () {
        singleInput.value = "";
        //清空图片预览
        $('.singleImgEle').remove();
        singleInput.click();

    }

    clear.onclick=function() {
        //清空图片预览
        $('.imgEle').remove();
        multifileDataArr = [];
        index = 0;
    }
    imgsSelect.onclick=function(){
        //清空已选图片
        $('.imgEle').remove();
        multifileDataArr = [];
        index = 0;
        imgsInput.click();
    }



}

/*封面图片的回显,利用图片上传按钮改变时触发此事件*/
$("#file_single_input").on('change',function(){
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


$("#submit").click(function () {

    /* 检查输入框是否符合正则表达式 */


    /* 检查ajax校验用户名是否可用后的标记 */

    /*因为涉及到文件的处理,无法直接用form封装到pojo中,所以先使用js代码来封装数据*/
    if(!multifileDataArr.length){
        return alert('请先选择专辑图片集');
    }
    var topic={};
    topic.title = $("#title").val();
    topic.content= $("#myEditor").val();
    topic.content= $("#myEditor").val();
    var categoryId= $("#category option:selected").attr("value");

    /*用户信息不能从页面往后台传,防止用户修改信息导致绑定了错误的作者*/

    var img = $("#file_single_input")[0].files[0];

    fd.append("topicStr",JSON.stringify(topic));
    fd.append("img",img);
    fd.append("categoryId",categoryId);


    $.ajax({
        url:"/topic/contribute",
        type:"POST",
        data:fd,
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

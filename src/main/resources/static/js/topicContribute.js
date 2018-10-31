
var result;//返回的结果
var multifileDataArr = []; // 储存多图片上传所选图片的结果(文件名和base64数据)

var fd;  //FormData方式发送请求
var imgsSelect = document.getElementById("select");//激活imgsInput(多图片上传输入框)的按钮;
var clear = document.getElementById("clear");
var imgsInput = document.getElementById("file_input");//隐藏的多图片上传输入框;
var singleInput = document.getElementById("file_single_input");//隐藏的封面图片上传输入框;
var selectCover = document.getElementById("selectCover");//激活singleInput(封面图片上传输入框)的按钮

/*多图片上传并回显,csdn上找到的原生js代码*/
window.onload = function(){
    if(typeof FileReader == 'undefined'){
        alert("抱歉，你的浏览器不支持 FileReader");
    }else{
        imgsInput.addEventListener('change',readFile,false);//对隐藏的多图片上传输入框添加监听事件,当此输入框的值被改变时会激活readFile方法;
    }

    function readFile(){
        fd = new FormData();//创建一个formData用于ajax往后台发送数据;  
        multifileDataArr = []; //创建一个数组储存所有要上传的图片文件,每次用户重新点击按钮时清空存储的图片数组;
        var iLen = this.files.length;
        var index = 0;
        for(var i=0;i<iLen;i++){
            if (!imgsInput['value'].match(/.jpg|.gif|.png|.jpeg|.bmp/i)){　　//判断上传文件格式
                return alert("上传的图片格式不正确，请重新选择");
            }
            var multifileReader = new FileReader();
            multifileReader.index = i;
            fd.append(i,this.files[i]);//往formData里添加文件
            multifileReader.readAsDataURL(this.files[i]);  //转成base64
            multifileReader.fileName = this.files[i].name;

            multifileReader.onload = function(e){
                var imgMsg = {
                    name : this.fileName,//获取文件名
                    base64 : this.result   //multifileReader.readAsDataURL方法执行完后，base64数据储存在multifileReader.result里
                }
                multifileDataArr.push(imgMsg);//往数组里压图片文件
                result = '<img src="'+this.result+'" alt=""/>';//生成图片预览要用的html元素;
                var p = document.createElement('p');
                p.innerHTML = result;//将图片预览的html元素插入到新生成的p标签里,p标签主要用于控制版式;
                p['style'] = 'float:left;';
                p['className'] = 'imgEle';
                p['index'] = index;
                $("#photoEcho").append(p); 　　//插入上面几行生成的图片元素
                var img = p.getElementsByTagName('img')[0];//从p标签里取出图片,因为一个p标签里只有一个img,所以用0下标也可以;
                //在img标签元素加载后设置一下它的样式;
                img.onload = function(){
                    var nowHeight = ReSizePic(this); //设置图片大小
                    this.parentNode.style.display = 'block';
                    var oParent = this.parentNode;
                    if(nowHeight){
                        oParent.style.paddingTop = (oParent.offsetHeight - nowHeight)/2 + 'px';
                    }
                }

                //当点击p标签时移除掉此图片预览
                p.onclick = function(){
                    this.remove();                  // 在页面中删除该图片元素
                    delete multifileDataArr[this.index];  // 删除multifileDataArr对应的数据
                }
                index++;
            }
        }
    }

    //点击"选择封面图片"按钮的事件;
    selectCover.onclick=function () {
    	//清除掉输入框里的所有图片;
        singleInput.value = "";
        //清空封面图片预览
        $('.singleImgEle').remove();
        //再次激活图片上传事件;
        singleInput.click();
    }

    //点击"清楚所有图片"按钮的事件;
    clear.onclick=function() {
        //清空图片预览
        $('.imgEle').remove();
        //清空储存图片的数组
        multifileDataArr = [];
        index = 0;
    }
    
    //点击"重新批量选择图片"按钮的事件;
    imgsSelect.onclick=function(){
        //清空图片预览
        $('.imgEle').remove();
        //清空储存图片的数组
        multifileDataArr = [];
        index = 0;
        imgsInput.click();
    }
}

/*封面图片的回显,利用图片上传按钮改变时触发此事件*/
$("#file_single_input").on('change',function(){
    $("#file_single_input").value = "";   // 将singleInput里的之前选择的图片清空
    //清空封面图片预览
    $('.singleImgEle').remove();

    var filePath = $(this).val(),         //获取到input的value，里面是文件的路径
        fileFormat = filePath.substring(filePath.lastIndexOf(".")).toLowerCase(),
        src = window.URL.createObjectURL(this.files[0]); //截取出文件格式用于校验,生成url用于页面回显图片;

    // 检查是否是图片
    if( !fileFormat.match(/.png|.jpg|.jpeg/) ) {
        alert('上传文件格式必须为：png/jpg/jpeg');
        return;
    }

    //在页面生成图片回显
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

/**
 * 点击提交按钮后的动作
 * @returns
 */
$("#submit").click(function () {
    /*因为涉及到文件的处理,无法直接用form封装到pojo中,所以先使用js代码来封装数据*/
    if(!multifileDataArr.length){
        return alert('请先选择专辑图片集');
    }
    var topic={};
    /*限制标题字数*/
    var title = $("#title").val();
    if ("" == title.trim()){
        alert("请输入有效的标题!");
        return false;
    }else if (30 <= title.length){
        alert("标题字数不得大于15个字!");
        return false;
    }
    topic.title = title;

    /*限制内容的字数*/
    var content = $("#myEditor").val();
    if ("" == content.trim()){
        alert("请输入有效的内容!");
        return false;
    }else if (2000 <= content.length){
        alert("内容字数不得大于1000个字!");
        return false;
    }
    topic.content= content;

    //设置分类id
    var categoryId= $("#category option:selected").attr("value");

    /*用户信息不能从页面往后台传,防止用户修改信息导致绑定了错误的作者*/

    //将封面图片追加到formData里,后端根据key'img'拿出来进行处理;
    var img = $("#file_single_input")[0].files[0];
    fd.append("img",img);
    fd.append("topicStr",JSON.stringify(topic));
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


/**
 * 此方法用于判断图片的长宽显示比例,然后调整图片到合理的预览尺寸;
 * @param ThisPic
 * @returns
 */
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

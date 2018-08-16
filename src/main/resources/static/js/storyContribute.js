/*富文本编辑器js开始*/
//实例化编辑器
//建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
var ue = UE.getEditor('editor');

function isFocus(e){
    alert(UE.getEditor('editor').isFocus());
    UE.dom.domUtils.preventDefault(e)
}
function setblur(e){
    UE.getEditor('editor').blur();
    UE.dom.domUtils.preventDefault(e)
}
function insertHtml() {
    var value = prompt('插入html代码', '');
    UE.getEditor('editor').execCommand('insertHtml', value)
}
function createEditor() {
    enableBtn();
    UE.getEditor('editor');
}
function getAllHtml() {
    alert(UE.getEditor('editor').getAllHtml())
}
function getContent() {
    var arr = [];
    arr.push("使用editor.getContent()方法可以获得编辑器的内容");
    arr.push("内容为：");
    arr.push(UE.getEditor('editor').getContent());
    alert(arr.join("\n"));
}
function getPlainTxt() {
    var arr = [];
    arr.push("使用editor.getPlainTxt()方法可以获得编辑器的带格式的纯文本内容");
    arr.push("内容为：");
    arr.push(UE.getEditor('editor').getPlainTxt());
    alert(arr.join('\n'))
}
function setContent(isAppendTo) {
    var arr = [];
    arr.push("使用editor.setContent('欢迎使用ueditor')方法可以设置编辑器的内容");
    UE.getEditor('editor').setContent('欢迎使用ueditor', isAppendTo);
    alert(arr.join("\n"));
}
function setDisabled() {
    UE.getEditor('editor').setDisabled('fullscreen');
    disableBtn("enable");
}

function setEnabled() {
    UE.getEditor('editor').setEnabled();
    enableBtn();
}

function getText() {
    //当你点击按钮时编辑区域已经失去了焦点，如果直接用getText将不会得到内容，所以要在选回来，然后取得内容
    var range = UE.getEditor('editor').selection.getRange();
    range.select();
    var txt = UE.getEditor('editor').selection.getText();
    alert(txt)
}

function getContentTxt() {
    var arr = [];
    arr.push("使用editor.getContentTxt()方法可以获得编辑器的纯文本内容");
    arr.push("编辑器的纯文本内容为：");
    arr.push(UE.getEditor('editor').getContentTxt());
    alert(arr.join("\n"));
}
function hasContent() {
    var arr = [];
    arr.push("使用editor.hasContents()方法判断编辑器里是否有内容");
    arr.push("判断结果为：");
    arr.push(UE.getEditor('editor').hasContents());
    alert(arr.join("\n"));
}
function setFocus() {
    UE.getEditor('editor').focus();
}
function deleteEditor() {
    disableBtn();
    UE.getEditor('editor').destroy();
}
function disableBtn(str) {
    var div = document.getElementById('btns');
    var btns = UE.dom.domUtils.getElementsByTagName(div, "button");
    for (var i = 0, btn; btn = btns[i++];) {
        if (btn.id == str) {
            UE.dom.domUtils.removeAttributes(btn, ["disabled"]);
        } else {
            btn.setAttribute("disabled", "true");
        }
    }
}
function enableBtn() {
    var div = document.getElementById('btns');
    var btns = UE.dom.domUtils.getElementsByTagName(div, "button");
    for (var i = 0, btn; btn = btns[i++];) {
        UE.dom.domUtils.removeAttributes(btn, ["disabled"]);
    }
}

function getLocalData () {
    alert(UE.getEditor('editor').execCommand( "getlocaldata" ));
}

function clearLocalData () {
    UE.getEditor('editor').execCommand( "clearlocaldata" );
    alert("已清空草稿箱")
}

UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
UE.Editor.prototype.getActionUrl = function(action) {
    if (action == 'uploadimage' || action == 'uploadscrawl' || action == 'uploadimage') {
        return 'http://47.98.240.27:8080/storyContribute/imgUpload'; //在这里返回我们实际的上传图片地址
    } else {
        return this._bkGetActionUrl.call(this, action);
    }
}
/*富文本编辑器js结束*/

/*封面图片上传并回显*/
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

/*提交稿件*/
$("#submit").click(function () {

    /* 检查输入框是否符合正则表达式 */


    /* 检查ajax校验用户名是否可用后的标记 */

    /*因为涉及到文件的处理,无法直接用form封装到pojo中,所以先使用js代码来封装数据*/
    var story={};
    /*限制标题字数*/
    var title = $("#title").val();
    if ("" == title.trim()){
        alert("请输入有效的标题!");
        return false;
    }else if (30 <= title.length){
        alert("标题字数不得大于15个字!");
        return false;
    }
    story.title = title;

    /*限制摘要的字数*/
    var subscribe = $("#subscribe").val();
    if ("" == subscribe.trim()){
        alert("请输入有效的摘要!");
        return false;
    }else if (200 <= subscribe.length){
        alert("摘要字数不得大于100个字!");
        return false;
    }
    story.subscribe = subscribe;

    story.content= UE.getEditor('editor').getContent();
    /*用户信息不能从页面往后台传,防止用户修改信息导致绑定了错误的作者*/

    var img = $("#file_single_input")[0].files[0];
    var formData = new FormData();
    formData.append("storyStr",JSON.stringify(story));
    formData.append("img",img);

    $.ajax({
        url:"/story/contribute",
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

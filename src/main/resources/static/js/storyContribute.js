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
/*富文本编辑器js结束*/

/*多图片上传并回显*/
window.onload = function(){
    var input = document.getElementById("file_input");
    var result;
    var dataArr = []; // 储存所选图片的结果(文件名和base64数据)
    var fd;  //FormData方式发送请求
    var oSelect = document.getElementById("select");
    var clear = document.getElementById("clear");
    var oAdd = document.getElementById("add");
    var oSubmit = document.getElementById("submit");
    var oInput = document.getElementById("file_input");

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
            var reader = new FileReader();
            reader.index = i;
            fd.append(i,this.files[i]);
            reader.readAsDataURL(this.files[i]);  //转成base64
            reader.fileName = this.files[i].name;


            reader.onload = function(e){
                var imgMsg = {
                    name : this.fileName,//获取文件名
                    base64 : this.result   //reader.readAsDataURL方法执行完后，base64数据储存在reader.result里
                }
                dataArr.push(imgMsg);
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
                    delete dataArr[this.index];  // 删除dataArr对应的数据

                }
                index++;
            }
        }
    }


    function send(){


        var submitArr = [];
        for (var i = 0; i < dataArr.length; i++) {
            if (dataArr[i]) {
                submitArr.push(dataArr[i]);
            }
        }
        // console.log('提交的数据：'+JSON.stringify(submitArr))
        $.ajax({
            url : 'http://123.206.89.242:9999',
            type : 'post',
            data : JSON.stringify(submitArr),
            dataType: 'json',
            //processData: false,   用FormData传fd时需有这两项
            //contentType: false,
            success : function(data){
                console.log('返回的数据：'+JSON.stringify(data))
            }

        })
    }


    oInput.onclick=function(){ //在多文件上传是单次点击选择图片按钮默认是重新选择图片;
        oInput.value = "";   // 将oInput值清空
        //清空图片预览
        $('.imgEle').remove();
        dataArr = [];
        index = 0;
    }




    oSubmit.onclick=function(){
        if(!dataArr.length){
            return alert('请先选择文件');
        }
        send();
    }

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

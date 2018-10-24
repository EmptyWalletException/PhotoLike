package com.kingguanzhang.toptalk.controller.component;

//@Controller
public class ImgUploadController {

    /**
     * 保存ue富文本编辑器上传的图片并回显;已经移交给ContributeController处理,此处保留一个被注释掉的模板供以后参考;
     * @param upfile
     * @return
     *//*
    @RequestMapping(value = "/imgUpload")
    @ResponseBody
    public String imgUpload3(MultipartFile upfile) {
        if (upfile.isEmpty()) {
            return "error";
        }
        try {
            //使用工具类保存图片并返回文件名给网页;
            String fileName = ImgUtil.generateThumbnail(upfile,"/temp",720,720);
            //url为文件访问的完整路径,注意应该配合mvc中配置的虚拟路径"/upload"
            String config = "{\"state\": \"SUCCESS\"," +
                    "\"url\": \"" + fileName + "\"," +
                    "\"title\": \"" + fileName + "\"," +
                    "\"original\": \"" + fileName + "\"}";
            return config;
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        return "error";
    }*/


}

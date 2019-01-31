package com.kingguanzhang.toptalk.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class DownloadZip {

    /**
     * 将传入的文件地址字符串数组打包成zip
     * @param tagPath  zip的输出地址
     * @param sourcePath 文件的来源地址，字符串数组
     * @throws IOException
     */
    public static void downLoadZIP(String tagPath,String[] sourcePath) throws IOException {

        //zip输出流
        ZipOutputStream out=new ZipOutputStream(new FileOutputStream(tagPath));
        File[] files=new File[sourcePath.length];
        //按照多个文件的打包方式，一个也可以
        for(int i=0;i<files.length;i++) {
            files[i]=new File(sourcePath[i]);//此处注意要替换掉字符串结尾可能会存在的逗号"," //更新,已经找到原因,现在后面不会在出现逗号;
        }
        byte[] b=new byte[1024];
        for(int j=0;j<files.length;j++) {
            //输入流
            FileInputStream in=new FileInputStream(files[j]);
            //把条目放到zip里面，意思就是把文件放到压缩文件里面
            out.putNextEntry(new ZipEntry(files[j].getName()));
            int len=0;
            //输出
            while((len=in.read(b))>-1) {
                out.write(b, 0, len);
            }
            out.closeEntry();
            in.close();
        }
        out.close();
    }

    /**
     * 测试代码成功运行并实现打包功能;
     */
   /* public static void main(String[] args) throws IOException {
        DownloadZip dl=new DownloadZip();
        String[] path= {"D:/test.jpg","D:/test2.jpg"};
        dl.downLoadZIP("D:/test.zip", path); //把上面两个文件打包成test.zip输出到D盘根目录
    }*/

}

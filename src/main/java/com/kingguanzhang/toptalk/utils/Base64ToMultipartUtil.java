package com.kingguanzhang.toptalk.utils;
import com.kingguanzhang.toptalk.component.BASE64DecodedMultipartFile;
import org.apache.commons.codec.binary.Base64;
import org.springframework.web.multipart.MultipartFile;


/**
 * 用于将base64转成mulitipart的工具
 */
public class Base64ToMultipartUtil {
    public static MultipartFile base64ToMultipart(String base64) {

        try {
            String[] baseStrs = base64.split(",");

            Base64 base64encoder = new Base64();
            byte[] b = new byte[baseStrs.length];
            b = base64encoder.decode(baseStrs[1]);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }

            return new BASE64DecodedMultipartFile(b, baseStrs[0]);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

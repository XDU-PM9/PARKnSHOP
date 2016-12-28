package com.parknshop.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by weina on 2016/12/27.
 */
@Component
public class Pay {
    public static final String IP = "192.168.31.62:8080";
    /**
     * 获取 二维码地址
     * @param session  param获取的session
     * @param content 需要转成二维码的一串字符
     * @return
     */
    public String getQRCode(HttpSession session, String content) {
        try {
            String context = session.getServletContext().getRealPath("/");
            File root = new File(context);
            String fileName = "resources/images/photos/14.jpg";
            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            Map hints = new HashMap();
            //内容所使用编码
            hints.put(EncodeHintType.CHARACTER_SET, "gb2312");
            BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, 200, 200, hints);
            //生成二维码
            File file = new File(root, fileName);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
            MatrixToImageWriter.writeToFile(bitMatrix, "jpg", file);
            return "/resources/images/photos/14.jpg";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

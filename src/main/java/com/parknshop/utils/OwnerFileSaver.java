package com.parknshop.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

/**
 * Created by fallb on 2016/12/9.
 */
public class OwnerFileSaver {


    public static String saveImage(MultipartFile multipartFile,String context) throws IOException {
        File root = new File(context);
        String[] splitFile = multipartFile.getOriginalFilename().split("\\.");
        String fileSuffix = splitFile[splitFile.length-1];
        String pathPrefix = "resources/images/photos/";
        String fileName = pathPrefix+UUID.randomUUID().toString()+"."+fileSuffix;

        File file = new File(root,fileName);
        if (!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        if (!file.exists()){
            file.createNewFile();
        }

        OutputStream out = new FileOutputStream(file);
        InputStream in = multipartFile.getInputStream();
        byte[] buffer = new byte[1024];
        int length = 0;
        while ((length = in.read(buffer))!=-1){
            out.write(buffer,0,length);
        }
        out.flush();
        in.close();
        out.close();
        return "/"+fileName;
    }

}

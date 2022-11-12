package com.hlw.utils;

import cn.hutool.core.util.IdUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * @author zzy
 * @desc
 */
public class ZZYUtil {

    private static String remotePath = "localhost";
    private static int port = 8080;

    public static String getRemotePath(MultipartFile file){
        String path = "http://"+remotePath +":"+port+"/assets/";
        System.out.println(path);
        //后缀
        String originalFilename = file.getOriginalFilename();
        String [] arr = originalFilename.split("\\.");
        String fix = arr[arr.length-1];

        //文件名
        String name = IdUtil.fastSimpleUUID();
        String fileName = name + "." +fix;

        //文件夹生成
        ApplicationHome home = new ApplicationHome();
        File dir = home.getDir();
        String resources = dir.getAbsolutePath()+"\\target\\static\\";
        System.out.println("文件储存地址为:"+resources);

        File file1 = new File(resources);
        if (!file1.exists()){
            file1.mkdirs();
        }
        File file2 = new File(resources + fileName);
        try {
            file.transferTo(file2);
        } catch (IOException e) {
            System.out.println("文件写入失败");
            throw new RuntimeException(e);
        }

        return path + fileName;
    }
    public static <T> T getSessionValue(HttpServletRequest request,String keyName,Class<T> clazz){
        T t;
        try {
            t = clazz.newInstance();
            t = (T) request.getSession(false).getAttribute(keyName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return t;
    }
}

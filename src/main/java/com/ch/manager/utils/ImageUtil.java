package com.ch.manager.utils;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * @author chenhao
 * @date ${date}
 */
public class ImageUtil {

    public static String  actionPath = "/image";

    /**
     * 获取上传图片后的路径
     *
     * @param req
     * @param imagePath 自定义图片上传路径
     * @return
     * @throws IOException
     */
    public static String uploadImage(MultipartHttpServletRequest req, String imagePath) throws IOException {
        return FileUtil.getActionPath(actionPath, FileUtil.uploadFile(req, imagePath));
    }

    /**
     * 访问图片
     *
     * @throws IOException
     * @rep
     * @fileName 图片名称
     * @imagePath 自定义访问图片路径
     */
    public static void getImage(HttpServletResponse rep, String fileName, String imagePath) throws IOException {
        getImage(rep, getImageFile(fileName, imagePath));
    }

    public static void getImage(HttpServletResponse rep, File file) throws IOException {
        rep.setContentType(getImgContentType(file.getName()));
        FileUtil.copyFile(rep, file);
    }

    /**
     * 解决get方式拿到图片名字后缀可能消失的问题
     *
     * @param fileName
     * @param imagePath
     * @return
     */
    public static File getImageFile(String fileName, String imagePath) {
        if (!isImage(fileName)) {
            String[] suffixs = { ".jpg", ".png", ".jpeg" };
            File file = null;
            for (String suffix : suffixs) {
                file = new File(FileUtil.getFilePath(imagePath, fileName + suffix));
                if (file.exists()) {
                    return file;
                }
            }
        }
        return new File(FileUtil.getFilePath(imagePath, fileName));
    }

    /**
     * 判断是否是图片
     *
     * @param name
     * @return
     */
    public static boolean isImage(String name) {
        name = name.toLowerCase();
        return name.endsWith(".jpg") || name.endsWith(".png") || name.endsWith(".gif") || name.endsWith(".bmp")
               || name.endsWith("jpeg");
    }

    /**
     * 获取图片对应文件类型ContentType
     *
     * @param fname
     * @return
     */
    public static String getImgContentType(String fname) {
        if (fname.endsWith(".jpg") || fname.endsWith(".jpeg")) {
            return "image/jpeg;charset=utf-8";
        } else if (fname.endsWith(".png")) {
            return "image/png;charset=utf-8";
        } else if (fname.endsWith(".gif")) {
            return "image/gif;charset=utf-8";
        }
        return null;
    }

}

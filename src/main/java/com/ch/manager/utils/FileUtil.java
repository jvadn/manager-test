package com.ch.manager.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.Iterator;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * @author chenhao
 * @date ${date}
 */
public class FileUtil {

    public static String  actionPath = "/file";

    /**
     * 上传文件,返回访问路径
     *
     * @param req
     * @param filePath 上传文件路径
     * @return
     * @throws IOException
     */
    public static String getActionPath(MultipartHttpServletRequest req, String filePath) throws IOException {
        return getActionPath(actionPath, uploadFile(req, filePath));
    }

    /**
     * 上传文件
     *
     * @param req
     * @param filePath 文件路径
     * @return
     * @throws IOException
     */
    public static String uploadFile(MultipartHttpServletRequest req, String filePath) throws IOException {
        MultipartFile mFile = getMultipartFile(req);
        String filep = newFilePath(filePath, getFileName(mFile));
        copyFile(mFile, new File(filep));
        return filep;
    }

    /**
     * 下载文件
     *
     * @param rep
     * @param filePath 下载文件路径
     * @throws IOException
     */
    public static void download(HttpServletResponse rep, String filePath) throws IOException {
        download(rep, new File(filePath));
    }

    /**
     * 下载文件
     *
     * @param rep
     * @param file 文件
     * @throws IOException
     */
    public static void download(HttpServletResponse rep, File file) throws IOException {
        rep.setContentType("application/octet-stream");
        try {
            rep.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(file.getName(), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        copyFile(rep, file);
    }

    /**
     * 将需下载文件复制到客户端 HttpServletResponse需先设置ContentType和Header
     *
     * @param rep
     * @param file
     * @throws IOException
     */
    public static void copyFile(HttpServletResponse rep, File file) throws IOException {
        FileInputStream fis = null;
        fis = new FileInputStream(file);
        IOUtils.copy(fis, rep.getOutputStream());
        rep.flushBuffer();
        fis.close();

    }

    /**
     * 将上传文件复制到服务端
     *
     * @param mFile
     * @param file
     * @throws IOException
     */
    public static void copyFile(MultipartFile mFile, File file) throws IOException {
        InputStream input = mFile.getInputStream();
        FileOutputStream fileOutput = new FileOutputStream(file);
        IOUtils.copy(input, fileOutput);
        fileOutput.close();
        input.close();
    }

    /**
     * 返回访问路径
     *
     * @param actionPath
     * @param path
     * @return
     */
    public static String getActionPath(String actionPath, String path) {
        if (StringUtil.isBlank(path)) {
            return null;
        }
        String[] urls = replacePath(path).split("/");
        return urls.length < 1 ? path : urls[urls.length - 1] + actionPath;
    }

    /**
     * 将windows路径\\转换成linux路径/
     *
     * @param path
     * @return
     */
    public static String replacePath(String path) {
        return path.replaceAll("\\\\", "/");
    }

    public static MultipartFile getMultipartFile(MultipartHttpServletRequest req) {
        for (Iterator<String> it = req.getFileNames(); it.hasNext();) {
            String fname = it.next();
            if (StringUtil.isNotBlank(fname)) {
                return req.getFile(fname);
            }
        }
        return null;
    }

    /**
     * 获取MultipartFile文件名
     *
     * @param mFile
     * @return
     */
    public static String getFileName(MultipartFile mFile) {
        String file = mFile.getOriginalFilename();
        return file.substring(file.lastIndexOf("\\") + 1);// 为兼容IE8
    }

    /**
     * 生成文件路径
     *
     * @param filePath
     * @param fileName
     * @return
     */
    public static String newFilePath(String filePath, String fileName) {
        return filePath + File.separator + IdUtil.getUUID() + "-" + fileName;
    }

    /**
     * 通过文件存放路径和文件名,返回文件路径
     *
     * @param filePath
     * @param fileName
     * @return
     */
    public static String getFilePath(String filePath, String fileName) {
        return filePath + File.separator + fileName;
    }

    /**
     * 是否超过最大限制(/最小单位kb)
     *
     * @param size 10/mb
     * @param fileSize 文件大小/字节(b)
     * @return
     */
    public static boolean maxSize(String size, long fileSize) {
        String[] sizes = size.split("/");
        if (sizes.length == 2 && StringUtil.toIntger(sizes[0]) > 0) {
            return fileSize > getSizeByUnit(Long.parseLong(sizes[0]), sizes[1]);
        }
        return false;
    }

    /**
     * 获取文件大小(kb)
     *
     * @param size
     * @param unit
     * @return
     */
    public static long getSizeByUnit(long size, String unit) {
        long b = size;
        if ("kb".equals(unit)) {
            b = size * 1024;
        } else if ("mb".equals(unit)) {
            b = size * 1024 * 1024;
        } else if ("gb".equals(unit)) {
            b = size * 1024 * 1024 * 1024;
        }
        return b;
    }

    public static String getSize(long size) {
        if (size / 1024 / 1024 / 1024 >= 1) {
            return format((double) size / 1024 / 1024 / 1024) + "/gb";
        } else if (size / 1024 / 1024 >= 1) {
            return format((double) size / 1024 / 1024) + "/mb";
        } else if (size / 1024 >= 1) {
            return format((double) size / 1024) + "/kb";
        }
        return null;
    }

    private static double format(double total) {
        DecimalFormat df = new DecimalFormat("#.00");
        return Double.parseDouble(df.format(total));
    }

    public static void close(InputStream in) {
        try {
            if (in != null) {
                in.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void close(FileOutputStream fos) {
        try {
            if (fos != null) {
                fos.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

package com.ch.manager.action.Base;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.ch.manager.utils.FileUtil;
import com.ch.manager.utils.ImageUtil;
import com.ch.manager.utils.StringUtil;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSONObject;

/**
 * @author chenhao
 * @date ${date}
 */
public abstract class AbstractImageAction extends BaseAction {

    protected String imagePath;

    protected String maxSize = "10/mb";

    public abstract String getImagePath();

    private String initImagePath() {
        if (StringUtil.isBlank(this.imagePath)) {
            this.imagePath = getImagePath();
        }
        return this.imagePath;
    }

    protected String getMaxSize() {
        return maxSize;
    }

    @RequestMapping("/upload-image")
    @ResponseBody
    public String uploadImage(MultipartHttpServletRequest req) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", false);
        if (FileUtil.getMultipartFile(req) == null) {
            map.put("msg", "文件为空！");
            return JSONObject.toJSONString(map);
        }
        // 判断是否图片格式
        if (StringUtil.isBlank(ImageUtil.getImgContentType(FileUtil.getFileName(FileUtil.getMultipartFile(req))))) {
            map.put("msg", "文件格式错误,不符合图片格式！");
            return JSONObject.toJSONString(map);
        }
        // 获取文件上传最大限制
        String size = StringUtil.isBlank(req.getParameter("size")) ? getMaxSize() : req.getParameter("size");
        if (FileUtil.maxSize(size, FileUtil.getMultipartFile(req).getSize())) {
            map.put("msg", "文件大小超出".concat(size));
            return JSONObject.toJSONString(map);
        }
        map.put("size", FileUtil.getSize(FileUtil.getMultipartFile(req).getSize()));
        try {
            // 上传文件
            String path = ImageUtil.uploadImage(req, initImagePath());
            map.put("url", path);
            map.put("name", path.replace(ImageUtil.actionPath, ""));
            map.put("status", true);
        } catch (IOException e) {
            map.put("msg", "上传失败！");
            e.printStackTrace();
        }
        return JSONObject.toJSONString(map);
    }

    @RequestMapping("/{fileName}/image")
    public void getImage(@PathVariable String fileName, HttpServletResponse res) {
        try {
            ImageUtil.getImage(res, fileName, initImagePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

package com.ch.manager.action.Base;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.ch.manager.utils.FileUtil;
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
public abstract class AbstractFileAction extends BaseAction {

    protected String filePath;

    protected String maxSize = "50/mb";

    public abstract String getFilePath();

    private String initFilePath() {
        if (StringUtil.isBlank(this.filePath)) {
            this.filePath = getFilePath();
        }
        return this.filePath;
    }

    protected String getMaxSize() {
        return maxSize;
    }

    @RequestMapping("/upload-file")
    @ResponseBody
    public String uploadFile(MultipartHttpServletRequest req) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", false);
        if (FileUtil.getMultipartFile(req) == null) {
            map.put("msg", "文件为空！");
            return JSONObject.toJSONString(map);
        }
        // 获取文件上传最大限制
        String size = StringUtil.isBlank(req.getParameter("size")) ? getMaxSize() : req.getParameter("size");
        long fileSize = FileUtil.getMultipartFile(req).getSize();
        if (FileUtil.maxSize(size, fileSize)) {
            map.put("msg", "文件大小超出".concat(size));
            return JSONObject.toJSONString(map);
        }
        map.put("size", FileUtil.getSize(fileSize));
        try {
            // 上传文件
            String path = FileUtil.getActionPath(req, initFilePath());
            map.put("url", path);
            map.put("name", path.split("-")[1].replaceAll(FileUtil.actionPath, ""));
            map.put("status", true);
        } catch (IOException e) {
            map.put("msg", "上传失败！");
            e.printStackTrace();
        }
        return JSONObject.toJSONString(map);
    }

    @RequestMapping("/{fileName}/file")
    public void downloadFile(@PathVariable String fileName, HttpServletResponse rep) {
        try {
            FileUtil.download(rep, initFilePath().concat("/").concat(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

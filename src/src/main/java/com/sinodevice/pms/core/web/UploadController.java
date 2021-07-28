package com.sinodevice.pms.core.web;

import com.baomidou.mybatisplus.extension.api.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 文件上传 Controller
 * </p>
 */
@RestController
@Slf4j
@RequestMapping("/upload")
public class UploadController {

    @Value("${spring.servlet.multipart.location}")
    private String fileTempPath;

    @PostMapping(value = "/local", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public R<Map> local(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return R.failed("文件内容为空");
        }
        String fileName = file.getOriginalFilename();
        String rawFileName = StringUtils.substring(fileName, 0, fileName.lastIndexOf("."));
        String fileType = StringUtils.substring(fileName, fileName.lastIndexOf("."), fileName.length());
        String localFilePath = new Date().getTime() + fileType;
        try {
            file.transferTo(new File(localFilePath));
        } catch (IOException e) {
            log.error("【文件上传至本地】失败，绝对路径：{}", localFilePath);
            return R.failed("文件上传失败");
        }

        log.info("【文件上传至本地】绝对路径：{}", localFilePath);

        Map map = new HashMap<>();
        map.put("fileName", fileName);
        map.put("filePath", "upload/download/" + localFilePath);
        return R.ok(map);
    }

    //实现Spring Boot 的文件下载功能，映射网址为/download
    @RequestMapping("/download/{fileName}")
    public String downloadFile(@PathVariable String fileName, HttpServletResponse response) throws UnsupportedEncodingException {
        // 如果文件名不为空，则进行下载
        if (fileName != null) {
            //设置文件路径
            File file = new File(fileTempPath, fileName);

            // 如果文件名存在，则进行下载
            if (file.exists()) {

                // 配置文件下载
                response.setHeader("content-type", "application/octet-stream");
                response.setContentType("application/octet-stream");
                // 下载文件能正常显示中文
                response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));

                // 实现文件下载
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    System.out.println("Download the song successfully!");
                } catch (Exception e) {
                    System.out.println("Download the song failed!");
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return null;
    }


}

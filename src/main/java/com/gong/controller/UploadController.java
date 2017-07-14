package com.gong.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * Created by Administrator on 2017/7/14.
 */
@Controller
public class UploadController {
    @RequestMapping(value = "/upload")
    public String upload(@RequestParam(value = "file",required = false) MultipartFile file, HttpServletRequest request, ModelMap modelMap){
        String path = request.getSession().getServletContext().getRealPath("upload");
        String fileName = file.getOriginalFilename();
        File targetFile = new File(path,fileName);
        if (!targetFile.exists()){
            targetFile.mkdirs();
        }
            try {
                file.transferTo(targetFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            modelMap.addAttribute("msg","上传成功");
            return "result";
        }
}

package com.example.canyon_gaming.controller;


import com.example.canyon_gaming.common.Result;
import com.example.canyon_gaming.service.IFilesService;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author sen
 * @since 2023-05-14
 */
@RestController
@RequestMapping("/files")
public class FilesController {
    @Resource
    IFilesService filesService;

    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws IOException {
        return Result.success(filesService.upload(file));
    }
    @GetMapping("/download/{uploadFileName}")
    public void download(@PathVariable String uploadFileName, HttpServletResponse response) throws Exception{
        filesService.download(uploadFileName,response);
    }
}

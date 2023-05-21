package com.example.canyon_gaming.service;

import com.example.canyon_gaming.entity.Files;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
* @author HealMe
* @description 针对表【files】的数据库操作Service
* @createDate 2023-05-19 12:20:07
*/
public interface IFilesService extends IService<Files> {
    String upload(MultipartFile file) throws IOException;

    void download(@PathVariable String uploadFileName, HttpServletResponse response) throws Exception;
}

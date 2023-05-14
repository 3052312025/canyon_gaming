package com.example.canyon_gaming.service;

import com.example.canyon_gaming.entity.Files;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sen
 * @since 2023-05-14
 */
public interface IFilesService extends IService<Files> {
    //文件下载方法
    Files upload(MultipartFile file) throws IOException;

}

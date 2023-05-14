package com.example.canyon_gaming.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import com.example.canyon_gaming.common.Constants;
import com.example.canyon_gaming.entity.Files;
import com.example.canyon_gaming.exception.ServiceException;
import com.example.canyon_gaming.mapper.FilesMapper;
import com.example.canyon_gaming.service.IFilesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sen
 * @since 2023-05-14
 */
@Service
public class FilesServiceImpl extends ServiceImpl<FilesMapper, Files> implements IFilesService {
    //配置好的文件存储地址
    @Value("${files.upload.path}")
    private String rootPath;
    @Autowired(required = false)
    private FilesMapper filesMapper;

    @Override
    public Files upload(MultipartFile file) {
        File parentPath = new File(rootPath);
        if (!parentPath.exists())//判断配置的文件目录是否存在，若不存在则创建一个新的文件目录
            parentPath.mkdirs();
        String uuid = IdUtil.fastSimpleUUID();//定义文件的唯一标识
        String originalFilename = file.getOriginalFilename();
        String extName = FileUtil.extName(originalFilename);//获取文件类型
        String uploadFileName = uuid + "." + extName;
        File uploadFile = new File(parentPath + "\\" + uploadFileName);//文件的上传路径
        try {
            file.transferTo(uploadFile);//上传文件
            String url = uploadFile.getAbsolutePath();//获取文件的地址
            Files files = new Files();
            files.setFilename(uploadFileName);
            files.setFileurl(url);
            filesMapper.insert(files);
            return  files;
        }catch (IOException ioException){
            throw  new ServiceException(Constants.CODE_600.getCode(), "上传图片失败!");
        }
    }

}

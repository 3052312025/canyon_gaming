package com.example.canyon_gaming.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.canyon_gaming.entity.Files;
import com.example.canyon_gaming.mapper.FilesMapper;
import com.example.canyon_gaming.service.IFilesService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

/**
* @author HealMe
* @description 针对表【files】的数据库操作Service实现
* @createDate 2023-05-19 12:20:07
*/
/**
 * @author HealMe
 * @description 针对表【files】的数据库操作Service实现
 * @createDate 2023-05-19 12:02:38
 */
@Service
public class FilesServiceImpl extends ServiceImpl<FilesMapper, Files>
        implements IFilesService {
    @Value("${files.upload.path}")
    private String rootPath;

    @Resource
    private FilesMapper filesMapper;

    /**
     * 上传接口
     * @param file  前端传过来的文件
     * @throws IOException
     */
    public String upload(MultipartFile file) throws IOException {
        File parentPath = new File(rootPath);
        if (!parentPath.exists())//判断配置的文件目录是否存在，若不存在则创建一个新的文件目录
            parentPath.mkdirs();
        String uuid = IdUtil.fastSimpleUUID();//定义文件的唯一标识
        String originalFilename = file.getOriginalFilename();
        String extName = FileUtil.extName(originalFilename);//获取文件类型
        String uploadFileName = uuid+"."+extName;
        File uploadFile = new File(parentPath + "\\" + uploadFileName);//文件的上传路径
        file.transferTo(uploadFile);//上传文件到磁盘
        String md5 = SecureUtil.md5(uploadFile);//获取文件的md5
        Files dbFile = getFileByMd5(md5);//从数据库查询是否存在相同的文件
        String fileUrl;
        if (dbFile != null){
            uploadFile.delete();
            System.out.println("发现相同文件，删除成功");
            fileUrl=dbFile.getFileurl();
            System.out.println(fileUrl);
            return fileUrl;
        } else{
            fileUrl ="http://localhost:8008/files/download/"+uploadFileName;
            Files files = new Files();
            files.setFileurl(fileUrl);
            files.setMd5(md5);
            filesMapper.insert(files);
            System.out.println(fileUrl);
            return fileUrl;
        }
    }
    /**
     * 下载接口
     * @param uploadFileName
     * @param response
     */
    public void download(@PathVariable String uploadFileName, HttpServletResponse response) throws Exception{
        //根据文件的唯一标识码获取文件
        File uploadPath = new File(rootPath + "\\" + uploadFileName);
        //设置输出流的格式
        ServletOutputStream os = response.getOutputStream();
        response.addHeader("Content-Disposition","attachment;filename=" + URLEncoder.encode(uploadFileName,"UTF-8"));
        response.setContentType("application/octet-stream");
        //读取文件的字节流
        os.write(FileUtil.readBytes(uploadPath));
        os.flush();
        os.close();
    }

    private Files getFileByMd5(String md5){
        QueryWrapper<Files> filesQueryWrapper = new QueryWrapper<>();
        filesQueryWrapper.eq("md5",md5);
        Files one = filesMapper.selectOne(filesQueryWrapper);
        return one;
    }
    
}





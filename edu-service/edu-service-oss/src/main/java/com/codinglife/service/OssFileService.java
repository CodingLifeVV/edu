package com.codinglife.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author CodingLife
 * @Description Oss对象存储管理
 * @since 2022/3/14 15:50
 */
public interface OssFileService {
    /**
     * 文件上传到阿里云
     * @param file
     * @return
     */
    String upload(MultipartFile file);
}

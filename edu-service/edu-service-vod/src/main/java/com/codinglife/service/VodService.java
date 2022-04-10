package com.codinglife.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Description: 视频点播服务接口
 * @author: CodingLifeVV
 * @date: 2022.04.10
 */
public interface VodService {

    String uploadVideo(MultipartFile file);

    void removeVideo(String videoId);

    void removeAllVideo(List videoIdList);
}

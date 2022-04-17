package com.codinglife.service.impl;


import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
import com.codinglife.exception.CustomizeApiException;
import com.codinglife.service.VodService;
import com.codinglife.utils.AliyunVodConfigConstant;
import com.codinglife.utils.AliyunVodSDKUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

/**
 * @Description: 视频点播服务实现类
 * Service: CodingLifeVV
 * @date: 2022.04.10
 */
@Service
@Slf4j
public class VodServiceImpl implements VodService {

    /**
     * 上传视频到阿里云端
     * @param file
     * @return
     */
    @Override
    public String uploadVideo(MultipartFile file) {
        try {
            //accessKeyId, accessKeySecret
            //fileName：上传文件原始名称
            // 01.03.09.mp4
            String fileName = file.getOriginalFilename();
            //title：上传之后显示名称
            String title = fileName.substring(0, fileName.lastIndexOf("."));
            //inputStream：上传文件输入流
            InputStream inputStream = file.getInputStream();
            UploadStreamRequest request = new UploadStreamRequest(AliyunVodConfigConstant.ACCESS_KEY_ID, AliyunVodConfigConstant.ACCESS_KEY_SECRET, title, fileName, inputStream);

            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);

            String videoId = response.getVideoId();
            // 如果设置回调URL无效,不影响视频上传,可以返回VideoId同时会返回错误码
            // 其他情况上传失败时,VideoId为空,此时需要根据返回错误码分析具体错误原因
            if (!response.isSuccess()) {
                String errorMessage = "阿里云上传错误：" + "code：" +
                        response.getCode() + ", message：" + response.getMessage();
                log.warn(errorMessage);
                if(ObjectUtils.isEmpty(videoId)){
                    throw new CustomizeApiException(20001, errorMessage);
                }
            }

            return videoId;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据视频id移除单个云端视频
     * @param videoId
     */
    @Override
    public void removeVideo(String videoId) {
        try {
            //初始化对象
            DefaultAcsClient client = AliyunVodSDKUtils.initVodClient(
                    AliyunVodConfigConstant.ACCESS_KEY_ID,
                    AliyunVodConfigConstant.ACCESS_KEY_SECRET);
            //创建删除视频的request对象
            DeleteVideoRequest request = new DeleteVideoRequest();
            //向request中设置videoId
            request.setVideoIds(videoId);
            //调用删除方法
            DeleteVideoResponse response = client.getAcsResponse(request);

            log.info("RequestId = " + response.getRequestId() + "\n");

        } catch (ClientException e) {
            throw new CustomizeApiException(20001, "视频删除失败");
        }
    }

    /**
     * 移除视频集合
     * @param videoIdList
     */
    @Override
    public void removeAllVideo(List videoIdList) {
        try {
            //初始化对象
            DefaultAcsClient client = AliyunVodSDKUtils.initVodClient(
                    AliyunVodConfigConstant.ACCESS_KEY_ID,
                    AliyunVodConfigConstant.ACCESS_KEY_SECRET);
            //创建删除视频的request对象
            DeleteVideoRequest request = new DeleteVideoRequest();

            //遍历数组元素 a[1,2,3,4] 变为 1,2,3,4
            String join = StringUtils.join(videoIdList.toArray(), ",");

            //向request中设置videoId
            request.setVideoIds(join);
            //调用删除方法
            DeleteVideoResponse response = client.getAcsResponse(request);

            System.out.print("RequestId = " + response.getRequestId() + "\n");

        } catch (ClientException e) {
            throw new CustomizeApiException(20001, "视频删除失败");
        }
    }
}

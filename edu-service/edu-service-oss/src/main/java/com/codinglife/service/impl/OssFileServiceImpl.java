package com.codinglife.service.impl;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.CannedAccessControlList;
import com.codinglife.service.OssFileService;
import com.codinglife.utils.AliyunOSSConfigConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.joda.time.DateTime;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author CodingLife
 * @Description 文件上传阿里云实现类
 * @since 2022/3/14 15:50
 * https://segmentfault.com/a/1190000017273629
 * https://segmentfault.com/a/1190000020963346
 * https://segmentfault.com/a/1190000023698805
 */
@Slf4j
@Service
public class OssFileServiceImpl implements OssFileService {

    @Override
    public String upload(MultipartFile file) {

        String bucketName = AliyunOSSConfigConstant.BUCKET_NAME;
        String endpoint = AliyunOSSConfigConstant.END_POINT;
        String accessKeyId = AliyunOSSConfigConstant.ACCESS_KEYID;
        String accessKeySecret = AliyunOSSConfigConstant.ACCESS_KEYSECRET;
        String uploadUrl; // 上传URL

        // 创建OSSClient实例
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {
            //判断oss实例是否存在, 不存在则创建
            if (!ossClient.doesBucketExist(bucketName)) {
                //创建bucket
                ossClient.createBucket(bucketName);
                // oss实例的访问权限:公共读
                ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
            }
            // 上传文件字节输入流
            InputStream inputStream = file.getInputStream();

            // 构建日期路径
            String fileUrl = file.getOriginalFilename(); // 获取文件名称
            String uuid = UUID.randomUUID().toString().replace("-", ""); // 拼接uuid
            String dateStr = new DateTime().toString("yyyy/MM/dd"); // 拼接时间
            fileUrl = dateStr + "/" + uuid + fileUrl;

            // 调用oss进行上传
            // 参数分别为 BucketName, 上传到oss文件的路径和名称, 第三个文件输入流
            ossClient.putObject(bucketName, fileUrl, inputStream);

            uploadUrl = "http://" + bucketName + "." + endpoint + "/" + fileUrl;
            return uploadUrl;

        } catch (OSSException oe) { // 以下 catch 语句来自阿里云帮助文档
            log.error(oe.getMessage());
        } catch (ClientException ce) {
            log.error(ce.getMessage());
        } catch (IOException ie) {
            log.error(ie.getMessage());
        } finally {
            if (ossClient != null) { // 最后要关闭 ossClient
                ossClient.shutdown();
            }
        }
        return null;
    }
}

package com.stylefeng.guns.core.utils;


import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.stylefeng.guns.config.properties.AliyunOssProperties;
import com.stylefeng.guns.core.domain.FilePath;
import com.stylefeng.guns.core.enums.ResultOssEnum;
import com.stylefeng.guns.core.exceptions.FileUploadException;
import com.stylefeng.guns.core.util.ToolUtil;

import jodd.datetime.JDateTime;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;


/**
 * Created by GQ
 * 2018年1月23日 上午11:19:22
 * 阿里对象存储工具类
 */
@Service
public class OssUtil {

    @Autowired
    AliyunOssProperties aliyunProp;

    // endpoint 是访问OSS的域名.
    private static String endpoint = "https://oss-cn-hangzhou.aliyuncs.com";

    /**
     * 文件上传 单文件
     *
     * @param file
     * @return
     * @throws Exception
     */
    public FilePath transferTo(MultipartFile file) {
        JDateTime jdt = new JDateTime();
        jdt.setCurrentTime();
        StringBuffer key = new StringBuffer("upload/image/" + jdt.toString("YYYYMM") + "/").append(UUID.randomUUID().toString()).append(".").append(FilenameUtils.getExtension(file.getOriginalFilename()));
        // 生成OSSClient.
        OSSClient ossClient = new OSSClient(endpoint, aliyunProp.getOssAccessKeyId(), aliyunProp.getOssAccessKeySecret());
        // 判断Bucket是否存在.
        String bucketName = aliyunProp.getOssBucket();
        if (!ossClient.doesBucketExist(bucketName)) {
            ossClient.createBucket(bucketName);
        }

        FilePath path = new FilePath();
        // 把file存入OSS,Object的名称为firstKey.
        try {
            ossClient.putObject(bucketName, key.toString(), file.getInputStream());
            StringBuffer filePath = new StringBuffer(getOssDomain()).append(key);
            path.setFileName(file.getOriginalFilename());
            path.setContentType(file.getContentType());
            path.setFileKey(key.toString());
            path.setFileRealPath(filePath.toString());
            path.setFileSize(file.getSize());
        } catch (Exception e) {
            e.printStackTrace();
            throw new FileUploadException(ResultOssEnum.FILE_UPLOAD_ERROR, path);
        } finally {
            ossClient.shutdown();
        }
        return path;
    }

    private String getOssDomain() {
        if (ToolUtil.isEmpty(aliyunProp.getOssDomain())) {
            return MessageFormat.format("https://{0}.oss-cn-hangzhou.aliyuncs.com/", aliyunProp.getOssBucket());
        } else {
            return aliyunProp.getOssDomain();
        }
    }

    /**
     * 文件上传
     *
     * @param files
     * @return
     * @throws Exception
     */
    public List<FilePath> transferTo(MultipartFile[] files) {
        JDateTime jdt = new JDateTime();
        jdt.setCurrentTime();

        // 生成OSSClient
        OSSClient ossClient = new OSSClient(endpoint, aliyunProp.getOssAccessKeyId(), aliyunProp.getOssAccessKeySecret());
        // 判断Bucket是否存在
        String bucketName = aliyunProp.getOssBucket();
        if (!ossClient.doesBucketExist(bucketName)) {
            ossClient.createBucket(bucketName);
        }

        StringBuffer basePath = new StringBuffer("upload/image/" + jdt.toString("YYYYMM") + "/");
        StringBuffer key = new StringBuffer();
        List<FilePath> paths = new ArrayList<>();
        try {
            for (MultipartFile file : files) {
                if (file != null && file.getSize() > 0) {
                    key.setLength(0);
                    key.append(basePath).append(UUID.randomUUID().toString()).append(".").append(FilenameUtils.getExtension(file.getOriginalFilename()));
                    ossClient.putObject(bucketName, key.toString(), file.getInputStream());
                    FilePath path = new FilePath();
                    path.setFileName(file.getOriginalFilename());
                    path.setContentType(file.getContentType());
                    path.setFileKey(key.toString());
                    path.setFileRealPath(key.insert(0, getOssDomain()).toString());
                    path.setFileSize(file.getSize());
                    paths.add(path);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new FileUploadException(ResultOssEnum.FILE_UPLOAD_ERROR, paths);
        } finally {
            ossClient.shutdown();
        }
        return paths;
    }

    public void deleteObjects(List<FilePath> paths) {
        // 生成OSSClient.
        OSSClient ossClient = new OSSClient(endpoint, aliyunProp.getOssAccessKeyId(), aliyunProp.getOssAccessKeySecret());
        try {
            String bucketName = aliyunProp.getOssBucket();
            for (FilePath filePath : paths) {
                ossClient.deleteObject(bucketName, filePath.getFileKey());
                System.out.println("ossClient.deleteObject >> " + filePath.getFileName() + "\t" + filePath.getFileKey());
            }
        } catch (OSSException e) {
            e.printStackTrace();
        } finally {
            ossClient.shutdown();
        }
    }
}

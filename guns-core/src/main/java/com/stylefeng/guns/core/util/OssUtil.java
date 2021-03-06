package com.stylefeng.guns.core.util;


import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.stylefeng.guns.core.config.properties.AliyunProperties;
import com.stylefeng.guns.core.domain.FilePath;
import com.stylefeng.guns.core.enums.OssType;
import com.stylefeng.guns.core.enums.ResultOssEnum;
import com.stylefeng.guns.core.exception.FileUploadException;
import com.stylefeng.guns.core.util.ToolUtil;

import jodd.datetime.JDateTime;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;



/**
 * Created by GQ
 * 2018年1月23日 上午11:19:22
 * 阿里对象存储工具类
 */
@Service
public class OssUtil {

    @Autowired
    AliyunProperties aliyunProp;

    // endpoint 是访问OSS的域名.
    private static String endpoint = "https://oss-cn-hangzhou.aliyuncs.com";

    /**
     * 文件上传 单文件
     *
     * @param file
     * @return
     * @throws Exception
     */
    public FilePath transferTo(MultipartFile file,OssType ossType) {
        JDateTime jdt = new JDateTime();
        jdt.setCurrentTime();
        
        String folderName = "";
        switch (ossType) {
		case OSS_IMAGE:
			folderName = "image";
			break;
		case OSS_FILE:
			folderName = "file";
			break;
		default:
			break;
		}
        
        StringBuffer key = new StringBuffer("upload/"+folderName+"/" + jdt.toString("YYYYMM") + "/").append(UUID.randomUUID().toString()).append(".").append(FilenameUtils.getExtension(file.getOriginalFilename()));
        // 生成OSSClient.
        OSSClient ossClient = new OSSClient(endpoint, aliyunProp.getOss().getAccessKeyId(), aliyunProp.getOss().getAccessKeySecret());
        // 判断Bucket是否存在.
        String bucketName = aliyunProp.getOss().getBucket();
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
        if (ToolUtil.isEmpty(aliyunProp.getOss().getDomain())) {
            return MessageFormat.format("https://{0}.oss-cn-hangzhou.aliyuncs.com/", aliyunProp.getOss().getBucket());
        } else {
            return aliyunProp.getOss().getDomain();
        }
    }

    /**
     * 文件上传
     *
     * @param files
     * @return
     * @throws Exception
     */
    public List<FilePath> transferTo(MultipartFile[] files,OssType ossType) {
        JDateTime jdt = new JDateTime();
        jdt.setCurrentTime();

        // 生成OSSClient
        OSSClient ossClient = new OSSClient(endpoint, aliyunProp.getOss().getAccessKeyId(), aliyunProp.getOss().getAccessKeySecret());
        // 判断Bucket是否存在
        String bucketName = aliyunProp.getOss().getBucket();
        if (!ossClient.doesBucketExist(bucketName)) {
            ossClient.createBucket(bucketName);
        }

        String folderName = "";
        switch (ossType) {
		case OSS_IMAGE:
			folderName = "image";
			break;
		case OSS_FILE:
			folderName = "file";
			break;
		default:
			break;
		}
        
        StringBuffer basePath = new StringBuffer("upload/"+folderName+"/" + jdt.toString("YYYYMM") + "/");
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
        OSSClient ossClient = new OSSClient(endpoint, aliyunProp.getOss().getAccessKeyId(), aliyunProp.getOss().getAccessKeySecret());
        try {
            String bucketName = aliyunProp.getOss().getBucket();
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

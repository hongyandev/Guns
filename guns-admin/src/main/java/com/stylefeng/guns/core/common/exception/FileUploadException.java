package com.stylefeng.guns.core.common.exception;

import java.util.Arrays;
import java.util.List;

import com.stylefeng.guns.core.common.file.FilePath;
import com.stylefeng.guns.core.common.enums.ResultEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by GQ
 * 2018年1月23日 上午10:55:53
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class FileUploadException extends RuntimeException {
    private static final long serialVersionUID = 492763763279338469L;
    private String code;
    private List<FilePath> paths;


    public FileUploadException(String code,String msg,FilePath path) {
        super(msg);
        this.code = code;
        this.paths = Arrays.asList(path);
    }

    public FileUploadException(ResultEnum resultEnum, List<FilePath> paths) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
        this.paths = paths;
    }

    public FileUploadException(String code,String msg, List<FilePath> paths){
        super(msg);
        this.code = code;
        this.paths = paths;
    }
}

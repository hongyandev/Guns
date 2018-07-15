package com.stylefeng.guns.core.common.exception;

import java.util.Arrays;
import java.util.List;

import com.stylefeng.guns.core.common.file.FilePath;
import com.stylefeng.guns.core.exception.GunsException;
import com.stylefeng.guns.core.exception.ServiceExceptionEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by GQ
 * 2018年1月23日 上午10:55:53
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class FileUploadException extends GunsException {
    private static final long serialVersionUID = 492763763279338469L;
    private List<FilePath> paths;

    public FileUploadException(ServiceExceptionEnum serviceExceptionEnum, FilePath path) {
        super(serviceExceptionEnum);
        this.paths = Arrays.asList(path);
    }

    public FileUploadException(ServiceExceptionEnum serviceExceptionEnum, List<FilePath> paths) {
        super(serviceExceptionEnum);
        this.paths = paths;
    }
}

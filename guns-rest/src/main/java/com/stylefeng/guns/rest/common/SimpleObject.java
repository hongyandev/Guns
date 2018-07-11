package com.stylefeng.guns.rest.common;

import org.springframework.web.multipart.MultipartFile;

/**
 * 测试用的
 *
 * @author fengshuonan
 * @date 2017-08-25 16:47
 */
public class SimpleObject {

    private String user;

    private String name;

    private String tips;

    private Integer age;
    
    private MultipartFile[] files;

    public MultipartFile[] getFiles() {
		return files;
	}

	public void setFiles(MultipartFile[] files) {
		this.files = files;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}

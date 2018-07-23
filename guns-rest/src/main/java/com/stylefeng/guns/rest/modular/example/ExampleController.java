package com.stylefeng.guns.rest.modular.example;

import com.stylefeng.guns.core.support.HttpKit;
import com.stylefeng.guns.rest.core.SimpleObject;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

/**
 * 常规控制器
 *
 * @author fengshuonan
 * @date 2017-08-23 16:02
 */
@Controller
@RequestMapping("/hello")
public class ExampleController {

    @RequestMapping("")
    public ResponseEntity hello(@RequestBody SimpleObject simpleObject) {
        System.out.println(simpleObject.getUser());
        return ResponseEntity.ok("请求成功!");
    }
    
    @RequestMapping("/file")
    public ResponseEntity hello(MultipartFile file) {
    	System.out.println(file.getOriginalFilename() +"\t content-type "+HttpKit.getRequest().getContentType());
        return ResponseEntity.ok("请求成功!");
    }
}

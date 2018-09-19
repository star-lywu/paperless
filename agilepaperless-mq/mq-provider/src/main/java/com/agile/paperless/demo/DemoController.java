package com.agile.paperless.demo;

import com.agile.paperless.api.demo.DemoClient;
import com.agile.paperless.db.entity.Demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: WuYL
 * @Description: 演示 feign
 * @Date: Create in 2018/5/23 10:29
 * @Modified By:
 * @Copyright: 2018 www.agilestarcn.com Inc. All rights reserved.
 */

@RestController
@RequestMapping("/backstage")
public class DemoController {

    @Autowired
    private DemoClient demoClient;

    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Demo get(@PathVariable("id") Long id){
        return demoClient.get(id);
    }
}

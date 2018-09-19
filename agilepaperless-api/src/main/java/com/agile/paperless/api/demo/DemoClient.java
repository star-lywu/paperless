package com.agile.paperless.api.demo;

import com.agile.paperless.db.entity.Demo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author: WuYL
 * @Description:
 * @Date: Create in 2018/5/23 10:04
 * @Modified By:
 * @Copyright: 2018 www.agilestarcn.com Inc. All rights reserved.
 */
@FeignClient(name = "mainprocess-controller", fallbackFactory = DemoFallbackFactory.class)
public interface DemoClient {

    @GetMapping("/demo/get/{id}")
    Demo get(@PathVariable("id") Long id);
}

package com.agile.paperless.demo;

import com.agile.paperless.db.entity.Demo;
import com.agile.paperless.service.IDemoService;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: WuYL
 * @Description: 演示 控制器
 * @Date: Create in 2018/5/22 14:09
 * @Modified By:
 * @Copyright: 2018 www.agilestarcn.com Inc. All rights reserved.
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private IDemoService demoService;

    @ApiOperation(value = "获取信息", notes = "根据ID获取信息")
    @ApiImplicitParams({
    })
    @ApiResponse(code = 200, message = "参见result中的message")
    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object get(@PathVariable("id") Long id){
        System.out.println("-------id = " + id);

        Demo demo =  new Demo();
        demo.setId(id);
        demo.setValue("demo");
        return demo;
    }

    @ApiOperation(value = "添加信息", notes = "添加一个示例信息")
    @ApiResponse(code = 200, message = "参见result中的message")
    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object add(@RequestBody Demo demo){
        System.out.println(demo.toString());
        demo.setValue("修改");
        return demo;
    }

    @GetMapping("/set")
    public void set(){
        Demo demo = new Demo();
        demo.setValue("你好");
        demo.setId(123L);
        demoService.set(demo);
    }

}

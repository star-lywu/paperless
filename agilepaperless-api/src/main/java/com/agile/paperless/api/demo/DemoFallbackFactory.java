package com.agile.paperless.api.demo;

import com.agile.paperless.db.entity.Demo;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @Author: WuYL
 * @Description: 演示 hystrix 的熔断机制
 * @Date: Create in 2018/5/23 10:06
 * @Modified By:
 * @Copyright: 2018 www.agilestarcn.com Inc. All rights reserved.
 */
@Component
public class DemoFallbackFactory implements FallbackFactory<DemoClient>{

    @Override
    public DemoClient create(Throwable throwable) {
        return new DemoClient(){
            @Override
            public Demo get(Long id) {
                Demo demo = new Demo();
                demo.setId(id);
                demo.setValue("失败");
                return demo;
            }
        };
    }
}

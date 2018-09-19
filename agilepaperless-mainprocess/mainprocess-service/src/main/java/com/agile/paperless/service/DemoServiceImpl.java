package com.agile.paperless.service;

import com.agile.paperless.db.entity.Demo;
import com.agile.paperless.redis.RedisHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @Author: WuYL
 * @Description:
 * @Date: Create in 2018/5/23 9:34
 * @Modified By:
 * @Copyright: 2018 www.agilestarcn.com Inc. All rights reserved.
 */
@Service
public class DemoServiceImpl implements IDemoService {

    @Autowired
    private RedisHelper redisHelper;

    @Override
    public Demo get(Long id) {
        Demo demo = new Demo();
        demo.setId(id);
        demo.setValue("service");
        return demo;
    }

    @Override
    public void set(Demo demo){
        boolean set = redisHelper.set("demo", demo);
        System.out.println(set);
        Demo o = (Demo)redisHelper.get("demo");
        System.out.println(o);
    }

}

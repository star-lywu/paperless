package com.agile.paperless.service;

import com.agile.paperless.db.entity.Demo;

/**
 * @Author: WuYL
 * @Description: 演示 业务层接口
 * @Date: Create in 2018/5/23 9:27
 * @Modified By:
 * @Copyright: 2018 www.agilestarcn.com Inc. All rights reserved.
 */
public interface IDemoService {

    Demo get(Long id);

    void set(Demo demo);
}

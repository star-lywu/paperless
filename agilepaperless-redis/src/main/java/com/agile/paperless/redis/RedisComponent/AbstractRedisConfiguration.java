package com.agile.paperless.redis.RedisComponent;

import com.agile.paperless.redis.configure.RedisPropertySource;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Author: WuYL
 * @Description: 基类 -> 使用组合模式实现多种redis连接方式
 * @Date: Create in 2018/5/24 14:59
 * @Modified By:
 * @Copyright: 2018 www.agilestarcn.com Inc. All rights reserved.
 */
public abstract class AbstractRedisConfiguration {

    public AbstractRedisConfiguration() {
    }

    public abstract JedisConnectionFactory factory(RedisPropertySource source, JedisPoolConfig config);

}

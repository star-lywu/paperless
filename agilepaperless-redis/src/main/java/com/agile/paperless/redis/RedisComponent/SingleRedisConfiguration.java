package com.agile.paperless.redis.RedisComponent;

import com.agile.paperless.redis.configure.RedisPropertySource;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.util.StringUtils;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Author: WuYL
 * @Description: 单机版
 * @Date: Create in 2018/5/24 15:03
 * @Modified By:
 * @Copyright: 2018 www.agilestarcn.com Inc. All rights reserved.
 */
public class SingleRedisConfiguration extends AbstractRedisConfiguration {

    public SingleRedisConfiguration() {
        super();
    }

    @Override
    public JedisConnectionFactory factory(RedisPropertySource source, JedisPoolConfig config) {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        //连接池
        jedisConnectionFactory.setPoolConfig(config);
        //IP地址
        jedisConnectionFactory.setHostName(source.getHostName());
        //端口号
        jedisConnectionFactory.setPort(source.getPort());
        //如果Redis设置有密码
        if(!StringUtils.isEmpty(source.getPassword())){
            jedisConnectionFactory.setPassword(source.getPassword());
        }
        //客户端超时时间单位是毫秒
        jedisConnectionFactory.setTimeout(source.getTimeOut());
        return jedisConnectionFactory;
    }
}

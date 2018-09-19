package com.agile.paperless.redis.RedisComponent;

import com.agile.paperless.redis.configure.RedisPropertySource;
import com.google.common.base.Splitter;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.util.StringUtils;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author: WuYL
 * @Description: 哨兵模式
 * @Date: Create in 2018/5/24 16:01
 * @Modified By:
 * @Copyright: 2018 www.agilestarcn.com Inc. All rights reserved.
 */
public class SentinelRedisConfiguration extends AbstractRedisConfiguration {

    @Override
    public JedisConnectionFactory factory(RedisPropertySource source, JedisPoolConfig config) {
        RedisSentinelConfiguration redisSentinelConfiguration = new RedisSentinelConfiguration();
        //配置matser的名称
        RedisNode redisNode = new RedisNode(source.getHostName(), source.getPort());
        redisNode.setName(source.getMasterName());
        redisSentinelConfiguration.master(redisNode);
        //配置redis的哨兵sentinel
        Iterable serverArray = Splitter.on(",").trimResults().omitEmptyStrings().split(source.getSentinelNodes());
        Set<RedisNode> redisNodeSet = new HashSet<>();
        serverArray.forEach(ipAndPort -> {
            List<String> ipPort = Splitter.on(":").trimResults().omitEmptyStrings().splitToList((String) ipAndPort);
            redisNodeSet.add(new RedisNode(ipPort.get(0), Integer.parseInt(ipPort.get(1))));
        });
        redisSentinelConfiguration.setSentinels(redisNodeSet);
        JedisConnectionFactory jedisConnectionFactory =  new JedisConnectionFactory(redisSentinelConfiguration,config);
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

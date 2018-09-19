package com.agile.paperless.redis.configure;

import com.agile.paperless.redis.RedisComponent.AbstractRedisConfiguration;
import com.agile.paperless.redis.RedisComponent.ClusterRedisConfiguration;
import com.agile.paperless.redis.RedisComponent.SentinelRedisConfiguration;
import com.agile.paperless.redis.RedisComponent.SingleRedisConfiguration;
import com.agile.paperless.redis.RedisHelper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Author: WuYL
 * @Description: 配置 Redis
 * @Date: Create in 2018/5/23 17:24
 * @Modified By:
 * @Copyright: 2018 www.agilestarcn.com Inc. All rights reserved.
 */
@Configuration
//开启注解
@EnableCaching
public class TemplateConfiguration {

//    private static final String SINGLE = "single";
//    private static final String CLUSTER = "cluster";
//    private static final String SENTINEL = "sentinel";

    protected enum Mode{
        SINGLE,CLUSTER,SENTINEL
    }

    @Bean
    public CacheManager cacheManager(RedisTemplate<?, ?> redisTemplate) {
        CacheManager cacheManager = new RedisCacheManager(redisTemplate);
        return cacheManager;
        /*RedisCacheManager rcm = new RedisCacheManager(redisTemplate);
        // 多个缓存的名称,目前只定义了一个
        rcm.setCacheNames(Arrays.asList("thisredis"));
        //设置缓存默认过期时间(秒)
        rcm.setDefaultExpiration(600);
        return rcm;*/
    }

    @Bean
    public JedisConnectionFactory factory(RedisPropertySource source, JedisPoolConfig config){
        AbstractRedisConfiguration configuration = null;
        if(Mode.SINGLE.name().equalsIgnoreCase(source.getConnectMode())){
            //单机模式
            configuration = new SingleRedisConfiguration();
        }else if(Mode.CLUSTER.name().equalsIgnoreCase(source.getConnectMode())){
            //集群模式
            configuration = new ClusterRedisConfiguration();
        }else if(Mode.SENTINEL.name().equalsIgnoreCase(source.getConnectMode())){
            //哨兵模式
            configuration = new SentinelRedisConfiguration();
        } else {
            //非法模式
            return null;
        }
        return configuration.factory(source, config);
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(JedisConnectionFactory factory, RedisPropertySource source) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        //使用fastJson 序列化和反序列化
        FastJsonSerializer<Object> serializer = new FastJsonSerializer<>(Object.class);
        template.setValueSerializer(serializer);
        //使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(new StringRedisSerializer());
        template.afterPropertiesSet();

        // 开启事务
        template.setEnableTransactionSupport(source.getEnableTransaction());
        return template;
    }
    @Bean
    public StringRedisTemplate stringRedisTemplate(JedisConnectionFactory factory) {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(factory);
        return stringRedisTemplate;
    }

    @Bean(name = "redisHelper")
    public RedisHelper redisHelper(RedisTemplate<String, Object> redisTemplate){
        return new RedisHelper(redisTemplate);
    }
}

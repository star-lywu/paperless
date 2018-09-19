package com.agile.paperless.redis.configure;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;

/**
 * @Author: WuYL
 * @Description:
 * @Date: Create in 2018/5/23 17:37
 * @Modified By:
 * @Copyright: 2018 www.agilestarcn.com Inc. All rights reserved.
 */
public class FastJsonSerializer<T> implements RedisSerializer<T> {

    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
    static {
        JSON.parseObject(JSON.toJSONString(new Object()), Object.class);
    }

    private Class<T> clazz;

    public FastJsonSerializer(Class<T> clazz) {
        super();
        this.clazz = clazz;
    }

    @Override
    public byte[] serialize(T t) throws SerializationException {
        if (t == null) {
            return new byte[0];
        }
        return JSON.toJSONString(t, SerializerFeature.WriteClassName).getBytes(DEFAULT_CHARSET);
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null || bytes.length <= 0) {
            return null;
        }
        return (T) JSON.parseObject(new String(bytes, DEFAULT_CHARSET), clazz);
    }

    public static void main(String[] args) {
        RedisSerializer<Object> serializer = new FastJsonSerializer<Object>(Object.class);
        Instant start = Instant.now();
        User user =  new User("123456789", "abcdefj");
        byte[] bytes = null;
        for (int i = 0; i < 1000; i++){
            bytes = serializer.serialize(user);
        }
        Instant serEnd = Instant.now();
        System.out.println("序列化消耗时间:" + Duration.between(start, serEnd).toMillis());

        for (int i = 0; i < 1000; i++){
            User users = (User)serializer.deserialize(bytes);
        }
        Instant end = Instant.now();
        System.out.println("返序列化消耗时间:" + Duration.between(serEnd, end).toMillis());
    }

    static class User implements Serializable{
        private String id;

        private String name;

        public User() {
        }

        public User(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}



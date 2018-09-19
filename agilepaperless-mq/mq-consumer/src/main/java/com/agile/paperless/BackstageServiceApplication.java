package com.agile.paperless;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@ServletComponentScan //设置启动时spring能够扫描到我们自己编写的servlet和filter
//@MapperScan("com.agile.sxij.dao") //用于扫描的mapper接口
@EnableFeignClients
public class BackstageServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackstageServiceApplication.class, args);
	}
}

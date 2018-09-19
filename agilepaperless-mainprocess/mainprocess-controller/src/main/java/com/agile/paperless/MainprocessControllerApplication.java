package com.agile.paperless;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableEurekaClient
@EnableSwagger2
@ServletComponentScan //设置启动时spring能够扫描到我们自己编写的servlet和filter
@MapperScan("com.agile.paperless.db.dao") //用于扫描的mapper接口
public class MainprocessControllerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MainprocessControllerApplication.class, args);
	}
}

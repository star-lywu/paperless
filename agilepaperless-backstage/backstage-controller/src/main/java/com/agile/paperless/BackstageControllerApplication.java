package com.agile.paperless;

import com.agile.paperless.api.annotation.ExcludFromComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@ServletComponentScan //设置启动时spring能够扫描到我们自己编写的servlet和filter
@ComponentScan(excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = ExcludFromComponentScan.class)})
public class BackstageControllerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackstageControllerApplication.class, args);
	}
}

package com.agile.paperless;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class AgilepaperlessZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgilepaperlessZuulApplication.class, args);
	}
}

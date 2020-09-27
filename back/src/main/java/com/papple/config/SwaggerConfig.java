package com.papple.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket postsApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("PineApple")
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.pappleblog.controllers"))
				.paths(PathSelectors.ant("/api/**"))
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("GunReview API")
				.description("GunReview API Reference for Developers")
				.termsOfServiceUrl("https://edu.ssafy.com")
				.license("MND License")
				.licenseUrl("ssafy@ssafy.com").version("1.0").build();
	}

}



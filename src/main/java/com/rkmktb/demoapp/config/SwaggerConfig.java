package com.rkmktb.demoapp.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicates;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(Predicates.not(PathSelectors.regex("(/actuator.*|/error)"))).paths(PathSelectors.ant("/api/**")).build()
				.pathMapping("/").apiInfo(metaData());
	}

	private ApiInfo metaData() {

		Contact contact = new Contact("MadhuKiran K Rajuladevi", "https://rkmktechnobuzz.in/", "mrajuladevi.java@gmail.com");

		return new ApiInfo("Demo Application", "Demo Application Admissions API : Version 1.0", "1.0", "Terms of Service: RKMKTB",
				contact, "Apache License Version 2.0 : Copyright 2022 RKMK Techno Buzz",
				"https://www.apache.org/licenses/LICENSE-2.0", new ArrayList<>());
	}


}

package com.auctionapp.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.*;

/**
 * @author Ensar Horozović
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

	private static final String FORWARD_TO_HOME = "forward:/";

	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(new DateConverter());
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {

		registry.addViewController("/{path:[^\\.]*}").setViewName(FORWARD_TO_HOME);
		registry.addViewController("/**/{path:[^\\.]*}").setViewName(FORWARD_TO_HOME);
		registry.addViewController("/{path:\\w+}/{path:?!(\\.js|\\.css)$}").setViewName(FORWARD_TO_HOME);

	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		// Resources controlled by Spring Security, which
		// adds "Cache-Control: must-revalidate".
		registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/static/assets/")
				.setCachePeriod(3600 * 24 * 7);

	}
}

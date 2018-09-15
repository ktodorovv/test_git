package com.coffee.shop.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.coffee.shop.utils.HotDrinkCalculator;
import com.coffee.shop.utils.ModelParserImpl;
import com.coffee.shop.utils.interfaces.ModelParser;

@Configuration
public class ApplicationBeanConfiguration {

	@Bean
	public ModelParser modelParser() {
		return new ModelParserImpl();
	}
	
	@Bean
	public HotDrinkCalculator hotDrinkCalculator() {
		return new HotDrinkCalculator();
	}
}
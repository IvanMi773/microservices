package com.example.currency_convertion_service;

import feign.codec.Decoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EnableFeignClients
public class CurrencyConvertionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyConvertionServiceApplication.class, args);
	}

	@Configuration
	public class FeignResponseDecoderConfig {
		@Bean
		public Decoder feignDecoder() {

			ObjectFactory<HttpMessageConverters> messageConverters = () -> {
				HttpMessageConverters converters = new HttpMessageConverters();
				return converters;
			};
			return new SpringDecoder(messageConverters);
		}
	}
}

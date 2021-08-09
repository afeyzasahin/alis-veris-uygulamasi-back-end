package com.Spring.ShopApp.JWTConfiguration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	private final long MAX_AGE_SECS = 3600;

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*").allowedMethods("HEAD", "OPTIONS", "GET", "POST")
				.maxAge(MAX_AGE_SECS);
	}
	/*
	 * @Bean public CorsConfigurationSource corsConfigurationSource() { final
	 * CorsConfiguration configuration = new CorsConfiguration();
	 * configuration.setAllowedOrigins(ImmutableList.of("*"));
	 * configuration.setAllowedMethods(ImmutableList.of("HEAD", "GET", "POST",
	 * "PUT", "DELETE", "PATCH")); 'Access-Control-Allow-Origin' başlığının değeri
	 * joker karakter '*' olmamalıdır. // setAllowCredentials(true), isteğin kimlik
	 * bilgisi modu 'include' olduğunda, yanıttaki
	 * configuration.setAllowCredentials(true); // fail with 403 Invalid CORS
	 * request configuration.setAllowedHeaders(ImmutableList.of("Authorization",
	 * "Cache-Control", "Content-Type")); final UrlBasedCorsConfigurationSource
	 * source = new UrlBasedCorsConfigurationSource();
	 * source.registerCorsConfiguration("/**", configuration); return source; }
	 */
}

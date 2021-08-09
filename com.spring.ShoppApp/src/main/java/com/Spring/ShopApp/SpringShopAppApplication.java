package com.Spring.ShopApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/*
 * https://spring.io/guides/tutorials/rest/
   @SpringBootApplication
   public class PayrollApplication {

    public static void main(String... args) {
    SpringApplication.run(PayrollApplication.class, args);
  }
}
 * */

@SpringBootApplication
public class SpringShopAppApplication extends SpringBootServletInitializer{
	
	
	/*
	 * "extends SpringBootServletInitializer", Spring Framework'ün Servlet 3.0
	 * desteğini kullanır ve uygulamayı sunucu uygulaması kapsayıcısı tarafından
	 * başlatıldığında yapılandırılmasını saglar.
	 */

	public static void main(String[] args) {
		SpringApplication.run(SpringShopAppApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringShopAppApplication.class);
	}

}

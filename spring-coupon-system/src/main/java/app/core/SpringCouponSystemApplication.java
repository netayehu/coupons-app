package app.core;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.CrossOrigin;

import app.core.exceptions.couponSystemException;
import app.core.filters.LoginFilter;
import app.core.jwt.util.JwtUtil;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
@EnableSwagger2
@SpringBootApplication
@EnableScheduling
@CrossOrigin
public class SpringCouponSystemApplication {

	public static void main(String[] args) throws couponSystemException {
		ConfigurableApplicationContext ctx= SpringApplication.run(SpringCouponSystemApplication.class, args);
		System.out.println("=== context is up");

		try {
			TimeUnit.HOURS.sleep(5);
		} catch (InterruptedException e) {
			e.getMessage();
		}
		
		ctx.close();
		System.out.println("== closed");
		
	}
	
	@Bean
	public FilterRegistrationBean<LoginFilter> loginFilter(JwtUtil jwtUtil) {
		FilterRegistrationBean<LoginFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new LoginFilter(jwtUtil));
		registrationBean.addUrlPatterns("/api/*");
		registrationBean.setOrder(1);
		return registrationBean;
	}

}

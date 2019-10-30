package TaskPlanner.Back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;

import TaskPlanner.Back.Config.JwtFilter;

@SpringBootApplication
public class BackApplication{

	public FilterRegistrationBean jwtFilter()
	{
		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter( new JwtFilter() );
		registrationBean.addUrlPatterns( "/api/*" );

		return registrationBean;
	}
	public static void main(String[] args) {
		SpringApplication.run(BackApplication.class, args);
	}
	
}

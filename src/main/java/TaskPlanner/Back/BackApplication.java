package TaskPlanner.Back;

import java.net.URL;

import com.mongodb.client.gridfs.model.GridFSFile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

import TaskPlanner.Back.Config.JwtFilter;

@SpringBootApplication
public class BackApplication {
	@Autowired
    GridFsTemplate gridFsTemplate;

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

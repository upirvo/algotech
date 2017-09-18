package pl.upir.algotechtest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import pl.upir.algotechtest.web.view.UsersBean;

/**
 * Created by Upir on 2017-09-17. Project: AlgotechTestApp
 */

@Configuration
public class ManageBeanConfig {

	@Bean
	@Scope("view")
	public UsersBean usersBean() {
		return new UsersBean();
	}


}

package pl.upir.algotechtest;

import java.util.ResourceBundle;

import javax.faces.webapp.FacesServlet;
import javax.servlet.MultipartConfigElement;

import org.primefaces.webapp.filter.FileUploadFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.autoconfigure.websocket.WebSocketAutoConfiguration;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//import AbstractEntity;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import com.upir.algotechtest.entity.AbstractEntity;

/**
 * Main application class
 * @athor Vitalii Upir
 */
@ComponentScan
@Configuration
@EntityScan( basePackageClasses = {AbstractEntity.class})
//@EnableJpaRepositories(repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class)
//@EnableAutoConfiguration
@SpringBootApplication(exclude={JacksonAutoConfiguration.class,WebSocketAutoConfiguration.class})
public class AlgotechTestApp extends SpringBootServletInitializer /*implements ServletContextAware*/  {
		
	
	public static void main(String[] args) {
		SpringApplication.run(new Class<?>[] { AlgotechTestApp.class}, args);
	}


	@Bean
	public FilterRegistrationBean fileUploadFilterRegistrationBean() {

		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.addUrlPatterns("*.xhtml");
		registrationBean.setFilter(new FileUploadFilter());
		return registrationBean;
	}

	/**
	 * Faces servlet init
	 * @param multipartConfigElement
	 * @return
	 */
	@Bean
	public ServletRegistrationBean servletRegistrationBean(MultipartConfigElement multipartConfigElement) {
		FacesServlet servlet = new FacesServlet();

		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(servlet, "*.xhtml");
		servletRegistrationBean.setMultipartConfig(multipartConfigElement);
		return servletRegistrationBean;
	}

	/**
	 * Own resolver
 	 * @return
	 */
	@Bean(name = "myresolver")
	public ViewResolver getViewResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		return resolver;
	}

	/**
	 * Resource bundle for translating
	 * @return
	 */
	@Bean
	public ResourceBundle resourceBundle() {
		return ResourceBundle.getBundle("app");
	}
}

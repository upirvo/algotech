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

	@Bean
	public ServletRegistrationBean servletRegistrationBean(MultipartConfigElement multipartConfigElement) {
		FacesServlet servlet = new FacesServlet();

		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(servlet, "*.xhtml");
		servletRegistrationBean.setMultipartConfig(multipartConfigElement);
		return servletRegistrationBean;
	}

	/*@Bean
	public EmbeddedServletContainerFactory servletContainer() {
		TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
		factory.setPort(8080);
		factory.setSessionTimeout(10, TimeUnit.MINUTES);
		return factory;
	}*/

	@Bean(name = "myresolver")
	public ViewResolver getViewResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		return resolver;
	}


	/*@Bean
	public ServletListenerRegistrationBean<ConfigureListener> jsfConfigureListener() {
		return new ServletListenerRegistrationBean<ConfigureListener>(
				new ConfigureListener());
	}*/

	/*@Override
	public void setServletContext(ServletContext servletContext) {
		servletContext.setInitParameter("com.sun.faces.forceLoadConfiguration", Boolean.TRUE.toString());
	}
*/
	/*@Override
	public void setServletContext(ServletContext servletContext) {
		ServletRegistration servletRegistration = servletContext.getServletRegistration("Faces Servlet");
		if (servletRegistration != null) {
			servletRegistration.addMapping("*.xhtml");
		}
	}*/

	@Bean
	public ResourceBundle resourceBundle() {
		return ResourceBundle.getBundle("app");
	}
}

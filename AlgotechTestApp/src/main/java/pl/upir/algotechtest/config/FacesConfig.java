package pl.upir.algotechtest.config;

import java.util.HashMap;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.primefaces.webapp.filter.FileUploadFilter;
import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.MimeMappings;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import pl.upir.algotechtest.config.scope.ViewScope;
/**
 * Created by Upir on 2017-09-17.
 * Project: AlgotechTestApp
 */

/**
 * Configuration web.xml
 */
@Configuration
public class FacesConfig implements ServletContextInitializer, EmbeddedServletContainerCustomizer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {

		System.err.println("------------------------------------");
		servletContext.setInitParameter("primefaces.CLIENT_SIDE_VALIDATION", "true");
		servletContext.setInitParameter("primefaces.THEME", "bootstrap");
		servletContext.setInitParameter("primefaces.FONT_AWESOME", "true");
		servletContext.setInitParameter("primefaces.UPLOADER", "native");
		servletContext.setInitParameter("javax.faces.FACELETS_LIBRARIES", "/WEB-INF/springsecurity.taglib.xml;");
		servletContext.setInitParameter("primefaces.UPLOADER", "native");
		servletContext.setInitParameter("javax.faces.PARTIAL_STATE_SAVING_METHOD", "true");
		servletContext.setInitParameter("javax.faces.PROJECT_STAGE", "Development");
		servletContext.setInitParameter("facelets.DEVELOPMENT", "true");
		servletContext.setInitParameter("javax.faces.FACELETS_REFRESH_PERIOD", "1");
		servletContext.setInitParameter("facelets.SKIP_COMMENTS", "true");

	}

	@Override
	public void customize(ConfigurableEmbeddedServletContainer configurableEmbeddedServletContainer) {
		MimeMappings mappings = new MimeMappings(MimeMappings.DEFAULT);
		mappings.add("woff", "application/font-woff");
		mappings.add("woff2", "application/font-woff2");
		mappings.add("eot", "application/vnd.ms-fontobject");
		mappings.add("ttf", "application/font-ttf");
		mappings.add("otf", "font/opentype");
		configurableEmbeddedServletContainer.setMimeMappings(mappings);
	}

	@Bean
	public Filter fileUploadFilter() {
		return new FileUploadFilter();
	}

	/**
	 * Initialization of viewScope bean
	 * @return new object viewScope
	 */
	@Bean
	public ViewScope viewScope() {
		return new ViewScope();
	}

	@Bean
	public CustomScopeConfigurer scopeConfigurer() {
		CustomScopeConfigurer configurer = new CustomScopeConfigurer();
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("view", viewScope());
		configurer.setScopes(hashMap);
		return configurer;
	}

}
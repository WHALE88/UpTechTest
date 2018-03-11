package team.uptech.max.oliinyk;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.cfg.AvailableSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import team.uptech.max.oliinyk.filter.HibernateStatisticsInterceptor;
import team.uptech.max.oliinyk.filter.RequestStatisticsInterceptor;

@Configuration
@EnableTransactionManagement
@PropertySource(value = { "classpath:application.properties" })
public class HibernateConfiguration {
	@Autowired
	private Environment env;

	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getRequiredProperty("datasource.driver"));
		dataSource.setUrl(env.getRequiredProperty("datasource.url"));
		dataSource.setUsername(env.getRequiredProperty("datasource.username"));
		dataSource.setPassword(env.getRequiredProperty("datasource.password"));
		return dataSource;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactory.setDataSource(getDataSource());
		entityManagerFactory
				.setPackagesToScan(new String[] { env.getRequiredProperty("entitymanager.packagesToScan") });
		entityManagerFactory.setJpaProperties(getHibernateProperties());
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		entityManagerFactory.setJpaVendorAdapter(vendorAdapter);
		return entityManagerFactory;
	}

	private Properties getHibernateProperties() {
		Properties properties = new Properties();
		properties.put(AvailableSettings.DIALECT, env.getRequiredProperty("hibernate.dialect"));
		properties.put(AvailableSettings.SHOW_SQL, env.getRequiredProperty("hibernate.show_sql"));
		properties.put(AvailableSettings.STATEMENT_BATCH_SIZE, env.getRequiredProperty("hibernate.batch.size"));
		properties.put(AvailableSettings.HBM2DDL_AUTO, env.getRequiredProperty("hibernate.hbm2ddl.auto"));
		properties.put(AvailableSettings.CURRENT_SESSION_CONTEXT_CLASS,
				env.getRequiredProperty("hibernate.current.session.context.class"));
		properties.put("hibernate.ejb.interceptor", hibernateInterceptor());
		return properties;
	}

	@Bean
	public JpaTransactionManager transactionManager() {
		return new JpaTransactionManager(entityManagerFactory().getObject());
	}

	/**
	 * PersistenceExceptionTranslationPostProcessor is a bean post processor which
	 * adds an advisor to any bean annotated with Repository so that any
	 * platform-specific exceptions are caught and then rethrown as one Spring's
	 * unchecked data access exceptions (i.e. a subclass of DataAccessException).
	 */
	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}
	
	@Bean
	public HibernateStatisticsInterceptor hibernateInterceptor() {
		return new HibernateStatisticsInterceptor();
	}

	@Configuration
	public static class WebApplicationConfig implements WebMvcConfigurer {

		@Autowired
		RequestStatisticsInterceptor requestStatisticsInterceptor;

		@Bean
		public RequestStatisticsInterceptor requestStatisticsInterceptor() {
			return new RequestStatisticsInterceptor();
		}

		@Override
		public void addInterceptors(InterceptorRegistry registry) {
			registry.addInterceptor(requestStatisticsInterceptor).addPathPatterns("/**");
		}
	}
}

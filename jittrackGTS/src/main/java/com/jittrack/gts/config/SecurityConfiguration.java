package com.jittrack.gts.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.expression.OAuth2MethodSecurityExpressionHandler;
import org.springframework.security.acls.AclPermissionEvaluator;
import org.springframework.security.acls.domain.EhCacheBasedAclCache;
import org.springframework.security.acls.jdbc.JdbcMutableAclService;
import org.springframework.security.acls.domain.ConsoleAuditLogger;
import org.springframework.security.acls.domain.DefaultPermissionGrantingStrategy;
import org.springframework.security.acls.domain.AclAuthorizationStrategyImpl;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.acls.jdbc.BasicLookupStrategy;
import javax.inject.Inject;
import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Inject
    private UserDetailsService userDetailsService;
    


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Inject
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
            .antMatchers("/bower_components/**")
            .antMatchers("/fonts/**")
            .antMatchers("/images/**")
            .antMatchers("/scripts/**")
            .antMatchers("/styles/**")
            .antMatchers("/views/**")
            .antMatchers("/i18n/**")
            .antMatchers("/swagger-ui/**")
            .antMatchers("/app/rest/register")
            .antMatchers("/app/rest/activate")
            .antMatchers("/websocket/activity");
    }

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	

	
	

    @EnableGlobalMethodSecurity(prePostEnabled = true, jsr250Enabled = true)
    private static class GlobalSecurityConfiguration extends GlobalMethodSecurityConfiguration {

        @Inject
        private DataSource dataSource;
    	
        @Bean
    	public ConsoleAuditLogger consoleAuditLoggerBean()  {
    		return new ConsoleAuditLogger();
    	}
        
        @Bean
    	public AclAuthorizationStrategyImpl aclAuthorizationStrategyImplBean()  {
    		return new AclAuthorizationStrategyImpl(new SimpleGrantedAuthority("ROLE_ADMIN") , 
    				new SimpleGrantedAuthority("ROLE_ADMIN") , new SimpleGrantedAuthority("ROLE_ADMIN"));
    	}
        
    	@Bean
    	public EhCacheBasedAclCache ehCacheBasedAclCacherBean()  {
    		
    		//EhCacheFactoryBean factoryBean= new EhCacheFactoryBean();
    		net.sf.ehcache.CacheManager cacheManager = net.sf.ehcache.CacheManager.create();

    		net.sf.ehcache.Cache cache = cacheManager.getCache("aclCache");
    		//factoryBean.setCacheName("");
    		//factoryBean.setca
    	//	factoryBean.setCacheManager(new EhCacheManagerFactoryBean());
    	//	AclAuthorizationStrategyImpl obAuth = new AclAuthorizationStrategyImpl(new SimpleGrantedAuthority("ROLE_ADMIN") , new SimpleGrantedAuthority("ROLE_ADMIN") , new SimpleGrantedAuthority("ROLE_ADMIN"));
    		EhCacheBasedAclCache aclCache= new EhCacheBasedAclCache(cache , 
    				new DefaultPermissionGrantingStrategy(consoleAuditLoggerBean()),
    				aclAuthorizationStrategyImplBean() );
    		
    		return aclCache;
    	}
    	
    	
    	@Bean
    	public BasicLookupStrategy basicLookupStrategyBean()  {
    		return new BasicLookupStrategy(dataSource, ehCacheBasedAclCacherBean(), 
    				aclAuthorizationStrategyImplBean(), 
    				new DefaultPermissionGrantingStrategy(consoleAuditLoggerBean()));
    	}
        
        @Override
		protected MethodSecurityExpressionHandler createExpressionHandler() {
			DefaultMethodSecurityExpressionHandler handler = new OAuth2MethodSecurityExpressionHandler();
			handler.setPermissionEvaluator(new AclPermissionEvaluator(
					new JdbcMutableAclService(dataSource, basicLookupStrategyBean() , ehCacheBasedAclCacherBean())));
			return handler;
		}
    }
}

package fr.diginamic.configuration;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import fr.diginamic.configuration.JWTConfigurer;
import fr.diginamic.configuration.TokenProvider;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

//	@Autowired
//	private AccessDeniedHandler accessDeniedHandler;

	@Autowired
	private AuthenticationManagerBuilder authenticationManagerBuilder;

	@Autowired
	private TokenProvider tokenProvider;

	@PostConstruct
	public void init() {
		try {
			authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		} catch (Exception e) {
			throw new BeanInitializationException("Security configuration failed", e);
		}
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**").antMatchers("/app/**/*.{js,html}").antMatchers("/i18n/**")
				.antMatchers("/content/**").antMatchers("/swagger-ui/index.html").antMatchers("/test/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable().exceptionHandling().authenticationEntryPoint(new Http403ForbiddenEntryPoint() {

		})
		.and().formLogin().loginProcessingUrl("/login")//.authenticationProvider(getProvider())
				.successHandler(new AuthentificationLoginSuccessHandler())
				.failureHandler(new SimpleUrlAuthenticationFailureHandler())
		.and().logout().logoutUrl("/logout")
				.logoutSuccessHandler(new AuthenticationLogoutSuccessHandler())
				.invalidateHttpSession(true)
			.and()
			.authorizeRequests()
				.antMatchers("/login").permitAll()
				.antMatchers("/logout").permitAll()
				.antMatchers("/user").authenticated().anyRequest().permitAll();
			.and()
			.apply(securityConfigurerAdapter));

	}

	private JWTConfigurer securityConfigurerAdapter() {
		return new JWTConfigurer(tokenProvider);
	}

	private class AuthentificationLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

		@Override
		public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
				Authentication authentication) throws IOException, ServletException {
			response.setStatus(HttpServletResponse.SC_OK);

		}

	}

	public class AuthenticationLogoutSuccessHandler implements LogoutSuccessHandler {

		@Override
		public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
				Authentication authentication) throws IOException, ServletException {
			response.setStatus(HttpServletResponse.SC_OK);

		}

	}

//	@Bean
//	public AuthenticationProvider getProvider() {
//
//		AuthProvider provider = new AuthProvider();
//		provider.setUserDDetailsService(userDetailsService);
//		return provider;
//	}

}

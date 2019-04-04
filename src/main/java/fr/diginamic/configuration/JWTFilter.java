package fr.diginamic.configuration;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;



public class JWTFilter extends GenericFilterBean {

	public static final String AUTHORIZATION_HEADER = "Authorization";

	private TokenProvider tokenProvider;

	public JWTFilter(TokenProvider tokenProvider) {
		this.tokenProvider = tokenProvider;
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
		String jwt = resolveToken(httpServletRequest);
		if (StringUtils.hasText(jwt) && this.tokenProvider.validateToken(jwt)) {
			Authentication authentication = this.tokenProvider.getAuthentication(jwt);
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		
		filterChain.doFilter(servletRequest, servletResponse);
		
	}

	private String resolveToken(HttpServletRequest request) {
		String porteurToken = request.getHeader(AUTHORIZATION_HEADER);
		if (StringUtils.isEmpty(porteurToken)) {
			porteurToken = request.getParameter(AUTHORIZATION_HEADER);
		}
		if (StringUtils.hasText(porteurToken) && porteurToken.startsWith("Bearer ")) {
			return porteurToken.substring(7, porteurToken.length());
		}		
		return null;
	}

}

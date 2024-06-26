package com.alti.baseTemplate.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

@Component
public class JwtAuthFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		String scopeHeader = request.getHeader("scope");
		if (StringUtils.hasText(scopeHeader) || request.getRequestURI().contains("h2")
				|| StringUtils.hasText("index.html")) {

			filterChain.doFilter(servletRequest, servletResponse);
		} else {
			HttpServletResponse resp = (HttpServletResponse) servletResponse;
			String error = "Scope in header is not present";
			resp.reset();
			resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			servletResponse.setContentLength(error.length());
			servletResponse.getWriter().write(error);
		}

	}
}

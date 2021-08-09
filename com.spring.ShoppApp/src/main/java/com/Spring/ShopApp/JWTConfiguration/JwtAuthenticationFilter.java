package com.Spring.ShopApp.JWTConfiguration;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtTokenProvider tokenProvider;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String JWTConfig;
			JWTConfig = getJwtFromRequest(request);

			if (tokenProvider.validateToken(JWTConfig)&&StringUtils.hasText(JWTConfig)) {
				Long userId;
				userId = tokenProvider.getUserIdFromJWT(JWTConfig);

				/*
				 * Ek olarak JWT talepleri içindeki kullanıcının kullanıcı adını ve rolleri
				 * kodlanabilir ve bu talepleri JWT'den ayrıştırarak UserDetails nesnesini
				 * oluşturulabilir.
				 */
				logger.info("Login user id: " + userId);
				UserDetails userDetails = customUserDetailsService.loadUserById(userId);
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authentication);

			}
		} catch (Exception e) {
			logger.error("Kullanıcı kimlik doğrulaması ayarlanamadı", e);
		}

		filterChain.doFilter(request, response);
	}

	private String getJwtFromRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		if (bearerToken.startsWith("Bearer ")  && StringUtils.hasText(bearerToken)) {
			return bearerToken.substring(7, bearerToken.length());
		}
		return null;
	}

}

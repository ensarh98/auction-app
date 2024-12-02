package com.auctionapp.web.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auctionapp.db.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * The same as built-in SimpleUrlAuthenticationSuccessHandler but without http
 * redirect at the end of process.
 *
 * @author Ensar Horozović
 */
@Component
@Transactional
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	private RequestCache requestCache = new HttpSessionRequestCache();

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private UserRepository userRepository;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {

		response.setContentType("application/json; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().println(objectMapper.writeValueAsString(buildResponse(authentication)));

		var savedRequest = requestCache.getRequest(request, response);

		if (savedRequest == null) {
			clearAuthenticationAttributes(request);
			return;
		}

		var targetUrlParam = getTargetUrlParameter();
		if (isAlwaysUseDefaultTargetUrl()
				|| (targetUrlParam != null && StringUtils.hasText(request.getParameter(targetUrlParam)))) {
			requestCache.removeRequest(request, response);
			clearAuthenticationAttributes(request);
			return;
		}

		clearAuthenticationAttributes(request);
	}

	public void setRequestCache(RequestCache requestCache) {
		this.requestCache = requestCache;
	}

	private LoginResponse buildResponse(Authentication authentication) {

		var user = userRepository.findOneByEmail(authentication.getName());
		var loginResponse = new LoginResponse();
		loginResponse.setId(user.getId());
		loginResponse.setEmail(user.getEmail());
		loginResponse.setFirstName(user.getFirstName());
		loginResponse.setLastName(user.getLastName());
		loginResponse.setCity(user.getCity());
		loginResponse.setAddress(user.getAddress());
		loginResponse.setPhone(user.getPhone());

		return loginResponse;
	}
}

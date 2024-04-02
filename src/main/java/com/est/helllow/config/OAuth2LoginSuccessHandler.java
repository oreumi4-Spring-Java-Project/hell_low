package com.est.helllow.config;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.est.helllow.domain.RegistrationSource;
import com.est.helllow.domain.User;
import com.est.helllow.domain.UserRole;
import com.est.helllow.service.UserInterfaceService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

//@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	// @Value("${frontend.url}")
	// private String frontendUrl;

	private final UserInterfaceService userService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
		Authentication authentication) throws ServletException, IOException {

		// OAuth2AccessToken

		OAuth2AuthenticationToken oAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
		System.out.println("token :" + oAuth2AuthenticationToken);

		if("google".equals(oAuth2AuthenticationToken.getAuthorizedClientRegistrationId())){
			DefaultOAuth2User principal = (DefaultOAuth2User) authentication.getPrincipal(); //Principal 에는 UserDetails 와 OAuth2User 타입만 저장

			Map<String, Object> attributes = principal.getAttributes();

			String email = attributes.getOrDefault("email", "").toString();
			String name = attributes.getOrDefault("name", "").toString();
			String picture = attributes.getOrDefault("picture", "").toString();

			System.out.println("email :" + email);
			System.out.println("name :"  + name);
			System.out.println("picture :"  + picture);

			userService.findByuserEmail(email)
				.ifPresentOrElse(user -> {
					DefaultOAuth2User newUser = new DefaultOAuth2User(List.of(new SimpleGrantedAuthority(user.getRole().name())),
						attributes, "name");
					Authentication securityAuth = new OAuth2AuthenticationToken(newUser, List.of(new SimpleGrantedAuthority(user.getRole().name())),
						oAuth2AuthenticationToken.getAuthorizedClientRegistrationId());
					SecurityContextHolder.getContext().setAuthentication(securityAuth);
				}, () -> {
					User userEntity = new User();
					userEntity.setRole(UserRole.ROLE_USER);
					userEntity.setUserEmail(email);
					userEntity.setUserName(name);
					userEntity.setSource(RegistrationSource.KaKao);
					userService.save(userEntity);
					DefaultOAuth2User newUser = new DefaultOAuth2User(List.of(new SimpleGrantedAuthority(userEntity.getRole().name())),
						attributes, "name");
					Authentication securityAuth = new OAuth2AuthenticationToken(newUser, List.of(new SimpleGrantedAuthority(userEntity.getRole().name())),
						oAuth2AuthenticationToken.getAuthorizedClientRegistrationId());
					SecurityContextHolder.getContext().setAuthentication(securityAuth);
				});

			//OAuth2LoginAuthenticationProvider 내부에서는 인가 코드를 전달 받아, Access Token 을 요청
		}
		this.setAlwaysUseDefaultTargetUrl(true);
		this.setDefaultTargetUrl("/index.html");
		super.onAuthenticationSuccess(request, response, authentication);
	}
}

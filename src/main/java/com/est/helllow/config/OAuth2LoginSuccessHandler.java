package com.est.helllow.config;

import java.io.IOException;
import java.util.Collections;
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
import com.est.helllow.domain.enum_class.UserRole;
import com.est.helllow.service.UserInterfaceService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	private final UserInterfaceService userService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
		Authentication authentication) throws ServletException, IOException {

		OAuth2AuthenticationToken oAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;

		if ("kakao".equals(oAuth2AuthenticationToken.getAuthorizedClientRegistrationId())) {
			DefaultOAuth2User principal = (DefaultOAuth2User) authentication.getPrincipal();
			Map<String, Object> attributes = principal.getAttributes();
			Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
			Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");

			String email = (String) kakaoAccount.get("email");
			String name = (String) profile.get("nickname");
			String picture = (String) profile.get("thumbnail_image_url");

			userService.findByUserEmail(email)
				.ifPresentOrElse(user -> registerUser(user, name, picture),
					() -> registerNewUser(email, name, picture));
		}
		this.setAlwaysUseDefaultTargetUrl(true);
		this.setDefaultTargetUrl("/index.html");
		super.onAuthenticationSuccess(request, response, authentication);
	}

	private void registerUser(User user, String name, String picture) {
	}

	private void registerNewUser(String email, String name, String picture) {
		User newUser = new User();
		newUser.setUserName(name);
		newUser.setUserEmail(email);
		newUser.setUserImg(picture);
		newUser.setUserGrade("3대 100");
		newUser.setRole(UserRole.ROLE_USER);
		newUser.setSource(RegistrationSource.KaKao);

		userService.save(newUser);

		DefaultOAuth2User oauthUser = new DefaultOAuth2User(
			Collections.singleton(new SimpleGrantedAuthority(newUser.getRole().name())),
			Collections.singletonMap("nickname", name), "nickname");
		Authentication newAuth = new OAuth2AuthenticationToken(oauthUser, oauthUser.getAuthorities(), "kakao");
		SecurityContextHolder.getContext().setAuthentication(newAuth);
	}

}

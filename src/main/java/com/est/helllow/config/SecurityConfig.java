package com.est.helllow.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	@Autowired
	private OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;

	// 로그인 안한 유저 접근 가능 URL
	private static final String[] anonymousUserUrl={"/login","/main-page"};

	// 로그인 유저 접근 가능 URL (로그인 안한 유저는 접근 불가)
	//todo
	// - 3대 300 관련 접근 권한 추가
	// - 3대 400 관련 접근 권한 추가
	// - 3대 500 관련 접근 권한 추가
	// - 관리자(admin) 접근 권한 추가
	// - 로그아웃 페이지 설정?

	private static final String[] authenticatedUserUrl={"/my-page","/api.hell-low.com/post-management/**"};

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return  http
				.csrf(AbstractHttpConfigurer::disable)
				.cors(cors -> cors.configurationSource(corsConfiguration()))
				.authorizeHttpRequests(auth -> {
					//접근 제어
					auth.anyRequest().permitAll();
//							.requestMatchers(anonymousUserUrl).anonymous()
//							.requestMatchers(authenticatedUserUrl).authenticated()
//							//3대 300 관련
//							.requestMatchers().hasAnyAuthority()
//							.requestMatchers().hasAnyAuthority()
//							.requestMatchers().hasAnyAuthority()
//							.requestMatchers().hasAnyAuthority()
//							//3대 400 관련
//							.requestMatchers().hasAnyAuthority()
//							.requestMatchers().hasAnyAuthority()
//							.requestMatchers().hasAnyAuthority()
//							.requestMatchers().hasAnyAuthority()
//							//3대 500 관련
//							.requestMatchers("/hand-over").hasAnyAuthority("SBD_400","SBD_500")
//							.requestMatchers().hasAnyAuthority()
//							.requestMatchers().hasAnyAuthority()
//							// 관리자 권한 (ADMIN)
//							.requestMatchers().hasAuthority("ROLE_ADMIN")
//							.requestMatchers().hasAnyAuthority()
//							.requestMatchers().hasAnyAuthority()
//							.requestMatchers().hasAnyAuthority()
// 							.anyRequest().authenticated();
				})
				//로그인 폼
				.formLogin(httpSecurityFormLoginConfigurer ->
						httpSecurityFormLoginConfigurer
								.loginPage("/login"))
				.oauth2Login(oath2->{
					// oath2.loginPage("/login").permitAll();
					oath2.successHandler(oAuth2LoginSuccessHandler);
				})
				.build();
	}

	@Bean
	CorsConfigurationSource corsConfiguration() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(List.of("http://localhost:8080", "http://localhost:63342"));
		configuration.addAllowedHeader("*");
		configuration.addAllowedMethod("*");
		configuration.setAllowCredentials(true);
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**",configuration);
		return urlBasedCorsConfigurationSource;
	}

}

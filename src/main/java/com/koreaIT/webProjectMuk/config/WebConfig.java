package com.koreaIT.webProjectMuk.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.koreaIT.webProjectMuk.interceptor.BeforeActionInterceptor;
import com.koreaIT.webProjectMuk.interceptor.NeedLoginInterceptor;
import com.koreaIT.webProjectMuk.interceptor.NeedLogoutInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	private BeforeActionInterceptor beforActionInterceptor;
	private NeedLoginInterceptor needLoginInterceptor;
	private NeedLogoutInterceptor needLogoutInterceptor;

	public WebConfig(BeforeActionInterceptor beforActionInterceptor, NeedLoginInterceptor needLoginInterceptor,
			NeedLogoutInterceptor needLogoutInterceptor) {
		this.beforActionInterceptor = beforActionInterceptor;
		this.needLoginInterceptor = needLoginInterceptor;
		this.needLogoutInterceptor = needLogoutInterceptor;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(beforActionInterceptor).addPathPatterns("/**").excludePathPatterns("/resource/**");

		registry.addInterceptor(needLoginInterceptor).addPathPatterns("/usr/article/write")
				.addPathPatterns("/usr/article/doWrite").addPathPatterns("/usr/article/modify")
				.addPathPatterns("/usr/article/doModify").addPathPatterns("/usr/article/doDelete")
				.addPathPatterns("/usr/member/doLogout");

		registry.addInterceptor(needLogoutInterceptor).addPathPatterns("/usr/member/join")
				.addPathPatterns("/usr/member/doJoin").addPathPatterns("/usr/member/login")
				.addPathPatterns("/usr/member/doLogin");
	}
}

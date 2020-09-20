package com.dev.kangkrkr;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * web.xml에 설정하던 ContextLoader를 아래와 같이 java code로 설정
 * @author 강승윤
 */
public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringDevApplication.class);
	}

}

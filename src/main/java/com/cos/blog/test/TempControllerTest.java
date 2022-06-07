package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//@Controller는 해당 경로 밑에 있는 파일을 리턴해준다.
@Controller
public class TempControllerTest {

	//http://localhost:8000/blog/temp/home
	@GetMapping("/temp/home")
	public String tempHome() {
		System.out.println("tempHome()");
		//파일 리턴 기본경로 : src/main.resource/static
		//리턴명 : /home.html 
		//풀 경로 : src/main.resource/static/home.html
		return "/home.html";
	}
	
	@GetMapping("/temp/jsp")
	public String tempJsp() {
		//pom.xml 에서 의존성을 주입했기 때문에!
		//prefix : /WEB-INF/views/
		//suffix : .jsp
		//풀네임 : /WEB-INF/views/test.jsp
		return "test";
	}
}

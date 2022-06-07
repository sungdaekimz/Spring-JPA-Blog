package com.cos.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

	@GetMapping({"","/"})
	public String index() {
		//yml 파일에 정의해놔서 알아서 jsp 파일을 찾아간다.
		return "index";
	}
}

package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//@Controller : 사용자가 요청->응답(Html을 응답해줌)
//@RestController : 사용자가 요청->응답(Data를 응답해줌)

@RestController
public class HttpControllerTest {

	private static final String TAG = "HttpControllerTest";
	
	//localhost:8000/blog/http/lombok
	@GetMapping("/http/lombok")
	public String lombokTest() {
		//builder 를 사용하면 순서를 지키지 않아도 되고, 오버로딩도 안해도 된다. 
		Member m =Member.builder().username("user").build();
		System.out.println(TAG+"getter : "+m.getId());
		m.setId(5000);
		System.out.println(TAG+"setter : "+m.getId());
		return "lombok test 완료";
		
	}
	//인터넷 브라우저 요청은 get방식만 가능하다.
	//http://localhost:8080/http/get(select)
	@GetMapping("/http/get")
	public String getTest() {
		return "get 요청 : ";
	}
	
	//http://localhost:8080/http/post(insert)
	@PostMapping("/http/post")
	public String postTest() {
		return "post 요청";
		}
	
	//http://localhost:8080/http/put(update)
	@PutMapping("/http/put")
	public String putTest() {
		return "put 요청";
	}
	
	//http://localhost:8080/http/delete(delete)
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete 요청";
	}
}

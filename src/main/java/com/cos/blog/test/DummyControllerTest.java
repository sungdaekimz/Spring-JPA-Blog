package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.persistence.Entity;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;


//데이터만 리턴해주는 컨트롤러
@RestController

//csrf 해제하는 어노테이션, 컨트롤러마다 달아주면 된다.
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)

public class DummyControllerTest {
	
	//더미가 메모리에 뜰 때, 유저레파지토리도 메모리에 뜬다.
	//의존성 주입(DI)
	@Autowired
	private UserRepository userRepository;

	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		try {
			userRepository.deleteById(id);
		} catch(EmptyResultDataAccessException e){
			return "삭제에 실패하였습니다. 해당 id는 DB에 없읍니다.";
		}
		
		return "삭제되었읍니다. id : " +id;
	}
	
	//save함수는 id를 전달하지 않으면 insert를 해주고
	//save함수는 id를 전달하면 해당 id의 데이터가 있다면 update,
	//save함수는 id를 전달하면 해당 id의 데이터가 없다면 insert를 한다.
	@Transactional //함수 종료 시, 자동 commit
	@PutMapping("/dummy/user/{id}")
																						//@RequestBody : json 데이터 받아오는 어노테이션
																						//json 데이터를 요청 -> MessageConverter가 Jackson 라이브러리를 호출해서 자바 오브젝트로 변환해서 받아줌.
	public User updateUser(@PathVariable int id, @RequestBody User requestUser ) {
		System.out.println("id : " + id);
		System.out.println("password : " + requestUser.getPassword());
		System.out.println("email : " + requestUser.getEmail());
		
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정에 실패하였읍니다.");
		});
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		
		//더티 체킹
		//트랜젝셔널 어노테이션을 추가하면 save 함수가 없어도 update가 된다.
		//userRepository.save(user);
		
		return user;
	}
	
	//유저 전체 받아오기
	@GetMapping("/dummy/users")
	public List<User> list(){
		return userRepository.findAll();
	}
	
	//페이징!!
	@GetMapping("/dummy/user")
	public List<User> pageList(@PageableDefault(size=2, sort="id", direction = Sort.Direction.DESC) Pageable pageable){
		Page<User> pagingUser = userRepository.findAll(pageable); //getContent()는 데이터만 출력해준다.
		
		List<User> users = pagingUser.getContent();
		return users;
	}
	
	
	//{id}주소로 파라미터를 전달받을 수 있음
	//http://
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		// user/4를 요청했는데, DB에 4가 없으면 내가 못찾아오고, NULL이 될 것이다.
		// 그럼 return null 이 리턴되는데 이것은 큰 문제이다.
		//Optional로 너의 User 객체를 감싸서 가져올테니, 알아서 판단해서 리턴해라.
		
		/* #1. 만약 없는 id를 요청했으면 이 메소드를 타서 빈 객체(null이 아닌 텅 빈 객체) 를 넘겨준다.
		User user = userRepository.findById(id).orElseGet(new Supplier<User>() {
			@Override
			public User get() {
				// TODO Auto-generated method stub
				return new User();
			}
		});
		*/
		
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				// TODO Auto-generated method stub
				return new IllegalArgumentException("해당 유저는 없습니다. id : "+id);
			}
		});
		
		//요청 -> 웹브라우저(웹브라우저는 html만 이해 가능, 자바 오브젝트 이해 못한다.)
		//user 객체 = 자바 오브젝트
		//웹브라우저가 이해할 수 있도록 자바 오브젝트를 변환해야한다.
		//JSON으로 변환하면 되겠지?
		//스프링부트 = MessageConverter가 응답시에 자동으로 작동한다.
		//만약에 자바 오브젝트를 리턴하게 되면, MessageConverter가 Jackson 라이브러리를 호출해서
		//user오브젝트를 json으로 변환해서 브라우저에게 던져준다.
		return user;
	}
	
	
	//http://localhost:8000/blog/dummy/join(요청)
	@PostMapping("/dummy/join")
	public String join(User user) { //key=value 형태 값을 받는다. (약속된 규칙)
		System.out.println("id : " + user.getId());
		System.out.println("username : " + user.getUsername());
		System.out.println("password : " + user.getPassword());
		System.out.println("email : " + user.getEmail());
		System.out.println("role : " + user.getRole());
		System.out.println("createDate"+user.getCreateDate());
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입 완료";
	}
}

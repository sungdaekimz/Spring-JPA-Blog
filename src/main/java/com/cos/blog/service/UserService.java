package com.cos.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

//스프링이 컴포넌트 스캔을 통해서 빈에 등록해줌. IoC
@Service
public class UserService {
	
	@Autowired
	private UserRepository userReposity;

	public Integer 회원가입(User user) {
		try {
			userReposity.save(user);
			return 1;
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("userservice:회원가입()"+e.getMessage());
		}
		return -1;
	}
}

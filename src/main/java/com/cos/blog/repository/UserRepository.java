package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.User;

//DAO
//자동으로 bean등록이 된다.
//@Repository 생략가능
public interface UserRepository extends JpaRepository<User, Integer>{
	//< > 의미 : 해당 레파지토리는 User테이블이 관리해. User테이블의 FK는 Integer야.

}

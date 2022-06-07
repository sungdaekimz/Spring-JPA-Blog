package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import org.hibernate.annotations.CreationTimestamp;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//ORM -> Java(다른언어) Object를 테이블로 매핑해주는 기술!!
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder //빌더 패턴
@Entity //User 클래스가 자동으로 MySQL에 테이블이 생성된다.
//@DynamicInsert insert시에 null인 필드를 제외시켜준다.
public class User {
 
	@Id //primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) //IDENTITY : 프로젝트에서 연결된 DB의 넘버링 전략을 따른다.
	private int id; 																					//ORACLE : 시퀀스, MySQL : auto_increment
	
	@Column(nullable = false, length = 30)
	private String username; //아이디
	
	@Column(nullable = false, length = 100) //123456 => 해쉬(비밀번호 암호화) 를 할것이므로 length를 100으로
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email;
	
	//DB는 RoleType이 없다.
	@Enumerated(EnumType.STRING)
	private RoleType role; //Enum을 쓰는게 좋다. Enum은 데이터에 도메인을 만들어줄 수 있다.
	
	@CreationTimestamp //시간이 자동으로 입력된다.
	private Timestamp createDate;
	
	
	
}

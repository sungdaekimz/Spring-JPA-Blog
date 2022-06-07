package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Lob //대용량 데이터
	private String content; // 섬머노트 라이브러리 사용할 것이다. <html> 코드가 섞여서 디자인된다.

	@ColumnDefault("0")
	private int count; //조회수

	//FetchType.EAGER : 데이터를 무조건 들고와라!
	@ManyToOne(fetch = FetchType.EAGER) //Many = Board, One = User -> 한명의 유저는 여러개의 게시글을 쓸 수 있다.
	@JoinColumn(name="userId")
	private User user; //DB는 Object를 저장할 수 없다. 그래서 FK를 사용한다.
										//자바는 Object를 저장할 수 있다.
										//DB에 만들어질 때, FK로 만들어진다.
	
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER) //하나의 게시글은 여러개의 댓글을 가질 수 있다.
																		//mappedBy가 적혀있으면 연관관계의 주인이 아니다! (나는 FK가 아니에요) DB에 칼럼 만들지마세요.
																		//난 그저 조인해서 값을 얻고 싶을 뿐
	private List<Reply> reply;
	
	@CreationTimestamp
	private Timestamp createDate;



}

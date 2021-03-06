

let index = {
    init: function(){
        $("#btn-save").on("click", ()=>{//fuction(){} 대신 ()=>{} 사용하는 이유는, this를 바인딩하기 위해서
            this.save();
        });
    },

    save: function(){
        let data = {
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()
        };
        //console.log(data);
        
        //ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청
        //ajax호출 시 default가 비동기 호출
        //ajax가 통신을 성공하고 json을 리턴해주면, 서버가 자동으로 자바 오브젝트로 변환해준다. wow!
        $.ajax({
			//회원가입 수행 요청
			type: "POST",
			url: "/blog/api/user",
			data: JSON.stringify(data), //http body 데이터
			contentType: "application/json; charset=utf-8", //bodydata가 어떤 타입인지(MIME)
			dataType: "json" // 서버로 요청을 해서 응답이 왔을 때, 기본적으로 모든 것이 문자열이다.
											//but 생긴게 json이라면, javascript object로 변경해줌
		}).done(function(resp){
			alert("회원가입이 완료되었습니다.");
			//console.log(resp);
			location.href="/blog";
		}).fail(function(){
			alert(JSON.stringify(error));
		});
    }
}
index.init();


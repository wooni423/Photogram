// (1) 회원정보 수정
function update(userId,event) {
	event.preventDefault();// form action 방지
	
	let data = $("#profileUpdate").serialize();
	
	console.log(data);
	
	$.ajax({
		type:"put",
		url:`/api/user/${userId}`,
		data:data,
		contentType:"application/x-www-form-urlencoded; charset=utf-8",
		dataType:"json"
	}).done(res  =>{ // HttpStatus 상태코드 200번대
		console.log("성공",res);
		//location.href=`/user/${userId}`;
	}).fail(error=>{ // HttpStatus 상태코드 200번대가 아닐때	
		alert(JSON.stringify(error.responseJSON.data));
	});
	
}

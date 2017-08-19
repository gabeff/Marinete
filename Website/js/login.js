function login() {
	var email = $("#email").val();
	var senha = $("#senha").val();

	$.ajax({
		beforeSend: function(request){
			request.setRequestHeader('Content-Type','application/json');
		},
		url : 'http://localhost:8080/Marinete/rest/usuario/logar',
		type : 'POST',
		async : true,
		cache : false,
		data : '{"email":"'+email+'","senha":"'+senha+'"}',
		success : function(data) {
			setCookie('token', data, 365);
			window.document.location.href = 'ListarMarinetes.html';
		},
		error : function(xhr, ajaxOptions, thrownError) {
			if (xhr.status == 401) {
				alert("Login e/ou senha inválidos");
			} else {
				alert("status "+xhr.status);
				alert("msg "+xhr.message);
			}
		}
	});	
}
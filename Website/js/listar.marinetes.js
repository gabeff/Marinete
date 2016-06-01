$(document).ready(function() {
	$.ajax({
		beforeSend: function(request){
			request.setRequestHeader('Authorization','Bearer hnvdje3opjqbpmktudgkikdi6d');
		},
	    datatype: 'json',
		url: '/Marinete/rest/marinete/listar', 
		success: function(data) {
			$.each(data, function(index) {
        	
	        	var dob = data[index].nascimento;
	        	dob = new Date(dob);
	        	var today = new Date();
	        	var age = Math.floor((today-dob) / (365.25 * 24 * 60 * 60 * 1000));
	        	
	    		$('.form-general').append(
					'<div class="panel panel-default"> '
	    				+'<div class="panel-body"> '
							+'<div class="row"> '
								+'<div class="media"> '
									+'<div class="media-left media-middle"> '
										+'<a href="#"> '
											+'<img class="img-circle" src="./imagens/marinetes/marinete-'+ data[index].id +'.png" onerror="this.src=`./imagens/icone.png`" '
											+'style="width: 80px; height: 80px; margin-left: 5%;"> '
										+'</a> '
									+'</div> '
									+'<div class="media-body"> '
										+'<h4 class="media-heading"> '+ data[index].nome +'</h4> '
										+ age +' anos </br> '
										+'<div class="rating-box"> '
											+'<div class="rating" style="width: '+ (data[index].avaliacao*100)/5 +'%;"> '
										+'</div> '
									+'</div> '
								+'</div> '
							+'</div> '
						+'</div> '
					+'</div> '
				);
			});
		}
	});
});
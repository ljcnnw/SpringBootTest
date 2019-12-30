
jQuery(document).ready(function() {
	
    /*
        Fullscreen background
    */
    $.backstretch("assets/img/backgrounds/1.jpg");
    
    /*
        Form validation
    */
    $('.login-form input[type="text"], .login-form input[type="password"], .login-form textarea').on('focus', function() {
    	$(this).removeClass('input-error');
    });
    
    $('.login-form').on('submit', function(e) {
    	
    	$(this).find('input[type="text"], input[type="password"], textarea').each(function(){
    		if( $(this).val() == "" ) {
    			e.preventDefault();
    			$(this).addClass('input-error');
    		}
    		else {
    			$(this).removeClass('input-error');
    		}
    	});
    	
    });
	$('#loginBtn').click(function () {

		$.ajax({
			url: "/pub/login",
			contentType: "application/json;charset=utf-8",
			data: JSON.stringify({
				userName: $("#formUsername").val(),
				password: $("#formPassword").val()
			}),
			type: "POST",
			success: function (result) {
				if(result.msg == 'success'){
					document.cookie = result.sessionId;
					sessionStorage.setItem("token",result.sessionId);
					sessionStorage.setItem("userName",$("#formUsername").val());
					window.console.log(result.user);
					sessionStorage.setItem("user",JSON.stringify(result.user));
					window.location.href = "/chat/index.html";
				}else {

				}
			}
		})
	});
    
});

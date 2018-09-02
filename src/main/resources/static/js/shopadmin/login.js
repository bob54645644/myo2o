$(function(){
	//登录的url
	var loginUrl = "/shopadmin/handlelogin";
	
	//登录按钮触发
	$('#login').click(function(){
		var username = $('#username').val();
		var password = $('#password').val();
		$.ajax({
			url:loginUrl,
			data:{
				username:username,
				password:password
			},
			type:'post',
			success:function(data){
				if(data.success){
					$.toast("登录成功！");
					window.open('/shopadmin/shoplist','_self');
				}else{
					$.toast(data.errMsg);
				}
			}
		});
	});
});
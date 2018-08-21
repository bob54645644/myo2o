//改变验证码
function changeVerigyCode(e){
	e.src='/kaptcha?num='+Math.floor(Math.random*100);
}
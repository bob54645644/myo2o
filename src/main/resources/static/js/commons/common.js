//改变验证码
function changeVerigyCode(e) {
	e.src = '/kaptcha?num=' + Math.floor(Math.random * 100);
}

// 获取url中的参数值
function getParamValue(name) {

	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null) {
		return decodeURIComponent(r[2]);
	}
	return '';
}
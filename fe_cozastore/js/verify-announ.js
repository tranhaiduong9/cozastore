$(document).ready(function(){
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    var token = urlParams.get('token')
    $.ajax({
        url: "http://localhost:8080/login/signup/verify",
        method: "delete",
        data: {
            token: token
        }
    }).done(function(result){
        if (result.data) {
            document.getElementById("idH1").innerHTML= "Bạn đã xác thực email thành công"
            document.getElementById("idH2").innerHTML= "Vui lòng quay lại trang chủ và đăng nhập"
            alert("Xác thực thành công, vui lòng quay lại trang chủ và đăng nhập")
        }  else {
            document.getElementById("idH1").innerHTML= "Tài khoản đã xác thực hoặc mã xác thực đã hết hạn"
            document.getElementById("idH2").innerHTML= "Hãy thử đăng nhập hoặc đăng ký lại"
            alert("Tài khoản đã xác thực hoặc mã xác thực đã hết hạn, hãy thử đăng nhập hoặc đăng ký lại")
        }
    })
})
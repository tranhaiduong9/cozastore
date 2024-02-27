$(document).ready(function(){
    

    $("#btn-sign-in").click(function(){

        var username = $("#user").val()
        var password = $("#pass").val()

        $.ajax({
            url: "http://localhost:8080/login/signin",
            method: "post",
            data: {
                email: username,
                password: password
            }
        }).done(function(result){
            if(result && result.statusCode ==200){
                localStorage.setItem("token", result.data);
                window.location=`index.html`;
            } else {
                alert("Sai email hoặc mật khẩu")
            }
        }).fail(function(failresutl){
            alert("Sai email hoặc mật khẩu")
        })
    })

    //Xử lý đăng ký
    $("#btn-sign-up").click(function(){

        var username = $("#user-sign-up").val()
        var password = $("#pass-sign-up").val()
        var repassword = $("#repass-sign-up").val()
        var email = $("#email-sign-up").val()
        
        if(!username || !password){
            alert("username và password không được trống")
        } else if (repassword != password) {
            alert("password nhập lại không giống với password gốc")
        } else {
            alert("email không được trống hoặc không đúng định dạng \"@gmail.com\"")
        }
        $.ajax({
            url: "http://localhost:8080/login/signup",
            method: "post",
            contentType: "application/json",
            data: JSON.stringify({
                userName: username,
                password: password,
                email: email
            })
        }).done(function(result){
            alert("Tạo tài khoản thành công")
        }).fail(function(failresutl){
            if(!username || !password){
                alert("username và password không được trống")
            } else if (repassword != password) {
                alert("password nhập lại không giống với password gốc")
            } else {
                alert("email không được trống hoặc không đúng định dạng \"@gmail.com\"")
            }
        })
    })

})
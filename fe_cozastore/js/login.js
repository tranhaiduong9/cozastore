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
            if(result && result.statusCode == 200){
                localStorage.setItem("token", result.data);
                window.location=`index.html`;
            } else {
                alert("Sai email hoặc mật khẩu");
                location.reload();
            }
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
        } else if (!validateEmail(email)) {
            alert("email không được trống hoặc không đúng định dạng")
        } else {
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
                if (result.data) {
                alert("Tạo tài khoản thành công, mã xác thực đã được gửi về email của bạn.")
                location.reload();
            } else {
                alert("Tạo tài khoản thất bại, email đã được sử dụng")
            }
            })
        }
        
    })
    //Check validate email
    const validateEmail = (email) => {
        return String(email)
          .toLowerCase()
          .match(
            /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|.(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
            );
      };
})
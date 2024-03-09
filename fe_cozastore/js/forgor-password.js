$(document).ready(function(){
    // create href link for "back to login" button
    var link = document.querySelector("a");
    link.setAttribute("href", "login.html");

    $("#btn-forgot").click(function(){
        var email = $("#email").val();
        $.ajax({
            url: "http://localhost:8080/login/forgot",
            method: "get",
            data: {
                email: email,
            }
        }).done(function(result){
            if(result.data){
                alert("Đã gửi yêu cầu về email của bạn.");
                location.reload();
            } else {
                alert("Không tìm thấy user");
            }
        })
    })
})
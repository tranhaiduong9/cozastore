$(document).ready(function(){
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    var email = urlParams.get('email');

    $("#btn-reset").click(function(){
        var pass = $("#pass").val();
        var rePass = $("#repass").val();
        if (pass != rePass){
            alert("password nhập lại không giống với password gốc");
        } else {
            $.ajax({
                url: "http://localhost:8080/login/reset",
                method: "put",
                data: {
                    email: email,
                    password: pass
                }
            }).done(function(result){
                if(result.data){
                    alert("Reset password thành công.");
                } else {
                    alert("Reset password thất bại.");
                }
            })
        }
    })
})
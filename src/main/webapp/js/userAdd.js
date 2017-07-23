/**
 * Created by zhongziming on 2017/5/7.
 */

$(document).ready(function () {
    $("#sign").click(function () {
        var msg = checkPassword()+checkEmail();
        if(msg == 2) $("#signForm").submit();
    });
    function checkPassword() {
        var password1 = $("#password1").val();
        var password = $("#password").val();
        console.log(password1);
        console.log(password);
        if(password1 == "" &&password == "") {toastr.error("密码不能为空");return 0;}
        else if (password1 == password) return 1;
        else {toastr.error("密码不正确！");return 0;}
    }
    function checkEmail() {
        var email = $("#email").val();
        var pattern=new RegExp("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");
        if(pattern.test(email)){
            return 1;
        }else{
            toastr.error("邮箱格式不正确！");
            return 0;
        }
    }
});

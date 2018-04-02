/**
 * Created by zhongziming on 2017/5/7.
 */

$(document).ready(function () {
    $("#sign").click(function () {
        if (!checkEmail()) {
            toastr.error("邮箱格式不正确！");
            return false ;
        } else if (!checkPassword()){
            toastr.error("请检查密码！");
            return false;
        } else{
            $("#signForm").submit();
        }
    });

    function checkPassword() {
        var password1 = $("#password1").val();
        var password = $("#password").val();
        if (password1 == "" && password == "") {
            return false;
        } else if (password1 != password) {
            return false;
        }
    }

    function checkEmail() {
        var email = $("#email").val();
        var pattern = new RegExp("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");
        return pattern.test(email);
    }
});

/**
 * Created by zhongziming on 2017/5/5.
 */
function getUserType(){
    var userType = $("input[name='login']:checked").val();
    console.log("userType is "+userType);
    return userType;
}

$(document).ready(
    function(){
        $("input[name='login']").change(function(){
            userType = getUserType();
            if(userType == "userLogin") {
                $('#loginForm').attr('action', 'login/userCheck');
            }else {
                $('#loginForm').attr('action', 'login/adminCheck');
            }
        });

    }
);



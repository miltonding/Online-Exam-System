//validate
function checkUserName() {
    $("#tip_userName").css("visibility","hidden");
    var length = $.trim($("#userName").val()).length;
    if (length == 0) {
        $("#tip_userName").css("visibility","visible");
         return false;
    } else {
         return true;
    }
}

function checkPassword() {
     $("#tip_password").css("visibility","hidden");
     var length = $.trim($("#password").val()).length;
     if (length == 0) {
         $("#tip_password").css("visibility","visible");
         return false;
     } else {
         return true;
     }
}

function login() {
    var userNameFlag = checkUserName();
    var passwordFlag = checkPassword();
    var isSubmitForm = userNameFlag && passwordFlag;
    if (isSubmitForm === true) {
        //Save userName and password
        if ($("#remeber_checkbox").attr("class") === "check_seleted") {
            var localStorage = window.localStorage;
            var userName = $.trim($("#userName").val());
            var password = $.trim($("#password").val());
            localStorage.setItem("exam_username", userName);
            localStorage.setItem("exam_password", password);
        }
        $("#loginForm").submit();
    } else {
        $(".warning").html("");
        $(".warning").css("visibility", "hidden");
    }
}
$(function() {
    //deal with outcomes
    var errMsg = $(".warning").html();
        if (errMsg.length != 0 ) {
            $(".warning").css("visibility", "visible");
         }

    //load username and password
    var localStorage = window.localStorage;
    var userName = localStorage.getItem("exam_username");
    var password = localStorage.getItem("exam_password");
    if (isNotEmpty(userName) && isNotEmpty(password)) {
         $("#userName").val(userName);
         $("#password").val(password);
         $("#remeber_checkbox").attr("class", "check_seleted");
            
    }

    //remember me
    $("#remeber_checkbox").click(function() {
           if ($(this).attr("class") === "check_unSeleted") {
                $(this).attr("class", "check_seleted");
            } else {
                $(this).attr("class", "check_unSeleted");
                var localStorage = window.localStorage;
                var userName = localStorage.getItem("exam_username");
                var password = localStorage.getItem("exam_password");
                if (isNotEmpty(userName) && isNotEmpty(password)) {
                    localStorage.removeItem("exam_username");
                    localStorage.removeItem("exam_password");
                 }
           }
    });

    $(".btn").on("click", login);
    $("#password").on("keydown", function(e) {
        if (e.keyCode === 13) {
            login();
        }
    });
    $("#userName").on("keydown", function(e) {
        if (e.keyCode === 13) {
            login();
        }
    });
});

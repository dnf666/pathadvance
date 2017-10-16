function login() {
    var adminName = $("#adminName").val();
    var password = $("#password").val();

    $.post("/pms/admin/login.do",{
        'adminName':adminName,
        'password':password
    },function(map){
        if (map.status == true){
            window.location.href = "/pms/admin/user/users.html";
        }else if (map.status == false){
            alert(map.message);
        }
    });
}
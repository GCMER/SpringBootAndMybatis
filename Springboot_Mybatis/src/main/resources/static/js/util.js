var address = window.location.origin;

function setCookie(name, value, days) {
    var d = new Date;
    d.setTime(d.getTime() + 24*60*60*1000*days);
    window.document.cookie = name + "=" + value + ";path=/;expires=" + d.toGMTString();
}

function getCookie(name) {
    var v = window.document.cookie.match('(^|;) ?' + name + '=([^;]*)(;|$)');
    return v ? v[2] : null;
}

function getUserName(userId) {
    var user = {};
    user.userId = userId;
    var response;
    $.ajax({
        async : false,
        type : 'POST',
        url : address+"getUser",
        data : user,
        dataType : 'json',
        success : function(result) {
            response = result;
        },
        error : function(result) {
            alert('服务器异常');
        }
    });
    if(response.status == "0"){
        var user = response.content;
        user = JSON.parse(user);
        return user.userName;
    }
    else{
        alert(response.content);
    }

    /*给空input设置背景色 用以提示*/
    function checknullcolor(element){
        var munoVal=element.value;
        /*将多行空格替换*/
        munoVal=munoVal.replace(/(^\s*)|(\s*$)/g, "");
        if(munoVal==""){
            $("#"+element.id).attr('class', "ui-state-error");
        }else{
            $("#"+element.id).removeClass('ui-state-error');
        }
    }
    /*给空input设置浮动文字 用以提示*/
    function checknulltext(element) {
        var munoVal = element.value;
        munoVal = munoVal.replace(/(^\s*)|(\s*$)/g, "");
        if (munoVal == "") {
            $("#" + element.id).stk_float({
                target: $("#MarktingInput")
            });
        } else {
            $("#" + element.id).stk_float({
                target: $("#MarktingInput2")
            });
        }
    }


}
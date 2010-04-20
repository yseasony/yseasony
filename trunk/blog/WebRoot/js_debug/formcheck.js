function isNull(id){
    var str = $("#" + id).get(0).value;
    str = $.trim(str);
    var i;
    if (str.length == 0) 
        return true;
    for (i = 0; i < str.length; i++) {
        if (str.charAt(i) == ' ') 
            return true;
    }
    return false;
}

function between(index, from, id, tipClass){
    var str = $("#" + id).get(0).value;
    str = $.trim(str);
    if (str.length < index || str.length > from) {
        tip(tipClass, "长度必须在" + index + "到" + from + "之间", successMessage, false);
    }
}

function onFocusCheck(tipClass, message){
    $("#" + tipClass).get(0).className = "onFocus";
    $("#" + tipClass).get(0).innerHTML = message;
}

function onBlurCheck(tipClass, successMessage){
    tip(tipClass, "　", successMessage, true);
    check = true;
}

function checkNull(tipClass, checkId, nullMessage){
    var str = $("#" + checkId).get(0).value;
    str = $.trim(str);
    var i;
    if (str.length == 0) {
        tip(tipClass, nullMessage, "　", false)
        return;
    }
    else {
        for (i = 0; i < str.length; i++) {
            if (str.charAt(i) == ' ') {
                tip(tipClass, nullMessage, "　", false)
                return;
            }
        }
    }
    tip(tipClass, nullMessage, "　", true);
    return;
}

function tip(tipClass, errorMessage, successMessage, value){
    if (value == false) {
        $("#" + tipClass).get(0).className = "onError";
        $("#" + tipClass).get(0).innerHTML = errorMessage;
        check = false;
    }
    else {
        $("#" + tipClass).get(0).className = "onCorrect";
        $("#" + tipClass).get(0).innerHTML = successMessage;
        check = true;
    }
}


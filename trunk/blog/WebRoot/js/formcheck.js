function isNull(id){
    var str = $("#" + id + "").get(0).value;
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

function between(index, from, id){
    var str = $("#" + id + "").get(0).value;
    str = $.trim(str);
    if (str.length < index || str.length > from) {
        return "长度必须在" + index + "到" + from + "之间";
    }
    else {
        return "";
    }
}

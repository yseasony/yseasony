function isNull(id) {
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

function between(index, from, id) {
	var str = $("#" + id).get(0).value;
	str = $.trim(str);
	if (str.length < index || str.length > from) {
		return "长度必须在" + index + "到" + from + "之间";
	} else {
		return "";
	}
}

function onFocusCheck(tipClass, message){
    $("#" + tipClass).get(0).className = "onFocus";
    $("#" + tipClass).get(0).innerHTML = message;
}


function onBlurCheck(tipClass, checkid, nullMessage,successMessage){
    if (isNull(checkid)) {
        $("#" + tipClass).get(0).className = "onError";
        $("#" + tipClass).get(0).innerHTML = nullMessage;
        check = false;
    }
    else {
        $("#" + tipClass).get(0).className = "onCorrect";
        $("#" + tipClass).get(0).innerHTML = successMessage;
        check = true;
    }
}

function exist(tipClass,checkId,nullMessage,errorMessage,successMessage,ajaxAddress,ajaxValueName){
	
	if (isNull(checkId)) {
        $("#" + tipClass).get(0).className = "onError";
        $("#" + tipClass).get(0).innerHTML = nullMessage;
        check = false;
    }
    
    String: checkValue = $("#" + checkId).get(0).value;
    $.ajax({
            url:ajaxAddress,
            dataType:"json",
            data:ajaxValueName+"="+checkValue,
            success:function(json){
        if (json.exist == "false") {
            $("#" + tipClass).get(0).className = "onError";
            $("#" + tipClass).get(0).innerHTML = errorMessage;
            check = false;
        }
        else {
            $("#" + tipClass).get(0).className = "onCorrect";
            $("#" + tipClass).get(0).innerHTML = successMessage;
            check = true;
        }
    }});
    
}

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

function between(index, from, id, tipClass) {
	var str = $("#" + id).get(0).value;
	str = $.trim(str);
	if (str.length < index || str.length > from) {
		$("#" + tipClass).get(0).className = "onError";
		$("#" + tipClass).get(0).innerHTML = "长度必须在" + index + "到" + from
				+ "之间";
		check = false;
	}
}

function onFocusCheck(tipClass, message) {
	$("#" + tipClass).get(0).className = "onFocus";
	$("#" + tipClass).get(0).innerHTML = message;
}

function onBlurCheck(tipClass,successMessage) {
	$("#" + tipClass).get(0).className = "onCorrect";
	$("#" + tipClass).get(0).innerHTML = successMessage;
	check = true;
}

function checkNull(tipClass, checkId, nullMessage) {
	var str = $("#" + checkId).get(0).value;
	str = $.trim(str);
	var i;
	if (str.length == 0) {
		$("#" + tipClass).get(0).className = "onError";
		$("#" + tipClass).get(0).innerHTML = nullMessage;
		check = false;
		return;
	} else {
		for (i = 0; i < str.length; i++) {
			if (str.charAt(i) == ' ') {
				$("#" + tipClass).get(0).className = "onError";
				$("#" + tipClass).get(0).innerHTML = nullMessage;
				check = false;
				return;
			}
		}
	}
	$("#" + tipClass).get(0).className = "onCorrect";
	$("#" + tipClass).get(0).innerHTML = "　";
	check = true;
}

function exist(tipClass, checkId, errorMessage, successMessage, ajaxAddress,
		ajaxValueName) {

	String: checkValue = $("#" + checkId).get(0).value;
	$.ajax( {
		url : ajaxAddress,
		dataType : "json",
		async: false,
		data : ajaxValueName + "=" + checkValue,
		success : function(json) {
			if (json.exist == "false") {
				$("#" + tipClass).get(0).className = "onError";
				$("#" + tipClass).get(0).innerHTML = errorMessage;
				check = false;
			} else {
				$("#" + tipClass).get(0).className = "onCorrect";
				$("#" + tipClass).get(0).innerHTML = successMessage;
				check = true;
			}
		}
	});

}

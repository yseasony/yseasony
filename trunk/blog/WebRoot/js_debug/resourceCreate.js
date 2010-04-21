Boolean: check = false;
$.ajaxSetup( {
	cache : false
});

$("#value").focus(function() {
	onFocusCheck("valueTip", "资源地址不能为空");
});
$("#value").blur(function() {
	exist();
});

$("#resourceName").focus(function() {
	onFocusCheck("resourceNameTip", "资源名称不能为空");
});
$("#resourceName").blur(function() {
	checkNull("resourceNameTip", "resourceName", "资源名称不能为空或含有空格");
});
$("#description").blur(function() {
	$("#descriptionTip").get(0).className = "onCorrect";
	$("#descriptionTip").get(0).innerHTML = "　";
});

$("#resourceType").focus(function() {
	onFocusCheck("resourceTypeTip", "资源类型不能为空");
});
$("#resourceType").blur(function() {
	checkNull("resourceTypeTip", "resourceType", "资源类型不能为空或含有空格");
	if (check == true) {
		onBlurCheck("resourceTypeTip", "　");
	}
});

$("#resourceType").blur(function() {
	checkNull("resourceTypeTip", "resourceType", "资源类型不能为空或含有空格");
	if (check == true) {
		onBlurCheck("resourceTypeTip", "　");
	}
});

function exist() {
	dwr.engine.setAsync(false);
	ResourceAjax.existResource($("#value").val(), callback);
	function callback(value) {
		tip("valueTip", "该资源地址已被创建", "该资源地址可以创建", value);
		return value;
	}
}

function checksave() {

	if (isNull("value")) {
		checkNull("valueTip", "value", "资源地址不能为空或含有空格");
	}

	if (check == true) {
		exist();
	}

	if (check == true) {
		checkNull("resourceNameTip", "resourceName", "资源名称不能为空或含有空格");
	}

	if (check == true) {
		checkNull("resourceTypeTip", "resourceType", "资源类型不能为空或含有空格");
	}

	if (check == true) {
		return true;
	} else {
		return false;
	}
}

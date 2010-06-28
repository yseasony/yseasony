Boolean: check = false;
$.ajaxSetup({
    cache: false
});

$("#displayName").focus(function(){
    onFocusCheck("displayNameTip", "权限名称不能为空");
});
$("#displayName").blur(function(){
    checkNull("displayNameTip", "displayName", "权限名称不能为空或含有空格");
    if (check == true) {
    	if (displayName != $("#displayName").val()) {
        existAuthority("displayName", "displayName", "displayNameTip", "该权限名称已被创建", "该权限名称可以创建");
    	}
    }
});

$("#name").focus(function(){
    onFocusCheck("nameTip", "权限代码不能为空");
});
$("#name").blur(function(){
    checkNull("nameTip", "name", "权限代码不能为空或含有空格");
    if (check == true) {
    	if (name != $("#name").val()) {
    		existAuthority("name", "name", "nameTip", "该权限代码已被创建", "该权限代码可以创建");
		}
    }
});

function existAuthority(column, value, valueTip, errorMessage, successMessage){
    dwr.engine.setAsync(false);
    AuthorityAjax.existAuthority(column, $("#" + value).val(), callback);
    function callback(value){
        tip(valueTip, errorMessage, successMessage, value);
        return value;
    }
}

function checksave(){

    check = true;
    
    if (isNull("displayName")) {
        checkNull("displayNameTip", "displayName", "权限名称不能为空或含有空格");
    }
    
    if (check == true) {
    	if (displayName != $("#displayName").val()) {
        existAuthority("displayName", "displayName", "displayNameTip", "该权限名称已被创建", "该权限名称可以创建");
    	}
    }
    if (isNull("name")) {
        checkNull("nameTip", "name", "权限代码不能为空或含有空格");
    }
    
    if (check == true) {
    	if (name != $("#name").val()){
        existAuthority("name", "name", "nameTip", "该权限代码已被创建", "该权限代码可以创建");
    	}
    }
    else {
        return false;
    }
}
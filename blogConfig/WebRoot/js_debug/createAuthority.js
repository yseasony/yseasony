$(document).ready(function(){
    $("#displayName").focus();
    $("#inputForm").validate({
        rules: {
            displayName: {
                required: true,
                existDisplayName: true,
                rangelength: [4, 20]
            },
            name: {
                required: true,
                existName: true,
                rangelength: [4, 20]
            }
        },
        messages: {
            displayName: {
                required: "权限名称不能为空",
                existDisplayName: "此权限名称已存在",
                rangelength: "长度必须在{0}-{1}之间"
            },
            name: {
                required: "权限代码不能为空",
                existName: "此权限代码已存在",
                rangelength: "长度必须在{0}-{1}之间"
            }
        },
        success: function(div){
            div.addClass("success").text("　")
        },
        errorElement: "div"
    });
});

$.ajaxSetup({
    cache: false
});

jQuery.validator.addMethod("existDisplayName", function existAuthority(value, element){
    dwr.engine.setAsync(false);
    if (typeof(displayName_value) == 'undefined') {
        return AuthorityAjax.existDisplayName(element.value);
    }
    else {
    	if(displayName_value == element.value){
			return true;
		} else{
        return AuthorityAjax.existDisplayName(element.value);
		}
    }
}, "Exist.");

jQuery.validator.addMethod("existName", function existAuthority(value, element){
    dwr.engine.setAsync(false);
	if (typeof(name_value) == 'undefined') {
        return AuthorityAjax.existName(element.value);
    }
    else {
    	if(name_value == element.value){
			return true;
		} else{
        return AuthorityAjax.existName(element.value);
		}
    }
}, "Exist.");


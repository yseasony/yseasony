$(document).ready(function(){
    $("#displayName").focus();
    $("#inputForm").validate({
        rules: {
            displayName: {
                required: true,
                existDisplayName: true
            },
            name: {
                required: true,
				existName: true
            }
        },
        messages: {
            displayName: {
                required: "权限名称不能为空",
                existDisplayName: "此权限名称已存在"
            },
            name: {
                required: "权限代码不能为空",
				existName:"此权限代码已存在"
            }
        },
		errorElement: "div"
    });
});

$.ajaxSetup({
    cache: false
});

jQuery.validator.addMethod("existDisplayName", function existAuthority(value, element){
    dwr.engine.setAsync(false);
    return AuthorityAjax.existDisplayName(element.value);
}, "Exist.");

jQuery.validator.addMethod("existName", function existAuthority(value, element){
    dwr.engine.setAsync(false);
    return AuthorityAjax.existName(element.value);
}, "Exist.");


Boolean: check = false;
$.ajaxSetup({
    cache: false
});


$("#name").focus(function(){
    onFocusCheck("nameTip", "角色名称不能为空");
});
$("#name").blur(function(){
    checkNull("nameTip", "name", "角色名称不能为空或含有空格");
    if (check == true) {
        existRole("name", "name", "nameTip", "该角色名称已被创建", "该角色名称可以创建");
    }
});

function existRole(column, value, valueTip, errorMessage, successMessage){
    dwr.engine.setAsync(false);
    AuthorityAjax.existAuthority(column, $("#" + value).val(), callback);
    function callback(value){
        tip(valueTip, errorMessage, successMessage, value);
        return value;
    }
}

function checksave(){ 

    check = true;
    
    if (isNull("name")) {
        checkNull("nameTip", "name", "角色名称不能为空或含有空格");
    }
    
    if (check == true) {
        existRole("name", "name", "nameTip", "该角色名称已被创建", "该角色名称可以创建");
    }
    
    else {
        return false;
    }
}




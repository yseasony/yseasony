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
        existAuthority("displayName", "displayName", "displayNameTip", "该权限名称已被创建", "该权限名称可以创建");
    }
});

$("#name").focus(function(){
    onFocusCheck("nameTip", "权限代码不能为空");
});
$("#name").blur(function(){
    checkNull("nameTip", "name", "权限代码不能为空或含有空格");
    if (check == true) {
        existAuthority("name", "name", "nameTip", "该权限代码已被创建", "该权限代码可以创建");
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
        existAuthority("displayName", "displayName", "displayNameTip", "该权限名称已被创建", "该权限名称可以创建");
    }
    
    if (isNull("name")) {
        checkNull("nameTip", "name", "权限代码不能为空或含有空格");
    }
    
    if (check == true) {
        existAuthority("name", "name", "nameTip", "该权限代码已被创建", "该权限代码可以创建");
    }
    
    if (check == true) {
        var arrayId = '';
        $("input[name='resourceId']:checked").each(function(){
            if (arrayId == '') {
                arrayId += $(this).val() + ',';
            }
            else {
                arrayId += $(this).val();
            }
        });
        $("#resourceIds").val(arrayId);
        document.form1.submit();
        return true;
    }
    else {
        return false;
    }
}

var loading = "<img id='jquery-loading' src='/images/70.gif' alt='Loading...' />";
$.ajaxSetup({
    cache: false
});

var totalPages = 0;
var filters = new Object();

$(document).ready(function(){
    page(1);
});

PageClick = function(pageclickednumber){
    page(pageclickednumber);
}

function page(pageNo){
    $("#loading").html(loading);
    ResourceAjax.getResourceList(pageNo, "", "", filters, callback);
    function callback(page){
        totalPages = parseInt(page.totalPages);
        $("#pager").pager({
            pagenumber: pageNo,
            pagecount: totalPages,
            buttonClickCallback: PageClick
        });
        
        var html = "";
        
        $.each(page.result, function(i, n){
            html = html + "<input type='checkbox' name='resourceId' id='resourceId' value =" + n.id + " >" + n.resourceName;
        });
        
        $("#result").html(html);
        $("#loading").html("");
    }
}


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
    
    if (check == true) {
        var arrayId = '';
        $("input[name='authorityId']:checked").each(function(){
            if (arrayId == '') {
                arrayId += $(this).val();
            }
            else {
                arrayId += ',' + $(this).val();
            }
        });
        $("#authorityIds").val(arrayId);
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
    AuthorityAjax.getAuthorityList(pageNo, "", "", filters, callback);
    function callback(page){
        totalPages = parseInt(page.totalPages);
        $("#pager").pager({
            pagenumber: pageNo,
            pagecount: totalPages,
            buttonClickCallback: PageClick
        });
        
        var html = "";
        
        $.each(page.result, function(i, n){
            html = html + "<input type='checkbox' name='authorityId' id='authorityId' value =" + n.id + " >" + n.displayName;
        });
        
        $("#result").html(html);
        $("#loading").html("");
    }
}


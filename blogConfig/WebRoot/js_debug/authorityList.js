var orderBy = "";
var order = "";
var filters = new Object();
var loading = "<img id='jquery-loading' src='../../images/70.gif' alt='Loading...' />";
$.ajaxSetup({
    cache: false
});

var totalPages = 0;

$(document).ready(function(){
    page(1);
});

PageClick = function(pageclickednumber){
    page(pageclickednumber);
}

function page(pageNo){
    $("#loading").html(loading);
    filters["LIKES_displayName"] = $("#filter_displayName").val();
    AuthorityAjax.getAuthorityList(pageNo, orderBy, order, filters, callback);
    function callback(page){
        totalPages = parseInt(page.totalPages);
        $("#pager").pager({
            pagenumber: pageNo,
            pagecount: totalPages,
            buttonClickCallback: PageClick
        });
        $(".ready").remove();
        $("#template").show();
        var count = 1;
        $.each(page.result, function(i, n){
        	var row = $("#template").clone();
        	var view = "<a href='editAuthority.do?authorityId="+n.id+"'>查看</a> ";
        	var del = "<a href='deleteAuthority.do?authorityId="+n.id+"'onclick='javascript:return del();'>删除</a>";
        	row.find("#cbox").html("<input type='checkbox' class='p_cbox'/>");
        	row.find("#aid").text(n.id);
        	row.find("#displayName").text(n.displayName);
        	row.find("#todo").html(view+del);
        	row.attr("class","ready");
        	row.appendTo("#b_table");
        	count++;
        });
        $("#template").hide();
        $("#loading").hide();
        $("#c_pager").css("height",24*count);
    }
}

function del() {
	var msg = "您真的确定要删除吗？";
	if (confirm(msg) == true) {
		return true;
	} else {
		return false;
	}
}
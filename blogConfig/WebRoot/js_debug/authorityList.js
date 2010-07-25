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
		
        var html = "";
        
        $.each(page.result, function(i, n){
            html = html + "<tr><td>" + n.id + "</td><td>" +
            n.displayName +
            "</td><td>" +
            "<a href='editAuthority.do?authorityId=" +
            n.id +
            "'>查看</a>　" +
            "<a href='deleteAuthority.do?authorityId=" +
            n.id +
            "' onclick='javascript:return del();' >删除</a>" +
            "</td></tr>";
        });
        
        $("#result").html(html);
        $("#loading").html("");
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
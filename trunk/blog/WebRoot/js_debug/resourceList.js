var orderBy = "";
var order = "";
var filters = new Object();
var loading = "<img id='jquery-loading' src='/images/70.gif' alt='Loading...' />";
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
    filters["LIKES_resourceName"] = $("#filter_resourceName").val();
    filters["LIKES_value"] = $("#filter_value").val();
    ResourceAjax.getResourceList("", pageNo, orderBy, order, filters, callback);
    function callback(page){
        totalPages = parseInt(page.totalPages);
        $("#pager").pager({
            pagenumber: pageNo,
            pagecount: totalPages,
            buttonClickCallback: PageClick
        });
		
        var html = "";
        
        $.each(page.result, function(i, n){
            html = html + "<tr><td>" + n.position + "</td><td>" +
            n.resourceName +
            "</td><td>" +
            n.resourceType +
            "</td><td>" +
            n.value +
            "</td><td>" +
            n.description +
            "</td><td>" +
            "<a href='/user/resourceEdit.do?resourceId=" +
            n.id +
            "'>查看</a>　" +
            "<a href='/user/resourceDel.do?resourceId=" +
            n.id +
            "&position=" +
            n.position +
            "'>删除</a>" +
            "</td></tr>";
        });
        
        $("#result").html(html);
        $("#loading").html("");
    }
}

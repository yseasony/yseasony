var common = {
	pageCallback : null,
	page : function(p, pageNo) {
		pageCallback = p;
		$.ajax({
			url : p.url,
			type : 'POST',
			data : {
				pageNo : pageNo
			},
			success : p.pageSuccess
		});
	},
	pagination : function(page) {
		$("#pagination").pager({
			pagenumber : page.pageNo,
			pagecount : page.totalPages,
			buttonClickCallback : pageCallback.pageClick
		});
		$(".ready").remove();
	}

};

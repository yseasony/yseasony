var common = {
	page : function(url, pageNo, f) {
		$.ajax({
			url : url,
			type : 'POST',
			data : {
				pageNo : pageNo
			},
			success : f
		});
	},
	pagination : function(page, pageClick) {
		$("#pagination").pager({
			pagenumber : page.pageNo,
			pagecount : page.totalPages,
			buttonClickCallback : pageClick
		});
		$(".ready").remove();
	}

};

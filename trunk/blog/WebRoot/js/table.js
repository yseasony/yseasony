function sort(orderBys, defaultOrder) {
	 
	if (orderBy == orderBys) {
		if (order == "") {
			order = defaultOrder;
		}
		else if (order == "desc") {
			order = "asc";
		}
		else if (order == "asc") {
			order = "desc";
		}
	}
	else {
		orderBy = orderBys;
		order = (defaultOrder);
	}
          page(orderNO);
}

function search() {
	$("#order").val("");
	$("#orderBy").val("");
	$("#pageNo").val("1");

	$("#mainForm").submit();
}
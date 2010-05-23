function sort(orderBys, defaultOrder) {
	if (orderBy == orderBys) {
		if (order == "") {
			order = defaultOrder;
		} else if (order == "desc") {
			order = "asc";
		} else if (order == "asc") {
			order = "desc";
		}
	} else {
		orderBy = orderBys;
		order = defaultOrder;
	}
	page(1);
}

function search() {
	page(1);
}
package org.yseasony.acg.services;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.yseasony.acg.dao.BaseDao;
import org.yseasony.acg.utils.Page;

@Component
public class BaseSvcImpl {

	<T> Page<T> getPage(BaseDao<T> basedao, Page<T> page) {
		return getPage(basedao, page, null);
	}

	<T> Page<T> getPage(BaseDao<T> basedao, Page<T> page, Map<String, ?> filters) {
		List<T> list = basedao.page(page);
		page.setResult(list);
		page.setTotalCount(basedao.count(filters));
		return page;
	}
}

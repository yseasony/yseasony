package org.yseasony.acg.utils;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.google.common.collect.Lists;

public class Pagination<T> {

	protected int pageNo = 0;
	protected int pageSize = 20;
	
	private List<T> list = Lists.newArrayList();;
	private long total = -1;
	private RowBounds rowBounds = RowBounds.DEFAULT;

	public Pagination() {
		rowBounds = new RowBounds(0, pageSize);
	}
	
	public Pagination(Integer pageSize) {
		if (pageSize != null) {
			
		}
		this.pageSize = pageSize;
	}

	public Pagination(RowBounds rowBounds) {
		this.rowBounds = rowBounds;
	}

	public RowBounds newRowBounds() {
		return rowBounds;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long totalCount) {
		this.total = totalCount;
	}

	public RowBounds getRowBounds() {
		return rowBounds;
	}

	public void setRowBounds(RowBounds rowBounds) {
		this.rowBounds = rowBounds;
	}
	
}

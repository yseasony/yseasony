package com.yy.test.utils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.yy.utils.Page;

public class PageTest extends Assert {
	private Page<Object> page;

	@Before
	public void setUp() {
		page = new Page<Object>();
	}

	/**
	 * 检测Page的默认值契约.
	 */
	@Test
	public void defaultParameter() {
		assertEquals(1, page.getPageNo());
		assertEquals(1, page.getPageSize());
		assertEquals(-1, page.getTotalCount());
		assertEquals(-1, page.getTotalPages());
		assertEquals(true, page.isAutoCount());

		page.setPageNo(-1);
		assertEquals(1, page.getPageNo());

		page.setPageSize(-1);
		assertEquals(1, page.getPageSize());

		assertNull(page.getOrder());
		assertNull(page.getOrderBy());

		assertEquals(false, page.isOrderBySetted());
		page.setOrderBy("Id");
		assertEquals(false, page.isOrderBySetted());
		page.setOrder("ASC,desc");
	}

	@Test(expected = IllegalArgumentException.class)
	public void checkInValidOrderParameter() {
		page.setOrder("asc,abcd");
	}

	@Test
	public void getFirst() {
		page.setPageSize(10);

		page.setPageNo(1);
		assertEquals(1, page.getFirst());
		page.setPageNo(2);
		assertEquals(11, page.getFirst());

	}

	@Test
	public void getTotalPages() {
		page.setPageSize(10);

		page.setTotalCount(1);
		assertEquals(1, page.getTotalPages());

		page.setTotalCount(10);
		assertEquals(1, page.getTotalPages());

		page.setTotalCount(11);
		assertEquals(2, page.getTotalPages());
	}

	@Test
	public void hasNextOrPre() {
		page.setPageSize(10);
		page.setPageNo(1);

		page.setTotalCount(9);
		assertEquals(false, page.isHasNext());

		page.setTotalCount(11);
		assertEquals(true, page.isHasNext());

		page.setPageNo(1);
		assertEquals(false, page.isHasPre());

		page.setPageNo(2);
		assertEquals(true, page.isHasPre());
	}

	@Test
	public void getNextOrPrePage() {
		page.setPageNo(1);
		assertEquals(1, page.getPrePage());

		page.setPageNo(2);
		assertEquals(1, page.getPrePage());

		page.setPageSize(10);
		page.setTotalCount(11);
		page.setPageNo(1);
		assertEquals(2, page.getNextPage());

		page.setPageNo(2);
		assertEquals(2, page.getNextPage());
	}
}

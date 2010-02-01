package com.yy.test.action;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.yy.action.ResourceAction;
import com.yy.test.base.SpringTxTestCase;

public class ResourceActionTest extends SpringTxTestCase {
	
	@Autowired
	private ResourceAction resourceAction;

	@Test
	@Rollback
	public void delResource(){
		resourceAction.delResource(request, 1L, 1.0);
	}
}

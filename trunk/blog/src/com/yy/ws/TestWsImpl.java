package com.yy.ws;


import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/restful")
public class TestWsImpl{

	@GET
    @Path(value="/helloWorld/{id}/")
    @Produces(MediaType.APPLICATION_JSON)
	public List<WsTest> helloWorld(@PathParam(value = "id")
            String id) {
		List<WsTest> list = new ArrayList<WsTest>();
		WsTest test = new WsTest();
		test.setB("c");
		list.add(test);
		return list;
	}

}

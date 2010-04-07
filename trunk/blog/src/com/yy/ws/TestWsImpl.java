package com.yy.ws;


import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
		System.out.println(id);
		List<WsTest> list = new ArrayList<WsTest>();
		WsTest test = new WsTest();
		test.setB("c");
		list.add(test);
		return list;
	}
	
	 	@POST
	    @Produces(MediaType.TEXT_PLAIN)
	    @Path("/updatecategory")
	    @Consumes(MediaType.TEXT_PLAIN)
	    public String updateCategory(@FormParam("a")  String a) {
	        System.out.println("接受的数据为:"+a);
	        return "ok";
	    }

}

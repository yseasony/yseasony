package com.yy.test.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.yy.model.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonTest extends SpringTxTestCase{
	
	
	@Test
	public void testArray(){
		
		JSONArray jsonArray = JSONArray.fromObject( "['json','is','easy']" );  
		System.out.println( jsonArray );  
		
		
	}
	
	@Test
	public void testMap(){
		
		Map<Object,Object> map = new HashMap<Object,Object>();  
		map.put( "name", "json" );  
		map.put( "bool", Boolean.TRUE );  
		map.put( "int", Integer.valueOf("1"));  
		map.put( "arr", new String[]{"a","b"} );  
		map.put( "func", "function(i){ return this.arr[i]; }" );  
		  
		JSONObject jsonObject = JSONObject.fromObject( map );  
		System.out.println( jsonObject );  
		
	}

	
	@Test
	public void testObject(){
		Resource resource = new Resource();
		resource.setId(1L);
		resource.setResourceName("test");
		
		JSONObject jsonObject = JSONObject.fromObject(resource);  
		System.out.println( jsonObject );  
		
	}
	
	@Test
	public void testList(){
		Resource resource = new Resource();
		resource.setId(1L);
		resource.setResourceName("test");
		
		Resource resource2 = new Resource();
		resource.setId(2L);
		resource.setResourceName("test");
		
		List<Resource> list = new ArrayList<Resource>();
		
		list.add(resource);
		list.add(resource2);
		
		
		JSONArray jsonArray = JSONArray.fromObject(list);  
		System.out.println( jsonArray.toString() );  
		
		
		
	}
}

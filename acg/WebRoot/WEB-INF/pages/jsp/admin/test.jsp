<?xml version="1.0" encoding="UTF-8" ?>
<%@ include file="../common/taglibs.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Spring MVC</title>
<%@ include file="../common/extjs.jsp"%>
    <script language="javascript">
	
	Ext.onReady(function(){
		var myCheckboxGroup = new Ext.form.CheckboxGroup({ 
		    id:'myGroup', 
		    xtype: 'checkboxgroup', 
		    renderTo :'form-cb',
		    fieldLabel: 'Single Column', 
		    itemCls: 'x-check-group-alt', 
		    columns: 3, 
		    items: [ 
		        {boxLabel: '唱歌', name: '1'}, 
		        {boxLabel: '游泳', name: '2', checked: true}, 
		        {boxLabel: '看书', name: '3'},
		        {boxLabel: '旅游', name: '4'},
		        {boxLabel: '游戏', name: '5'},
		        {boxLabel: '睡觉', name: '6'} 
		    ] 
		}); 
		
		//CheckboxGroup取值方法
		for (var i = 0; i < myCheckboxGroup.items.length; i++)
		{
			if (myCheckboxGroup.items.itemAt(i).checked)
			{
				alert(myCheckboxGroup.items.itemAt(i).name);				
			}
		}
	});
    </script>
</head>
<body>
<div id="form-cb"></div>
</body>
</html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/admin/login.css" />
<%@ include file="../common/extjs.jsp"%>
<title>test</title>
</head>
<body>
<div id="b"></div>
<div id="c"></div>
<script type="text/javascript">
	//构造器函数

	Ext.ux.MyPanel = Ext.extend(Ext.Panel, {
		width : 300,
		height : 300,
		constructor : function(config) {
	          Ext.apply(this, config);
	          Ext.ux.MyPanel.superclass.constructor.call(this, arguments);
	        }
	});

	var myfirstpanel = new Ext.ux.MyPanel({
		title : 'My First Panel'
	});

	var mysecondpanel = new Ext.ux.MyPanel({
		title : 'My Second Panel'
	});

	myfirstpanel.render('b');
	mysecondpanel.render('c');
</script>

</body>
</html>
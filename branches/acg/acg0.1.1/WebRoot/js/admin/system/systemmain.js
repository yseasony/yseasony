Ext.ns('Acg.System');
System = Acg.System;

System.systemPanel = new Ext.Panel({
	title : Lang.menu.system_config,
	autoScroll : true,
	border : false,
	iconCls : 'system',
	items : []
});

System.init = function() {
	Index.menu.add(System.systemPanel);
	Index.menu.doLayout();
};

Ext.onReady(System.init);
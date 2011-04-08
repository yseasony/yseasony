Ext.ns('Acg.Index');
Index = Acg.Index;

Index.center = function() {
	var center = new Ext.TabPanel({
		id : 'center',
		activeTab : 0,
		region : 'center',
		margins : '0 0 0 0',
		resizeTabs : true,
		enableTabScroll : true,
		items : [ {
			closable : true,
			layout : 'fit',
			title : Lang.msg.welcome,
			hideMode : 'offsets'
		} ]
	});
	Index.centerPanel = center;
	return center;
};

Index.west = function() {
	var menu = new Ext.Panel({
		region : 'west',
		id : 'menu',
		title : '功能菜单',
		width : 200,
		minSize : 200,
		maxSize : 200,
		margins : '0 0 0 5',
		split : true,
		collapsible : true,
		layoutConfig : {
			animate : true
		}

	});
	Index.menu = menu;
	return menu;
};

Index.east = function() {
	var right = new Ext.Panel({
		region : 'east',
		collapsible : true,
		split : true,
		width : 180,
		minSize : 180,
		maxSize : 180,
		margins : '0 5 0 0'
	});
	return right;
};

Index.north = function() {
	return new Ext.Panel({
		region : 'north',
		border : false,
		autoWidth : false,
		autoHeight : true,
		margins : '5 5 5 5',
		buttonAlign : 'right',
		items : [ new Ext.Toolbar({
			items : [ '-', {
				text : Lang.msg.welcome + ':'
			}, {
				text : userInfo.username,
				iconCls : 'user'
			}, '->',{
				id : 'clock',
				iconCls : 'logout'
			}, {
				text : Lang.msg.logout,
				iconCls : 'logout'
			}, '-' ]
		}) ]
	});
};

Index.south = function() {
	return new Ext.BoxComponent({
		region : 'south',
		height : 5
	});
};

Index.createViewport = function() {
	var viewport = new Ext.Viewport({
		layout : 'border',
		items : [ Index.north(), Index.east(), Index.west(), Index.center(),
				Index.south() ]
	});
};

Index.init = function() {
	new Ext.ux.Clock().display('clock');
	Ext.QuickTips.init();
	Ext.form.Field.prototype.msgTarget = 'side';
	Ext.Msg.minWidth = 300;
	Ext.state.Manager.setProvider(new Ext.state.CookieProvider());
	Index.createViewport();
};

Ext.onReady(Index.init);
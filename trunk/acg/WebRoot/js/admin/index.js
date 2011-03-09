Ext.ns('Acg.Index');
Index = Acg.Index;

Index.center = function() {
	var center = new Ext.TabPanel({
				id : 'center',
				activeTab : 0,
				region : 'center',
				margins : '35 0 5 0',
				resizeTabs : true,
				tabWidth : 150,
				minTabWidth : 120,
				enableTabScroll : true,
				items : [{
							closable : true,
							layout : 'fit',
							title : '欢迎您!',
							hideMode : 'offsets'
						}],
				listeners : {
					remove : function(tp, c) {
						c.hide();
					}
				}
			});
	Index.centerPanel = center;
	return center;
};

Index.west = function() {
	var menu = new Ext.Panel({
				region : 'west',
				id : 'menu',
				title : '功能菜单',
				split : true,
				width : 200,
				minSize : 175,
				maxSize : 400,
				collapsible : true,
				margins : '35 0 5 5',
				cmargins : '35 5 5 5',
				layout : 'accordion',
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
				width : 225,
				maxSize : 400,
				minSize : 175,
				margins : '35 5 5 0'
			});
	return right;
};

Index.createViewport = function() {
	var viewport = new Ext.Viewport({
				layout : 'border',
				items : [Index.east(), Index.west(), Index.center()]
			});
};

Index.init = function() {
	Ext.QuickTips.init();
	Ext.form.Field.prototype.msgTarget = 'side';
	Ext.Msg.minWidth = 300;
	Ext.state.Manager.setProvider(new Ext.state.CookieProvider());
	Index.createViewport();
};

Ext.onReady(Index.init);
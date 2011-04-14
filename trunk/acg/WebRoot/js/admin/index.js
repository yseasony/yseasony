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
				items : [{
							closable : true,
							layout : 'fit',
							title : Lang.msg.welcome,
							hideMode : 'offsets'
						}]
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
	var aWeek = Lang.common.week;
	var clock = new Ext.Toolbar.Item({
				iconCls : 'logout',
				text : new Date().format('Y-m-d G:i:s A ')
						+ aWeek[new Date().getDay()]
			});
	// 定期更新时间
	Ext.TaskMgr.start({
				run : function() {
					Ext.fly(clock.getEl()).update(new Date()
							.format('Y-m-d G:i:s A ')
							+ aWeek[new Date().getDay()]);
				},
				interval : 1000
			});
	return new Ext.Panel({
		region : 'north',
		border : false,
		autoWidth : false,
		autoHeight : true,
		margins : '5 5 5 5',
		buttonAlign : 'right',
		items : [new Ext.Toolbar({
					items : ['-', {
								text : Lang.msg.welcome + ':'
							}, {
								text : userInfo.username,
								iconCls : 'user'
							}, '->', clock, '-', {
								text : Lang.msg.logout,
								iconCls : 'logout',
								handler : function() {
									Ext.Ajax.request({
												url : './logout',
												async : false,
												success : function(loginFrom,
														action) {
													window.location = './login';
												}
											});
								}
							}, '-']
				})]
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
				items : [Index.north(), Index.east(), Index.west(),
						Index.center(), Index.south()]
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
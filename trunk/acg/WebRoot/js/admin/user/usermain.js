Ext.ns('Acg.User');
User = Acg.User;

User.userTree = new Ext.tree.TreePanel({
	border : false,
	id : 'tree',
	rootVisible : false,
	loader : new Ext.tree.TreeLoader(),
	root : new Ext.tree.AsyncTreeNode({
		children : [{
					text : Lang.user.user_manager,
					leaf : true,
					listeners : {
						click : function(control, e) {
							var tid = control.id;
							var p = new User.userGridPanel();
							Index.centerPanel.add({
										closable : true,
										id : tid,
										title : Lang.user.user_manager,
										layout : 'fit',
										items : p
									});
							p.store.load({
										params : {
											start : 0,
											limit : Common.pageSize
										}
									});
							Index.centerPanel.setActiveTab(tid);
						}
					}
				}, {
					text : Lang.auth.auth_manager,
					leaf : false,
					children : [{
								text : Lang.role.role,
								leaf : true,
								listeners : {
									click : function(control, e) {
										var tid = control.id;
										var p = new User.RoleGridPanel();
										Index.centerPanel.add({
													closable : true,
													id : tid,
													title : Lang.role.role_manager,
													layout : 'fit',
													items : p
												});
										p.store.load({
													params : {
														start : 0,
														limit : Common.pageSize
													}
												});
										Index.centerPanel.setActiveTab(tid);
									}
								}
							}, {
								text : Lang.auth.auth,
								leaf : true,
								listeners : {
									click : function(control, e) {
										User.buildTabPanel('p_auth', control.id);
									}
								}
							}]
				}]
	})
});

User.buildTabPanel = function(id, tid) {
	var tabPanel = Ext.getCmp(id);
	console.log(tabPanel);
	if (tabPanel == null) {
		switch (id) {
			case 'p_auth' :
				tabPanel = new User.AuthGridPanel();
				Index.centerPanel.add({
							closable : true,
							id : tid,
							title : Lang.auth.auth_manager,
							layout : 'fit',
							items : tabPanel
						});
				break
			case 'p_role' :
				tabPanel = new User.AuthGridPanel();
				Index.centerPanel.add({
							closable : true,
							id : tid,
							title : Lang.auth.auth_manager,
							layout : 'fit',
							items : tabPanel
						});
				break
			case 'p_user' :
				tabPanel = new User.AuthGridPanel();
				Index.centerPanel.add({
							closable : true,
							id : tid,
							title : Lang.auth.auth_manager,
							layout : 'fit',
							items : tabPanel
						});
				break
		}
	}
	tabPanel.store.load({
				params : {
					start : 0,
					limit : Common.pageSize
				}
			});
	Index.centerPanel.setActiveTab(tid);
};
	
User.userPanel = new Ext.Panel({
			title : '后台用户管理',
			autoScroll : true,
			border : false,
			iconCls : 'user',
			items : [User.userTree]
		});

User.init = function() {
	Index.menu.add(User.userPanel);
	Index.menu.doLayout();
};

Ext.onReady(User.init);
Ext.ns('Acg.User');
User = Acg.User;

User.userTree = new Ext.tree.TreePanel({
	border : false,
	id : 'tree',
	rootVisible : false,
	loader : new Ext.tree.TreeLoader(),
	root : new Ext.tree.AsyncTreeNode({
		children : [ {
			text : Lang.user.user_manager,
			leaf : true,
			disabled : userInfo.authButtons.USER,
			listeners : {
				click : function(control, e) {
					User.buildTabPanel('p_user', control.id);
				}
			}
		}, {
			text : Lang.auth.auth_manager,
			leaf : false,
			disabled : userInfo.authButtons.AUTH,
			children : [ {
				text : Lang.role.role,
				disabled : isGranted(userInfo.authButtons.ROLEPAGE),
				leaf : true,
				listeners : {
					click : function(control, e) {
						User.buildTabPanel('p_role', control.id);
					}
				}
			}, {
				text : Lang.auth.auth,
				leaf : true,
				disabled : isGranted(userInfo.authButtons.AUTHPAGE),
				listeners : {
					click : function(control, e) {
						User.buildTabPanel('p_auth', control.id);
					}
				}
			} ]
		} ]
	})
});

User.buildTabPanel = function(id, tid) {
	var tabPanel = Ext.getCmp(id);
	if (tabPanel == null) {
		switch (id) {
		case 'p_auth':
			tabPanel = new User.AuthGridPanel();
			Index.centerPanel.add({
				closable : true,
				id : tid,
				title : Lang.auth.auth_manager,
				layout : 'fit',
				items : tabPanel
			});
			break;
		case 'p_role':
			tabPanel = new User.RoleGridPanel();
			Index.centerPanel.add({
				closable : true,
				id : tid,
				title : Lang.role.role_manager,
				layout : 'fit',
				items : tabPanel
			});
			break;
		case 'p_user':
			tabPanel = new User.UserGridPanel();
			Index.centerPanel.add({
				closable : true,
				id : tid,
				title : Lang.user.user_manager,
				layout : 'fit',
				items : tabPanel
			});
			break;
		}
	}
	tabPanel.getStore().baseParams = tabPanel.params;
	tabPanel.load();
	Index.centerPanel.setActiveTab(tid);
};

User.userPanel = new Ext.Panel({
	title : Lang.menu.back_user_manager,
	autoScroll : true,
	collapsed : true,
	border : false,
	iconCls : 'user',
	items : [ User.userTree ]
});

User.init = function() {
	Index.menu.add(User.userPanel);
	Index.menu.doLayout();
};

Ext.onReady(User.init);
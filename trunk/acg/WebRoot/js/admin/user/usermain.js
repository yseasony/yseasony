Ext.ns('Acg.User');
User = Acg.User;

User.userTree = new Ext.tree.TreePanel({
	border : false,
	id : 'tree',
	rootVisible : false,
	loader : new Ext.tree.TreeLoader(),
	root : new Ext.tree.AsyncTreeNode({
		children : [{
					text : '用户管理',
					leaf : true,
					listeners : {
						click : function(control, e) {
							tid = control.id;
							var p = new User.userGridPanel()
							Index.centerPanel.add({
										closable : true,
										id : tid,
										title : '用户管理',
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
					text : '权限管理',
					leaf : false,
					children : [{
								text : '角色',
								leaf : true
							}, {
								text : '权限',
								leaf : true,
								listeners : {
									click : function(control, e) {
										tid = control.attributes.id;
										if (Ext.get(tid) == null) {
											Index.centerPanel.add({
												closable : true,
												id : tid,
												title : '权限管理',
												layout : 'fit',
												items : new User.userGridPanel()
											});
											Index.centerPanel.setActiveTab(tid);
										}
										this.getStore.load({
													params : {
														start : 0,
														limit : Common.pageSize
													}
												});
									}
								}
							}]
				}]
	})
});

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
Ext.ns('Acg.User');
User = Acg.User;

User.usm = new Ext.grid.CheckboxSelectionModel();

User.userFrom = Ext.extend(Ext.ux.FormPanelEx, {

});

User.utb = new Ext.Toolbar({
	items : [{
				text : '添加用户',
				iconCls : 'user_add',
				handler : function() {
					var windowUserAdd = new Ext.ux.WindowEx({
								title : '添加用户',
								iconCls : 'user_add',
								width : 330
							});
					windowUserAdd.show();
					windowUserAdd.center();
				}
			}, {
				text : '编辑用户',
				iconCls : 'user_edit'
			}, {
				text : '删除用户',
				iconCls : 'user_delete',
				handler : function() {
					var selections = User.userGridPanel.getSelectionModel()
							.getSelections();
					var count = selections.length;
					var ids = [];
					Ext.each(selections, function(item) {
								ids.push(item.id);
							});
					if (count <= 0) {
						Ext.MessageBox.show({
									msg : '至少选择一个用户！',
									buttons : Ext.MessageBox.OK,
									icon : Ext.MessageBox.WARNING
								});
					} else {
						Ext.Msg.confirm('确认', '真的要删除选中的数据吗？', function(btn) {
							if (btn == 'yes') {
								Ext.Ajax.request({
									url : 'userDelete',
									success : function(request) {
										var result = Ext
												.decode(request.responseText);
										if (result.success) {
											Ext.MessageBox.show({
														msg : '删除成功!',
														buttons : Ext.MessageBox.OK,
														icon : Ext.MessageBox.OK
													});
											User.ustore.reload();
										} else {
											Ext.MessageBox.show({
														msg : '删除失败!请联系管理员!',
														buttons : Ext.MessageBox.ERROR,
														icon : Ext.MessageBox.ERROR
													});
										}

									},
									failure : function(request) {
										Ext.MessageBox.show({
													msg : '服务器出现错误，删除失败!',
													buttons : Ext.MessageBox.ERROR,
													icon : Ext.MessageBox.ERROR
												});
									},
									params : {
										ids : ids.join(',')
									}
								});
							}
						});
					}
				}
			}]
});

User.ustore = new Ext.data.JsonStore({
			totalProperty : 'page.totalCount',
			root : 'page.result',
			url : './userPage',
			fields : [{
						name : 'id',
						sortable : true,
						type : 'int'
					}, {
						name : 'loginName',
						type : 'string'
					}, {
						name : 'name',
						type : 'string'
					}, {
						name : 'enabled',
						type : 'boolean'
					}, {
						name : 'sex',
						type : 'boolean'
					}, {
						name : 'email',
						type : 'string'
					}]

		});

User.ucm = new Ext.grid.ColumnModel([User.usm, {
			header : 'id',
			dataIndex : 'id'
		}, {
			header : '登录名',
			dataIndex : 'loginName'
		}, {
			header : '姓名',
			dataIndex : 'name'
		}, {
			header : '性别',
			dataIndex : 'sex',
			renderer : function(value) {
				return value ? '男' : '女';
			}
		}, {
			header : '用户状态',
			dataIndex : 'enabled',
			renderer : function(value) {
				return value ? '启用' : '禁用';
			}
		}, {
			header : 'email',
			dataIndex : 'email'
		}]);

User.userGridPanel = new Ext.ux.GridPanelEx({
			cm : User.ucm,
			sm : User.usm,
			store : User.ustore,
			tbar : User.utb,
			bbar : new Ext.ux.PagingToolbarEx({
						pageSize : Common.pageSize, // data to display
						store : User.ustore
					})
		});

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
											tid = control.attributes.id;
											Index.centerPanel.add({
														closable : true,
														id : tid,
														title : '用户管理',
														layout : 'fit',
														items : User.userGridPanel
													});
											Index.centerPanel.setActiveTab(tid);
											User.ustore.load({
														params : {
															start : 0,
															limit : Common.pageSize
														}
													});
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
												leaf : true
											}]
								}]
					})
		});

User.userPanel = new Ext.Panel({
			title : '后台用户管理',
			autoScroll : true,
			border : false,
			iconCls : 'user',
			items : [ User.userTree ]
});

User.init = function() {
	Index.menu.add(User.userPanel);
	Index.menu.doLayout();
};

Ext.onReady(User.init);
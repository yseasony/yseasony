User.AuthFormEx = Ext.extend(Ext.ux.FormPanelEx, {
			bodyStyle : "padding:10px;",
			initComponent : function() {
				this.items = [{
							name : 'password',
							fieldLabel : '权限名',
							inputType : 'password',
							maxLength : 10,
							minLength : 6,
							allowBlank : false,
							blankText : '请输入权限名！'
						}, {
							name : 'name',
							fieldLabel : '权限代码',
							maxLength : 10,
							allowBlank : false,
							blankText : '请输入姓名！'
						}, {
							xtype : "hidden",
							name : "id",
							hidden : true,
							hiddenLabel : true
						}];
				User.UserFormEx.superclass.initComponent.apply(this, arguments);
			},
			constructor : function(config) {
				Ext.apply(this, config);
				User.UserFormEx.superclass.constructor.call(this, {
							buttons : []
						});
			}
		});

User.AuthToolbar = Ext.extend(Ext.Toolbar, {
	ownerGrid : null,
	initComponent : function() {
		_this = this;
		var window = new Ext.ux.WindowEx({
					closeAction : 'hide',
					width : 280
				});
		this.items = [{
					text : '添加权限',
					iconCls : 'user_add',
					handler : function() {
						window.removeAll();
						window.add(new User.userAddForm(ownerGrid));
						window.title = '添加权限';
						window.iconCls = 'user_add';
						window.show();
						window.center();
					}
				}, {
					text : '编辑用户',
					iconCls : 'user_edit',
					handler : function() {
						var selections = ownerGrid.selModel.getSelections();
						if (selections.length != 1) {
							Ext.MessageBox.show({
										msg : '必须选择一个用户！',
										buttons : Ext.MessageBox.OK,
										icon : Ext.MessageBox.WARNING
									});
						} else {
							window.removeAll();
							var userEditForm = new User.userEditForm()
							window.add(userEditForm);
							window.title = '编辑用户';
							window.iconCls = 'user_edit';
							window.show();
							window.center();
							userEditForm.formLoad(selections[0].id);
						}
					}
				}, {
					text : '删除用户',
					iconCls : 'user_delete',
					handler : function() {
						var selections = ownerGrid.selModel.getSelections();
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
							Ext.Msg.confirm('确认', '真的要删除选中的数据吗？',
									function(btn) {
										if (btn == 'yes') {
											Ext.Ajax.request({
												url : './user/userDelete',
												success : function(request) {
													var result = Ext
															.decode(request.responseText);
													if (result.success) {
														Ext.MessageBox.show({
															msg : '删除成功!',
															buttons : Ext.MessageBox.OK,
															icon : Ext.MessageBox.OK
														});
														ownerGrid.store
																.reload();
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
				}], User.UToolbar.superclass.initComponent.apply(this,
				arguments);
	},

	constructor : function(config) {
		ownerGrid = config;
		User.UToolbar.superclass.constructor.call(this, arguments);
	}

});

User.authGridPanel = Ext.extend(Ext.ux.GridPanelEx, {
			constructor : function(config) {
				this.selModel = new Ext.grid.CheckboxSelectionModel();
				this.colModel = new Ext.grid.ColumnModel([this.selModel, {
							header : 'id',
							dataIndex : 'id'
						}, {
							header : '权限名',
							dataIndex : 'displayName'
						}, {
							header : '权限代码',
							dataIndex : 'name'
						}]);
				this.store = new Ext.data.JsonStore({
							totalProperty : 'page.totalCount',
							root : 'page.result',
							url : './auth/authPage',
							fields : [{
										name : 'id',
										sortable : true,
										type : 'int'
									}, {
										name : 'displayName',
										type : 'string'
									}, {
										name : 'name',
										type : 'string'
									}]

						});
				this.bbar = new Ext.ux.PagingToolbarEx({
							pageSize : Common.pageSize, // data to display
							store : this.store
						}), Ext.apply(this, config);
				// this.tbar = new User.UToolbar(this),
				User.authGridPanel.superclass.constructor.call(this, arguments);
			}
		});

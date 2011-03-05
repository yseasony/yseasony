Ext.ns('Acg.User');
User = Acg.User;

User.usm = new Ext.grid.CheckboxSelectionModel();

User.UserFormEx = Ext.extend(Ext.ux.FormPanelEx, {
			bodyStyle : "padding:10px;",
			initComponent : function() {
				this.items = [{
							id : 'password',
							name : 'password',
							fieldLabel : '密　码',
							inputType : 'password',
							maxLength : 10,
							minLength : 6,
							allowBlank : false,
							blankText : '请输入密码！'
						}, {
							name : 'password2',
							fieldLabel : '确认密码',
							inputType : 'password',
							maxLength : 10,
							minLength : 6,
							allowBlank : false,
							blankText : '请输入确认密码！',
							vtype : "password",// 自定义的验证类型
							vtypeText : "两次密码不一致！",
							confirmTo : "password"// 要比较的另外一个的组件的id
						}, {
							name : 'name',
							fieldLabel : '姓名',
							maxLength : 10,
							allowBlank : false,
							blankText : '请输入姓名！'
						}, {
							name : 'email',
							fieldLabel : 'email',
							vtype : "email",// email格式验证
							vtypeText : "不是有效的邮箱地址",
							maxLength : 30
						}, new Ext.form.RadioGroup({
									fieldLabel : '性别',
									items : [new Ext.form.Radio({
														name : 'sex',
														boxLabel : '男',
														inputValue : 1,
														checked : true

													}), new Ext.form.Radio({
														name : 'sex',
														boxLabel : '女',
														inputValue : 0
													})]
								}), new Ext.form.RadioGroup({
									fieldLabel : '是否启用',
									items : [new Ext.form.Radio({
														name : 'enabled',
														boxLabel : '是',
														inputValue : 1,
														checked : true

													}), new Ext.form.Radio({
														name : 'enabled',
														boxLabel : '否',
														inputValue : 0
													})]
								})];
				User.UserFormEx.superclass.initComponent.apply(this, arguments);
			},
			constructor : function(config) {
				Ext.apply(this, config);
				User.UserFormEx.superclass.constructor.call(this, {
							buttons : []
						});
			}
		});

User.userAddForm = Ext.extend(User.UserFormEx, {
			formSubmit : function() {
				this.getForm().submit({
							url : './user/userSave',
							success : function(userAddForm, action) {
								this.ownerCt.hide();
								User.ustore.reload();
							},
							failure : function(userAddForm, action) {
								if (action.failureType == 'server') {
									Ext.Msg.alert('', '添加失败！');
								}
							},
							scope : this
						});
			},
			formCancel : function() {
				this.ownerCt.hide();
			},
			constructor : function(config) {
				Ext.apply(this, config);
				User.userAddForm.superclass.constructor.call(this, {
							buttons : []
						});
				this.insert(0, {
							xtype : 'textfield',
							name : 'loginName',
							fieldLabel : '用户名',
							maxLength : 10,
							minLength : 6,
							allowBlank : false,
							blankText : '请输入用户名！',
							vtype : "valueExist",
							existUrl : './user/userExist',
							filterName : 'filter_loginName',
							vtypeText : "用户名已存在"
						});
			}
		});

User.userEditForm = Ext.extend(User.UserFormEx, {
			formSubmit : function() {
				this.getForm().submit({
							url : './user/userSave',
							success : function(userEditForm, action) {
								this.ownerCt.hide();
								User.ustore.reload();
							},
							failure : function(userEditForm, action) {
								if (action.failureType == 'server') {
									Ext.Msg.alert('', '添加失败！');
								}
							},
							scope : this
						});
			},
			formCancel : function() {
				this.ownerCt.hide();
			},
			formLoad : function() {
			},
			constructor : function(config) {
				Ext.apply(this, config);
				User.userEditForm.superclass.constructor.call(this, {
							buttons : []
						});
			}
		});

User.windowUser = new Ext.ux.WindowEx({
			closeAction : 'hide',
			width : 280
		});

User.utb = new Ext.Toolbar({
	items : [{
				text : '添加用户',
				iconCls : 'user_add',
				handler : function() {
					User.windowUser.removeAll();
					User.windowUser.add(new User.userAddForm());
					User.windowUser.title = '添加用户';
					User.windowUser.iconCls = 'user_add';
					User.windowUser.show();
					User.windowUser.center();
				}
			}, {
				text : '编辑用户',
				iconCls : 'user_edit',
				handler : function() {
					var selections = User.userGridPanel.getSelectionModel()
							.getSelections();
					if (selections.length != 1) {
						Ext.MessageBox.show({
									msg : '必须选择一个用户！',
									buttons : Ext.MessageBox.OK,
									icon : Ext.MessageBox.WARNING
								});
					} else {
						User.windowUser.removeAll();
						User.windowUser.add(new User.userEditForm());
						User.windowUser.title = '编辑用户';
						User.windowUser.iconCls = 'user_edit';
						User.windowUser.show();
						User.windowUser.center();
					}
				}
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
			url : './user/userPage',
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
			items : [User.userTree]
		});

User.init = function() {
	Index.menu.add(User.userPanel);
	Index.menu.doLayout();
};

Ext.onReady(User.init);
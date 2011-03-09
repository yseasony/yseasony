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
							name : 'name',
							fieldLabel : '姓名',
							maxLength : 10,
							allowBlank : false,
							blankText : '请输入姓名！'
						}, {
							name : 'email',
							fieldLabel : 'email',
							vtype : "email",
							vtypeText : "不是有效的邮箱地址",
							maxLength : 30
						}, {
							xtype : "hidden",
							name : "id",
							hidden : true,
							hiddenLabel : true
						}, new Ext.form.RadioGroup({
									fieldLabel : '性别',
									items : [new Ext.ux.RadioGroupEx({
														name : 'sex',
														boxLabel : '男',
														inputValue : true,
														checked : true

													}), new Ext.form.Radio({
														name : 'sex',
														boxLabel : '女',
														inputValue : false
													})]
								}), new Ext.form.RadioGroup({
									fieldLabel : '是否启用',
									items : [new Ext.ux.RadioGroupEx({
														name : 'enabled',
														boxLabel : '是',
														inputValue : true,
														checked : true

													}), new Ext.form.Radio({
														name : 'enabled',
														boxLabel : '否',
														inputValue : false
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
								ownerGrid.store.reload();
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
				this.ownerGrid = config;
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
				this.insert(2, {
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
						});
			}
		});

User.userEditForm = Ext.extend(User.UserFormEx, {
			formSubmit : function() {
				this.getForm().submit({
							url : './user/userSave',
							success : function(userEditForm, action) {
								this.ownerCt.hide();
								ownerGrid.store.reload();
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
			formLoad : function(id) {
				this.getForm().load({
							url : './user/userEdit',
							params : {
								uid : id
							},
							waitTitle : '提示',
							waitMsg : '正在处理您的请求,请稍候...',
							success : function(form, action) {
								console.log(action.result.data);
							},
							failure : function(form, action) {
							}
						});
			},
			constructor : function(config) {
				this.ownerGrid = config;
				User.userEditForm.superclass.constructor.call(this, {
							buttons : []
						});
			}
		});

User.UToolbar = Ext.extend(Ext.Toolbar, {
	ownerGrid : null,
	initComponent : function() {
		_this = this;
		var windowUser = new Ext.ux.WindowEx({
					closeAction : 'hide',
					width : 280
				});
		this.items = [{
					text : '添加用户',
					iconCls : 'user_add',
					handler : function() {
						windowUser.removeAll();
						windowUser.add(new User.userAddForm(ownerGrid));
						windowUser.title = '添加用户';
						windowUser.iconCls = 'user_add';
						windowUser.show();
						windowUser.center();
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
							windowUser.removeAll();
							var userEditForm = new User.userEditForm()
							windowUser.add(userEditForm);
							windowUser.title = '编辑用户';
							windowUser.iconCls = 'user_edit';
							windowUser.show();
							windowUser.center();
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

User.userGridPanel = Ext.extend(Ext.ux.GridPanelEx, {
	constructor : function(config) {
		this.selModel = new Ext.grid.CheckboxSelectionModel();
		this.colModel = new Ext.grid.ColumnModel([this.selModel, {
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
		this.store = new Ext.data.JsonStore({
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
		this.bbar = new Ext.ux.PagingToolbarEx({
					pageSize : Common.pageSize, // data to display
					store : this.store
				}), Ext.apply(this, config);
		this.tbar = new User.UToolbar(this), User.userGridPanel.superclass.constructor
				.call(this, arguments);
	}
});

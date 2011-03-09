User.UserFormEx = Ext.extend(Ext.ux.FormPanelEx, {
			bodyStyle : "padding:10px;",
			initComponent : function() {
				this.items = [{
							id : 'password',
							name : 'password',
							fieldLabel : Lang.user.password,
							inputType : 'password',
							maxLength : 10,
							minLength : 6,
							allowBlank : false,
							blankText : Lang.msg.pwd_blank
						}, {
							name : 'name',
							fieldLabel : Lang.common.name,
							maxLength : 10,
							allowBlank : false,
							blankText : Lang.msg.name_blank
						}, {
							name : 'email',
							fieldLabel : Lang.common.email,
							vtype : "email",
							vtypeText : Lang.msg.email_check,
							maxLength : 30
						}, {
							xtype : "hidden",
							name : "id",
							hidden : true,
							hiddenLabel : true
						}, new Ext.form.RadioGroup({
									fieldLabel : Lang.common.gender,
									items : [new Ext.ux.RadioGroupEx({
														name : 'sex',
														boxLabel : Lang.common.male,
														inputValue : true,
														checked : true

													}), new Ext.form.Radio({
														name : 'sex',
														boxLabel : Lang.common.female,
														inputValue : false
													})]
								}), new Ext.form.RadioGroup({
									fieldLabel : Lang.common.available,
									items : [new Ext.ux.RadioGroupEx({
														name : 'enabled',
														boxLabel : Lang.common.yes,
														inputValue : true,
														checked : true

													}), new Ext.form.Radio({
														name : 'enabled',
														boxLabel : Lang.common.no,
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
									Ext.Msg.alert('', Lang.msg.addfaile);
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
							fieldLabel : Lang.common.login_name,
							maxLength : 10,
							minLength : 6,
							allowBlank : false,
							blankText : Lang.msg.login_name_blank,
							vtype : "valueExist",
							existUrl : './user/userExist',
							filterName : 'filter_loginName',
							vtypeText : Lang.msg.data_exist
						});
				this.insert(2, {
							name : 'password2',
							fieldLabel : Lang.user.confirm_password,
							inputType : 'password',
							maxLength : 10,
							minLength : 6,
							allowBlank : false,
							blankText : Lang.msg.confirm_password_blank,
							vtype : "password",// 自定义的验证类型
							vtypeText : Lang.msg.confirm_error,
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
									Ext.Msg.alert('', Lang.msg.addfaile);
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
							waitMsg : Lang.msg.wait_msg,
							success : function(form, action) {
							},
							failure : function(form, action) {
								Ext.Msg.alert('', Lang.msg.server_error);
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
					text : Lang.user.user_add,
					iconCls : 'user_add',
					handler : function() {
						windowUser.removeAll();
						windowUser.add(new User.userAddForm(ownerGrid));
						windowUser.title = Lang.common.user_add;
						windowUser.iconCls = 'user_add';
						windowUser.show();
						windowUser.center();
					}
				}, {
					text : Lang.user.user_edit,
					iconCls : 'user_edit',
					handler : function() {
						var selections = ownerGrid.selModel.getSelections();
						if (selections.length != 1) {
							Ext.MessageBox.show({
										msg : Lang.msg.select_least_one,
										buttons : Ext.MessageBox.OK,
										icon : Ext.MessageBox.WARNING
									});
						} else {
							windowUser.removeAll();
							var userEditForm = new User.userEditForm()
							windowUser.add(userEditForm);
							windowUser.title = Lang.user.user_edit;
							windowUser.iconCls = 'user_edit';
							windowUser.show();
							windowUser.center();
							userEditForm.formLoad(selections[0].id);
						}
					}
				}, {
					text : Lang.user.user_delete,
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
										msg : Lang.msg.select_least_one,
										buttons : Ext.MessageBox.OK,
										icon : Ext.MessageBox.WARNING
									});
						} else {
							Ext.Msg.confirm(Lang.common.confirm,
									Lang.msg.confirm_msg, function(btn) {
										if (btn == 'yes') {
											Ext.Ajax.request({
												url : './user/userDelete',
												success : function(request) {
													var result = Ext
															.decode(request.responseText);
													if (result.success) {
														Ext.MessageBox.show({
															msg : Lang.msg.delete_success,
															buttons : Ext.MessageBox.OK,
															icon : Ext.MessageBox.OK
														});
														ownerGrid.store
																.reload();
													} else {
														Ext.MessageBox.show({
															msg : Lang.msg.server_error,
															buttons : Ext.MessageBox.ERROR,
															icon : Ext.MessageBox.ERROR
														});
													}

												},
												failure : function(request) {
													Ext.MessageBox.show({
														msg : Lang.msg.server_error,
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
					header : Lang.common.login_name,
					dataIndex : 'loginName'
				}, {
					header : Lang.common.name,
					dataIndex : 'name'
				}, {
					header : Lang.common.gender,
					dataIndex : 'sex',
					renderer : function(value) {
						return value ? Lang.common.male : Lang.common.female;
					}
				}, {
					header : Lang.user.user_enabled,
					dataIndex : 'enabled',
					renderer : function(value) {
						return value
								? Lang.common.enabled
								: Lang.common.enabled;
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

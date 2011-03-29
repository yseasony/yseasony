User.RoleFormEx = Ext.extend(Ext.ux.FormPanelEx, {
	labelWidth : 60,
	formCancel : function() {
		this.ownerCt.hide();
	},
	formSubmit : function() {
		this.getForm().submit({
					url : './role/roleSave',
					success : function(form, action) {
						User.roleWindow.hide();
						Ext.getCmp('p_role').store.reload();
					},
					failure : function(form, action) {
						if (action.failureType == 'server') {
							Ext.Msg.alert('', Lang.msg.addfaile);
						}
					}
				});
	},
	formLoad : function(id) {
		this.getForm().load({
			url : './role/roleEdit',
			params : {
				id : id
			},
			waitMsg : Lang.msg.wait_msg,
			success : function(response, action) {
				var auths = action.result.data.authorities;
				var authbox = Ext.getCmp('authsCheckbox');
				for (var i = 0; i < authbox.items.length; i++) {
					Ext.each(auths, function(item) {
								if (authbox.items.itemAt(i).inputValue == item.id) {
									authbox.items.itemAt(i).setValue(true);
								}
							});
				}
			},
			failure : function(response, action) {
				Ext.Msg.alert('', Lang.msg.server_error);
			}
		});
	},
	getAuths : function() {
		var auths = '';
		Ext.Ajax.request({
					async : false,
					url : './auth/authAll',
					success : function(response) {
						auths = Ext.util.JSON.decode(response.responseText);
					},
					failure : function(response) {
						Ext.MessageBox.show({
									msg : Lang.msg.server_error,
									buttons : Ext.MessageBox.ERROR,
									icon : Ext.MessageBox.ERROR
								});
					}
				});
		return auths;
	},
	initComponent : function() {
		this.items = [{
					name : 'name',
					fieldLabel : Lang.role.role,
					maxLength : 10,
					allowBlank : false,
					blankText : Lang.msg.role_name_blank,
					vtype : "valueExist",
					existUrl : './role/roleExist',
					filterName : 'filter_name',
					vtypeText : Lang.msg.data_exist
				}, {
					id : 'authsCheckbox',
					xtype : 'checkboxgroup',
					fieldLabel : Lang.auth.auth_name,
					columns : 2,
					hideLables : true,
					items : this.getAuths()
				}, {
					xtype : "hidden",
					name : "id",
					hidden : true,
					hiddenLabel : true
				}];
		User.RoleFormEx.superclass.initComponent.apply(this, arguments);
	},
	constructor : function(config) {
		Ext.apply(this, config);
		User.RoleFormEx.superclass.constructor.call(this, {
					buttons : []
				});
	}
});

User.roleSm = new Ext.grid.CheckboxSelectionModel();

User.roleWindow = new Ext.ux.WindowEx({
			closeAction : 'hide',
			width : 300
		});

User.RoleGridPanel = Ext.extend(Ext.ux.GridPanelEx, {
	id : 'p_role',
	sm : User.roleSm,
	initComponent : function() {
		this.colModel = new Ext.grid.ColumnModel([User.roleSm, {
					header : 'id',
					dataIndex : 'id'
				}, {
					header : Lang.role.role,
					dataIndex : 'name'
				}, {
					header : Lang.auth.auth,
					dataIndex : 'authorities',
					renderer : function(value) {
						var authNames = [];
						Ext.each(value, function(item) {
									authNames.push(item.displayName);
								});
						return authNames.join(',');
					}
				}]);
		this.store = new Ext.data.JsonStore({
					totalProperty : 'totalCount',
					root : 'result',
					url : './role/rolePage',
					fields : [{
								name : 'id',
								sortable : true,
								type : 'int'
							}, {
								name : 'name',
								type : 'string'
							}, {
								name : 'authorities',
								type : 'object'
							}]

				});
		this.bbar = new Ext.ux.PagingToolbarEx({
					pageSize : Common.pageSize, // data to display
					store : this.store
				});
		this.tbar = this.buildTopToolbar();
		User.RoleGridPanel.superclass.initComponent.call(this);
	},
	constructor : function(config) {
		Ext.apply(this, config);
		User.RoleGridPanel.superclass.constructor.call(this, arguments);
	},
	buildTopToolbar : function() {
		return [{
					text : Lang.role.role_add,
					iconCls : 'add',
					handler : function() {
						User.roleWindow.removeAll();
						User.roleWindow.add(new User.RoleFormEx());
						User.roleWindow.title = Lang.role.role_add;
						User.roleWindow.iconCls = 'add';
						User.roleWindow.show();
						User.roleWindow.center();
					}
				}, {
					text : Lang.role.role_edit,
					iconCls : 'edit',
					handler : function() {
						var selections = User.roleSm.getSelections();
						if (selections.length != 1) {
							Ext.MessageBox.show({
										msg : Lang.msg.select_least_one,
										buttons : Ext.MessageBox.OK,
										icon : Ext.MessageBox.WARNING
									});
						} else {
							User.roleWindow.removeAll();
							var form = new User.RoleFormEx();
							User.roleWindow.add(form);
							User.roleWindow.title = Lang.role.role_edit;
							User.roleWindow.iconCls = 'edit';
							User.roleWindow.show();
							User.roleWindow.center();
							form.formLoad(selections[0].id);
						}
					}
				}, {
					text : Lang.role.role_delete,
					iconCls : 'delete',
					handler : function() {
						var selections = User.roleSm.getSelections();
						var ids = [];
						Ext.each(selections, function(item) {
									ids.push(item.id);
								});
						if (selections.length < 1) {
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
												url : './role/roleDelete',
												success : function(request) {
													var result = Ext
															.decode(request.responseText);
													if (result.success) {
														Ext.MessageBox.show({
															msg : Lang.msg.delete_success,
															buttons : Ext.MessageBox.OK,
															icon : Ext.MessageBox.OK
														});
														Ext.getCmp('p_role').store
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
				}]
	}
});

User.AuthFormEx = Ext.extend(Ext.ux.FormPanelEx, {
			bodyStyle : "padding:10px;",
			initComponent : function() {
				this.items = [{
							name : 'displayName',
							fieldLabel : Lang.auth.auth_displayName,
							maxLength : 15,
							minLength : 1,
							allowBlank : false,
							blankText : Lang.msg.auth_displayName_blank
						}, {
							name : 'name',
							fieldLabel : Lang.auth.auth_name,
							maxLength : 100,
							allowBlank : false,
							blankText : Lang.msg.auth_name_blank
						}, {
							xtype : "hidden",
							name : "id",
							hidden : true,
							hiddenLabel : true
						}];
				User.AuthFormEx.superclass.initComponent.apply(this, arguments);
			},
			constructor : function(config) {
				Ext.apply(this, config);
				User.AuthFormEx.superclass.constructor.call(this, {
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
					text : Lang.auth.auth_add,
					iconCls : 'add',
					handler : function() {
						var u = new ownerGrid.store.recordType({
									name : '',
									displayName : ''
								});
						console.log(ownerGrid);
						ownerGrid.stopEditing();
						ownerGrid.store.insert(0, u);
						ownerGrid.startEditing(0, 1);
					}
				}, {
					text : Lang.auth.auth_delete,
					iconCls : 'delete',
					handler : function() {
						var selections = ownerGrid.selModel.getSelections();
						var count = selections.length;
						var ids = [];
						console.log(ownerGrid.getSelectionModel()
								.getSelectedCell());
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
												url : './auth/authDelete',
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
				}, {
					text : Lang.common.save,
					iconCls : 'save',
					handler : function() {
						ownerGrid.store.save();
						ownerGrid.store.reload();
					}
				}], User.UToolbar.superclass.initComponent.apply(this,
				arguments);
	},

	constructor : function(config) {
		ownerGrid = config;
		User.UToolbar.superclass.constructor.call(this, arguments);
	}

});

User.authProxy = new Ext.data.HttpProxy({
			api : {
				read : './auth/authPage',
				create : './auth/authSave',
				update : './auth/authSave',
				destroy : './auth/authDelete'
			}
		});

User.authReader = new Ext.data.JsonReader({
			totalProperty : 'totalCount',
			root : 'result',
			successProperty : 'success',
			idProperty : 'id',
			messageProperty : 'message'
		}, [{
					name : 'id'
				}, {
					name : 'displayName',
					allowBlank : false
				}, {
					name : 'name',
					allowBlank : false
				}]);

User.authWriter = new Ext.data.CleanJsonWriter({
			encode : false,
			writeAllFields : false
		});

User.authGridPanel = Ext.extend(Ext.ux.EditorGridPanelEx, {
	initComponent : function() {
		this.relayEvents(this.store, ['destroy', 'save', 'update']);
		User.authGridPanel.superclass.initComponent.call(this);
	},
	constructor : function(config) {
		this.selModel = new Ext.grid.CheckboxSelectionModel();
		this.colModel = new Ext.grid.ColumnModel([this.selModel, {
					header : 'id',
					dataIndex : 'id'
				}, {
					header : Lang.auth.auth_displayName,
					name : 'displayName',
					dataIndex : 'displayName',
					editor : new Ext.form.TextField({})
				}, {
					header : Lang.auth.auth_name,
					name : 'name',
					dataIndex : 'name',
					editor : new Ext.form.TextField({})
				}]);
		this.store = new Ext.data.Store({
					id : 'auth',
					proxy : User.authProxy,
					reader : User.authReader,
					writer : User.authWriter,
					autoSave : false,
					restful : true,
					async : false
				});
		this.bbar = new Ext.ux.PagingToolbarEx({
					pageSize : Common.pageSize, // data to display
					store : this.store
				}), Ext.apply(this, config);
		this.tbar = new User.AuthToolbar(this), User.authGridPanel.superclass.constructor
				.call(this, arguments);
	}
});

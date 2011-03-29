User.authProxy = new Ext.data.HttpProxy({
			api : {
				read : './auth/authPage',
				create : './auth/authSave',
				update : './auth/authUpdate',
				destroy : './auth/authDelete'
			},
			listeners : {
				exception : function(_this, options, response, error) {
					console.log(e);
				}
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

User.AuthGridPanel = Ext.extend(Ext.ux.EditorGridPanelEx, {
	id : 'p_auth',
	initComponent : function() {
		this.relayEvents(this.store, ['destroy', 'save', 'update']);
		this.selModel = new Ext.grid.CheckboxSelectionModel();
		this.colModel = new Ext.grid.ColumnModel([this.selModel, {
					header : 'id',
					dataIndex : 'id'
				}, {
					header : Lang.auth.auth_displayName,
					name : 'displayName',
					dataIndex : 'displayName',
					editor : new Ext.form.TextField({
								allowBlank : false
							})
				}, {
					header : Lang.auth.auth_name,
					name : 'name',
					dataIndex : 'name',
					editor : new Ext.form.TextField({
								allowBlank : false,
								vtype : "valueExist",
								existUrl : './auth/authExist',
								filterName : 'filter_name',
								vtypeText : Lang.msg.data_exist
							})
				}]);

		this.bbar = new Ext.ux.PagingToolbarEx({
					pageSize : Common.pageSize, // data to display
					store : this.store
				});
		this.tbar = this.buildTopToolbar();
		User.AuthGridPanel.superclass.initComponent.call(this);
	},
	constructor : function(config) {
		this.store = new Ext.data.Store({
					id : 'auth',
					proxy : User.authProxy,
					reader : User.authReader,
					writer : User.authWriter,
					autoSave : false,
					restful : true
				});
		Ext.apply(this, config);
		User.AuthGridPanel.superclass.constructor.call(this, arguments);
	},
	buildTopToolbar : function() {
		return [{
					text : Lang.auth.auth_add,
					iconCls : 'add',
					handler : function() {
						var u = new this.store.recordType({
									name : '',
									displayName : ''
								});
						this.stopEditing();
						this.store.insert(0, u);
						this.startEditing(0, 1);
					},
					scope : this
				}, {
					text : Lang.auth.auth_delete,
					iconCls : 'delete',
					handler : function() {
						var selections = this.selModel.getSelections();
						var ids = [];
						var gird = this;
						Ext.each(selections, function(item) {
									if (!isNaN(item.id)) {
										ids.push(item.id);
									}
								});
						var count = selections.length;
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
												async : false,
												params : {
													ids : ids.join(',')
												},
												success : function(request) {
													var result = Ext
															.decode(request.responseText);
													if (result.success) {
														Ext.MessageBox.show({
															msg : Lang.msg.delete_success,
															buttons : Ext.MessageBox.OK,
															icon : Ext.MessageBox.OK
														});
														Ext.each(selections,
																function(item) {
																	if (isNaN(item.id)) {
																		this.store
																				.remove(item);
																	}
																});
														gird.store.reload();
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
												}
											});
										}
									});
						}
					},
					scope : this
				}, {
					text : Lang.common.save,
					iconCls : 'save',
					handler : function() {
						if (this.validateEx(this)) {
							var c = this.store.save();
							if (c != -1) {
								this.store.reload();
							}
						}
					},
					scope : this
				}];
	}
});

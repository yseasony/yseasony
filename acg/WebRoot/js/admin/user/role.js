User.RoleFormEx = Ext.extend(Ext.ux.FormPanelEx, {
			formCancel : function() {
				this.ownerCt.hide();
			},
			initComponent : function() {
				this.items = [{
							name : 'name',
							fieldLabel : Lang.role.role,
							maxLength : 10,
							allowBlank : false,
							blankText : Lang.msg.role_name_blank
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

User.RoleGridPanel = Ext.extend(Ext.ux.GridPanelEx, {
			initComponent : function() {
				this.selModel = new Ext.grid.CheckboxSelectionModel();
				this.colModel = new Ext.grid.ColumnModel([this.selModel, {
							header : 'id',
							dataIndex : 'id'
						}, {
							header : Lang.role.role,
							dataIndex : 'name'
						}]);
				this.store = new Ext.data.JsonStore({
							totalProperty : 'totalCount',
							root : 'result',
							url : './user/userPage',
							fields : [{
										name : 'id',
										sortable : true,
										type : 'int'
									}, {
										name : 'name',
										type : 'string'
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
				var window = new Ext.ux.WindowEx({
							closeAction : 'hide',
							width : 280
						});
				return [{
							text : Lang.role.role_add,
							iconCls : 'add',
							handler : function() {
								window.removeAll();
								window.add(new User.RoleFormEx());
								window.title = Lang.role.role_add;
								window.iconCls = 'add';
								window.show();
								window.center();
							}
						}, {
							text : Lang.role.role_edit,
							iconCls : 'edit',
							handler : function() {
								var selections = this.selModel.getSelections();
								if (selections.length != 1) {
									Ext.MessageBox.show({
												msg : Lang.msg.select_least_one,
												buttons : Ext.MessageBox.OK,
												icon : Ext.MessageBox.WARNING
											});
								} else {
									window.removeAll();
									var form = new User.RoleFormEx();
									window.add(form);
									window.title = Lang.role.role;
									window.iconCls = 'edit';
									window.show();
									window.center();
									userEditForm.formLoad(selections[0].id);
								}
							}
						}]
			}
		});

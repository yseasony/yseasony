User.authGridPanel = Ext.extend(Ext.ux.GridPanelEx, {
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

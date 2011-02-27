Ext.ux.PagingToolbarEx = Ext.extend(Ext.PagingToolbar, {
			displayInfo : true,
			afterPageText : '/{0}页',
			beforePageText : '第',
			displayInfo : true,
			displayMsg : '显示 {0} - {1} 条，一共有{2}条数据',
			firstText : '第一页',
			prevText : '前一页',
			nextText : '后一页',
			lastText : '最后一页',
			refreshText : '刷新',
			emptyMsg : '没有记录',
			constructor : function(config) {
				Ext.apply(this, config);
				Ext.ux.PagingToolbarEx.superclass.constructor.call(this,
						arguments);
			}
		});

Ext.ux.GridPanelEx = Ext.extend(Ext.grid.GridPanel, {
			loadMask : true,
			autoWidth : true,
			stripeRows : true,
			viewConfig : {
				forceFit : true
			},
			constructor : function(config) {
				Ext.apply(this, config);
				Ext.ux.GridPanelEx.superclass.constructor.call(this, arguments);
			}
		});

Ext.ux.WindowEx = Ext.extend(Ext.Window, {
			layout : 'fit',
			border : false,
			bodyStyle : 'padding:5px 5px 0px 5px;',
			buttonAlign : 'center',
			resizable : false,
			autoHeight : true,
			plain : true,
			modal : true,
			constructor : function(config) {
				Ext.apply(this, config);
				Ext.ux.WindowEx.superclass.constructor.call(this, arguments);
			}
		});

Ext.ux.FormPanelEx = Ext.extend(Ext.FormPanel, {
			frame : true,
			region : 'center',
			buttonAlign : 'center',
			autoHeight : true,
			labelAlign : 'right',
			labelWidth : 50,
			defaultType : 'textfield',
			formSubmit : function() {
			},
			formCancel : function() {
			},
			constructor : function(config) {
				Ext.apply(this, config);
				_this = this;
				Ext.ux.FormPanelEx.superclass.constructor.call(this, {
							keys : [{
										key : [Ext.EventObject.ENTER],
										handler : function() {
											_this.formSubmit();
										}
									}],
							buttons : [_this.buttons, {
										text : '提交',
										handler : function() {
											_this.formSubmit();
										}
									}, {
										text : '取消',
										handler : function() {
											_this.formCancel();
										}
									}]
						});
			}
		});

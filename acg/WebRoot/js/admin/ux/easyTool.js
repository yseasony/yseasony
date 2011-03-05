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
	labelWidth : 70,
	defaultType : 'textfield',
	formSubmit : function() {
	},
	formCancel : function() {
	},
	formLoad : function() {
	},
	constructor : function(config) {
		Ext.apply(this, config);
		Ext.apply(Ext.form.VTypes, {
					password : function(val, field) {// val指这里的文本框值，field指这个文本框组件，大家要明白这个意思
						if (field.confirmTo) {// confirmTo是我们自定义的配置参数，一般用来保存另外的组件的id值
							var pwd = Ext.get(field.confirmTo);// 取得confirmTo的那个id的值
							return (val == pwd.getValue());
						}
						return false;
					}
				});
		Ext.apply(Ext.form.VTypes, {
			valueExist : function(val, field) {
				if (field.existUrl && val.length >= field.minLength) {// 校验地址
					var filters = new Object();
					var b = false;
					filters[field.filterName] = val;
					Ext.Ajax.request({
								async : false,
								url : field.existUrl,
								params : filters,
								success : function(response, options) {
									b = Ext.decode(response.responseText).success
								}
							});

				}
				return b;
			}
		});
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
							}],
					items : this.items
				});
	}
});

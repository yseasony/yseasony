{
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
			Ext.ux.PagingToolbarEx.superclass.constructor.call(this, arguments);
		}
	});
}
{
	Ext.ux.GridPanelEx = Ext.extend(Ext.grid.GridPanel, {
				loadMask : true,
				autoWidth : true,
				frame : true,
				stripeRows : true,
				viewConfig : {
					forceFit : true
				},
				constructor : function(config) {
					Ext.apply(this, config);
					Ext.ux.GridPanelEx.superclass.constructor.call(this,
							arguments);
				}
			});
}
{
	Ext.ux.EditorGridPanelEx = Ext.extend(Ext.grid.EditorGridPanel, {
		loadMask : true,
		autoWidth : true,
		stripeRows : true,
		frame : true,
		viewConfig : {
			forceFit : true
		},
		// 校验
		validateEx : function(ownerGrid) {
			var m = ownerGrid.store.modified.slice(0);
			for (var i = 0; i < m.length; i++) {
				var record = m[i];
				var fields = record.fields.keys;
				for (var j = 0; j < fields.length; j++) {
					var name = fields[j];
					var value = record.data[name];
					var colIndex = ownerGrid.colModel.findColumnIndex(name);
					var rowIndex = ownerGrid.store.indexOfId(record.id);
					var editor = ownerGrid.colModel.getCellEditor(colIndex);
					if (editor != null) {
						editor = ownerGrid.colModel.getCellEditor(colIndex).field;
						if (!editor.validateValue(value)) {
							var cell = ownerGrid.getView().getCell(rowIndex,
									colIndex);
							cell = Ext.get(cell);
							cell.focus();
							ownerGrid.startEditing(rowIndex, colIndex);
							return false;
						}
					}
				}
			}
			return true;
		},
		constructor : function(config) {
			Ext.apply(Ext.form.VTypes, {
						valueExist : Ext.vt
					});
			Ext.lib.Ajax.async = false;
			Ext.form.Field.prototype.msgTarget = 'side';
			Ext.apply(this, config);
			Ext.ux.EditorGridPanelEx.superclass.constructor.call(this,
					arguments);
		}
	});
}
{
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
					Ext.ux.WindowEx.superclass.constructor
							.call(this, arguments);
				}
			});
}
{
	Ext.ux.FormPanelEx = Ext.extend(Ext.FormPanel, {
				frame : true,
				region : 'center',
				buttonAlign : 'center',
				autoHeight : true,
				labelAlign : 'right',
				labelWidth : 70,
				bodyStyle : "padding:10px;",
				defaultType : 'textfield',
				ownerGrid : null,
				formSubmit : function() {
				},
				formCancel : function() {
				},
				formLoad : function() {
				},
				constructor : function(config) {
					Ext.form.Field.prototype.msgTarget = 'qtip';
					Ext.apply(this, config);
					Ext.apply(Ext.form.VTypes, {
								password : function(val, field) {// val指这里的文本框值，field指这个文本框组件，大家要明白这个意思
									if (field.confirmTo) {// confirmTo是我们自定义的配置参数，一般用来保存另外的组件的id值
										var pwd = Ext.get(field.confirmTo);// 取得confirmTo的那个id的值
										return val == pwd.getValue();
									}
									return false;
								}
							});
					Ext.apply(Ext.form.VTypes, {
								valueExist : Ext.vt
							});
					var _this = this;
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
}

{

	Ext.ux.RadioGroupEx = Ext.extend(Ext.form.Field, {
				/**
				 * @cfg {String} focusClass The CSS class to use when the
				 *      checkbox receives focus (defaults to undefined)
				 */
				focusClass : undefined,
				/**
				 * @cfg {String} fieldClass The default CSS class for the
				 *      checkbox (defaults to "x-form-field")
				 */
				fieldClass : "x-form-field",
				/**
				 * @cfg {Boolean} checked True if the the checkbox should render
				 *      already checked (defaults to false)
				 */
				checked : false,
				/**
				 * @cfg {String/Object} autoCreate A DomHelper element spec, or
				 *      true for a default element spec (defaults to {tag:
				 *      "input", type: "radio", autocomplete: "off"})
				 */
				defaultAutoCreate : {
					tag : "input",
					type : 'radio',
					autocomplete : "off"
				},
				/**
				 * @cfg {String} boxLabel The text that appears beside the
				 *      checkbox
				 */

				getId : function() {
					// if multiple radios are defined use this information
					if (this.radios && this.radios instanceof Array) {
						if (this.radios.length) {
							var r = this.radios[0];
							this.value = r.value;
							this.boxLabel = r.boxLabel;
							this.checked = r.checked || false;
							this.readOnly = r.readOnly || false;
							this.disabled = r.disabled || false;
							this.tabIndex = r.tabIndex;
							this.cls = r.cls;
							this.listeners = r.listeners;
							this.style = r.style;
							this.bodyStyle = r.bodyStyle;
							this.hideParent = r.hideParent;
							this.hidden = r.hidden;
						}
					}
					Ext.ux.RadioGroupEx.superclass.getId.call(this);
				},

				// private
				initComponent : function() {
					Ext.ux.RadioGroupEx.superclass.initComponent.call(this);
					this.addEvents(
							/**
							 * @event change Fires when the radio value changes.
							 * @param {Ext.vx.RadioGroup}
							 *            this This radio
							 * @param {Boolean}
							 *            checked The new checked value
							 */
							'check');
				},

				// private
				onResize : function() {
					Ext.ux.RadioGroupEx.superclass.onResize.apply(this,
							arguments);
					if (!this.boxLabel) {
						this.el.alignTo(this.wrap, 'c-c');
					}
				},

				// private
				initEvents : function() {
					Ext.ux.RadioGroupEx.superclass.initEvents.call(this);
					this.el.on("click", this.onClick, this);
					this.el.on("change", this.onClick, this);
				},

				// private
				getResizeEl : function() {
					return this.wrap;
				},

				// private
				getPositionEl : function() {
					return this.wrap;
				},

				/**
				 * Overridden and disabled. The editor element does not support
				 * standard valid/invalid marking.
				 * 
				 * @hide
				 * @method
				 */
				markInvalid : Ext.emptyFn,
				/**
				 * Overridden and disabled. The editor element does not support
				 * standard valid/invalid marking.
				 * 
				 * @hide
				 * @method
				 */
				clearInvalid : Ext.emptyFn,

				// private
				onRender : function(ct, position) {
					Ext.ux.RadioGroupEx.superclass.onRender.call(this, ct,
							position);
					this.wrap = this.el.wrap({
								cls : "x-form-check-wrap"
							});
					if (this.boxLabel) {
						this.wrap.createChild({
									tag : 'label',
									htmlFor : this.el.id,
									cls : 'x-form-cb-label',
									html : this.boxLabel
								});
					}
					if (!this.isInGroup) {
						this.wrap.applyStyles({
									'padding-top' : '2px'
								});
					}
					if (this.checked) {
						this.setChecked(true);
					} else {
						this.checked = this.el.dom.checked;
					}
					if (this.radios && this.radios instanceof Array) {
						this.els = new Array();
						this.els[0] = this.el;
						for (var i = 1; i < this.radios.length; i++) {
							var r = this.radios[i];
							this.els[i] = new Ext.ux.RadioGroupEx({
										renderTo : this.wrap,
										hideLabel : true,
										boxLabel : r.boxLabel,
										checked : r.checked || false,
										value : r.value,
										name : this.name || this.id,
										readOnly : r.readOnly || false,
										disabled : r.disabled || false,
										tabIndex : r.tabIndex,
										cls : r.cls,
										listeners : r.listeners,
										style : r.style,
										bodyStyle : r.bodyStyle,
										hideParent : r.hideParent,
										hidden : r.hidden,
										isInGroup : true
									});
							if (this.horizontal) {
								this.els[i].el.up('div.x-form-check-wrap')
										.applyStyles({
													'display' : 'inline',
													'padding-left' : '5px'
												});
							}
						}
						if (this.hidden)
							this.hide();
					}
				},

				initValue : function() {
					if (this.value !== undefined) {
						this.el.dom.value = this.value;
					} else if (this.el.dom.value.length > 0) {
						this.value = this.el.dom.value;
					}
				},

				// private
				onDestroy : function() {
					if (this.radios && this.radios instanceof Array) {
						var cnt = this.radios.length;
						for (var x = 1; x < cnt; x++) {
							if (typeof(this.els) != 'undefined') {
								this.els[x].destroy();
							}
						}
					}
					if (this.wrap) {
						this.wrap.remove();
					}
					Ext.ux.RadioGroupEx.superclass.onDestroy.call(this);
				},

				setChecked : function(v) {
					if (this.el && this.el.dom) {
						var fire = false;
						if (v != this.checked)
							fire = true;
						this.checked = v;
						this.el.dom.checked = this.checked;
						this.el.dom.defaultChecked = this.checked;
						if (fire)
							this.fireEvent("check", this, this.checked);
					}
				},
				/**
				 * Returns the value of the checked radio.
				 * 
				 * @return {Mixed} value
				 */
				getValue : function() {
					if (!this.rendered) {
						return this.value;
					}
					var p = this.el.up('form');// restrict to the form if it is
					// in a
					// form
					if (!p)
						p = Ext.getBody();
					var c = p.child('input[name=' + escape(this.el.dom.name)
									+ ']:checked', true);
					return (c) ? c.value : this.value;
				},

				// private
				onClick : function() {
					if (this.el.dom.checked != this.checked) {
						var p = this.el.up('form');
						if (!p)
							p = Ext.getBody();
						var els = p.select('input[name='
								+ escape(this.el.dom.name) + ']');
						els.each(function(el) {
									if (el.dom.id == this.id) {
										this.setChecked(true);
									} else {
										var e = Ext.getCmp(el.dom.id);
										e.setChecked.apply(e, [false]);
									}
								}, this);
					}
				},

				/**
				 * Checks the radio box with the matching value
				 * 
				 * @param {Mixed}
				 *            v
				 */

				setValue : function(v) {
					if (!this.rendered) {
						this.value = v;
						return;
					}
					var p = this.el.up('form');// restrict to the form if it is
					// in a
					// form
					if (!p)
						p = Ext.getBody();
					var target = p.child('input[name='
									+ escape(this.el.dom.name) + '][value=' + v
									+ ']', true);
					if (target)
						target.checked = true;
				},

				clear : function() {
					if (!this.rendered)
						return;
					var p = this.el.up('form');// restrict to the form if it is
					// in a
					// form
					if (!p)
						p = Ext.getBody();
					var c = p.child('input[name=' + escape(this.el.dom.name)
									+ ']:checked', true);
					if (c)
						c.checked = false;
				},

				disable : function() {
					if (!this.rendered)
						return;
					var p = this.el.up('form');// restrict to the form if it is
					// in a
					// form
					if (!p)
						p = Ext.getBody();
					var els = p.select('input[name=' + escape(this.el.dom.name)
							+ ']');
					els.each(function(el) {
								if (el.dom.id == this.id) {
									Ext.ux.RadioGroupEx.superclass.disable
											.call(this);
								} else {
									var e = Ext.getCmp(el.dom.id);
									Ext.ux.RadioGroupEx.superclass.disable
											.call(e);
								}
							}, this);
				},

				enable : function() {
					if (!this.rendered)
						return;
					var p = this.el.up('form');// restrict to the form if it is
					// in a
					// form
					if (!p)
						p = Ext.getBody();
					var els = p.select('input[name=' + escape(this.el.dom.name)
							+ ']');
					els.each(function(el) {
								if (el.dom.id == this.id) {
									Ext.ux.RadioGroupEx.superclass.enable
											.call(this);
								} else {
									var e = Ext.getCmp(el.dom.id);
									Ext.ux.RadioGroupEx.superclass.enable
											.call(e);
								}
							}, this);
				},

				hide : function() {
					if (!this.rendered)
						return;
					this.wrap.hide();
					this.wrap.parent().parent().hide();
				},

				show : function() {
					if (!this.rendered)
						return;
					this.wrap.show();
					this.wrap.parent().parent().show();
				}
			});
	Ext.reg('RadioGroupEx', Ext.ux.RadioGroupEx);

}

{
	Ext.form.TextField.prototype.size = 20;
	Ext.form.TextField.prototype.initValue = function() {
		if (this.value !== undefined) {
			this.setValue(this.value);
		} else if (this.el.dom.value.length > 0) {
			this.setValue(this.el.dom.value);
		}
		this.el.dom.size = this.size;
		if (!isNaN(this.maxLength) && (this.maxLength * 1) > 0
				&& (this.maxLength != Number.MAX_VALUE)) {
			this.el.dom.maxLength = this.maxLength * 1;
		}

	};
}

{
	Ext.data.CleanJsonWriter = Ext.extend(Ext.data.JsonWriter, {
				render : function(params, baseParams, data) {
					params.jsonData = data;
				},
				constructor : function(config) {
					Ext.apply(this, config);
					Ext.data.CleanJsonWriter.superclass.constructor.call(this,
							arguments);
				}
			});
}
{
	Ext.vt = function(val, field) {
		if (field.existUrl && val.length >= field.minLength) {
			var b = true;
			var filters = new Object();
			filters[field.filterName] = val;
			if (field.startValue != undefined && field.startValue.isEmpty()) {
				Ext.Ajax.request({
							async : false,
							url : field.existUrl,
							params : filters,
							success : function(response, options) {
								b = Ext.decode(response.responseText).success;
							}
						});
			} else if (field.startValue != undefined && !field.startValue.isEmpty()) {
				if (field.startValue != val) {
					Ext.Ajax.request({
								async : false,
								url : field.existUrl,
								params : filters,
								success : function(response, options) {
									b = Ext.decode(response.responseText).success;
								}
							});
				}

			}
		}
		return b;
	};
}

{
	String.prototype.isEmpty = function() {
		return /^\s*$/.test(this);
	};
}

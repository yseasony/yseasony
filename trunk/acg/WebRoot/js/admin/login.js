Ext.ns('Acg.Login');
Login = Acg.Login;
// 登录from
Login.LoginFromEx = Ext.extend(Ext.ux.FormPanelEx, {
			formSubmit : function() {
				this.getForm().submit({
					url : '../../admin/j_spring_security_check',
					success : function(loginFrom, action) {
						window.location = '../../admin/index';
					},
					failure : function(loginFrom, action) {
						if (action.failureType == 'server') {
							obj = Ext.util.JSON
									.decode(action.response.responseText);
							var r = obj.errors.reason;
							if (r == -1) {
								Ext.Msg.alert('', '登录失败！');
							} else if (r == -2) {
								Ext.Msg.alert('', '验证码错误！');
								reloadcode();
							} else {
								Ext.Msg.alert('', '服务器出现问题！');
							}
						}
					}
				})
			},
			formCancel : function() {
				this.getForm().reset();
			},
			bodyStyle : "padding:10px;",
			constructor : function(config) {
				Ext.apply(this, config);
				Login.LoginFromEx.superclass.constructor.call(this, {
							buttons : []
						});
				this.buttons[0].text = '登录';
				this.buttons[1].text = '重置';
			}
		});

Login.loginFrom = new Login.LoginFromEx({
			items : [{
						id : 'j_username',
						name : 'j_username',
						fieldLabel : '用户名',
						maxLength : 10,
						allowBlank : false,
						blankText : '请输入用户名！'
					}, {
						id : 'j_password',
						name : 'j_password',
						fieldLabel : '密　码',
						inputType : 'password',
						maxLength : 10,
						allowBlank : false,
						blankText : '请输入密码！'
					}, {
						name : 'j_captcha',
						id : 'randCode',
						value : '54321',
						fieldLabel : '验证码',
						width : 52,
						maxLength : 5,
						allowBlank : false,
						blankText : '请输入验证码！'
					}]
		});

Login.win = new Ext.ux.WindowEx({
			width : 270,
			height : 200,
			bodyStyle : "padding:10px;",
			title : '用户登录',
			closable : false,
			items : Login.loginFrom
		});

Login.reloadcode = function() {
	document.getElementById("code").src = "/admin/jcaptcha.jpg?"
			+ Math.random();
}

Ext.onReady(function() {
			Login.win.show();
			var bd = Ext.get(Ext.getDom('randCode').parentNode);
			bd.createChild({
						width : 70,
						height : 22,
						id : 'code',
						tag : 'img',
						src : '/admin/jcaptcha.jpg',
						style : 'padding-left:7px;',
						align : 'absbottom',
						onclick : "javascript:Login.reloadcode()"
					});
		});

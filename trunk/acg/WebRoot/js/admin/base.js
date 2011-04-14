Ext.ns('Acg.Common');
Common = Acg.Common;
Common.pageSize = 4;
Ext.QuickTips.init();

isGranted = function(auth) {
	if (auth == undefined)
		return true;
	else
		return auth;
};

var userInfo = new Object();
userInfo.authButtons = new Object();
Ext.Ajax.request({
			async : false,
			url : './user/userInfo',
			success : function(response) {
				userInfo = Ext.util.JSON.decode(response.responseText);
			},
			failure : function(response) {
				Ext.MessageBox.show({
							msg : Lang.msg.server_error,
							buttons : Ext.MessageBox.ERROR,
							icon : Ext.MessageBox.ERROR
						});
			}
		});


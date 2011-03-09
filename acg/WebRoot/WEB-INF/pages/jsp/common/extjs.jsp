<link rel="stylesheet" type="text/css" href="${ctx}/ext/resources/css/ext-all.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/admin/global.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/ext-patch.css" />
<script type="text/javascript" src="${ctx}/ext/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="${ctx}/ext/ext-all-debug.js"></script>
<script type="text/javascript" src="${ctx}/js/admin/ux/ext-basex.js"></script>
<script type="text/javascript" src="${ctx}/js/admin/ux/message_zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/js/admin/ux/easyTool.js"></script>
<script type="text/javascript">
	Ext.BLANK_IMAGE_URL = "${ctx}/ext/resources/images/default/s.gif";
	Ext.ns('Acg.Common');
	Common = Acg.Common;
	Common.pageSize = 20;
	Ext.QuickTips.init();
	Ext.form.Field.prototype.msgTarget = 'qtip';
</script>
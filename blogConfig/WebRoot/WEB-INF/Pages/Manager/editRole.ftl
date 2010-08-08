<#include "/Pages/ftl_lib/config.ftl"/>
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>添加角色</title>
        <script type="text/javascript" src="<@path/>/jquery.js"></script>
        <script type="text/javascript" src="<@path/>/formcheck.js"></script>
        <script type="text/javascript" src="<@path/>/jquery.pager.js"></script>
		<script type='text/javascript' src="<@path/>/createRole.js"></script>
        <script type='text/javascript' src='<@ctx/>/dwr/interface/RoleAjax.js'></script>
        <script type='text/javascript' src='<@ctx/>/dwr/engine.js'></script>
        <link href="<@ctx/>/css/form.css" rel="stylesheet" type="text/css" />
		<link href="<@ctx/>/css/pagerCreate.css" rel="stylesheet" type="text/css" />
		</head>
    <body>
    	${error?if_exists}
    	<form id="form1" name="form1" method="post" action="<@ctx/>/manage/user/saveRole.do" onsubmit="return checksave();">
        <table border="0px" width="500px" style="font-size: 12px;">
                <tr>
                    <td>角色名称:</td>
                    <td>
                        <input type="text" name="name" id="name" />
                    </td>
                    <td>
                        <div id="nameTip" class="onShow" style="width: 250px;">请输入角色名称</div>
                    </td>
                </tr>
                <tr>
                    <td>授权：</td>
                    <td>
                    <#list list as obj>
                     ${obj.displayName}<input type='checkbox' name='authorityIds' value ="${obj.id}" />
                    </#list>
                    </td>
                    <td>
                        <div id="descriptionTip" class="onShow" style="width: 250px;">请选择关联权限 </div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="submit" name="button" id="button" value="提交" />
						<input type="reset" name="button2" id="button2" value="重置" />
                    </td>
                </tr>
        </table>
		<input type="hidden" name="token" value="${token?if_exists}" />
		</form>
    </body>
</html>

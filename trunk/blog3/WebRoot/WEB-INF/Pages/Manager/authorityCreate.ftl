<#include "/Pages/ftl_lib/js_config.ftl"/> <?xml version="1.0" encoding="UTF-8"?>
 <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>添加权限</title>
        <script language="javascript" src="<@path/>/jquery.js"></script>
        <script language="javascript" src="<@path/>/formcheck.js"></script>
        <script src="<@path/>/jquery.pager.js" type="text/javascript"></script>
        <script type='text/javascript' src='/dwr/interface/AuthorityAjax.js'></script>
        <script type='text/javascript' src='/dwr/interface/ResourceAjax.js'></script>
        <script type='text/javascript' src='/dwr/engine.js'></script>
        <link href="/css/form.css" rel="stylesheet" type="text/css"></head>
		<link href="/css/pagerCreate.css" rel="stylesheet" type="text/css" />
    <body>
        <table border="0px" width="500px" style="font-size: 12px;">
            <form id="form1" name="form1" method="post" action="/user/authoritySave.do" onsubmit="return checksave();">
                <tr>
                    <td>权限名称:</td>
                    <td>
                        <input type="text" name="displayName" id="displayName" />
                    </td>
                    <td>
                        <div id="displayNameTip" class="onShow" style="width: 250px;">请输入权限名称</div>
                    </td>
                </tr>
                <tr>
                    <td>权限代码:</td>
                    <td>
                        <input type="text" name="name" id="name" />
                    </td>
                    <td>
                        <div id="nameTip" class="onShow" style="width: 250px;">请输入 权限代码</div>
                    </td>
                </tr>
                <tr>
                    <td>关联资源地址：</td>
                    <td>
                        <div id="result"></div>
                        <div id="pager"></div>
                        <span id="loading"></span>
                    </td>
                    <td>
                        <div id="descriptionTip" class="onShow" style="width: 250px;">请选择关联地址</div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="submit" name="button" id="button" value="提交" /><input type="reset" name="button2" id="button2" value="重置" />
                    </td>
                </tr><input type="hidden" name="token" value="${token}">
				<input type="hidden" name="resourceIds" id="resourceIds"></form>
        </table>
        <script language="javascript" src="<@path/>/authorityCreate.js"></script>
    </body>
</html>

<#include "/Pages/ftl_lib/config.ftl"/>
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>修改权限</title>
    <script type="text/javascript" src="<@path/>/jquery.js"></script>
    <script type="text/javascript" src="<@path/>/jquery.validate.js"></script>
    <script type="text/javascript" src="<@path/>/editAuthority.js"></script>
    <script type="text/javascript" src="<@ctx/>/dwr/interface/AuthorityAjax.js"></script>
    <script type="text/javascript" src="<@ctx/>/dwr/engine.js"></script>
    <link href="<@ctx/>/css/base.css" type="text/css" rel="stylesheet"/>
    <link href="<@ctx/>/css/jquery.validate.css" type="text/css" rel="stylesheet"/>
    <script language="javascript">
        var displayName_value = '${authority?if_exists.displayName?if_exists}';
        var name_value = '${authority?if_exists.name?if_exists}';
    </script>
</head>
    <body>
    	${error?if_exists}
    	<form id="inputForm" name="form1" method="post" action="<@ctx/>/manage/user/saveAuthority.do">
        <table border="0px" width="300px">
                <tr>
                    <td>权限名称:</td>
                    <td>
                        <input type="text" name="displayName" id="displayName" value="${authority?if_exists.displayName?if_exists}"/>
                    </td>
                </tr>
                <tr>
                    <td>权限代码:</td>
                    <td>
                       <input type="text" name="name" id="name" value="${authority?if_exists.name?if_exists}"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" name="button" id="button" value="提交" /><input type="reset" name="button2" id="button2" value="重置" />
                    </td>
                </tr>
        </table>
		<input type="hidden" name="id" value="${authority?if_exists.id?if_exists}" />
		<input type="hidden" name="token" value="${token?if_exists}" />
		</form>
    </body>
</html>

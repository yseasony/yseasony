<#include "/Pages/ftl_lib/js_config.ftl"/>
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>资源管理</title>
        <script language="javascript" src="<@path/>/jquery.js"></script>
        <script language="javascript" src="<@path/>/formcheck.js"></script>
        <script type='text/javascript' src='/dwr/interface/ResourceAjax.js'></script>
        <script type='text/javascript' src='/dwr/engine.js'></script>
        <link href="/css/form.css" rel="stylesheet" type="text/css"></head>
    <body>
        <table border="0px" width="500px" style="font-size: 12px;">
            <form id="form1" name="form1" method="post" action="/user/resourceSave.do" onsubmit="return checksave();">
                <tr>
                    <td>资源名称:</td>
                    <td>
                        <input type="text" name="resourceName" id="resourceName" />
                    </td>
                    <td>
                        <div id="resourceNameTip" class="onShow" style="width: 250px;">请输入资源名称</div>
                    </td>
                </tr>
                <tr>
                    <td>资源地址:</td>
                    <td>
                        <input type="text" name="value" id="value" />
                    </td>
                    <td>
                        <div id="valueTip" class="onShow" style="width: 250px;">请输入资源地址</div>
                    </td>
                </tr>
                <tr>
                    <td>资源类型:</td>
                    <td>
                        <input type="text" name="resourceType" id="resourceType" value="url"/>
                    </td>
                    <td>
                        <div id="resourceTypeTip" class="onShow" style="width: 250px;">请输入 资源类型</div>
                    </td>
                </tr>
                <tr>
                    <td>描述：</td>
                    <td>
                        <textarea cols="15" name="description" id="description"></textarea>
                    </td>
                    <td>
                        <div id="descriptionTip" class="onShow" style="width: 250px;">请输入描述</div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="submit" name="button" id="button" value="提交" /><input type="reset" name="button2" id="button2" value="重置" />
                    </td>
                </tr><input type="hidden" name="token" value="${token}"><input type="hidden" name="position" value="${max}"/>
            </form>
        </table>
        <script language="javascript" src="<@path/>/resourceCreate.js"></script>
    </body>
</html>

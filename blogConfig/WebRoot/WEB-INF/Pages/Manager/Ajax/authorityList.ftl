<#include "/Pages/ftl_lib/config.ftl"/>
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head> 
        <title>权限列表</title>
        <link href="<@ctx/>/css/Pager.css" rel="stylesheet" type="text/css" />
        <script src="<@path/>/jquery.js" type="text/javascript"></script>
        <script src="<@path/>/jquery.pager.js" type="text/javascript"></script>
        <script src="<@path/>/table.js" type="text/javascript"></script>
        <script type='text/javascript' src='<@ctx/>/dwr/interface/AuthorityAjax.js'></script>
        <script type='text/javascript' src='<@ctx/>/dwr/engine.js'></script>
        <script type="text/javascript" src="<@path/>/authorityList.js"></script>
    </head>
    <body>
        <table width="620">
            <tr>
                <td>
                    名称: <input type="text" name="filter_LIKES_displayName" id="filter_displayName" size="9"/>
                </td>
                <td>
                    <input class="button" type="button" value="搜索" onclick="search();"/>
                </td>
            </tr>
        </table>
        <table width="620">
            <tr>
                <th>
                    <a href="javascript:sort('id','desc')">序号</a>
                </th>
                <th>权限名</th>
                <th>操作</th>
            </tr>
            <tbody id="result"></tbody>
        </table>
        <div id="pager"></div>
        <span id="loading"></span>
    </body>
</html>

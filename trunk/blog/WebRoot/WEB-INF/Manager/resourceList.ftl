<?xml version="1.0" encoding="UTF-8"?>
 <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
    <head>
        <title>资源列表</title>
    </head>
    <body>
        <table id="contentTable">
            <tr>
                <th>
                    名称
                </th>
                <th>
                    授权
                </th>
                <th>
                    操作
                </th>
            </tr>
            <#list page.result as obj>
            <tr>
                <td>
                    ${obj.resourceType}
                </td>
                <td>
                    ${obj.value}
                </td>
                <td>
                    <security:authorize ifAnyGranted="A_VIEW_ROLE">
                        <security:authorize ifNotGranted="A_MODIFY_ROLE">
                            <a href="role!input.action?id=${obj.id}">查看</a>&nbsp;
                        </security:authorize>
                    </security:authorize>
                    <security:authorize ifAnyGranted="A_MODIFY_ROLE">
                        <a href="role!input.action?id=${obj.id}">修改</a>&nbsp;<a href="role!delete.action?id=${obj.id}">删除</a>
                    </security:authorize>
                </td>
            </tr>
            </#list>
        </table>
    </body>
</html>

<?xml version="1.0" encoding="UTF-8"?>
 <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
    <head>
        <title>资源列表</title>
        <script language="javascript" src="/js/jquery.js"></script>
        <script language="javascript" src="/js/table.js">
        </script>
    </head>
    <body>
        <form id="mainForm" action="/user/resourceList.do" method="post">
            <input type="hidden" name="pageNo" id="pageNo" value="${page.pageNo}"/>
			<input type="hidden" name="orderBy" id="orderBy" value="${page.orderBy}"/>
			<input type="hidden" name="order" id="order" value="${page.order}"/>
            <div>
                <table id="contentTable">
                    <tr>
                           <th>
                          <a href="javascript:sort('position','asc')">序号</a>
                        </th>          	
                        <th>
                            名称
                        </th>
                        <th>
                            类型
                        </th>
                        <th>
                           <a href="javascript:sort('value','asc')">地址 </a>
                        </th>
                        <th>
                            描述
                        </th>
                        <th>
                            操作
                        </th>
                    </tr>
                    <#list page.result as obj>
                    <tr>
                    	 <td>
                            ${obj.position}
                        </td>
                        <td>
                            ${obj.resourceName}
                        </td>
                        <td>
                            ${obj.resourceType}
                        </td>
                        <td>
                            ${obj.value}
                        </td>
                        <td>
                            ${obj.description}
                        </td>
                        <td>
                            <security:authorize ifAnyGranted="A_VIEW_ROLE">
                                <security:authorize ifNotGranted="A_MODIFY_ROLE">
                                    <a href="role!input.action?id=${obj.id}">查看</a>&nbsp;
                                </security:authorize>
                            </security:authorize>
                            <security:authorize ifAnyGranted="A_MODIFY_ROLE">
                                <a href="role!input.action?id=${obj.id}">修改</a>&nbsp;<a href="/user/delResource.do?resourceId=${obj.id}&position=${obj.position}">删除</a>
                            </security:authorize>
                        </td>
                    </tr>
                    </#list>
                </table>
            </div>
            <div>第${page.pageNo}页, 共${page.totalPages}页
                <a href="javascript:jumpPage(1)">首页</a>
                <#if page.hasPre><a href="javascript:jumpPage(${page.prePage})">上一页</a>
                </#if><#if page.hasNext><a href="javascript:jumpPage(${page.nextPage})">下一页</a>
                </#if><a href="javascript:jumpPage(${page.totalPages})">末页</a>
            </div>
        </form>
    </body>
</html>

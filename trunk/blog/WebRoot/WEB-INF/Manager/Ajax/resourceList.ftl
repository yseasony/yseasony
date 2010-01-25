﻿<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>资源列表</title>
        <link href="/css/Pager.css" rel="stylesheet" type="text/css" />
        <script src="/js/jquery.js" type="text/javascript">
        </script>
        <script src="/js/jquery.pager.js" type="text/javascript">
        </script>
		<script src="/js/table.js" type="text/javascript">
        </script>
        <script type="text/javascript" language="javascript">
			var orderBy = "";
			var order = "";
			var orderNO ;
            $.ajaxSetup({
                cache: false
            });
            
            var totalPages = 0;
            
            $(document).ready(function(){
                page(1);
            });
            
            PageClick = function(pageclickednumber){
                page(pageclickednumber);
            }
            
            function page(pageNo){
            	orderNO = pageNo;
                $.ajax({
                    url: "/user/resourceList.ajax",
                    data: "pageNo=" + pageNo+"&orderBy="+orderBy+"&order="+order,
                    dataType: "json",
                    success: function(json){
                        // alert(json.page.pageSize);
                        totalPages = parseInt(json.page.totalPages);
                        
                        $("#pager").pager({
                            pagenumber: pageNo,
                            pagecount: totalPages,
                            buttonClickCallback: PageClick
                        });
                        
                        var html = "";
                        
                        $.each(json.page.result, function(i, n){
                            html = html + "<tr><td>" + n.position + "</td><td>" +
                            n.resourceName +
                            "</td><td>" +
                            n.resourceType +
                            "</td><td>" +
                            n.value +
                            "</td><td>" +
                            n.description +
                            "</td><td>" +
                            "<a href=\"role!input.action?id=" +
                            n.id +
                            "\">查看</a></td></tr>";
                        });
                        
                        $("#result").html(html);
                        
                    }
                });
            }
        </script>
    </head>
    <body>
        <table width="620">
            <tr>
                <th>
                    <a href="javascript:sort('position','desc')">序号</a>
                </th>
                <th>
                    名称
                </th>
                <th>
                    类型
                </th>
                <th>
                    <a href="javascript:sort('value','desc')">地址 </a>
                </th>
                <th width="50">
                    描述
                </th>
                <th>
                    操作
                </th>
            </tr>
            <tbody id="result">
            </tbody>
        </table>
        <div id="pager">
        </div>
    </body>
</html>

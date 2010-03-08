var orderBy = "";
            var order = "";
            var orderNO;
            var url = "";
			var loading = "<img id='jquery-loading' src='/images/70.gif' alt='Loading...' />";
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
            
            function page(pageNo,search){
				$("#loading").html(loading);
                orderNO = pageNo;
				
				url = "pageNo=" + pageNo + "&orderBy=" + orderBy + "&order=" + order+
				"&filter_LIKES_resourceName="+$("#filter_resourceName").val()+
				"&filter_LIKES_value="+$("#filter_value").val();
                $.ajax({
                    url: "/user/resourceList.ajax",
                    data: url,
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
                            "<a href='/user/editResource.do?resourceId=" + n.id + "'>查看</a>　"+
							"<a href='/user/delResource.do?resourceId=" + n.id +"&position="+n.position+"'>删除</a>"+
							"</td></tr>";
                        });
                        
                        $("#result").html(html);
                        $("#loading").html("");
                    }
                });
            }
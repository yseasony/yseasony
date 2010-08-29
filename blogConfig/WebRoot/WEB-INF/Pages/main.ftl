<#assign decorator=JspTaglibs["/WEB-INF/tld/sitemesh-decorator.tld"]>
<#assign page=JspTaglibs["/WEB-INF/tld/sitemesh-page.tld"]>
<#include "ftl_lib/config.ftl"/>
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
	<head>
		<title><@decorator.title/></title>
		<link href="<@ctx/>/css/main.css" rel="stylesheet" type="text/css" />
		<@decorator.head />
        <script language="JavaScript" type="text/JavaScript">
            $(function(){
            window.onresize = adjust; 
            adjust(); 
            }); 
            var adjust=function(){
            var h=document.documentElement.clientHeight;
			var w=document.documentElement.clientWidth;
			w = w - 157;
			var cw = $("#c_pager").width();
			if(w<cw){
			w = cw;
			}
			$("#content").css("width",w);
			$("#container").css("width",w+157);
			$("#foot").css("width",w+157);
            if(h<500){
            var f = 500-h;
            h=500; 
            $("#footer").css("bottom",-f); 
            }else{ 
            $("#footer").css("bottom",0); 
            }
            $("#left").css("height",h-80); 
            } 
        </script>
	</head>
	<body>
    <div id="container">
        <div id="head">
        	<@page.applyDecorator name="header" />
        </div>
        <div id="center">
        	<div id="left">
        		<@page.applyDecorator name="left" />
        	</div>
        	<div id="content">
        		<div id="bc"></div>
				<@decorator.body />
        	</div>
			<div id="clearfloat"></div>
        </div>
        <div id="footer">
        	<@page.applyDecorator name="footer" />
        </div>
    </div>
	</body>
</html>

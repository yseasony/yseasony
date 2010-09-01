<#include "/ftl_lib/config.ftl"/>
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
    	<span id="loading"></span>
    	<div id="search" style="width:428px;">
		<div id="input"><label>权限名:</label><input type="text" name="filter_LIKES_displayName" id="filter_displayName" size="20"/></div>
		<div id="button"><input class="s_button" type="button" value="搜索" onclick="search();"/></div>
		</div>
        <div id="c_pager" style="width:828px;">
            <ul id="b_table">
                <li class="b_title" style="width:23px;">
                </li>
                <li class="b_title" style="width:200px;">
                    <a href="javascript:sort('id','desc')">ID</a>
                </li>
                <li class="b_title" style="width:300px;">
                    <label>权限名</label>
                </li>
                <li class="b_title" style="width:300px;">
                   <label>操作</label>
                </li>
                <span id="template">
					<li id="cbox" style="width:23px;"></li>
					<li id="aid" style="width:200px;"></li>
					<li id="displayName" style="width:300px;"></li>
					<li id="todo" style="width:300px;"></li>
                </span>
            </ul>
        </div>
		<div style="width:828px;" id="d_pager"><div id="pager"></div></div>
	    </body>
</html>

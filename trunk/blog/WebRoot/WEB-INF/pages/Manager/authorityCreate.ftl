<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>资源管理</title>
        <script language="javascript" src="/js/jquery.min.js">
        </script>
        <script language="javascript" src="/js/formcheck.js">
        </script>
        <link href="/css/form.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <table border="0px" width="500px" style="font-size: 12px;">
            <form id="form1" name="form1" method="post" action="/user/resourceSave.do" onsubmit="return checksave();">
                <tr>
                    <td>
                        权限名称:
                    </td>
                    <td>
                        <input type="text" name="displayName" id="displayName"  />
                    </td>
                    <td>
                        <div id="displayNameTip" class="onShow" style="width: 250px;">
                            请输入权限名称
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>
                        权限代码:
                    </td>
                    <td>
                        <input type="text" name="name" id="name"  />
                    </td>
                    <td>
                        <div id="nameTip" class="onShow" style="width: 250px;">
                            请输入 权限代码
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>
                        关联资源地址：
                    </td>
                    <td>
                        <textarea cols="15" name="description" id="description"  >
                        </textarea>
                    </td>
                    <td>
                        <div id="descriptionTip" class="onShow" style="width: 250px;">
                            请选择关联地址
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="submit" name="button" id="button" value="提交" /><input type="reset" name="button2" id="button2" value="重置" />
                    </td>
                </tr>
				<input type="hidden" name="token" value="${token}">
                <input type="hidden" name="position" value="${max}"/>
            </form>
        </table>
        <script type="text/javascript">
            Boolean: check = false;
            $.ajaxSetup({
                cache: false
            });
            
            $("#displayName").focus(function(){
                onFocusCheck("displayNameTip", "权限名称不能为空");
            });
            $("#displayName").blur(function(){
                checkNull("displayNameTip", "displayName", "权限名称不能为空或含有空格");
                if (check == true) {
                    exist("displayNameTip", "displayName", "该权限名称已被创建", "该权限名称可以创建", "/resourceExist.ajax", "resourceType");
                }
            });
            
            
            $("#name").focus(function(){
                onFocusCheck("nameTip", "权限代码不能为空");
            });
            $("#name").blur(function(){
                checkNull("nameTip", "name", "权限代码不能为空或含有空格");
                if (check == true) {
                    exist("nameTip", "name", "该权限代码已被创建", "该权限代码可以创建", "/resourceExist.ajax", "resourceType");
                }
            });
            
            function checksave(){
                checkNull("displayNameTip", "displayName", "权限名称不能为空或含有空格");
                if (check == true) {
                    exist("displayNameTip", "displayName", "该权限名称已被创建", "该权限名称可以创建", "/resourceExist.ajax", "resourceType");
                }
				
				checkNull("nameTip", "name", "权限代码不能为空或含有空格");
                if (check == true) {
                    exist("nameTip", "name", "该权限代码已被创建", "该权限代码可以创建", "/resourceExist.ajax", "resourceType");
                }
				
                if (check == true) {
                    return true;
                }
                else {
                    return false;
                }
            }
        </script>
    </body>
</html>

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
                        <input type="text" name="name" id="name"  />
                    </td>
                    <td>
                        <div id="nameTip" class="onShow" style="width: 250px;">
                            请输入权限名称
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>
                        权限代码:
                    </td>
                    <td>
                        <input type="text" name="value" id="value"  />
                    </td>
                    <td>
                        <div id="valueTip" class="onShow" style="width: 250px;">
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
            
            $("#name").focus(function(){
                onFocusCheck("nameTip", "资源地址不能为空");
            });
            $("#value").blur(function(){
                checkNull("valueTip", "name", "资源地址不能为空或含有空格");
                if (check == true) {
                    exist("valueTip", "name", "该资源地址已被创建", "该资源地址可以创建", "/resourceExist.ajax", "resourceType");
                }
            });
            
            
            $("#resourceType").blur(function(){
                checkNull("resourceTypeTip", "resourceType", "资源类型不能为空或含有空格");
                if (check == true) {
                    onBlurCheck("resourceTypeTip", "　");
                }
            });
            
            function checksave(){
                checkNull("valueTip", "value", "资源地址不能为空或含有空格");
                if (check == true) {
                    exist("valueTip", "value", "该资源地址已被创建", "该资源地址可以创建", "/resourceExist.ajax", "resourceType");
                }
				
				if (check == true) {
					checkNull("resourceNameTip", "resourceName", "资源名称不能为空或含有空格");
                }
				
                if (check == true) {
                    checkNull("resourceTypeTip", "resourceType", "资源类型不能为空或含有空格");
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

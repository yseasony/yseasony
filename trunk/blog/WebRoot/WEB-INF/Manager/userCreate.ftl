<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>无标题文档</title>
        <script language="javascript" src="../../js/jquery-1.3.2.js">
        </script>
        <script language="javascript" src="../../js/formcheck.js">
        </script>
        <link href="../../css/form.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <table border="0px" width="280px" style="font-size: 12px;">
            <form id="form1" name="form1" method="post" action="/user/userSave.do" onsubmit="return checksave();">
                <tr>
                    <td>
                        用户名:
                    </td>
                    <td>
                        <input type="text" name="loginName" id="loginName"/>
                    </td>
                    <td>
                        <div id="loginNameTip" class="onShow" style="width: 250px;">
                            请输入 用户名
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>
                        姓　名:
                    </td>
                    <td>
                        <input type="text" name="name" id="name" />
                    </td>
                    <td>
                        <div id="nameTip" class="onShow" style="width: 250px;">
                            请输入 姓名
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>
                        E-mail:
                    </td>
                    <td>
                        <input type="text" name="email" id="email" />
                    </td>
                    <td>
                        <div id="emailTip" class="onShow" style="width: 250px;">
                            请输入 E-mail
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>
                        密　码:
                    </td>
                    <td>
                        <input type="text" name="password" id="password" />
                    </td>
                    <td>
                        <div id="passwordTip" class="onShow" style="width: 250px;">
                            请输入 密码
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>
                        排　序:
                    </td>
                    <td>
                        <input type="text" name="displayorder" value="${max}" id="displayorder" readonly="readonly"/>
                    </td>
                    <td>
                        <div id="displayorderTip" class="onShow" style="width: 250px;">
                            此排序自动生成请不要更改
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>
                        状　态:
                    </td>
                    <td>
                        启用<input name="status" type="radio" id="status" value="1" checked="checked" />停用<input type="radio" name="status" id="status" value="0" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="submit" name="button" id="button" value="提交" /><input type="reset" name="button2" id="button2" value="重置" />
                    </td>
                </tr>
            </form>
        </table>
        <script type="text/javascript">
            Boolean: check = true;
            $.ajaxSetup({
                cache: false
            });
            
            $("#loginName").focus(function(){
                onFocusCheck("loginNameTip", "登录名至少6个字符,最多10个字符");
            });
            
            $("#loginName").blur(function(){
                checkNull("loginNameTip", "loginName", "用户名不能为空或含有空格");
                if (check == true) {
                    between(6, 10, "loginName", "loginNameTip");
                }
                if (check == true) {
                    exist("loginNameTip", "loginName", "该用户名已被使用", "该用户名可以注册", "/userExist.ajax", "loginname");
                }
            });
            
            $("#name").focus(function(){
                onFocusCheck("nameTip", "姓名不能为空");
            });
            $("#name").blur(function(){
                checkNull("nameTip", "name", "姓名不能为空或含有空格");
            });
			$("#email").blur(function(){
               $("#emailTip").get(0).className="onCorrect";
			   $("#emailTip").get(0).innerHTML ="　";
            });
			$("#password").focus(function(){
                onFocusCheck("passwordTip", "密码不能为空");
            });
            $("#password").blur(function(){
                checkNull("passwordTip", "password", "密码不能为空或含有空格");
            });
            
            function onfocuscheck(theclass, text){
                $("#" + theclass + "").get(0).className = "onFocus";
                $("#" + theclass + "").get(0).innerHTML = text;
            }
            
            function checksave(){
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

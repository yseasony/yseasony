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
        <table border="0px" width="500px" style="font-size: 12px;">
            <form id="form1" name="form1" method="post" action="/user/resourceSave.do" onsubmit="return checksave();">
                <tr>
                    <td>
                        资源地址:
                    </td>
                    <td>
                        <input type="text" name="value" id="value" onfocus="onfocuscheck('valueTip','资源地址不能为空');" onblur="exist('valueTip',this.id);"/>
                    </td>
                    <td>
                        <div id="valueTip" class="onShow" style="width: 250px;">
                            请输入资源地址
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>
                        资源类型:
                    </td>
                    <td>
                        <input type="text" name="resourceType" id="resourceType" value="url" onfocus="onfocuscheck('resourceTypeTip','资源类型不能为空');" onblur="onblurcheck('resourceTypeTip',this.id,'')"/>
                    </td>
                    <td>
                        <div id="resourceTypeTip" class="onShow" style="width: 250px;">
                            请输入 资源类型
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
            function exist(theclass, id){
                if (isNull(id)) {
                    $("#" + theclass).get(0).className = "onError";
                    $("#" + theclass).get(0).innerHTML = "资源地址不能为空或含有空格";
                    check = false;
                    return;
                }
                
                String: resourceType = $("#" + id + "").get(0).value;
                $.getJSON("/resourceExist.ajax", {
                    "resourceType": resourceType
                }, function(json){
                    if (json.exist == "false") {
                        $("#" + theclass).get(0).className = "onError";
                        $("#" + theclass).get(0).innerHTML = "该资源地址已被创建";
                        check = false;
                    }
                    else {
                        $("#" + theclass).get(0).className = "onCorrect";
                        $("#" + theclass).get(0).innerHTML = "该资源地址可以创建";
                        check = true;
                    }
                });
            }
            
            function onfocuscheck(theclass, text){
            
                $("#" + theclass).get(0).className = "onFocus";
                $("#" + theclass).get(0).innerHTML = text;
            }
            
            function onblurcheck(theclass, id, text){
            
                if (isNull(id)) {
                    $("#" + theclass).get(0).className = "onError";
                    $("#" + theclass).get(0).innerHTML = "不能为空或含有空格";
                    check = false;
                    return;
                }
                else {
                    $("#" + theclass).get(0).className = "onCorrect";
                    $("#" + theclass).get(0).innerHTML = "&nbsp";
                    check = true;
                }
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

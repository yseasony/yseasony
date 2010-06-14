<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
 <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <title>My JSP 'index.jsp' starting page</title>
        <script src="/js/jquery.js" type="text/javascript">
        </script>
        <script>
            
            function test(){
                $.ajax({
                    type: "POST",
                    url: "http://127.0.0.1:8080/services/restful/updatecategory",
                    data: "a=a&b=b",
                    dataType: "text",
                    success: function(json){
                        alert(json);
                    }
                });
            }
        </script>
    </head>
    <body>
        <form action="/services/restful/updatecategory" method="post">
            <input type="text" name="a">
            <button type="submit">
                1
            </button>
        </form>
        <button type="button" onclick="test();">
            OK
        </button>
        
        <div style="width:100%; border:solid 1px #0000ff; line-height:25px">
<input type="checkbox" name="cbType1" id="cbType1" value="1" checked/>1&nbsp;&nbsp;&nbsp;&nbsp;
<input type="checkbox" name="cbType1" id="cbType2" value="2" />2&nbsp;&nbsp;&nbsp;&nbsp;
<input type="checkbox" name="cbType1" id="cbType3" value="3" checked />3&nbsp;&nbsp;&nbsp;&nbsp;
<input type="checkbox" name="cbType1" id="cbType4" value="4" checked />4
<br/>
<input id="bt_checkbox" type="button" onclick="test2();" value="多选框测试">
<script type="text/javascript">
       function test2(){
                    var aa="";
                    $("input[name='cbType1'][@checked]").each(function(){
                                aa+=$(this).val()+",";
                    })
                    alert(aa);
        }
</script>           
</div>
    </body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
 <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <title>My JSP 'index.jsp' starting page</title>
        <script src="/js/jquery.min.js" type="text/javascript">
        </script>
        <script>
            
            function test(){
                $.ajax({
                    type: "POST",
                    url: "http://127.0.0.1:8080/services/restful/updatecategory",
                    data: "a=a",
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
    </body>
</html>

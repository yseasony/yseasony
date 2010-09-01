<#include "/Pages/ftl_lib/config.ftl"/> 
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
    <head>
        <title>登录</title>
        <style type="text/css">
        <!--body {
            margin-left: 0px;
            margin-top: 0px;
            margin-right: 0px;
            margin-bottom: 0px;
            background-color: #1D3647;
        }
        -->
        </style>
        <link href="<@ctx/>/css/login.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr>
                <td height="42" valign="top">
                    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="login_top_bg" >
                        <tr>
                            <td width="1%" height="21">&nbsp;</td>
                            <td height="42">&nbsp;</td>
                            <td width="17%">&nbsp;</td>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr>
                <td valign="top">
                	<div align="center" class="login_bg">
                    <table width="1024px" border="0" cellpadding="0" cellspacing="0" >
                        <tr>
                            <td width="49%" align="right">
                                <table width="91%" border="0" cellpadding="0" cellspacing="0" class="login_bg2" >
                                    <tr>
                                        <td height="138" valign="top">
                                            <table width="89%" border="0" cellpadding="0" cellspacing="0">
                                                <tr>
                                                    <td height="149">&nbsp;</td>
                                                </tr>
                                                <tr>
                                                    <td height="60" align="right" valign="top">&nbsp;</td>
                                                </tr>
                                                <tr>
                                                    <td height="208" align="right" valign="top">
                                                        <img src="<@ctx/>/images/logo.png" width="279" height="68" alt=""/></td>
                                                </tr>
                                            </table>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                            <td width="1%">&nbsp;</td>
                            <td width="50%" valign="bottom">
                                <table width="100%" border="0" cellpadding="0" cellspacing="0">
                                    <tr>
                                        <td width="4%">&nbsp;</td>
                                        <td width="95%" height="38" align="left">
                                            <span class="login_txt_bt">登陆BLOG后台管理</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="4%">&nbsp;</td>
                                        <td width="95%">
                                            <table width="100%" border="0">
                                                <tr>
                                                    <td height="164" colspan="2">
                                                        <form name="myform" action="index.html" method="post">
                                                            <table width="100%" border="0" id="table212">
                                                                <tr>
                                                                    <td width="13%" height="38" class="top_hui_text">
                                                                        <span class="login_txt">管理员：&nbsp;&nbsp; </span>
                                                                    </td>
                                                                    <td height="38" colspan="2" class="top_hui_text" align="left">
                                                                        <input name="username" class="editbox4" value="" size="20" />                            </td>
                                                                </tr>
                                                                <tr>
                                                                    <td width="13%" height="35" class="top_hui_text">
                                                                        <span class="login_txt"> 密 码： &nbsp;&nbsp; </span>
                                                                    </td>
                                                                    <td height="35" colspan="2" class="top_hui_text" align="left">
                                                                        <input class="editbox4" type="password" size="20" name="password" /><img src="<@ctx/>/images/luck.gif" width="19" height="18" alt=""/> </td>
                                                                </tr>
                                                                <tr>
                                                                    <td width="13%" height="35">
                                                                        <span class="login_txt">验证码：</span>
                                                                    </td>
                                                                    <td height="35" colspan="2" class="top_hui_text" align="left">
                                                                        <input class="wenbenkuang" name="verifycode" type="text" value="" maxlength="4" size="10" /></td>
                                                                </tr>
                                                                <tr>
                                                                    <td height="35">&nbsp;</td>
                                                                    <td width="20%" height="35" align="left">
                                                                        <input name="Submit" type="submit" class="button" id="Submit" value="登 陆"/> </td>
                                                                    <td width="67%" class="top_hui_text" align="left">
                                                                        <input name="cs" type="button" class="button" id="cs" value="取 消"/></td>
                                                                </tr>
                                                            </table>
                                                          </form>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td width="433" height="164" align="right" valign="bottom"></td>
                                                    <td width="57" align="right" valign="bottom">&nbsp;</td>
                                                </tr>
                                            </table>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
					</div>
                </td>
            </tr>
            <tr>
                <td height="20">
                    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="login-buttom-bg">
                        <tr>
                            <td align="center">
                                <span class="login-buttom-txt">Copyright &copy; 2010-2011 YseasonY</span>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
    </body>
</html>

<?xml version="1.0" encoding="UTF-8" ?>
<%@ include file="../common/taglibs.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Spring MVC</title>
<%@ include file="../common/extjs.jsp"%>
    <script language="javascript">
        Ext.onReady(function() {

            var store = [
                [1, '选项一'],
                [2, '选项二'],
                [3, '选项三'],
                [4, '选项四'],
                [5, '选项五'],
                [6, '选项六'],
                [7, '选项七'],
                [8, '选项八']
            ];

            var form = new Ext.FormPanel({
                title: '单选/多选下拉列表',
                renderTo: 'form',
                frame: true,
                bodyStyle: 'padding:5px',
                items: [
                    { xtype: 'select', fieldLabel: '单选下拉', name: 's1', store: store, value: 1 },
                    { xtype: 'multiselect', fieldLabel: '多选下拉', name: 's2', store: store, value: [2, 5, 4] },
                    { xtype: 'multiselect', fieldLabel: '多选下拉', name: 's2', store: store, listWidth: 300}
                ]
            });


        }, this, { delay: 200 });
    </script>
</head>
<body>
<div id="form"></div>

</body>
</html>
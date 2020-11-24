<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>电子商务平台——后台登录页</title>
<!-- 引入EasyUI的相关css和js文件 -->
<link href="EasyUI/themes/default/easyui.css" rel="stylesheet"
	type="text/css" />
<link href="EasyUI/themes/icon.css" rel="stylesheet" type="text/css" />
<link href="EasyUI/demo.css" rel="stylesheet" type="text/css" />
<script src="EasyUI/jquery.min.js" type="text/javascript"></script>
<script src="EasyUI/jquery.easyui.min.js" type="text/javascript"></script>
<script src="EasyUI/easyui-lang-zh_CN.js" type="text/javascript"></script>
</head>

<body>
<div id="cc" class="easyui-layout" style="min-width:600px;min-height:400px; height: 100%">
	<div data-options="region:'center',title:'登录页面'" style="padding:5px;background:#eee;">
		<div id="dlg" class="easyui-dialog" title="Basic Dialog" data-options="iconCls:'icon-save'" style="width:400px;height:300px;padding:10px">
			<form id="ff" method="post">
                <div style="margin-bottom:10px">
                    <input name="username" class="easyui-textbox" style="width:100%;height:40px;padding:12px" data-options="prompt:'Username',iconCls:'icon-man',iconWidth:38">
                </div>
                <div style="margin-bottom:20px">
                    <input name="pwd" class="easyui-textbox" type="password" style="width:100%;height:40px;padding:12px" data-options="prompt:'Password',iconCls:'icon-lock',iconWidth:38">
                </div>

			<div style="margin-bottom:20px">
				<input type="checkbox" checked="checked">
				<span>Remember me</span>
			</div>

			<div>
				<a onclick="toLogin()" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" style="padding:5px 0px;width:100%;">
					<span style="font-size:14px;">Login</span>
				</a>
			</div>
			<div>
				<a onclick="clearForm()" href="#" class="easyui-linkbutton onclear" data-options="iconCls:'icon-reload'" style="padding:5px 0px;width:100%;margin-top:10px">
					<span style="font-size:14px;">重置</span>
				</a>
			</div>
			</form>
		</div>
		<div style="margin:20px 0;">
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="$('#dlg').dialog('open')">Open</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="$('#dlg').dialog('close')">Close</a>
		</div>
	</div>
</div>

<script>
	function toLogin(){
		$("#ff").form("submit", {
			// 向控制器类AdminInfoController中login方法发送请求
			url : 'admin/toLogin',
			success : function(result) {
				if("ok" === result) window.location.href = "admin.jsp"
				else $.messager.alert('Warning',"用户名或密码错误");
			}
		});
	}
	function clearForm() {
		$('.easyui-linkbutton .onclear').form('clear');
	}
</script>
</body>
</html>
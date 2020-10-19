<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>写博客页面</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/demo.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>

	<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/static/ueditor1_4_3_3/ueditor.config.js"></script>
	<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/static/ueditor1_4_3_3/ueditor.all.min.js"></script>
	<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/static/ueditor1_4_3_3/lang/zh-cn/zh-cn.js"></script>

	<script type="text/javascript">
        //提交博主信息
        function submitData() {
            var nickName=$("#nickName").val();
            var sign=$("#sign").val();
            var proFile = UE.getEditor("editor").getContent();
            if(nickName==null || nickName==""){
                $.messager.alert("系统提示","请输入昵称");
            }else if (sign==null || sign==""){
                $.messager.alert("系统提示","请输入个性签名");
            }else if (proFile==null || proFile==""){
                $.messager.alert("系统提示","请输入简介");
            }else{
                $("#profile").val(proFile);
                $("#form1").submit();
            }

        }
	</script>
</head>

<body style="margin: 10px; font-family: microsoft yahei">

<div id="p" class="easyui-panel" title="修改个人信息" style="padding: 10px;">
	<form id="form1" action="${pageContext.request.contextPath}/admin/blogger/save.do" method="post" enctype="multipart/form-data">
		<input type="hidden" id="id" name="id" value="${currentUser.id }"/>
		<input type="hidden" id="profile" name="profile" value="${currentUser.profile}"/>
		<table cellspacing="20px">
			<tr>
				<td width="80px">用户名：</td>
				<td>

					<input type="text" id="userName" name="userName" style="width:200px" readonly="readonly" value="${currentUser.userName}"/>
				</td>
			</tr>
			<tr>
				<td>昵称：</td>
				<td>
					<input type="text" id="nickName" name="nickName" style="width:200px" value="${currentUser.nickName}"
						   class="easyui-validatebox" required="true"/>
				</td>
			</tr>
			<tr>
				<td>个性签名：</td>
				<td>
					<input type="text" id="sign" name="sign" style="width:400px" value="${currentUser.sign}"
						   class="easyui-validatebox" required="true"/>
				</td>
			</tr>
			<tr>
				<td>个人头像：</td>
				<td>
					<input type="file" id="imageFile" name="imageFile"/>
				</td>
			</tr>
			<tr>
				<td>个人简介：</td>
				<td>
					<script id="editor" type="text/plain" style="width:80%; height:500px;"></script>
				</td>
			</tr>

			<tr>
				<td></td>
				<td><a href="javascript:submitData()" class="easyui-linkbutton"data-options="iconCls:'icon-submit'">提交</a></td>
			</tr>
		</table>
	</form>
</div>

<%-- 实例化编辑器 --%>
<script type="text/javascript">
var ue = UE.getEditor("editor");
ue.addListener("ready", function(){
	//通过UE自己封装的ajax请求数据
	//
	UE.ajax.request("${pageContext.request.contextPath}/admin/blogger/find.do",{
		method:"post",
		async:false,
		data:{},
		onsuccess:function (result) {
			result = eval("("+result.responseText+")");
            UE.getEditor("editor").setContent(result.profile);
        }
	})
});
</script>

</body>
</html>

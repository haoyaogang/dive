<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="jb.model.TdiveAddress"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="jb" uri="http://www.jb.cn/jbtag"%>  
<script type="text/javascript">
	var editor;
	$(function() {
		window.setTimeout(function() {
			editor = KindEditor.create('#description', {
				width : '580px',
				height : '300px',
				items : [ 'source', '|', 'undo', 'redo', '|', 'preview', 'print', 'template', 'code', 'cut', 'copy', 'paste', 'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright', 'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript', 'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/', 'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image', 'flash', 'media', 'insertfile', 'table', 'hr', 'emoticons', 'baidumap', 'pagebreak', 'anchor', 'link', 'unlink' ],
				uploadJson : '${pageContext.request.contextPath}/fileController/upload',
				fileManagerJson : '${pageContext.request.contextPath}/fileController/fileManage',
				allowFileManager : true
			});
		}, 1);
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${pageContext.request.contextPath}/diveAddressController/edit',
			onSubmit : function() {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
				var isValid = $(this).form('validate');
				if (!isValid) {
					parent.$.messager.progress('close');
				}
				editor.sync();
				return isValid;
			},
			success : function(result) {
				parent.$.messager.progress('close');
				result = $.parseJSON(result);
				if (result.success) {
					parent.$.modalDialog.openner_dataGrid.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_dataGrid这个对象，是因为user.jsp页面预定义好了
					parent.$.modalDialog.handler.dialog('close');
				} else {
					parent.$.messager.alert('错误', result.msg, 'error');
				}
			}
		});
		
		function ProcessFile() {
			var file = document.getElementById('iconFile').files[0];
			if (file) {
				var reader = new FileReader();
				reader.onload = function ( event ) {
					var txt = event.target.result;
					$('.img-preview').attr('src',txt);
					$('#icon').val(txt);
				};
			}
		    reader.readAsDataURL(file);
		}
		$(document).delegate('#iconFile','change',function () {
			ProcessFile();
		});
	});
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title=""
		style="overflow: auto;">
		<form id="form" method="post">
			<input type="hidden" name="id" value="${diveAddress.id}" />
			<table class="table table-hover table-condensed">
				<tr>
					<th><%=TdiveAddress.ALIAS_NAME%></th>
					<td><input class="span2" name="name" type="text" class="span2"
						value="${diveAddress.name}" /></td>
					<th><%=TdiveAddress.ALIAS_STATUS%></th>
					<td>
						<jb:select dataType="ST" name="status"  value="${diveAddress.status}"></jb:select>		
					</td>
				</tr>
				<tr>
					<th><%=TdiveAddress.ALIAS_ICON%></th>
					<td colspan="3">
						<input name="icon" id="icon" type="hidden" value="${diveAddress.icon}"> 
						<img class="img-preview" src="${diveAddress.icon}" width="50" height="50"/> 
						<input type="file" id="iconFile">
					</td>
				</tr>
				<tr>
					<th><%=TdiveAddress.ALIAS_AREA%></th>
					<td>
						<jb:select dataType="AR" name="area" value="${diveAddress.area}"></jb:select>
					</td>
					<th><%=TdiveAddress.ALIAS_FEATURE%></th>
					<td>
						<jb:select dataType="FT" name="feature" value="${diveAddress.feature}"></jb:select>
					</td>
				</tr>
				<tr>
					<th><%=TdiveAddress.ALIAS_SUMARY%></th>
					<td colspan="3">
						<textarea style="width: 500px;" name="sumary">${diveAddress.sumary}</textarea>
					</td>
				</tr>
				<tr>
					<th><%=TdiveAddress.ALIAS_DESCRIPTION%></th>
					<td colspan="3">
						<textarea  name="description" id="description" style="height:180px;visibility:hidden;">${diveAddress.description}</textarea>
					</td>	
				</tr>
			</table>
		</form>
	</div>
</div>
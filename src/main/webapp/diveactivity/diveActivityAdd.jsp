<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jb.model.TdiveActivity" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="jb" uri="http://www.jb.cn/jbtag"%>
<script type="text/javascript">
	var editor;
	$(function() {
		window.setTimeout(function() {
			editor = KindEditor.create('#introduce', {
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
			url : '${pageContext.request.contextPath}/diveActivityController/add',
			onSubmit : function() {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
				var isValid = $(this).form('validate');
				if (!isValid) {
					parent.$.messager.progress('close');
				}
				/* var businessId;
				if($("input:radio[name=businessType]:checked").val() == 'BT01') {
					businessId = $('[name=travelId]').val();
				} else {
					businessId = $('[name=storeId]').val();
				}
				$("#businessId").val(businessId); */
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
		
		/* $("input:radio[name=businessType]").bind('click', function(){
			if($(this).val() == 'BT01') {
				$("#travel").show();
				$("#store").hide();
			} else {
				$("#travel").hide();
				$("#store").show();
			}
		}); */
		
		function ProcessFile() {
			var file = document.getElementById('iconFile').files[0];
			if (file) {
				var reader = new FileReader();
				reader.onload = function ( event ) {
					var txt = event.target.result;
					$('.img-preview').attr('src',txt);
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
	<div data-options="region:'center',border:false" title="" style="overflow: auto;">	
		<form id="form" method="post" enctype="multipart/form-data">		
				<input type="hidden" name="id"/>
			<table class="table table-hover table-condensed">
				<tr>	
					<th width="10%"><%=TdiveActivity.ALIAS_NAME%></th>	
					<td width="40%">
					<input class="span2" name="name" type="text" class="span2"/>
					</td>	
					<th width="10%"><%=TdiveActivity.ALIAS_ROOM_TYPE%></th>	
					<td width="40%">
						<jb:select dataType="RT" name="roomType"></jb:select>	
					</td>													
				</tr>
				<tr>
					<th><%=TdiveActivity.ALIAS_ICON%></th>
					<td colspan="3">
						<img class="img-preview" src="" width="50" height="50"/> 
						<input type="file" id="iconFile" name="iconFile">
					</td>
				</tr>	
				<!-- 	
				<tr>	
					<th><%=TdiveActivity.ALIAS_BUSINESS_TYPE%></th>	
					<td>
						<input type="radio" name="businessType" value="BT01" checked="checked"/> 船宿
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="businessType" value="BT05" /> 度假村
					</td>	
					<th>选择业务</th>	
					<td>
						<div id="travel">
							<jb:selectSql dataType="SL003" name="travelId"></jb:selectSql>
						</div>
						<div id="store" style="display: none;">
							<jb:selectSql dataType="SL004" name="storeId"></jb:selectSql>
						</div>
						<input type="hidden" name="businessId" id="businessId"/>
					</td>	
																	
				</tr> -->
				<tr>
					<th>热门值</th>
					<td colspan="3">
						<input class="span2" name="hot" type="text" />（值越大排序越靠前）
					</td>
				</tr>	
				<tr>	
					<th><%=TdiveActivity.ALIAS_DIVER_PRICE%></th>	
					<td>
						<input class="span2" name="diverPrice" type="text"/>
					</td>	
					<th><%=TdiveActivity.ALIAS_NON_DRIVE_PRICE%></th>	
					<td>
						<input class="span2" name="nonDrivePrice" type="text"/>
					</td>													
				</tr>	
				<tr>	
					<th><%=TdiveActivity.ALIAS_SINGLE_ROOM_PRICE%></th>	
					<td>
						<input class="span2" name="singleRoomPrice" type="text"/>
					</td>	
					<th><%=TdiveActivity.ALIAS_PEER_NAME%></th>	
					<td>
						<input class="span2" name="peerName" type="text"/>
					</td>													
				</tr>	
				<tr>	
					<th><%=TdiveActivity.ALIAS_START_DATE%></th>	
					<td>
					<input class="span2" name="startDateStr" type="text" onclick="WdatePicker({dateFmt:'<%=TdiveActivity.FORMAT_START_DATE%>'})"  maxlength="0" class="" />
					</td>	
					<th><%=TdiveActivity.ALIAS_END_DATE%></th>	
					<td>
					<input class="span2" name="endDateStr" type="text" onclick="WdatePicker({dateFmt:'<%=TdiveActivity.FORMAT_END_DATE%>'})"  maxlength="0" class="" />
					</td>							
				</tr>	
				<tr>	
					<th><%=TdiveActivity.ALIAS_START_ADDR%></th>	
					<td colspan="3">
						<input class="span2" name="startAddr" type="text" style="width: 500px;"/>
					</td>	
				</tr>	
				<tr>	
					<th><%=TdiveActivity.ALIAS_END_ADDR%></th>	
					<td colspan="3">
						<input class="span2" name="endAddr" type="text"  style="width: 500px;"/>
					</td>								
				</tr>	
				<tr>	
					<th><%=TdiveActivity.ALIAS_DESCRIPTION%></th>	
					<td colspan="3">
						<textarea style="width: 500px;" name="description"></textarea>
					</td>							
				</tr>	
				<tr>
					<th><%=TdiveActivity.ALIAS_INTRODUCE%></th>
					<td colspan="3">
						<textarea  name="introduce" id="introduce" style="height:180px;visibility:hidden;"></textarea>
					</td>	
				</tr>
			</table>		
		</form>
	</div>
</div>
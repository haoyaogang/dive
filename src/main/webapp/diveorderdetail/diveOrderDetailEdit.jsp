<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jb.model.TdiveOrderDetail" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="jb" uri="http://www.jb.cn/jbtag"%> 
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${pageContext.request.contextPath}/diveOrderDetailController/edit',
			onSubmit : function() {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
				var isValid = $(this).form('validate');
				if (!isValid) {
					parent.$.messager.progress('close');
				}
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
	});
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;">
		<form id="form" method="post">
				<input type="hidden" name="id" value = "${diveOrderDetail.id}"/>
			<table class="table table-hover table-condensed">
				<tr>	
					<th><%=TdiveOrderDetail.ALIAS_ORDER_ID%></th>	
					<td>
											<input class="span2" name="orderId" type="text" value="${diveOrderDetail.orderId}"/>
					</td>							
					<th><%=TdiveOrderDetail.ALIAS_BUSINESS_ID%></th>	
					<td>
											<input class="span2" name="businessId" type="text" value="${diveOrderDetail.businessId}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TdiveOrderDetail.ALIAS_BUSINESS_TYPE%></th>	
					<td>
											<jb:select dataType="BT" name="businessType" value="${diveOrderDetail.businessType}"></jb:select>	
					</td>							
					<th><%=TdiveOrderDetail.ALIAS_NUMBER%></th>	
					<td>
											<input class="span2" name="number" type="text" value="${diveOrderDetail.number}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TdiveOrderDetail.ALIAS_PRICE%></th>	
					<td>
											<input class="span2" name="price" type="text" value="${diveOrderDetail.price}"/>
					</td>							
			</tr>	
			</table>				
		</form>
	</div>
</div>
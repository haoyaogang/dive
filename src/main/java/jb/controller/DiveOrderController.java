﻿package jb.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jb.pageModel.Colum;
import jb.pageModel.DiveAccount;
import jb.pageModel.DiveOrder;
import jb.pageModel.DataGrid;
import jb.pageModel.Json;
import jb.pageModel.PageHelper;
import jb.pageModel.SessionInfo;
import jb.service.DiveAccountServiceI;
import jb.service.DiveOrderDetailServiceI;
import jb.service.DiveOrderServiceI;
import jb.util.ConfigUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * DiveOrder管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/diveOrderController")
public class DiveOrderController extends BaseController {

	@Autowired
	private DiveOrderServiceI diveOrderService;
	
	@Autowired
	private DiveOrderDetailServiceI diveOrderDetailService;
	
	@Autowired
	private DiveAccountServiceI accountService;


	/**
	 * 跳转到DiveOrder管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/diveorder/diveOrder";
	}

	/**
	 * 获取DiveOrder数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(DiveOrder diveOrder, PageHelper ph, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo)session.getAttribute(ConfigUtil.getSessionInfoName());
		if(checkRoleMark("RL01", sessionInfo)) {
			diveOrder.setAddUserId_travel(sessionInfo.getId());
		}
		if(checkRoleMark("RL03", sessionInfo)) {
			diveOrder.setAddUserId_equip(sessionInfo.getId());
		}
		
		return diveOrderService.dataGridComplex(diveOrder, ph);
	}
	/**
	 * 获取DiveOrder数据表格excel
	 * 
	 * @param user
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws IOException 
	 */
	@RequestMapping("/download")
	public void download(DiveOrder diveOrder, PageHelper ph,String downloadFields,HttpServletResponse response, HttpSession session) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		DataGrid dg = dataGrid(diveOrder,ph,session);		
		downloadFields = downloadFields.replace("&quot;", "\"");
		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
		downloadTable(colums, dg, response);
	}
	/**
	 * 跳转到添加DiveOrder页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		DiveOrder diveOrder = new DiveOrder();
		diveOrder.setId(UUID.randomUUID().toString());
		return "/diveorder/diveOrderAdd";
	}

	/**
	 * 添加DiveOrder
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(DiveOrder diveOrder) {
		Json j = new Json();		
		diveOrderService.add(diveOrder);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到DiveOrder查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, String id) {
		DiveOrder diveOrder = diveOrderService.get(id);
		if(diveOrder != null) {
			DiveAccount account = accountService.get(diveOrder.getAccountId());
			diveOrder.setUserName(account.getUserName());
			diveOrder.setNickname(account.getNickname());
			
			diveOrder.setDetail_list(diveOrderDetailService.getOrderDetail(id));
		}
		request.setAttribute("diveOrder", diveOrder);
		return "/diveorder/diveOrderView";
	}

	/**
	 * 跳转到DiveOrder修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, String id) {
		DiveOrder diveOrder = diveOrderService.get(id);
		request.setAttribute("diveOrder", diveOrder);
		return "/diveorder/diveOrderEdit";
	}

	/**
	 * 修改DiveOrder
	 * 
	 * @param diveOrder
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(DiveOrder diveOrder) {
		Json j = new Json();		
		diveOrderService.edit(diveOrder);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 删除DiveOrder
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(String id) {
		Json j = new Json();
		diveOrderService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}

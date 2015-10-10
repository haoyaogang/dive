package jb.controller;

import javax.servlet.http.HttpServletRequest;

import jb.interceptors.TokenManage;
import jb.listener.Application;
import jb.pageModel.DataGrid;
import jb.pageModel.DiveTravel;
import jb.pageModel.Json;
import jb.pageModel.PageHelper;
import jb.pageModel.SessionInfo;
import jb.service.DiveTravelServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 潜水旅游模块接口
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/api/apiTravelController")
public class ApiTravelController extends BaseController {
	
	
	@Autowired
	private TokenManage tokenManage;
		
	@Autowired
	private DiveTravelServiceI diveTravelService;
	
	
	
	/**
	 * 国家/地区列表
	 * @param ph
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/arealist")
	public Json arealist() {
		Json j = new Json();
		try{
			j.setObj(diveTravelService.getAllArea());
			j.success();
		}catch(Exception e){
			j.fail();
			e.printStackTrace();
		}		
		return j;
	}	
	
	/**
	 * 船宿列表查询
	 * @param ph
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/travellist")
	public Json travellist(PageHelper ph,DiveTravel diveTravel) {	
		Json j = new Json();
		try{
			j.setObj(diveTravelService.dataGrid(diveTravel,ph));
			j.success();
		}catch(Exception e){
			j.fail();
			e.printStackTrace();
		}		
		return j;
	}	
	
	/**
	 * 首页热门列表查询
	 * @param ph
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/travel_hot")
	public Json travel_hot(PageHelper ph) {	
		Json j = new Json();
		try{
			ph.setPage(1);
			ph.setRows(Integer.valueOf(Application.getString("SV400")));
			ph.setSort("hot desc, t.addtime");
			ph.setOrder("desc");
			j.setObj(diveTravelService.dataGrid(null,ph).getRows());
			j.success();
		}catch(Exception e){
			j.fail();
			e.printStackTrace();
		}		
		return j;
	}	
	
	/**
	 * 搜索
	 * @param ph
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/search")
	public Json search(PageHelper ph,DiveTravel diveTravel) {	
		Json j = new Json();
		try{
			diveTravel.setSearchValue(diveTravel.getName());
			diveTravel.setName(null);
			j.setObj(diveTravelService.dataGrid(diveTravel,ph));
			j.success();
		}catch(Exception e){
			j.fail();
			e.printStackTrace();
		}		
		return j;
	}
	
	/**
	 * 获取详情接口
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getTravelDetail")
	public Json getTravelDetail(String id, HttpServletRequest request) {
		Json j = new Json();
		try{
			SessionInfo s = getSessionInfo(request);
			j.setObj(diveTravelService.getDetail(id, s.getId()));
			j.success();
		}catch(Exception e){
			j.fail();
			e.printStackTrace();
		}		
		return j;
	}	
	
	/**
	 * 个人收藏-潜水旅游收藏列表查询
	 * @param ph
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/collectlist")
	public Json collectlist(PageHelper ph, HttpServletRequest request) {	
		Json j = new Json();
		try{
			SessionInfo s = getSessionInfo(request);
			DataGrid dg = diveTravelService.dataGridCollect(s.getId(), ph);
			j.setObj(dg);
			j.success();
		}catch(Exception e){
			j.fail();
			e.printStackTrace();
		}		
		return j;
	}	
	
	private SessionInfo getSessionInfo(HttpServletRequest request){
		SessionInfo s = tokenManage.getSessionInfo(request);
		return s;		
	}
}

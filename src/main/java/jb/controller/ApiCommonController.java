package jb.controller;

import jb.absx.F;
import jb.interceptors.TokenManage;
import jb.listener.Application;
import jb.pageModel.*;
import jb.service.*;
import jb.util.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 公共模块接口
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/api/apiCommon")
public class ApiCommonController extends BaseController {
	
	
	@Autowired
	private TokenManage tokenManage;
		
	@Autowired
	private DiveTravelServiceI diveTravelService;
	@Autowired
	private DiveAddressServiceI diveAddressService;
	@Autowired
	private DiveEquipServiceI diveEquipService;
	@Autowired
	private DiveActivityServiceI diveActivityService;
	@Autowired
	private DiveStoreServiceI diveStoreService;
	@Autowired
	private DiveCourseServiceI diveCourseService;
	@Autowired
	private DiveLogServiceI diveLogService;
	@Autowired
	private DiveLogDetailServiceI diveLogDetailService;
	@Autowired
	private DiveAccountServiceI diveAccountService;
	
	@Autowired
	private BugServiceI bugService;
	
	/**
	 * 生成html
	 * @return
	 */
	@RequestMapping("/html")
	public void html(String type,String id,HttpServletResponse response) {
		PrintWriter out = null;
		String content = "";
		try{
			response.setContentType("text/html");  
			response.setCharacterEncoding("UTF-8");
			if("BT01".equals(type)) { // 潜水旅游
				content = diveTravelService.get(id).getDescription();
			} else if("BT02".equals(type)) { // 潜点
				content = diveAddressService.get(id).getDescription();
			} else if("BT03".equals(type)) { // 装备
				content = diveEquipService.get(id).getEquipDes();
			} else if("BT04".equals(type)) { // 活动
				content = diveActivityService.get(id).getIntroduce();
			} else if("BT05".equals(type)) { // 度假村
				content = diveStoreService.get(id).getDescription();
			} else if("BT06".equals(type)) { // 学习
				content = diveCourseService.get(id).getIntroduce();
			}
			out = response.getWriter();
			out.write("<html><head>");
			out.write("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no\">");
			out.write("<style type=\"text/css\">");
			out.write("body {font-family:\"微软雅黑\";font-size:12px; background-color:#f8f7f5;}");
			out.write("ul,ol,li{padding:0; margin:0;}");
			out.write("img{border:0; line-height:0; width: 100%;}");
			out.write("ol,ul {list-style:none;}");
			out.write("a { color: #000; text-decoration: none; outline: none;}");
			out.write("a img { border: none; }");
			out.write("</style></head><body>");
			out.write(content);
			out.write("</body></html>");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(out!=null){
				out.flush();
				out.close();
			}
		}	
	}	
	
	/**
	 * 
	 * @param
	 * @param
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/error")
	public Json error() {
		Json j = new Json();
		j.setObj("token_expire");
		j.setSuccess(false);
		j.setMsg("token过期，请重新登录！");
		return j;
	}
	
	/**
	 * 分享统一入口
	 * @param
	 * @param
	 * @return
	 * eg: http://www.e-diving.com.cn/api/apiCommon/share?businessId=4fa21103-c7cb-495c-a35d-691c73d32f37&businessType=BT06
	 */
	@RequestMapping("/share")
	public String share(String businessId,String businessType,HttpServletRequest request) {
		String title = "";
		String content = "";
		Date date = null;
		if("BT01".equals(businessType)) { // 潜水旅游
			DiveTravel t = diveTravelService.get(businessId);
			content = t.getDescription();
			title = t.getName();
			date = t.getAddtime();
		} else if("BT02".equals(businessType)) { // 潜点
			DiveAddress t = diveAddressService.get(businessId);
			content = t.getDescription();
			title = t.getName();
			date = t.getAddtime();
		} else if("BT03".equals(businessType)) { // 装备
			DiveEquip t = diveEquipService.get(businessId);
			content = t.getEquipDes();
			title = t.getEquipName();
			date = t.getAddtime();
		} else if("BT04".equals(businessType)) { // 活动
			DiveActivity t = diveActivityService.getDetail(businessId, null, false);
			request.setAttribute("activity", t);
			return "/diveshare/activityshare";
//			content = t.getEquipDes();
//			title = t.getEquipName();
		} else if("BT05".equals(businessType)) { // 度假村
			DiveStore t = diveStoreService.get(businessId);
			content = t.getDescription();
			title = t.getName();
			date = t.getAddtime();
		} else if("BT06".equals(businessType)) { // 视频
			DiveCourse t = diveCourseService.get(businessId);
			request.setAttribute("title", t.getTitle());
			request.setAttribute("date", t.getAddtime());
			request.setAttribute("fileId", t.getFileId());
			return "/diveshare/courseshare";
		} else if("BT07".equals(businessType)) { // 潜水日志
			DiveLog t = diveLogService.get(businessId);
			List<String> imageList = new ArrayList<String>();
			if(!F.empty(t.getFileSrc())) {
				String[] fileSrcArr = t.getFileSrc().split("\\|\\|");
				for(String str : fileSrcArr) {
					if(F.empty(str)) continue;
					imageList.add(str);
				}
			}
			DiveAccount account = diveAccountService.get(t.getAccountId());

			PageHelper ph = new PageHelper();
			ph.setSort("addtime");
			ph.setOrder("asc");
			ph.setPage(1);
			ph.setRows(100);
			DiveLogDetail diveLogDetail = new DiveLogDetail();
			diveLogDetail.setLogId(businessId);
			List<DiveLogDetail> details =  diveLogDetailService.dataGrid(diveLogDetail, ph).getRows();
			
			request.setAttribute("log", t);
			request.setAttribute("imageList", imageList);
			request.setAttribute("account", account);
			request.setAttribute("details", details);
			if(t.getOutTime() != null && t.getInTime() != null)
				request.setAttribute("duration", (t.getOutTime().getTime()-t.getInTime().getTime())/(60*1000));
			return "/diveshare/divelog/logshare";
			
		}
		request.setAttribute("title", title);
		request.setAttribute("content", content);
		request.setAttribute("date", date);
		return "/diveshare/diveshare";
	}
	
	/**
	 * app错误日志上传
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/upload_errorlog")
	public Json uploadErrorlog(Bug bug, @RequestParam MultipartFile logFile, HttpServletRequest request) {
		Json j = new Json();
		try{
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
			Calendar calendar = Calendar.getInstance();  
			String dirName = "errorlog/" + calendar.get(Calendar.YEAR) + "/" + (calendar.get(Calendar.MONTH)+1) + "/" + calendar.get(Calendar.DAY_OF_MONTH);
			bug.setFilePath(uploadFile(request, dirName, logFile, format.format(calendar.getTime())));
			bug.setTypeId("0"); // 错误
			bug.setId(UUID.randomUUID().toString());
			bugService.add(bug);
			j.success();
		}catch(Exception e){
			j.fail();
			e.printStackTrace();
		}		
		return j;
	}
	
	/**
	 * 检查更新
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/checkUpdate")
	public Json checkUpdate(String versionNo) {
		Json j = new Json();
		try{
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("andriod_mark", false);
			result.put("ios_mark", false);
			
			// 检查android版本
			BaseData android_version = Application.get("VM01");
			if(F.empty(versionNo) || (android_version != null && !versionNo.equals(android_version.getName()))) {
				result.put("andriod_mark", true);
				result.put("android_filePath", android_version.getIcon());
			} 
			
			// 检查ios版本
			BaseData ios_version = Application.get("VM02");
			if(F.empty(versionNo) || (ios_version != null && !versionNo.equals(ios_version.getName()))) {
				result.put("ios_mark", true);
				result.put("ios_downloadUrl", ios_version.getDescription());
			} 
			
			j.setObj(result);
			j.success();
		}catch(Exception e){
			j.fail();
			e.printStackTrace();
		}		
		return j;
	}
	
	
	@ResponseBody
	@RequestMapping("/getIQiYiMP4Url")
	public Json getIQiYiMP4Url(String src, HttpServletRequest request) {
		Json j = new Json();
		try{
			j.setObj(HttpUtil.httpRequest(src, "GET", null));
			j.success();
		}catch(Exception e){
			j.fail();
			e.printStackTrace();
		}		
		return j;
	}
}

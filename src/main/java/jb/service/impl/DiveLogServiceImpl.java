package jb.service.impl;

import jb.absx.F;
import jb.dao.*;
import jb.listener.Application;
import jb.model.TdiveAccount;
import jb.model.TdiveLog;
import jb.model.TdiveLogComment;
import jb.pageModel.*;
import jb.service.DiveLogServiceI;
import jb.util.MyBeanUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DiveLogServiceImpl extends BaseServiceImpl<DiveLog> implements DiveLogServiceI {

	@Autowired
	private DiveLogDaoI diveLogDao;
	
	@Autowired
	private DivePraiseDaoI divePraiseDao;
	@Autowired
	private DiveCollectDaoI diveCollectDao;
	@Autowired
	private DiveLogCommentDaoI diveLogCommentDao;
	@Autowired
	private DiveAccountDaoI diveAccountDao;

	@Override
	public DataGrid dataGrid(DiveLog diveLog, PageHelper ph) {
		List<DiveLog> ol = new ArrayList<DiveLog>();
		String hql = " from TdiveLog t ";
		DataGrid dg = dataGridQuery(hql, ph, diveLog, diveLogDao);
		@SuppressWarnings("unchecked")
		List<TdiveLog> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TdiveLog t : l) {
				DiveLog o = new DiveLog();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	

	protected String whereHql(DiveLog diveLog, Map<String, Object> params) {
		String whereHql = "";	
		if (diveLog != null) {
			whereHql += " where 1=1 ";
			if (!F.empty(diveLog.getLogType())) {
				whereHql += " and t.logType = :logType";
				params.put("logType", diveLog.getLogType());
			}		
			if (!F.empty(diveLog.getFileSrc())) {
				whereHql += " and t.fileSrc = :fileSrc";
				params.put("fileSrc", diveLog.getFileSrc());
			}		
			if (!F.empty(diveLog.getAccountId())) {
				whereHql += " and t.accountId = :accountId";
				params.put("accountId", diveLog.getAccountId());
			}		
			if (!F.empty(diveLog.getDiveType())) {
				whereHql += " and t.diveType = :diveType";
				params.put("diveType", diveLog.getDiveType());
			}		
			if (!F.empty(diveLog.getWeather())) {
				whereHql += " and t.weather = :weather";
				params.put("weather", diveLog.getWeather());
			}		
				
		}	
		return whereHql;
	}

	@Override
	public void add(DiveLog diveLog) {
		TdiveLog t = new TdiveLog();
		BeanUtils.copyProperties(diveLog, t);
		t.setId(UUID.randomUUID().toString());
		t.setAddtime(new Date());
		diveLogDao.save(t);
	}

	@Override
	public DiveLog get(String id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TdiveLog t = diveLogDao.get("from TdiveLog t  where t.id = :id", params);
		DiveLog o = new DiveLog();
		BeanUtils.copyProperties(t, o);
		return o;
	}

	@Override
	public void edit(DiveLog diveLog) {
		TdiveLog t = diveLogDao.get(TdiveLog.class, diveLog.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(diveLog, t, new String[] { "id" , "createdatetime" },true);
			//t.setModifydatetime(new Date());
		}
	}

	@Override
	public void delete(String id) {
		diveLogDao.delete(diveLogDao.get(TdiveLog.class, id));
		diveLogDao.executeSql("delete from dive_log_detail where log_id = '" + id + "'");
		diveLogDao.executeSql("delete from dive_log_comment where log_id = '" + id + "'");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public DataGrid dataGriComplex(DiveLog diveLog, PageHelper ph) {
		DataGrid datagrid = dataGrid(diveLog, ph);
		List<DiveLog> diveLogs = datagrid.getRows();
		
		if(diveLogs!=null&&diveLogs.size()>0){
			String[] businessIds = new String[diveLogs.size()];
			int i = 0;
			for(DiveLog d : diveLogs){
				businessIds[i] = d.getId();
				i++;
			}
			//查询收藏数，赞数，评论数
			HashMap<String,Integer> collects = diveCollectDao.getCountCollectNum(LOG_TAG, businessIds);
			HashMap<String,Integer> praises = divePraiseDao.getCountPraiseNum(LOG_TAG, businessIds);
			HashMap<String,Integer> comments = diveLogCommentDao.getCountCommentNum(businessIds);
			
			for(DiveLog d : diveLogs){
				Integer num = praises.get(d.getId());
				if(num != null)
				d.setPraiseNum(num);
				
				num = comments.get(d.getId());
				if(num != null)
				d.setCommentNum(num);
				
				num = collects.get(d.getId());
				if(num != null)
				d.setCollectNum(num);
			}
		}
		return datagrid;
	}
	
	/**
	 * 获取详情信息
	 */
	public DiveLog getDetail(String id, String accountId) {
		DiveLog log = get(id);
		
		Map<String, Object> params = new HashMap<String, Object>();
		if(!F.empty(accountId)) {
			String cHql = "select count(*) from TdiveCollect t ";
			String pHql = "select count(*) from TdivePraise t ";
			params.put("businessId", id);
			params.put("businessType", LOG_TAG);
			String where = " where t.businessId = :businessId and t.businessType = :businessType ";
			log.setCollectNum(diveCollectDao.count(cHql + where, params).intValue()); // 收藏数
			log.setPraiseNum(divePraiseDao.count(pHql + where, params).intValue()); // 赞数
			
			params.put("accountId", accountId);
			where += " and t.accountId = :accountId ";
			if(diveCollectDao.count(cHql + where, params) > 0) {
				log.setCollect(true); // 已收藏
			} else {
				log.setCollect(false); // 未收藏
			}
			
			if(divePraiseDao.count(pHql + where, params) > 0) {
				log.setPraise(true); // 已赞
			} else {
				log.setPraise(false); // 未赞
			}

		}
		
		// 评论列表
		setCommentList(log);
		
		return log;
	}

	/**
	 * 更新阅读数
	 * @param log
	 */
	public void updateLogRead(DiveLog log) {
		int addNum = 1;
		try {
			String numStr = Application.getString("SV500");
			if(!F.empty(numStr)) {
				int num = Integer.valueOf(numStr);
				if(num > 0) {
					addNum = num;
				} else if(num < 0) {
					Random random = new Random();
					addNum = random.nextInt(-num) + 1;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", log.getId());
		diveLogDao.executeSql("update dive_log t set t.log_read = ifnull(t.log_read, 0) + "+addNum+" where t.id=:id", params);

		log.setLogRead(log.getLogRead() + addNum);
	}

	private void setCommentList(DiveLog diveLog) {
		List<DiveAccount> commentUsers = convert(diveAccountDao.getDiveAccountByLogComment(diveLog.getId()));
		Map<String,DiveAccount> commentUsersMap = new HashMap<String,DiveAccount>();
		for(DiveAccount t : commentUsers){
			commentUsersMap.put(t.getId(), t);
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("logId", diveLog.getId());
		List<TdiveLogComment> tDiveLogCommentList = diveLogCommentDao.find("from TdiveLogComment t where t.logId = :logId order by addtime", params);
		List<DiveLogComment> diveLogCommentList = new ArrayList<DiveLogComment>();
		Map<String, DiveAccount> commentAccountMap = new HashMap<String, DiveAccount>();
		for(TdiveLogComment t : tDiveLogCommentList) {
			commentAccountMap.put(t.getId(), commentUsersMap.get(t.getUserId()));
		}
		
		for(TdiveLogComment t : tDiveLogCommentList){
			DiveLogComment diveLogComment = new DiveLogComment();
			BeanUtils.copyProperties(t,diveLogComment);
			diveLogComment.setCommentUser(commentUsersMap.get(t.getUserId()));
			if(!F.empty(t.getPid())) {
				diveLogComment.setParentCommentUser(commentAccountMap.get(t.getPid()));
			}
			diveLogCommentList.add(diveLogComment);
			
		}
		
		diveLog.setCommentList(diveLogCommentList);
	}

	private List<DiveAccount> convert(List<TdiveAccount> diveAccounts){
		List<DiveAccount> list = new ArrayList<DiveAccount>();
		for(TdiveAccount s : diveAccounts){
			DiveAccount o = new DiveAccount();
			MyBeanUtils.copyProperties(s, o, new String[] { "password" , "personality", "email", "recommend", "hxPassword", "hxStatus", "addtime" }, true );
			list.add(o);
		}
		return list;		
	}

	/**
	 * 个人收藏-潜水日志收藏列表查询
	 */
	public DataGrid dataGridCollect(DiveLog log, String accountId, PageHelper ph) {
		List<DiveLog> ol = new ArrayList<DiveLog>();
		ph.setSort("addtime");
		ph.setOrder("desc");
		
		DataGrid dg = new DataGrid();
		
		String hql = "select a from TdiveLog a ,TdiveCollect t  "
				+ " where a.id = t.businessId and t.businessType='"+LOG_TAG+"' and t.accountId = :accountId ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("accountId", accountId);
		if(!F.empty(log.getLogType())) {
			hql += " and a.logType = :logType ";
			params.put("logType", log.getLogType());
		}
		List<TdiveLog> l = diveLogDao.find(hql   + orderHql(ph), params, ph.getPage(), ph.getRows());
		dg.setTotal(diveLogDao.count("select count(*) " + hql.substring(8) , params));
		if (l != null && l.size() > 0) {
			for (TdiveLog t : l) {
				DiveLog o = new DiveLog();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
}

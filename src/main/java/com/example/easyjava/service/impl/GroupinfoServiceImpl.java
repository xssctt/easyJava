package com.example.easyjava.service.impl;

import java.util.Date;
import com.example.easyjava.entity.po.Groupinfo;
import com.example.easyjava.entity.query.GroupinfoQuery;
import java.util.List;
import com.example.easyjava.entity.query.SimplePage;
import com.example.easyjava.entity.vo.PaginationResultVo;
import com.example.easyjava.enums.PageSize;
import com.example.easyjava.service.GroupinfoService;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.example.easyjava.mappers.GroupinfoMapper;


	/** 
	 *
	 * @Desoription 群service 逻辑层
	 * @Auther 摸鱼
	 * @Date 2024-05-30
	 */
@Service("/groupinfoService")
public class GroupinfoServiceImpl implements GroupinfoService{

	@Resource
	private GroupinfoMapper<Groupinfo,GroupinfoQuery> groupinfoMapper;

	/** 
	 *
	 *  根据条件查询列表
	 */
	public List<Groupinfo> findListByParam(GroupinfoQuery query){
	return this.groupinfoMapper.selectList(query);
	}
	/** 
	 *
	 *  根据条件查询多少数量
	 */
	public Integer findCountByParam(GroupinfoQuery query){
		return this.groupinfoMapper.selectCount(query);
	}
	/** 
	 *
	 *  分页查询
	 */
	public PaginationResultVo<Groupinfo> findListByPage(GroupinfoQuery query){
		Integer count=this.findCountByParam(query);
		Integer pageSze= query.getPageSize() == null ? PageSize.SIZE15.getSize() : query.getPageSize();
		SimplePage page=new SimplePage(query.getPageNo(),count,pageSze);
		query.setSimplePage(page);
		List<Groupinfo> list=this.findListByParam(query);
		PaginationResultVo<Groupinfo> result=new PaginationResultVo<Groupinfo>(count,page.getPageSize(),page.getPageNo(),page.getPageTotal(),list);
		return result;
	}
	/** 
	 *
	 *  新增
	 */
	public Integer add(Groupinfo bean){
		return this.groupinfoMapper.insert(bean);
	}
	/** 
	 *
	 *  批量新增
	 */
	public Integer addBatch(List<Groupinfo> listBean){
		if (listBean == null || listBean.isEmpty()){
			return 0;
		}
		return this.groupinfoMapper.insertBatch(listBean);
	}
	/** 
	 *
	 *  批量新增/修改
	 */
	public Integer addOrUpdateBatch(List<Groupinfo> listBean){
		if (listBean == null || listBean.isEmpty()){
			return 0;
		}
		return this.groupinfoMapper.insertOrUpdateBatch(listBean);
	}
	/** 
	 *
	 *  根据Groupid查询
	 */

	public Groupinfo getGroupinfoByGroupid(String groupid){
		return this.groupinfoMapper.selectByGroupid(groupid);
	}

	/** 
	 *
	 *  根据Groupid更新
	 */

	public Integer updateGroupinfoByGroupid(Groupinfo bean, String groupid){
		return this.groupinfoMapper.updateByGroupid(bean,groupid);
	}

	/** 
	 *
	 *  根据Groupid删除
	 */

	public Integer deleteGroupinfoByGroupid(String groupid){
		return this.groupinfoMapper.deleteByGroupid(groupid);
	}

}

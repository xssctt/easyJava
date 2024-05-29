package com.example.easyjava.service.impl;

import java.util.Date;
import com.example.easyjava.entity.po.Groupinfo;
import com.example.easyjava.entity.query.GroupinfoQuery;
import java.util.List;
import com.example.easyjava.entity.vo.PaginationResultVo;
import com.example.easyjava.service.GroupinfoService;


	/** 
	 *
	 * @Desoription 群service 逻辑层
	 * @Auther 摸鱼
	 * @Date 2024-05-29
	 */
public class GroupinfoServiceImpl implements GroupinfoService{

	/** 
	 *
	 *  根据条件查询列表
	 */
	public List<Groupinfo> findListByParam(GroupinfoQuery param){
	return null;
	}
	/** 
	 *
	 *  根据条件查询多少数量
	 */
	public Integer findCountByParam(GroupinfoQuery param){
	return null;
	}
	/** 
	 *
	 *  分页查询
	 */
	public PaginationResultVo<Groupinfo> findListByPage(GroupinfoQuery param){
	return null;
	}
}

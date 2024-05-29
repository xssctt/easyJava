package com.example.easyjava.service;

import java.util.Date;
import com.example.easyjava.entity.po.Groupinfo;
import com.example.easyjava.entity.query.GroupinfoQuery;
import java.util.List;
import com.example.easyjava.entity.vo.PaginationResultVo;


	/** 
	 *
	 * @Desoription 群service 逻辑层
	 * @Auther 摸鱼
	 * @Date 2024-05-29
	 */
public interface GroupinfoService {

	/** 
	 *
	 *  根据条件查询列表
	 */
	List<Groupinfo> findListByParam(GroupinfoQuery param);
	/** 
	 *
	 *  根据条件查询多少数量
	 */
	Integer findCountByParam(GroupinfoQuery param);
	/** 
	 *
	 *  分页查询
	 */
	PaginationResultVo<Groupinfo> findListByPage(GroupinfoQuery param);
}

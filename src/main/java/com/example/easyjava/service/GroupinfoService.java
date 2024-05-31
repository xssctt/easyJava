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
	 * @Date 2024-05-31
	 */
public interface GroupinfoService {

	/** 
	 *
	 *  根据条件查询列表
	 */
	List<Groupinfo> findListByParam(GroupinfoQuery query);
	/** 
	 *
	 *  根据条件查询多少数量
	 */
	Integer findCountByParam(GroupinfoQuery query);
	/** 
	 *
	 *  分页查询
	 */
	PaginationResultVo<Groupinfo> findListByPage(GroupinfoQuery query);
	/** 
	 *
	 *  新增
	 */
	Integer add(Groupinfo bean);
	/** 
	 *
	 *  批量新增
	 */
	Integer addBatch(List<Groupinfo> listBean);
	/** 
	 *
	 *  批量新增/修改
	 */
	Integer addOrUpdateBatch(List<Groupinfo> listBean);
	/** 
	 *
	 *  根据Groupid查询
	 */

	Groupinfo getGroupinfoByGroupid(String groupid);

	/** 
	 *
	 *  根据Groupid更新
	 */

	Integer updateGroupinfoByGroupid(Groupinfo bean, String groupid);

	/** 
	 *
	 *  根据Groupid删除
	 */

	Integer deleteGroupinfoByGroupid(String groupid);

}

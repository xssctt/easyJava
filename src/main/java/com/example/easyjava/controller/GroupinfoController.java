package com.example.easyjava.controller;

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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.easyjava.mappers.GroupinfoMapper;


	/** 
	 *
	 * @Desoription 群controller 控制层
	 * @Auther 摸鱼
	 * @Date 2024-05-30
	 */
@Controller("groupinfoController")
@RequestMapping("groupinfo")
public class GroupinfoController {

	@Resource
	GroupinfoService groupinfoService;

	/** 
	 *
	 *  根据条件查询列表
	 */
@RequestMapping("findListByParam")
	List<Groupinfo> findListByParam(GroupinfoQuery query){
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

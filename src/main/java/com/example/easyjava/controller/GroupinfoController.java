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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.easyjava.mappers.GroupinfoMapper;
import com.example.easyjava.controller.ABaseController;
import com.example.easyjava.entity.vo.ResponseVo;


	/** 
	 *
	 * @Desoription 群controller 控制层
	 * @Auther 摸鱼
	 * @Date 2024-05-31
	 */
@RestController("groupinfoController")
@RequestMapping("/groupinfo")
public class GroupinfoController extends ABaseController{

	@Resource
	private GroupinfoService groupinfoService;

	/** 
	 *
	 *  
	 */
	@RequestMapping("//loadDateList")
	public ResponseVo loadDataList(GroupinfoQuery query){
		return getSuccessResponseVo(groupinfoService.findListByParam(query));
	}
	/** 
	 *
	 *  新增
	 */
	@RequestMapping("/add")
	public ResponseVo add(Groupinfo bean){
		return getSuccessResponseVo(groupinfoService.add(bean));
	}
	/** 
	 *
	 *  批量新增
	 */
	@RequestMapping("/addBatch")
	public ResponseVo addBatch(@RequestBody List<Groupinfo> listBean){
		return getSuccessResponseVo(groupinfoService.addBatch(listBean));
	}
	/** 
	 *
	 *  批量新增/修改
	 */
	@RequestMapping("/addOrUpdateBatch")
	public ResponseVo addOrUpdateBatch(@RequestBody List<Groupinfo> listBean){
		return getSuccessResponseVo(groupinfoService.addOrUpdateBatch(listBean));
	}
	/** 
	 *
	 *  根据Groupid查询
	 */
	@RequestMapping("/getGroupinfoByGroupid")
	public ResponseVo getGroupinfoByGroupid(String groupid){
		return getSuccessResponseVo(groupinfoService.getGroupinfoByGroupid(groupid));
	}

	/** 
	 *
	 *  根据Groupid更新
	 */
	@RequestMapping("/updateGroupinfoByGroupid")
	public ResponseVo updateGroupinfoByGroupid(Groupinfo bean, String groupid){
		return getSuccessResponseVo(groupinfoService.updateGroupinfoByGroupid(bean,groupid));
	}

	/** 
	 *
	 *  根据Groupid删除
	 */
	@RequestMapping("/deleteGroupinfoByGroupid")
	public ResponseVo deleteGroupinfoByGroupid(String groupid){
		return getSuccessResponseVo(groupinfoService.deleteGroupinfoByGroupid(groupid));
	}

}

package com.example.easyjava.controller;

import java.util.Date;
import com.example.easyjava.entity.po.CeshiUser;
import com.example.easyjava.entity.query.CeshiUserQuery;
import java.util.List;
import com.example.easyjava.entity.query.SimplePage;
import com.example.easyjava.entity.vo.PaginationResultVo;
import com.example.easyjava.enums.PageSize;
import com.example.easyjava.service.CeshiUserService;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.easyjava.mappers.CeshiUserMapper;
import com.example.easyjava.controller.ABaseController;
import com.example.easyjava.entity.vo.ResponseVo;


	/** 
	 *
	 * @Desoription 用户controller 控制层
	 * @Auther 摸鱼
	 * @Date 2024-05-31
	 */
@RestController("ceshiUserController")
@RequestMapping("/ceshiUser")
public class CeshiUserController extends ABaseController{

	@Resource
	private CeshiUserService ceshiUserService;

	/** 
	 *
	 *  
	 */
	@RequestMapping("//loadDateList")
	public ResponseVo loadDataList(CeshiUserQuery query){
		return getSuccessResponseVo(ceshiUserService.findListByParam(query));
	}
	/** 
	 *
	 *  新增
	 */
	@RequestMapping("/add")
	public ResponseVo add(CeshiUser bean){
		return getSuccessResponseVo(ceshiUserService.add(bean));
	}
	/** 
	 *
	 *  批量新增
	 */
	@RequestMapping("/addBatch")
	public ResponseVo addBatch(@RequestBody List<CeshiUser> listBean){
		return getSuccessResponseVo(ceshiUserService.addBatch(listBean));
	}
	/** 
	 *
	 *  批量新增/修改
	 */
	@RequestMapping("/addOrUpdateBatch")
	public ResponseVo addOrUpdateBatch(@RequestBody List<CeshiUser> listBean){
		return getSuccessResponseVo(ceshiUserService.addOrUpdateBatch(listBean));
	}
	/** 
	 *
	 *  根据Id查询
	 */
	@RequestMapping("/getCeshiUserById")
	public ResponseVo getCeshiUserById(Integer id){
		return getSuccessResponseVo(ceshiUserService.getCeshiUserById(id));
	}

	/** 
	 *
	 *  根据Id更新
	 */
	@RequestMapping("/updateCeshiUserById")
	public ResponseVo updateCeshiUserById(CeshiUser bean, Integer id){
		return getSuccessResponseVo(ceshiUserService.updateCeshiUserById(bean,id));
	}

	/** 
	 *
	 *  根据Id删除
	 */
	@RequestMapping("/deleteCeshiUserById")
	public ResponseVo deleteCeshiUserById(Integer id){
		return getSuccessResponseVo(ceshiUserService.deleteCeshiUserById(id));
	}

	/** 
	 *
	 *  根据IdAndName查询
	 */
	@RequestMapping("/getCeshiUserByIdAndName")
	public ResponseVo getCeshiUserByIdAndName(Integer id, String name){
		return getSuccessResponseVo(ceshiUserService.getCeshiUserByIdAndName(id, name));
	}

	/** 
	 *
	 *  根据IdAndName更新
	 */
	@RequestMapping("/updateCeshiUserByIdAndName")
	public ResponseVo updateCeshiUserByIdAndName(CeshiUser bean, Integer id, String name){
		return getSuccessResponseVo(ceshiUserService.updateCeshiUserByIdAndName(bean,id, name));
	}

	/** 
	 *
	 *  根据IdAndName删除
	 */
	@RequestMapping("/deleteCeshiUserByIdAndName")
	public ResponseVo deleteCeshiUserByIdAndName(Integer id, String name){
		return getSuccessResponseVo(ceshiUserService.deleteCeshiUserByIdAndName(id, name));
	}

	/** 
	 *
	 *  根据SexAndId查询
	 */
	@RequestMapping("/getCeshiUserBySexAndId")
	public ResponseVo getCeshiUserBySexAndId(Integer sex, Integer id){
		return getSuccessResponseVo(ceshiUserService.getCeshiUserBySexAndId(sex, id));
	}

	/** 
	 *
	 *  根据SexAndId更新
	 */
	@RequestMapping("/updateCeshiUserBySexAndId")
	public ResponseVo updateCeshiUserBySexAndId(CeshiUser bean, Integer sex, Integer id){
		return getSuccessResponseVo(ceshiUserService.updateCeshiUserBySexAndId(bean,sex, id));
	}

	/** 
	 *
	 *  根据SexAndId删除
	 */
	@RequestMapping("/deleteCeshiUserBySexAndId")
	public ResponseVo deleteCeshiUserBySexAndId(Integer sex, Integer id){
		return getSuccessResponseVo(ceshiUserService.deleteCeshiUserBySexAndId(sex, id));
	}

}

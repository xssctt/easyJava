package com.example.easyjava.service.impl;

import java.util.Date;
import com.example.easyjava.entity.po.CeshiUser;
import com.example.easyjava.entity.query.CeshiUserQuery;
import java.util.List;
import com.example.easyjava.entity.vo.PaginationResultVo;
import com.example.easyjava.service.CeshiUserService;


	/** 
	 *
	 * @Desoription 用户service 逻辑层
	 * @Auther 摸鱼
	 * @Date 2024-05-29
	 */
public class CeshiUserServiceImpl implements CeshiUserService{

	/** 
	 *
	 *  根据条件查询列表
	 */
	public List<CeshiUser> findListByParam(CeshiUserQuery param){
	return null;
	}
	/** 
	 *
	 *  根据条件查询多少数量
	 */
	public Integer findCountByParam(CeshiUserQuery param){
	return null;
	}
	/** 
	 *
	 *  分页查询
	 */
	public PaginationResultVo<CeshiUser> findListByPage(CeshiUserQuery param){
	return null;
	}
}

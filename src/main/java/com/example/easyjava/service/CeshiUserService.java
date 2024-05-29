package com.example.easyjava.service;

import java.util.Date;
import com.example.easyjava.entity.po.CeshiUser;
import com.example.easyjava.entity.query.CeshiUserQuery;
import java.util.List;
import com.example.easyjava.entity.vo.PaginationResultVo;


	/** 
	 *
	 * @Desoription 用户service 逻辑层
	 * @Auther 摸鱼
	 * @Date 2024-05-29
	 */
public interface CeshiUserService {

	/** 
	 *
	 *  根据条件查询列表
	 */
	List<CeshiUser> findListByParam(CeshiUserQuery param);
	/** 
	 *
	 *  根据条件查询多少数量
	 */
	Integer findCountByParam(CeshiUserQuery param);
	/** 
	 *
	 *  分页查询
	 */
	PaginationResultVo<CeshiUser> findListByPage(CeshiUserQuery param);
}

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
	 * @Date 2024-05-31
	 */
public interface CeshiUserService {

	/** 
	 *
	 *  根据条件查询列表
	 */
	List<CeshiUser> findListByParam(CeshiUserQuery query);
	/** 
	 *
	 *  根据条件查询多少数量
	 */
	Integer findCountByParam(CeshiUserQuery query);
	/** 
	 *
	 *  分页查询
	 */
	PaginationResultVo<CeshiUser> findListByPage(CeshiUserQuery query);
	/** 
	 *
	 *  新增
	 */
	Integer add(CeshiUser bean);
	/** 
	 *
	 *  批量新增
	 */
	Integer addBatch(List<CeshiUser> listBean);
	/** 
	 *
	 *  批量新增/修改
	 */
	Integer addOrUpdateBatch(List<CeshiUser> listBean);
	/** 
	 *
	 *  根据Id查询
	 */

	CeshiUser getCeshiUserById(Integer id);

	/** 
	 *
	 *  根据Id更新
	 */

	Integer updateCeshiUserById(CeshiUser bean, Integer id);

	/** 
	 *
	 *  根据Id删除
	 */

	Integer deleteCeshiUserById(Integer id);

	/** 
	 *
	 *  根据IdAndName查询
	 */

	CeshiUser getCeshiUserByIdAndName(Integer id, String name);

	/** 
	 *
	 *  根据IdAndName更新
	 */

	Integer updateCeshiUserByIdAndName(CeshiUser bean, Integer id, String name);

	/** 
	 *
	 *  根据IdAndName删除
	 */

	Integer deleteCeshiUserByIdAndName(Integer id, String name);

	/** 
	 *
	 *  根据SexAndId查询
	 */

	CeshiUser getCeshiUserBySexAndId(Integer sex, Integer id);

	/** 
	 *
	 *  根据SexAndId更新
	 */

	Integer updateCeshiUserBySexAndId(CeshiUser bean, Integer sex, Integer id);

	/** 
	 *
	 *  根据SexAndId删除
	 */

	Integer deleteCeshiUserBySexAndId(Integer sex, Integer id);

}

package com.example.easyjava.service.impl;

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
import com.example.easyjava.mappers.CeshiUserMapper;


	/** 
	 *
	 * @Desoription 用户service 逻辑层
	 * @Auther 摸鱼
	 * @Date 2024-05-30
	 */
@Service("/ceshiUserService")
public class CeshiUserServiceImpl implements CeshiUserService{

	@Resource
	private CeshiUserMapper<CeshiUser,CeshiUserQuery> ceshiUserMapper;

	/** 
	 *
	 *  根据条件查询列表
	 */
	public List<CeshiUser> findListByParam(CeshiUserQuery query){
	return this.ceshiUserMapper.selectList(query);
	}
	/** 
	 *
	 *  根据条件查询多少数量
	 */
	public Integer findCountByParam(CeshiUserQuery query){
		return this.ceshiUserMapper.selectCount(query);
	}
	/** 
	 *
	 *  分页查询
	 */
	public PaginationResultVo<CeshiUser> findListByPage(CeshiUserQuery query){
		Integer count=this.findCountByParam(query);
		Integer pageSze= query.getPageSize() == null ? PageSize.SIZE15.getSize() : query.getPageSize();
		SimplePage page=new SimplePage(query.getPageNo(),count,pageSze);
		query.setSimplePage(page);
		List<CeshiUser> list=this.findListByParam(query);
		PaginationResultVo<CeshiUser> result=new PaginationResultVo<CeshiUser>(count,page.getPageSize(),page.getPageNo(),page.getPageTotal(),list);
		return result;
	}
	/** 
	 *
	 *  新增
	 */
	public Integer add(CeshiUser bean){
		return this.ceshiUserMapper.insert(bean);
	}
	/** 
	 *
	 *  批量新增
	 */
	public Integer addBatch(List<CeshiUser> listBean){
		if (listBean == null || listBean.isEmpty()){
			return 0;
		}
		return this.ceshiUserMapper.insertBatch(listBean);
	}
	/** 
	 *
	 *  批量新增/修改
	 */
	public Integer addOrUpdateBatch(List<CeshiUser> listBean){
		if (listBean == null || listBean.isEmpty()){
			return 0;
		}
		return this.ceshiUserMapper.insertOrUpdateBatch(listBean);
	}
	/** 
	 *
	 *  根据Id查询
	 */

	public CeshiUser getCeshiUserById(Integer id){
		return this.ceshiUserMapper.selectById(id);
	}

	/** 
	 *
	 *  根据Id更新
	 */

	public Integer updateCeshiUserById(CeshiUser bean, Integer id){
		return this.ceshiUserMapper.updateById(bean,id);
	}

	/** 
	 *
	 *  根据Id删除
	 */

	public Integer deleteCeshiUserById(Integer id){
		return this.ceshiUserMapper.deleteById(id);
	}

	/** 
	 *
	 *  根据IdAndName查询
	 */

	public CeshiUser getCeshiUserByIdAndName(Integer id, String name){
		return this.ceshiUserMapper.selectByIdAndName(id, name);
	}

	/** 
	 *
	 *  根据IdAndName更新
	 */

	public Integer updateCeshiUserByIdAndName(CeshiUser bean, Integer id, String name){
		return this.ceshiUserMapper.updateByIdAndName(bean,id, name);
	}

	/** 
	 *
	 *  根据IdAndName删除
	 */

	public Integer deleteCeshiUserByIdAndName(Integer id, String name){
		return this.ceshiUserMapper.deleteByIdAndName(id, name);
	}

	/** 
	 *
	 *  根据SexAndId查询
	 */

	public CeshiUser getCeshiUserBySexAndId(Integer sex, Integer id){
		return this.ceshiUserMapper.selectBySexAndId(sex, id);
	}

	/** 
	 *
	 *  根据SexAndId更新
	 */

	public Integer updateCeshiUserBySexAndId(CeshiUser bean, Integer sex, Integer id){
		return this.ceshiUserMapper.updateBySexAndId(bean,sex, id);
	}

	/** 
	 *
	 *  根据SexAndId删除
	 */

	public Integer deleteCeshiUserBySexAndId(Integer sex, Integer id){
		return this.ceshiUserMapper.deleteBySexAndId(sex, id);
	}

}

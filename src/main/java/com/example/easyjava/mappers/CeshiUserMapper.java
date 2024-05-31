package com.example.easyjava.mappers;

import com.example.easyjava.mappers.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.Date;
	/** 
	 *
	 * @Desoription 用户mappers
	 * @Auther 摸鱼
	 * @Date 2024-05-31
	 */
public interface CeshiUserMapper<T, P> extends BaseMapper{
	/** 
	 *
	 *  根据Id查询
	 */

	T selectById(@Param("id") Integer id);

	/** 
	 *
	 *  根据Id更新
	 */

	Integer updateById(@Param("bean") T t, @Param("id") Integer id);

	/** 
	 *
	 *  根据Id删除
	 */

	Integer deleteById(@Param("id") Integer id);

	/** 
	 *
	 *  根据IdAndName查询
	 */

	T selectByIdAndName(@Param("id") Integer id, @Param("name") String name);

	/** 
	 *
	 *  根据IdAndName更新
	 */

	Integer updateByIdAndName(@Param("bean") T t, @Param("id") Integer id, @Param("name") String name);

	/** 
	 *
	 *  根据IdAndName删除
	 */

	Integer deleteByIdAndName(@Param("id") Integer id, @Param("name") String name);

	/** 
	 *
	 *  根据SexAndId查询
	 */

	T selectBySexAndId(@Param("sex") Integer sex, @Param("id") Integer id);

	/** 
	 *
	 *  根据SexAndId更新
	 */

	Integer updateBySexAndId(@Param("bean") T t, @Param("sex") Integer sex, @Param("id") Integer id);

	/** 
	 *
	 *  根据SexAndId删除
	 */

	Integer deleteBySexAndId(@Param("sex") Integer sex, @Param("id") Integer id);

}
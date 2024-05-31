package com.example.easyjava.mappers;

import com.example.easyjava.mappers.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.Date;
	/** 
	 *
	 * @Desoription 群mappers
	 * @Auther 摸鱼
	 * @Date 2024-05-31
	 */
public interface GroupinfoMapper<T, P> extends BaseMapper{
	/** 
	 *
	 *  根据Groupid查询
	 */

	T selectByGroupid(@Param("groupid") String groupid);

	/** 
	 *
	 *  根据Groupid更新
	 */

	Integer updateByGroupid(@Param("bean") T t, @Param("groupid") String groupid);

	/** 
	 *
	 *  根据Groupid删除
	 */

	Integer deleteByGroupid(@Param("groupid") String groupid);

}
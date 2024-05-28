package com.example.easyjava.entity.query;

import java.util.Date;
import com.example.easyjava.entity.query.BaseQuery;


	/** 
	 *
	 * @Desoription 群查询对象
	 * @Auther 摸鱼
	 * @Date 2024-05-27
	 */
public class GroupinfoQuery extends BaseQuery{
	/** 
	 *
	 *  群组id
	 */
	private String groupid;

	private String groupidFuzzy;

	/** 
	 *
	 *  群组名称
	 */
	private String groupname;

	private String groupnameFuzzy;

	/** 
	 *
	 *  群主id
	 */
	private String groupownerid;

	private String groupowneridFuzzy;

	/** 
	 *
	 *  创建时间
	 */
	private Date createtime;

	private String createtimeStart;

	private String createtimeEnd;

	/** 
	 *
	 *  群公告
	 */
	private String groupnotice;

	private String groupnoticeFuzzy;

	/** 
	 *
	 *  0直接加入2需要同意
	 */
	private Integer jointype;

	/** 
	 *
	 *  1正常0解散
	 */
	private Integer status;

	public void setGroupid(String groupid) {
		this.groupid=groupid;
	}

	public String getGroupid() {
		return this.groupid;
	}

	public void setGroupname(String groupname) {
		this.groupname=groupname;
	}

	public String getGroupname() {
		return this.groupname;
	}

	public void setGroupownerid(String groupownerid) {
		this.groupownerid=groupownerid;
	}

	public String getGroupownerid() {
		return this.groupownerid;
	}

	public void setCreatetime(Date createtime) {
		this.createtime=createtime;
	}

	public Date getCreatetime() {
		return this.createtime;
	}

	public void setGroupnotice(String groupnotice) {
		this.groupnotice=groupnotice;
	}

	public String getGroupnotice() {
		return this.groupnotice;
	}

	public void setJointype(Integer jointype) {
		this.jointype=jointype;
	}

	public Integer getJointype() {
		return this.jointype;
	}

	public void setStatus(Integer status) {
		this.status=status;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setGroupidFuzzy(String groupidFuzzy) {
		this.groupidFuzzy=groupidFuzzy;
	}

	public String getGroupidFuzzy() {
		return this.groupidFuzzy;
	}

	public void setGroupnameFuzzy(String groupnameFuzzy) {
		this.groupnameFuzzy=groupnameFuzzy;
	}

	public String getGroupnameFuzzy() {
		return this.groupnameFuzzy;
	}

	public void setGroupowneridFuzzy(String groupowneridFuzzy) {
		this.groupowneridFuzzy=groupowneridFuzzy;
	}

	public String getGroupowneridFuzzy() {
		return this.groupowneridFuzzy;
	}

	public void setCreatetimeStart(String createtimeStart) {
		this.createtimeStart=createtimeStart;
	}

	public String getCreatetimeStart() {
		return this.createtimeStart;
	}

	public void setCreatetimeEnd(String createtimeEnd) {
		this.createtimeEnd=createtimeEnd;
	}

	public String getCreatetimeEnd() {
		return this.createtimeEnd;
	}

	public void setGroupnoticeFuzzy(String groupnoticeFuzzy) {
		this.groupnoticeFuzzy=groupnoticeFuzzy;
	}

	public String getGroupnoticeFuzzy() {
		return this.groupnoticeFuzzy;
	}

}
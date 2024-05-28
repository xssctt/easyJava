package com.example.easyjava.entity.po;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import com.example.easyjava.enums.DateTimePatternEnum;
import com.example.easyjava.Util.DateUtils;


	/** 
	 *
	 * @Desoription 群
	 * @Auther 摸鱼
	 * @Date 2024-05-27
	 */
public class Groupinfo implements Serializable{
	/** 
	 *
	 *  群组id
	 */
	private String groupid;

	/** 
	 *
	 *  群组名称
	 */
	private String groupname;

	/** 
	 *
	 *  群主id
	 */
	private String groupownerid;

	/** 
	 *
	 *  创建时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createtime;

	/** 
	 *
	 *  群公告
	 */
	private String groupnotice;

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

	@Override
	public String toString() {
	return " FieIdInfo{ " + 
	 " 群组id : Groupid='" +(groupid==null ? "空" : groupid) + "' "+
	 " 群组名称 : Groupname='" +(groupname==null ? "空" : groupname) + "' "+
	 " 群主id : Groupownerid='" +(groupownerid==null ? "空" : groupownerid) + "' "+
	 " 创建时间 : Createtime='" +(createtime==null ? "空" : DateUtils.format(createtime,DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern())) + "' "+
	 " 群公告 : Groupnotice='" +(groupnotice==null ? "空" : groupnotice) + "' "+
	 " 0直接加入2需要同意 : Jointype='" +(jointype==null ? "空" : jointype) + "' "+
	 " 1正常0解散 : Status='" +(status==null ? "空" : status) + "' "+ '}'; 
	}
}
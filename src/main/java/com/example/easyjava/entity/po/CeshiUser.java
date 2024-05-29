package com.example.easyjava.entity.po;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import com.example.easyjava.enums.DateTimePatternEnum;
import com.example.easyjava.Util.DateUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;


	/** 
	 *
	 * @Desoription 用户
	 * @Auther 摸鱼
	 * @Date 2024-05-29
	 */
public class CeshiUser implements Serializable{
	/** 
	 *
	 *  id
	 */
	private Integer id;

	/** 
	 *
	 *  name
	 */
	private String name;

	/** 
	 *
	 *  年龄
	 */
	private Integer age;

	/** 
	 *
	 *  sex
	 */
	@JsonIgnore
	private Integer sex;

	/** 
	 *
	 *  create
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createtime;

	/** 
	 *
	 *  up
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updatetime;

	/** 
	 *
	 *  date
	 */
	@JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;

	public void setId(Integer id) {
		this.id=id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setName(String name) {
		this.name=name;
	}

	public String getName() {
		return this.name;
	}

	public void setAge(Integer age) {
		this.age=age;
	}

	public Integer getAge() {
		return this.age;
	}

	public void setSex(Integer sex) {
		this.sex=sex;
	}

	public Integer getSex() {
		return this.sex;
	}

	public void setCreatetime(Date createtime) {
		this.createtime=createtime;
	}

	public Date getCreatetime() {
		return this.createtime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime=updatetime;
	}

	public Date getUpdatetime() {
		return this.updatetime;
	}

	public void setDate(Date date) {
		this.date=date;
	}

	public Date getDate() {
		return this.date;
	}

	@Override
	public String toString() {
	return " FieIdInfo{ " + 
	 " id : Id='" +(id==null ? "空" : id) + "' "+
	 " name : Name='" +(name==null ? "空" : name) + "' "+
	 " 年龄 : Age='" +(age==null ? "空" : age) + "' "+
	 " sex : Sex='" +(sex==null ? "空" : sex) + "' "+
	 " create : Createtime='" +(createtime==null ? "空" : DateUtils.format(createtime,DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern())) + "' "+
	 " up : Updatetime='" +(updatetime==null ? "空" : DateUtils.format(updatetime,DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern())) + "' "+
	 " date : Date='" +(date==null ? "空" : DateUtils.format(date,DateTimePatternEnum.YYYY_MM_DD.getPattern())) + "' "+ '}'; 
	}
}
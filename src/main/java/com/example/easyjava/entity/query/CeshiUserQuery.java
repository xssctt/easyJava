package com.example.easyjava.entity.query;

import java.util.Date;
import com.example.easyjava.entity.query.BaseQuery;


	/** 
	 *
	 * @Desoription 用户查询对象
	 * @Auther 摸鱼
	 * @Date 2024-05-30
	 */
public class CeshiUserQuery extends BaseQuery{
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

	private String nameFuzzy;

	/** 
	 *
	 *  年龄
	 */
	private Integer age;

	/** 
	 *
	 *  sex
	 */
	private Integer sex;

	/** 
	 *
	 *  create
	 */
	private Date createtime;

	private String createtimeStart;

	private String createtimeEnd;

	/** 
	 *
	 *  up
	 */
	private Date updatetime;

	private String updatetimeStart;

	private String updatetimeEnd;

	/** 
	 *
	 *  date
	 */
	private Date date;

	private String dateStart;

	private String dateEnd;

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

	public void setNameFuzzy(String nameFuzzy) {
		this.nameFuzzy=nameFuzzy;
	}

	public String getNameFuzzy() {
		return this.nameFuzzy;
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

	public void setUpdatetimeStart(String updatetimeStart) {
		this.updatetimeStart=updatetimeStart;
	}

	public String getUpdatetimeStart() {
		return this.updatetimeStart;
	}

	public void setUpdatetimeEnd(String updatetimeEnd) {
		this.updatetimeEnd=updatetimeEnd;
	}

	public String getUpdatetimeEnd() {
		return this.updatetimeEnd;
	}

	public void setDateStart(String dateStart) {
		this.dateStart=dateStart;
	}

	public String getDateStart() {
		return this.dateStart;
	}

	public void setDateEnd(String dateEnd) {
		this.dateEnd=dateEnd;
	}

	public String getDateEnd() {
		return this.dateEnd;
	}

}
package com.bean.breakfast.basic.model;

import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import javax.persistence.*;

/**
 * TBfUser entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_bf_user", catalog = "breakfast", uniqueConstraints = {})
public class TBfUser implements java.io.Serializable {

	// Fields

	private String userId;
	private String loginName;
	private String password;
	private String status;
	private String userName;
	private String mobile;
	private String weixin;
	private String qq;
	private Date registerTime;
	private String registerWay;
	private Date createTime;
	private String createBy;
	private Date lastModifyTime;
	private String lastModifyBy;
	private Long optTime;

	// Constructors

	/** default constructor */
	public TBfUser() {
	}

	/** minimal constructor */
	public TBfUser(String userId) {
		this.userId = userId;
	}

	/** full constructor */
	public TBfUser(String userId, String loginName, String password,
			String status, String userName, String mobile, String weixin,
			String qq, Date registerTime, String registerWay, Date createTime,
			String createBy, Date lastModifyTime, String lastModifyBy,
			Long optTime) {
		this.userId = userId;
		this.loginName = loginName;
		this.password = password;
		this.status = status;
		this.userName = userName;
		this.mobile = mobile;
		this.weixin = weixin;
		this.qq = qq;
		this.registerTime = registerTime;
		this.registerWay = registerWay;
		this.createTime = createTime;
		this.createBy = createBy;
		this.lastModifyTime = lastModifyTime;
		this.lastModifyBy = lastModifyBy;
		this.optTime = optTime;
	}

	// Property accessors
	@Id
	@GeneratedValue(generator = "id")
	@GenericGenerator(name = "id", strategy = "uuid")
	@Column(name = "user_id", unique = true, nullable = false, insertable = true, updatable = true, length = 32)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "login_name", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@Column(name = "password", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "status", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "user_name", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "mobile", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "weixin", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public String getWeixin() {
		return this.weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

	@Column(name = "qq", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	public String getQq() {
		return this.qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "register_time", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public Date getRegisterTime() {
		return this.registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	@Column(name = "register_way", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getRegisterWay() {
		return this.registerWay;
	}

	public void setRegisterWay(String registerWay) {
		this.registerWay = registerWay;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "create_by", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getCreateBy() {
		return this.createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_modify_time", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public Date getLastModifyTime() {
		return this.lastModifyTime;
	}

	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	@Column(name = "last_modify_by", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getLastModifyBy() {
		return this.lastModifyBy;
	}

	public void setLastModifyBy(String lastModifyBy) {
		this.lastModifyBy = lastModifyBy;
	}

	@Column(name = "opt_time", unique = false, nullable = true, insertable = true, updatable = true)
	public Long getOptTime() {
		return this.optTime;
	}

	public void setOptTime(Long optTime) {
		this.optTime = optTime;
	}

}
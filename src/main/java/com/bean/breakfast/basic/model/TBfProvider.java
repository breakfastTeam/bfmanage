package com.bean.breakfast.basic.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TBfProvider entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity(name = "t_bf_provider")
public class TBfProvider implements java.io.Serializable {

	// Fields

	private String providerId;
	private String providerName;
	private String phone;
	private String alternativePhone;
	private String address;
	private String intro;
	private String status;
	private Date createTime;
	private String createBy;
	private Date lastModifyTime;
	private String lastModifyBy;
	private Integer optTime;

	// Constructors

	/** default constructor */
	public TBfProvider() {
	}

	/** minimal constructor */
	public TBfProvider(String providerId) {
		this.providerId = providerId;
	}

	/** full constructor */
	public TBfProvider(String providerId, String providerName, String phone,
			String alternativePhone, String address, String intro,
			String status, Date createTime, String createBy,
			Date lastModifyTime, String lastModifyBy, Integer optTime) {
		this.providerId = providerId;
		this.providerName = providerName;
		this.phone = phone;
		this.alternativePhone = alternativePhone;
		this.address = address;
		this.intro = intro;
		this.status = status;
		this.createTime = createTime;
		this.createBy = createBy;
		this.lastModifyTime = lastModifyTime;
		this.lastModifyBy = lastModifyBy;
		this.optTime = optTime;
	}

	// Property accessors
	@Id
	@Column(name = "provider_id", unique = true, nullable = false, insertable = true, updatable = true, length = 32)
	public String getProviderId() {
		return this.providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	@Column(name = "provider_name", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getProviderName() {
		return this.providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	@Column(name = "phone", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "alternative_phone", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public String getAlternativePhone() {
		return this.alternativePhone;
	}

	public void setAlternativePhone(String alternativePhone) {
		this.alternativePhone = alternativePhone;
	}

	@Column(name = "address", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "intro", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getIntro() {
		return this.intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	@Column(name = "status", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Temporal(TemporalType.DATE)
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

	@Temporal(TemporalType.DATE)
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
	public Integer getOptTime() {
		return this.optTime;
	}

	public void setOptTime(Integer optTime) {
		this.optTime = optTime;
	}

}
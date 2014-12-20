package com.bean.breakfast.basic.model;

import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import javax.persistence.*;

/**
 * TBfInformation entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_bf_information")
public class TBfInformation implements java.io.Serializable {

	// Fields

	private String informationId;
	private String title;
	private String content;
	private String informationType;
	private Date postTime;
	private String postBy;
	private String status;
	private Date createTime;
	private String createBy;
	private Date lastModifyTime;
	private String lastModifyBy;
	private Long optTime;

	// Constructors

	/** default constructor */
	public TBfInformation() {
	}

	/** minimal constructor */
	public TBfInformation(String informationId) {
		this.informationId = informationId;
	}

	/** full constructor */
	public TBfInformation(String informationId, String title, String content,
			String informationType, Date postTime, String postBy,
			String status, Date createTime, String createBy,
			Date lastModifyTime, String lastModifyBy, Long optTime) {
		this.informationId = informationId;
		this.title = title;
		this.content = content;
		this.informationType = informationType;
		this.postTime = postTime;
		this.postBy = postBy;
		this.status = status;
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
	@Column(name = "information_id", unique = true, nullable = false, insertable = true, updatable = true, length = 32)
	public String getInformationId() {
		return this.informationId;
	}

	public void setInformationId(String informationId) {
		this.informationId = informationId;
	}

	@Column(name = "title", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "content", unique = false, nullable = true, insertable = true, updatable = true)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "information_type", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public String getInformationType() {
		return this.informationType;
	}

	public void setInformationType(String informationType) {
		this.informationType = informationType;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "post_time", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public Date getPostTime() {
		return this.postTime;
	}

	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}

	@Column(name = "post_by", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getPostBy() {
		return this.postBy;
	}

	public void setPostBy(String postBy) {
		this.postBy = postBy;
	}

	@Column(name = "status", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
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
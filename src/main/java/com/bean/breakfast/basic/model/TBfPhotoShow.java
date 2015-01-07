package com.bean.breakfast.basic.model;

import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import javax.persistence.*;

/**
 * TBfPhotoShow entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_bf_photo_show")
public class TBfPhotoShow implements java.io.Serializable {

	// Fields

	private String photoId;
	private String orginFileId;
	private String smallFileId;
	private Date uploadTime;
	private Date validDate;
	private String createBy;
	private String status;
	private Date createTime;
	private String createBy_1;
	private Date lastModifyTime;
	private String lastModifyBy;
	private Long optTime;

	// Constructors

	/** default constructor */
	public TBfPhotoShow() {
	}

	/** minimal constructor */
	public TBfPhotoShow(String photoId) {
		this.photoId = photoId;
	}

	/** full constructor */
	public TBfPhotoShow(String photoId, String orginFileId, String smallFileId,
			Date uploadTime, Date validDate, String createBy, String status,
			Date createTime, String createBy_1, Date lastModifyTime,
			String lastModifyBy, Long optTime) {
		this.photoId = photoId;
		this.orginFileId = orginFileId;
		this.smallFileId = smallFileId;
		this.uploadTime = uploadTime;
		this.validDate = validDate;
		this.createBy = createBy;
		this.status = status;
		this.createTime = createTime;
		this.createBy_1 = createBy_1;
		this.lastModifyTime = lastModifyTime;
		this.lastModifyBy = lastModifyBy;
		this.optTime = optTime;
	}

	// Property accessors
	@Id
	@GeneratedValue(generator = "id")
	@GenericGenerator(name = "id", strategy = "uuid")
	@Column(name = "photo_id", unique = true, nullable = false, insertable = true, updatable = true, length = 32)
	public String getPhotoId() {
		return this.photoId;
	}

	public void setPhotoId(String photoId) {
		this.photoId = photoId;
	}

	@Column(name = "orgin_file_id", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getOrginFileId() {
		return this.orginFileId;
	}

	public void setOrginFileId(String orginFileId) {
		this.orginFileId = orginFileId;
	}

	@Column(name = "small_file_id", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getSmallFileId() {
		return this.smallFileId;
	}

	public void setSmallFileId(String smallFileId) {
		this.smallFileId = smallFileId;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "upload_time", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public Date getUploadTime() {
		return this.uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "valid_date", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public Date getValidDate() {
		return this.validDate;
	}

	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}

	@Column(name = "createBy", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getCreateBy() {
		return this.createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	@Column(name = "status", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
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
	public String getCreateBy_1() {
		return this.createBy_1;
	}

	public void setCreateBy_1(String createBy_1) {
		this.createBy_1 = createBy_1;
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
package com.bean.breakfast.basic.model;

import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import javax.persistence.*;

/**
 * TBfUserElementStatistics entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_bf_user_element_statistics",  uniqueConstraints = {})
public class TBfUserElementStatistics implements java.io.Serializable {

	// Fields

	private String statisticsId;
	private String userId;
	private String elementId;
	private Double quantity;
	private Date statisticsStartTime;
	private Date statisticsEndTime;
	private Date createTime;
	private String createBy;
	private Date lastModifyTime;
	private String lastModifyBy;
	private Long optTime;

	// Constructors

	/** default constructor */
	public TBfUserElementStatistics() {
	}

	/** minimal constructor */
	public TBfUserElementStatistics(String statisticsId, String userId,
			String elementId) {
		this.statisticsId = statisticsId;
		this.userId = userId;
		this.elementId = elementId;
	}

	/** full constructor */
	public TBfUserElementStatistics(String statisticsId, String userId,
			String elementId, Double quantity, Date statisticsStartTime,
			Date statisticsEndTime, Date createTime, String createBy,
			Date lastModifyTime, String lastModifyBy, Long optTime) {
		this.statisticsId = statisticsId;
		this.userId = userId;
		this.elementId = elementId;
		this.quantity = quantity;
		this.statisticsStartTime = statisticsStartTime;
		this.statisticsEndTime = statisticsEndTime;
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
	@Column(name = "statistics_id", unique = true, nullable = false, insertable = true, updatable = true, length = 32)
	public String getStatisticsId() {
		return this.statisticsId;
	}

	public void setStatisticsId(String statisticsId) {
		this.statisticsId = statisticsId;
	}

	@Column(name = "user_id", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "element_id", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
	public String getElementId() {
		return this.elementId;
	}

	public void setElementId(String elementId) {
		this.elementId = elementId;
	}

	@Column(name = "quantity", unique = false, nullable = true, insertable = true, updatable = true, precision = 4, scale = 3)
	public Double getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "statistics_start_time", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public Date getStatisticsStartTime() {
		return this.statisticsStartTime;
	}

	public void setStatisticsStartTime(Date statisticsStartTime) {
		this.statisticsStartTime = statisticsStartTime;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "statistics_end_time", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public Date getStatisticsEndTime() {
		return this.statisticsEndTime;
	}

	public void setStatisticsEndTime(Date statisticsEndTime) {
		this.statisticsEndTime = statisticsEndTime;
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
	public Long getOptTime() {
		return this.optTime;
	}

	public void setOptTime(Long optTime) {
		this.optTime = optTime;
	}

}
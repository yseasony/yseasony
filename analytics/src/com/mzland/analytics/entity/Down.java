package com.mzland.analytics.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author yseasony(yseasony@gmail.com)
 */
@Entity
@Table(name = "ma_down", catalog = "mz_analytics")
public class Down implements java.io.Serializable {

	private static final long serialVersionUID = -2512707190599647259L;
	private String multimediaId;
	private String titile;
	private Integer categoryId;
	private Integer downloadsTotal;
	private Timestamp createTime = new Timestamp(System.currentTimeMillis());
	private Timestamp lastModifyTime = new Timestamp(System.currentTimeMillis());

	@Id
	@Column(name = "multimedia_id", unique = true, nullable = false, length = 38)
	public String getMultimediaId() {
		return this.multimediaId;
	}

	public void setMultimediaId(String multimediaId) {
		this.multimediaId = multimediaId;
	}

	@Column(name = "titile", nullable = false, length = 150)
	public String getTitile() {
		return this.titile;
	}

	public void setTitile(String titile) {
		this.titile = titile;
	}

	@Column(name = "category_id", nullable = false)
	public Integer getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	@Column(name = "downloads_total", nullable = false)
	public Integer getDownloadsTotal() {
		return this.downloadsTotal;
	}

	public void setDownloadsTotal(Integer downloadsTotal) {
		this.downloadsTotal = downloadsTotal;
	}

	@Column(name = "create_time", nullable = false, length = 19)
	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Column(name = "last_modify_time", nullable = false, length = 19)
	public Timestamp getLastModifyTime() {
		return this.lastModifyTime;
	}

	public void setLastModifyTime(Timestamp lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}
}
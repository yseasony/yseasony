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
@Table(name = "ma_gamesoft_down", catalog = "mz_analytics")
public class GamesoftDown implements java.io.Serializable {

	private static final long serialVersionUID = -1938834200575320082L;
	private String gamesoftId;
	private String title;
	private Integer categoryId;
	private Integer downloadsTotal;
	private Timestamp createTime = new Timestamp(System.currentTimeMillis());
	private Timestamp lastModifyTime = new Timestamp(System.currentTimeMillis());

	@Id
	@Column(name = "gamesoft_id", unique = true, nullable = false, length = 36)
	public String getGamesoftId() {
		return this.gamesoftId;
	}

	public void setGamesoftId(String gamesoftId) {
		this.gamesoftId = gamesoftId;
	}

	@Column(name = "title", nullable = false, length = 50)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
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
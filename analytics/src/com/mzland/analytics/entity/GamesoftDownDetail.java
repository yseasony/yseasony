package com.mzland.analytics.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * 
 * @author yseasony(yseasony@gmail.com)
 */
@Entity
@Table(name = "ma_gamesoft_down_detail", catalog = "mz_analytics")
public class GamesoftDownDetail implements java.io.Serializable {

	private static final long serialVersionUID = 7672737048698533199L;
	private String detailId;
	private String gamesoftId;
	private String customerId;
	private Integer phoneModelId;
	private Timestamp downloadTime = new Timestamp(System.currentTimeMillis()) ;


	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "detail_id", unique = true, nullable = false, length = 32)
	public String getDetailId() {
		return this.detailId;
	}

	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}

	@Column(name = "phone_model_id", nullable = false)
	public Integer getPhoneModelId() {
		return this.phoneModelId;
	}

	public void setPhoneModelId(Integer phoneModelId) {
		this.phoneModelId = phoneModelId;
	}

	@Column(name = "download_time", nullable = false, length = 19)
	public Timestamp getDownloadTime() {
		return this.downloadTime;
	}

	public void setDownloadTime(Timestamp downloadTime) {
		this.downloadTime = downloadTime;
	}

	@Column(name = "gamesoft_id", nullable = false)
	public String getGamesoftId() {
		return gamesoftId;
	}

	public void setGamesoftId(String gamesoftId) {
		this.gamesoftId = gamesoftId;
	}

	@Column(name = "customer_id")
	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

}
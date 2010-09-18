package org.yseasony.blog.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tx")
public class Tx implements java.io.Serializable {

	private static final long serialVersionUID = 7694467104194088271L;
	private Integer id;
	private String value;

	public Tx() {}

	public Tx(String value) {
		this.value = value;
	}

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "value", nullable = false, length = 15)
	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
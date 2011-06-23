package org.yseasony.acg.entity;

import java.io.Serializable;
import java.util.List;

import com.google.common.collect.Lists;

public class Role implements Serializable{

	private static final long serialVersionUID = 3101134025201100284L;

	private Integer id;

    private String name;

    private List<Authority> authorities = Lists.newArrayList();
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

	public List<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}

    
}
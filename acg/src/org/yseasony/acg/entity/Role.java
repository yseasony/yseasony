package org.yseasony.acg.entity;

import java.util.List;

import com.google.common.collect.Lists;

public class Role {
    private Long id;

    private String name;

    private List<Authority> authorityList = Lists.newArrayList();
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
    
    public List<Authority> getAuthorityList() {
		return authorityList;
	}

	public void setAuthorityList(List<Authority> authorityList) {
		this.authorityList = authorityList;
	}
}
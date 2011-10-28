package org.yseasony.exam.entity;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.yseasony.exam.utils.JsonDateSerializer;

public class ExaminationPager implements Serializable{
    
    private static final long serialVersionUID = -1238225048006320354L;

    private Integer exId;

    private String title;

    private Date startTime;

    private Date endTime;

    private String invigilateName;

    private Date createTime = new Date();

    public Integer getExId() {
        return exId;
    }

    public void setExId(Integer exId) {
        this.exId = exId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    @JsonSerialize(using=JsonDateSerializer.class) 
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @JsonSerialize(using=JsonDateSerializer.class) 
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getInvigilateName() {
        return invigilateName;
    }

    public void setInvigilateName(String invigilateName) {
        this.invigilateName = invigilateName == null ? null : invigilateName.trim();
    }

    @JsonSerialize(using=JsonDateSerializer.class) 
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
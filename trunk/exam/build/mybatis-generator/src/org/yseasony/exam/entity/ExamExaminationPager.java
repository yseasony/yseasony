package org.yseasony.exam.entity;

import java.util.Date;

public class ExamExaminationPager {
    private Integer exId;

    private String title;

    private Date startTime;

    private Date endTime;

    private String invigilateName;

    private Date createTime;

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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
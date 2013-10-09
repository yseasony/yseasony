package org.yseasony.edm.entiy;

import java.io.Serializable;

public class EdmTask implements Serializable {

    private static final long serialVersionUID = -473109943184483521L;
    private Long              id;

    private String            name;

    private Long              taskStartTime;

    private Integer           isTest;

    private Integer           testNum;

    private Integer           taskSendNum;

    private Integer           sendSuccessNum;

    private String            sendConditions;

    private Integer           templateId;

    private Long              createTime;

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
        this.name = name.trim();
    }

    public Long getTaskStartTime() {
        return taskStartTime;
    }

    public void setTaskStartTime(Long taskStartTime) {
        this.taskStartTime = taskStartTime;
    }

    public Integer getTestNum() {
        return testNum;
    }

    public void setTestNum(Integer testNum) {
        this.testNum = testNum;
    }

    public Integer getTaskSendNum() {
        return taskSendNum;
    }

    public void setTaskSendNum(Integer taskSendNum) {
        this.taskSendNum = taskSendNum;
    }

    public Integer getSendSuccessNum() {
        return sendSuccessNum;
    }

    public void setSendSuccessNum(Integer sendSuccessNum) {
        this.sendSuccessNum = sendSuccessNum;
    }

    public String getSendConditions() {
        return sendConditions;
    }

    public void setSendConditions(String sendConditions) {
        this.sendConditions = sendConditions.trim();
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Integer getIsTest() {
        return isTest;
    }

    public void setIsTest(Integer isTest) {
        this.isTest = isTest;
    }

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }
}

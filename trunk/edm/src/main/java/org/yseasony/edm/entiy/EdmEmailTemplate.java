package org.yseasony.edm.entiy;

import java.io.Serializable;

public class EdmEmailTemplate implements Serializable {

    private static final long serialVersionUID = 4269448228094298736L;
    private Long              id;

    private String            name;

    private String            emailTitle;

    private String            emailHtml;

    private String            emailParameter;

    private Long              createTime;

    private String            fromName;

    private String            fromEmail;

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

    public String getEmailTitle() {
        return emailTitle;
    }

    public void setEmailTitle(String emailTitle) {
        this.emailTitle = emailTitle.trim();
    }

    public String getEmailHtml() {
        return emailHtml;
    }

    public void setEmailHtml(String emailHtml) {
        this.emailHtml = emailHtml.trim();
    }

    public String getEmailParameter() {
        return emailParameter;
    }

    public void setEmailParameter(String emailParameter) {
        this.emailParameter = emailParameter.trim();
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getFromEmail() {
        return fromEmail;
    }

    public void setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
    }
}

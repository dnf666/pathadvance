package com.path.model;

public class QuestionBasic {
    private Integer qId;

    private String qName;

    private String qDescript;

    private String qRem1;

    private String qRem2;

    public Integer getqId() {
        return qId;
    }

    public void setqId(Integer qId) {
        this.qId = qId;
    }

    public String getqName() {
        return qName;
    }

    public void setqName(String qName) {
        this.qName = qName == null ? null : qName.trim();
    }

    public String getqDescript() {
        return qDescript;
    }

    public void setqDescript(String qDescript) {
        this.qDescript = qDescript == null ? null : qDescript.trim();
    }

    public String getqRem1() {
        return qRem1;
    }

    public void setqRem1(String qRem1) {
        this.qRem1 = qRem1 == null ? null : qRem1.trim();
    }

    public String getqRem2() {
        return qRem2;
    }

    public void setqRem2(String qRem2) {
        this.qRem2 = qRem2 == null ? null : qRem2.trim();
    }
}
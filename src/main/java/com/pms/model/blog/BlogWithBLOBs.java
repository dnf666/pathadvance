package com.pms.model.blog;

public class BlogWithBLOBs extends Blog {
    private String context;//正文

    private String createTime;//创建时间

    private String delTime;//删除时间

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context == null ? null : context.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getDelTime() {
        return delTime;
    }

    @Override
    public String toString() {
        return "BlogWithBLOBs{" +
                "context='" + context + '\'' +
                ", createTime='" + createTime + '\'' +
                ", delTime='" + delTime + '\'' +
                '}';
    }

    public void setDelTime(String delTime) {

        this.delTime = delTime == null ? null : delTime.trim();
    }
}
package com.zhongziyue.pan.service.dto;

public class ActualFileDto {
    private Integer lid;
    private String target;
    private String name;
    private Boolean fileJudge;

    public ActualFileDto() {
    }

    public Integer getLid() {
        return lid;
    }

    public ActualFileDto setLid(Integer lid) {
        this.lid = lid;
        return this;
    }

    public String getTarget() {
        return target;
    }

    public ActualFileDto setTarget(String target) {
        this.target = target;
        return this;
    }

    public String getName() {
        return name;
    }

    public ActualFileDto setName(String name) {
        this.name = name;
        return this;
    }

    public Boolean getFileJudge() {
        return fileJudge;
    }

    public void setFileJudge(Boolean fileJudge) {
        this.fileJudge = fileJudge;
    }

    @Override
    public String toString() {
        return String.format("{lid=%d, target='%s', name='%s'}", lid, target, name);
    }
}

package com.practice.spboot.dto;

import java.time.format.DateTimeFormatter;

import com.practice.spboot.domain.BaseEntity;

public abstract class BaseDto {

    private Long idx;
    
    private String regdate;

    private String moddate;
    
    protected BaseDto() {
    }

    protected <T extends BaseEntity> BaseDto(T entity) {
        this.idx = entity.getIdx();
        this.regdate = entity.getRegdate().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
        this.moddate = entity.getModdate().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
    }

    public Long getIdx() {
        return idx;
    }

    public void setId(Long idx) {
        this.idx = idx;
    }

    public String getRegdate() {
        return regdate;
    }

    public String getModdate() {
        return moddate;
    }

}

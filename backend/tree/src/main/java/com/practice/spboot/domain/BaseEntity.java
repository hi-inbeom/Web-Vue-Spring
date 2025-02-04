package com.practice.spboot.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass // idx 값을 매핑해주는 역할
@EntityListeners(AuditingEntityListener.class) // regdate와 moddate의 시간이 자동 저장되도록 도와줌
public class BaseEntity {
	
	@Id // pk 키 정의
	@GeneratedValue // pk값이 부여되는 방식을 설정함, 자동으로 DB단에 시퀀스 혹은 아이덴티티가 설정됨, properties에 의해 자동 생성 여부 제어 가능
	private Long idx;
	
    @CreatedDate
    @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
    private LocalDateTime regdate;

    @LastModifiedDate
    @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
    private LocalDateTime moddate;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private Statuses status;

    protected BaseEntity() {
    	this.status = Statuses.ACTIVATE;
    }

    protected BaseEntity(Long idx) {
    	this();
        this.idx = idx;
    }

    public Long getIdx() {
        return idx;
    }

    public LocalDateTime getRegdate() {
        return regdate;
    }

    public LocalDateTime getModdate() {
        return moddate;
    }
    
    public Statuses getStatus() {
    	return status;
    }
    
    @Override
    public String toString() {
        return "[idx=" + idx + ", regdate=" + regdate + ", moddate=" + moddate + ", status=" + status + "]";
    }
}

package com.practice.spboot.domain.board;

import com.practice.spboot.domain.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Reply extends BaseEntity {	
	@Column(nullable=false)
	private String boardId;
	@Column(nullable=false)
	private String userId;
	@Column(nullable=false)
	private String parentReplyId;
	@Column(nullable=false)
	private String replyContent;
	
	public Reply(Long idx) {
		super(idx);
	}

	public Reply(String boardId, String userId, String parentReplyId, String replyContent) {
		this.boardId = boardId;
		this.userId = userId;
		this.parentReplyId = parentReplyId;
		this.replyContent = replyContent;
	}

	@Override
	public String toString() {
		return "Reply [boardId=" + boardId + ", userId=" + userId + ", parentReplyId=" + parentReplyId
				+ ", replyContent=" + replyContent + "]";
	}
	
	
}

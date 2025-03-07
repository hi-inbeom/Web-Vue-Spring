package com.practice.spboot.dto.board;

import java.util.List;
import java.util.stream.Collectors;

import com.practice.spboot.domain.board.Reply;
import com.practice.spboot.dto.BaseDto;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ReplyDto extends BaseDto {
	@Size(min = 1, message = "정상적인 접근이 아닙니다.")
	private String boardId;
	@Size(min = 1, message = "로그인이 되어있지 않습니다.")
	private String userId;
	private String parentReplyId;
	@Size(min = 1, message = "댓글 내용을 작성해주세요.")
	private String replyContent;
	

	public ReplyDto(Reply reply) {
		super(reply);
		this.boardId = reply.getBoardId();
		this.userId = reply.getUserId();
		this.parentReplyId = reply.getParentReplyId();
		this.replyContent = reply.getReplyContent();
	}
	
	public static ReplyDto of(Reply reply) {
		return new ReplyDto(reply);
	}
	
	public static List<ReplyDto> of(List<Reply> replys) {
	    return replys.stream()
                .map(ReplyDto::new)
                .collect(Collectors.toList());
	}
	
	public Reply toEntity() {
		return new Reply(boardId, userId, parentReplyId, replyContent);
	}
	
	@Override
	public String toString() {
		return "ReplyDto [boardId=" + boardId + ", userId=" + userId + ", parentReplyId=" + parentReplyId
				+ ", replyContent=" + replyContent + "]";
	}
	

}

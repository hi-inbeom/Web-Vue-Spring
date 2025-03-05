package com.practice.spboot.domain.board;

import com.practice.spboot.domain.BaseEntity;
import com.practice.spboot.dto.board.BoardDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Board extends BaseEntity{
	@Column(nullable=false)
	private String boardTitle;
	
	@Column(nullable=false)
	private String boardContent;
	
	@Column(nullable=false)
	private String userId;
	
	public Board(Long idx) {
		super(idx);
	}
	
	public Board(String boardTitle, String boardContent, String userId) {
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.userId = userId;
	}
	
	public Board() {
	}
	

	public void update(BoardDto boardDto) {
		this.boardTitle = boardDto.getBoardTitle();
		this.boardContent = boardDto.getBoardContent();
	}
	
	
	@Override
	public String toString() {
		return "Board [boardTitle=" + boardTitle + ", boardContent=" + boardContent
				+ ", userId=" + userId + "]";
	}	
}

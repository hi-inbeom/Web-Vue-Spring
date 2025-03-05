package com.practice.spboot.dto.board;

import java.util.List;
import java.util.stream.Collectors;

import com.practice.spboot.domain.board.Board;
import com.practice.spboot.dto.BaseDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class BoardDto extends BaseDto {
	@Size(min = 1, message = "제목을 입력해주세요.")
	private String boardTitle;
	@Size(min = 1, message = "내용을 입력해주세요.")
	private String boardContent;
	@NotBlank(message = "비정상적인 접근 방식입니다. 로그인을 해주세요.")
	private String userId;
	
	public BoardDto(Board board) {
		super(board);
		this.boardTitle = board.getBoardTitle();
		this.boardContent = board.getBoardContent();
		this.userId = board.getUserId();
	}
	
	public static BoardDto of(Board board) {
		return new BoardDto(board);
	}
	
	public static List<BoardDto> of(List<Board> boards) {
	    return boards.stream()
	                 .map(BoardDto::new)
	                 .collect(Collectors.toList());
	}
	
	public Board toEntity() {
		return new Board( boardTitle, boardContent, userId);
	}
}

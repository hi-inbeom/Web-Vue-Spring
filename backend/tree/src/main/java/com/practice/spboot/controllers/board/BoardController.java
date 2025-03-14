package com.practice.spboot.controllers.board;

import java.awt.print.Pageable;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.practice.spboot.dto.board.BoardDto;
import com.practice.spboot.service.board.BoardService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
	
	private final BoardService boardService;
	
	// 게시글 전부 가져오기
//	@GetMapping("/")
//	public List<BoardDto> boardList(@RequestParam String orderby) {
//		return boardService.findAll();
//	}
	
	@GetMapping("/")
	public void boardList(@RequestParam("page") String page) {
	    boardService.findAll(page);
	}

	// 게시글 작성하기
	@PostMapping("/write/save")
	public void boardWrite(@RequestBody @Valid BoardDto boardDto) {
		boardService.save(boardDto);
	}
	
	// 게시글 수정하기
	@PutMapping("/modify")
	public void boardUpdate(@RequestBody @Valid BoardDto boardDto) {
		boardService.modify(boardDto);
	}
	
	// 게시글 삭제하기
	@DeleteMapping("/delete")
	public void boardDelete(@RequestBody @Valid BoardDto boardDto) {
		boardService.delete(boardDto);
	}
	
	// 게시글 자세히보기
	@GetMapping("/detail/{idx}")
	public BoardDto boardDetail(@PathVariable("idx") Long idx) {
		return boardService.findById(idx);
	}
}

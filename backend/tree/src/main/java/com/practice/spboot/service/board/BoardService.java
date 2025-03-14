package com.practice.spboot.service.board;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.practice.spboot.domain.board.Board;
import com.practice.spboot.domain.board.BoardRepository;
import com.practice.spboot.dto.board.BoardDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	
	private final BoardRepository boardRepository;
	
	public List<BoardDto> findAll(String page) {
	    if (page == null || page.isEmpty()) {
	        page = "1";
	    }
		int currentPage = Integer.parseInt(page);
		int pageLimit = 10;
		int start = (pageLimit * currentPage) - (pageLimit - 1);
		int end = (pageLimit * currentPage);
		
		
		
        return boardRepository.findAll()
                .stream()
                .map(BoardDto::of)
                .collect(Collectors.toList());
	}
	
	public void save(BoardDto boardDto) {
		boardRepository.save(boardDto.toEntity());
	}
	
	@Transactional
	public void modify(BoardDto boardDto) {
		Board board = boardRepository.findById(boardDto.getIdx())
				.orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));
		System.out.println(board.toString());
		board.update(boardDto);
		System.out.println(board.toString());
	}

	public void delete(@Valid BoardDto boardDto) {
		boardRepository.deleteById(boardDto.getIdx());
	}

	public BoardDto findById(Long idx) {
	    Board board = boardRepository.findById(idx)
	        .orElseThrow(() -> new IllegalArgumentException("해당 게시글을 찾을 수 없습니다. ID: " + idx));

	    return BoardDto.of(board);
	}

}
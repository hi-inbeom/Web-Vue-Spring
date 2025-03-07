//package com.practice.spboot.service.board;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//import org.springframework.stereotype.Service;
//
//import com.practice.spboot.domain.board.ReplyRepository;
//import com.practice.spboot.dto.board.ReplyDto;
//
//import lombok.RequiredArgsConstructor;
//
//@Service
//@RequiredArgsConstructor
//public class ReplyService {
//	
//	private final ReplyRepository replyRepository;
//	
//	public List<ReplyDto> findById(Long idx) {
//        return replyRepository.findByBoardId(idx)
//                .stream()
//                .map(ReplyDto::of)
//                .collect(Collectors.toList());
//	}
//
//}

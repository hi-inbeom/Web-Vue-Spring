//package com.practice.spboot.controllers.board;
//
//import java.util.List;
//
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.practice.spboot.dto.board.ReplyDto;
//import com.practice.spboot.service.board.ReplyService;
//
//import lombok.RequiredArgsConstructor;
//
//@RestController
//@RequestMapping("/reply")
//@RequiredArgsConstructor
//public class ReplyController {
//	
//	private final ReplyService replyService;
//	
//	@GetMapping("/{:idx}")
//	public List<ReplyDto> getReply(@PathVariable("idx") Long idx ) {
//		return replyService.findById(idx);
//	}
//
//}

package com.practice.spboot.jspController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// RestController의 경우 JSON으로 응답을 반환하려고 하기 떄문에 Controller를 사용해야 됨.
@Controller
@RequestMapping("/api/v1")
public class MainController {
	@GetMapping("/")
	public String index() {
		return "main";
	}

}

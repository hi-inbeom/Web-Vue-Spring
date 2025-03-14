package com.practice.spboot.dto.board;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class SearchOptionDto {
	private String orderby;
	private String page;
	private String command;
}

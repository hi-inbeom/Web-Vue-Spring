package com.practice.spboot.domain;

// activate = 활성 상태로 유저에게 보여도 되는 상태
// inactivate = 비활성 상태로 유저에게 보이면 안되는 상태, 신고 게시물의 경우 판단 후 ACTIVATE로 전환시키는 형식, 관리자 게시판 만들면 사용될듯
public enum Statuses {
	ACTIVATE,
	INACTIVATE;
}

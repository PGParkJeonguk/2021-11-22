package co.micol.prj.member.service;

import java.util.List;

public interface MemberService {
	List<MemberVO> memberSelectList();
	MemberVO memberSelect(MemberVO vo);	//login과 멤버서치, 1명의 데이터 가죠오기
	int memberInsert(MemberVO vo);
	int memberDelete(MemberVO vo);
	int memberUpdate(MemberVO vo);
	boolean memberIdCheck(MemberVO vo);	//id 중복체크를 위해서 만듬
	int memberAuthorUpdate(MemberVO vo);	//멤버권한변경.	
}

package co.micol.prj.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.micol.prj.comm.Command;
import co.micol.prj.member.service.MemberService;
import co.micol.prj.member.service.MemberServiceImpl;
import co.micol.prj.member.service.MemberVO;

public class MemberInfo implements Command {

	@Override
	public String run(HttpServletRequest request, HttpServletResponse response) {
	      // 회원정보 보기
	      HttpSession session = request.getSession();
	      MemberService memberDao = new MemberServiceImpl();
	      MemberVO vo = new MemberVO();
	      vo.setId((String)session.getAttribute("id")); // 세션에서 불러오면 object 타입이라 string으로 boxing 해줘야한다.
	      request.setAttribute("member", memberDao.memberSelect(vo));
	      
	      return "member/memberInfo";
	   }


}

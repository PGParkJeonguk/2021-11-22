package co.micol.prj.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.prj.comm.Command;
import co.micol.prj.member.service.MemberService;
import co.micol.prj.member.service.MemberServiceImpl;
import co.micol.prj.member.service.MemberVO;

public class MemberIdCheck implements Command {

	@Override
	public String run(HttpServletRequest request, HttpServletResponse response) {
		//아이디 중복 체크(ajax를 이용한)
		MemberService memberDao = new MemberServiceImpl();
		MemberVO vo = new MemberVO();
		vo.setId(request.getParameter("chkid"));
		boolean b = memberDao.memberIdCheck(vo);	//true 존재, false 사용가능
		String chk = "0";
		if(b) {	//존재하는지 안하는지 확인하는 작업. 
			chk = "1";
		}
		return "ajax:"+chk;
	}

}

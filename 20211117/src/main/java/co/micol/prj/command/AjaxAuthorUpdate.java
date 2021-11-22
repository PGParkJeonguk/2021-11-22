package co.micol.prj.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.prj.comm.Command;
import co.micol.prj.member.service.MemberService;
import co.micol.prj.member.service.MemberServiceImpl;
import co.micol.prj.member.service.MemberVO;

public class AjaxAuthorUpdate implements Command {
	private String result;
	@Override
	public String run(HttpServletRequest request, HttpServletResponse response) {
		// 멤버 권한 변경.
		MemberService memberDao = new MemberServiceImpl();
		MemberVO vo = new MemberVO();
		vo.setId(request.getParameter("id"));
		vo.setAuthor(request.getParameter("author"));
		int n = memberDao.memberAuthorUpdate(vo);
		if(n !=0) {
			result = "yes";
		}else {
			result = "no";
		}
		
		return "ajax:" + result;
		
	}

}

package co.micol.prj.comm;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.prj.command.AjaxAuthorUpdate;
import co.micol.prj.command.CommonFileUpload;
import co.micol.prj.command.AjaxFileDownLoad;
import co.micol.prj.command.HomeCommand;
import co.micol.prj.command.Logout;
import co.micol.prj.command.MemberDelete;
import co.micol.prj.command.MemberEditSave;
import co.micol.prj.command.MemberIdCheck;
import co.micol.prj.command.MemberInfo;
import co.micol.prj.command.MemberJoin;
import co.micol.prj.command.MemberJoinForm;
import co.micol.prj.command.MemberList;
import co.micol.prj.command.MemberLogin;
import co.micol.prj.command.MemberLoginForm;
import co.micol.prj.command.MemberUpdate;
import co.micol.prj.command.NoticeForm;
import co.micol.prj.command.NoticeList;
import co.micol.prj.command.NoticeRead;
import co.micol.prj.command.NoticeResister;
import co.micol.prj.command.ServletApIUpload;

@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HashMap<String, Command> map = new HashMap<String, Command>();
	
    public FrontController() {
        super();

    }


	public void init(ServletConfig config) throws ServletException {
		// Command 그룹들을 map.put을 이용해서 넣어준다.
		// Command들의 요청을 처리할수 있도록 메모리에 구성하는것.
		map.put("/home.do", new HomeCommand());	//홈 페이지 
		map.put("/memberLoginForm.do", new MemberLoginForm()); //로그인 폼 호출
		map.put("/memberLogin.do", new MemberLogin());	//로그인처리
		map.put("/logout.do", new Logout());	//로그아웃
		map.put("/memberList.do", new MemberList());	//멤버리스트
		map.put("/memberJoinForm.do", new MemberJoinForm());	//회원가입 폼 호출
		map.put("/ajaxIdCheck.do", new MemberIdCheck());	//아이디 중복 체크
		map.put("/memberJoin.do", new MemberJoin());	//회원가입처리
		map.put("/memberInfo.do", new MemberInfo());	//회원정보
		map.put("/memberUpdate.do", new MemberUpdate()); //회원정보수정
		map.put("/memberEditSave.do", new MemberEditSave());	//회원정보변경저장
		map.put("/memberDelete.do", new MemberDelete()); //회원탈퇴
		map.put("/ajaxAuthorUpdate.do", new AjaxAuthorUpdate());	//회권 권한 변경.
		map.put("/noticeForm.do", new NoticeForm());
		map.put("/noticeList.do", new NoticeList());
		//map.put("/noticeResister.do", new NoticeResister());
		//map.put("/noticeResister.do", new ServletApIUpload());	// 공지사항 저장 Servle Part 사용
		map.put("/noticeResister.do", new CommonFileUpload());	//공지사항 저정 common-fileupload 사용.
		map.put("/noticeRead.do", new NoticeRead());	//공지사항 상세보기
		map.put("/ajaxFileDownLoad.do", new AjaxFileDownLoad()); // 파일 다운로드
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 요청을 분석하고 실행할 명열을 찾아 수행하고 결과를 돌려주는 메소드.
		request.setCharacterEncoding("UTF-8");
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String page = uri.substring(contextPath.length());
		
		Command command = map.get(page);	//new HomeCommand()
		String viewPage = command.run(request, response);	//값을 들고 오고
		
		if(!viewPage.endsWith(".do")) {
			if(viewPage.startsWith("ajax:")) {		//ajax 처리
				response.setContentType("text/html; charset=UTF-8");
				response.getWriter().append(viewPage.substring(5));
				return;
			}else {
				viewPage = "WEB-INF/views/" + viewPage + ".jsp";
			}
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response); //값을 들고 가서 홈페이지에 보여주고.
		
	}

}

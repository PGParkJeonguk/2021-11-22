package co.micol.prj.command;

import java.io.File;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import co.micol.prj.comm.Command;
import co.micol.prj.notice.service.NoticeService;
import co.micol.prj.notice.service.NoticeServiceImpl;
import co.micol.prj.notice.service.NoticeVO;

public class NoticeResister implements Command {
	private String filePath="C:\\FileTest";		//파일이 저장되는 절대경로
	private int fileSize = 1024*1024*100;		//파일 최대 사이즈(100M)
	@Override	
	public String run(HttpServletRequest request, HttpServletResponse response) {
		//공지사항 저장
		NoticeService noticeDao = new NoticeServiceImpl();
		NoticeVO vo = new NoticeVO();
		HttpSession session = request.getSession();
		vo.setId((String) session.getAttribute("id"));	//세션에 저장된 아이디 값.
		vo.setName((String) session.getAttribute("name")); //세션에 저장된 이름 값.
		try {
			 MultipartRequest multi = new MultipartRequest(request, filePath, fileSize, "UTF-8", new DefaultFileRenamePolicy());
			//filename은 중복이름이 들어올 경우 자동으로 index가 있는 물리파일명.
			String fileName = multi.getFilesystemName("fileName");
			//index 되기 전의 원본명.
			String original = multi.getOriginalFileName("fileName");	//원본 파일 명
			fileName = filePath + File.separator + fileName;	//저장경로를 포함해서 만듬 c:/FileTest/fileName
			if(original != null) {
				vo.setFileName(original);	//원본
				vo.setPfileName(fileName);	//물리파일명
			}else {
				vo.setFileName("");
				vo.setPfileName("");
			}
				vo.setWdate(Date.valueOf(multi.getParameter("wdate")));
				vo.setTitle(multi.getParameter("title"));
				vo.setSubject(multi.getParameter("subject"));
			noticeDao.noticeInsert(vo);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "noticeList.do";
	}

}

package co.micol.prj.command;

import java.io.File;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import co.micol.prj.comm.Command;
import co.micol.prj.notice.service.NoticeService;
import co.micol.prj.notice.service.NoticeServiceImpl;
import co.micol.prj.notice.service.NoticeVO;

public class CommonFileUpload implements Command {

	private String fileSave = "C:\\FileTest"; // 개발시 업로드 파일 저장공간. 실제 저장소 
	//private String fileSave = "fileUpload"; //운영서버에 실제 동작환경을 꾸밀때

	@Override
	public String run(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> map = new HashMap<String, String>();
		NoticeService noticeDao = new NoticeServiceImpl();
		NoticeVO vo = new NoticeVO();
		HttpSession session = request.getSession();
		vo.setId((String) session.getAttribute("id")); // 세션에 저장된 아이디 값.
		vo.setName((String) session.getAttribute("name")); // 세션에 저장된 이름 값.
		String fileName = null;	//파일명
		String pfileName = null;	//물리 파일명
		
		DiskFileItemFactory fileItemFactory = new DiskFileItemFactory(); // 파일저장소 관련 정보
		ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory); // request 객체 parse

		try {
			List<FileItem> items = fileUpload.parseRequest(request);
			// FileItem 객체는 폼에서 넘어온 multipart 객체형식을 다루는 객체
			for (FileItem item : items) {
				if (item.isFormField()) {
					map.put(item.getFieldName(), item.getString("utf-8"));
				} else if(!item.isFormField() && item.getSize() > 0){
					int index = item.getName().lastIndexOf(File.separator); // 마지막 \의 위치
					fileName = item.getName().substring(index + 1); // 실 파일명만 추출
					String extension = fileName.substring(fileName.lastIndexOf("."),fileName.length());//파일확장자명 찾기.
					UUID uuid = UUID.randomUUID();	//고유한 UUID생성
					String newFileName = uuid.toString() + extension;//	UUID를 통한 새로운 파일명으로 변환
					pfileName = fileSave + File.separator + newFileName; // c:\\FileTest\파일명
//					map.put("fileName", fileName);	//원본명 담기
//					map.put("pfileName", pfileName);	//물리파일명 담기
					File uploadFile = new File(pfileName); // 파일을 열어서 읽고.
					item.write(uploadFile); // 파일 업로드가 일어남.
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 이곳에 데이터 처리해야할것을 넣어주는곳.
		vo.setFileName(fileName); // 원본
		vo.setPfileName(pfileName); // 물리파일명
		vo.setWdate(Date.valueOf(map.get("wdate")));
		vo.setTitle(map.get("title"));
		vo.setSubject(map.get("subject"));
		noticeDao.noticeInsert(vo);
		return "noticeList.do";
	}

}

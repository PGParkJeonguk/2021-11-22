package co.micol.prj.comm;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AjaxFiledown
 */
@WebServlet("/AjaxFiledown")
public class AjaxFiledown extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AjaxFiledown() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String fileName = request.getParameter("fileName");
		String pfileName = request.getParameter("pfileName");

		System.out.println(fileName + "=======" + pfileName);
		// 파일다운로드 로직 작성
		InputStream in = null;
		OutputStream out = null;
		File file = null;
		try {
			file = new File(pfileName); // 물리위치에서 파일을 선택하고
			in = new FileInputStream(file); // 읽어 줌
			fileName = new String(fileName.getBytes("utf-8"), "ISO-8859-1");// 한글처리

			response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
			out = response.getOutputStream(); // response 객체로 초기화
			byte b[] = new byte[(int) file.length()]; // 메모리에 파일을 담음
			int leng = 0;
			while ((leng = in.read(b)) > 0) { // 실제 다운로드 함.
				out.write(b, 0, leng);
			}

			in.close(); // 반드시 닫아 준다.
			out.close(); // 반드시 닫아 준다.
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.getWriter().print("OK");
	}
}

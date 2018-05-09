package board;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import model.Action;
import model.ActionData;
import model.BoardDAO;
import model.BoardVO;

public class FileDelete implements Action {

	@Override
	public ActionData execute(HttpServletRequest request, HttpServletResponse response) {
		
		BoardVO vo = new BoardVO();
		vo.setTitle(request.getParameter("title"));
		vo.setContent(request.getParameter("content"));
		vo.setPname(request.getParameter("pname"));
		vo.setSeq(Integer.parseInt(request.getParameter("seq")));
		vo.setId(Integer.parseInt(request.getParameter("id")));
		vo.setPw(request.getParameter("pw"));
		vo.setUpfile(request.getParameter("upfile"));
		
		BoardDAO dao = new BoardDAO();
		BoardVO res = dao.search(vo);
		
		String msg = "파일삭제 실패";

		if(res != null) {
			
			String path = request.getRealPath("up")+"\\";
			path = "C:\\Users\\85jbb\\mvcReview\\Review\\WebContent\\up\\";
			new File(path + vo.getUpfile()).delete();
			
			dao.fileDelete(vo.getId());
			vo.setUpfile("");
			msg = "파일 삭제 성공";

		}
		dao.close();
		
		request.setAttribute("msg", msg);
		request.setAttribute("data", vo);
		request.setAttribute("main", "modifyForm.jsp");
		
		return new ActionData();
	}
}

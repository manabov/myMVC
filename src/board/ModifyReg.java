package board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Action;
import model.ActionData;
import model.BoardDAO;
import model.BoardVO;

public class ModifyReg implements Action {

	@Override
	public ActionData execute(HttpServletRequest request, HttpServletResponse response) {
		
		BoardVO vo = new BoardVO();
		vo.setId(Integer.parseInt(request.getParameter("id")));
		vo.setPw(request.getParameter("pw"));
		
		BoardDAO dao = new BoardDAO();
		
		String msg = "인증실패";
		String url = "ModifyReg?id="+vo.getId();
		if(dao.search(vo) != null) {
			
			vo.setPname(request.getParameter("pname"));
			vo.setTitle(request.getParameter("title"));
			vo.setContent(request.getParameter("content"));
			
			if(request.getParameter("upfile") != null) {
				vo.setUpfile(request.getParameter("upfile"));
			} else {
				vo.setUpfile(new InsertReg().fileUpload(request));
			}
			
			dao.modify(vo);
			msg = "수정 성공";
			url = "Detail?id="+vo.getId();
			
		}
		
		dao.close();
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		request.setAttribute("main", "alert.jsp");
		
		return new ActionData();
	}

}

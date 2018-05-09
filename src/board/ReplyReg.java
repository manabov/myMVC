package board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Action;
import model.ActionData;
import model.BoardDAO;
import model.BoardVO;

public class ReplyReg implements Action {

	@Override
	public ActionData execute(HttpServletRequest request, HttpServletResponse response) {

		BoardVO vo = new BoardVO();
		vo.setId(Integer.parseInt(request.getParameter("id")));
		vo.setPname(request.getParameter("pname"));
		vo.setTitle(request.getParameter("title"));
		vo.setContent(request.getParameter("content"));
		vo.setPw(request.getParameter("pw"));
		
		BoardDAO dao = new BoardDAO();
		int id = dao.reply(vo);
		dao.close();
		
		ActionData data = new ActionData();
		data.setRedirect(true);
		data.setPath("Detail?id="+id);
		return data;
	}
}
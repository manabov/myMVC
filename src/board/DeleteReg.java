package board;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Action;
import model.ActionData;
import model.BoardDAO;
import model.BoardVO;

public class DeleteReg implements Action {

	@Override
	public ActionData execute(HttpServletRequest request, HttpServletResponse response) {
		
		BoardVO vo = new BoardVO();
		vo.setId(Integer.parseInt(request.getParameter("id")));
		vo.setPw(request.getParameter("pw"));
		
		BoardDAO dao = new BoardDAO();
		BoardVO res = dao.search(vo);
		
		String msg = "삭제 실패";
		String url = "DeleteForm?id="+vo.getId();
		if(res != null) {
			
			if(!res.getUpfile().equals("")) {
				
				String path = request.getRealPath("up")+"\\";
				path = "C:\\Users\\85jbb\\mvcReview\\Review\\WebContent\\up\\";
				new File(path + res.getUpfile()).delete();
				
			}
			
			dao.delete(vo.getId());
			msg = "삭제 성공";
			url = "List";
			
		}
		dao.close();
		
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		request.setAttribute("main", "alert.jsp");
		return new ActionData();
	}

}

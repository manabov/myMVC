package board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Action;
import model.ActionData;
import model.BoardDAO;

public class List implements Action {

	@Override
	public ActionData execute(HttpServletRequest request, HttpServletResponse response) {
		
		int page=1, limit = 3, pageLimit = 4;
		
		BoardDAO dao = new BoardDAO();
		
		if(request.getParameter("id") != null && !request.getParameter("id").equals("")) {
			int rnum = dao.getRnum(Integer.parseInt(request.getParameter("id")));
			page = rnum / limit;
			
			if(rnum % limit != 0) {
				page++;
			}
		}
		
		if(request.getParameter("page") != null && !request.getParameter("page").equals("")) {
			page=Integer.parseInt(request.getParameter("page"));
		}
		
		int start = (page-1) * limit + 1;
		int end = page * limit;
		
		int startPage = (page-1)/pageLimit * pageLimit + 1;
		int endPage = startPage + pageLimit - 1;
		
		int total = dao.totalCnt();
		int totalPage = total/limit;
		
		if( total % limit != 0) {
			totalPage++;
		}
		
		if(endPage > totalPage) {
			endPage = totalPage;
		}
		
		request.setAttribute("page", page);
		request.setAttribute("start", start);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("totalPage", totalPage);
		
		request.setAttribute("data", dao.list(start, end));
		dao.close();
		request.setAttribute("main", "list.jsp");
		return new ActionData();
	}
}

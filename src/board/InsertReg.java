package board;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.Action;
import model.ActionData;
import model.BoardDAO;
import model.BoardVO;

public class InsertReg implements Action {

	@Override
	public ActionData execute(HttpServletRequest request, HttpServletResponse response) {
		
		
		BoardDAO dao = new BoardDAO();
		BoardVO vo = new BoardVO();
		
		vo.setPname(request.getParameter("pname"));
		vo.setTitle(request.getParameter("title"));
		vo.setPw(request.getParameter("pw"));
		vo.setContent(request.getParameter("content"));
		
		vo.setUpfile(fileUpload(request));
		
		int id = dao.insert(vo);
		dao.close();
		ActionData data = new ActionData();
		data.setRedirect(true);
		data.setPath("Detail?id="+id);
		
		return data;
	}
	
	public String fileUpload(HttpServletRequest request) {
		
		try {
			Part part = request.getPart("upfile");
			
			if(part.getContentType() != null) {
				String fileName = "";
				for(String hh : part.getHeader("Content-Disposition").split(";")) {
					if(hh.trim().startsWith("filename")) {
						fileName = hh.substring(hh.indexOf("=")+1).trim().replaceAll("\"", "");
					}
				}
				
				if(!fileName.equals("")) {
					return fileSave(part, request, fileName);
				}
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	
		return "";
	}
	
	String fileSave(Part part, HttpServletRequest request, String fileName) {
		
		int pos = fileName.lastIndexOf(".");
		String fileDo = fileName.substring(0, pos);
		String exp = fileName.substring(pos);
		int cnt = 0;
		
		String path = request.getRealPath("up")+"\\";
		path = "C:\\Users\\85jbb\\mvcReview\\Review\\WebContent\\up\\";
		File file= new File(path+fileName);
		
		while(file.exists()) {
			fileName = fileDo + "_" + (cnt++) + exp;
			file = new File(path + fileName);
			
		}
		
		try {
			part.write(path+fileName);
			part.delete();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return fileName;
	}

}

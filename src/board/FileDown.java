package board;

import java.io.FileInputStream;
import java.net.URLEncoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Action;
import model.ActionData;

public class FileDown implements Action {

	@Override
	public ActionData execute(HttpServletRequest request, HttpServletResponse response) {
	
		String path = request.getRealPath("up")+"\\";
		path = "C:\\Users\\85jbb\\mvcReview\\Review\\WebContent\\up";
		String fileName = request.getParameter("file");
		
		
		try {
			String en = URLEncoder.encode(fileName, "utf-8");
			response.setHeader("Content-Disposition", "attachment;filename="+en);
			
			ServletOutputStream sos = response.getOutputStream();
			FileInputStream fis = new FileInputStream(path + fileName);
			
			
			byte[] buf = new byte[1024];
			
			while(fis.available() > 0) {
				sos.write(buf, 0, fis.read(buf));
				
			}
			sos.close();
			fis.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}

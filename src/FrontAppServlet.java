import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FrontAppServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		resp.setContentType("text/html; charset=EUC-KR");
		
		String reqUri = req.getRequestURI();
		String upPath = req.getContextPath();
		String cmd = reqUri.substring(upPath.length());
		
		String nextPage = null;
		if (cmd.equals("/board.do")) {
			nextPage = "list.board";
		}else if (cmd.equals("/member.do")) {
			nextPage = "index.mem";
		}else if (cmd.equals("/login.do")) {
			nextPage = "WEB-INF/jsp/login/login.jsp";
		}
		
		RequestDispatcher view = req.getRequestDispatcher(nextPage);
		view.forward(req, resp);
		//PrintWriter pw = resp.getWriter();
		//pw.println("여기까지 오면 성공!! "+ cmd);
	}
	
}











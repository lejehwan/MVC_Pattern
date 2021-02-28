package board;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteProBoardCommand implements BoardCommandIf{

	@Override
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String num = req.getParameter("num");
		String passwd = req.getParameter("passwd");
		
		BoardDataBean dao = new BoardDataBean();
		int res = 0;
		try {
			res = dao.deleteBoard(Integer.parseInt(num), passwd);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		String msg = null, url = null;
		if (res>0) {
			msg ="success.";
			url = "list.board";
		}else if (res<0) {
			msg ="fail";
			url = "writeForm.board?num="+num;
		}else {
			msg ="db error";
			url = "content.board?num="+num;
		}
		req.setAttribute("msg", msg);
		req.setAttribute("url", url);
		return "message.jsp";
	}

}















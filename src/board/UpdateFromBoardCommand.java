package board;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateFromBoardCommand implements BoardCommandIf{

	@Override
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String num = req.getParameter("num");
		BoardDBBean dto = null;
		BoardDataBean dao = new BoardDataBean();
		try {
			dto = dao.getBoard(Integer.parseInt(num));
		}catch(SQLException e) {
			e.printStackTrace();
		}
		req.setAttribute("getBoard", dto);
		return "WEB-INF/jsp/board/updateForm.jsp";
	}

}
















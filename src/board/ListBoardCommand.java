package board;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListBoardCommand implements BoardCommandIf{

	@Override
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		BoardDataBean dao = new BoardDataBean();
		List<BoardDBBean> list = null;
		try {
			list = dao.listBoard();
		}catch(SQLException e) {
			System.out.println("list 실행 중 오류 발생!!");
			e.printStackTrace();
		}
		req.setAttribute("list", list);
		return "WEB-INF/jsp/board/list.jsp";
	}

}








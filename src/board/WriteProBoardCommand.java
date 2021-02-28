package board;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WriteProBoardCommand implements BoardCommandIf {

	@Override
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		BoardDBBean dto = new BoardDBBean();
		dto.setWriter(req.getParameter("writer"));
		dto.setEmail(req.getParameter("email"));
		dto.setSubject(req.getParameter("subject"));
		dto.setPasswd(req.getParameter("passwd"));
		dto.setContent(req.getParameter("content"));
		dto.setIp(req.getRemoteAddr());
		BoardDataBean dao = new BoardDataBean();
		String msg = null, url = null;
		try {
			int res = dao.insertBoard(dto);
			if (res>0) {
				msg = "�Խñ۵�ϼ���!! �Խñ۸���������� �̵��մϴ�.";
				url = "list.board";
			}else {
				msg = "�Խñ۵�Ͻ���!! �Խñ۵���������� �̵��մϴ�.";
				url = "writeForm.board";
			}
		}catch(SQLException e) {
			e.printStackTrace();
			msg = "�Խñ۵�������� ���� �� DB���� ���� �߻�!!";
			url = "list.board";
		}
		req.setAttribute("msg", msg);
		req.setAttribute("url", url);
		return "message.jsp";
	}

}









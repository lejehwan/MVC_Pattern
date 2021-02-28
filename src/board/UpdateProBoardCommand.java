package board;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateProBoardCommand implements BoardCommandIf{

	@Override
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		BoardDBBean dto = new BoardDBBean();
		dto.setNum(Integer.parseInt(req.getParameter("num")));
		dto.setWriter(req.getParameter("writer"));
		dto.setEmail(req.getParameter("email"));
		dto.setSubject(req.getParameter("subject"));
		dto.setContent(req.getParameter("content"));
		dto.setPasswd(req.getParameter("passwd"));
		BoardDataBean dao = new BoardDataBean();
		int res = 0;
		try {
			res = dao.updateBoard(dto);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		String msg = null, url = null;
		if (res>0) {
			msg ="글수정 성공!! 글목록페이지로 이동합니다.";
			url = "list.board";
		}else if (res<0) {
			msg ="비밀번호가 틀렸습니다. 다시 입력해 주세요!!";
			url = "updateForm.board?num="+dto.getNum();
		}else {
			msg ="글수정 실패!! 글보기페이지로 이동합니다.";
			url = "content.board?num="+dto.getNum();
		}
		req.setAttribute("msg", msg);
		req.setAttribute("url", url);
		return "message.jsp";
	}

}














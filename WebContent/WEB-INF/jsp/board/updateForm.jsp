<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="board.*"%>
<!-- updateForm.jsp -->
<script type="text/javascript">
	function check(){
		if (f.subject.value==""){
			alert("제목을 입력해 주세요!!")
			f.subject.focus()
			return false
		}
		if (f.content.value==""){
			alert("내용을 입력해 주세요!!")
			f.content.focus()
			return false
		}
		if (f.passwd.value==""){
			alert("비밀번호를 입력해 주세요!!")
			f.passwd.focus()
			return false
		}
		return true
	}
</script>
<%	BoardDBBean dto = (BoardDBBean)request.getAttribute("getBoard"); %>
<div align="center">
	<form name="f" action="updatePro.board" method="post" onsubmit="return check()">
		<input type="hidden" name="num" value="<%=dto.getNum()%>"/>
		<table border="1" width="500">
			<tr bgcolor="yellow">
				<td align="center" colspan="2">글 수 정</td>
			</tr>
			<tr>  
				<td align="center" width="20%" bgcolor="yellow">이 름</td>
				<td><input type="text" name="writer" value="<%=dto.getWriter()%>"></td>
			</tr>
			<tr>
				<td align="center" width="20%" bgcolor="yellow">제 목</td>
				<td><input type="text" name="subject" value="<%=dto.getSubject()%>" size="50"></td>
			</tr>
			<tr>
				<td align="center" width="20%" bgcolor="yellow">Email</td>
				<td><input type="text" name="email" value="<%=dto.getEmail()%>" size="50"></td>
			</tr>
			<tr>
				<td align="center" width="20%" bgcolor="yellow">내 용</td>
				<td><textarea name="content" rows="10" cols="50" class="box"><%=dto.getContent()%></textarea></td>
			</tr>
			<tr>
				<td align="center" width="20%" bgcolor="yellow">비밀번호</td>
				<td><input type="password" name="passwd"></td>
			</tr>
			<tr>
				<td colspan="2" bgcolor="yellow" align="center">
					<input type="submit" value="글수정">
					<input type="reset" value="다시작성">
					<input type="button" value="목록보기" onclick="window.location='list.board'">
				</td>
			</tr>
		</table>
	</form>
</div>
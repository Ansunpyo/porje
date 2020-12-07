<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="vo.BoardBean" %>
<%
	BoardBean article = (BoardBean)request.getAttribute("article");
	String nowPage = (String)request.getAttribute("page");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC 게시판</title>
<style>
	#registForm {
		width: 500px;
		height: 610px;
		border: 1px solid red;
		margin: auto;
	}
	
	h2 {
		text-align: center;
	}
	
	table {
		margin: auto;
		width: 450px;
	}
	
	.td_left {
		width: 150px;
		background: orange;
	}
	
	.td_right {
		width: 300px;
		backgroundL skyblue;
	}
	
	#commandCell {
		text-align: center;
	}
</style>
</head>
<body>
	<!-- 게시판 답변 -->
	
	<section id="writeForm">
		<h2>게시판 답변 등록</h2>
		<form action="boardReplyPro.do" method="post" name="boardform">
			<input type="hidden" name="page" value="<%=nowPage %>" />
			<input type="hidden" name="board_num" value="<%=article.getBoard_num() %>" />
			<input type="hidden" name="board_re_ref" value="<%=article.getBoard_re_ref() %>" />
			<input type="hidden" name="board_re_lev" value="<%=article.getBoard_re_lev() %>" />
			<input type="hidden" name="board_re_seq" value="<%=article.getBoard_re_seq() %>" />
			<table>
				<tr>
					<td class="td_left"><label for="board_name">글쓴이</label></td>
					<td class="td_right"><input type="text" name="board_name" id="board_name" /></td>
				</tr>
				<tr>
					<td class="td_left"><label for="board_pass">비밀번호</label></td>
					<td class="td_right"><input type="password" name="board_pass" id="board_pass" /></td>
				</tr>
				<tr>
					<td class="td_left"><label for="board_subject">제 목</label></td>
					<td class="td_right"><input type="text" name="board_subject" id="board_subject" /></td>
				</tr>
				<tr>
					<td class="td_left"><label for="board_content">내 용</label></td>
					<td><textarea id="board_content" name="board_content" cols="40" rows="15"></textarea></td>
				</tr>
			</table>
			<section id="commandCell">
				<a href="boardDetail.do?board_num=<%=article.getBoard_num() %>&page=<%=nowPage %>">본문</a>
				<input type="submit" value="답변글 등록" />&nbsp;&nbsp;
				<input type="reset" value="다시 작성" />
			</section>
		</form>
	</section>
</body>
</html>
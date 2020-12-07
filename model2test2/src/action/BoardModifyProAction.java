package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.BoardModifyProService;
import vo.ActionForward;
import vo.BoardBean;

public class BoardModifyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		boolean isModifySuccess = false;
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		BoardBean article = new BoardBean();
		BoardModifyProService boardModifyProService = new BoardModifyProService();
		boolean isRightUser = boardModifyProService.isArticleWriter(board_num, request.getParameter("board_pass"));
		
		if(!isRightUser) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('수정할 권한이 없습니다.');history.back();</script>");
		} else {
			article.setBoard_num(board_num);
			article.setBoard_subject(request.getParameter("board_subject"));
			article.setBoard_content(request.getParameter("board_content"));
			isModifySuccess = boardModifyProService.modifyArticle(article);
			
			if(!isModifySuccess) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('수정 실패');history.back();</script>");
			} else {
				forward = new ActionForward();
				forward.setRedirect(true);
				forward.setPath("boardDetail.do?board_num=" + article.getBoard_num() + "&page=" + request.getParameter("page"));
			}
		}
		return forward;
	}

}

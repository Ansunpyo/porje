package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.BoardDeleteProService;
import vo.ActionForward;

public class BoardDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		String nowPage = request.getParameter("page");
		BoardDeleteProService boardDeleteProService = new BoardDeleteProService();
		boolean isArticleWriter = boardDeleteProService.isArticleWriter(board_num, request.getParameter("board_pass"));
		
		if(!isArticleWriter) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('삭제할 권한이 없습니다');history.back();</script>");
			out.close();
		} else {
			boolean isDeleteSuccess = boardDeleteProService.removeArticle(board_num);
			
			if(!isDeleteSuccess) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('삭제 실패');history.back();</script>");
				out.close();
			} else {
				forward = new ActionForward();
				forward.setRedirect(true);
				forward.setPath("boardList.do?page=" + nowPage);
			}
		}
		return forward;
	}

}

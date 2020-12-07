package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.BoardReplyProService;
import vo.ActionForward;
import vo.BoardBean;

public class BoardReplyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		String nowPage = request.getParameter("page");
		BoardBean article = new BoardBean();
		article.setBoard_num(Integer.parseInt(request.getParameter("board_num")));
		article.setBoard_name(request.getParameter("board_name"));
		article.setBoard_pass(request.getParameter("board_pass"));
		article.setBoard_subject(request.getParameter("board_subject"));
		article.setBoard_content(request.getParameter("board_content"));
		article.setBoard_re_ref(Integer.parseInt(request.getParameter("board_re_ref")));
		article.setBoard_re_lev(Integer.parseInt(request.getParameter("board_re_lev")));
		article.setBoard_re_seq(Integer.parseInt(request.getParameter("board_re_seq")));
		BoardReplyProService boardReplyProService = new BoardReplyProService();
		boolean isReplySuccess = boardReplyProService.replyArticle(article);
		
		if(isReplySuccess) {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("boardList.do?page=" + nowPage);
		} else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('답장실패');history.back();</script>");
		}
		return forward;
	}

}

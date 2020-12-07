package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.BoardDAO;
import vo.BoardBean;

public class BoardReplyProService {

	public boolean replyArticle(BoardBean article) {
		boolean isReplySuccess = false;
		int insertCount = 0;
		Connection conn = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(conn);
		insertCount = boardDAO.insertReplyArticle(article);
		
		if(insertCount > 0) {
			commit(conn);
			isReplySuccess = true;
		} else {
			rollback(conn);
		}
		
		if(conn != null) close(conn);
		return isReplySuccess;
	}

	
}

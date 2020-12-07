package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.BoardDAO;
import vo.BoardBean;

public class BoardDetailService {

	public BoardBean getArticle(int board_num) {
		BoardBean article = null;
		Connection conn = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(conn);
		int updateCount = boardDAO.updateReadCount(board_num);
		
		if(updateCount > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		article = boardDAO.selectArticle(board_num);
		if(conn != null) close(conn);
		
		return article;
	}

}

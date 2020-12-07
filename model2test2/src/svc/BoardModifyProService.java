package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.BoardDAO;
import vo.BoardBean;

public class BoardModifyProService {

	public boolean isArticleWriter(int board_num, String pass) {
		boolean isArticleWriter = false;
		Connection conn = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(conn);
		isArticleWriter = boardDAO.isArticleBoardWriter(board_num, pass);
		if(conn != null) close(conn);
		return isArticleWriter;
	}

	public boolean modifyArticle(BoardBean article) {
		boolean isModifySuccess = false;
		Connection conn = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(conn);
		int updateCount = boardDAO.updateArticle(article);
		
		if(updateCount > 0) {
			commit(conn);
			isModifySuccess = true;
		} else {
			rollback(conn);
		}
		
		if(conn != null) close(conn);
		return isModifySuccess;
	}

}

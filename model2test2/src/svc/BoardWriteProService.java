package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.BoardDAO;
import vo.BoardBean;

public class BoardWriteProService {

	public boolean registArticle(BoardBean boardBean) throws Exception {
		boolean isWriteSuccess = false;
		Connection conn = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(conn);
		int insertCount = boardDAO.insertArticle(boardBean);
		
		if(insertCount > 0) {
			commit(conn);
			isWriteSuccess = true;
		} else {
			rollback(conn);
		}
		
		if (conn != null) close(conn);
		
		return isWriteSuccess;
	}

}

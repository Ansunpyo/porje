package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.BoardDAO;
import vo.BoardBean;

public class BoardListService {

	public int getListCount() {
		int listCount = 0;
		Connection conn = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(conn);
		listCount = boardDAO.selectListCount();
		if (conn != null) close(conn);
		return listCount;
	}

	public ArrayList<BoardBean> getArticleList(int page, int limit) {
		ArrayList<BoardBean> articleList = null;
		Connection conn = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(conn);
		articleList = boardDAO.selectArticleList(page, limit);
		if (conn != null) close(conn);
		return articleList;
	}

}

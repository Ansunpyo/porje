<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="vo.PageInfo, vo.BoardBean, java.util.*, java.text.SimpleDateFormat" %>
<%
	ArrayList<BoardBean> articleList = (ArrayList<BoardBean>)request.getAttribute("articleList");
	PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");
	int listCount = pageInfo.getListCount();
	int nowPage = pageInfo.getPage();
	int maxPage = pageInfo.getMaxPage();
	int startPage = pageInfo.getStartPage();
	int endPage = pageInfo.getEndPage();
%>
<!DOCTYPE html>
<html>
<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
 		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" />
		<title>강사소개</title>
	</head>
<body>
<header>
	<nav class="navbar navbar-expand-sm navbar-dark bg-dark fixed-top">
		<div class="container">
			<a class="navbar-brand" href="index.html">2LW</a>	
		</div>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item active"><a class="nav-link" href="#">강사소개
					<span class="sr-only">(current)</span>
				</a></li>
				<li class="nav-item"><a class="nav-link" href="#carrer">강의목록</a></li>
				<li class="nav-item"><a class="nav-link" href="#portfolio">마이페이지</a></li>
				<li class="nav-item"><a class="nav-link" href="#contact">고객센터</a></li>
			</ul>
			<form class="form-inline my-2 my-lg-0">
				<input class="form-control mr-sm-2" type="search"
					placeholder="Search" aria-label="Search">
				<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
			</form>
		</div>
	</nav>
</header>
	<div class="container" id="main">
		<table>
			<%
			if(articleList != null && listCount > 0) {
			%>
			<tbody>
				<%
				for(int i=0;i<articleList.size();i++) {
				%>
				<tr>
					<td>
						<img src="/upload/<%=articleList.get(i).getBoard_file() %>" alt="-" 
						style="width : 355px; height:300px; align:center;"/>
					</td>					
				</tr>
				<tr>
					<td>
						<%if(articleList.get(i).getBoard_re_lev()!=0) { %>
						<%}else{ %><%} %>
							<a href="boardDetail.do?board_num=<%=articleList.get(i).getBoard_num() %>&page=<%=nowPage %>">
							<%=articleList.get(i).getBoard_subject() %>
							</a>
					</td>
				</tr>
				<%} %></tbody>
		</table>
			<%
			}
			else
			{
	%>
		<div class="container" style="text-align: center;"><h2>등록된 소개가 없습니다.</h2></div>
	<%
			}
	%>
	<nav aria-label="Page navigation example">
	  <ul class="pagination pagination-sm justify-content-center">
<%
		if (nowPage <= 1) {
%>
	    <li class="page-item disabled"><a class="page-link" href="boardList.do?page=<%=nowPage-1 %>">Previous</a></li>
<%
		} else {
%>
	    <li class="page-item"><a class="page-link" href="boardList.do?page=<%=nowPage-1 %>">Previous</a></li>
<%
		}
		for(int a = startPage;a<=endPage;a++){
			if(a==nowPage){
%>
	    <li class="page-item active"><a class="page-link" href="boardList.do?page=<%=a %>"><%=a %></a></li>
<%
		} else {
%>
		<li class="page-item"><a class="page-link" href="boardList.do?page=<%=a %>"><%=a %></a></li>
<%
		}
	}
		
		if(nowPage>=endPage) {
%>
	    <li class="page-item disabled"><a class="page-link" href="boardList.do?page=<%=nowPage+1 %>">Next</a></li>
<%
		} else {
%>
	    <li class="page-item"><a class="page-link" href="boardList.do?page=<%=nowPage+1 %>">Next</a></li>
<%
		}
%>	    
	  </ul>
	</nav>
	<div class="row">
		<div class="ml-auto"><a href="boardWriteForm.do"><input type="button" class="btn btn-primary" value="작성하기"/></a></div>
	</div>
	</div>	
		<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js"></script>
		<script>
			$(function(){
				$("#main").css("margin-top", $("nav").outerHeight(true) + "px");
				$(window).resize(function(){
					$("#main").css("margin-top", $("nav").outerHeight(true) + "px");
				});
			});
		</script>
</body>
</html>
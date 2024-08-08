<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="ARTICLE WRITE" />

<%@ include file="../../common/head.jsp" %>
<%@ include file="../../common/toastUiEditorLib.jsp" %>

	<section class="mt-8"> 
		<div class="container mx-auto px-3">
			<form action="doWrite" method="post" onsubmit="submitForm(this); return false;">
				<input type="text" name="body">
				<table class="table">
					<tr class="border-b border-slate-400 mt">
						<th>게시판</th>
						<td>
							<div class="flex">
								<div>
									<label class="flex items-center">
										<input class="radio radio-sm" type="radio" name="boardId" value="1" checked />
										&nbsp;&nbsp;공지사항
									</label>
								</div>
								<div class="w-20"></div>
								<div>
									<label class="flex items-center">
										<input class="radio radio-sm" type="radio" name="boardId" value="2" />
										&nbsp;&nbsp;자유
									</label>
								</div>
							</div>
						</td>
					</tr>
					<tr class="border-b border-slate-400">
						<td>제목</td>
						<td><input class="input input-bordered w-full" type="text" name="title" value="${article.title }"/></td>
					</tr>
					<tr>
						<td colspan="2">
							<div class="toast-ui-editor">
								<script type="text/x-template"></script>
							</div>
						</td>
					</tr>
				</table>
				<div class="flex mt-1">
					<button class="btn btn-outline btn-primary mr-2">작성</button>
					<button class="btn btn-outline btn-primary mr-2" type="button" onclick="history.back();"> 뒤로가기</button>
				</div>
			</form>
		</div>
	</section>
<%@ include file="../../common/foot.jsp" %>
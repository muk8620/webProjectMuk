<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="MYPAGE" />

<%@ include file="../../common/head.jsp" %>

	<section class="mt-8 text-lg">
		<div class="container mx-auto px-3 pb-8">
			<div class="table-box-type">
				<table class="table table-lg">
					<tr>
						<th>번호</th>
						<td>${member.id }</td>
					</tr>
					<tr>
						<th>가입일</th>
						<td>${member.regDate.substring(2, 16) }</td>
					</tr>
					<tr>
						<th>정보 수정일</th>
						<td>${member.updateDate.substring(2, 16) }</td>
					</tr>
					<tr>
						<th>아이디</th>
						<td>${member.loginId }</td>
					</tr>
					<tr>
						<th>이름</th>
						<td>${member.name }</td>
					</tr>
					<tr>
						<th>닉네임</th>
						<td>${member.nickname }</td>
					</tr>
					<tr>
						<th>전화번호</th>
						<td>${member.cellphoneNum }</td>
					</tr>
					<tr>
						<th>이메일</th>
						<td>${member.email }</td>
					</tr>
				</table>
			</div>
			
			<div class="mt-3 flex justify-between">
				<button class="btn btn-active btn-sm" onclick="history.back();">뒤로가기</button>
				<a class="btn btn-active btn-sm" href="checkPassword?loginId=${member.loginId }">회원정보수정</a>
			</div>
		</div>
	</section>
<%@ include file="../../common/foot.jsp" %>
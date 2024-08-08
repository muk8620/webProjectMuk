<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="PASSWORD MODIFY" />

<%@ include file="../../common/head.jsp" %>

	<script>
		const passwordModifyForm_onSubmit = function(form) {
			form.loginPw.value = form.loginPw.value.trim();
			
			if (form.loginPw.value.length == 0) {
				alert('비밀번호를 입력해주세요');
				form.loginPw.focus();
				return;
			}
			
			if (form.loginPw.value != form.loginPwChk.value) {
				alert('비밀번호가 일치하지 않습니다');
				form.loginPw.value = '';
				form.loginPwChk.value = '';
				form.loginPw.focus();
				return;
			}
			
			form.submit();
		}
	</script>

	<section class="mt-8 text-lg">
		<div class="container mx-auto px-3">
			<form action="doPasswordModify" method="post" onsubmit="passwordModifyForm_onSubmit(this); return false;">
				<div class="table-box-type">
					<table class="table table-lg">
						<tr>
							<th>새 비밀번호</th>
							<td><input class="input input-bordered w-full" type="text" name="loginPw" placeholder="새 비밀번호를 입력해주세요"/></td>
						</tr>
						<tr>
							<th>새 비밀번호 확인</th>
							<td><input class="input input-bordered w-full" type="text" name="loginPwChk" placeholder="새 비밀번호 확인을 입력해주세요" /></td>
						</tr>
						<tr>
							<td colspan="2">
								<div class="btns flex justify-center">
									<button class="btn btn-active btn-wide">변경</button>
								</div>
							</td>
						</tr>
					</table>
				</div>
			</form>
			<div class="mt-3 text-sm">
				<button class="btn btn-active btn-sm" onclick="history.back();">뒤로가기</button>
			</div>
		</div>
	</section>
<%@ include file="../../common/foot.jsp" %>
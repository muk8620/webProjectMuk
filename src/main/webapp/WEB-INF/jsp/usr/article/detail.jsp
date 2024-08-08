<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="ARTICLE DETAIL" />

<%@ include file="../../common/head.jsp" %>
	<script>
		$(function(){
			
			const Editor = toastui.Editor;
			
			const viewer = new toastui.Editor.factory({
				el: document.querySelector('#toast-ui-viewer'),
				viewer: true,
				initialValue: `${article.getBody() }`
			});
			
			getLikePoint();
			getNickname();
			
			$('#likePointBtn').click(async function(){
				
				let liked = $('#likePointBtn').hasClass('btn-outline') === false;
				try{
					await $.ajax({
						url : '../likePoint/doLikePoint',
						type : 'GET',
						data : {
							relTypeCode : 'article',
							relId : ${article.id },
							liked : liked
						},
					})
					
					let totalCnt = await getLikePoint();
					
					$('.likePointCnt').each(function(index, item) {
						$(item).html('추천 ' + totalCnt.data);
					})
				} catch (error) {
					console.log('Error : ', error);
				}
			})
			
			
		})
		
		const getLikePoint = async function(){
			return $.ajax({
				url : '../likePoint/getLikePoint',
				type : 'GET',
				data : {
					relTypeCode : 'article',
					relId : ${article.id }
				},
				dataType : 'json',
				success : function(data) {
					if (data.success) {
						$('#likePointBtn').removeClass('btn-outline');
					} else {
						$('#likePointBtn').addClass('btn-outline');
					}
				},
				error : function(xhr, status, error) {
					console.log(error);
				}
			})
		}
		
		const getNickname = function(){
			$.ajax({
				url : '../member/getNickname',
				type : 'GET',
				dataType : 'text',
				success : function(data) {
					$('#replyNickname').html(data);
				},
				error : function(xhr, status, error) {
					console.log(error);
				}
			})
		}
		
		let originalForm = null;
		let originalId = null;
		
		const replyModifyForm = function(i){
			
			if (originalForm != null) {
				replyModifyCancle(originalId);
			}
			
			$.ajax({
				url : '../reply/getReply',
				type : 'GET',
				data : {
					id : i
				},
				dataType : 'json',
				success : function(data) {

					let replyForm = $('#reply' + i);
					
					originalForm = replyForm.html();
					originalId = i;
					
					let addHtml = `
						<form action="../reply/doModify" method="post" onsubmit="replyForm_onSubmit(this); return false;">
							<input type="hidden" name="id" value="\${data.data.id }"/>
							<input type="hidden" name="relId" value="\${data.data.relId }"/>
							<div class="mt-4 reply-border p-4">
								<div class="mb-2"><span id="replyNickname">\${data.data.writerName }</span></div>
								<textarea class="textarea textarea-bordered textarea-lg w-full" name="body" placeholder="댓글을 입력해보세요">\${data.data.body }</textarea>
								<div class="flex justify-end">
									<button onclick="replyModifyCancle(\${i});" class="btn btn-active btn-sm">취소</button>
									<button class="btn btn-active btn-sm mx-2">수정</button>
								</div>
							</div>
						</form>
					`;
					
					replyForm.html(addHtml);
					
				},
				error : function(xhr, status, error) {
					console.log(error);
				}
			})
		}
		
		const replyModifyCancle = function(i){
			let replyForm = $('#reply' + i);
			
			replyForm.html(originalForm);
			
			originalForm = null;
			originalId = null;
		}
		
	</script>
	
	<section class="mt-8">
		<div class="container mx-auto px-3">
			<div class="py-2">
				<div class="text-xl">${article.title }</div>
				<div>
					${article.writerName }
					<i class="fa-solid ${article.memberId == 1 ? 'fa-user-secret' : 'fa-user'}"></i>
				</div>
				<div class="text-xs text-gray-400 mt-1">${article.updateDate.substring(2 , 16)}</div>
				<div class="flex">
					<div class="text-sm text-gray-400">조회수 ${article.views }</div>
					<div class="w-2"></div>
					<div class="likePointCnt text-sm text-gray-400">추천 ${article.likePoint }</div>
				</div>
			</div>
			<div id="toast-ui-viewer"></div>
			<div class="flex justify-center">
				<button class="btn btn-outline btn-primary ${rq.loginedMemberId == 0 ? 'btn-disabled' : '' }" id="likePointBtn">
					<i class="fa-solid fa-thumbs-up fa-xl"></i>
					<div class="likePointCnt badge">${article.likePoint }</div>
				</button>
			</div>
	
			<div class="flex mx-auto mt-4">
				<c:if test="${rq.loginedMemberId == article.memberId}">
					<a class="btn btn-outline btn-primary mr-2" href="modify?id=${article.id }">수정</a>
					<a class="btn btn-outline btn-primary mr-2" href="doDelete?id=${article.id }&boardId=${article.boardId }" onclick="return confirm('삭제하시겠습니까?');">삭제</a>
				</c:if>
				<a class="btn btn-outline btn-primary mr-2" href="list?boardId=${article.boardId }">목록</a>
			</div>
			
			<script>
				const replyForm_onSubmit = function(form){
					form.body.value = form.body.value.trim();
					
					if (form.body.value.length == 0) {
						alert('비어있는 댓글은 작성할 수 없습니다');
						form.body.focus();
						return;
					}
					
					form.submit();
				}
			</script>
			
			<section class="my-5 text-base">
				<div class="container mx-auto px-3">
					<div class="text-lg">댓글</div>
					
					<c:forEach var="reply" items="${replies }" varStatus="status">
						<div id="reply${reply.id }" class="py-2 border-bottom-line pl-12">
							<div class="flex justify-between items-end">
								<div>${reply.writerName }</div>
								<c:if test="${reply.memberId == rq.loginedMemberId}">
									<div class="dropdown dropdown-end">
									  <div tabindex="0" role="button" class="btn btn-sm ml-2"><i class="fa-solid fa-ellipsis"></i></div>
									  <ul tabindex="0" class="dropdown-content menu bg-base-200 rounded-box z-[1] w-20 p-2 shadow">
									    <li><a onclick="replyModifyForm(${reply.id });">수정</a></li>
									    <li><a href="../reply/doDelete?id=${reply.id }&relId=${article.id }"onclick="return confirm('삭제하시겠습니까?');">삭제</a></li>
									  </ul>
									</div>
								</c:if>
							</div>
							<div class="text-lg my-1 ml-2">${reply.getForPrintBody() }</div>
							<div class="text-xs text-gray-400">${reply.regDate.substring(2 , 16) }</div>
						</div>
					</c:forEach>
					
					<c:if test="${rq.loginedMemberId != 0}">
						<form action="../reply/doWrite" method="post" onsubmit="replyForm_onSubmit(this); return false;">
							<input type="hidden" name="relTypeCode" value="article"/>
							<input type="hidden" name="relId" value="${article.id }"/>
							<div class="mt-4 reply-border p-4">
								<div class="mb-2"><span id="replyNickname"></span></div>
								<textarea class="textarea textarea-bordered textarea-lg w-full" name="body" placeholder="댓글을 입력해주세요"></textarea>
								<div class="flex justify-end"><button class="btn btn-active btn-sm">작성</button></div>
							</div>
						</form>
					</c:if>
				</div>
				
			</section>
		</div>
	</section>
<%@ include file="../../common/foot.jsp" %>
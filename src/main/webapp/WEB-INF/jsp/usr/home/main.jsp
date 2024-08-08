<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="MAIN" />

<%@ include file="../../common/head.jsp" %>
	
	<section>
		<div class="container mx-auto px-3">
			<div>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Labore facilis cum aut facere numquam magni itaque. Repellendus quibusdam maiores tenetur autem repellat consectetur minus nobis aut soluta tempora architecto natus?</div>
			<div>안녕하세요</div>
			
			<div class="flex">
				<div class="modal-exam btn btn-active mr-10"><span>모달예시</span></div>
				
				<div class="popUp-exam btn btn-active"><span>팝업예시</span></div>
			</div>
			
			<div class="modalLayer-bg"></div>
			<div class="modalLayer">
				<div>MODAL</div>
				<span>&times;</span>
				<div>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Eius molestiae fugiat sunt ratione quo iure temporibus asperiores praesentium suscipit ab nulla et culpa quae atque eaque perferendis sint officiis voluptas.</div>				
				<button class="close-btn btn btn-active">CLOSE</button>
			</div>	
		</div>
		
	</section>

<%@ include file="../../common/foot.jsp" %>
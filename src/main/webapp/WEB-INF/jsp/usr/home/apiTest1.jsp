<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<c:set var="pageTitle" value="API TEST" />

<%@ include file="../../common/head.jsp" %>

<script>
	async function getData() {
		const API_KEY = 'sd2%2Fw1FPMP7dCiLT1r8GNJatfwBCKhZfFVQAA3lNV55hr4o2tNP9B0NpNBn7iAGvAN8QwKTBfli73H%2Fdq7xZBw%3D%3D';
		const url = 'http://apis.data.go.kr/1360000/WthrWrnInfoService/getWthrWrnList?serviceKey=' + API_KEY + '&numOfRows=10&pageNo=1&dataType=json';
		
		const response = await fetch(url);
		const data = await response.json();
		console.log(data);
		
		$('#apiTest').html(data.response.body.items.item[0].title);
	}
	
// 		const API_KEY = 'sd2%2Fw1FPMP7dCiLT1r8GNJatfwBCKhZfFVQAA3lNV55hr4o2tNP9B0NpNBn7iAGvAN8QwKTBfli73H%2Fdq7xZBw%3D%3D';
// 		const url = 'http://apis.data.go.kr/1360000/WthrWrnInfoService/getWthrWrnList?serviceKey=' + API_KEY + '&numOfRows=10&pageNo=1&dataType=json';
		
// 		$.ajax({
// 			url : url,
// 			type : 'GET',
// 			dataType : 'json',
// 			success : function(data) {
// 				console.log(data);
// 			},
// 			error : function(xhr, status, error) {
// 				console.log(error);
// 			}
// 		})
// 	}
		
	getData();
</script>

<div>API TEST1</div>
<div id="apiTest"></div>

<%@ include file="../../common/foot.jsp" %>
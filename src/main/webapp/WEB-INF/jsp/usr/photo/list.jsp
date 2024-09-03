<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="MEMBER LOGIN" />

<%@ include file="../../common/head.jsp" %>

<script>
	async function getData() {
		const appKey = 'i0bJ3t8bYWDFUOxD';
		const secretKey = '8Ybnw1o13wXCTu5bUP0gYFSigvCbWeKo';
		const url = 'https://api-image.nhncloudservice.com/image/v2.0/appkeys/' + appKey + '/folders?Authorization=' + secretKey + '&path=%2Fmyfolder';
		
		const response = await fetch(url);
		const data = await response.json();
		console.log(data);
	}
	
	getData();
</script>


<%@ include file="../../common/foot.jsp" %>
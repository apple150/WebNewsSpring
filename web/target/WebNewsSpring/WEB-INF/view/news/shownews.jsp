<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
	<script language='JavaScript' type='text/javascript'>
		function back() {
			document.getElementById('do').value = '';
			document.getElementById('id').value = '';
			document.getElementById('idComm').value = '';
			document.getElementById('form').submit();
		}
		function saveComment() {
			document.getElementById('do').value = 'saveComm';
			document.getElementById('id').value = '${news.newsId}';
			document.getElementById('idComm').value = '';
			document.getElementById('form').submit();
		}
		function deleteComment(idComments) {
			if (confirm("Удалить комментарий #" + idComments)) {
				document.getElementById('do').value = 'delComm';
				document.getElementById('id').value = '${news.newsId}';
				document.getElementById('idComm').value = idComments;
				document.getElementById('form').submit();
			}
		}
		function editComment(idComments) {
				document.getElementById('do').value = 'showEditComm';
				document.getElementById('id').value = '';
				document.getElementById('idComm').value = idComments;
				document.getElementById('form').submit();
		}
	</script>
	<form id="form" action="" method="get">
		<input type="hidden" name="do" id="do">
		<input type="hidden" name="idComm" id="idComm">
		<input type="hidden" name="id" id="id">
		<%--<table border="1" align="center">--%>
		<table border="1" style="border-collapse: collapse;" align="center">
			<tr>
				<td><spring:message code="date"/></td>
				<td><fmt:formatDate value="${news.newsDetail.drelease}" pattern="yyyy-MM-dd" /></td>
			</tr>
			<tr>
				<td><spring:message code="title"/></td>
				<td>${news.title}</td>
			</tr>
			<tr>
				<td><spring:message code="category"/></td>
				<td>${news.category.categoryName}</td>
			</tr>
			<tr>
				<td><spring:message code="tags"/></td>
				<td><c:forEach var="tags" items="${news.tag}">${tags.tagName}<br>
				</c:forEach></td>
			</tr>
			<tr>
				<td><spring:message code="user"/></td>
				<td>${news.user.name} ${news.user.surName}</td>
			</tr>
			<tr>
				<td><spring:message code="author"/></td>
				<td>${news.newsDetail.author}</td>
			</tr>
			<tr>
				<td><spring:message code="agency"/></td>
				<td>${news.newsDetail.agency}</td>
			</tr>
			<tr>
				<td><spring:message code="text"/></td>
				<td>${news.document}</td>
			</tr>
			<sec:authorize access="hasRole('ADMINISTRATOR')">
				<tr>
					<td colspan="2"><spring:message code="comments"/></td>
				</tr>
				<c:forEach var="comments" items="${news.comment}">
					<tr>
						<td colspan="2">#${comments.commentId} ${comments.user.name}</td>
					</tr>
					<tr>
						<td colspan="2">${comments.comment}<br></td>
						<td/>
						<td><input type="button" value="Редактировать" onclick="editComment(${comments.commentId})"></td>
						<td><input type="button" value="Удалить" onclick="deleteComment(${comments.commentId})"></td>
					</tr>
				</c:forEach>
			</sec:authorize>
		</table>
		<table align="center">
			<tr><td><spring:message code="button.add_comment"/></td></tr>
			<tr><td><textarea rows="4" cols="53" name="text"></textarea></td></tr>
		</table>
		<div style="text-align: center">
			<input type="button" value="<spring:message code="button.add_comment"/>" onclick="saveComment()" />
			<input type="button" value="<spring:message code="button.back"/>" onclick="back()" />
		</div>
	</form>
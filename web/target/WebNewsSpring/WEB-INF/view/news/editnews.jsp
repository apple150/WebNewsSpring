<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
    <script language='JavaScript' type='text/javascript'>
        function save() {
            document.getElementById('do').value = 'save';
            document.getElementById('id').value = '${news.newsId}';
            document.getElementById('form').submit();
        }
        function back() {
            document.getElementById('do').value = '';
            document.getElementById('header').value = '';
            document.getElementById('categorySel').value = '';
            document.getElementById('text').value = '';
            document.getElementById('form').submit();
        }
    </script>
    <form method="get" action="" id="form">
        <input type="hidden" name="do" id="do">
        <input type="hidden" name="id" id="id">
        <BR>
        <table border="1" style="border-collapse: collapse;" align="center">
            <tr>
                <td><spring:message code="title"/></td>
                <td><input type="text" size="50" id="header" name="header" value='${news.title}'></td>
            </tr>
            <tr>
                <td><spring:message code="category"/></td>
                <td><select id="categorySel" name="category">
                    <c:forEach var="cat" items="${categories}">
                        <option value="${cat.categoryId}"
                                <c:if test="${news.category.categoryName == cat.categoryName}">selected</c:if>>${cat.categoryName}</option>
                    </c:forEach>
                </select></td>
            </tr>
            <tr>
                <td><spring:message code="tags"/></td>
                <td><c:forEach var="tags" items="${tags}">
                    <input type="checkbox" name="tags" value="${tags.tagId}" of
                    <c:forEach var="tagNews" items="${news.tag}">
                           <c:if test="${tagNews.tagName == tags.tagName}">checked</c:if>
                    </c:forEach>>${tags.tagName}<br>
                </c:forEach></td>
            </tr>
            <tr>
                <td><spring:message code="text"/></td>
                <td><textarea id="text" rows="4" cols="53" name="text">${news.document}</textarea></td>
            </tr>
        </table>
        <br>
        <div style="text-align: center">
            <input type="button" onclick="save()" value="<spring:message code="button.save"/>">
            <input type="button" onclick="back()" value="<spring:message code="button.back"/>">
        </div>
    </form>
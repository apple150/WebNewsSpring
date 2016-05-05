<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
    <script language='JavaScript' type='text/javascript'>
        window.onload = function() {
            document.getElementById('newsByPageSel').options[${newsByPage/3}].selected = true;
        }
        function newsByPageSelector(sel) {
            document.getElementById('newsByPage').value = sel;
            document.getElementById('form').submit();
        }
        function pageSelector(sel) {
            document.getElementById('startPage').value = sel;
            document.getElementById('form').submit();
        }
        function deleteNews(idNews, newsHeader) {
            if (confirm("Удалить новость \"" + newsHeader + "\"")) {
                document.getElementById('do').value = 'del';
                document.getElementById('id').value = idNews;
                document.getElementById('form').submit();
            }
        }
        function addNews() {
            document.getElementById('do').value = 'addNews';
            document.getElementById('id').value = '';
            document.getElementById('form').submit();
        }
        function editNews(idNews) {
            document.getElementById('do').value = 'showEditNews';
            document.getElementById('id').value = idNews;
            document.getElementById('form').submit();
        }
        function logout() {
            document.getElementById('form').action = '<c:url value="/logout" />';
            document.getElementById('form').submit();
        }
        function showNews(idNews) {
            document.getElementById('do').value = 'show';
            document.getElementById('id').value = idNews;
            document.getElementById('form').submit();
        }
        function sort(column, ordr) {
            document.getElementById('column').value = column;
            document.getElementById('sortOrder').value = ordr;
            document.getElementById('form').submit();
        }
    </script>
    <form action="" method="get" id="form">
        <input type="hidden" name="newsByPage" id="newsByPage" value="${newsByPage}"/>
        <input type="hidden" name="startPage" id="startPage" value="${startPage}"/>
        <input type="hidden" name="do" id="do"/>
        <input type="hidden" name="id" id="id"/>
        <input type="hidden" name="column" id="column" value ="${column}"/>
        <input type="hidden" name="sortOrder" id="sortOrder" value ="${sortOrder}"/>
        <div style="text-align: center">
            <%--Show by items Selector--%>
            <spring:message code="select.label.show"/>
            <select id="newsByPageSel" onchange="newsByPageSelector(this.value)">
                <option value=""></option>
                <option value="3">3</option>
                <option value="6">6</option>
                <option value="9">9</option>
            </select>
            <input type="button" onclick="addNews()" value="<spring:message code="button.add_news"/>"/>
            <input type="button" onclick="logout()" value="<spring:message code="button.exit"/>"/>
        </div>
        <br/>
        <table border="1" style="border-collapse: collapse;" align="center">
            <%--Header of table--%>
            <tr align="center">
                <th><spring:message code="date"/></th> <%--Header of Date--%>
                <th><spring:message code="title"/></th> <%--Header of Title--%>
                <!--<th>Date<div><input type="button" onclick="sort('newsDate','asc')" value="&#x2191" />-->
                <!--<input type="button" onclick="sort('newsDate','desc')" value="&#x2193" /></div></th>-->
                <!--<th>Header<div><input type="button" onclick="sort('header','asc')" value="&#x2191" />-->
                <!--<input type="button" onclick="sort('header','desc')" value="&#x2193" /></div></th>-->
                <th><spring:message code="category"/><div>
                    <input type="button" onclick="sort('category','asc')" value="&#x2191" />
                    <input type="button" onclick="sort('category','desc')" value="&#x2193" />
                </div></th>
                <th><spring:message code="tags"/></th>
                <th><spring:message code="author"/><div>
                    <input type="button" onclick="sort('user','asc')" value="&#x2191" />
                    <input type="button" onclick="sort('user','desc')" value="&#x2193" />
                </div></th>
                <th><spring:message code="comments"/></th>
                <th>&nbsp</th>
                <th>&nbsp</th>
            </tr>
            <c:forEach var="news" items="${newsList}">
            <%--Data of table--%>
            <tr>
                <td><fmt:formatDate value="${news.newsDetail.drelease}" pattern="yyyy-MM-dd"/></td>
                <td>${news.title} <input type="button" onclick="showNews(${news.newsId})" value="->"/></td>
                <td>${news.category.categoryName}</td>
                <td><c:forEach items="${news.tag}" var="tag">${tag.tagName}<br/></c:forEach></td>
                <td>${news.user.name} ${news.user.surName}</td>
                <td align="center">${news.comment.size()}</td>
                <td><input type="button" onclick="editNews(${news.newsId})"
                           value="<spring:message code="button.edit"/>" /></td>
                <td><input type="button" onclick="deleteNews(${news.newsId},'${news.title}')"
                           value="<spring:message code="button.delete"/>" /></td>
            </tr>
            </c:forEach>
        </table>
        <br/>
        <div style="text-align: center">
            <c:if test="${startPage > 1}">
                <input type="button" onclick="pageSelector(1)" value="||<-" />
                <input type="button" onclick="pageSelector(${startPage-1})" value="<-"/>
            </c:if>
            ${startPage}
            <c:if test="${startPage < lastPage}">
                <input type="button" onclick="pageSelector(${startPage + 1})" value="->"/>
                <input type="button" onclick="pageSelector(${lastPage})" value="->||"/>
            </c:if>
        </div>
    </form>
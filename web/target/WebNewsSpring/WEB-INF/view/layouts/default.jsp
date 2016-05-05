<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<html>
    <head>
        <title><tiles:insertAttribute name="title"/></title>
    </head>
    <body>
    <tiles:insertAttribute name="header"/>
    <div style="float: right;">
        <a href="?locale=ru" style="padding: 5px">RU</a>
        <a href="?locale=en" style="padding: 5px">EN</a>
    </div>
    <tiles:insertAttribute name="body"/>
    <tiles:insertAttribute name="footer"/>
    </body>
</html>
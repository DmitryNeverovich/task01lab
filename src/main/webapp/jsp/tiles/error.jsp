<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@ page isELIgnored="false" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="content_head"><a href="/NewsManagement/News_action.do?method=list"><bean:message key="news.content.header"/></a> >> <bean:message key="news.list.content.header.topic"/></div>
<div class="content_body">
    <c:set var="current_page" value="error_page" scope="session"/>
    <h1><bean:message key="news.error"/></h1>
</div>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>

<div class="header">
    <tiles:insert attribute="language_box"/>
    <h1><bean:message key="news.header"/></h1>
</div>

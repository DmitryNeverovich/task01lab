<%@ page contentType="text/html;charset=UTF-8" 
         language="java" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title><bean:message key="news.header"/></title>
        <link href="jsp/css/style.css" rel="stylesheet" type="text/css" />
        <jsp:include page="/jsp/js/validationMessage.jsp"/>
        <script type="text/javascript" src="jsp/js/validation.js"></script>
    </head>
    <body>
        <tiles:insert attribute="header"/>
        <div class="layout">
            <div class="sidebar">
                <div class="sidebar_head"><bean:message key="news.menu.header"/></div>
                <ul>
                    <li><a href="/NewsManagement/News_action.do?method=list"><bean:message key="news.menu.item.news_list"/></a></li>
                    <li><a href="/NewsManagement/News_action.do?method=add"><bean:message key="news.menu.item.add_news"/></a></li>
                </ul>
            </div>
            <div class="content">
                <tiles:insert attribute="body"/>
            </div>
        </div>
        <div class="footer"><bean:message key="news.copyright"/></div>
    </body>
</html>
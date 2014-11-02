<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>

<div class="languages_box">
    <a href="/NewsManagement/News_action.do?method=switchLang&lang=en"><bean:message key="news.language.en"/></a>
    <a href="/NewsManagement/News_action.do?method=switchLang&lang=ru"><bean:message key="news.language.ru"/></a>
</div>

<%-- 
    Document   : add
    Created on : Sep 22, 2014, 10:58:25 AM
    Author     : Dzmitry_Neviarovich
--%>
<%@ page isELIgnored="false" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="content_head"><a href="/NewsManagement/News_action.do?method=list"><bean:message key="news.content.header"/></a> >> <bean:message key="news.save.content.header.topic"/></div>
<div class="content_body">
    <html:form action="/News_action">
        <input type="hidden" name="page" value="page_edit">
        <html:hidden property="newsMessage.id"/>
        <input type="hidden" name="newsMessage.date" value='<bean:write name="newsForm" property="newsMessage.date" formatKey="format.date"/>'/>
        <c:set var="current_page" value="news_save_page" scope="session"/>
        <div class="news_title">
            <div class="news_title_head"><bean:message key="news.save.news_title"/></div>
            
            <div class="news_title_body_edit"><html:text name="newsForm" property="newsMessage.title" maxlength="100"/></div>
        </div>
        <div class="news_title">
            <div class="news_title_head"><bean:message key="news.save.news_date"/></div>
            <div class="news_title_body"><input type="text" name="date" maxlength="10"/></div>
        </div>
        <div class="news_title">
            <div class="news_title_head"><bean:message key="news.save.news_brief"/></div>
            <div class="news_title_body"><html:textarea name="newsForm" property="newsMessage.brief" rows="4" cols="60" /></div>
        </div>
        <div class="news_title">
            <div class="news_title_head"><bean:message key="news.save.news_content"/></div>
            <div class="news_title_body"><html:textarea name="newsForm" property="newsMessage.content" rows="8" cols="40"/></div>
        </div>
        <div class="news_edit_submit">
            <html:submit property="method" onclick="return checkForm()"><bean:message key="news.save.submit.save"/></html:submit>
            <html:submit property="method"><bean:message key="news.save.submit.cancel"/></html:submit>
            </div>
    </html:form>
</div>
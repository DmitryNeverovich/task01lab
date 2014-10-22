<%@ page contentType="text/html;charset=UTF-8" 
         language="java" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <link href="jsp/css/style.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript">         
            var resource = {
                checkform_error: '<bean:message key="checkform.error"/>',
                checkform_error_title: '<bean:message key="checkform.error.title"/>',
                checkform_error_date: '<bean:message key="checkform.error.date"/>',
                
                checkform_error_date_day: '<bean:message key="checkform.error.date.day"/>',
                checkform_error_date_month: '<bean:message key="checkform.error.date.month"/>',
                checkform_error_date_year: '<bean:message key="checkform.error.date.year"/>',
                
                checkform_error_brief: '<bean:message key="checkform.error.brief"/>',
                checkform_error_brief_overflow: '<bean:message key="checkform.error.brief_overflow"/>',
                checkform_error_content: '<bean:message key="checkform.error.content"/>',
                checkform_error_content_overflow: '<bean:message key="checkform.error.content_overflow"/>',
                checkform_error_date_format: '<bean:message key="checkform.error.date.format"/>',
                askDelete: '<bean:message key="askDelete"/>',
                askDeleteAllOneNews: '<bean:message key="askDeleteNews"/>',
                askDeleteAllSomeNews: '<bean:message key="askDeleteSomeNews"/>',
                askDeleteAll_error: '<bean:message key="askDeleteAll.error"/>',
                        
                lang: '<bean:message key="format.lang"/>',
                formatDate: '<bean:message key="format.date"/>'
            };
        </script>
        <script type="text/javascript" src="jsp/js/script.js"></script>
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
        <div class="footer">Copyright © EPAM 2008.All rights reserved.</div>
    </body>
</html>
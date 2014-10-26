/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.testapp.presentation.action;

import com.epam.testapp.model.News;
import com.epam.testapp.presentation.form.NewsForm;
import com.epam.testapp.recource.DataUtil;
import com.epam.testapp.service.IService;
import com.epam.testapp.service.ServiceException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;
import org.apache.struts.actions.LookupDispatchAction;

/**
 *
 * @author Dzmitry_Neviarovich
 */
public class NewsAction extends LookupDispatchAction {
    
    private final static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(NewsAction.class);

    private IService newsService;

    public void setNewsService(IService newsService) {
        this.newsService = newsService;
    }

    /**
     * Gets list news from the service and returns the ActionForward instance for the page to view list news.
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        
        NewsForm newsForm = (NewsForm) form;
        
        try {
            newsForm.setNewsList(newsService.getList());
        } catch (ServiceException ex) {
            logger.error("Problem with DAO", ex);
            return mapping.findForward(DataUtil.ERROR_PAGE);
        }
        
        ActionErrors actionErrors = form.validate(mapping, request);
        if (!actionErrors.isEmpty()) {
            addErrors(request, actionErrors);
        }
        
        return mapping.findForward(DataUtil.NEWS_LIST);
    }

    /**
     * Gets the selected news from the service and returns the ActionForward instance for the page to view news.
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        
        String strId = request.getParameter(DataUtil.ID);
        News news = null;
        int id = 0;
        
        if (!strId.isEmpty()) {
            id = Integer.valueOf(strId);
        }
        
        try {
            news = newsService.findById(id);
        } catch (ServiceException ex) {
            logger.error("Problem with DAO", ex);
            return mapping.findForward(DataUtil.ERROR_PAGE);
        }
        
        NewsForm newsForm = (NewsForm) form;
        newsForm.setNewsMessage(news);

        return mapping.findForward(DataUtil.NEWS_VIEW);
    }

    /**
     * Gets the selected news from the service and returns the ActionForward instance for the page to edit news.
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        NewsForm newsForm = (NewsForm) form;
        String strId = request.getParameter(DataUtil.ID);
        int id = 0;
              
        if (StringUtils.isNotEmpty(strId)) {
            id = Integer.valueOf(strId);
        }else{
            id = newsForm.getNewsMessage().getId();
        }
        
        try {
            newsForm.setNewsMessage(newsService.findById(id));
        } catch (ServiceException ex) {
            logger.error("Problem with DAO", ex);
            return mapping.findForward(DataUtil.ERROR_PAGE);
        }
        
        return mapping.findForward(DataUtil.NEWS_SAVE);
    }

    /**
     * Sends request to remove news from the Service and redirects to view page.
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        NewsForm newsForm = (NewsForm)form;
        try {
            newsService.remove(newsForm.getNewsID());
        } catch (ServiceException ex) {
            logger.error("Problem with DAO", ex);
            return mapping.findForward(DataUtil.ERROR_PAGE);
        }
        
        ActionRedirect redirectList = new ActionRedirect(DataUtil.ACTION);
        redirectList.addParameter(DataUtil.METHOD, DataUtil.LIST);
        return redirectList;
    }

    /**
     * Returns the ActionForward instance for the previous page.
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward cancel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        String previousPage = (String) request.getSession().getAttribute(DataUtil.PREVIOUS_PAGE);
        return mapping.findForward(previousPage);
    }

    /**
     * Sends request to save news from the Service and redirects to view page.
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        
        NewsForm newsForm = (NewsForm) form;
        String id = request.getParameter(DataUtil.ID);        
        
        if (!StringUtils.equals(id, DataUtil.ID_ZERO) && !StringUtils.isEmpty(id)) {
            newsForm.getNewsMessage().setId(Integer.valueOf(id));
        }
        try {
            News news = newsService.save(newsForm.getNewsMessage());
            newsForm.setNewsMessage(news);
        } catch (ServiceException ex) {
            logger.error("Problem with DAO", ex);
            return mapping.findForward(DataUtil.ERROR_PAGE);
        }

        return mapping.findForward(DataUtil.NEWS_VIEW);
    }

    /**
     * Returns the ActionForward instance for the page to add news.
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        NewsForm newsForm = (NewsForm) form;
        News news = new News();
        news.setDate(new Date());
        newsForm.setNewsMessage(news);
        
        return mapping.findForward(DataUtil.NEWS_SAVE);
    }

    /**
     * Switches the language for the current page.
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward switchLang(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        Locale lang = new Locale((String) request.getParameter(DataUtil.LANG));
        request.getSession().setAttribute(Globals.LOCALE_KEY, lang);
        String currentPage = (String) request.getSession().getAttribute(DataUtil.CURRENT_PAGE);

        return mapping.findForward(currentPage);
    }
    
    @Override
    protected Map getKeyMethodMap() {
        Map keyMap = new HashMap();
        keyMap.put("list", "list");
        keyMap.put("view", "view");
        keyMap.put("edit", "edit");
        keyMap.put("delete", "delete");
        keyMap.put("cancel", "cancel");
        keyMap.put("save", "save");
        keyMap.put("add", "add");
        keyMap.put("switchLang", "switchLang");
        keyMap.put("news.view.submit.edit", "edit");
        keyMap.put("news.list.submit.delete", "delete");
        keyMap.put("news.save.submit.save", "save");
        keyMap.put("news.save.submit.cancel", "cancel");
        return keyMap;

    }
}

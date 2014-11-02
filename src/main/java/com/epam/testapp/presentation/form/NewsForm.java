
package com.epam.testapp.presentation.form;

import com.epam.testapp.model.News;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author Dzmitry_Neviarovich
 */
public class NewsForm extends ActionForm{
    private News newsMessage;
    private List<News> newsList;
    private Integer[] newsID;

    public NewsForm(){
        newsMessage = new News();
    }

    public Integer[] getNewsID() {
        return newsID;
    }

    public void setNewsID(Integer[] newsID) {
        this.newsID = newsID;
    }
        
    public News getNewsMessage() {
        return newsMessage;
    }

    public void setNewsMessage(News newsMessage) {
        this.newsMessage = newsMessage;
    }

    public List<News> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
    }

    public NewsForm(News newsMessage, List<News> newsList, Integer[] newsID) {
        this.newsMessage = newsMessage;
        this.newsList = newsList;
        this.newsID = newsID;
    }

    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        
        ActionErrors errors = new ActionErrors();
        if (newsList != null && newsList.isEmpty()) {
            errors.add("listEmpty", new ActionMessage("error.list.empty"));
        }
        if (newsList == null) {
            errors.add("listEmpty", new ActionMessage("error.list.empty"));
        }
        return errors;
    }
    
}

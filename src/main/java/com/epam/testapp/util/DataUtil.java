package com.epam.testapp.util;

/**
 * Class containing String constants - names of page
 * @author Dzmitry_Neviarovich
 */
public final class DataUtil {

    private DataUtil() {
    }
    
    public final static String ACTION = "/News_action.do";
    public final static String NEWS_LIST = "news_list_page";
    public final static String NEWS_SAVE = "news_save_page";
    public final static String NEWS_VIEW = "news_view_page";
    public final static String ERROR_PAGE = "error_page";

    public final static String CURRENT_PAGE = "current_page";
    public final static String PREVIOUS_PAGE = "previous_page";

    public final static String ID_ZERO = "0";
    public final static String LIST = "list";
    public final static String VIEWS = "view";
    public final static String METHOD = "method";
    public final static String LANG = "lang";
    public final static String NEWS_MESSAGE_ID = "newsMessage.id";
    public final static String ID = "id";
    
    public static final String SQL_DELETE_NEWS_QUERY = "DELETE FROM NEWS_DATA WHERE NEWS_ID IN (";
    
    public final static String DATE_FORMAT = "MM/dd/yyyy";
    public static final String ENCODING = "encoding";
    
}

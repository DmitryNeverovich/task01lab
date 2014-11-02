/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.testapp.database.impl;

import com.epam.testapp.database.AbstractNewsDAO;
import com.epam.testapp.database.DAOException;
import com.epam.testapp.model.News;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import junit.framework.Assert;
import static junit.framework.Assert.assertEquals;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;



/**
 *
 * @author Dzmitry_Neviarovich
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/application-context-test.xml" })
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class,
    TransactionalTestExecutionListener.class,
    DbUnitTestExecutionListener.class })
@TransactionConfiguration(defaultRollback = true)
@DatabaseSetup("classpath:news.xml")
public class NewsDAOTest {
    
    private final static String NEWS_TITLE = "test title 1";
    private final static String NEWS_TITLE_INSERT = "newsTitle";    
    private final static String BRIEF = "BRIEF TEST 1";    
    private final static String CONTENT = "CONTENT TEST 1";    

    private final static int NEWS_LIST_SIZE = 3;
    
    @Autowired
    private DataSource dataSource;
    
    @Autowired
    private AbstractNewsDAO newsDAO;
    
    

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public DataSource getDataSource() {
        return dataSource;
    }
    
    public void setNewsDAO(NewsDAO newsDAO) {
        this.newsDAO = newsDAO;
    }
        
    public NewsDAOTest() {
    }

    /**
     * Test of getList method, of class NewsDAO.
     */
    @Test
    public void testGetList(){

        List<News> result = null;
        try {
            result = newsDAO.getList();
        } catch (DAOException ex) {
            Logger.getLogger(NewsDAOTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertEquals(result.size(), NEWS_LIST_SIZE);

    }

    @Test
    public void testInsert() throws Exception {

        News insertNews = new News();
        insertNews.setId(0);
        insertNews.setTitle(NEWS_TITLE_INSERT);
        insertNews.setDate(new Date());
        insertNews.setBrief(BRIEF);
        insertNews.setContent(CONTENT);
        
        News news = newsDAO.save(insertNews);
        
        assertEquals(insertNews.getTitle(), news.getTitle());
        assertEquals(insertNews.getDate(), news.getDate());
        assertEquals(insertNews.getBrief(), news.getBrief());
        assertEquals(insertNews.getContent(), news.getContent());
       
    }

    /**
     * Test of save method, of class NewsDAO.
     */
    @Test
    public void testSave() throws Exception {

        News newNews = newsDAO.findById(1);
        newNews.setTitle(NEWS_TITLE);
        newsDAO.save(newNews);
        News newSaveNews = newsDAO.findById(1);
        assertEquals(newSaveNews.getTitle(), newNews.getTitle());
        
    }

    /**
     * Test of remove method, of class NewsDAO.
     */
    @Test
    public void testRemove() throws Exception {
        
        List<News> beforeNewsList = newsDAO.getList();
        News news = newsDAO.findById(2);
        Integer[] ids = {news.getId() };
        newsDAO.remove(ids);
        List<News> afterNewsList = newsDAO.getList();
        Assert.assertNotSame(beforeNewsList.size(),afterNewsList.size());
                
    }

    /**
     * Test of findById method, of class NewsDAO.
     */
    @Test
    public void testFindById() throws Exception {
        News result = null;
        try {
            result = newsDAO.findById(1);
        } catch (DAOException ex) {
            Logger.getLogger(NewsDAOTest.class.getName()).log(Level.SEVERE, null, ex);
        }
//        News result = newsDAO.findById(1);
        assertEquals(NEWS_TITLE, result.getTitle());
    }

}

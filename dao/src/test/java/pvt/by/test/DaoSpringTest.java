/**
 * Created by Sergey Buglak
 */

package pvt.by.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import pvt.by.dao.exceptions.DaoException;
import pvt.by.dao.implement.NewsDaoImpl;
import pvt.by.dao.interfaces.NewsDao;
import pvt.by.pojos.News;
import pvt.by.pojos.NewsDetail;


import javax.transaction.Transactional;

import java.util.Date;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.*;

@ContextConfiguration("/testContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class DaoSpringTest {
    // Get log4j2 logger
    private static Logger logger = LogManager.getLogger(DaoSpringTest.class);
    @Autowired
    private NewsDao newsDao;

    @Test
    public void addPerson() {
        News n = new News();
        n.setTitle("Title");
        n.setAnnotation("Annotation");
        n.setDocument("Document");

        NewsDetail nd = new NewsDetail();
        nd.setAuthor("Author");
        nd.setAgency("Agency");
        nd.setDrelease(new Date());
        n.setNewsDetail(nd);
        nd.setNews(n);

        try {
            newsDao.saveOrUpdate(n);
            assertNotNull(n.getNewsId());
        } catch (DaoException e) {
            logger.info("Error Add News to NewsHiber_Test "+e);
        }
        try {
            assertEquals("Проверка", n, newsDao.get(n.getNewsId()));
        } catch (DaoException e) {
            logger.info("Error in Test Not Equal object "+e );
        }
    }

    @After
    public void deletePerson() {
        List<News> list = null;
        try {
            list = newsDao.getNewsAll();
        } catch (DaoException e) {
            logger.info("Error Add News to NewsHiber_Test");
        }
        int size = list.size();
        News persistent = list.get(0);
        try {
            newsDao.delete(persistent);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        try {
            assertNotSame(newsDao.getNewsCount(), size);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
}

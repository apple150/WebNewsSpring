/**
 * Created by Sergey Buglak
 */

package pvt.by.dao.implement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pvt.by.dao.interfaces.NewsDetailDao;
import pvt.by.pojos.NewsDetail;

/**
 * Custom methods for NewsDetail
 */
@Repository
public class NewsDetailDaoImpl extends BaseDao<NewsDetail> implements NewsDetailDao {
    // Get log4j2 logger
    private static Logger logger = LogManager.getLogger(NewsDetailDaoImpl.class);

    @Autowired
    public NewsDetailDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
        logger.info("Create NewsDetailDaoImpl");
    }
}
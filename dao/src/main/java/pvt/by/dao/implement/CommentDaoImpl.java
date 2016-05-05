/**
 * Created by Sergey Buglak
 */

package pvt.by.dao.implement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pvt.by.dao.interfaces.CommentDao;
import pvt.by.pojos.Comment;

/**
 * Custom methods for Comments
 */
@Repository
public class CommentDaoImpl extends BaseDao<Comment> implements CommentDao {
    // Get log4j2 logger
    private static Logger logger = LogManager.getLogger(CommentDaoImpl.class);

    @Autowired
    public CommentDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
        logger.info("Create CommentDaoImpl");
    }
}

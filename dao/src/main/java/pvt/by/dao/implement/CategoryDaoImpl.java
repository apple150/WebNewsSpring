/**
 * Created by Sergey Buglak
 */

package pvt.by.dao.implement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pvt.by.dao.exceptions.DaoException;
import pvt.by.dao.interfaces.CategoryDao;
import pvt.by.pojos.Category;

import java.util.List;

/**
 * Custom methods for Category
 */
@Repository
public class CategoryDaoImpl extends BaseDao<Category> implements CategoryDao {
    private static final String GET_ALL_CATEGORIES_EXCEPTION = "Get all categories exception";
    private static final String GET_ALL_CATEGORIES_OK = "Get all categories OK";
    // Get log4j2 logger
    private static Logger logger = LogManager.getLogger(CategoryDaoImpl.class);

    @Autowired
    public CategoryDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
        logger.info("Create CategoryDaoImpl");
    }

    @Override
    public List<Category> getCategories() throws DaoException {
        String hql = "FROM Category";
        Query query = getSession().createQuery(hql);
        try {
            List<Category> categories = query.list();
            logger.info(GET_ALL_CATEGORIES_OK);
            return categories;
        } catch (HibernateException e) {
            logger.info(GET_ALL_CATEGORIES_EXCEPTION);
            throw new DaoException(e);
        }
    }
}

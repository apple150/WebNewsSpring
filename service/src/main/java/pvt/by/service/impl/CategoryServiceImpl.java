/**
 * Created by Sergey Buglak
 */

package pvt.by.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pvt.by.dao.exceptions.DaoException;
import pvt.by.dao.interfaces.CategoryDao;
import pvt.by.pojos.Category;
import pvt.by.service.interfaces.CategoryService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    private static final String GET_CATEGORIES_EXCEPTION = "Get categories exception";
    // Get log4j2 logger
    private static Logger logger = LogManager.getLogger(CategoryServiceImpl.class);

    @Autowired
    private CategoryDao categoryDao;
    @Autowired
    private SessionFactory sessionFactory;

    public CategoryServiceImpl() {
        super();
    }

    @Override
    public List<Category> getCategories() {
        List<Category> categories = null;
        try {
            categories = categoryDao.getCategories();
            return categories;
        } catch (DaoException e) {
            logger.error(GET_CATEGORIES_EXCEPTION);
            return new ArrayList<Category>();
        }
    }
}

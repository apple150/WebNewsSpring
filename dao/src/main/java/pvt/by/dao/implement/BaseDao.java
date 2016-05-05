/**
 * Created by Sergey Buglak
 */

package pvt.by.dao.implement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pvt.by.dao.exceptions.DaoException;
import pvt.by.dao.interfaces.Dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * CRUD DAO operations
 */

@Repository
public class BaseDao<T> implements Dao<T> {
    private static final String SAVE_OR_UPDATE_OK = "saveOrUpdate(t): OK";
    private static final String SAVE_OR_UPDATE_EXCEPTION = "saveOrUpdate() exception";
    private static final String ERROR_SAVE_OR_UPDATE = "Error saveOrUpdate NEWS in Dao";
    private static final String GET_ID_OK = "get(id) OK";
    private static final String GET_EXCEPTION = "get() exception";
    private static final String ERROR_GET_ID = "Error get(id)";
    private static final String LOAD_ID_OK = "load(id) OK";
    private static final String ERROR_LOAD_ID = "Error load ID";
    private static final String DELETE_OK = "delete(t) OK";
    private static final String DELETE_EXCEPTION = "delete() exception";
    private static final String ERROR_DELETE = "Error delete(t)";
    private static final String GET_ALL_OK = "getALL() OK";
    private static final String GET_ALL_EXCEPTION = "getAll() exception";

    // Get log4j2 logger
    private static Logger logger = LogManager.getLogger(BaseDao.class);

    private SessionFactory sessionFactory;

    @Autowired
    public BaseDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public BaseDao() {
    }

    @Override
    public void saveOrUpdate(T t) throws DaoException {
        try {
            getSession().saveOrUpdate(t);
            logger.info(SAVE_OR_UPDATE_OK);
        } catch (HibernateException e) {
            logger.error(SAVE_OR_UPDATE_EXCEPTION);
            throw new DaoException(e);
        }
    }

    @Override
    public T get(Serializable id) throws DaoException {
        try {
            T t = (T) getSession().get(getPersistentClass(), id);
            logger.info(GET_ID_OK);
            return t;
        } catch (HibernateException e) {
            logger.error(GET_EXCEPTION);
            throw new DaoException(e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public T load(Serializable id) throws DaoException {
        T t = null;
        try {
            t = (T) getSession().load(getPersistentClass(), id);
            logger.info(LOAD_ID_OK);
        } catch (HibernateException e) {
            logger.error(ERROR_LOAD_ID);
            throw new DaoException(e);
        }
        return t;
    }

    @Override
    public void delete(T t) throws DaoException {
        try {
            getSession().delete(t);
            logger.info(DELETE_OK);
        } catch (HibernateException e) {
            logger.error(DELETE_EXCEPTION);
            throw new DaoException(e);
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private Class getPersistentClass() {
        return (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }
}
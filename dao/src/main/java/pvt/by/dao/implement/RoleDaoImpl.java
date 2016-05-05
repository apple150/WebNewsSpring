/**
 * Created by Sergey Buglak
 */

package pvt.by.dao.implement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pvt.by.dao.interfaces.RoleDao;
import pvt.by.pojos.Role;

/**
 * Custom methods for Role
 */
@Repository
public class RoleDaoImpl extends BaseDao<Role> implements RoleDao {
    // Get log4j2 logger
    private static Logger logger = LogManager.getLogger(RoleDaoImpl.class);

    @Autowired
    public RoleDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
        logger.info("Create RoleDaoImpl");
    }
}
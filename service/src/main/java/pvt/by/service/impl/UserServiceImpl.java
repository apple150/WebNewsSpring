/**
 * Created by Sergey Buglak
 */

package pvt.by.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pvt.by.dao.interfaces.UserDao;
import pvt.by.pojos.User;
import pvt.by.service.interfaces.UserService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    // Get log4j2 logger
    private static Logger logger = LogManager.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao; // load User DAO
    @Autowired
    private SessionFactory sessionFactory;

    private UserServiceImpl() {
        super();
    }
    /**
     * Look for user&password and returns true if found
     */
    @Override
    public boolean isLoginSuccess(String username, String pass) {
        List<User> users = null;
        // users will be initialized as List or method returns false
        try {
            users = userDao.getAllUsers();
        } catch (Exception e) {
            return false;
        }

        // Iterate thru Users to find suitable
        for (User user : users) {
            if (user.getEmail().equals(username) && user.getPassword().equals(pass)) {
                return true;
            }
        }
        return false;
    }
}
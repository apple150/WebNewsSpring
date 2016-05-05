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
import pvt.by.dao.interfaces.TagDao;
import pvt.by.pojos.Tag;
import pvt.by.service.interfaces.TagService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TagServiceImpl implements TagService {
    private static final String GET_TAGS_EXCEPTION = "getTags() exception";
    private static final String GET_TAGS_OK = "getTags() OK";

    // Get log4j2 logger
    private static Logger logger = LogManager.getLogger(TagServiceImpl.class);

    @Autowired
    private TagDao tagDao;
    @Autowired
    private SessionFactory sessionFactory;

    private TagServiceImpl() {
        super();
    }

    @Override
    public List<Tag> getTags() {
        List<Tag> tags;
        try {
            tags = tagDao.getTags();
            logger.info(GET_TAGS_OK);
            return tags;
        } catch (DaoException e) {
            logger.error(GET_TAGS_EXCEPTION);
            return new ArrayList<Tag>();
        }
    }
}

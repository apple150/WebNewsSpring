/**
 * Created by Sergey Buglak
 */

package pvt.by.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pvt.by.command.util.WebConstants;
import pvt.by.pojos.Category;
import pvt.by.pojos.News;
import pvt.by.pojos.Tag;
import pvt.by.service.impl.CategoryServiceImpl;
import pvt.by.service.impl.NewsServiceImpl;
import pvt.by.service.impl.TagServiceImpl;
import pvt.by.service.impl.UserServiceImpl;
import pvt.by.service.interfaces.CategoryService;
import pvt.by.service.interfaces.NewsService;
import pvt.by.service.interfaces.TagService;
import pvt.by.service.interfaces.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Component
public class ShowEditNewsPageCommand implements Command {
    // Get log4j2 logger
    private static Logger logger = LogManager.getLogger(ShowEditNewsPageCommand.class);

    // Get UserService singleton
//    @Autowired
//    private UserService userService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private TagService tagService;
    @Autowired
    private NewsService newsService;

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        String idNews = req.getParameter(WebConstants.ID_NEWS_PARAM);

        // Get single news if exist
        News news = null;
        try {
            news = newsService.getSingleNews(idNews);
        } catch (Exception e) {
        }
        // create proxy if news not exist
        if (news == null) {
            news = new News();
        }

        // Get Categories list
        List<Category> categories = categoryService.getCategories();
        // Get Tags list
        List<Tag> tags = tagService.getTags();

        // Attach lists to request
        req.setAttribute(WebConstants.NEWS_ATTR, news);
        req.setAttribute(WebConstants.CATEGORIES_ATTR, categories);
        req.setAttribute(WebConstants.TAGS_ATTR, tags);

        // Forward request
        req.setAttribute(WebConstants.REQUEST_HANDLE_DIRECTIVE_ATTR, WebConstants.FORWARD);
        req.setAttribute(WebConstants.URI_ATTR, WebConstants.EDIT_NEWS_PAGE);
    }
}

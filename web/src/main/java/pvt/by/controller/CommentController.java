/**
 * Created by Sergey Buglak
 */
package pvt.by.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import pvt.by.command.util.WebConstants;
import pvt.by.pojos.News;
import pvt.by.service.interfaces.CommentService;
import pvt.by.service.interfaces.NewsService;
import pvt.by.util.ServiceUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CommentController {
    // Get log4j2 logger
    private static Logger logger = LogManager.getLogger(NewsController.class);

    // Default value of newsByPage
    private static final int DEFAULT_NEWS_BY_PAGE = 3;

    // Name of the attribute which contains all news
    private static final String NEWS_ATTR = "newsList";
    private static final String PAGINATION_START_PAGE = "startPage";
    private static final String PAGINATION_NEWS_BY_PAGE = "newsByPage";
    private static final String PAGINATION_LAST_PAGE = "lastPage";

    @Autowired
    private CommentService commentService;


    @RequestMapping(value = "/", params = "do=delComm")
    public String deleteComment(ModelMap model, HttpServletRequest req) {
        String idNews = req.getParameter(WebConstants.ID_NEWS_PARAM);
        // Call service method to delete Comment by its id from request
        try {
            commentService.deleteComment(req.getParameter(WebConstants.ID_COMM_PARAM));
        } catch (Exception e) {
            logger.error("Error delete Comment: "+e);
        }
        return "redirect:/?do=show&id="+idNews;
    }
}

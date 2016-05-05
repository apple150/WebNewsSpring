package pvt.by.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import pvt.by.command.util.WebConstants;
import pvt.by.dto.CommentDTO;
import pvt.by.dto.NewsDTO;
import pvt.by.pojos.Category;
import pvt.by.pojos.News;
import pvt.by.pojos.Tag;
import pvt.by.service.interfaces.CategoryService;
import pvt.by.service.interfaces.CommentService;
import pvt.by.service.interfaces.NewsService;
import pvt.by.service.interfaces.TagService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * Created by ircst on 04.04.15.
 */
@Controller
public class NewsController {
    // Get log4j2 logger
    private static Logger logger = LogManager.getLogger(NewsController.class);
    @Autowired
    private NewsService newsService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private TagService tagService;
    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/", params = "do=show")
    public String showOneNews(ModelMap model, HttpServletRequest req) {
        News news = null;
        try {
            news = newsService.getSingleNews(req.getParameter(WebConstants.ID_NEWS_PARAM));
        } catch (Exception e) {
            logger.error("Error: "+e);
        }
        req.setAttribute(WebConstants.NEWS_ATTR, news);
        return "news/one";
    }

    @RequestMapping(value = "/", params = "do=del")
    public String deleteOneNews(ModelMap model, HttpServletRequest req) {
        try {
            newsService.deleteNews(req.getParameter(WebConstants.ID_NEWS_PARAM));
        } catch (Exception e) {
            logger.error("Error Delete: "+e);
        }
        return "redirect:/";
    }

    /**
     * Save One News
     */
    @RequestMapping(value = "/", params = "do=save")
    public String saveOneNews(ModelMap model, HttpServletRequest req) {
        // Parse request parameters
        String idNews = req.getParameter(WebConstants.ID_NEWS_PARAM);
        String header = req.getParameter(WebConstants.NEWS_HEADER_PARAM);
        String text = req.getParameter(WebConstants.NEWS_TEXT_PARAM);
        String category = req.getParameter(WebConstants.NEWS_CATEGORY_PARAM);
        String[] tags = req.getParameterValues(WebConstants.NEWS_TAG_PARAM);
        Date date = new Date();

        //Get username from Security context
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();

//        String user = (String) req.getSession().getAttribute(WebConstants.LOGIN_ATTR);

        // Put parameters to DTO
        NewsDTO newsDto = new NewsDTO();
        newsDto.setCategory(category);
        newsDto.setDate(date);
        newsDto.setHeader(header);
        newsDto.setTags(tags);
        newsDto.setText(text);
//        newsDto.setUser(user);
        newsDto.setUser(username);
        newsDto.setIdNews(idNews);

        // Call service method
        try {
            newsService.saveNews(newsDto);
        } catch (Exception e) {
            logger.error("Error save one News: " + e);
        }
        return "redirect:/";
    }

    /**
     * Show News for it's correction
     * @param model
     * @param req
     * @return
     */
    @RequestMapping(value = "/", params = "do=showEditNews")
    public String editOneNews(ModelMap model, HttpServletRequest req) {
        String idNews = req.getParameter(WebConstants.ID_NEWS_PARAM);

        // Get single news if exist
        News news = null;
        try {
            news = newsService.getSingleNews(idNews);
        } catch (Exception e) {
            logger.error("Error Can't find news in edit: "+e);
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

        return "news/edit";
    }

    /**
     * Add One News
     * @param model
     * @param req
     * @return
     */
    @RequestMapping(value = "/", params = "do=addNews")
    public String addOneNews(ModelMap model, HttpServletRequest req) {
        // Create empty single news
        News news = new News();
        // Get Categories list
        List<Category> categories = categoryService.getCategories();
        // Get Tags list
        List<Tag> tags = tagService.getTags();
        // Attach lists to request
        req.setAttribute(WebConstants.NEWS_ATTR, news);
        req.setAttribute(WebConstants.CATEGORIES_ATTR, categories);
        req.setAttribute(WebConstants.TAGS_ATTR, tags);
        // **** Go to tiles.xml ****
        // <definition name="news/add" extends="default">
        //    <put-attribute name="title" value="AddNews"/>
        //    <put-attribute name="body" value="/WEB-INF/view/news/editnews.jsp"/>
        // </definition>
        return "news/add";
    }

    /**
     * Save One Comment
     * @param model
     * @param req
     * @return
     */
    @RequestMapping(value = "/", params = "do=saveComm")
    public String saveComment(ModelMap model, HttpServletRequest req) {
        // Get comment text and id of news from request
        Date commentDate = new Date();
        String idComm = req.getParameter(WebConstants.ID_COMM_PARAM);
        String idNews = req.getParameter(WebConstants.ID_NEWS_PARAM);
        String text = req.getParameter(WebConstants.COMMENT_TEXT_PARAM);
        // Get username from session

//        String user = (String) req.getSession().getAttribute(WebConstants.LOGIN_ATTR);
        //Get username from Security context
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();

        // Put parameters to DTO
        CommentDTO commentDto = new CommentDTO();
        commentDto.setCommentDate(commentDate);
        commentDto.setIdComm(idComm);
        commentDto.setIdNews(idNews);
        commentDto.setText(text);
//        commentDto.setUser(user);
        commentDto.setUser(username);

        // Call service method
        try {
            commentService.saveComment(commentDto);
        } catch (Exception e) {
            logger.error("Save One Comment: "+e);
        }
        return "redirect:/?do=show&id="+idNews;
    }
}

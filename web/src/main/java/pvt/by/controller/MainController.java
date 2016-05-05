/**
 * Created by Sergey Buglak
 */
package pvt.by.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import pvt.by.command.util.WebConstants;
import pvt.by.pojos.News;
import pvt.by.service.interfaces.NewsService;
import pvt.by.util.ServiceUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class MainController {
    // Default value of newsByPage
    private static final int DEFAULT_NEWS_BY_PAGE = 3;

    // Name of the attribute which contains all news
    private static final String NEWS_ATTR = "newsList";
    private static final String PAGINATION_START_PAGE = "startPage";
    private static final String PAGINATION_NEWS_BY_PAGE = "newsByPage";
    private static final String PAGINATION_LAST_PAGE = "lastPage";

    @Autowired
    private NewsService newsService;


    @RequestMapping("/loginPage")
    public String showLoginPage() {
        return "loginDir/loginPage";
    }

    @RequestMapping("/")
    public String showMainPage(ModelMap model, HttpServletRequest req) {
        // Get pagination parameters
        int newsByPage;
        int startPage;
        // Needed here to set default pagination newsByPage
        int newsCount = (int) (newsService.getNewsCount() + 0);

        // Get int pagination parameters.
        // Get news by page count
        try {
            newsByPage = ServiceUtil.stringToPositiveInt(req.getParameter(PAGINATION_NEWS_BY_PAGE));
        } catch (NumberFormatException e) {
            newsByPage = DEFAULT_NEWS_BY_PAGE;
        }
        // Get start page
        try {
            startPage = ServiceUtil.stringToPositiveInt(req.getParameter(PAGINATION_START_PAGE));
        } catch (NumberFormatException e) {
            startPage = 1;
        }

        // Calculate last page
        int lastPage = newsCount / newsByPage;
        if (newsCount % newsByPage != 0) {
            lastPage++;
        }

        // Check if startPage over total pages
        if (startPage > lastPage) {
            startPage = lastPage;
        }

        //Get sort order
        String columnName = req.getParameter(WebConstants.SORT_COLUMN_NAME_PARAM);
        if (columnName == null) {
            columnName = WebConstants.SORT_TITLE;
        }

        String sortOrder = req.getParameter(WebConstants.SORT_ORDER_PARAM);
        if (sortOrder == null) {
            sortOrder = WebConstants.SORT_ORDER_DESC;
        }

        // Get all news (pages = 1,2,3...)
        List<News> news = newsService.getNewsRows(newsByPage, startPage, columnName, sortOrder);

        // Attach them to request
        model.addAttribute(NEWS_ATTR, news);

        // Attach pagination parameters
        model.addAttribute(PAGINATION_NEWS_BY_PAGE, newsByPage);
        model.addAttribute(PAGINATION_START_PAGE, startPage);
        model.addAttribute(PAGINATION_LAST_PAGE, lastPage);

        // Attach sort parameters
        model.addAttribute(WebConstants.SORT_COLUMN_NAME_PARAM, columnName);
        model.addAttribute(WebConstants.SORT_ORDER_PARAM, sortOrder);
        return "news/view";
    }
}

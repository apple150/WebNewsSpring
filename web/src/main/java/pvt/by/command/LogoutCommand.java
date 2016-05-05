/**
 * Created by Sergey Buglak
 */

package pvt.by.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import pvt.by.command.util.WebConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LogoutCommand implements Command {
    // Get log4j2 logger
    private static Logger logger = LogManager.getLogger(LogoutCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        // Set login to null
        req.getSession().setAttribute(WebConstants.LOGIN_ATTR, null);

        // Redirect
        req.setAttribute(WebConstants.REQUEST_HANDLE_DIRECTIVE_ATTR, WebConstants.SEND_REDIRECT);
        req.setAttribute(WebConstants.URI_ATTR, WebConstants.CONTROLLER_URL);
    }
}

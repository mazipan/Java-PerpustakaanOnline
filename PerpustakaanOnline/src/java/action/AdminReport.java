package action;

import entity.Administrator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mazipan
 */
public class AdminReport implements ActionInterface {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        Administrator administrator = (Administrator) session.getAttribute("loginasadmin");

        if (administrator != null) {
            return "adminReport.jsp";

        } else {
            return "index.jsp";
        }

    }

}

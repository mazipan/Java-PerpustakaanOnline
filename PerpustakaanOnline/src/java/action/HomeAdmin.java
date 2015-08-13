package action;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author mazipan
 */
public class HomeAdmin implements ActionInterface {

    //Override methode
    @Override
    public String execute(HttpServletRequest request) {

        return "homeAdmin.jsp";
    }

}

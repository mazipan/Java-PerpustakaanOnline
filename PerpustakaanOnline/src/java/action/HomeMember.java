package action;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author mazipan
 */
public class HomeMember implements ActionInterface {

    //Override methode
    @Override
    public String execute(HttpServletRequest request) {

        return "homeMember.jsp";
    }

}

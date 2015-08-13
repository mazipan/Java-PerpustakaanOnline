package action;

import entity.Member;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 *
 * @author mazipan
 */
public class MemberProfile implements ActionInterface {

    //Override methode
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        Member member = (Member) session.getAttribute("loginasmember");

        if (member != null) {
            return "memberProfile.jsp";
        } else {
            return "index.jsp";
        }

    }

}

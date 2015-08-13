package action;

import entity.Member;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 *
 * @author mazipan
 */
public class MemberProfileEdit implements ActionInterface {

    //Override methode
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        Member member = (Member) session.getAttribute("loginasmember");

        if (member != null) {

            return "memberProfileEdit.jsp";
        } else {
            return "index.jsp";
        }

    }

}

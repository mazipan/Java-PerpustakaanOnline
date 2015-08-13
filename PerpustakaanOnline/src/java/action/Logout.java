package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mazipan
 */
public class Logout implements ActionInterface {

    @Override
    public String execute(HttpServletRequest request) {
        //deklarasi dan inisialisasi variabel userName
        System.out.println("this call logout methods...");

        HttpSession session = request.getSession(true);

        session.setAttribute("loginasadmin", null);
        session.setAttribute("loginasmember", null);
        session.setAttribute("msg", null);

        session.removeAttribute("loginasadmin");
        session.removeAttribute("loginasmember");
        session.removeAttribute("msg");

        try {
            session.invalidate();
        } catch (IllegalStateException e) {
        }

        String fileJsp = "index.jsp";

        //return fileJsp
        return fileJsp;
    }
}

package action;

import dao.MemberDao;
import entity.Administrator;
import entity.Member;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import service.ConnectionMySQL;

/**
 *
 * @author mazipan
 */
public class AdminUserManEdit implements ActionInterface {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        Administrator administrator = (Administrator) session.getAttribute("loginasadmin");
        Member member = null;

        if (administrator != null) {
            try {
                MemberDao memberDao = ConnectionMySQL.getMemberDao();
                try {

                    String idStr = request.getParameter("id");
                    if (idStr != null) {
                        Integer idUser = Integer.parseInt(idStr);
                        System.out.println("idUser edit : " + idUser);

                        member = memberDao.getMemberById(idUser);
                    }

                } catch (Exception ex) {
                    Logger.getLogger(AdminCategory.class.getName()).log(Level.SEVERE, null, ex);
                }

            } catch (SQLException ex) {
                Logger.getLogger(AdminCategory.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            return "index.jsp";
        }

        request.setAttribute("isMember", "member");
        request.setAttribute("userforedit", member);
        return "adminUserManEdit.jsp";

    }

}

package action;

import dao.AdministratorDao;
import dao.MemberDao;
import entity.Administrator;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import service.ConnectionMySQL;

/**
 *
 * @author mazipan
 */
public class AdminUserManAdminDelete implements ActionInterface {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        Administrator administrator = (Administrator) session.getAttribute("loginasadmin");
        String msg = null;

        if (administrator != null) {
            try {
                AdministratorDao administratorDao = ConnectionMySQL.getAdministratorDao();
                try {
                    String idStr = request.getParameter("id");
                    if (idStr != null) {
                        Integer idUser = Integer.parseInt(idStr);
                        System.out.println("idAdmin delete : " + idUser);

                        if (idUser == administrator.getIdAdministrator()) {
                            msg = "Sorry, you can't delete logged-in administrator";
                        } else {
                            administratorDao.Delete(idUser);
                            msg = "Admnistrator has been delete";
                        }

                    }

                } catch (Exception ex) {
                    msg = "Failed delete administrator " + ex.getMessage();
                }

            } catch (SQLException ex) {
                msg = "Failed delete administrator " + ex.getMessage();
            }
        } else {
            return "index.jsp";
        }

        request.setAttribute("isAdmin", "admin");
        request.setAttribute("msg", msg);
        return "adminUserManRedirect.jsp";

    }

}

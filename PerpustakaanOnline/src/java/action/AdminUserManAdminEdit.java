package action;

import dao.AdministratorDao;
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
public class AdminUserManAdminEdit implements ActionInterface {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        Administrator administrator = (Administrator) session.getAttribute("loginasadmin");
        Administrator adminForEdit = null;

        if (administrator != null) {
            try {
                AdministratorDao administratorDao = ConnectionMySQL.getAdministratorDao();
                try {

                    String idStr = request.getParameter("id");
                    if (idStr != null) {
                        Integer idUser = Integer.parseInt(idStr);
                        System.out.println("idAdmin edit : " + idUser);

                        if (idUser == administrator.getIdAdministrator()) {
                            adminForEdit = administratorDao.getById(idUser);
                            request.setAttribute("adminForEdit", adminForEdit);
                        } else {
                            String msg = null;
                            msg = "Sorry, you can't edit other admin except logged-in administrator";
                            request.setAttribute("isAdmin", "admin");
                            request.setAttribute("msg", msg);
                            return "adminUserManRedirect.jsp";
                        }

                    }

                } catch (Exception ex) {
                }

            } catch (SQLException ex) {
            }
        } else {
            return "index.jsp";
        }

        request.setAttribute("isAdmin", "admin");
        return "adminUserManAdminEdit.jsp";

    }

}

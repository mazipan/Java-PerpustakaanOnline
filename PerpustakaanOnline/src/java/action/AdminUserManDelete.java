package action;

import dao.MemberDao;
import dao.PeminjamanDao;
import entity.Administrator;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import service.ConnectionMySQL;

/**
 *
 * @author mazipan
 */
public class AdminUserManDelete implements ActionInterface {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        Administrator administrator = (Administrator) session.getAttribute("loginasadmin");
        String msg = null;

        if (administrator != null) {
            try {
                MemberDao memberDao = ConnectionMySQL.getMemberDao();
                PeminjamanDao peminjamanDao = ConnectionMySQL.getPeminjamanDao();

                try {

                    String idStr = request.getParameter("id");
                    if (idStr != null) {
                        Integer idUser = Integer.parseInt(idStr);
                        System.out.println("idUser delete : " + idUser);

                        boolean exist = peminjamanDao.isExistByMember(idUser);
                        if (!exist) {
                            memberDao.Delete(idUser);
                            msg = "Member has been delete";
                        } else {
                            msg = "Sorry, Member already use by transaction. </br> Please delete all transaction </br> that use this member before";
                        }

                    }

                } catch (Exception ex) {
                    msg = "Failed delete member " + ex.getMessage();
                }

            } catch (SQLException ex) {
                msg = "Failed delete member " + ex.getMessage();
            }
        } else {
            return "index.jsp";
        }

        request.setAttribute("isMember", "member");
        request.setAttribute("msg", msg);
        return "adminUserManRedirect.jsp";

    }

}

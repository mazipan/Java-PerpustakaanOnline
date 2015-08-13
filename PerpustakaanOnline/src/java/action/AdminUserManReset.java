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
import util.AES;

/**
 *
 * @author mazipan
 */
public class AdminUserManReset implements ActionInterface {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        Administrator administrator = (Administrator) session.getAttribute("loginasadmin");
        Member member = null;
        String msg = null;

        if (administrator != null) {
            try {
                MemberDao memberDao = ConnectionMySQL.getMemberDao();

                try {

                    String idStr = request.getParameter("id");
                    if (idStr != null) {
                        Integer idUser = Integer.parseInt(idStr);
                        System.out.println("idUser reset password : " + idUser);

                        member = memberDao.getMemberById(idUser);
                        member.setMemberPassword(AES.encrypt("123456"));

                        memberDao.Update(member);
                        msg = "Member password has been reset to default";
                    }

                } catch (Exception ex) {
                    msg = "Failed to save member password " + ex.getMessage();
                    Logger.getLogger(AdminCategory.class.getName()).log(Level.SEVERE, null, ex);
                }

            } catch (SQLException ex) {
                msg = "Failed to save member password " + ex.getMessage();
                Logger.getLogger(AdminCategory.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            return "index.jsp";
        }

        request.setAttribute("isMember", "member");
        request.setAttribute("msg", msg);
        return "adminUserManRedirect.jsp";

    }

}

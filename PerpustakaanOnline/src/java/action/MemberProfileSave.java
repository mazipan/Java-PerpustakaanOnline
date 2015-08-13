package action;

import dao.MemberDao;
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
 *
 * @author mazipan
 */
public class MemberProfileSave implements ActionInterface {

    //Override methode
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        Member member = (Member) session.getAttribute("loginasmember");

        if (member != null) {

            MemberDao memberDao;
            try {
                memberDao = ConnectionMySQL.getMemberDao();

                String id = request.getParameter("id");
                String memberPass = request.getParameter("password");

                try {
                    member.setMemberPassword(AES.encrypt(memberPass));
                } catch (Exception ex) {
                    Logger.getLogger(MemberProfileSave.class.getName()).log(Level.SEVERE, null, ex);
                }

                member.setMemberId(Integer.parseInt(id));
                
                try {
                    memberDao.Update(member);
                } catch (Exception ex) {
                    Logger.getLogger(MemberProfileSave.class.getName()).log(Level.SEVERE, null, ex);
                }

            } catch (SQLException ex) {
                Logger.getLogger(MemberProfileSave.class.getName()).log(Level.SEVERE, null, ex);
            }
            return "memberProfileRedirect.jsp";
        } else {
            return "index.jsp";
        }

    }

}

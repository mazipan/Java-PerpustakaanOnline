package action;

import dao.BookDao;
import dao.MemberDao;
import entity.Administrator;
import entity.Book;
import entity.Category;
import entity.Member;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import service.ConnectionMySQL;
import util.AES;

/**
 *
 * @author mazipan
 */
public class AdminUserManSave implements ActionInterface {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        Administrator administrator = (Administrator) session.getAttribute("loginasadmin");
        String msg;
        Member member = new Member();
        
        if (administrator != null) {
            try {
                MemberDao memberDao = ConnectionMySQL.getMemberDao();
                try {
                    String id = request.getParameter("id");

                    String memberNik = request.getParameter("memberNik");
                    String memberName = request.getParameter("memberName");
                    String memberAlamat = request.getParameter("memberAlamat");
                    String memberTelp = request.getParameter("memberTelp");
                    String memberExt = request.getParameter("memberExt");
                    String memberBagian = request.getParameter("memberBagian");
                    String memberLokasi = request.getParameter("memberLokasi");                    

                    member.setMemberNik(memberNik);
                    member.setMemberName(memberName.toUpperCase());
                    member.setMemberAlamat(memberAlamat);
                    member.setMemberTelp(memberTelp);
                    member.setMemberExt(memberExt);
                    member.setMemberBagian(memberBagian);
                    member.setMemberLokasi(memberLokasi);

                    if (id == null) {
                        member.setMemberPassword(AES.encrypt("123456"));
                        memberDao.Insert(member);
                    } else {
                        member.setMemberPassword(AES.encrypt(member.getMemberPassword()));
                        member.setMemberId(Integer.parseInt(id));
                        memberDao.Update(member);
                    }

                    msg = "Member has been saved";

                } catch (Exception ex) {
                    msg = "Failed to save member " + ex.getMessage();
                }

            } catch (SQLException ex) {
                msg = "Failed to save member " + ex.getMessage();
            }
        } else {
            return "index.jsp";
        }

        request.setAttribute("isMember", "member");
        request.setAttribute("msg", msg);
        return "adminUserManRedirect.jsp";

    }

}

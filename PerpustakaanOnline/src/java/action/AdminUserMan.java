package action;

import dao.AdministratorDao;
import dao.MemberDao;
import entity.Administrator;
import entity.Category;
import entity.Member;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import service.ConnectionMySQL;

/**
 *
 * @author mazipan
 */
public class AdminUserMan implements ActionInterface {

    @Override
    public String execute(HttpServletRequest request) {

        int pages = 1;
        int recordsPerPage = 10;
        int noOfRecords = 0;
        int noOfPages = 0;
        int noOfRecordsAdmin = 0;
        int noOfPagesAdmin = 0;

        List<Member> members = new ArrayList<>();
        List<Administrator> administrators = new ArrayList<>();

        HttpSession session = request.getSession(true);
        Administrator administrator = (Administrator) session.getAttribute("loginasadmin");

        if (administrator != null) {
            String is = request.getParameter("is");
            if (is != null) {
                if (is.equalsIgnoreCase("admin")) {
                    request.setAttribute("isAdmin", "admin");
                } else if (is.equalsIgnoreCase("member")) {
                    request.setAttribute("isMember", "member");
                }
            }
            try {

                MemberDao memberDao = ConnectionMySQL.getMemberDao();
                AdministratorDao administratorDao = ConnectionMySQL.getAdministratorDao();
                String pageStr = request.getParameter("page");
                if (pageStr != null) {
                    pages = Integer.parseInt(pageStr);
                }
                request.setAttribute("currentPage", pages);

                try {
                    //members = memberDao.selectAll();
                    //administrators = administratorDao.selectAll();

                    members = memberDao.selectAllWithLimit((pages - 1) * recordsPerPage, recordsPerPage);

                    noOfRecords = memberDao.getNoOfRecords();
                    noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

                    administrators = administratorDao.selectAllWithLimit((pages - 1) * recordsPerPage, recordsPerPage);

                    noOfRecordsAdmin = administratorDao.getNoOfRecords();
                    noOfPagesAdmin = (int) Math.ceil(noOfRecordsAdmin * 1.0 / recordsPerPage);

                } catch (Exception ex) {
                    Logger.getLogger(AdminCategory.class.getName()).log(Level.SEVERE, null, ex);
                }

                request.setAttribute("noOfPagesAdmin", noOfPagesAdmin);
                request.setAttribute("noOfPages", noOfPages);
                request.setAttribute("members", members);
                request.setAttribute("administrators", administrators);

                return "adminUserMan.jsp";

            } catch (SQLException ex) {
                Logger.getLogger(AdminCategory.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            return "index.jsp";
        }

        return "adminUserMan.jsp";
    }

}

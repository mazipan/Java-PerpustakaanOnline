package action;

import dao.PeminjamanDao;
import entity.Administrator;
import entity.Member;
import entity.Peminjaman;
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
public class MemberTransaction implements ActionInterface {

    @Override
    public String execute(HttpServletRequest request) {

        int pages = 1;
        int recordsPerPage = 10;
        int noOfRecords = 0;
        int noOfPages = 0;

        List<Peminjaman> peminjamans = new ArrayList<>();

        HttpSession session = request.getSession(true);
        Member member = (Member) session.getAttribute("loginasmember");

        if (member != null) {
            try {

                PeminjamanDao peminjamanDao = ConnectionMySQL.getPeminjamanDao();

                try {
                    String pageStr = request.getParameter("page");
                    if (pageStr != null) {
                        pages = Integer.parseInt(pageStr);
                    }

                    request.setAttribute("currentPage", pages);

                    //peminjamans = peminjamanDao.getPeminjamanByIdMember(member.getMemberId());
                    peminjamans = peminjamanDao.getPeminjamanByIdMemberWithLimit(member.getMemberId(), (pages - 1) * recordsPerPage, recordsPerPage);

                    noOfRecords = peminjamanDao.getNoOfRecordsByIdMember(member.getMemberId());
                    noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

                    request.setAttribute("noOfPages", noOfPages);

                } catch (Exception ex) {
                    Logger.getLogger(AdminCategory.class.getName()).log(Level.SEVERE, null, ex);
                }

                request.setAttribute("peminjamans", peminjamans);

                return "memberTransaction.jsp";

            } catch (SQLException ex) {
                Logger.getLogger(AdminCategory.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            return "index.jsp";
        }

        return "memberTransaction.jsp";
    }

}

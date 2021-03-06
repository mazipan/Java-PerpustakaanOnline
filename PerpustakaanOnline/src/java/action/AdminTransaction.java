package action;

import dao.PeminjamanDao;
import entity.Administrator;
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
public class AdminTransaction implements ActionInterface {

    @Override
    public String execute(HttpServletRequest request) {
        int page = 1;
        int recordsPerPage = 10;

        List<Peminjaman> peminjamans = new ArrayList<>();

        HttpSession session = request.getSession(true);
        Administrator administrator = (Administrator) session.getAttribute("loginasadmin");

        if (administrator != null) {
            try {

                PeminjamanDao peminjamanDao = ConnectionMySQL.getPeminjamanDao();

                try {
                    String pageStr = request.getParameter("page");
                    if (pageStr != null) {
                        page = Integer.parseInt(pageStr);
                    }
                    //peminjamans = peminjamanDao.selectAll();
                    peminjamans = peminjamanDao.selectAllWithLimit((page - 1) * recordsPerPage, recordsPerPage);
                    System.out.println("peminjamans " + peminjamans.size());

                    int noOfRecords = peminjamanDao.getNoOfRecords();
                    int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

                    request.setAttribute("peminjamans", peminjamans);
                    request.setAttribute("noOfPages", noOfPages);
                    request.setAttribute("currentPage", page);

                } catch (Exception ex) {
                    Logger.getLogger(AdminCategory.class.getName()).log(Level.SEVERE, null, ex);
                }

                return "adminTransaction.jsp";

            } catch (SQLException ex) {
                Logger.getLogger(AdminCategory.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            return "index.jsp";
        }

        return "adminTransaction.jsp";
    }

}

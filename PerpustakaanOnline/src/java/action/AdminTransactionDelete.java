package action;

import dao.PeminjamanDao;
import entity.Administrator;
import entity.Peminjaman;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import service.ConnectionMySQL;

/**
 *
 * @author mazipan
 */
public class AdminTransactionDelete implements ActionInterface {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        Administrator administrator = (Administrator) session.getAttribute("loginasadmin");
        
        String msg = "";
        if (administrator != null) {
            try {
                PeminjamanDao peminjamanDao = ConnectionMySQL.getPeminjamanDao();
                try {

                    String idStr = request.getParameter("id");
                    if (idStr != null) {
                        Integer id = Integer.parseInt(idStr); 
                        peminjamanDao.Delete(id);

                        msg = "Peminjaman has been delete";
                    }

                } catch (Exception ex) {
                    msg = "Failed to delete peminjaman " + ex.getMessage();
                }

            } catch (SQLException ex) {
                msg = "Failed to delete peminjaman " + ex.getMessage();
            }
        } else {
            return "index.jsp";
        }

        request.setAttribute("msg", msg);

        return "adminTransactionRedirect.jsp";

    }

}

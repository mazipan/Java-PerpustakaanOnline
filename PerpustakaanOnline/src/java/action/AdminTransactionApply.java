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
public class AdminTransactionApply implements ActionInterface {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        Administrator administrator = (Administrator) session.getAttribute("loginasadmin");
        Peminjaman peminjaman;
        String msg = "";
        if (administrator != null) {
            try {
                PeminjamanDao peminjamanDao = ConnectionMySQL.getPeminjamanDao();
                try {

                    String idStr = request.getParameter("id");
                    if (idStr != null) {
                        Integer id = Integer.parseInt(idStr);
                        peminjaman = peminjamanDao.getPeminjamanById(id);
                        Date dateKembali = peminjaman.getTglKembali();

                        Calendar now = Calendar.getInstance();
                        String dateStr = (now.get(Calendar.YEAR) + "-" + (now.get(Calendar.MONTH) + 1)) + "-" + now.get(Calendar.DATE);

                        Date date = null;
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                        try {
                            date = sdf.parse(dateStr);

                            if (date.after(dateKembali)) {
                                peminjaman.setIsDenda(true);
                            } else {
                                peminjaman.setIsDenda(false);
                            }
                        } catch (ParseException e) {
                            msg = "Failed to update peminjaman " + e.getMessage();
                        }

                        if (peminjaman.isIsKembali()) {
                            peminjaman.setIsKembali(false);
                            peminjaman.setTglPengembalian(null);
                        } else {
                            peminjaman.setIsKembali(true);
                            peminjaman.setTglPengembalian(date);

                        }

                        peminjamanDao.Update(peminjaman);

                        msg = "Peminjaman has been update";
                    }

                } catch (Exception ex) {
                    msg = "Failed to update peminjaman " + ex.getMessage();
                }

            } catch (SQLException ex) {
                msg = "Failed to update peminjaman " + ex.getMessage();
            }
        } else {
            return "index.jsp";
        }

        request.setAttribute("msg", msg);

        return "adminTransactionRedirect.jsp";

    }

}

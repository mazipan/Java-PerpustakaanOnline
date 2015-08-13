package action;

import dao.CategoryDao;
import dao.NewsDao;
import entity.Administrator;
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
public class AdminNewsUpdateDelete implements ActionInterface {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        Administrator administrator = (Administrator) session.getAttribute("loginasadmin");
        String msg = "Failed to delete news update";

        if (administrator != null) {
            try {
                NewsDao newsDao = ConnectionMySQL.getNewsDao();

                try {

                    String idStr = request.getParameter("id");
                    if (idStr != null) {
                        Integer id = Integer.parseInt(idStr);

                        newsDao.Delete(id);
                        msg = "News update has been delete";
                    }

                } catch (Exception ex) {
                    msg = "Failed to delete news update " + ex.getMessage();
                    Logger.getLogger(AdminCategory.class.getName()).log(Level.SEVERE, null, ex);
                }

            } catch (SQLException ex) {
                msg = "Failed to delete news update " + ex.getMessage();
                Logger.getLogger(AdminCategory.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            return "index.jsp";
        }

        request.setAttribute("msg", msg);

        return "adminNewsUpdateRedirect.jsp";

    }

}

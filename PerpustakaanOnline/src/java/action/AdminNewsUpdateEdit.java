package action;

import dao.CategoryDao;
import dao.NewsDao;
import entity.Administrator;
import entity.Category;
import entity.News;
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
public class AdminNewsUpdateEdit implements ActionInterface {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        Administrator administrator = (Administrator) session.getAttribute("loginasadmin");
        News news = null;

        if (administrator != null) {
            try {
                NewsDao newsDAO = ConnectionMySQL.getNewsDao();
                try {

                    String idStr = request.getParameter("id");
                    if (idStr != null) {
                        Integer id = Integer.parseInt(idStr);

                        news = newsDAO.selectById(id);
                    }

                } catch (Exception ex) {
                    Logger.getLogger(AdminCategory.class.getName()).log(Level.SEVERE, null, ex);
                }

            } catch (SQLException ex) {
                Logger.getLogger(AdminCategory.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            return "index.jsp";
        }

        request.setAttribute("newsforedit", news);
        return "adminNewsUpdateEdit.jsp";

    }

}

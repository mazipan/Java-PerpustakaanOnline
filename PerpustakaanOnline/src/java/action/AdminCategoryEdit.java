package action;

import dao.CategoryDao;
import entity.Administrator;
import entity.Category;
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
public class AdminCategoryEdit implements ActionInterface {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        Administrator administrator = (Administrator) session.getAttribute("loginasadmin");
        Category category = null;

        if (administrator != null) {
            try {
                CategoryDao categoryDAO = ConnectionMySQL.getCategoryDao();
                try {

                    String idStr = request.getParameter("id");
                    if (idStr != null) {
                        Integer id = Integer.parseInt(idStr);
                        System.out.println("idcategory edit : " + id);

                        category = categoryDAO.selectCategotyById(id);
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

        request.setAttribute("categoryforedit", category);
        return "adminCategoryEdit.jsp";

    }

}

package action;

import dao.BookDao;
import dao.CategoryDao;
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
public class AdminCategoryDelete implements ActionInterface {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        Administrator administrator = (Administrator) session.getAttribute("loginasadmin");
        String msg = "Failed to delete category";

        if (administrator != null) {
            try {
                CategoryDao categoryDao = ConnectionMySQL.getCategoryDao();

                try {

                    String idStr = request.getParameter("id");
                    if (idStr != null) {
                        Integer id = Integer.parseInt(idStr);
                        System.out.println("idcategory for delete : " + id);

                        BookDao bookDao = ConnectionMySQL.getBookDao();

                        boolean exist = bookDao.isExistByCategory(id);
                        if (!exist) {
                            categoryDao.Delete(id);
                            msg = "Category has been delete";
                        } else {
                            msg = "Sorry, Category already use by book. </br> Please delete all book </br> that use this category before";
                        }

                    }

                } catch (Exception ex) {
                    msg = "Failed to delete category " + ex.getMessage();
                    Logger.getLogger(AdminCategory.class.getName()).log(Level.SEVERE, null, ex);
                }

            } catch (SQLException ex) {
                msg = "Failed to delete category " + ex.getMessage();
                Logger.getLogger(AdminCategory.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            return "index.jsp";
        }

        request.setAttribute("msg", msg);

        return "adminCategoryRedirect.jsp";

    }

}

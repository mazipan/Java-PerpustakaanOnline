package action;

import dao.CategoryDao;
import entity.Administrator;
import entity.Category;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import service.ConnectionMySQL;

/**
 *
 * @author mazipan
 */
public class AdminCategorySave implements ActionInterface {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        Administrator administrator = (Administrator) session.getAttribute("loginasadmin");
        String msg;

        if (administrator != null) {
            try {
                CategoryDao categoryDao = ConnectionMySQL.getCategoryDao();
                try {
                    String id = request.getParameter("id");
                    String categoryName = request.getParameter("categoryName");

                    Category category = new Category();
                    category.setCategoryName(categoryName);

                    if (id == null) {
                        System.out.println("insert new book");
                        categoryDao.Insert(category);
                    } else {
                        System.out.println("update new book");
                        category.setIdCategory(Integer.parseInt(id));
                        categoryDao.Update(category);
                    }

                    msg = "Category has been saved";

                } catch (Exception ex) {
                    msg = "Failed to save category " + ex.getMessage();
                }

            } catch (SQLException ex) {
                msg = "Failed to save category " + ex.getMessage();
            }
        } else {
            return "index.jsp";
        }

        request.setAttribute("msg", msg);

        return "adminCategoryRedirect.jsp";

    }

}

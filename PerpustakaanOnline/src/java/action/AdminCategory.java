package action;

import dao.CategoryDao;
import entity.Administrator;
import entity.Category;
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
public class AdminCategory implements ActionInterface {

    @Override
    public String execute(HttpServletRequest request) {
        int page = 1;
        int recordsPerPage = 10;
        List<Category> categoryList = new ArrayList<>();

        HttpSession session = request.getSession(true);
        Administrator administrator = (Administrator) session.getAttribute("loginasadmin");

        if (administrator != null) {
            try {
                CategoryDao categoryDAO = ConnectionMySQL.getCategoryDao();

                try {
                    String pageStr = request.getParameter("page");
                    if (pageStr != null) {
                        page = Integer.parseInt(pageStr);
                    }

                    //categoryList = categoryDAO.selectAll();
                    categoryList = categoryDAO.selectAllWithLimit((page - 1) * recordsPerPage, recordsPerPage);
                    System.out.println("categoryList " + categoryList.size());

                    int noOfRecords = categoryDAO.getNoOfRecords();
                    int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

                    request.setAttribute("categories", categoryList);
                    request.setAttribute("noOfPages", noOfPages);
                    request.setAttribute("currentPage", page);

                } catch (Exception ex) {
                    Logger.getLogger(AdminCategory.class.getName()).log(Level.SEVERE, null, ex);
                }

                return "adminCategory.jsp";

            } catch (SQLException ex) {
                Logger.getLogger(AdminCategory.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            return "index.jsp";
        }

        return "adminCategory.jsp";
    }

}

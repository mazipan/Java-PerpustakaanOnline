package action;

import dao.BookDao;
import dao.CategoryDao;
import entity.Administrator;
import entity.Book;
import entity.Category;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
public class AdminBookEdit implements ActionInterface {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        Administrator administrator = (Administrator) session.getAttribute("loginasadmin");
        Book book = null;
        List<Category> categoryList = new ArrayList<>();

        if (administrator != null) {
            try {
                BookDao bookDAO = ConnectionMySQL.getBookDao();
                CategoryDao categoryDAO = ConnectionMySQL.getCategoryDao();

                try {

                    String idStr = request.getParameter("id");
                    if (idStr != null) {
                        Integer idBook = Integer.parseInt(idStr);
                        System.out.println("idbook edit : " + idBook);

                        book = bookDAO.getBookById(idBook);
                    }

                    categoryList = categoryDAO.selectAll();
                    System.out.println("categoryList " + categoryList.size());

                    Date date = new Date();
                    SimpleDateFormat frm = new SimpleDateFormat("YYYY");
                    String DateStr = frm.format(date);
                    Integer DateInt = Integer.parseInt(DateStr) + 1;
                    List<String> listYear = new ArrayList();
                    for (int i = 0; i < 20; i++) {
                        DateInt -= 1;
                        listYear.add(Integer.toString(DateInt));
                    }
                    System.out.println("listYear " + listYear.size());
                    request.setAttribute("yearforedit", listYear);

                } catch (Exception ex) {
                    Logger.getLogger(AdminCategory.class.getName()).log(Level.SEVERE, null, ex);
                }

            } catch (SQLException ex) {
                Logger.getLogger(AdminCategory.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            return "index.jsp";
        }

        request.setAttribute("bookforedit", book);
        request.setAttribute("categoryforedit", categoryList);

        return "adminBookEdit.jsp";

    }

}

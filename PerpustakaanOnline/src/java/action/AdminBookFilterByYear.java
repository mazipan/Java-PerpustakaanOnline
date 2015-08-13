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
public class AdminBookFilterByYear implements ActionInterface {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        Administrator administrator = (Administrator) session.getAttribute("loginasadmin");

        int pages = 1;
        int recordsPerPage = 10;
        int noOfRecords = 0;
        int noOfPages = 0;

        List<Category> categoryList = new ArrayList<>();
        List<Book> books = new ArrayList<>();

        if (administrator != null) {
            try {
                BookDao bookDAO = ConnectionMySQL.getBookDao();
                CategoryDao categoryDAO = ConnectionMySQL.getCategoryDao();
                try {

                    String pageStr = request.getParameter("page");
                    if (pageStr != null) {
                        pages = Integer.parseInt(pageStr);
                    }

                    request.setAttribute("currentPage", pages);

                    categoryList = categoryDAO.selectAll();

                    Category categoryAll = new Category();
                    categoryAll.setCategoryName("All");
                    categoryAll.setIdCategory(0);

                    categoryList.add(0, categoryAll);

                    Date date = new Date();
                    SimpleDateFormat frm = new SimpleDateFormat("YYYY");
                    String DateStr = frm.format(date);
                    Integer DateInt = Integer.parseInt(DateStr) + 1;
                    List<String> listYear = new ArrayList();
                    listYear.add("All");
                    for (int i = 0; i < 20; i++) {
                        DateInt -= 1;
                        listYear.add(Integer.toString(DateInt));
                    }

                    request.setAttribute("listyear", listYear);
                    request.setAttribute("categories", categoryList);

                    String idYear = request.getParameter("id");
                    if (idYear != null) {

                        request.setAttribute("yearSelected", idYear);

                        if (idYear.equalsIgnoreCase("All")) {
                            //books = bookDAO.selectAll();                            
                            books = bookDAO.selectAllWithLimit((pages - 1) * recordsPerPage, recordsPerPage);

                            noOfRecords = bookDAO.getNoOfRecords();
                            noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
                        } else {
                            books = bookDAO.selectByYearWithLimit(idYear, (pages - 1) * recordsPerPage, recordsPerPage);
                            noOfRecords = bookDAO.getNoOfRecordsByYear(idYear);
                            noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
                        }

                        request.setAttribute("noOfPages", noOfPages);
                        request.setAttribute("booksadmin", books);
                    }

                } catch (Exception ex) {
                    Logger.getLogger(AdminCategory.class.getName()).log(Level.SEVERE, null, ex);
                }

                return "adminBookYear.jsp";

            } catch (SQLException ex) {
                Logger.getLogger(AdminCategory.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            return "index.jsp";
        }

        return "adminBookYear.jsp";

    }

}

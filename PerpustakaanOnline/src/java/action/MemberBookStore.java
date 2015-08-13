package action;

import dao.BookDao;
import dao.CategoryDao;
import entity.Book;
import entity.Category;
import entity.Member;
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
 * Kelas HomeAdmin Mengimplentasi interface ActionInterface
 *
 * @author joker
 */
public class MemberBookStore implements ActionInterface {

    //Override methode
    @Override
    public String execute(HttpServletRequest request) {

        int pages = 1;
        int recordsPerPage = 10;
        int noOfRecords = 0;
        int noOfPages = 0;

        List<Category> categoryList = new ArrayList<>();
        List<Book> books = new ArrayList<>();

        HttpSession session = request.getSession(true);
        Member member = (Member) session.getAttribute("loginasmember");

        if (member != null) {
            try {
                BookDao bookDAO = ConnectionMySQL.getBookDao();
                CategoryDao categoryDAO = ConnectionMySQL.getCategoryDao();

                try {

                    String pageStr = request.getParameter("page");
                    if (pageStr != null) {
                        pages = Integer.parseInt(pageStr);
                    }

                    request.setAttribute("currentPage", pages);

                    //books = bookDAO.selectWhereNotDipinjam();
                    books = bookDAO.selectWhereNotDipinjamWithLimit((pages - 1) * recordsPerPage, recordsPerPage);

                    noOfRecords = bookDAO.getNoOfRecordsWhereNotDipinjam();
                    noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

                    request.setAttribute("noOfPages", noOfPages);

                    categoryList = categoryDAO.selectAll();

                    Category categoryAll = new Category();
                    categoryAll.setCategoryName("All");
                    categoryAll.setIdCategory(0);

                    categoryList.add(0, categoryAll);
                    System.out.println("categoryList " + categoryList.size());

                } catch (Exception ex) {
                    Logger.getLogger(AdminCategory.class.getName()).log(Level.SEVERE, null, ex);
                }

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

                request.setAttribute("books", books);
                request.setAttribute("listyear", listYear);
                request.setAttribute("categories", categoryList);

                return "memberBookStore.jsp";

            } catch (SQLException ex) {
                Logger.getLogger(AdminCategory.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            return "index.jsp";
        }

        return "memberBookStore.jsp";
    }

}

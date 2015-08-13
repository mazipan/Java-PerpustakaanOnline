package action;

import dao.BookDao;
import entity.Administrator;
import entity.Book;
import entity.Category;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import service.ConnectionMySQL;

/**
 *
 * @author mazipan
 */
public class AdminBookSave implements ActionInterface {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        Administrator administrator = (Administrator) session.getAttribute("loginasadmin");
        String msg;

        if (administrator != null) {
            try {
                BookDao bookDAO = ConnectionMySQL.getBookDao();
                try {
                    String id = request.getParameter("id");

                    String bookCategory = request.getParameter("bookCategory");
                    String bookTittle = request.getParameter("bookTitle");
                    String bookPengarang = request.getParameter("bookPengarang");
                    String bookYear = request.getParameter("bookYear");
                    bookYear = bookYear + "-01-01";

                    Book book = new Book();

                    Category category = new Category();
                    category.setIdCategory(Integer.parseInt(bookCategory));

                    book.setCategory(category);
                    book.setBookTitle(bookTittle);
                    book.setPengarang(bookPengarang);

                    Date date;
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                    try {
                        date = sdf.parse(bookYear);
                        book.setTerbitDate(date);
                    } catch (ParseException e) {
                    }

                    if (id == null) {
                        System.out.println("insert new book");
                        bookDAO.Insert(book);
                    } else {
                        System.out.println("update new book");
                        book.setIdBook(Integer.parseInt(id));
                        bookDAO.Update(book);
                    }

                    msg = "Book has been saved";

                } catch (Exception ex) {
                    msg = "Failed to save book " + ex.getMessage();
                }

            } catch (SQLException ex) {
                msg = "Failed to save book " + ex.getMessage();
            }
        } else {
            return "index.jsp";
        }

        request.setAttribute("msg", msg);
        return "adminBookRedirect.jsp";

    }

}

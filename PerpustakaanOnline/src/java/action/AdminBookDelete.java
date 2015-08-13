package action;

import dao.BookDao;
import dao.PeminjamanDao;
import entity.Administrator;
import entity.Book;
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
public class AdminBookDelete implements ActionInterface {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        Administrator administrator = (Administrator) session.getAttribute("loginasadmin");
        String msg = "Failed to delete book";

        if (administrator != null) {
            try {
                BookDao bookDAO = ConnectionMySQL.getBookDao();
                PeminjamanDao peminjamanDao = ConnectionMySQL.getPeminjamanDao();

                try {

                    String idStr = request.getParameter("id");
                    if (idStr != null) {
                        Integer idBook = Integer.parseInt(idStr);
                        System.out.println("idbook delete : " + idBook);
                        Book book = bookDAO.getBookById(idBook);

                        boolean exist = peminjamanDao.isExistByBook(idBook);
                        if (!exist) {
                            if (book.getIsDipinjam().equalsIgnoreCase("Available")) {
                                bookDAO.Delete(idBook);
                                msg = "book has been delete";
                            } else {
                                msg = "book already borrowed, you can't delete this";
                            }
                        } else {
                            msg = "Sorry, Book already use by transaction. </br> Please delete all transaction </br> that use this book before";
                        }

                    }

                } catch (Exception ex) {
                    msg = "Failed to delete book " + ex.getMessage();
                    Logger.getLogger(AdminCategory.class.getName()).log(Level.SEVERE, null, ex);
                }

            } catch (SQLException ex) {
                msg = "Failed to delete book " + ex.getMessage();
                Logger.getLogger(AdminCategory.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            return "index.jsp";
        }

        request.setAttribute("msg", msg);
        return "adminBookRedirect.jsp";

    }

}

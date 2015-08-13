package action;

import dao.BookDao;
import dao.NewsDao;
import entity.Book;
import entity.Member;
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
public class MemberNewsUpdate implements ActionInterface {

    @Override
    public String execute(HttpServletRequest request) {

        HttpSession session = request.getSession(true);
        Member member = (Member) session.getAttribute("loginasmember");

        if (member != null) {
            try {
                BookDao bookDao = ConnectionMySQL.getBookDao();
                NewsDao newsDao = ConnectionMySQL.getNewsDao();
                try {
                    Book book = bookDao.selectLastRecord();
                    request.setAttribute("bookupdate", book);

                    News news = newsDao.selectLastRecord();
                    request.setAttribute("newsupdate", news);
                } catch (Exception ex) {
                    Logger.getLogger(MemberNewsUpdate.class.getName()).log(Level.SEVERE, null, ex);
                }

            } catch (SQLException ex) {
                Logger.getLogger(MemberNewsUpdate.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            return "index.jsp";
        }

        return "memberNewsUpdate.jsp";
    }

}

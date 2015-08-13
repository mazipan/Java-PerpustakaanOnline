package action;

import dao.BookDao;
import dao.PeminjamanDao;
import entity.Book;
import entity.Member;
import entity.Peminjaman;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
public class MemberBookStorePinjam implements ActionInterface {

    //Override methode
    @Override
    public String execute(HttpServletRequest request) {

        List<Book> books = new ArrayList<>();

        HttpSession session = request.getSession(true);
        Member member = (Member) session.getAttribute("loginasmember");
        String msg = null;

        if (member != null) {
            try {
                BookDao bookDAO = ConnectionMySQL.getBookDao();
                PeminjamanDao peminjamanDAO = ConnectionMySQL.getPeminjamanDao();
                try {
                    Integer count = peminjamanDAO.getCountPinjamByIdMember(member.getMemberId());
                    System.out.println("member meminjam buku = "+count);
                    if (count < 5) {
                        String idStr = request.getParameter("id");
                        if (idStr != null) {
                            Integer idBook = Integer.parseInt(idStr);

                            try {
                                Book book = bookDAO.getBookById(idBook);

                                Peminjaman peminjaman = new Peminjaman();
                                peminjaman.setMember(member);
                                peminjaman.setBook(book);
                                Calendar now = Calendar.getInstance();
                                String dateStr = (now.get(Calendar.YEAR) + "-" + (now.get(Calendar.MONTH) + 1)) + "-" + now.get(Calendar.DATE);
                                System.out.println("before " + dateStr);

                                Date date;
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                                try {
                                    date = sdf.parse(dateStr);
                                    peminjaman.setTglPinjam(date);
                                } catch (ParseException e) {
                                }

                                now.add(Calendar.WEEK_OF_YEAR, 2);
                                dateStr = (now.get(Calendar.YEAR) + "-" + (now.get(Calendar.MONTH) + 1)) + "-" + now.get(Calendar.DATE);
                                System.out.println("after " + dateStr);
                                try {
                                    date = sdf.parse(dateStr);
                                    peminjaman.setTglKembali(date);
                                } catch (ParseException e) {
                                }

                                peminjaman.setIsKembali(false);
                                peminjaman.setIsDenda(false);

                                peminjamanDAO.Insert(peminjaman);

                                msg = "Peminjaman has been saved";

                            } catch (Exception ex) {
                                msg = "Can not found book " + ex.getMessage();
                            }
                        }
                    } else {
                        msg = "Sorry, you have maximal 5 books borrowed, </br> Please return before";
                    }

                } catch (Exception ex) {
                    Logger.getLogger(MemberBookStorePinjam.class.getName()).log(Level.SEVERE, null, ex);
                }

            } catch (SQLException ex) {
                msg = "Can not save peminjaman " + ex.getMessage();
            }
        } else {
            return "index.jsp";
        }

        request.setAttribute("msg", msg);

        return "memberBookStoreRedirect.jsp";
    }

}

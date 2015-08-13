package action;

import dao.NewsDao;
import entity.Administrator;
import entity.News;
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
public class AdminNewsUpdate implements ActionInterface {

    @Override
    public String execute(HttpServletRequest request) {
        int page = 1;
        int recordsPerPage = 10;
        List<News> newsList = new ArrayList<>();

        HttpSession session = request.getSession(true);
        Administrator administrator = (Administrator) session.getAttribute("loginasadmin");

        if (administrator != null) {
            try {
                NewsDao newsDAO = ConnectionMySQL.getNewsDao();

                try {
                    String pageStr = request.getParameter("page");
                    if (pageStr != null) {
                        page = Integer.parseInt(pageStr);
                    }

                    //categoryList = categoryDAO.selectAll();
                    newsList = newsDAO.selectAllWithLimit((page - 1) * recordsPerPage, recordsPerPage);

                    int noOfRecords = newsDAO.getNoOfRecord();
                    int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

                    request.setAttribute("newsList", newsList);
                    request.setAttribute("noOfPages", noOfPages);
                    request.setAttribute("currentPage", page);

                } catch (Exception ex) {
                    Logger.getLogger(AdminCategory.class.getName()).log(Level.SEVERE, null, ex);
                }

                return "adminNewsUpdate.jsp";

            } catch (SQLException ex) {
                Logger.getLogger(AdminCategory.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            return "index.jsp";
        }

        return "adminNewsUpdate.jsp";
    }

}

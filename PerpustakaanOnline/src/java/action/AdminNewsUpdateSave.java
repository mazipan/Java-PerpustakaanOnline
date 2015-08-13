package action;

import dao.CategoryDao;
import dao.NewsDao;
import entity.Administrator;
import entity.Category;
import entity.News;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.joda.time.DateTime;
import service.ConnectionMySQL;
import util.DateChanger;

/**
 *
 * @author mazipan
 */
public class AdminNewsUpdateSave implements ActionInterface {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        Administrator administrator = (Administrator) session.getAttribute("loginasadmin");
        String msg;

        if (administrator != null) {
            try {
                NewsDao newsDao = ConnectionMySQL.getNewsDao();
                try {
                    String id = request.getParameter("id");
                    String newsName = request.getParameter("newsName");
                    String newsPlace = request.getParameter("newsPlace");
                    String newsDateTime = request.getParameter("newsDateTime");
                    String newsDesc = request.getParameter("newsDesc");

                    System.out.println("id : " + id + " name : " + newsName);
                    System.out.println("newsPlace : " + newsPlace + " newsDesc : " + newsDesc);
                    System.out.println("newsDateTime : " + newsDateTime);

                    News news = new News();
                    news.setNewsName(newsName);
                    news.setNewsPlace(newsPlace);

                    Date date;
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    if (newsDateTime != null) {
                        try {
                            date = sdf.parse(DateChanger.changeDateToMin(newsDateTime));
                            DateTime dateTime = new DateTime(date);
                            news.setNewsDateTime(dateTime);
                        } catch (ParseException e) {
                        }
                    }

                    news.setNewsDesc(newsDesc);

                    if (id == null) {
                        System.out.println("insert new news update");
                        newsDao.Insert(news);
                    } else {
                        System.out.println("update new news update");
                        news.setIdNews(Integer.parseInt(id));
                        newsDao.Update(news);
                    }

                    msg = "News update has been saved";

                } catch (Exception ex) {
                    msg = "Failed to save News update " + ex.getMessage();
                }

            } catch (SQLException ex) {
                msg = "Failed to save News update " + ex.getMessage();
            }
        } else {
            return "index.jsp";
        }

        request.setAttribute("msg", msg);

        return "adminNewsUpdateRedirect.jsp";

    }

}

package action;

import dao.AdministratorDao;
import dao.BookDao;
import dao.MemberDao;
import entity.Administrator;
import entity.Book;
import entity.Category;
import entity.Member;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import service.ConnectionMySQL;
import util.AES;

/**
 *
 * @author mazipan
 */
public class AdminUserManAdminSave implements ActionInterface {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        Administrator administrator = (Administrator) session.getAttribute("loginasadmin");
        String msg;

        if (administrator != null) {
            try {
                AdministratorDao administratorDao = ConnectionMySQL.getAdministratorDao();
                try {

                    String id = request.getParameter("id");
                    String truename = request.getParameter("truename");
                    String username = request.getParameter("username");
                    String password = request.getParameter("password");

                    boolean isExist = administratorDao.isExist(username);
                    if (isExist) {
                        msg = "Administrator with username " + username + " is exist";
                    } else {
                        Administrator newAdmin = new Administrator();
                        newAdmin.setTruename(truename);
                        newAdmin.setUsername(username.toLowerCase());
                        if (password != null) {
                            newAdmin.setPassword(AES.encrypt(password.toLowerCase()));
                        } else {
                            newAdmin.setPassword(AES.encrypt(administrator.getPassword().toLowerCase()));
                        }

                        if (id == null) {
                            administratorDao.Insert(newAdmin);
                        } else {
                            newAdmin.setIdAdministrator(Integer.parseInt(id));
                            administratorDao.Update(newAdmin);
                        }
                         msg = "Administrator has been saved";
                    }

                   

                } catch (Exception ex) {
                    msg = "Failed to save administrator ";
                }

            } catch (SQLException ex) {
                msg = "Failed to save administrator ";
            }
        } else {
            return "index.jsp";
        }

        request.setAttribute("isAdmin", "admin");
        request.setAttribute("msg", msg);
        return "adminUserManRedirect.jsp";

    }

}

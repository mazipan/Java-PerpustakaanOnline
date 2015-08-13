package action;

import dao.AdministratorDao;
import entity.Administrator;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import service.ConnectionMySQL;
import util.AES;

/**
 *
 * @author mazipan
 */
public class LoginAdmin implements ActionInterface {

    @Override
    public String execute(HttpServletRequest request) {
        //deklarasi dan inisialisasi variabel userName

        System.out.println("calling LoginAdmin.java");

        String fileJsp = "index.jsp";
        String pesan = "";

        if (request != null) {
            String userName = request.getParameter("username");
            String password = request.getParameter("password");

            if (userName != null && password != null) {
                if (userName.trim().equals("") && password.trim().equals("")) {
                    pesan = "Username and Password still empty";
                } else if (userName.trim().equals("")) {
                    pesan = "Username still empty";
                } else if (password.trim().equals("")) {
                    pesan = "Password still empty";
                } else {
                    try {
                        AdministratorDao administratorDao = ConnectionMySQL.getAdministratorDao();
                        Administrator administrator = administratorDao.getByNameAndPass(userName.toLowerCase(), AES.encrypt(password.toLowerCase()));
                        if (administrator != null) {
                            HttpSession session = request.getSession(true);
                            session.setAttribute("loginasadmin", administrator);

                            pesan = "Success Login...";

                            fileJsp = "homeAdmin.jsp";

                        } else {
                            pesan = "Wrong Username or Password";
                        }

                    } catch (SQLException e) {
                        pesan = "Error when connecting to database, </br> Please check your MySQL connecion";

                    } catch (Exception e) {
                        pesan = "Error when connecting to database, </br> Please check your MySQL connecion";

                    }
                }
            }
        }

        //set attribut untuk menampilkasn pesan jika 
        //proses login gagal
        request.setAttribute("msg", pesan);

        System.out.println("fileJsp " + fileJsp);
        System.out.println("pesan " + pesan);
        //return fileJsp
        return fileJsp;
    }
}

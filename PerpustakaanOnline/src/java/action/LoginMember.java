package action;

import dao.MemberDao;
import entity.Member;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import service.ConnectionMySQL;
import util.AES;

/**
 *
 * @author mazipan
 */
public class LoginMember implements ActionInterface {

    @Override
    public String execute(HttpServletRequest request) {
        //deklarasi dan inisialisasi variabel userName

        String fileJsp = "index.jsp";
        String pesan = "";

        if (request != null) {

            String userName = request.getParameter("nama");
            String password = request.getParameter("nik");

            if (userName != null && password != null) {
                if (userName.trim().equals("") && password.trim().equals("")) {
                    pesan = "Username and Password still empty";
                } else if (userName.trim().equals("")) {
                    pesan = "Username still empty";
                } else if (password.trim().equals("")) {
                    pesan = "Password still empty";
                } else {
                    try {
                        MemberDao memberDao = ConnectionMySQL.getMemberDao();
                        Member member = memberDao.getMemberByNameAndPass(userName.toUpperCase(), AES.encrypt(password.toLowerCase()));

                        if (member != null) {
                            HttpSession session = request.getSession(true);
                            session.setAttribute("loginasmember", member);

                            pesan = "Success Login...";

                            fileJsp = "homeMember.jsp";
                        } else {
                            pesan = "Wrong Username or Password";
                        }

                    } catch (SQLException e) {
                        pesan = "Error when connecting to database, Please check your MySQL connecion";
                    } catch (Exception e) {
                        pesan = "Error when connecting to database, Please check your MySQL connecion";
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

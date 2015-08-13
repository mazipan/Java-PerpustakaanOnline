package action;

import entity.Administrator;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import util.ReportGenerator;

/**
 *
 * @author mazipan
 */
public class AdminGenerateReport implements ActionInterface {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        Administrator administrator = (Administrator) session.getAttribute("loginasadmin");

        if (administrator != null) {
            String adminName = administrator.getUsername();
            String type = request.getParameter("type");
            String idStr = request.getParameter("option");
            String location = null;
            /*
             1 List all books 
             2 List all book that borrowed 
             3 List all book that not borrowed
             
             11 List all administrator 
             12 List all member
            
             21 List all transaction 
             22 List all transaction that have not return 
             23 List all transaction that have return 
             24 List all transaction that get penalty 
             25 List all transaction that not get penalty 
             26 List all transaction by member
             */
            switch (idStr) {
                case "1":
                    if (type.equalsIgnoreCase("xls")) {
                        location = ReportGenerator.reportAllBook(adminName, true, false, false, false);
                    } else if (type.equalsIgnoreCase("xlsx")) {
                        location = ReportGenerator.reportAllBook(adminName, false, true, false, false);
                    } else if (type.equalsIgnoreCase("pdf")) {
                        location = ReportGenerator.reportAllBook(adminName, false, false, true, false);
                    } else if (type.equalsIgnoreCase("csv")) {
                        location = ReportGenerator.reportAllBook(adminName, false, false, false, true);
                    }
                    break;
                case "2":
                    if (type.equalsIgnoreCase("xls")) {
                        location = ReportGenerator.reportAllBookBorrowed(adminName, true, false, false, false);
                    } else if (type.equalsIgnoreCase("xlsx")) {
                        location = ReportGenerator.reportAllBookBorrowed(adminName, false, true, false, false);
                    } else if (type.equalsIgnoreCase("pdf")) {
                        location = ReportGenerator.reportAllBookBorrowed(adminName, false, false, true, false);
                    } else if (type.equalsIgnoreCase("csv")) {
                        location = ReportGenerator.reportAllBookBorrowed(adminName, false, false, false, true);
                    }
                    break;
                case "3":
                    if (type.equalsIgnoreCase("xls")) {
                        location = ReportGenerator.reportAllBookNotBorrowed(adminName, true, false, false, false);
                    } else if (type.equalsIgnoreCase("xlsx")) {
                        location = ReportGenerator.reportAllBookNotBorrowed(adminName, false, true, false, false);
                    } else if (type.equalsIgnoreCase("pdf")) {
                        location = ReportGenerator.reportAllBookNotBorrowed(adminName, false, false, true, false);
                    } else if (type.equalsIgnoreCase("csv")) {
                        location = ReportGenerator.reportAllBookNotBorrowed(adminName, false, false, false, true);
                    }
                    break;
                case "11":
                    if (type.equalsIgnoreCase("xls")) {
                        location = ReportGenerator.reportAllAdministrator(adminName, true, false, false, false);
                    } else if (type.equalsIgnoreCase("xlsx")) {
                        location = ReportGenerator.reportAllAdministrator(adminName, false, true, false, false);
                    } else if (type.equalsIgnoreCase("pdf")) {
                        location = ReportGenerator.reportAllAdministrator(adminName, false, false, true, false);
                    } else if (type.equalsIgnoreCase("csv")) {
                        location = ReportGenerator.reportAllAdministrator(adminName, false, false, false, true);
                    }
                    break;
                case "12":
                    if (type.equalsIgnoreCase("xls")) {
                        location = ReportGenerator.reportAllMember(adminName, true, false, false, false);
                    } else if (type.equalsIgnoreCase("xlsx")) {
                        location = ReportGenerator.reportAllMember(adminName, false, true, false, false);
                    } else if (type.equalsIgnoreCase("pdf")) {
                        location = ReportGenerator.reportAllMember(adminName, false, false, true, false);
                    } else if (type.equalsIgnoreCase("csv")) {
                        location = ReportGenerator.reportAllMember(adminName, false, false, false, true);
                    }
                    break;
                case "21":
                    if (type.equalsIgnoreCase("xls")) {
                        location = ReportGenerator.reportAllTransaction(adminName, true, false, false, false);
                    } else if (type.equalsIgnoreCase("xlsx")) {
                        location = ReportGenerator.reportAllTransaction(adminName, false, true, false, false);
                    } else if (type.equalsIgnoreCase("pdf")) {
                        location = ReportGenerator.reportAllTransaction(adminName, false, false, true, false);
                    } else if (type.equalsIgnoreCase("csv")) {
                        location = ReportGenerator.reportAllTransaction(adminName, false, false, false, true);
                    }
                    break;
                case "22":
                    if (type.equalsIgnoreCase("xls")) {
                        location = ReportGenerator.reportAllTransactionHaveNotReturn(adminName, true, false, false, false);
                    } else if (type.equalsIgnoreCase("xlsx")) {
                        location = ReportGenerator.reportAllTransactionHaveNotReturn(adminName, false, true, false, false);
                    } else if (type.equalsIgnoreCase("pdf")) {
                        location = ReportGenerator.reportAllTransactionHaveNotReturn(adminName, false, false, true, false);
                    } else if (type.equalsIgnoreCase("csv")) {
                        location = ReportGenerator.reportAllTransactionHaveNotReturn(adminName, false, false, false, true);
                    }
                    break;
                case "23":
                    if (type.equalsIgnoreCase("xls")) {
                        location = ReportGenerator.reportAllTransactionHaveReturn(adminName, true, false, false, false);
                    } else if (type.equalsIgnoreCase("xlsx")) {
                        location = ReportGenerator.reportAllTransactionHaveReturn(adminName, false, true, false, false);
                    } else if (type.equalsIgnoreCase("pdf")) {
                        location = ReportGenerator.reportAllTransactionHaveReturn(adminName, false, false, true, false);
                    } else if (type.equalsIgnoreCase("csv")) {
                        location = ReportGenerator.reportAllTransactionHaveReturn(adminName, false, false, false, true);
                    }
                    break;
                case "24":
                    if (type.equalsIgnoreCase("xls")) {
                        location = ReportGenerator.reportAllTransactionGetPenalty(adminName, true, false, false, false);
                    } else if (type.equalsIgnoreCase("xlsx")) {
                        location = ReportGenerator.reportAllTransactionGetPenalty(adminName, false, true, false, false);
                    } else if (type.equalsIgnoreCase("pdf")) {
                        location = ReportGenerator.reportAllTransactionGetPenalty(adminName, false, false, true, false);
                    } else if (type.equalsIgnoreCase("csv")) {
                        location = ReportGenerator.reportAllTransactionGetPenalty(adminName, false, false, false, true);
                    }
                    break;
                case "25":
                    if (type.equalsIgnoreCase("xls")) {
                        location = ReportGenerator.reportAllTransactionNotGetPenalty(adminName, true, false, false, false);
                    } else if (type.equalsIgnoreCase("xlsx")) {
                        location = ReportGenerator.reportAllTransactionNotGetPenalty(adminName, false, true, false, false);
                    } else if (type.equalsIgnoreCase("pdf")) {
                        location = ReportGenerator.reportAllTransactionNotGetPenalty(adminName, false, false, true, false);
                    } else if (type.equalsIgnoreCase("csv")) {
                        location = ReportGenerator.reportAllTransactionNotGetPenalty(adminName, false, false, false, true);
                    }
                    break;
            }

            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.open(new File(location));
            } catch (IOException ex) {
                Logger.getLogger(AdminGenerateReport.class.getName()).log(Level.SEVERE, null, ex);
            }

            request.setAttribute("msg", "Report has been generated, </br> Please check path : " + location);

            return "adminReportRedirect.jsp";

        } else {
            return "index.jsp";
        }

    }

}

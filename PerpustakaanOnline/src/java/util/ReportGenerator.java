/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import action.AdminGenerateReport;
import dao.AdministratorDao;
import dao.BookDao;
import dao.MemberDao;
import dao.PeminjamanDao;
import entity.Administrator;
import entity.Book;
import entity.Member;
import entity.Peminjaman;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import service.ConnectionMySQL;

/**
 *
 * @author jitzu
 */
public class ReportGenerator {

    public ReportGenerator() {

    }

    public static String reportAllBook(String admin, boolean isExcel03, boolean isExcel07, boolean isPdf, boolean isCSV) {
        try {
            BookDao bookDAO = ConnectionMySQL.getBookDao();
            try {
                List<Book> books = bookDAO.selectAll();
                String[] header = ObjectReformat.headerBook();
                String[][] data = ObjectReformat.booksToString(books, header);

                Calendar now = Calendar.getInstance();
                String dateStr = (now.get(Calendar.YEAR) + "-" + (now.get(Calendar.MONTH) + 1)) + "-" + now.get(Calendar.DATE) + "-" + (now.get(Calendar.HOUR_OF_DAY)) + "-" + (now.get(Calendar.MINUTE)) + "-" + (now.get(Calendar.SECOND));

                ExcelConverter.makeFolder("C:/report_perpustakaan_online");
                ExcelConverter.makeFolder("C:/report_perpustakaan_online/" + admin);

                String saveLocation = "C:/report_perpustakaan_online/" + admin + "/allbook_" + dateStr + "";

                if (isExcel03) {
                    saveLocation = "C:/report_perpustakaan_online/" + admin + "/allbook_" + dateStr + ".xls";
                    ExcelConverter.createXls(header, data, saveLocation);
                }

                if (isExcel07) {
                    saveLocation = "C:/report_perpustakaan_online/" + admin + "/allbook_" + dateStr + ".xlsx";
                    ExcelConverter.createXlsx(header, data, saveLocation);
                }

                if (isPdf) {
                    saveLocation = "C:/report_perpustakaan_online/" + admin + "/allbook_" + dateStr + ".pdf";
                    float[] columnWidths = new float[]{5f, 30f, 10f, 20f, 6f, 5f};
                    PDFconverter.createPDF(header, data, saveLocation, "Report All Book", columnWidths);
                }

                if (isCSV) {
                    saveLocation = "C:/report_perpustakaan_online/" + admin + "/allbook_" + dateStr + ".csv";
                    CSVconverter.createCSV(header, data, saveLocation);
                }

                return saveLocation;

            } catch (Exception ex) {
                Logger.getLogger(AdminGenerateReport.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AdminGenerateReport.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static String reportAllBookNotBorrowed(String admin, boolean isExcel03, boolean isExcel07, boolean isPdf, boolean isCSV) {
        try {
            BookDao bookDAO = ConnectionMySQL.getBookDao();
            try {
                List<Book> books = bookDAO.selectWhereNotDipinjam();
                String[] header = ObjectReformat.headerBook();
                String[][] data = ObjectReformat.booksToString(books, header);

                Calendar now = Calendar.getInstance();
                String dateStr = (now.get(Calendar.YEAR) + "-" + (now.get(Calendar.MONTH) + 1)) + "-" + now.get(Calendar.DATE) + "-" + (now.get(Calendar.HOUR_OF_DAY)) + "-" + (now.get(Calendar.MINUTE)) + "-" + (now.get(Calendar.SECOND));
                ExcelConverter.makeFolder("C:/report_perpustakaan_online");
                ExcelConverter.makeFolder("C:/report_perpustakaan_online/" + admin);

                String saveLocation = "C:/report_perpustakaan_online/" + admin + "/allbook_not_borrowed_" + dateStr + "";

                if (isExcel03) {
                    saveLocation = "C:/report_perpustakaan_online/" + admin + "/allbook_not_borrowed_" + dateStr + ".xls";
                    ExcelConverter.createXls(header, data, saveLocation);
                }

                if (isExcel07) {
                    saveLocation = "C:/report_perpustakaan_online/" + admin + "/allbook_not_borrowed_" + dateStr + ".xlsx";
                    ExcelConverter.createXlsx(header, data, saveLocation);
                }

                if (isPdf) {
                    saveLocation = "C:/report_perpustakaan_online/" + admin + "/allbook_not_borrowed_" + dateStr + ".pdf";
                    float[] columnWidths = new float[]{5f, 30f, 10f, 20f, 6f, 5f};
                    PDFconverter.createPDF(header, data, saveLocation, "Report All Book That Not Borrowed", columnWidths);
                }

                if (isCSV) {
                    saveLocation = "C:/report_perpustakaan_online/" + admin + "/allbook_not_borrowed_" + dateStr + ".csv";
                    CSVconverter.createCSV(header, data, saveLocation);
                }

                return saveLocation;

            } catch (Exception ex) {
                Logger.getLogger(AdminGenerateReport.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AdminGenerateReport.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static String reportAllBookBorrowed(String admin, boolean isExcel03, boolean isExcel07, boolean isPdf, boolean isCSV) {
        try {
            BookDao bookDAO = ConnectionMySQL.getBookDao();
            try {
                List<Book> books = bookDAO.selectWhereDipinjam();
                String[] header = ObjectReformat.headerBook();
                String[][] data = ObjectReformat.booksToString(books, header);

                Calendar now = Calendar.getInstance();
                String dateStr = (now.get(Calendar.YEAR) + "-" + (now.get(Calendar.MONTH) + 1)) + "-" + now.get(Calendar.DATE) + "-" + (now.get(Calendar.HOUR_OF_DAY)) + "-" + (now.get(Calendar.MINUTE)) + "-" + (now.get(Calendar.SECOND));
                ExcelConverter.makeFolder("C:/report_perpustakaan_online");
                ExcelConverter.makeFolder("C:/report_perpustakaan_online/" + admin);

                String saveLocation = "C:/report_perpustakaan_online/" + admin + "/allbook_borrowed_" + dateStr + "";

                if (isExcel03) {
                    saveLocation = "C:/report_perpustakaan_online/" + admin + "/allbook_borrowed_" + dateStr + ".xls";
                    ExcelConverter.createXls(header, data, saveLocation);
                }

                if (isExcel07) {
                    saveLocation = "C:/report_perpustakaan_online/" + admin + "/allbook_borrowed_" + dateStr + ".xlsx";
                    ExcelConverter.createXlsx(header, data, saveLocation);
                }

                if (isPdf) {
                    saveLocation = "C:/report_perpustakaan_online/" + admin + "/allbook_borrowed_" + dateStr + ".pdf";
                    float[] columnWidths = new float[]{5f, 30f, 10f, 20f, 6f, 5f};
                    PDFconverter.createPDF(header, data, saveLocation, "Report All Book That Borrowed", columnWidths);
                }

                if (isCSV) {
                    saveLocation = "C:/report_perpustakaan_online/" + admin + "/allbook_borrowed_" + dateStr + ".csv";
                    CSVconverter.createCSV(header, data, saveLocation);
                }

                return saveLocation;

            } catch (Exception ex) {
                Logger.getLogger(AdminGenerateReport.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AdminGenerateReport.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static String reportAllMember(String admin, boolean isExcel03, boolean isExcel07, boolean isPdf, boolean isCSV) {
        try {
            MemberDao memberDAO = ConnectionMySQL.getMemberDao();
            try {
                List<Member> members = memberDAO.selectAll();
                String[] header = ObjectReformat.headerUserMember();
                String[][] data = ObjectReformat.userMemberToString(members, header);

                Calendar now = Calendar.getInstance();
                String dateStr = (now.get(Calendar.YEAR) + "-" + (now.get(Calendar.MONTH) + 1)) + "-" + now.get(Calendar.DATE) + "-" + (now.get(Calendar.HOUR_OF_DAY)) + "-" + (now.get(Calendar.MINUTE)) + "-" + (now.get(Calendar.SECOND));
                ExcelConverter.makeFolder("C:/report_perpustakaan_online");
                ExcelConverter.makeFolder("C:/report_perpustakaan_online/" + admin);

                String saveLocation = "C:/report_perpustakaan_online/" + admin + "/allmember";
                if (isExcel03) {
                    saveLocation = "C:/report_perpustakaan_online/" + admin + "/allmember" + dateStr + ".xls";
                    ExcelConverter.createXls(header, data, saveLocation);
                }

                if (isExcel07) {
                    saveLocation = "C:/report_perpustakaan_online/" + admin + "/allmember" + dateStr + ".xlsx";
                    ExcelConverter.createXlsx(header, data, saveLocation);
                }

                if (isPdf) {
                    saveLocation = "C:/report_perpustakaan_online/" + admin + "/allmember" + dateStr + ".pdf";
                    float[] columnWidths = new float[]{5f, 20f, 10f, 30f, 15f, 15f, 10f, 10f};
                    PDFconverter.createPDF(header, data, saveLocation, "Report All Member", columnWidths);
                }

                if (isCSV) {
                    saveLocation = "C:/report_perpustakaan_online/" + admin + "/allmember" + dateStr + ".csv";
                    CSVconverter.createCSV(header, data, saveLocation);
                }

                return saveLocation;

            } catch (Exception ex) {
                Logger.getLogger(AdminGenerateReport.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AdminGenerateReport.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static String reportAllAdministrator(String admin, boolean isExcel03, boolean isExcel07, boolean isPdf, boolean isCSV) {
        try {
            AdministratorDao administratorDao = ConnectionMySQL.getAdministratorDao();
            try {
                List<Administrator> admins = administratorDao.selectAll();
                String[] header = ObjectReformat.headerUserAdmin();
                String[][] data = ObjectReformat.userAdminToString(admins, header);

                Calendar now = Calendar.getInstance();
                String dateStr = (now.get(Calendar.YEAR) + "-" + (now.get(Calendar.MONTH) + 1)) + "-" + now.get(Calendar.DATE) + "-" + (now.get(Calendar.HOUR_OF_DAY)) + "-" + (now.get(Calendar.MINUTE)) + "-" + (now.get(Calendar.SECOND));
                ExcelConverter.makeFolder("C:/report_perpustakaan_online");
                ExcelConverter.makeFolder("C:/report_perpustakaan_online/" + admin);

                String saveLocation = "C:/report_perpustakaan_online/" + admin + "/alladministrator";
                if (isExcel03) {
                    saveLocation = "C:/report_perpustakaan_online/" + admin + "/alladministrator" + dateStr + ".xls";
                    ExcelConverter.createXls(header, data, saveLocation);
                }

                if (isExcel07) {
                    saveLocation = "C:/report_perpustakaan_online/" + admin + "/alladministrator" + dateStr + ".xlsx";
                    ExcelConverter.createXlsx(header, data, saveLocation);
                }

                if (isPdf) {
                    saveLocation = "C:/report_perpustakaan_online/" + admin + "/alladministrator" + dateStr + ".pdf";
                    float[] columnWidths = new float[]{5f, 30f, 20f};
                    PDFconverter.createPDF(header, data, saveLocation, "Report All Administrator", columnWidths);
                }

                if (isCSV) {
                    saveLocation = "C:/report_perpustakaan_online/" + admin + "/alladministrator" + dateStr + ".csv";
                    CSVconverter.createCSV(header, data, saveLocation);
                }

                return saveLocation;

            } catch (Exception ex) {
                Logger.getLogger(AdminGenerateReport.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AdminGenerateReport.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static String reportAllTransaction(String admin, boolean isExcel03, boolean isExcel07, boolean isPdf, boolean isCSV) {
        try {
            PeminjamanDao peminjamanDao = ConnectionMySQL.getPeminjamanDao();
            try {
                List<Peminjaman> peminjamans = peminjamanDao.selectAll();
                String[] header = ObjectReformat.headerTransaction();
                String[][] data = ObjectReformat.transactionToString(peminjamans, header);

                Calendar now = Calendar.getInstance();
                String dateStr = (now.get(Calendar.YEAR) + "-" + (now.get(Calendar.MONTH) + 1)) + "-" + now.get(Calendar.DATE) + "-" + (now.get(Calendar.HOUR_OF_DAY)) + "-" + (now.get(Calendar.MINUTE)) + "-" + (now.get(Calendar.SECOND));
                ExcelConverter.makeFolder("C:/report_perpustakaan_online");
                ExcelConverter.makeFolder("C:/report_perpustakaan_online/" + admin);

                String saveLocation = "C:/report_perpustakaan_online/" + admin + "/alltransaction";
                if (isExcel03) {
                    saveLocation = "C:/report_perpustakaan_online/" + admin + "/alltransaction" + dateStr + ".xls";
                    ExcelConverter.createXls(header, data, saveLocation);
                }

                if (isExcel07) {
                    saveLocation = "C:/report_perpustakaan_online/" + admin + "/alltransaction" + dateStr + ".xlsx";
                    ExcelConverter.createXlsx(header, data, saveLocation);
                }

                if (isPdf) {
                    saveLocation = "C:/report_perpustakaan_online/" + admin + "/alltransaction" + dateStr + ".pdf";
                    float[] columnWidths = new float[]{5f, 20f, 30f, 10f, 10f, 10f, 10f, 10f};
                    PDFconverter.createPDF(header, data, saveLocation, "Report All Transaction", columnWidths);
                }

                if (isCSV) {
                    saveLocation = "C:/report_perpustakaan_online/" + admin + "/alltransaction" + dateStr + ".csv";
                    CSVconverter.createCSV(header, data, saveLocation);
                }

                return saveLocation;

            } catch (Exception ex) {
                Logger.getLogger(AdminGenerateReport.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AdminGenerateReport.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static String reportAllTransactionHaveNotReturn(String admin, boolean isExcel03, boolean isExcel07, boolean isPdf, boolean isCSV) {
        try {
            PeminjamanDao peminjamanDao = ConnectionMySQL.getPeminjamanDao();
            try {
                List<Peminjaman> peminjamans = peminjamanDao.selectThatHaveNotReturn();
                String[] header = ObjectReformat.headerTransaction();
                String[][] data = ObjectReformat.transactionToString(peminjamans, header);

                Calendar now = Calendar.getInstance();
                String dateStr = (now.get(Calendar.YEAR) + "-" + (now.get(Calendar.MONTH) + 1)) + "-" + now.get(Calendar.DATE) + "-" + (now.get(Calendar.HOUR_OF_DAY)) + "-" + (now.get(Calendar.MINUTE)) + "-" + (now.get(Calendar.SECOND));
                ExcelConverter.makeFolder("C:/report_perpustakaan_online");
                ExcelConverter.makeFolder("C:/report_perpustakaan_online/" + admin);

                String saveLocation = "C:/report_perpustakaan_online/" + admin + "/transactionhavenotreturn";
                if (isExcel03) {
                    saveLocation = "C:/report_perpustakaan_online/" + admin + "/transactionhavenotreturn" + dateStr + ".xls";
                    ExcelConverter.createXls(header, data, saveLocation);
                }

                if (isExcel07) {
                    saveLocation = "C:/report_perpustakaan_online/" + admin + "/transactionhavenotreturn" + dateStr + ".xlsx";
                    ExcelConverter.createXlsx(header, data, saveLocation);
                }

                if (isPdf) {
                    saveLocation = "C:/report_perpustakaan_online/" + admin + "/transactionhavenotreturn" + dateStr + ".pdf";

                    float[] columnWidths = new float[]{5f, 20f, 30f, 10f, 10f, 10f, 10f, 10f};
                    PDFconverter.createPDF(header, data, saveLocation, "Report Transaction That Have Not Return", columnWidths);
                }

                if (isCSV) {
                    saveLocation = "C:/report_perpustakaan_online/" + admin + "/transactionhavenotreturn" + dateStr + ".csv";
                    CSVconverter.createCSV(header, data, saveLocation);
                }

                return saveLocation;

            } catch (Exception ex) {
                Logger.getLogger(AdminGenerateReport.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AdminGenerateReport.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static String reportAllTransactionHaveReturn(String admin, boolean isExcel03, boolean isExcel07, boolean isPdf, boolean isCSV) {
        try {
            PeminjamanDao peminjamanDao = ConnectionMySQL.getPeminjamanDao();
            try {
                List<Peminjaman> peminjamans = peminjamanDao.selectThatHaveReturn();
                String[] header = ObjectReformat.headerTransaction();
                String[][] data = ObjectReformat.transactionToString(peminjamans, header);

                Calendar now = Calendar.getInstance();
                String dateStr = (now.get(Calendar.YEAR) + "-" + (now.get(Calendar.MONTH) + 1)) + "-" + now.get(Calendar.DATE) + "-" + (now.get(Calendar.HOUR_OF_DAY)) + "-" + (now.get(Calendar.MINUTE)) + "-" + (now.get(Calendar.SECOND));
                ExcelConverter.makeFolder("C:/report_perpustakaan_online");
                ExcelConverter.makeFolder("C:/report_perpustakaan_online/" + admin);

                String saveLocation = "C:/report_perpustakaan_online/" + admin + "/transactionhavereturn";
                if (isExcel03) {
                    saveLocation = "C:/report_perpustakaan_online/" + admin + "/transactionhavereturn" + dateStr + ".xls";
                    ExcelConverter.createXls(header, data, saveLocation);
                }

                if (isExcel07) {
                    saveLocation = "C:/report_perpustakaan_online/" + admin + "/transactionhavereturn" + dateStr + ".xlsx";
                    ExcelConverter.createXlsx(header, data, saveLocation);
                }

                if (isPdf) {
                    saveLocation = "C:/report_perpustakaan_online/" + admin + "/transactionhavereturn" + dateStr + ".pdf";

                    float[] columnWidths = new float[]{5f, 20f, 30f, 10f, 10f, 10f, 10f, 10f};
                    PDFconverter.createPDF(header, data, saveLocation, "Report Transaction That Have Return", columnWidths);
                }

                if (isCSV) {
                    saveLocation = "C:/report_perpustakaan_online/" + admin + "/transactionhavereturn" + dateStr + ".csv";
                    CSVconverter.createCSV(header, data, saveLocation);
                }

                return saveLocation;

            } catch (Exception ex) {
                Logger.getLogger(AdminGenerateReport.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AdminGenerateReport.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static String reportAllTransactionGetPenalty(String admin, boolean isExcel03, boolean isExcel07, boolean isPdf, boolean isCSV) {
        try {
            PeminjamanDao peminjamanDao = ConnectionMySQL.getPeminjamanDao();
            try {
                List<Peminjaman> peminjamans = peminjamanDao.selectAll();
                List<Peminjaman> peminjamansFake = new ArrayList<>();
                for (Peminjaman peminjaman : peminjamans) {
                    if (peminjaman.isIsDendaFake()) {
                        peminjamansFake.add(peminjaman);
                    }
                }
                String[] header = ObjectReformat.headerTransaction();
                String[][] data = ObjectReformat.transactionToString(peminjamansFake, header);

                Calendar now = Calendar.getInstance();
                String dateStr = (now.get(Calendar.YEAR) + "-" + (now.get(Calendar.MONTH) + 1)) + "-" + now.get(Calendar.DATE) + "-" + (now.get(Calendar.HOUR_OF_DAY)) + "-" + (now.get(Calendar.MINUTE)) + "-" + (now.get(Calendar.SECOND));
                ExcelConverter.makeFolder("C:/report_perpustakaan_online");
                ExcelConverter.makeFolder("C:/report_perpustakaan_online/" + admin);

                String saveLocation = "C:/report_perpustakaan_online/" + admin + "/transactiongetpenalty";
                if (isExcel03) {
                    saveLocation = "C:/report_perpustakaan_online/" + admin + "/transactiongetpenalty" + dateStr + ".xls";
                    ExcelConverter.createXls(header, data, saveLocation);
                }

                if (isExcel07) {
                    saveLocation = "C:/report_perpustakaan_online/" + admin + "/transactiongetpenalty" + dateStr + ".xlsx";
                    ExcelConverter.createXlsx(header, data, saveLocation);
                }

                if (isPdf) {
                    saveLocation = "C:/report_perpustakaan_online/" + admin + "/transactiongetpenalty" + dateStr + ".pdf";

                    float[] columnWidths = new float[]{5f, 20f, 30f, 10f, 10f, 10f, 10f, 10f};
                    PDFconverter.createPDF(header, data, saveLocation, "Report Transaction That Get Penalty", columnWidths);
                }

                if (isCSV) {
                    saveLocation = "C:/report_perpustakaan_online/" + admin + "/transactiongetpenalty" + dateStr + ".csv";
                    CSVconverter.createCSV(header, data, saveLocation);
                }

                return saveLocation;

            } catch (Exception ex) {
                Logger.getLogger(AdminGenerateReport.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AdminGenerateReport.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static String reportAllTransactionNotGetPenalty(String admin, boolean isExcel03, boolean isExcel07, boolean isPdf, boolean isCSV) {
        try {
            PeminjamanDao peminjamanDao = ConnectionMySQL.getPeminjamanDao();
            try {
                List<Peminjaman> peminjamans = peminjamanDao.selectAll();
                List<Peminjaman> peminjamansFake = new ArrayList<>();
                for (Peminjaman peminjaman : peminjamans) {
                    if (!peminjaman.isIsDendaFake()) {
                        peminjamansFake.add(peminjaman);
                    }
                }
                String[] header = ObjectReformat.headerTransaction();
                String[][] data = ObjectReformat.transactionToString(peminjamansFake, header);

                Calendar now = Calendar.getInstance();
                String dateStr = (now.get(Calendar.YEAR) + "-" + (now.get(Calendar.MONTH) + 1)) + "-" + now.get(Calendar.DATE) + "-" + (now.get(Calendar.HOUR_OF_DAY)) + "-" + (now.get(Calendar.MINUTE)) + "-" + (now.get(Calendar.SECOND));
                ExcelConverter.makeFolder("C:/report_perpustakaan_online");
                ExcelConverter.makeFolder("C:/report_perpustakaan_online/" + admin);

                String saveLocation = "C:/report_perpustakaan_online/" + admin + "/transactionnotgetpenalty";
                if (isExcel03) {
                    saveLocation = "C:/report_perpustakaan_online/" + admin + "/transactionnotgetpenalty" + dateStr + ".xls";
                    ExcelConverter.createXls(header, data, saveLocation);
                }

                if (isExcel07) {
                    saveLocation = "C:/report_perpustakaan_online/" + admin + "/transactionnotgetpenalty" + dateStr + ".xlsx";
                    ExcelConverter.createXlsx(header, data, saveLocation);
                }

                if (isPdf) {
                    saveLocation = "C:/report_perpustakaan_online/" + admin + "/transactionnotgetpenalty" + dateStr + ".pdf";

                    float[] columnWidths = new float[]{5f, 20f, 30f, 10f, 10f, 10f, 10f, 10f};
                    PDFconverter.createPDF(header, data, saveLocation, "Report Transaction That Not Get Penalty", columnWidths);
                }

                if (isCSV) {
                    saveLocation = "C:/report_perpustakaan_online/" + admin + "/transactionnotgetpenalty" + dateStr + ".csv";
                    CSVconverter.createCSV(header, data, saveLocation);
                }

                return saveLocation;

            } catch (Exception ex) {
                Logger.getLogger(AdminGenerateReport.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AdminGenerateReport.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}

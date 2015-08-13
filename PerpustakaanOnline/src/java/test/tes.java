package test;

import action.AdminGenerateReport;
import dao.BookDao;
import dao.CategoryDao;
import dao.MemberDao;
import dao.NewsDao;
import dao.PeminjamanDao;
import entity.Book;
import entity.Category;
import entity.Member;
import entity.News;
import entity.Peminjaman;
import java.io.File;
import java.io.FileInputStream;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.TableModel;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.joda.time.DateTime;
import service.ConnectionMySQL;
import util.DateChanger;
import util.ExcelConverter;
import util.ReportGenerator;

public class tes {

    /**
     * @param args the command line arguments
     */
    public tes() {
//        try {
//            BookDao bookDAO = ConnectionMySQL.getBookDao();
//            try {
//                List<Book> books = bookDAO.selectAll();
//                String[] header = {"Book Tittle", "Book Category", "Book Author",
//                    "Book Year", "Status Peminjaman"};
//
//                String[][] data = new String[books.size()][header.length];
//                int i = 0;
//                for (Book book : books) {
//                    data[i][0] = book.getBookTitle();
//                    data[i][1] = book.getCategory().getCategoryName();
//                    data[i][2] = book.getPengarang();
//                    data[i][3] = book.getBookYear();
//                    data[i][4] = book.getIsDipinjam();
//                    i++;
//                }
//                Calendar now = Calendar.getInstance();
//                String dateStr = (now.get(Calendar.YEAR) + "-" + (now.get(Calendar.MONTH) + 1)) + "-" + now.get(Calendar.DATE);
//
//                ExcelConverter.makeFolder();
//                String saveLocation = "C:/report_perpustakaan_online/report_incoming_harian2_" + dateStr + ".xlsx";
//                System.out.println("saveLocation "+saveLocation);
//                
//                ExcelConverter.runFormulaExport07(header, data, saveLocation);
//                
//                        
//
//            } catch (Exception ex) {
//                Logger.getLogger(AdminGenerateReport.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
//        } catch (SQLException ex) {
//            Logger.getLogger(AdminGenerateReport.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    public static void main(String[] args) throws SQLException, Exception {
        // TODO code application logic here
        String s = "13/07/2014 14:46:58";
        System.out.println(DateChanger.changeDateToMin(s));

        //String location = ReportGenerator.reportAllBook("admin", false, false, true);
        //System.out.println("location "+location);
//        NewsDao dao = ConnectionMySQL.getNewsDao();
//
//        News n = new News();
//        n.setNewsName("newName");
//        n.setNewsPlace("newPlace");
//        n.setNewsDesc("newDesc");
//        Calendar now = Calendar.getInstance();
//        String dateStr = (now.get(Calendar.YEAR) + "-" + (now.get(Calendar.MONTH) + 1)) + "-" + now.get(Calendar.DATE) + " " + (now.get(Calendar.HOUR_OF_DAY)) + ":" + (now.get(Calendar.MINUTE)) + ":" + (now.get(Calendar.SECOND));
//        System.out.println(dateStr);
//        Date date;
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        try {
//            date = sdf.parse(dateStr);
//            DateTime dateTime = new DateTime(date);
//            n.setNewsDateTime(dateTime);
//        } catch (ParseException e) {
//        }
//
//        dao.Insert(n);
//        PeminjamanDao peminjamanDao = ConnectionMySQL.getPeminjamanDao();
//        Peminjaman p = peminjamanDao.getPeminjamanById(19);
//        Date d = p.getTglKembali();
//        System.out.println("d :" + d);
//
//        Calendar now = Calendar.getInstance();
//        String dateStr = (now.get(Calendar.YEAR) + "-" + (now.get(Calendar.MONTH) + 1)) + "-" + now.get(Calendar.DATE);
//
//        Date date;
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//
//        try {
//            date = sdf.parse(dateStr);
//            System.out.println("date :"+date);
//            boolean a = date.before(d);
//            System.out.println("a :"+a);
//        } catch (ParseException e) {
//        }
//
//        CategoryDao cat = ConnectionMySQL.getCategoryDao();
//        Category c = cat.selectCategotyById(1);
//        System.out.println(c);
//        BookDao book = ConnectionMySQL.getBookDao();
//        Book b = book.getBookById(1);
//        Category category = new Category();
//        category.setIdCategory(1);
//
//        b.setCategory(category);
//        b.setBookTitle("tittle");
//        b.setPengarang("pengarang");
//
//        Date date = null;
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//
//        try {
//            date = sdf.parse("2008-02-02");
//            b.setTerbitDate(date);
//        } catch (ParseException e) {
//        }
//
//        System.out.println("insert new book");
//        book.Insert(b);
//        System.out.println(b);
//
//        Member member = new Member();
//        member.setMemberId(3);
//
//        member.setMemberNik("2009");
//        member.setMemberName("memberName");
//        member.setMemberAlamat("memberAlamat");
//        member.setMemberTelp("02145000000");
//        member.setMemberBagian("memberBagian");
//        member.setMemberLokasi("memberLokasi");
//
//        MemberDao memberDao = ConnectionMySQL.getMemberDao();
//
//        memberDao.Update(member);
//
//        Peminjaman peminjaman;
//        peminjaman = peminjamanDao.getPeminjamanById(1);
//        System.out.println("" + peminjaman);
//        peminjaman.setIdPinjam(peminjaman.getIdPinjam());
//        peminjaman.setIsKembali(false);
//
//        peminjamanDao.Update(peminjaman);
//        Date date = new Date();
//        SimpleDateFormat frm = new SimpleDateFormat("YYYY");
//        String DateStr = frm.format(date);
//        Integer DateInt = Integer.parseInt(DateStr)+1;
//        for (int i = 0; i < 20; i++) {
//            DateInt-=1;
//            System.out.println(DateInt);
//        }
//        Calendar now = Calendar.getInstance();
//
//        String dateStr = (now.get(Calendar.YEAR) + "-" + (now.get(Calendar.MONTH) + 1)) + "-" + now.get(Calendar.DATE);
//        System.out.println("before " + dateStr);
//        Date date;
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//
//        try {
//            date = sdf.parse(dateStr);
//            System.out.println("date " + sdf.format(date));
//        } catch (ParseException e) {
//        }
//        now.add(Calendar.WEEK_OF_YEAR, 2);
//
//        dateStr = (now.get(Calendar.YEAR) + "-" + (now.get(Calendar.MONTH) + 1)) + "-" + now.get(Calendar.DATE);
//        System.out.println("after " + dateStr);
//        try {
//            date = sdf.parse(dateStr);
//            System.out.println("date " + sdf.format(date));
//            
//        } catch (ParseException e) {
//        }
    }
}

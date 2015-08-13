/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import entity.Administrator;
import entity.Book;
import entity.Member;
import entity.Peminjaman;
import java.util.List;

/**
 *
 * @author jitzu
 */
public class ObjectReformat {

    public ObjectReformat() {

    }

    public static String[] headerBook() {
        String[] header = {"No", "Tittle", "Category", "Author",
            "Year", "Stock"};
        return header;
    }

    public static String[][] booksToString(List<Book> books, String[] header) {
        String[][] data = new String[books.size()][header.length];
        int i = 0;
        for (Book book : books) {
            data[i][0] = Integer.toString((i + 1));
            data[i][1] = book.getBookTitle();
            data[i][2] = book.getCategory().getCategoryName();
            data[i][3] = book.getPengarang();
            data[i][4] = book.getBookYear();
            data[i][5] = Integer.toString(book.getStock());
            i++;
        }
        return data;
    }

    public static String[] headerUserMember() {
        String[] header = {"No", "Name", "NIK", "Alamat",
            "Telepon", "Ext", "Bagian", "Lokasi"};
        return header;
    }

    public static String[][] userMemberToString(List<Member> members, String[] header) {
        String[][] data = new String[members.size()][header.length];
        int i = 0;
        for (Member member : members) {
            data[i][0] = Integer.toString((i + 1));
            data[i][1] = member.getMemberName();
            data[i][2] = member.getMemberNik();
            data[i][3] = member.getMemberAlamat();
            data[i][4] = member.getMemberTelp();
            data[i][5] = member.getMemberExt();
            data[i][6] = member.getMemberBagian();
            data[i][7] = member.getMemberLokasi();
            i++;
        }
        return data;
    }

    public static String[] headerUserAdmin() {
        String[] header = {"No", "Name", "Username"};
        return header;
    }

    public static String[][] userAdminToString(List<Administrator> admins, String[] header) {
        String[][] data = new String[admins.size()][header.length];
        int i = 0;
        for (Administrator admin : admins) {
            data[i][0] = Integer.toString((i + 1));
            data[i][1] = admin.getTruename();
            data[i][2] = admin.getUsername();
            i++;
        }
        return data;
    }

    public static String[] headerTransaction() {
        String[] header = {"No", "Member Name", "Book Tittle", "Tgl Peminjaman",
            "Tgl Kembali", "Pengembalian", "Status", "Denda"};
        return header;
    }

    public static String[][] transactionToString(List<Peminjaman> peminjamans, String[] header) {
        String[][] data = new String[peminjamans.size()][header.length];
        int i = 0;
        for (Peminjaman peminjaman : peminjamans) {
            data[i][0] = Integer.toString((i + 1));
            data[i][1] = peminjaman.getMember().getMemberName();
            data[i][2] = peminjaman.getBook().getBookTitle();
            data[i][3] = peminjaman.getTglPinjamStr();
            data[i][4] = peminjaman.getTglKembaliStr();
            data[i][5] = peminjaman.getTglPengembalianStr();
            data[i][6] = peminjaman.getStatus();
            peminjaman.isIsDendaFake();
            data[i][7] = peminjaman.getStatusDendaFake();
            i++;
        }
        return data;
    }
}

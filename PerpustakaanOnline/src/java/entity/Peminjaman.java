/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Administrator
 */
public class Peminjaman {

    private int idPinjam;
    private Book book;
    private Member member;
    private Date tglPengembalian;
    private Date tglKembali;
    private Date tglPinjam;
    private boolean isKembali;
    private boolean isDenda;
    private String statusDenda;

    private String status;

    private boolean isDendaFake;
    private String statusDendaFake;

    public Peminjaman() {
    }

    public int getIdPinjam() {
        return idPinjam;
    }

    public void setIdPinjam(int idPinjam) {
        this.idPinjam = idPinjam;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public String getTglKembaliStr() {
        SimpleDateFormat frm = new SimpleDateFormat("yyyy-MM-dd");
        String str = frm.format(tglKembali);
        return str;
    }

    public Date getTglKembali() {
        return tglKembali;
    }

    public void setTglKembali(Date tglKembali) {
        this.tglKembali = tglKembali;
    }

    public String getTglPinjamStr() {
        SimpleDateFormat frm = new SimpleDateFormat("yyyy-MM-dd");
        String str = frm.format(tglPinjam);
        return str;
    }

    public Date getTglPinjam() {
        return tglPinjam;
    }

    public void setTglPinjam(Date tglPinjam) {
        this.tglPinjam = tglPinjam;
    }

    public String getTglPengembalianStr() {
        if (tglPengembalian != null) {
            SimpleDateFormat frm = new SimpleDateFormat("yyyy-MM-dd");
            String str = frm.format(tglPengembalian);
            return str;
        } else {
            return null;
        }
    }

    public Date getTglPengembalian() {
        return tglPengembalian;
    }

    public void setTglPengembalian(Date tglPengembalian) {
        this.tglPengembalian = tglPengembalian;
    }

    public boolean isIsKembali() {
        return isKembali;
    }

    public void setIsKembali(boolean isKembali) {
        this.isKembali = isKembali;
    }

    public String getStatus() {

        if (isKembali) {
            status = "Sudah Kembali";
        } else {
            status = "Belum Kembali";
        }

        return status;
    }

    public boolean isIsDenda() {
        return isDenda;
    }

    public void setIsDenda(boolean isDenda) {
        this.isDenda = isDenda;
    }

    public String getStatusDenda() {
        if (isDenda) {
            statusDenda = "Kena Denda";
        } else {
            statusDenda = "Tidak Kena Denda";
        }

        return statusDenda;
    }

    public boolean isIsDendaFake() {
        Calendar now = Calendar.getInstance();
        String dateStr = (now.get(Calendar.YEAR) + "-" + (now.get(Calendar.MONTH) + 1)) + "-" + now.get(Calendar.DATE);

        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
        }

        if (isDenda) {
            isDendaFake = true;
        } else {
            isDendaFake = date.after(tglKembali);
        }

        return isDendaFake;
    }

    public String getStatusDendaFake() {
        if (isDendaFake) {
            statusDendaFake = "Kena Denda";
        } else {
            statusDendaFake = "Tidak Kena Denda";
        }

        return statusDendaFake;
    }

    @Override
    public String toString() {
        return "Peminjaman[idPinjam=" + idPinjam + ",book=" + book.toString() + ",member=" + member.toString() + ",tglKembali=" + getTglKembaliStr() + ",tglPinjam=" + getTglPinjamStr() + ",isKembali=" + isKembali + "]";
    }

}

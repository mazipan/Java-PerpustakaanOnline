/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Administrator
 */
public class Book {

    private final Book book;
    private Integer idBook;
    private String bookTitle;
    private String pengarang;
    private Date terbitDate;
    private Category category;
    private GeneralModel gmBook;
    private String isDipinjam;
    private String bookYear;
    private Integer stock;

    public GeneralModel getGmBook() {
        gmBook = new GeneralModel(book.getIdBook(), book.getBookTitle(), "Book", book);

        return gmBook;
    }

    public Book() {
        book = this;
    }

    public Integer getIdBook() {
        return idBook;
    }

    public void setIdBook(Integer idBook) {
        this.idBook = idBook;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getPengarang() {
        return pengarang;
    }

    public void setPengarang(String pengarang) {
        this.pengarang = pengarang;
    }

    public String getTerbitDateStr() {
        SimpleDateFormat frm = new SimpleDateFormat("yyyy-MM-dd");
        String str = frm.format(terbitDate);
        return str;
    }

    public String getBookYear() {
        SimpleDateFormat frm = new SimpleDateFormat("yyyy");
        bookYear = frm.format(terbitDate);
        return bookYear;
    }

    public void setBookYear(String bookYear) {
        this.bookYear = bookYear;
    }

    public Date getTerbitDate() {
        return terbitDate;
    }

    public void setTerbitDate(Date terbitDate) {
        this.terbitDate = terbitDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getIsDipinjam() {
        return isDipinjam;
    }

    public void setIsDipinjam(String isDipinjam) {
        this.isDipinjam = isDipinjam;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Book[idBook=" + idBook + ",category=" + category.toString() + ",pengarang=" + pengarang + ",terbitDate=" + getTerbitDateStr() + ",isDipinjam=" + isDipinjam + "]";
    }

}

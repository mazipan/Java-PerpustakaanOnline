/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.joda.time.DateTime;

/**
 *
 * @author mazipan
 */
public class News {

    private Integer idNews;
    private String newsName;
    private String newsPlace;
    private String newsDesc;
    private DateTime newsDateTime;
    private String newsDateTimeStr;
    private String newsDateTimeStrItalic;

    public News() {

    }

    public Integer getIdNews() {
        return idNews;
    }

    public void setIdNews(Integer idNews) {
        this.idNews = idNews;
    }

    public String getNewsName() {
        return newsName;
    }

    public void setNewsName(String newsName) {
        this.newsName = newsName;
    }

    public String getNewsPlace() {
        return newsPlace;
    }

    public void setNewsPlace(String newsPlace) {
        this.newsPlace = newsPlace;
    }

    public String getNewsDesc() {
        return newsDesc;
    }

    public void setNewsDesc(String newsDesc) {
        this.newsDesc = newsDesc;
    }

    public DateTime getNewsDateTime() {
        return newsDateTime;
    }

    public void setNewsDateTime(DateTime newsDateTime) {
        this.newsDateTime = newsDateTime;
    }

    public String getNewsDateTimeStr() {
        if (newsDateTime != null) {
            SimpleDateFormat frm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            newsDateTimeStr = frm.format(newsDateTime.toDate());
            return newsDateTimeStr;
        } else {
            return null;
        }
    }

    public String getNewsDateTimeStrItalic() {
        if (newsDateTime != null) {
            SimpleDateFormat frm = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss");
            newsDateTimeStrItalic = frm.format(newsDateTime.toDate());
            return newsDateTimeStrItalic;
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        return "News[idNews=" + idNews + ",newsName=" + newsName + ",newsPlace=" + newsPlace + ",newsDateTime=" + getNewsDateTimeStr() + "]";
    }

}

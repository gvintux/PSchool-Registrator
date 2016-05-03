package me.fyret.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "news")
public class NewsItem
{

    public NewsItem(int id, String title, String content, Date date, byte[] pic)
    {
        this.id = id;
        this.title = title;
        this.content = content;
        this.date = date;
        this.pic = pic;
    }

    public NewsItem()
    {
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public byte[] getPic()
    {
        return pic;
    }

    public void setPic(byte[] pic)
    {
        this.pic = pic;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "content", nullable = false)
    private String content;
    @Column(name = "date", nullable = false)
    private Date date;
    @Lob
    @Column(name = "pic", nullable = true)
    private byte[] pic;
}

package me.fyret.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "footer_links")
public class FooterLink
{

    public FooterLink(int id, String address, String caption, String description, int order)
    {
        this.id = id;
        this.address = address;
        this.caption = caption;
        this.description = description;
        this.order = order;
    }

    public FooterLink()
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

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getCaption()
    {
        return caption;
    }

    public void setCaption(String caption)
    {
        this.caption = caption;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public int getOrder()
    {
        return order;
    }

    public void setOrder(int order)
    {
        this.order = order;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "address", nullable = false)
    private String address;
    @Column(name = "caption", nullable = false)
    private String caption;
    @Column(name = "description", nullable = true)
    private String description;
    @Column(name = "order", nullable = false)
    private int order;

}

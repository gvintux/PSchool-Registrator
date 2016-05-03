package me.fyret.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "contacts")
public class Contact
{

    public Contact(int id, String title, String person, String link, String email, String phone, String address, int order)
    {
        this.id = id;
        this.title = title != null ? title.trim() : null;
        this.person = person != null ? person.trim() : null;
        this.link = link != null ? link.trim() : null;
        this.email = email != null ? email.trim() : null;
        this.phone = phone != null ? phone.trim() : null;
        this.address = address != null ? address.trim() : null;
        this.order = order;
    }

    public Contact()
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

    public String getPerson()
    {
        return person;
    }

    public void setPerson(String person)
    {
        this.person = person;
    }

    public String getLink()
    {
        return link;
    }

    public void setLink(String link)
    {
        this.link = link;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
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
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "person", nullable = false)
    private String person;
    @Column(name = "link", nullable = false)
    private String link;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "phone", nullable = false, length = 12)
    private String phone;
    @Column(name = "address", nullable = true)
    private String address;
    @Column(name = "order", nullable = false)
    private int order;
}

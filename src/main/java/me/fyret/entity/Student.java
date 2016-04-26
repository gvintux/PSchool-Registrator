package me.fyret.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "students")
public class Student
{

    public Student(int id, String name, String email, String phone, String school, int grade, Character literal, byte[] pic, String login, String password)
    {
        this.id = id;
        this.name = name != null ? name.trim() : null;
        this.email = email != null ? email.trim() : null;
        this.phone = phone != null ? phone.trim() : null;
        this.school = school != null ? school.trim() : null;
        this.grade = grade;
        this.literal = literal;
        this.pic = pic;
        this.login = login != null ? login.trim() : null;
        this.password = password != null ? password.trim() : null;
    }

    public Student()
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

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name != null ? name.trim() : null;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email != null ? email.trim() : null;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone != null ? phone.trim() : null;
    }

    public String getSchool()
    {
        return school;
    }

    public void setSchool(String school)
    {
        this.school = school != null ? school.trim() : null;
    }

    public int getGrade()
    {
        return grade;
    }

    public void setGrade(int grade)
    {
        this.grade = grade;
    }

    public Character getLiteral()
    {
        return literal;
    }

    public void setLiteral(Character literal)
    {
        this.literal = literal;
    }

    public byte[] getPic()
    {
        return pic;
    }

    public void setPic(byte[] pic)
    {
        this.pic = pic;
    }

    public String getLogin()
    {
        return login;
    }

    public void setLogin(String login)
    {
        this.login = login != null ? login.trim() : null;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password != null ? password.trim() : null;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "phone", nullable = false, length = 12)
    private String phone;
    @Column(name = "school", nullable = false)
    private String school;
    @Column(name = "grade", nullable = false)
    private int grade;
    @Column(name = "literal", nullable = true, length = 1)
    private Character literal;
    @Lob
    @Column(name = "pic", nullable = true)
    private byte[] pic;
    @Column(name = "login", nullable = true)
    private String login;
    @Column(name = "password", nullable = true)
    private String password;
}

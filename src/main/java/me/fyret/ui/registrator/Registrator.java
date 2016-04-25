package me.fyret.ui.registrator;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import me.fyret.data.HibernateUtil;
import me.fyret.entity.Student;
import org.hibernate.Session;

@Theme("registrator_theme")
@Widgetset("me.fyret.registrator.RegistratorWidgetset")
public class Registrator extends UI
{

    @Override
    protected void init(VaadinRequest vaadinRequest)
    {
        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
//        setContent(layout);
        setContent(new Header());
        setSizeFull();
        Label label = new Label();
        layout.addComponent(label);
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Student> list = session.createCriteria(Student.class).list();
        String data = new String();
        for (Student s : list)
        {
            data += s.getName() + " " + s.getSchool() + "\n";
        }
        label.setCaption(data);
        session.close();
    }

    @WebServlet(urlPatterns = "/*", name = "RegistratorServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = Registrator.class, productionMode = false)
    public static class RegistratorServlet extends VaadinServlet
    {
    }
}

package me.fyret.ui.registrator;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import java.util.ArrayList;
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
        int[] classes = {9, 10, 11};
        Session session = HibernateUtil.getSessionFactory().openSession();
        int regCount = session.createCriteria(Student.class).list().size();
        session.close();
        Header header = new Header(regCount);
        Form form = new Form(classes, "АБВГДЕЖ", header);
        Panel formPanel = new Panel("Предварительная регистрация", form);
        formPanel.setIcon(FontAwesome.PLUS);
        formPanel.setWidth("100%");
        formPanel.setStyleName("ps-form-panel");
        Label lgap = new Label();
        Label rgap = new Label();
        HorizontalLayout hl = new HorizontalLayout(lgap, formPanel, rgap);
        hl.setWidth("100%");

        ArrayList<Link> links = new ArrayList<>();
        links.add(new Link("Рабочая программа", new ExternalResource("http://fyret.me/WorkProgram")));
        links.add(new Link("Местоположение", new ExternalResource("https://www.google.ru/maps/place/АмГПГУ/@50.5400131,137.0331013")));
        links.add(new Link("Контакты", new ExternalResource("http://fyret.me/Contacts")));

        Footer footer = new Footer(links);
        footer.setWidth("100%");
        final VerticalLayout layout = new VerticalLayout();
        layout.setSpacing(true);
        layout.setWidth("100%");
        setContent(layout);
        layout.addComponents(header, hl, footer);
        setSizeFull();
        setStyleName("ps-registrator");
    }

    @WebServlet(urlPatterns = "/*", name = "RegistratorServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = Registrator.class, productionMode = false)
    public static class RegistratorServlet extends VaadinServlet
    {
    }
}

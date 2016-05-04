package me.fyret.ui.contacts;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import java.util.ArrayList;
import java.util.List;
import me.fyret.entity.Contact;
import me.fyret.ui.common.Footer;
import me.fyret.ui.common.Header;
import me.fyret.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class Contacts extends CustomComponent implements View
{

    public static final String VIEW_NAME = "contacts";

    public Contacts()
    {
        setStyleName("ps-contacts");
        setCompositionRoot(new Label());
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event)
    {
        contacts = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            contacts = session.createCriteria(Contact.class).list();
            contacts.sort((Contact o1, Contact o2)
                    -> {
                if (o1.getOrder() > o2.getOrder()) {
                    return 1;
                }
                if (o1.getOrder() < o2.getOrder()) {
                    return -1;
                }
                return 0;
            });
        }
        catch (HibernateException ex) {
            ex.printStackTrace();
            Notification.show("Ошибка!", "Не удалось создать сессию", Notification.Type.ERROR_MESSAGE);
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
        hl = new HorizontalLayout();
        hl.setWidth("100%");

        vl = new VerticalLayout();
        for (Contact contact : contacts) {
            vl.addComponent(new ContactCard(contact));
        }
        content = new Panel(vl);
        lgap = new Label();
        rgap = new Label();
        hl.addComponents(lgap, content, rgap);
        hl.setExpandRatio(lgap, 0.25f);
        hl.setExpandRatio(content, 0.50f);
        hl.setExpandRatio(rgap, 0.25f);

        content.setStyleName("ps-contacts-content");
        header = new Header("Контакты", new Label());
        footer = new Footer();
        root = new VerticalLayout(header, hl, footer);
        setCompositionRoot(root);
    }
    private Header header;
    private Footer footer;
    private Panel content;
    private VerticalLayout vl, root;
    private HorizontalLayout hl;
    private Label lgap, rgap;
    private Session session;
    private List<Contact> contacts;
}

package me.fyret.ui.contacts;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import java.util.ArrayList;
import java.util.List;
import me.fyret.entity.Contact;
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
            contacts.sort((Contact o1, Contact o2) -> {
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
        lgap = new Label();
        lgap.setSizeFull();
        rgap = new Label();
        rgap.setSizeFull();
        hl.addComponents(lgap, vl, rgap);
        hl.setExpandRatio(lgap, 0.25f);
        hl.setExpandRatio(vl, 0.75f);
        hl.setExpandRatio(rgap, 0.25f);

        setCompositionRoot(hl);
    }
    private VerticalLayout root;
    private VerticalLayout vl;
    private HorizontalLayout hl;
    private Label lgap, rgap;
    private Session session;
    private List<Contact> contacts;
}

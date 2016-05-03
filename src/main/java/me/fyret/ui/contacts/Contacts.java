package me.fyret.ui.contacts;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import java.util.Comparator;
import java.util.List;
import me.fyret.data.HibernateUtil;
import me.fyret.entity.Contact;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class Contacts extends CustomComponent implements View
{

    public Contacts()
    {
        List<Contact> contacts = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            contacts = session.createCriteria(Contact.class).list();
            contacts.sort(new Comparator<Contact>()
            {
                @Override
                public int compare(Contact o1, Contact o2)
                {
                    if (o1.getOrder() > o2.getOrder()) {
                        return 1;
                    }
                    if (o1.getOrder() < o2.getOrder()) {
                        return -1;
                    }
                    return 0;
                }
            });
        }
        catch (HibernateException ex) {
            ex.printStackTrace();
            Notification.show("Ошибка!", "Не удалось создать сессию", Notification.Type.ERROR_MESSAGE);
        }
        VerticalLayout vl = new VerticalLayout();
        for (Contact contact : contacts) {
            vl.addComponent(new ContactCard(contact));
        }

        setStyleName("ps-contacts");
        setCompositionRoot(vl);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event)
    {

    }
    private Session session;
}

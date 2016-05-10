package me.fyret.ui.registrator;

import com.vaadin.data.util.ObjectProperty;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import me.fyret.entity.Student;
import me.fyret.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class RegCounter extends CustomComponent
{

    public RegCounter()
    {
        count = new ObjectProperty<>(0);
        countLabel = new Label(count);
        countLabel.setDescription("Количество учеников, подавших заявку");
        setCompositionRoot(countLabel);
        setStyleName("ps-specific-regcounter");
    }

    public void updateCounter()
    {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            int c = session.createCriteria(Student.class).list().size();
            count.setValue(c);
        }
        catch (HibernateException ex) {
            ex.printStackTrace();
            Notification.show("Ошибка!", "Не удалось открыть сессию", Notification.Type.ERROR_MESSAGE);
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
    }
    private ObjectProperty<Integer> count;
    private Label countLabel;

}

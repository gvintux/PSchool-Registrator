package me.fyret.ui.news;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import java.util.List;
import me.fyret.entity.NewsItem;
import me.fyret.ui.cabinet.Curriculum;
import me.fyret.ui.cabinet.PersonalMenu;
import me.fyret.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class News extends CustomComponent implements View
{

    public static final String VIEW_NAME = "news";

    public News()
    {
        setStyleName("ps-news");
        setCompositionRoot(new Label());
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event)
    {
        Session session = null;
        list = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            list = session.createCriteria(NewsItem.class).list();
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
        vl = new VerticalLayout();
        vl.setSizeFull();
        for (NewsItem item : list) {
            vl.addComponent(new NewsCard(item));

            menu = new PersonalMenu();
            curriculum = new Curriculum();

            hl = new HorizontalLayout(menu, vl, curriculum);
            hl.setExpandRatio(menu, 0.25f);
            hl.setExpandRatio(vl, 0.75f);
            hl.setExpandRatio(curriculum, 0.25f);
            hl.setSizeFull();
            setCompositionRoot(hl);
            setSizeFull();
        }
    }
    private List<NewsItem> list;
    VerticalLayout vl;
    HorizontalLayout hl;
    PersonalMenu menu;
    Curriculum curriculum;
}

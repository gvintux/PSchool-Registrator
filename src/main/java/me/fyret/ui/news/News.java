package me.fyret.ui.news;

import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.VerticalLayout;
import java.util.List;
import me.fyret.util.HibernateUtil;
import me.fyret.entity.NewsItem;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class News extends CustomComponent
{

    public News()
    {
        Session session = null;
        VerticalLayout vl = new VerticalLayout();
        try
        {
            session = HibernateUtil.getSessionFactory().openSession();
            List<NewsItem> list = session.createCriteria(NewsItem.class).list();
            for (NewsItem item : list)
            {
                vl.addComponent(new NewsCard(item));
            }

        } catch (HibernateException ex)
        {
            ex.printStackTrace();
        } finally
        {
            if (session != null)
            {
                session.close();
            }
        }
        setStyleName("ps-news");
        vl.setSpacing(true);
        setCompositionRoot(vl);
    }
}

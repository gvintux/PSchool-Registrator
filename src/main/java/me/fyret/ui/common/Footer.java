package me.fyret.ui.common;

import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import java.util.List;
import me.fyret.entity.FooterLink;
import me.fyret.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class Footer extends CustomComponent
{

    public Footer()
    {
        Session session = null;
        links = null;
        hl = new HorizontalLayout();
        lgap = new Label();
        rgap = new Label();

        hl.setWidth("100%");
        hl.setHeight("3em");
        hl.addComponent(lgap);
        hl.setExpandRatio(lgap, 1.0f);
        try
        {
            session = HibernateUtil.getSessionFactory().openSession();
            links = session.createCriteria(FooterLink.class).list();
            links.sort((FooterLink o1, FooterLink o2)
                    ->
                    {
                        if (o1.getOrder() > o2.getOrder())
                        {
                            return 1;
                        }
                        if (o1.getOrder() < o2.getOrder())
                        {
                            return -1;
                        }
                        return 0;
            });

            for (FooterLink link : links)
            {
                Link l = new Link(link.getCaption(), new ExternalResource(link.getAddress()));
                l.setDescription(link.getDescription());
                l.setWidthUndefined();
                l.setTargetName("_blank");
                hl.addComponent(l);
                hl.setComponentAlignment(l, Alignment.MIDDLE_CENTER);
            }

        } catch (HibernateException ex)
        {
        } finally
        {
            if (session != null)
            {
                session.close();
            }
        }
        hl.addComponent(rgap);
        hl.setExpandRatio(rgap, 1.0f);
        hl.setSpacing(true);

        setStyleName("ps-footer");
        setCompositionRoot(hl);
    }
    private final Label lgap;
    private final Label rgap;
    private final HorizontalLayout hl;
    private List<FooterLink> links;
}

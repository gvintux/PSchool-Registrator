package me.fyret.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.BootstrapFragmentResponse;
import com.vaadin.server.BootstrapListener;
import com.vaadin.server.BootstrapPageResponse;
import com.vaadin.server.ServiceException;
import com.vaadin.server.SessionInitEvent;
import com.vaadin.server.SessionInitListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import me.fyret.ui.contacts.Contacts;
import me.fyret.ui.main.MainPage;
import me.fyret.ui.news.News;
import me.fyret.ui.registrator.Registrator;

@Theme("pschool_theme")
@Widgetset("me.fyret.PSchoolWidgetset")
public class PSchool extends UI
{

    public static final String TITLE = "{void} - школа программирования";

    @Override
    protected void init(VaadinRequest request)
    {
        getPage().setTitle(TITLE);
        navigator = new Navigator(this, this);
//
        navigator.addView(Contacts.VIEW_NAME, Contacts.class);
//
        navigator.addView(News.VIEW_NAME, News.class);
        navigator.addView(MainPage.VIEW_NAME, MainPage.class);
        navigator.addView(Registrator.VIEW_NAME, Registrator.class);
        navigator.setErrorView(MainPage.class);
        setContent(new Label("dumb"));
//          navigator.navigateTo(MainPage.VIEW_NAME);
    }

    @WebServlet(urlPatterns = "/*", name = "PSchoolServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = PSchool.class, productionMode = false)
    public static class PSchoolServlet extends VaadinServlet
    {

        @Override
        protected void servletInitialized() throws ServletException
        {
            super.servletInitialized(); //To change body of generated methods, choose Tools | Templates.
            getService().addSessionInitListener(new SessionInitListener()
            {
                @Override
                public void sessionInit(SessionInitEvent event) throws ServiceException
                {
                    event.getSession().addBootstrapListener(new BootstrapListener()
                    {
                        @Override
                        public void modifyBootstrapFragment(BootstrapFragmentResponse response)
                        {
//                            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        }

                        @Override
                        public void modifyBootstrapPage(BootstrapPageResponse response)
                        {
//                            response.getDocument().head().appendElement("meta").attr("name", "keywords").attr(TITLE, TITLE) //                            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        }
                    });
                }
            });
        }

    }

    private Navigator navigator;

}

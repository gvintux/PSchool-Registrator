package me.fyret.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
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

//        setContent(new Footer());
        setContent(new Label("dumb"));

        navigator.navigateTo(Registrator.VIEW_NAME);
    }

    @WebServlet(urlPatterns = "/*", name = "PSchoolServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = PSchool.class, productionMode = false)
    public static class PSchoolServlet extends VaadinServlet
    {

    }

    private Navigator navigator;

}

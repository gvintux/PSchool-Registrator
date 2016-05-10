package me.fyret.ui.main;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinService;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import me.fyret.ui.common.Footer;
import me.fyret.ui.common.Header;
import me.fyret.ui.registrator.RegLink;

public class MainPage extends CustomComponent implements View
{

    public static final String VIEW_NAME = "";

    public MainPage()
    {
        setStyleName("ps-mainpage");
        setCompositionRoot(new Label());
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event)
    {
        loadProperties();
        buildUI();
    }

    public void loadProperties()
    {
        String baseDir = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
        Properties props = new Properties();
        try (FileInputStream in = new FileInputStream(baseDir + "/WEB-INF/classes/mainpage.properties")) {
            props.load(in);
        }
        catch (IOException ex) {
            ex.printStackTrace();
            Notification.show("Ошибка!", "Не загрузить параметры из файла", Notification.Type.ERROR_MESSAGE);
        }
        textContent = (String) props.get("content");
    }

    public void buildUI()
    {
        header = new Header(null, new RegLink());
        footer = new Footer();
        content = new Panel();
        content.setStyleName("ps-mainpage-content");
//        content.setWidth("100%");
        Label contentLabel = new Label(textContent, ContentMode.HTML);
        contentLabel.setStyleName("ps-mainpage-content-text");
        content.setContent(contentLabel);
        lgap = new Label();
        rgap = new Label();

        hl = new HorizontalLayout(lgap, content, rgap);
        hl.setWidth("100%");
        hl.setExpandRatio(lgap, 0.25f);
        hl.setExpandRatio(content, 0.5f);
        hl.setExpandRatio(rgap, 0.25f);

        vl = new VerticalLayout(header, hl, footer);
        setCompositionRoot(vl);

    }

    private Header header;
    private Footer footer;
    private Panel content;
    private Label lgap, rgap;
    private HorizontalLayout hl;
    private VerticalLayout vl;
    private String textContent;

}

package me.fyret.ui.main;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import me.fyret.ui.common.Footer;
import me.fyret.ui.common.Header;

public class MainPage extends CustomComponent implements View
{

    public static final String VIEW_NAME = "main";

    public MainPage()
    {
        setStyleName("ps-mainpage");
        setCompositionRoot(new Label());
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event)
    {
        header = new Header(null, new Label());
        footer = new Footer();
        content = new Panel();
//        content.setWidth("100%");
        content.setContent(new Label("Мы рады видеть Вас в нашей школе программирования, если Вы:\n"
                + "обучаетесь в 9-11 классе;\n"
                + "интересуетесь программированием и всем, что с ним связано;\n"
                + "недовольны уровнем преподавания информатики в Вашей школе;\n"
                + "жаждите новых знаний;\n"
                + "хотите попасть в интересный коллектив с эксцентричным преподавателем;\n"
                + "хотите примерить на себя роль студента.\n"
                + "\n"
                + "Что Вас ждёт?\n"
                + "80-часовой курс программирования. И... да! Он Вам по силам;\n"
                + "Уютная университетская атмосфера;\n"
                + "Личный кабинет, электронный журнал, доступ к электронной почте, чату и конференциям;\n"
                + "Использование современных программ и технологий;\n"
                + "Электронные книги;\n"
                + "Преподаватель университета.\n"
                + "Ооочень много чая!\n"
                + "\n"
                + "Что в конце?\n"
                + "Сертификат о прохождении курса с указанием пройденных тем, количества часов и полученных баллов.\n"
                + "Новые знания и, возможно, новые друзья;", ContentMode.PREFORMATTED));
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

}

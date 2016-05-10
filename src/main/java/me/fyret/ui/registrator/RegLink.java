package me.fyret.ui.registrator;

import com.vaadin.server.ExternalResource;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Link;

public class RegLink extends CustomComponent
{

    public RegLink()
    {
        Link link = new Link("Зарегистрироваться", new ExternalResource("http://fyret.me:8080/#!registrator"));
        link.setWidth("100%");
        link.setTargetName("_blank");
        link.setDescription("Заполните форму регистрации и получите уведомление о начале занятий");
        setCompositionRoot(link);
        setStyleName("ps-specific-reglink");
    }
}

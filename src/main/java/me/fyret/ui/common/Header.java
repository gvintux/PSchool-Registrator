package me.fyret.ui.common;

import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

public class Header extends CustomComponent
{

    public Header(String pageName, AbstractComponent specific)
    {
        this.specific = specific;
        logo = new Label("logo");
//        logo.setSizeFull();
        logo.setStyleName("ps-header-logo");
        title = new Label(pageName);
//        title.setSizeFull();
        title.setStyleName("ps-header-title");
        hl = new HorizontalLayout(logo, title, specific);
        hl.setWidth("100%");
        hl.setHeight("3em");
        hl.setExpandRatio(logo, 0.25f);
        hl.setExpandRatio(title, 0.5f);
        hl.setExpandRatio(specific, 0.25f);
        hl.setComponentAlignment(logo, Alignment.MIDDLE_CENTER);
        hl.setComponentAlignment(title, Alignment.MIDDLE_LEFT);
        hl.setComponentAlignment(specific, Alignment.MIDDLE_CENTER);
        setStyleName("ps-header");
        setCompositionRoot(hl);
    }
    private final AbstractComponent specific;
    private final Label logo;
    private final Label title;
    private final HorizontalLayout hl;
}

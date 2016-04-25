package me.fyret.ui.registrator;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

public class Header extends CustomComponent
{

    public Header()
    {
        Label logo = new Label("Школа программиста", ContentMode.HTML);
        logo.setWidthUndefined();
        logo.setStyleName("ps-header-logo");

        String totalRegistered = "Всего зарегистрировалось: ";

        Label regCount = new Label(totalRegistered + 0, ContentMode.HTML);
        regCount.setWidthUndefined();
        regCount.setStyleName("ps-header-reg-count");
        Label hgap = new Label();
        HorizontalLayout hl = new HorizontalLayout(logo, hgap, regCount);
        hl.setExpandRatio(hgap, 1.0f);
        hl.setWidth("100%");
        setStyleName("ps-header");
        setCompositionRoot(hl);
    }
}

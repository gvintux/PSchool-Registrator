package me.fyret.ui.registrator;

import com.vaadin.data.util.ObjectProperty;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

public class Header extends CustomComponent
{

    public Header(int count)
    {
        countProperty = new ObjectProperty<>(count);
        logo = new Label("Школа программиста", ContentMode.HTML);
//        logo.setIcon(FontAwesome.TERMINAL);
        logo.setWidthUndefined();
        logo.setStyleName("ps-header-logo");

        regCount = new Label(countProperty, ContentMode.HTML);
//        regCount.setIcon(FontAwesome.USERS);
        regCount.setDescription("Количество зарегистрировавшихся студентов");
        regCount.setWidthUndefined();
        regCount.setStyleName("ps-header-reg-count");

        hgap = new Label();
        hl = new HorizontalLayout(logo, hgap, regCount);
        hl.setExpandRatio(hgap, 1.0f);
        hl.setWidth("100%");

        setStyleName("ps-header");
        setCompositionRoot(hl);
    }

    public void addCount()
    {
        countProperty.setValue(countProperty.getValue() + 1);
    }

    private final ObjectProperty<Integer> countProperty;
    private final Label regCount;
    private final Label logo;
    private final Label hgap;
    private final HorizontalLayout hl;
}

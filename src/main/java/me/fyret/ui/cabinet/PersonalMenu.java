package me.fyret.ui.cabinet;

import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;

public class PersonalMenu extends CustomComponent
{

    public PersonalMenu()
    {
        setCompositionRoot(new Label("menu"));
        setSizeFull();
    }

}

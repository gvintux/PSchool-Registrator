package me.fyret.ui.cabinet;

import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;

public class Curriculum extends CustomComponent
{

    public Curriculum()
    {
        setCompositionRoot(new Label("curriculum"));
        setSizeFull();
    }

}

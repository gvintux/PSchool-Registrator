package me.fyret.ui.common;

import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import java.util.ArrayList;

public class Footer extends CustomComponent
{

    public Footer(ArrayList<Link> links)
    {
        lgap = new Label();
        rgap = new Label();
        hl = new HorizontalLayout();
        hl.setWidth("100%");
        hl.addComponent(lgap);
        hl.setExpandRatio(lgap, 1.0f);

        for (Link link : links) {
            link.setWidthUndefined();
            link.setTargetName("_blank");
            link.setWidthUndefined();
            hl.addComponent(link);
        }

        hl.addComponent(rgap);
        hl.setExpandRatio(rgap, 1.0f);
        hl.setSpacing(true);

        setStyleName("ps-footer");
        setCompositionRoot(hl);
    }
    private final Label lgap;
    private final Label rgap;
    private final HorizontalLayout hl;
}

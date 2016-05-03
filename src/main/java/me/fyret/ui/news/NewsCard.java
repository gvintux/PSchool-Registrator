package me.fyret.ui.news;

import com.vaadin.server.StreamResource;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import me.fyret.util.ImageResource;
import me.fyret.entity.NewsItem;

public class NewsCard extends CustomComponent
{

    public NewsCard(NewsItem item)
    {
        Label title = new Label(item.getTitle());
        title.setStyleName("ps-newscard-title");
        Label date = new Label(item.getDate().toString());
        date.setStyleName("ps-newscard-date");
        Label content = new Label(item.getContent(), ContentMode.HTML);
        content.setStyleName("ps-newscard-content");
        ImageResource imageResource = new ImageResource(item.getPic());
        Image pic = new Image(null, new StreamResource(imageResource, "news-pic-" + item.getId()));
        pic.setStyleName("ps-newscard-pic");
        pic.setWidth(240, Unit.PIXELS);
        pic.setHeight(240, Unit.PIXELS);
//        if(pic.get)
        VerticalLayout vl = new VerticalLayout(title, date, content, pic);
        vl.setStyleName("ps-newscard");
        vl.setSpacing(true);
        setCompositionRoot(vl);
    }

}

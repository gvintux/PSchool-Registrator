package me.fyret.ui.contacts;

import com.vaadin.server.ExternalResource;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.VerticalLayout;
import me.fyret.entity.Contact;

public class ContactCard extends CustomComponent
{

    public ContactCard(Contact contact)
    {
        Link contactTitle = new Link(contact.getTitle(), new ExternalResource(contact.getLink()));
        contactTitle.setStyleName("ps-contactcard-title");
        contactTitle.setTargetName("_blank");

        Label contactPerson = new Label(contact.getPerson());
        contactPerson.setStyleName("ps-contactcard-person");

        Label contactAddress = new Label(contact.getAddress());
        contactAddress.setStyleName("ps-contactcard-address");

        Label contactPhone = new Label(contact.getPhone());
        contactPhone.setStyleName("ps-contactcard-phone");

        Link contactEmail = new Link(contact.getEmail(), new ExternalResource("mailto:" + contact.getEmail()));
        contactEmail.setStyleName("ps-contactcard-email");

        VerticalLayout vl = new VerticalLayout(contactTitle, contactPerson, contactAddress, contactEmail, contactPhone);
        setStyleName("ps-contactcard");
        vl.setSpacing(true);
        setCompositionRoot(vl);
    }
}

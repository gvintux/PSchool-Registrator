package me.fyret.ui.registrator;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import me.fyret.ui.common.Footer;
import me.fyret.ui.common.Header;

public class Registrator extends CustomComponent implements View
{

    public static final String VIEW_NAME = "registrator";

    public Registrator()
    {
        setStyleName("ps-registrator");
        setCompositionRoot(new Label());
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event)
    {
        loadProperties();
        buildUI();
    }

    private void loadProperties()
    {
        String baseDir = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
        Properties props = new Properties();
        try (FileInputStream in = new FileInputStream(baseDir + "/WEB-INF/classes/registrator.properties")) {
            props.load(in);
        }
        catch (IOException ex) {
            ex.printStackTrace();
            Notification.show("Ошибка!", "Не загрузить параметры из файла", Notification.Type.ERROR_MESSAGE);
        }
        grades = new ArrayList<>();
        literals = new ArrayList<>();

        String[] gds = ((String) props.get("grades")).split(",");

        for (int i = 0; i < gds.length; ++i) {
            grades.add(Integer.valueOf(gds[i]));
        }

        String[] ltrs = ((String) props.get("literals")).split(",");

        for (int i = 0; i < ltrs.length; ++i) {
            literals.add(ltrs[i].charAt(0));
        }
    }

    private void buildUI()
    {
        RegCounter regCounter = new RegCounter();
        regCounter.updateCounter();
        header = new Header("Предварительная регистрация", regCounter);
        footer = new Footer();
        lgap = new Label();
        rgap = new Label();
        form = new Form(grades, literals, regCounter);
        regButton = new Button("Зарегистрироваться", form);
        regButton.setStyleName("ps-registrator-regbutton");
        regButton.setWidth("100%");
        regButton.setDescription("Нажимая на эту кнопку Вы соглашаетесь на обработку персональных данных и несёте ответственность за корректность введённых данных");
        vl = new VerticalLayout(form, regButton);
        vl.setSpacing(true);
        hl = new HorizontalLayout(lgap, vl, rgap);
        hl.setWidth("100%");
        root = new VerticalLayout(header, hl, footer);
        setCompositionRoot(root);
    }

    private Header header;
    private Footer footer;
    private Form form;
    private Button regButton;
    private VerticalLayout vl, root;
    private HorizontalLayout hl;
    private Label lgap, rgap;
    ArrayList<Integer> grades;
    ArrayList<Character> literals;
}

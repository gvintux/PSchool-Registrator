package me.fyret.ui.registrator;

import com.vaadin.data.Validator;
import com.vaadin.data.util.BeanItem;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.regex.Pattern;
import me.fyret.util.HibernateUtil;
import me.fyret.entity.Student;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Form extends CustomComponent
{

    public Form(int[] classes, String literals, Header header)
    {
        String requiredError = "Это поле обязательно для заполнения";
        String formatError = "Неверный формат данных. Должно быть: ";
        Validator.InvalidValueException requiredException = new Validator.InvalidValueException(requiredError);
        this.header = header;
        student = new Student();
        BeanItem<Student> studentContainer = new BeanItem<>(student);
        TextField name = new TextField("Как Вас зовут?", studentContainer.getItemProperty("name"));
        name.setIcon(FontAwesome.USER);
        name.setNullRepresentation("Например, Иванов Андрей Петрович");
        name.setDescription("Нам нужно Ваше имя чтобы внести список учеников и создать учётную запись для личного кабинета");
        name.setImmediate(true);
        name.setWidth("100%");
        name.addValidator(obj -> {
            String data = (String) obj;
            if (data == null) {
                throw requiredException;
            }
            if (data.isEmpty()) {
                throw requiredException;
            }
            StringTokenizer tokenizer = new StringTokenizer(data, " ");
            Validator.InvalidValueException formatException = new Validator.InvalidValueException(formatError + "Фамилия Имя Отчество");
            if (tokenizer.countTokens() < 3) {
                throw formatException;
            }
            boolean bigLetters = true;
            while (tokenizer.hasMoreTokens()) {
                bigLetters &= Character.isUpperCase(tokenizer.nextToken().charAt(0));
            }
            if (!bigLetters) {
                throw formatException;
            }
        });

        TextField school = new TextField("В какой школе Вы учитесь?", studentContainer.getItemProperty("school"));
        school.setIcon(FontAwesome.BUILDING);
        school.setNullRepresentation("Например, МБОУ СОШ №69");
        school.setDescription("Нам нужно знать, в какой школе Вы учитесь для того, чтобы выяснить, будут ли Ваши знакомые посещать занятия вместе с Вами");
        school.setImmediate(true);
        school.setWidth("100%");
        school.addValidator(obj -> {
            String data = (String) obj;
            if (data == null) {
                throw requiredException;
            }
            if (data.isEmpty()) {
                throw requiredException;
            }
        });

        ArrayList<Integer> classesList = new ArrayList<>();
        for (int i : classes) {
            classesList.add(i);
        }
        ComboBox grade = new ComboBox(null, classesList);
        grade.setPropertyDataSource(studentContainer.getItemProperty("grade"));
        grade.setTextInputAllowed(false);
        grade.setNullSelectionAllowed(false);
        grade.select(classesList.get(0));
        grade.setSizeUndefined();

        ArrayList<Character> litList = new ArrayList<>();
        for (int i = 0; i < literals.length(); ++i) {
            litList.add(literals.charAt(i));
        }
        ComboBox literal = new ComboBox(null, litList);
        literal.setPropertyDataSource(studentContainer.getItemProperty("literal"));
        literal.setTextInputAllowed(false);
        literal.setNullSelectionAllowed(true);
        literal.select(null);
        literal.setSizeUndefined();

        Label hgap = new Label();
        HorizontalLayout classLayout = new HorizontalLayout(grade, literal, hgap);
        classLayout.setCaption("В каком Вы классе?");
        classLayout.setIcon(FontAwesome.GRADUATION_CAP);
        classLayout.setExpandRatio(hgap, 1.0f);
        classLayout.setDescription("Нам нужно знать, в каком классе Вы учитесь, чтобы адекватно задать нагрузку");
        classLayout.setImmediate(true);
        classLayout.setWidth("100%");
        classLayout.setSpacing(true);

        TextField phone = new TextField("Ваш номер телефона?", studentContainer.getItemProperty("phone"));
        phone.setIcon(FontAwesome.PHONE);
        phone.setNullRepresentation("Например, +79998887766");
        phone.setDescription("Нам нужно знать Ваш номер телефона, чтобы оповестить о начале занятий");
        phone.setImmediate(true);
        phone.setWidth("100%");
        phone.addValidator(obj -> {
            String data = ((String) obj);
            if (data == null) {
                throw requiredException;
            }
            if (data.isEmpty()) {
                throw requiredException;
            }
            Validator.InvalidValueException formatException = new Validator.InvalidValueException(formatError + "+71234567890");
            if (data.trim().length() != 12) {
                throw formatException;
            }
            if (!data.trim().substring(0, 2).contentEquals("+7")) {
                throw formatException;
            }
            boolean onlyNumbers = true;
            for (int i = 1; i < data.length(); ++i) {
                onlyNumbers &= Character.isDigit(data.charAt(i));
            }
            if (!onlyNumbers) {
                throw formatException;
            }

        });

        TextField email = new TextField("Ваш e-mail?", studentContainer.getItemProperty("email"));
        email.setIcon(FontAwesome.ENVELOPE);
        email.setNullRepresentation("Например, ap_ivanov@mymail.ru");
        email.setDescription("Нам нужно знать Ваш e-mail, чтобы оповестить о начале занятий и прислать учётные данные "
                + "для личного кабинета");
        email.setImmediate(true);
        email.setWidth("100%");
        email.addValidator(obj -> {
            String data = (String) obj;
            if (data == null) {
                throw requiredException;
            }
            if (data.isEmpty()) {
                throw requiredException;
            }
            Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
            if (!pattern.matcher(data.trim()).matches()) {
                throw new Validator.InvalidValueException(formatError + "name@server.domain");
            }
        });

        Button makeRegister = new Button("Зарегистрироваться", FontAwesome.CHECK);
        makeRegister.setDescription("Нажимая эту кнопку Вы соглашаетесь на обработку персональных данных и несёте "
                + "ответственность за корректность предоставленных Вами данных");
        makeRegister.setWidth("100%");
        makeRegister.addClickListener(ev -> {
            boolean validForm = name.isValid() && school.isValid() && phone.isValid() && email.isValid();
            if (validForm) {
                Session session = HibernateUtil.getSessionFactory().openSession();
                Transaction transaction = null;
                try {
                    transaction = session.beginTransaction();
                    Integer id = (Integer) session.save(student);
                    transaction.commit();
                    Notification successNotification = new Notification("Поздравляем с успешной регистрацией!", "О начале занятий Вы будете уведомлены "
                            + "по указанным Вами контактным данным", Notification.Type.HUMANIZED_MESSAGE, false);
                    successNotification.setDelayMsec(Notification.DELAY_FOREVER);
                    successNotification.show(getUI().getPage());
                    header.addCount();
                }
                catch (HibernateException ex) {
                    if (transaction != null) {
                        transaction.rollback();
                    }
                    ex.printStackTrace();
                    Notification errorNotification = new Notification("Возникла ошибка!", "Сообщите в службу поддержки\n" + ex.getCause().getLocalizedMessage(), Notification.Type.ERROR_MESSAGE, false);
                    errorNotification.show(getUI().getPage());
                }
                finally {
                    session.close();
                }
            }
            else {
                Notification errorNotification = new Notification("Ошибка!", "\nНекоторые поля формы заполнены неверно!\nИсправьте ошибки и повторите "
                        + "попытку", Notification.Type.ERROR_MESSAGE, false);
                errorNotification.show(getUI().getPage());
            }
        });

        VerticalLayout vl = new VerticalLayout();
        vl.addComponents(name, school, classLayout, phone, email, new VerticalSplitPanel(email, makeRegister), makeRegister);
        vl.setSpacing(true);
        vl.setImmediate(true);
        setStyleName("ps-form");
        vl.setMargin(true);
        setCompositionRoot(vl);
    }

    private Student student;
    private final Header header;

}

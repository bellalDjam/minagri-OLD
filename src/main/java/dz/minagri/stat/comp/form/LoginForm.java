package dz.minagri.stat.comp.form;

import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

public class LoginForm extends VerticalLayout {

    /**
	 * 
	 */
	private static final long serialVersionUID = 3107726977454285307L;

	public LoginForm(LoginCallback callback) {
        setSizeFull();
        
        VerticalLayout centerLayout = new VerticalLayout();
        centerLayout.setSizeUndefined();
        
        TextField username = new TextField("Username");
        centerLayout.addComponent(username);

        PasswordField password = new PasswordField("Password");
        centerLayout.addComponent(password);

        Button login = new Button("Login", evt -> {
            String pword = password.getValue();
            password.setValue("");
            if (!callback.login(username.getValue(), pword)) {
                Notification.show("Login failed");
                username.focus();
            }
        });
        login.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        login.addStyleName(ValoTheme.BUTTON_PRIMARY);
        centerLayout.addComponent(login);
        addComponent(centerLayout);
        setComponentAlignment(centerLayout, Alignment.MIDDLE_CENTER);
    }

    @FunctionalInterface
    public interface LoginCallback {

        boolean login(String username, String password);
    }
}

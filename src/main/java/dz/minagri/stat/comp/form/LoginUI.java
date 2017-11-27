/**
 * 
 */
package dz.minagri.stat.comp.form;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * @author bellal djamel
 *
 */
@SpringUI(path = "/login")
@Title("LoginPage")
@Theme("valo")
public class LoginUI  extends UI {

    /**
	 * 
	 */
	private static final long serialVersionUID = 890454452441128129L;
	TextField user;
    PasswordField password;
    Button loginButton = new Button("Login", this::loginButtonClick);
    private static final String username = "username";
    private static final String passwordValue = "test123";

    @Override
    protected void init(VaadinRequest request) {
        setSizeFull();

        user = new TextField("User:");
        user.setWidth("300px");
        user.setPlaceholder("Your username");

        password = new PasswordField("Password:");
        password.setWidth("300px");
        password.setValue("");

        VerticalLayout fields = new VerticalLayout(user, password, loginButton);
        fields.setCaption("Please login to access the application");
        fields.setSpacing(true);
        fields.setMargin(new MarginInfo(true, true, true, false));
        fields.setSizeUndefined();

        VerticalLayout uiLayout = new VerticalLayout(fields);
        uiLayout.setSizeFull();
        uiLayout.setComponentAlignment(fields, Alignment.MIDDLE_CENTER);
        setFocusedComponent(user);

        setContent(uiLayout);
    }

    public void loginButtonClick(Button.ClickEvent e) {
//       Authentication auth = new UsernamePasswordAuthenticationToken(user.getValue(),password.getValue());
//       Authentication authenticated = daoAuthenticationProvider.authenticate(auth);
//       SecurityContextHolder.getContext().setAuthentication(authenticated);
//
////redirect to main application
//getPage().setLocation("/main");
    }

}
